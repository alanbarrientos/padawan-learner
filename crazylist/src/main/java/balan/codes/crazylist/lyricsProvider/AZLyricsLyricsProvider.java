package balan.codes.crazylist.lyricsProvider;

import org.springframework.stereotype.Component;

import com.github.kevinsawicki.http.HttpRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AZLyricsLyricsProvider implements LyricsProvider{
    private static final String HOST = "https://www.azlyrics.com";
    private static final String CSRF_URL = "https://www.azlyrics.com/geo.js";
    private static final String SEARCH_URL = "https://search.azlyrics.com/search.php";

    @Override
    public String fetchLyrics(String artist, String songName) {
        return fetchLyrics(artist + " " + songName);
    }

    @Override
    public String fetchLyrics(String songName) {
        String tokenCsrf = getCsrfToken();
        String listLyricsUrl = getBodyListLyricsUrl(songName, tokenCsrf);
        String firstLyricsUrl = getFirstLyricsUrl(listLyricsUrl);
        String lyricsPageBody = getBodyLyricsPage(firstLyricsUrl);
        String songLyrics = extractLyrics(lyricsPageBody);
        return songLyrics;
    }

    private static final Pattern TOKEN_PATTERN = Pattern.compile("\"value\", \"(.*)\"");
    private String getCsrfToken(){
        HttpRequest csrfTokenRequest = HttpRequest.get(CSRF_URL);
        String body = csrfTokenRequest.body();
        Matcher matcher = TOKEN_PATTERN.matcher(body);
        String token = null;
        if(matcher.find()){
            token = matcher.group(1);
        }
        return token;
    }

    private String getBodyListLyricsUrl(String songName, String tokenCsrf){
        return HttpRequest.get(SEARCH_URL, true, "q", songName, "x", tokenCsrf).body();
    }

    private static final Pattern FIRST_URL_PATTERN = Pattern.compile("href=\"(.*)\">1.");
    private String getFirstLyricsUrl(String listLyricsUrl){
        Matcher matcher = FIRST_URL_PATTERN.matcher(listLyricsUrl);
        String firstUrl = null;
        if(matcher.find()){
            firstUrl = matcher.group(1);
        }
        return firstUrl;
    }

    private String getBodyLyricsPage(String lyricsUrl){
        return HttpRequest.get(lyricsUrl).body();
    }

    private static final Pattern LYRICS_CONTENT_PATTERN =
            Pattern.compile(
                    "</span>\\R</div>\\R+<b>(.*)\\R</div>\\R+<br><br>\\R",
                    Pattern.MULTILINE | Pattern.DOTALL
            );
    private String extractLyrics(String lyricsPageBody){
        Matcher matcher = LYRICS_CONTENT_PATTERN.matcher(lyricsPageBody);
        String songLyrics = null;
        if(matcher.find()){
            songLyrics = matcher.group(1).replaceAll("<.*>", "");
        }
        return songLyrics;
    }
}
