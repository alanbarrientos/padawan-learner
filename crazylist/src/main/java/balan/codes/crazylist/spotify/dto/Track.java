package balan.codes.crazylist.spotify.dto;

import java.util.ArrayList;
import java.util.Objects;

public class Track {

    public Album album;
    public ArrayList<Artist> artists;
    public ArrayList<String> available_markets;
    public int disc_number;
    public int duration_ms;
    public boolean episode;
    public boolean explicit;
    public ExternalIds external_ids;
    public ExternalUrls external_urls;
    public String href;
    public String id;
    public boolean is_local;
    public String name;
    public int popularity;
    public String preview_url;
    public boolean track;
    public int track_number;
    public String type;
    public String uri;

    @Override
    public String toString() {
        return "Track{" +
                "album=" + album +
                ", artists=" + artists +
                ", available_markets=" + available_markets +
                ", disc_number=" + disc_number +
                ", duration_ms=" + duration_ms +
                ", episode=" + episode +
                ", explicit=" + explicit +
                ", external_ids=" + external_ids +
                ", external_urls=" + external_urls +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", is_local=" + is_local +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                ", preview_url='" + preview_url + '\'' +
                ", track=" + track +
                ", track_number=" + track_number +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
