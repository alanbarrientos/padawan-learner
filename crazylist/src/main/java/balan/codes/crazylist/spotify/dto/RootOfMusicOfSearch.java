package balan.codes.crazylist.spotify.dto;

import java.util.ArrayList;

public class RootOfMusicOfSearch {
    public Track tracks;
    public static class Track{
        public String href;
        public ArrayList<Item> items;
        public int limit;
        public String next;
        public int offset;
        public Object previous;
        public int total;
    }
    public static class  Item{
        public Album album;
        public ArrayList<Artist> artists;
        public ArrayList<String> available_markets;
        public int disc_number;
        public int duration_ms;
        public boolean explicit;
        public ExternalIds external_ids;
        public ExternalUrls external_urls;
        public String href;
        public String id;
        public boolean is_local;
        public String name;
        public int popularity;
        public String preview_url;
        public int track_number;
        public String type;
        public String uri;
    }
}
