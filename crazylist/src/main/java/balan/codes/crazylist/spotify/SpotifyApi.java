package balan.codes.crazylist.spotify;

import balan.codes.crazylist.spotify.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class SpotifyApi {
//    This base Url have to be passed to the builder of the webClient
//    private static final String BASE_DOMAIN_URL = "https://api.spotify.com/v1";
    @Autowired
    WebClient webClient;

//    public SpotifyApi(WebClient webClient) {
//        this.webClient = webClient;
//    }

    public List<ItemFromPlaylist> getUserPlaylist() {
        List<ItemFromPlaylist> playlists = new ArrayList<>();
        boolean existMorePlaylist = true;
        int offset = 0;
        int limit = 50;
        while(existMorePlaylist){
            List<ItemFromPlaylist> playlistsFromSpotify = getUserPlaylistPortion(offset, limit);
            playlists.addAll(playlistsFromSpotify);
            if(playlistsFromSpotify.size()<49){
                existMorePlaylist = false;
            }
            offset = offset + 50;
        }
        return playlists;
    }
    private List<ItemFromPlaylist> getUserPlaylistPortion(int offset, int limit) {
        String resourceUri = "/me/playlists";
        String body = webClient
                .get()
//                .uri(resourceUri)

                .uri(uf->
                        uf.path(resourceUri)
                                .queryParam("offset", offset)
                                .queryParam("limit", limit)
                                .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        UriBuilder uriBuilder;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootFromPlaylist rootFromMusicOfPlaylist = objectMapper.readValue(body, RootFromPlaylist.class);
            System.out.println(rootFromMusicOfPlaylist.items);
            return rootFromMusicOfPlaylist.items;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Track> getMusicsFromPlaylist(String playlistId){
        List<Track> tracks = new ArrayList<>();
        boolean existMoreTracks = true;
        int offset = 0;
        int limit = 50;
        while(existMoreTracks){
            List<Track> tracksFromSpotify = getMusicsFromPlaylistPortion(playlistId, offset, limit);
            tracks.addAll(tracksFromSpotify);
            if(tracksFromSpotify.size()<49){
                existMoreTracks = false;
            }
            offset = offset + 50;
        }
        return tracks;
    }
    private List<Track> getMusicsFromPlaylistPortion(String playlistId, int offset, int limit){
        String resourceUri ="/playlists/{playlistId}/tracks";
        String body = webClient
                .get()
                .uri(uf->
                        uf.path(resourceUri)
                                .queryParam("offset", offset)
                                .queryParam("limit", limit)
                                .build(playlistId)
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.setDateFormat(dateFormat);
            RootFromMusicOfPlaylist rootMusicsFromList = objectMapper.readValue(body, RootFromMusicOfPlaylist.class);
            System.out.println(rootMusicsFromList.items);
            List<Track> tracks = new ArrayList<>();
            rootMusicsFromList.items.forEach((t) -> tracks.add(t.track));
            return tracks;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Track> getUserLiked(){
        List<Track> tracks = new ArrayList<>();
        boolean existMoreTracks = true;
        int offset = 0;
        int limit = 50;
        while(existMoreTracks){
            List<Track> tracksFromSpotify = getUserLikedPortion(offset, limit);
            tracks.addAll(tracksFromSpotify);
            if(tracksFromSpotify.size()<49){
                existMoreTracks = false;
            }
            offset = offset + 50;
        }
        return tracks;
    }
    private List<Track> getUserLikedPortion(int offset, int limit){
        String resourceUri = "/me/tracks";
        String body = webClient
                .get()
                .uri(uf->
                        uf.path(resourceUri)
                                .queryParam("offset", offset)
                                .queryParam("limit", limit)
                                .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootFromUserLiked rootFromUserLiked = objectMapper.readValue(body, RootFromUserLiked.class);
            System.out.println(rootFromUserLiked.items);
            List<Track> tracks = new ArrayList<>();
            rootFromUserLiked.items.forEach((t) -> tracks.add(t.track));
            return tracks;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String createPlaylist(String name, String description){
//        TODO para setiembre cuando salga java 21 :P
//        String bodyContent2 = STR."""
//                {
//                    "name": "\{name}",
//                    "description": "\{description}",
//                    "public": false
//                }
//                """;

        String resourceUri = "/users/{userId}/playlists";

        String bodyContent = String.format("""
                {
                    "name": "%s",
                    "description": "%s",
                    "public": false
                }
                """, name, description);

        String userId = getUserId();
        String body = webClient
                .post()
                .uri(uf->
                        uf.path(resourceUri)
                                .build(userId)
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bodyContent))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootFromPlaylistCreated rootFromPlaylistCreated = objectMapper.readValue(body, RootFromPlaylistCreated.class);
            System.out.println(rootFromPlaylistCreated.id);
            return rootFromPlaylistCreated.id;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertMusic(String playlistId, List<String> tracksURI){
        insertMusicInternal(playlistId, tracksURI);
    }

    private void insertMusicInternal(String playlistId, List<String> tracksURI){
        String resourceUri = "/playlists/{playlistId}/tracks";
        StringBuilder tracksUriToSent = new StringBuilder();
        boolean firstTime = true;
        for (String t: tracksURI) {
            if(!firstTime){
                tracksUriToSent.append(",");
            }
            firstTime = false;
            tracksUriToSent.append("\"");
            tracksUriToSent.append(t);
            tracksUriToSent.append("\"");
        }
        String bodyContent = String.format("""
                {
                    "uris": [%s],
                    "position": 0
                }
                """, tracksUriToSent);
        System.out.println(bodyContent) ;
        String body = webClient
                .post()
                .uri(uf->
                        uf.path(resourceUri)
                                .build(playlistId)
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bodyContent))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    public void deletePlaylist(String playlistId){
        String resourceUri = "/playlists/{playlistId}/followers";
        String body = webClient
                .delete()
                .uri(uf->
                        uf.path(resourceUri)
                                .build(playlistId)
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    private String getUserId() {
        String resourceUri = "/me";
        String body = webClient
                .get()
                .uri(resourceUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String pattern = "\"id\"\\s*:\\s*\"([^\"]+)\"";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(body);
        if (matcher.find()) {
            String id = matcher.group(1);
            System.out.println(id);
            return id;
        }
        return null;
    }

}
