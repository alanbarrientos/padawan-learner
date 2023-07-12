package balan.codes.crazylist.spotify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RootFromPlaylistCreated {
    public boolean collaborative;
    public Object description;
    public ExternalUrls external_urls;
    public Followers followers;
    public String href;
    public String id;
    public ArrayList<Object> images;
    public String name;
    public Owner owner;
    public Object primary_color;
    @JsonProperty("public")
    public boolean mypublic;
    public String snapshot_id;
    public TracksFromPlaylistCreated tracks;
    public String type;
    public String uri;
}
