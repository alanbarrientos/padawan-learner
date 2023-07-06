package balan.codes.crazylist.languagedetector;

import balan.codes.crazylist.languagedetector.models.RootTexAnalisisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Component
public class GoogleLanguageDetector implements LanguageDetectorProvider{
    @Value("${googlelanguagedetector.apikey}")
    private String API_KEY;
    private static final String HOST = "google-translate1.p.rapidapi.com";
    private static final String BASE_URL = "https://" + HOST +"/language/translate/v2/detect";
    @Override
    public String detectLanguageOfText(String text) throws Throwable {
        String body = "q=" + text;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "application/gzip")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", HOST)
                .method("POST", HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        Gson gson = new Gson();
        Map  responseProperties = gson.fromJson(responseBody, Map.class);
        String language = (String) ((Map)((List)((List)((Map)responseProperties.get("data")).get("detections")).get(0)).get(0)).get("language");
        return language;
    }
}
