package balan.codes.crazylist.lyricsprovider.shazamapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.http.HttpResponse;
import java.util.Date;

@ToString
@Getter
@Setter
public class ShazamMetadata {
    Date releaseDate;
    String lyrics;
    Boolean haveLyrics;
}
