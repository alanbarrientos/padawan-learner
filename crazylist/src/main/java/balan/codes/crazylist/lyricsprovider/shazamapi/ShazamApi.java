package balan.codes.crazylist.lyricsprovider.shazamapi;

import balan.codes.crazylist.lyricsprovider.LyricsProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.stereotype.Component;

import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
public class ShazamApi implements LyricsProvider {
    private static final String SEARCH_KEY_FIRST_PART_URL = "https://www.shazam.com/services/search/v4/en-US/PY/web/search";
    private static final String LYRICS_URL = "https://www.shazam.com/discovery/v5/en-US/PY/web/-/track/";

    @Override
    public String fetchLyrics(String songName) {
        return getMetadataSong(songName).lyrics;
    }

    public ShazamMetadata getMetadataSong(String songName){
        songName = songName.replaceAll("\\(.*\\)","");
        songName = songName.replaceAll("feat\\.","");
        Integer key = getMusicId(songName);
        if(key == null){
            return new ShazamMetadata();
        }
        System.out.println("beforeCallShazam");
        String body = null;
        try {
            body = HttpRequest.get(LYRICS_URL + key, true,
                    "shazamapiversion", "v3",
                    "video", "v3"
            ).readTimeout(2000).body();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("afterCallShazam");
        RawMetadataModel responseLyrics = null;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            responseLyrics = objectMapper.readValue(body, RawMetadataModel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ShazamMetadata shazamMetadata = extractRelevantData(responseLyrics);
        return shazamMetadata;

    }

    private ShazamMetadata extractRelevantData(RawMetadataModel responseLyrics){
        String releaseDate = responseLyrics.releasedate;
        ArrayList<String> lyrics = responseLyrics.sections.get(1).text;
        ShazamMetadata insert = new ShazamMetadata();
        insert.setHaveLyrics(false);
        if(lyrics != null) {
            insert.setLyrics(String.join(" ", lyrics));
            insert.setHaveLyrics(true);
        }
        try{
            insert.releaseDate = new SimpleDateFormat("dd-MM-yyyy").parse(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
        return insert;
    }

    private Integer getMusicId(String songName){
        String responseContentUrl = null;
        try {
            HttpRequest keySearchResponse = HttpRequest.get(SEARCH_KEY_FIRST_PART_URL, true,
                    "term", songName,
                    "numResults", 1,
                    "offset", 0,
                    "types", "artists,songs",
                    "limit", 1
            ).readTimeout(2000);
            responseContentUrl = keySearchResponse.body();
        }catch (Exception e){
            e.printStackTrace();
        }
        Integer id = null;
        System.out.println("This is the response of shazam when get id" + responseContentUrl);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            SearchResultModel responseKey = objectMapper.readValue(responseContentUrl, SearchResultModel.class);
            id = Integer.parseInt(responseKey.tracks.hits.get(0).track.key);
        } catch (Exception e) {
                System.out.println("This is the response of shazam when get id" + responseContentUrl);
                e.printStackTrace();
        }
        if(id==null){
            System.out.println("The id is null");
        }
        return id;
    }


    private static class SearchResultModel {
        public Tracks tracks;
        public static class Tracks{
            public ArrayList<Hit> hits;
        }
        public static class Hit{
            public Track track;
        }
        public static class Track{
            public String key;
        }
    }
    private static class RawMetadataModel{
        public ArrayList<Section> sections;
        public String releasedate;
        public static class Section{
            public ArrayList<String> text;
        }
    }


}
