package balan.codes.crazylist.LyricsProvider;

import balan.codes.crazylist.lyricsprovider.shazamapi.ShazamApi;
import balan.codes.crazylist.lyricsprovider.shazamapi.ShazamMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpotifyApiTest {
    @Autowired
    ShazamApi shazamApi;


    @Test
    public void shouldGiveMeLyricsAndReleaseDate(){
        ShazamMetadata shazamMetadata = shazamApi.getMetadataSong("Gambare Gambare Senpai - Full Tiktok VER Tyo Pla");
        System.out.println(shazamMetadata);
    }
}
