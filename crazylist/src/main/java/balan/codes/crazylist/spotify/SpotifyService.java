package balan.codes.crazylist.spotify;

import balan.codes.crazylist.languagedetector.TextAnalysisLanguageDetector;
import balan.codes.crazylist.lyricsprovider.shazamapi.ShazamApi;
import balan.codes.crazylist.lyricsprovider.shazamapi.ShazamMetadata;
import balan.codes.crazylist.spotify.models.ItemFromPlaylist;
import balan.codes.crazylist.spotify.models.RootFromPlaylistCreated;
import balan.codes.crazylist.spotify.models.Track;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController()
public class SpotifyService {
//    @Autowired
//    WebClient webClient;
   @Autowired
    SpotifyApi spotifyApi;
   @Autowired
    ShazamApi shazamApi;
   @Autowired
    TextAnalysisLanguageDetector languageDetectorService;

//    @GetMapping(value = "/user-top-artists")
//    public String getUserTopArtists() {
//            String resourceUri = "https://api.spotify.com/v1/me/top/artists";
//            String body = webClient
//                    .get()
//                    .uri(resourceUri)
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block();
////            System.out.println(body);
//        return body;
//    }
    @GetMapping(value = "/user-playlists")
    public String getUserPlaylist() {
//        SpotifyApi spotifyApi = new SpotifyApi(webClient);
        return Arrays.toString(spotifyApi.getUserPlaylist().toArray());
    }
    @GetMapping(value = "/user-liked/{offset}")
    public String getUserLiked(@PathVariable("offset") Integer listOffset ) {
//        SpotifyApi spotifyApi = new SpotifyApi(webClient);
        return Arrays.toString(spotifyApi.getUserLiked().toArray());
    }

    @GetMapping(value = "/create-playlist")
    public String createPlaylist() {
//        SpotifyApi spotifyApi = new SpotifyApi(webClient);
        return spotifyApi.createPlaylist("Release", "A list of all music order by Release");
    }

    @GetMapping(value = "/add-music-in-playlist/{playlistid}")
    public String addMusicToPlaylist(@PathVariable("playlistid") String playlistId){
//        SpotifyApi spotifyApi = new SpotifyApi(webClient);
        List<String> tracksIds = new ArrayList<>();
        tracksIds.add("spotify:track:3PG9Q89FOydXO8rJesUewH");
        tracksIds.add("spotify:track:3PG9Q89FOydXO8rJesUewH");
        spotifyApi.insertMusic(playlistId, tracksIds);
        return "Inserted Successfully";
    }



    @GetMapping(value = "/musics-of-playlist/{playlistid}")
    public String getMusicsFromPlaylist(@PathVariable("playlistid") String playlistId) throws ParseException {
//        SpotifyApi spotifyApi = new SpotifyApi(webClient);
        return Arrays.toString(spotifyApi.getMusicsFromPlaylist(playlistId).toArray());
    }


    @GetMapping(value = "/delete-playlist/{playlistid}")
    public String deletePlaylist(@PathVariable("playlistid") String playlistId) {
//        SpotifyApi spotifyApi = new SpotifyApi(webClient);
        spotifyApi.deletePlaylist(playlistId);
        return "Playlist Deleted Successfully";
    }

