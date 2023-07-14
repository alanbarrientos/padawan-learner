package com.beginner.practices.datatypesprimitives;

public class AmazonProductPrimitiveVariables {
    public static void main(String[] args) {
        byte reviewStars = 4;
        short pieces = 2;
        int solds = 1324;
        float price = 239.99F;
        float deliveryPrice = 2.99F;
        float weitht = 2.3F;
        double height = 3.67382934; //just to use double
        double width = 1.56342344; //just to use double
        double lenght = 5.23453336; //just to use double
        char condition = 'N';// N for new and U for used
        boolean isDisponible = true;

        System.out.println("KIT PROGRAMMING, Keyboard and mouse");
        System.out.println("Pieces: " + pieces);
        System.out.println("Review Stars: " + reviewStars);
        System.out.println("Solds: " + solds);
        System.out.println("Price: $" + price);
        System.out.println("Delivery Price: $" + deliveryPrice);
        System.out.println("Weith: " + weitht + "kg.");
        System.out.println("Size: height " + height + " x width " + width + " x lenght " + lenght);
        System.out.println("Condition: " + condition + " (N:new U:used)");
        System.out.println("Is Disponible: " + isDisponible);

    }
}

