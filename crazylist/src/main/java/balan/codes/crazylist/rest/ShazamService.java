package balan.codes.crazylist.rest;


import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class ShazamService {
    public static void main(String[] args) throws IOException {
        callShazam();
//        cutMusic();
    }

    private static void cutMusic(){
//        ffmpeg -i "C:\Users\alanb\Downloads\Music\Music\Caraluna.mp3" -ss 1 -t 5 -acodec copy "C:\Users\alanb\Downloads\Music\Music\CaralunaCuted.mp3"
//        The comand of above work
        String inputFile = "C:\\Users\\alanb\\Downloads\\Music\\Music\\Caraluna.mp3";
        String outputFile = "C:\\Users\\alanb\\Downloads\\Music\\Music\\CaralunaCuted.mp3";
        int startTimeInSeconds = 1;
        int durationInSeconds = 5;

        String ffmpegCommand = String.format("ffmpeg -i \"%s\" -ss %d -t %d -acodec copy \"%s\"", inputFile, startTimeInSeconds, durationInSeconds, outputFile);

        try {
            Process process = Runtime.getRuntime().exec(ffmpegCommand);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Music file cut successfully.");
            } else {
                System.out.println("Failed to cut music file. FFmpeg command returned non-zero exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void callShazam() {
        String url = "https://shazam-core.p.rapidapi.com/v1/tracks/recognize";
        String apiKey = "076f560fa8msh7c3ec65f7ee1a87p17ac3cjsne2a9d285bbad";

        OkHttpClient client = new OkHttpClient();
//        "subject":" this is the way to find the music
        File file = new File("C:\\Users\\alanb\\Downloads\\Music\\Music\\CaralunaCuted.mp3");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "CaralunaCuted.mp3",
                        RequestBody.create(MediaType.parse("audio/mpeg"), file))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "shazam-core.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println(response.body().string());
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
