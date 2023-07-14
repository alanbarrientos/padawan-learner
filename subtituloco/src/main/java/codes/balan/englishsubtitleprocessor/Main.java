package codes.balan.englishsubtitleprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    //$_ java -jar esp.jar arg1 arg2 arg3
    public static void main(String[] args) throws IOException {
        Set<Word> setWords = new HashSet<Word>();
//        String subtitleFile = args[0];
//        String outputFile = args[1];
//        String Type = args[2];
//
        String subtitleFile = "C:\\Users\\alanb\\Downloads\\suits.s01.e01.pilot.(2011).eng.1cd.(8832673)\\Suits S01E06 Tricks of the Trade.DVDRip.NonHI.en.UNVSL.srt";
            String outputFile = "C:\\Users\\alanb\\Downloads\\suits.s01.e01.pilot.(2011).eng.1cd.(8832673)\\Suits S01E06.txt";
        String Type = "As File";

        Utils utils = new Utils();
        SentenceParser sentenceParser = new SentenceParser(subtitleFile);


        int minute = 0;
// leer set de archivo de episodios anteriores.
        Set<String> setStringsWords= utils.readPreviousWords();
        while (sentenceParser.hasMoreData()) {
            minute = minute + 10;
            List<String> sentences = sentenceParser.getNextSentences(minute);

            for (String s : sentences) {
                Word[] words = utils.tagPOS(s);

                for (Word w : words) {
                    w.setWord(w.getWord().replaceAll("\\??\\.?\\,?\\:?", ""));
                    w.setWord(w.getWord().replaceAll("</?i>?", ""));
                    w.setWord(w.getWord().replaceAll("\"", ""));
                    w.setWord(w.getWord().replaceAll("\\$\\d+", ""));
                    w.setWord(w.getWord().toLowerCase());
                    if(!setStringsWords.contains(w.getWord())){
                        setStringsWords.add(w.getWord());
                        setWords.add(w);
                        switch (Type){
                            case "As File":
                                utils.writeInFile(minute, w, outputFile);
                            case"As HTML":
                                utils.writeInHTML(minute, w, outputFile);
                            case "As Subtitle":
                                utils.writeAsSubtitle(Files.readAllLines(Paths.get(subtitleFile), StandardCharsets.UTF_8), w, outputFile);
                        }
                    }
                }
            }

        }
        // guardar set en archivo
    }
}

