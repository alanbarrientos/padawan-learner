package balan.codes.crazylist.spotify.dto;

import java.util.ArrayList;

public class Album {
    public String album_type;
    public ArrayList<Artist> artists;
    public ArrayList<String> available_markets;
    public ExternalUrls external_urls;
    public String href;
    public String id;
    public ArrayList<Image> images;
    public String name;
    public String release_date;
    public String release_date_precision;
    public int total_tracks;
    public String type;
    public String uri;

//    public Date getRelease_date() {
//        return release_date;
//    }
}
