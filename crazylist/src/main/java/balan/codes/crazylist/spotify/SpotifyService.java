package balan.codes.crazylist.spotify;

import balan.codes.crazylist.spotify.models.ItemFromPlaylist;
import balan.codes.crazylist.spotify.models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController()
public class SpotifyService {
//    @Autowired
//    WebClient webClient;
   @Autowired
    SpotifyApi spotifyApi;

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

//    @GetMapping(value = "/org-sync")
//    public boolean organizeSync(){
////        SpotifyApi spotifyApi = new SpotifyApi(webClient);
//
////      -----------------------------------------------------------------------
//        //        todo: If Exist this playlist delete
////      -----------------------------------------------------------------------
//
////        Obtain all tracks of the user
////      -----------------------------------------------------------------------
//        List<ItemFromPlaylist> playlists = spotifyApi.getUserPlaylist();
//        Set<Track> nonDuplicateTracks = new HashSet<>();
//        for (ItemFromPlaylist p: playlists  ) {
//            List<Track> trackList = spotifyApi.getMusicsFromPlaylist(p.id);
//            nonDuplicateTracks.addAll(trackList);
//        }
//        List<Track> tracksFromLiked = spotifyApi.getUserLiked();
//        nonDuplicateTracks.addAll(tracksFromLiked);
//        List<Track> nonDuplicatedTrackList = new ArrayList<>();
//        nonDuplicatedTrackList.addAll(nonDuplicateTracks);
////      -----------------------------------------------------------------------
//
//
////      -----------------------------------------------------------------------
//      List<String> idsOfTrackReleaseOrder = new ArrayList<>();
//      int firstsReleaseId = 0;
//        for (int i=0; i<nonDuplicatedTrackList.size(); i++){
//            for(int k=0; k<nonDuplicatedTrackList.size(); i++){
//                if(nonDuplicatedTrackList.get(k).album.release_date)
//            }
//            idsOfTrackReleaseOrder.add(nonDuplicatedTrackList.get())
//        }
//
////        Create all necesary Playlists for the Organize
////      -----------------------------------------------------------------------
//        String releasePlaylistId = spotifyApi.createPlaylist("cl_Release", "A Playlist that organize the song based on date or release");
//        String EnglishPlaylistId = spotifyApi.createPlaylist("cl_English", "A Playlist of english Songs that organize the song based on date or release");
//        String SpanishPlaylistId = spotifyApi.createPlaylist("cl_Spanish", "A Playlist of Spanish Songs that organize the song based on date or release");
//        String OtherLanguagesPlaylistId = spotifyApi.createPlaylist("cl_Other_Languages","A Playlist Other languages than Spanish or English that organize the song based on date or release");
////      -----------------------------------------------------------------------
//
//
//
//
//
//        return true;
//    }

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
