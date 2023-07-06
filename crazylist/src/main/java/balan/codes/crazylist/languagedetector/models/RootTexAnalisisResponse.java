package balan.codes.crazylist.languagedetector.models;

import java.util.Map;

public class RootTexAnalisisResponse {
    public String app_version;
    public double time_taken;
    public String msg;
    public boolean ok;
    public Map<String, Double> language_probability;

    @Override
    public String toString() {
        return "RootTexAnalisisResponse{" +
                "app_version='" + app_version + '\'' +
                ", time_taken=" + time_taken +
                ", msg='" + msg + '\'' +
                ", ok=" + ok +
                ", language_probability=" + language_probability +
                '}';
    }
}
