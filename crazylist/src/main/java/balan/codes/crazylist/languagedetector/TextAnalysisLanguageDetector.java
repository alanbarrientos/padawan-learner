package balan.codes.crazylist.languagedetector;

import balan.codes.crazylist.languagedetector.models.RootTexAnalisisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class TextAnalysisLanguageDetector implements LanguageDetectorProvider {
    @Value("${textanalysis.apikey}")
    private String API_KEY;
    private static final String HOST = "text-analysis12.p.rapidapi.com";
    private static final String BASE_URL = "https://" + HOST +"/language-detection/api/v1.1";
    @Override
    public String detectLanguageOfText(String text) throws Throwable {
        String body = "{\"text\": \""+ text + "\"}";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", HOST)
                .method("POST", HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        RootTexAnalisisResponse texAnalisisResponse = new ObjectMapper().readValue(responseBody, RootTexAnalisisResponse.class);
        String language = findPrimaryLanguage(texAnalisisResponse);
        System.out.println(texAnalisisResponse);
        System.out.println(language);
        return language;
    }
    private String findPrimaryLanguage(RootTexAnalisisResponse texAnalisisResponse){
        Double max = null;
        String language = null;
        for (String l : texAnalisisResponse.language_probability.keySet()) {
            if(max == null || texAnalisisResponse.language_probability.get(l) > max){
                max = texAnalisisResponse.language_probability.get(l);
                language = l;
            }
        }
        return language;
    }
}
