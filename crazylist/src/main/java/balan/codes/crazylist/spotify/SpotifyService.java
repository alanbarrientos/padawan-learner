package balan.codes.crazylist.spotify;

import balan.codes.crazylist.languagedetector.TextAnalysisLanguageDetector;
import balan.codes.crazylist.lyricsprovider.shazamapi.ShazamApi;
import balan.codes.crazylist.lyricsprovider.shazamapi.ShazamMetadata;
import balan.codes.crazylist.model.FailLogs;
import balan.codes.crazylist.model.MusicCache;
import balan.codes.crazylist.repository.FailLogsRepository;
import balan.codes.crazylist.repository.MusicCacheRepository;
import balan.codes.crazylist.spotify.dto.Artist;
import balan.codes.crazylist.spotify.dto.ItemFromPlaylist;
import balan.codes.crazylist.spotify.dto.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController()
public class SpotifyService {
    private static String FILE_CACHE_PATH = "C:\\Users\\alanb\\DEV\\padawan-learner\\crazylist\\src\\main\\java\\balan\\codes\\crazylist\\spotify\\cache.txt";

   @Autowired
    SpotifyApi spotifyApi;
   @Autowired
    ShazamApi shazamApi;
   @Autowired
    TextAnalysisLanguageDetector languageDetectorService;
    @Autowired
    MusicCacheRepository musicCacheRepository;
    @Autowired
    FailLogsRepository failLogsRepository;

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
        System.out.println("-----------------------Organizando.....-----------------------------");
        String userId = spotifyApi.getUserId();

        System.out.println("-----------------------Borrando Listas previas-----------------------------");
        deletePreviusPlaylistCreatedByUs();

        System.out.println("-----------------------Obteniendo todos los tracks no duplicados-----------------------------");
        List<Track> nonDuplicatedTrackList = getNonDuplicatedTrackList(userId);

        System.out.println("-----------------------Recolectado metadata de los tracks-----------------------------");
        List<TrackEssentialMetadata> tracksEssentialMetadata = getTrackEssentialMetadata(nonDuplicatedTrackList);

        System.out.println("-----------------------Ordenando por release-----------------------------");
        List<TrackEssentialMetadata> trackEssentialMetadataInReleaseOrder = tracksEssentialMetadata.stream()
              .sorted(Comparator.comparing(TrackEssentialMetadata::getReleaseDate).reversed()).
              collect(Collectors.toList());

        System.out.println("-----------------------Creando playlist e insertando byRelease-----------------------------");
        String playlistReleaseId = spotifyApi.createPlaylist("cl_Release", "A Playlist that organize the song based on date of release");
        List<String> listToInsertReleaseOrder = getListOfIdSpotifyToInsert(trackEssentialMetadataInReleaseOrder);
        spotifyApi.insertMusic(playlistReleaseId, listToInsertReleaseOrder);


        System.out.println("-----------------------Ordenando by language e insertando para cada lenguage significativo para el usuario-----------------------------");
        Map<String, Integer> languagesAndCuantityOfMusics = getCuantityOfMusicsByLanguguage(trackEssentialMetadataInReleaseOrder);

        List<String> idOfOtherLanguagesTracks = new ArrayList<>();
        for (Map.Entry<String, Integer> l: languagesAndCuantityOfMusics.entrySet()) {
            List<TrackEssentialMetadata> trackEssentialMetadataOtherLanguages;
            if(l.getValue()<20){
                trackEssentialMetadataOtherLanguages = trackEssentialMetadataInReleaseOrder.stream()
                        .filter(t -> t.getLanguage().equals(l.getKey()))
                        .collect(Collectors.toList());
                idOfOtherLanguagesTracks.addAll(getListOfIdSpotifyToInsert(trackEssentialMetadataOtherLanguages));
                continue;
            }
            insertNewLanguageIntoNewPlaylist(trackEssentialMetadataInReleaseOrder, l.getKey());
        }

        System.out.println("-----------------------Creando playlist e insertando OtherLanguages-----------------------------");
        String playlistOtherLanguagesId = spotifyApi.createPlaylist("cl_OtherLanguages", "A Playlist that organize the song based on date of release Of Other languages");
        spotifyApi.insertMusic(playlistOtherLanguagesId, idOfOtherLanguagesTracks);

