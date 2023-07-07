package balan.codes.crazylist.LyricsProvider;

import balan.codes.crazylist.lyricsprovider.AZLyricsLyricsProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class AZLyricsProviderTest {
    @Autowired
    AZLyricsLyricsProvider azLyricsLyricsProvider;

    @Test
    public void shouldReturnLyrics(){
        String songName = "Runaway Aurora";
        String lyrics = "I was listening to the ocean\n" +
        "I saw a face in the sand\n" +
                "But when I picked it up\n" +
                "Then it vanished away from my hands, down\n" +
                "I had a dream I was seven\n" +
                "Climbing my way in a tree\n" +
                "I saw a piece of heaven\n" +
                "Waiting, impatient, for me, down\n";
        String resultFetch = azLyricsLyricsProvider.fetchLyrics(songName);
        assertThat(resultFetch).contains(lyrics);
    }
}
