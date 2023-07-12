package balan.codes.crazylist.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "music_cache")
public class MusicCache {

    public MusicCache() {

    }

    public MusicCache(String spotifyId, String name, Date releaseDate, String language, String artist, Boolean haveLyrics) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.releaseDate = releaseDate;
        this.language = language;
        this.artist = artist;
        this.haveLyrics = haveLyrics;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "spotify_id")
    private String spotifyId;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "language")
    private String language;

    @Column(name = "artist")
    private String artist;

    @Column(name = "have-lyrics")
    private Boolean haveLyrics;
}