        return true;
    }

    private static Map<String, Integer> getCuantityOfMusicsByLanguguage(List<TrackEssentialMetadata> trackEssentialMetadataInReleaseOrder) {
        Map<String, Integer> languagesAndCuantityOfMusics = new HashMap<>();
        for (TrackEssentialMetadata t : trackEssentialMetadataInReleaseOrder) {
            if(!languagesAndCuantityOfMusics.containsKey(t.getLanguage())){
                languagesAndCuantityOfMusics.put(t.getLanguage(), 0);
            }
            languagesAndCuantityOfMusics.put(t.getLanguage(), languagesAndCuantityOfMusics.get(t.getLanguage())+1);
        }
        return languagesAndCuantityOfMusics;
    }

    private List<Track> getNonDuplicatedTrackList(String userId) {
        List<ItemFromPlaylist> playlists = spotifyApi.getUserPlaylist();
        Set<Track> nonDuplicateTracks = new HashSet<>();
        for (ItemFromPlaylist p: playlists  ) {
//            if(userId.equals(p.owner.id) && !p.id.equals("1xoRekWXQAGjfetfFud8IR")){
            if(userId.equals(p.owner.id)){
                List<Track> trackList = spotifyApi.getMusicsFromPlaylist(p.id);
                nonDuplicateTracks.addAll(trackList);
            }
        }
        List<Track> tracksFromLiked = spotifyApi.getUserLiked();
        nonDuplicateTracks.addAll(tracksFromLiked);
        return new ArrayList<>(nonDuplicateTracks);
    }

    private void insertNewLanguageIntoNewPlaylist(List<TrackEssentialMetadata> trackEssentialMetadataInReleaseOrder, String languageCode) {
        List<TrackEssentialMetadata> trackEssentialMetadata;
        trackEssentialMetadata = trackEssentialMetadataInReleaseOrder.stream()
                .filter(t -> t.getLanguage().equals(languageCode))
                .collect(Collectors.toList());
        List<String> listToInsert = getListOfIdSpotifyToInsert(trackEssentialMetadata);
        String playlistId = spotifyApi.createPlaylist("cl_" + languageCode, "A Playlist that organize the song based on a language and date of release");
        spotifyApi.insertMusic(playlistId, listToInsert);
    }

    private static List<String> getListOfIdSpotifyToInsert(List<TrackEssentialMetadata> trackEssentialMetadataInReleaseOrder) {
        List<String> listToInsertReleaseOrder = new ArrayList<>();
        for (TrackEssentialMetadata t: trackEssentialMetadataInReleaseOrder) {

            listToInsertReleaseOrder.add(t.getSpotifyId());
        }
        return listToInsertReleaseOrder;
    }

    private List<TrackEssentialMetadata> getTrackEssentialMetadata(List<Track> nonDuplicatedTrackList) {
        List<TrackEssentialMetadata> tracksEssentialMetadata  = new ArrayList<>();
        int counter = 0;
        for (Track track : nonDuplicatedTrackList) {
            MusicCache mc = musicCacheRepository.findMusicCacheBySpotifyId(track.uri);
            if(mc != null){
                System.out.println(counter++);
                if (mc.getLanguage() != null && mc.getReleaseDate() != null){
                    tracksEssentialMetadata.add(new TrackEssentialMetadata(mc.getSpotifyId(), mc.getName(), mc.getArtist(), mc.getReleaseDate(), mc.getLanguage(), mc.getHaveLyrics()));
                }
                continue;
            }
            System.out.println(counter);
            counter++;
            TrackEssentialMetadata tem = getMetaDataFromInternet(track.uri, track.name, track.artists);
            if(tem.getLanguage() != null && tem.getReleaseDate() != null){
                tracksEssentialMetadata.add(tem);
            }
            if(tem.getHaveLyrics() != null){
                musicCacheRepository.save(new MusicCache(tem.getSpotifyId(),tem.getName(), tem.getReleaseDate(), tem.getLanguage(), tem.getArtists(), tem.getHaveLyrics()));
            }else{
               failLogsRepository.save(new FailLogs("Music Not found", tem.toString()));
            }
        }
        return tracksEssentialMetadata;
    }

    private TrackEssentialMetadata getMetaDataFromInternet(String spotifyId, String musicName, List<Artist> artists){
        String nameOfArtists = artists.stream().map(artist -> artist.name).collect(Collectors.joining(", "));
        TrackEssentialMetadata tem = new TrackEssentialMetadata();
        tem.setName(musicName);
        tem.setSpotifyId(spotifyId);
        tem.setArtists(nameOfArtists);
        System.out.println(tem.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ShazamMetadata trackShazamMetadata = shazamApi.getMetadataSong(tem.getName() + " " + nameOfArtists);
        tem.setReleaseDate(trackShazamMetadata.getReleaseDate());
        if(trackShazamMetadata.getLyrics() != null){
            String language = languageDetectorService.detectLanguageOfText(trackShazamMetadata.getLyrics());
            tem.setLanguage(language);
        }
        tem.setHaveLyrics(trackShazamMetadata.getHaveLyrics());
        System.out.println(tem.getReleaseDate());
        return tem;
    }

    private void deletePreviusPlaylistCreatedByUs() {
        List<ItemFromPlaylist> toDelete = spotifyApi.getUserPlaylist();
        List<String> idPlaylistToDelete = toDelete.stream()
                .filter(p -> p.name.contains("cl_")).map(p -> p.id)
                .toList();

        for (String idP: idPlaylistToDelete) {
            spotifyApi.deletePlaylist(idP);
        }
    }


}
