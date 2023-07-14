package com.beginner.practices.controlflow;

public class WhoWantsToBeAMillionaire {
    public static void main(String[] args) {
        char answer = 'C';

        System.out.println("Who Wants to be 1 dollar more rich");

        System.out.println("Who is named as the inventor of the light bulb?");

        System.out.println("Possible answers: A: Nikola Tesla, B: Marie Curie, C: Tomas Edison, D: Robert Oppenheimer");

        System.out.println("Your answer is: " + answer);

        if (answer == 'C'){
            System.out.println("Congrats :), You won a dollar!");
        } else {
            System.out.println("No :( Sorry, the answer is C: Tomas Edison");
        }
    }
}
