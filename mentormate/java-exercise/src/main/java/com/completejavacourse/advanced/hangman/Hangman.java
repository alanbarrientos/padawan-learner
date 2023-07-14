package com.completejavacourse.advanced.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Hangman {
    ConsoleFancyOutput cfo = new ConsoleFancyOutput();

    private final String FILE_PATH = "alan-barrientos-java\\src\\main\\java\\com\\completejavacourse\\advanced\\hangman\\hangman-words.txt";
    public void startPlay() {
        char[] wordSelected = getRandomWord();
        if (wordSelected == null) return;
        System.out.println(cfo.purple("Welcome to the best Handman console game of the history. You have 4 lives"));
        char[] wordToGuess = new char[wordSelected.length];
        Arrays.fill(wordToGuess, '-');

        HashMap wordSelectedHashmap = getSelectedWord(wordSelected);

        startHangman(wordSelected, wordToGuess, wordSelectedHashmap);
    }

    private void startHangman(char[] wordSelected, char[] wordToGuess, HashMap wordSelectedHashmap) {
        Scanner scanner = new Scanner(System.in);
        GameStatus gameStatus = GameStatus.PLAYING;
        int lives = 4;
        String enterValue;
        do {
            System.out.println(wordToGuess);
            System.out.println(cfo.blue("Enter a letter/word"));
            enterValue = scanner.next();
            enterValue = enterValue.toLowerCase();
            if (enterValue.length() > 1) {
                if (new String(wordSelected).equals(enterValue)) {
                    clearconsole();
                    gameStatus = GameStatus.WIN;
                } else {
                    lives--;
                    clearconsole();
                    System.out.println(cfo.yellow("The word (" + enterValue + ") is not correct"));
                }
            } else {
                int[] indexOfLetter;
                indexOfLetter = checkEnterValue(wordSelectedHashmap, wordToGuess, enterValue.toCharArray()[0]);
                if (indexOfLetter[0]>=0) {
                    for(int i=0; i<indexOfLetter.length; i++){
                        wordToGuess[indexOfLetter[i]] = enterValue.toCharArray()[0];
                    }
                    gameStatus = checkIfWordCompleted(wordToGuess, gameStatus, enterValue);
                } else {
                    clearconsole();
                    System.out.println(cfo.yellow("the letter (" + enterValue + ") is not correct or you already enter this letter"));
                    lives--;
                }
            }

            if (lives == 0) {
                gameStatus = GameStatus.LOST;
            }
            if(gameStatus!=GameStatus.WIN){
                printHandman(lives);
            }
        } while (gameStatus == GameStatus.PLAYING);

        winOrLost(wordSelected, gameStatus);
    }

    private void winOrLost(char[] wordSelected, GameStatus gameStatus) {
        switch (gameStatus) {
            case WIN:
                System.out.println(String.format(cfo.green("YOU WIN THE WORD WAS:") + cfo.blue(new String(wordSelected))
                ));
                break;
            case LOST:
                System.out.println(cfo.red("Sorry good luck for the next time the word was: ") + cfo.blue(new String(wordSelected)));
                break;
        }
    }

    private GameStatus checkIfWordCompleted(char[] wordToGuess, GameStatus gameStatus, String enterValue) {
        if (new String (wordToGuess).contains("-")) {
            clearconsole();
            System.out.println(cfo.cyan("Great (" + enterValue + ") is it a correct letter"));
        } else {
            clearconsole();
            gameStatus = GameStatus.WIN;
        }
        return gameStatus;
    }

    private char[] getRandomWord() {
        char[] wordSelected = new char[0];
        try {
            wordSelected = getRadmonWordChars(FILE_PATH);
        } catch (IOException e) {
            System.out.println("The file no exist");
            return null;
        }
        return wordSelected;
    }

    private static HashMap getSelectedWord(char[] wordSelected) {
        HashMap wordSelectedHashmap = new HashMap<Character, List<Integer>>();
        for (int i = 0; i< wordSelected.length; i++) {
            if(wordSelectedHashmap.containsKey(wordSelected[i])){
                List list = (List) wordSelectedHashmap.get(wordSelected[i]);
                list.add(i);
                wordSelectedHashmap.replace(wordSelected[i], list);
            }else{
                List list = new ArrayList();
                list.add(i);
                wordSelectedHashmap.put(wordSelected[i], list);
            }
        }
        return wordSelectedHashmap;
    }

    private char[] getRadmonWordChars(String path) throws IOException {
        Random random = new Random();
        Path filePath = Path.of(path);
        List<String> lines = Files.lines(filePath).collect(Collectors.toList());
        return lines.get(random.nextInt(lines.size())).toLowerCase().toCharArray();
    }

    private int[] checkEnterValue(HashMap<Character, List<Integer>> wordSelected, char[] wordToGuess, char letter){
        if(wordSelected.containsKey(letter) && !Arrays.asList(wordToGuess).contains(letter)){
            List<Integer> indexes = wordSelected.get(letter);
            int[] indexArray = new int[indexes.size()];
            for(int i = 0; i < indexes.size(); i++){
                indexArray[i] = indexes.get(i);
            }
            return indexArray;
        }
        return new int[]{-1};
    }

    private void printHandman(int lives){
        System.out.println(cfo.purple("You have ") + lives + cfo.purple(" lives"));
        switch (lives){
            case 4  :
                System.out.println(cfo.green(
                                "_____\n" +
                                "|  \n" +
                                "|  \n" +
                                "|  \n" +
                                "|   "
                ));
                break;
            case 3:
                System.out.println(cfo.white(
                                "_____\n" +
                                "|   |\n" +
                                "|  ( )\n" +
                                "|  \n" +
                                "|   "
                ));
                break;
            case 2:
                System.out.println(cfo.yellow(
                                "_____\n" +
                                "|   |\n" +
                                "|  ( )\n" +
                                "|   |\n" +
                                "|   "
                ));
                break;
            case 1:
                System.out.println(cfo.yellow(
                                "_____\n" +
                                "|   |\n" +
                                "|  ( )\n" +
                                "|  -|-\n" +
                                "|   "
                ));
                break;
            case 0:
                System.out.println(cfo.red(
                            "_____\n" +
                            "|   |\n" +
                            "|  ( )\n" +
                            "|  -|-\n" +
                            "|   ^"
                ));
                break;

        }

    }

    private void clearconsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

