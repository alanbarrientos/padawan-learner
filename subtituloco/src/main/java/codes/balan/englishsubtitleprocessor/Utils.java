package codes.balan.englishsubtitleprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    File file= null;
    private boolean isFirstTime= true;
    private int localMinute = 0;

    //todo falta inplementar todo lo de abajo
    public Word[] tagPOS(String sentence){
        String[] wordsString = sentence.split(" +");
        Word[] words = new Word[wordsString.length];
        for (int i=0; i<wordsString.length; i++) {
            if(i==0){
                words[i]=new Word(wordsString[i], TypeWord.VERB);
            }
            words[i]=new Word(wordsString[i], TypeWord.NOT_VERB);
        }
        return words;
    }
    public void writeInFile(int minute, Word word, String outputFile) throws IOException {
//       Write a tile if the minute change when we call again and write words in file with a ", " and if the word is VERB put some advise like this "<word>"
        if(isFirstTime){
            file = new File(outputFile);
            FileWriter fileWriter= new FileWriter(file);
            fileWriter.write("");
            isFirstTime=false;
            fileWriter.close();
        }
        try {
            // Creates a Writer using FileWriter

            if(localMinute!=minute){
                localMinute = minute;
                String localTitle = "\n Words Up to minute: "+ minute + "\n\n";
                Files.write(Paths.get(file.getAbsolutePath()), localTitle.getBytes(), StandardOpenOption.APPEND);

            }else{
                if(!word.getWord().trim().matches("\\d+")) {
                    String localWord = word.getWord() + "\n";
                    // Writes the program to file
                    Files.write(Paths.get(file.getAbsolutePath()), localWord.getBytes(), StandardOpenOption.APPEND);
                    Files.write(Paths.get("NewWordsAlan.txt"), localWord.getBytes(), StandardOpenOption.APPEND);
                    // Closes the writer
                }
            }

        }catch (Exception e) {
            e.getStackTrace();
        }

    }
    public void writeInHTML(int minute, Word word, String outputFile){

    }
    public void writeAsSubtitle(List<String> LinesFileSubtitle, Word word, String outputFile){
    }

    public Set<String> readPreviousWords() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("NewWordsAlan.txt"), StandardCharsets.UTF_8);
        Set<String> words = new HashSet<>();
        for (String l:lines) {
            words.add(l);
        }
        return words;
    }
}
