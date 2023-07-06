package balan.codes.crazylist.lyricsProvider;

public interface LyricsProvider {
    String fetchLyrics(String songName);
    String fetchLyrics(String artist, String songName);
}
