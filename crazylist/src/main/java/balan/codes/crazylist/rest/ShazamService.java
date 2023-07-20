package balan.codes.crazylist.rest;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ShazamService {
    @Value("${shazam.token}")
    private static  String API_KEY;


    public String cutMusic(String inputFile){
//        ffmpeg -i "C:\Users\alanb\Downloads\Music\Music\Caraluna.mp3" -ss 1 -t 5 -acodec copy "C:\Users\alanb\Downloads\Music\Music\CaralunaCuted.mp3"
//        The comand of above work
//        String inputFile = "C:\\Users\\alanb\\Downloads\\Music\\Music\\Caraluna.mp3";
        String outputFile = inputFile.replaceAll(".mp3$","cuted.mp3" );
        int startTimeInSeconds = 1;
        int durationInSeconds = 5;

        String ffmpegCommand = String.format("ffmpeg -i \"%s\" -ss %d -t %d -acodec copy \"%s\"", inputFile, startTimeInSeconds, durationInSeconds, outputFile);

        System.out.println(ffmpegCommand);
        ProcessUtil processUtil = new ProcessUtil();
        int exitCode = processUtil.execProccessAndWaitFor(ffmpegCommand);
        if (exitCode == 0) {
            System.out.println("Music file cut successfully.");
            return outputFile;
        } else {
            System.out.println("Failed to cut music file. FFmpeg command returned non-zero exit code: " + exitCode);
            return null;
        }
    }
    public String callShazam(String inputFile) {
        String url = "https://shazam-core.p.rapidapi.com/v1/tracks/recognize";
//        String apiKey = apikey;

        OkHttpClient client = new OkHttpClient();
//        "subject":" this is the way to find the music
//        File file = new File("C:\\Users\\alanb\\Downloads\\Music\\Music\\CaralunaCuted.mp3");
        File file = new File(inputFile);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", "CaralunaCuted.mp3",
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("audio/mpeg"), file))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-RapidAPI-Host", "shazam-core.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", API_KEY)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println(responseBody);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseBody);
                String trackArtist = rootNode.path("track").path("urlparams").path("{trackartist}").asText();
                String trackTitle = rootNode.path("track").path("urlparams").path("{tracktitle}").asText();
                return trackTitle + " " + trackArtist;
            } else {
                System.out.println("Request failed with code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
//    public static void main(String[] args) {
//        String url = "https://shazam-core.p.rapidapi.com/v1/tracks/recognize";
//        String apiKey = "076f560fa8msh7c3ec65f7ee1a87p17ac3cjsne2a9d285bbad";
//        String filePath = "C:\\Users\\alanb\\Downloads\\Caraluna (mp3cut.net).mp3";
//
//        HttpClient httpClient = HttpClient.newBuilder().build();
//
//        try {
//            // Create multipart/form-data request body
//            String boundary = "===" + System.currentTimeMillis() + "===";
//            Path file = Paths.get(filePath);
//            byte[] fileBytes = Files.readAllBytes(file);
//            HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofByteArray(fileBytes);
//
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(url))
//                    .POST(bodyPublisher)
//                    .header("X-RapidAPI-Key", apiKey)
//                    .header("X-RapidAPI-Host", "shazam-core.p.rapidapi.com")
//                    .header("Content-Type", "multipart/form-data; boundary=" + boundary)
//                    .build();
//
//            // Send the request
//            HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
//            int statusCode = response.statusCode();
//
//            // Process the response
//            if (statusCode == 200) {
//                byte[] responseBody = response.body();
//                String responseString = new String(responseBody);
//                System.out.println(responseString);
//            } else {
//                System.out.println("Request failed with code: " + statusCode);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
