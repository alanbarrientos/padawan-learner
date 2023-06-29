package balan.codes.crazylist.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

//@RestController
public class Login {
//    private static String CLIENT_ID ="99aec08e4af54420bc94fa765f3d48e2";
//    private static String SECRET_CLIENT ="c35cf4380f5c4e23af06fb100ff8feef";
//    private static String AUTHORIZE_URL = "https://accounts.spotify.com/authorize";
//    private static String REDIRECT_URI = "http://localhost:8080/alreadylogged/";
//    private static String  GET_TOKEN_URL = "https://accounts.spotify.com/api/token";
//
//    private static String token = "";
//    private static String refreshToken = "";
//
//    @GetMapping("/dasf")
//    public RedirectView login(){
//        String urlSpotifyAuthorization = AUTHORIZE_URL +
//        "?client_id=" + CLIENT_ID +
//        "&response_type=code" +
//        "&redirect_uri=" + REDIRECT_URI +
//        "&show_dialog=true" +
//        "&scope=user-read-private user-read-email user-modify-playback-state user-read-playback-position user-library-read streaming user-read-playback-state user-read-recently-played playlist-read-private";
//        return new RedirectView(urlSpotifyAuthorization);
//    }
//
////    @GetMapping("/alreadylogged/{code}")
////    public RedirectView redirect(@RequestParam("code") String authoritation_code){
////        return new RedirectView("https://stateful.com/blog/oauth-refresh-token-best-practices");
////    }
//    @GetMapping("/alreadylogged/")
//    public String redirect(@RequestParam(value = "code") String authoritation_code) throws URISyntaxException {
//        return "The authorization code is: " + authoritation_code;
//    }
//
//    private void getTokenAndRefreshToken(String authoritation_code) throws URISyntaxException, IOException, InterruptedException {
//        String body = "grant_type=authorization_code" +
//                "&code=" +
//                "&redirect_uri=" +
//                "&client_id=" +
//                "&client_secret=";
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI(GET_TOKEN_URL))
//                .headers("Content-Type", "application/x-www-form-urlencoded")
//                .timeout(Duration.of(10, SECONDS))
//                .POST(HttpRequest.BodyPublishers.ofString(body))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
////        if ( this.status == 200 ){
////            var data = JSON.parse(this.responseText);
////            console.log(data);
////            var data = JSON.parse(this.responseText);
////            if ( data.access_token != undefined ){
////                access_token = data.access_token;
////                localStorage.setItem("access_token", access_token);
////            }
////            if ( data.refresh_token  != undefined ){
////                refresh_token = data.refresh_token;
////                localStorage.setItem("refresh_token", refresh_token);
////            }
////            onPageLoad();
////        }
//        if(response.statusCode() == 200){
//
//        }
//
//    }


}
