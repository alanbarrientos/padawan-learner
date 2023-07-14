package com.advanced.hangman;

public class ConsoleFancyOutput {

    private static final String PURPLE =  "\u001B[35m";
    private static final String DEFAULT = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE ="\u001B[37m";


    public String black(String str) {
        return BLACK + str + DEFAULT;
    }
    public String red(String str) {
        return RED + str + DEFAULT;
    }
    public String green(String str) {
        return GREEN + str + DEFAULT;
    }
    public String yellow(String str) {
        return YELLOW + str + DEFAULT;
    }
    public String blue(String str) {
        return BLUE + str + DEFAULT;
    }
    public String purple(String str) {
        return PURPLE + str + DEFAULT;
    }
    public String cyan(String str) {
        return CYAN + str + DEFAULT;
    }
    public String white(String str) {
        return WHITE + str + DEFAULT;
    }

}
