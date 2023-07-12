package balan.codes.crazylist.spotify.dto;

public class Owner {
    @Override
    public String toString() {
        return "Owner{" +
                "display_name='" + display_name + '\'' +
                ", external_urls=" + external_urls +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }

    public String display_name;
    public ExternalUrls external_urls;
    public String href;
    public String id;
    public String type;
    public String uri;
}
