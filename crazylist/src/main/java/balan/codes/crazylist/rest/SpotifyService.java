package balan.codes.crazylist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController()
public class SpotifyService {
    @Autowired
    WebClient webClient;

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
        String resourceUri = "https://api.spotify.com/v1/me/playlists?limit=50";
        String body = webClient
                .get()
                .uri(resourceUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
//           System.out.println(body);
        return body;
    }
    @GetMapping(value = "/user-liked/{offset}")
    public String getUserLiked(@PathVariable("offset") Integer listOffset ) {
        String resourceUri = "https://api.spotify.com/v1/me/tracks?limit=50&offset=" + listOffset;
        String body = webClient
                .get()
                .uri(resourceUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return body;
    }

    @GetMapping(value = "/create-playlist")
    public String createPlaylist() {
        String resourceUri = "https://api.spotify.com/v1/users/" + getUserId() + "/playlists";
        String body = webClient
                .post()
                .uri(resourceUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{\n" +
                        "    \"name\": \"New Playlist\",\n" +
                        "    \"description\": \"New playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(body);
        return body;
    }

    @GetMapping(value = "/add-music-in-playlist/{playlistid}")
    public String addMusicToPlaylist(@PathVariable("playlistid") String playlistId) {
        String resourceUri = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
        String body = webClient
                .post()
                .uri(resourceUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{\n" +
                        "    \"uris\": [\n" +
                        "        \"spotify:track:3PG9Q89FOydXO8rJesUewH\"\n" +
                        "    ],\n" +
                        "    \"position\": 0\n" +
                        "}"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(body);
        return body;
    }



    @GetMapping(value = "/musics-of-playlist/{playlistid}")
    public String getMusicsFromPlaylist(@PathVariable("playlistid") String playlistId) {
        String resourceUri = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
        String body = webClient
                .get()
                .uri(resourceUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return body;
    }


    @GetMapping(value = "/delete-playlist/{playlistid}")
    public String deletePlaylist(@PathVariable("playlistid") String playlistId) {
        String resourceUri = "https://api.spotify.com/v1/playlists/" + playlistId + "/followers";
        String body = webClient
                .delete()
                .uri(resourceUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return body;
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

    public String getUserId() {
        String resourceUri = "https://api.spotify.com/v1/me";
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
