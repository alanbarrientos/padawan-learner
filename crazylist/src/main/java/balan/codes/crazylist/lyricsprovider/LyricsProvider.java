package balan.codes.crazylist.lyricsprovider;

public interface LyricsProvider {
    String fetchLyrics(String songName);
    default String fetchLyrics(String artist, String songName){
        return fetchLyrics(songName + " " + artist);
    };
}
