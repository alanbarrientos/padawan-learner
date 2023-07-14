package codes.balan.englishsubtitleprocessor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class SentenceParser {
    final private String SENTENSE_NOT_END = "SENTENSE_NOT_END";
    final private String START_NUMBERS = "^\\d{1,4}$";
    final private String TIME = "^\\d{2}:\\d{2}:\\d{2},\\d{3} --> \\d{2}:\\d{2}:\\d{2},\\d{3}";
    final private String END_OF_SENTENCE = "\\w+'?(\\w+)?(\\.|\\?)";
    final private String SHORTS_WORDS = "mr\\.|Mr\\.|mrs\\.|Mrs\\.|p[1]\\.|a[1]\\.|m[1]\\.";
    private StringBuilder inProcessSentence = new StringBuilder();
    private List<String> subtitleFileLines;
    private boolean firstTime = true;
    private String sentence= null;
    private String lineOfSubtitle = null;
    private boolean finalOfFile=false;
    private int idOfWord = 0;
    private int idOfLine = 0;

    public SentenceParser(String subtitle) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(subtitle), StandardCharsets.UTF_8);
        this.subtitleFileLines = lines;
    }


    public List<String> getNextSentences(int upToMinute){
        String sentence=null;
        if(firstTime ==true){
            filterFile();
            firstTime =false;
            idOfLine = 0;
        }

        List<String> sentences = new ArrayList<>();


        do {
            lineOfSubtitle = subtitleFileLines.get(idOfLine);
            if(idOfLine==subtitleFileLines.size()-1){
                finalOfFile = true;
                break;
            }
            sentence = getNextSentence(lineOfSubtitle);
            if(sentence==SENTENSE_NOT_END) {
                idOfLine=idOfLine+1;
                continue;
            }
            idOfLine=idOfLine+1;
            sentences.add(sentence);
//            if(lineOfSubtitle.matches("^"+String.valueOf(upToMinute)+".$")){
//                System.out.println("sadfdsfa");
//            }
        }while(!lineOfSubtitle.matches("^"+String.valueOf(upToMinute)+".$"));
        return sentences;
    }

    private String getNextSentence(String line){
        String[] wordsOfSentence = line.split(" +");
        for (int i = idOfWord; i < wordsOfSentence.length; i++) {
            if (wordsOfSentence[i].matches(END_OF_SENTENCE) && !wordsOfSentence[i].matches(SHORTS_WORDS)) {
                    if (i == wordsOfSentence.length - 1) {
                        idOfWord = 0;
                        inProcessSentence.append(wordsOfSentence[i]);
                        sentence= inProcessSentence.toString();
                        inProcessSentence.replace(0,inProcessSentence.length(),"");
                        return sentence;
                    } else {
                        idOfWord = i + 1;
                        inProcessSentence.append(wordsOfSentence[i]);
                        sentence= inProcessSentence.toString();
                        inProcessSentence.replace(0,inProcessSentence.length(),"");
                        return sentence;
                    }
            } else {
                inProcessSentence.append(wordsOfSentence[i] + " ");
            }
        }
        if(idOfWord>0){
            return  SENTENSE_NOT_END;
        }
        sentence= inProcessSentence.toString();
        inProcessSentence.replace(0,inProcessSentence.length(),"");
        return sentence;
    }



//    Filtra tod lo que hay en la lista de subtileFileLines y deja si nada a esepcion de el numero de minuto que es cada 10 minutos
    private void  filterFile(){
        idOfLine =0;
        int minute = 10;
        List<String> filteredSubtitleFile = new ArrayList<>();
        while(idOfLine < subtitleFileLines.toArray().length-1)  {
            // trim newline when comparing with lineToRemove
            String lineOfSubtitle = subtitleFileLines.get(idOfLine);
//            lineOfSubtitle = lineOfSubtitle.trim();
            lineOfSubtitle = lineOfSubtitle.replaceAll("[^\\x00-\\x7F]", "");

            // erases all the ASCII control characters
            lineOfSubtitle = lineOfSubtitle.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

            // removes non-printable characters from Unicode
            lineOfSubtitle = lineOfSubtitle.replaceAll("\\p{C}", "");
            if (lineOfSubtitle.matches(START_NUMBERS)) {
                idOfLine = idOfLine + 1;
                lineOfSubtitle = subtitleFileLines.get(idOfLine);
            }
            if (lineOfSubtitle.matches(TIME)) {
                String[] times = lineOfSubtitle.split("--> ");
                int time = minuteOfSubtitle(times[1]);
                if (time-1 > minute) {
                    lineOfSubtitle = String.valueOf(minute)+".";
                    minute=minute+10;
                }else{
                    idOfLine = idOfLine + 1;
                    lineOfSubtitle = subtitleFileLines.get(idOfLine);
                }

            }
            if (lineOfSubtitle.trim().isEmpty()) {
                idOfLine = idOfLine + 1;
                lineOfSubtitle = subtitleFileLines.get(idOfLine);
                continue;
            }
            filteredSubtitleFile.add(lineOfSubtitle);
            idOfLine = idOfLine + 1;
        }
        subtitleFileLines= filteredSubtitleFile;
    }



    public boolean hasMoreData() {
        return !finalOfFile;
    }

    private int minuteOfSubtitle(String time){
        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
        return localTime.get(ChronoField.MINUTE_OF_DAY);
    }

}
