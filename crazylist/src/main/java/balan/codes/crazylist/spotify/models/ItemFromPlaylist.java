package balan.codes.crazylist.spotify.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ItemFromPlaylist {
    public Boolean collaborative;
    public String description;
    public ExternalUrls external_urls;
    public String href;
    public String id;
    public ArrayList<Image> images;
    public String name;
    public Owner owner;
    public Object primary_color;
    @JsonProperty("public")
    public boolean mypublic;
    public String snapshot_id;
    public Tracks tracks;
    public String type;
    public String uri;
}