    @GetMapping(value = "/org-sync")
    public boolean organizeSync(){
        System.out.println("Entre en organizar");
        String userId = spotifyApi.getUserId();
        String filePath = "C:\\Users\\alanb\\DEV\\padawan-learner\\crazylist\\src\\main\\java\\balan\\codes\\crazylist\\spotify\\cache.txt";






        File file = new File(filePath);

        if (file.exists()) {
            System.out.println("File exists.");
        } else {
            TrackEssentialMetadataList cacheTracks = new TrackEssentialMetadataList();
            TrackEssentialMetadata trackEssentialMetadata = new TrackEssentialMetadata();
            cacheTracks.trackEssentialMetadataList.put("someting", trackEssentialMetadata);
            String payload = "";
            try {
                payload = new ObjectMapper().writeValueAsString(cacheTracks);
            }catch (Exception e){
                e.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(payload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        SpotifyApi spotifyApi = new SpotifyApi(webClient);

//      -----------------------------------------------------------------------
        //        todo: If Exist this playlist delete
//      -----------------------------------------------------------------------

//        Obtain all tracks of the user
//      -----------------------------------------------------------------------
        List<ItemFromPlaylist> playlists = spotifyApi.getUserPlaylist();
        Set<Track> nonDuplicateTracks = new HashSet<>();
        for (ItemFromPlaylist p: playlists  ) {
            if(userId.equals(p.owner.id) && !p.id.equals("1xoRekWXQAGjfetfFud8IR")){
                List<Track> trackList = spotifyApi.getMusicsFromPlaylist(p.id);
                nonDuplicateTracks.addAll(trackList);
            }
        }
        List<Track> tracksFromLiked = spotifyApi.getUserLiked();
        nonDuplicateTracks.addAll(tracksFromLiked);
        List<Track> nonDuplicatedTrackList = new ArrayList<>(nonDuplicateTracks);
//      -----------------------------------------------------------------------





        StringBuilder jsonStringFromFile = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringFromFile.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonContent = jsonStringFromFile.toString();
        Map<String, TrackEssentialMetadata> cacheEM = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            cacheEM = objectMapper.readValue(jsonContent, TrackEssentialMetadataList.class).trackEssentialMetadataList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }





//        Obtain all metadata of tracks
//      -----------------------------------------------------------------------
        List<TrackEssentialMetadata> tracksEssentialMetadata  = new ArrayList<>();
        int counter = 0;
        for (Track track : nonDuplicatedTrackList) {
            if(cacheEM.containsKey(track.uri)){
                System.out.println(counter++);
                if (cacheEM.get(track.uri).getLanguage() != null && cacheEM.get(track.uri).getReleaseDate() != null){
                    tracksEssentialMetadata.add(cacheEM.get(track.uri));
                }
                continue;
            }
            System.out.println(counter);
            counter++;
            TrackEssentialMetadata tem = new TrackEssentialMetadata();
            tem.setName(track.name + " " + track.artists.get(0).name);
            tem.setSpotifyId(track.uri);
            System.out.println(tem.getName());
//            if(tem.getName().equals("Unspeakable Strudel Mitchell Doxon")){
//                continue;
//            }
            try {
                Thread.sleep(1000); // Sleep for a short interval before checking the condition again
            } catch (InterruptedException e) {
                // Handle the exception
                e.printStackTrace();
            }
            ShazamMetadata trackShazamMetadata = shazamApi.getMetadataSong(tem.getName());
            tem.setReleaseDate(trackShazamMetadata.getReleaseDate());
            if(trackShazamMetadata.getLyrics() != null && trackShazamMetadata.getReleaseDate() != null){
                String language = languageDetectorService.detectLanguageOfText(trackShazamMetadata.getLyrics());
                tem.setLanguage(language);
                System.out.println(trackShazamMetadata.getReleaseDate());
                tracksEssentialMetadata.add(tem);
            }
            cacheEM.put(tem.getSpotifyId(), tem);
//            if(trackShazamMetadata.getReleaseDate() != null){
//            }
            TrackEssentialMetadataList trackEssentialMetadataList = new TrackEssentialMetadataList();
            trackEssentialMetadataList.setTrackEssentialMetadataList(cacheEM);
            String payload = "";
            try {
                payload = new ObjectMapper().writeValueAsString(trackEssentialMetadataList);
                Files.write(Path.of(filePath), payload.getBytes(StandardCharsets.UTF_8));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//      -----------------------------------------------------------------------















//      Order By Release
//      -----------------------------------------------------------------------
//        List<TrackEssentialMetadata> trackEssentialMetadataInReleaseOrder = new ArrayList<>(tracksEssentialMetadata);
        List<TrackEssentialMetadata> trackEssentialMetadataInReleaseOrder = new ArrayList<>();
      trackEssentialMetadataInReleaseOrder = tracksEssentialMetadata.stream()
              .sorted(Comparator.comparing(TrackEssentialMetadata::getReleaseDate).reversed()).
              collect(Collectors.toList());

        System.out.println(Arrays.toString(trackEssentialMetadataInReleaseOrder.toArray()));

        List<String> listToInsertReleaseOrder = new ArrayList<>();
        for (TrackEssentialMetadata t: trackEssentialMetadataInReleaseOrder) {

            listToInsertReleaseOrder.add(t.getSpotifyId());
        }
//        -----------------------------------------------------------------------




//        Order By language English by date
//        -----------------------------------------------------------------------
        List<TrackEssentialMetadata> trackEssentialMetadataEnglish = new ArrayList<>();
        trackEssentialMetadataEnglish = trackEssentialMetadataInReleaseOrder.stream()
                .filter(t -> t.getLanguage().equals("en"))
                .collect(Collectors.toList());
        List<String> listToInsertEnglish = new ArrayList<>();
        for (TrackEssentialMetadata t: trackEssentialMetadataEnglish) {

            listToInsertEnglish.add(t.getSpotifyId());
        }

//        -----------------------------------------------------------------------

        //        Order By language Spanish by date
//        -----------------------------------------------------------------------
        List<TrackEssentialMetadata> trackEssentialMetadataSpanish = new ArrayList<>();
        trackEssentialMetadataSpanish = trackEssentialMetadataInReleaseOrder.stream()
                .filter(t -> t.getLanguage().equals("es"))
                .collect(Collectors.toList());
        List<String> listToInsertSpanish = new ArrayList<>();
        for (TrackEssentialMetadata t: trackEssentialMetadataSpanish) {

            listToInsertSpanish.add(t.getSpotifyId());
        }

//        -----------------------------------------------------------------------



        //        Order By Other language and by date
//        -----------------------------------------------------------------------
        List<TrackEssentialMetadata> trackEssentialMetadataOrderByLanguageAndReleaseDate = new ArrayList<>();
        trackEssentialMetadataOrderByLanguageAndReleaseDate = tracksEssentialMetadata.stream()
                .sorted(Comparator.comparing(TrackEssentialMetadata::getLanguage).thenComparing(Comparator.comparing(TrackEssentialMetadata::getReleaseDate).reversed())).
                collect(Collectors.toList());

        List<TrackEssentialMetadata> trackEssentialMetadataOthers = new ArrayList<>();
        trackEssentialMetadataOthers = trackEssentialMetadataOrderByLanguageAndReleaseDate.stream()
                .filter(t -> !t.getLanguage().equals("en"))
                .filter(t -> !t.getLanguage().equals("es"))
                .collect(Collectors.toList());
        List<String> listToInsertOthers = new ArrayList<>();
        for (TrackEssentialMetadata t: trackEssentialMetadataOthers) {

            listToInsertOthers.add(t.getSpotifyId());
        }

//        -----------------------------------------------------------------------
//        Delete playlists

        List<ItemFromPlaylist> toDelete = spotifyApi.getUserPlaylist();
        List<String> idPlaylistToDelete = toDelete.stream()
                .filter(p -> p.name.contains("cl_")).map(p -> p.id)
                .collect(Collectors.toList());

        for (String idP: idPlaylistToDelete) {
            spotifyApi.deletePlaylist(idP);
        }



//        Create all necesary Playlists for the Organize
//      -----------------------------------------------------------------------
        String releasePlaylistId = spotifyApi.createPlaylist("cl_Release", "A Playlist that organize the song based on date or release");
        String englishPlaylistId = spotifyApi.createPlaylist("cl_English", "A Playlist of english Songs that organize the song based on date or release");
        String spanishPlaylistId = spotifyApi.createPlaylist("cl_Spanish", "A Playlist of Spanish Songs that organize the song based on date or release");
        String otherLanguagesPlaylistId = spotifyApi.createPlaylist("cl_Other_Languages","A Playlist Other languages than Spanish or English that organize the song based on date or release");
//      -----------------------------------------------------------------------
        spotifyApi.insertMusic(releasePlaylistId, listToInsertReleaseOrder);
        spotifyApi.insertMusic(englishPlaylistId, listToInsertEnglish);
        spotifyApi.insertMusic(spanishPlaylistId, listToInsertSpanish);
        spotifyApi.insertMusic(otherLanguagesPlaylistId, listToInsertOthers);




        return true;
    }



//    @GetMapping(value = "/follow-playlist")
//    public String followPlaylist() {
//        String playlistId = "5UKOxOFaLPHX4kpNfiNiQe";
//        String resourceUri = "https://api.spotify.com/v1/playlists/" + playlistId + "/followers";
//        String body = webClient
//                .put()
//                .uri(resourceUri)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue("{\n" +
//                        "    \"public\": false\n" +
//                        "}"))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//        return body;
//    }

}
