package balan.codes.crazylist.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessUtil {

    public Integer execProccessAndWaitFor(String command){
        try {

            Process process = Runtime.getRuntime().exec(command);
            InputStream stdout = process.getInputStream();
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));

            // Captura la salida de error del proceso
            InputStream stderr = process.getErrorStream();
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));

            // Lee y procesa la salida estándar del proceso
            Thread stdoutThread = new Thread(() -> {
                String line;
                try {
                    while ((line = stdoutReader.readLine()) != null) {
                        // Procesa la línea de salida estándar según tus necesidades
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            stdoutThread.start();

            // Lee y procesa la salida de error del proceso
            Thread stderrThread = new Thread(() -> {
                String line;
                try {
                    while ((line = stderrReader.readLine()) != null) {
                        // Procesa la línea de salida de error según tus necesidades
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            stderrThread.start();
            int exitCode = process.waitFor();
            return exitCode;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
