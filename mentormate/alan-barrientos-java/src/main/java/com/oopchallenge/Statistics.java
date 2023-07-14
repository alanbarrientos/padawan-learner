package com.oopchallenge;

import java.util.ArrayList;
import java.util.List;

public class    Statistics {

    private List<String> fights = new ArrayList<>();

    ConsoleFancyOutput cfo = new ConsoleFancyOutput();

    public void printStatistics(){
        System.out.println("Fights: " + fights.size());
        int numberOfFight = 0;
        for (String fight: fights) {
            numberOfFight++;
            System.out.print(cfo.blue("Number of Fight: " + numberOfFight));
            System.out.println(fight);
        }
    }

    public void addFight(Hero winner, Hero defeated, int turnsTaken, String location){
        fights.add(
                cfo.blue(" in location: " + location + "\n")+
                cfo.cyan("Turns Taken: " + turnsTaken + "\n") +
                cfo.green(winner.getClass().getSimpleName() + " " + winner.getName() + " IS THE WINNER\n") +
                cfo.red(defeated.getClass().getSimpleName() + " " + defeated.getName() + " is defeat\n")
        );
    }
}
