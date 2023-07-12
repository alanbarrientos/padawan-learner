package balan.codes.crazylist.spotify.dto;

import java.util.Date;

public class ItemFromMusicOfPlaylist {
    public Date added_at;
    public AddedBy added_by;
    public boolean is_local;
    public Object primary_color;
    public Track track;
    public VideoThumbnail video_thumbnail;

    @Override
    public String
    toString() {
        return "ItemFromMusicOfPlaylist{" +
                "added_at=" + added_at +
                ", added_by=" + added_by +
                ", is_local=" + is_local +
                ", primary_color=" + primary_color +
                ", track=" + track +
                ", video_thumbnail=" + video_thumbnail +
                '}';
    }
}
