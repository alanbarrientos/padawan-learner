package balan.codes.crazylist.spotify;

import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrackEssentialMetadata {
    private String spotifyId;
    private String name;
    private String artists;
    private Date releaseDate;
    private String language;
    private Boolean haveLyrics;

}
