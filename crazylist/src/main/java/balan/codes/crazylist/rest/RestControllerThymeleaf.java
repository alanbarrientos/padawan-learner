package balan.codes.crazylist.rest;

import balan.codes.crazylist.languagedetector.TextAnalysisLanguageDetector;
import balan.codes.crazylist.lyricsprovider.shazamapi.ShazamApi;
import balan.codes.crazylist.lyricsprovider.shazamapi.ShazamMetadata;
import balan.codes.crazylist.model.FailLogs;
import balan.codes.crazylist.model.MusicCache;
import balan.codes.crazylist.repository.FailLogsRepository;
import balan.codes.crazylist.repository.MusicCacheRepository;
import balan.codes.crazylist.spotify.RecordProcessing;
import balan.codes.crazylist.spotify.SpotifyApi;
import balan.codes.crazylist.spotify.TrackEssentialMetadata;
import balan.codes.crazylist.spotify.dto.Artist;
import balan.codes.crazylist.spotify.dto.ItemFromPlaylist;
import balan.codes.crazylist.spotify.dto.Track;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RestControllerThymeleaf {
    private static Long ESTIMATED_PER_MUSIC = 3000L;

    private static Map<String, RecordProcessing> recordProcessingMap = new HashMap<>();
    private final String UPLOAD_FOLDER = "C:\\Users\\alanb\\DEV\\padawan-learner\\crazylist\\src\\main\\java\\balan\\codes\\crazylist\\upload\\";


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
    @Autowired
    ShazamService shazamService;

    @GetMapping("/username")
    public String getUserName(){
        return spotifyApi.getUserName();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file) {
        StringBuilder sb = new StringBuilder();
        String userId = spotifyApi.getUserId();


        if (file.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        try {
            if (!Files.exists(Paths.get(UPLOAD_FOLDER + userId))) {
                try {
                    Files.createDirectories(Paths.get(UPLOAD_FOLDER + userId));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            List<ItemFromPlaylist> itemsFromPlaylist = spotifyApi.getUserPlaylist();
            String playlistId = null;
            for (ItemFromPlaylist p: itemsFromPlaylist) {
                if(p.name.equals("fromLocal")){
                    playlistId = p.id;
                }
            }
            if(playlistId==null){
                playlistId = spotifyApi.createPlaylist("fromLocal", "Its a playlist created by Crazylist where the music that you have before in your local machine are saved");
            }
            List<String> urisTrackFromPlaylist = spotifyApi.getMusicsFromPlaylist(playlistId).stream()
                    .map(t -> t.uri)
                    .toList();

            Files.copy(file.getInputStream(), Paths.get(UPLOAD_FOLDER + spotifyApi.getUserId(), file.getOriginalFilename()));
            sb.append("You successfully uploaded " + file.getOriginalFilename() + "!\n");
            String musicCutedPath = shazamService.cutMusic(UPLOAD_FOLDER + spotifyApi.getUserId() +"\\"+ file.getOriginalFilename());
            String musicName = shazamService.callShazam(musicCutedPath);
            String spotifyIdOfMusic = spotifyApi.getSpotifyIdOfMusic(musicName);
            if(!urisTrackFromPlaylist.contains(spotifyIdOfMusic)){
                spotifyApi.insertMusic(playlistId, new ArrayList<>(List.of(new String[]{spotifyIdOfMusic})));
            }
            File directory = new File(UPLOAD_FOLDER + userId);
            FileUtils.cleanDirectory(directory);
        } catch (IOException | RuntimeException e) {
           e.printStackTrace();
        }

        return new ResponseEntity("Successfully uploaded - " +
                file.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

//    @PostMapping("/upload/files")
//    public String handleFilesUpload(@RequestParam("files") MultipartFile files[]) {
//        System.out.println("entre en upload files");
//        StringBuilder sb = new StringBuilder();
//
//        for (MultipartFile file : files) {
//            if (!file.isEmpty()) {
//                try {
//                    if (!Files.exists(Paths.get(UPLOAD_FOLDER))) {
//                        try {
//                            Files.createDirectories(Paths.get(UPLOAD_FOLDER));
//                        } catch (IOException ioe) {
//                            ioe.printStackTrace();
//                        }
//                    }
//
//                    Files.copy(file.getInputStream(), Paths.get(UPLOAD_FOLDER, file.getOriginalFilename()));
//                    sb.append("You successfully uploaded " + file.getOriginalFilename() + "!\n");
//
////                    map.addAttribute("msg", sb.toString());
//                } catch (IOException | RuntimeException e) {
//                    sb.append("Failued to upload " + (file != null ? file.getOriginalFilename() : "") + " => "
//                            + e.getMessage() + String.valueOf(e) + "\n");
//
////                    map.addAttribute("msg", sb.toString());
//                }
//            } else {
//                sb.append("Failed to upload file\n");
////                map.addAttribute("msg", sb.toString());
//            }
//        }
//
//        return "file";
//    }

    @GetMapping("/info-org-sync")
    public String getInfoOrgSync(){
        String userId = spotifyApi.getUserId();
        if(recordProcessingMap.containsKey(userId)){
            if(recordProcessingMap.get(userId).getIsSuccessful()){
                if(new Date (recordProcessingMap.get(userId).getEstimated().getTime() + 120000).before(new Date())){
                    recordProcessingMap.remove(userId);
                }
                return "Revisa tu app tus musicas en Spotify fueron organizado correctamente 🙂";
            }
            if(recordProcessingMap.get(userId).getCallItAgain()){
//                return "Tranquilizate estamos procesando tu pedido \uD83D\uDE21\uD83D\uDE44" ;
                return "Continuamos Organizando tu pedido anterior";
            }
            return "Organizando...";
        }
        return"";
    }
    @GetMapping(value = "/org-sync")
    public String organizeSync(){
        String userId = spotifyApi.getUserId();
        if(recordProcessingMap.containsKey(userId) && !recordProcessingMap.get(userId).getIsSuccessful()){
            RecordProcessing rptemp = recordProcessingMap.get(userId);
            rptemp.setCallItAgain(true);
            recordProcessingMap.put(userId, rptemp);
            return "Tranquilizate estamos procesando tu pedido \uD83D\uDE21\uD83D\uDE44";
        }

        recordProcessingMap.put(userId, new RecordProcessing());

        System.out.println("-----------------------Organizando.....-----------------------------");
        System.out.println("-----------------------Borrando Listas previas-----------------------------");
        deletePreviusPlaylistCreatedByUs();

        System.out.println("-----------------------Obteniendo todos los tracks no duplicados-----------------------------");
        List<Track> nonDuplicatedTrackList = getNonDuplicatedTrackList(userId);
        RecordProcessing rp = new RecordProcessing();
        rp.setEstimated(new Date(new Date().getTime() + ESTIMATED_PER_MUSIC * nonDuplicatedTrackList.size()));
        recordProcessingMap.put(userId, rp);

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
         RecordProcessing rptemp = recordProcessingMap.get(userId);
         rptemp.setIsSuccessful(true);
         recordProcessingMap.put(userId, rptemp);
         System.out.println("Se supone que le setee successful true" + recordProcessingMap.get(userId));
        return "Spotify correctamente Organizado";

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
