package balan.codes.crazylist.lyricsprovider.shazamapi;

import java.util.Date;

public class ShazamMetadata {
    Date releaseDate;
    String lyrics;

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public String toString() {
        return "ShazamMetadata{" +
                "releaseDate=" + releaseDate +
                ", lyrics='" + lyrics + '\'' +
                '}';
    }
}
