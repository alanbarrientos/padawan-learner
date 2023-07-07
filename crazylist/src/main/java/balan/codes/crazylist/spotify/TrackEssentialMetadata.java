package balan.codes.crazylist.spotify;

import java.util.Date;

public class TrackEssentialMetadata {
    private Date releaseDate;
    private String name;
    private String spotifyId;
    private String Language;

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    @Override
    public String toString() {
        return "TrackEssentialMetadata{" +
                "releaseDate=" + releaseDate +
                ", name='" + name + '\'' +
                ", spotifyId='" + spotifyId + '\'' +
                ", Language='" + Language + '\'' +
                '}';
    }
}
