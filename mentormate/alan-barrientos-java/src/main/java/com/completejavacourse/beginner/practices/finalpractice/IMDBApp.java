package com.completejavacourse.beginner.practices.finalpractice;

import java.util.Calendar;

public class IMDBApp {
    public static void main(String[] args) {

        String actorName = "Adam Sandler";
        int yearOfBorn = 1966;
        int age = Calendar.getInstance().get(Calendar.YEAR) - yearOfBorn;

        String[] movies = {
                "Anger Management", "Pixels", "Murder Mystery",
                "The Meyerowitz Stories", "Jack and Jill", "Click"
        };

        float[] movieRatings = { 6.2F, 5.6F, 6.0F, 6.9F, 3.3F, 6.4F};

        System.out.println(
                "Actor's name: " + actorName +
                "\nBorn: " + yearOfBorn +
                "\nAge: " + age
        );

        for(int i = 0; i < movies.length; i++) {
            System.out.println(movies[i] + " - " + getRatingText(movieRatings[i]));
        }
    }

    static String getRatingText(float rating){
        String ratingText = "";
        if (rating <= 5.0){
            ratingText = "bad";
        } else if (rating > 5.0 && rating <= 6.5){
            ratingText = "average";
        } else if (rating > 6.5 && rating <= 7.0){
            ratingText = "good";
        } else if (rating > 7.0 && rating <= 8.0){
            ratingText = "very good";
        }else{
            ratingText = "amazing";
        }
        return ratingText;
    }
}
