package com.intermediate.practices.polymorphism;

public class PolymorphismApp {
    public static void main(String[] args) {
        AmazonProduct kitMouseKeyboard = new AmazonProduct("KIT PROGRAMMING, Keyboard and mouse", (byte) 4, (short) 2,
        1343,239.99F,2.99F,2.3F,5.2345333,1.56342344,
        4.232332325,'N',true,false);
        kitMouseKeyboard.setPrice(199.99F);
        kitMouseKeyboard.printDescription();
        kitMouseKeyboard.changeDisponibility();
        kitMouseKeyboard.setPrice(249.99F, 4.99F); // this is the overloaded method
        kitMouseKeyboard.printDescription();
        System.out.println("The title of the product is: " + kitMouseKeyboard.getTitle());
        System.out.println("Total amount of money of solds product are: $" + kitMouseKeyboard.getTotalSolds());
        System.out.println("The volume of the package is: " + kitMouseKeyboard.getVolume());
        System.out.println("This is the method Overrided getWeightInKg: " + kitMouseKeyboard.getWeightInKg());
        System.out.println("This is the weigth in kg: " + kitMouseKeyboard.getWeightInKg() + " kg.");
        System.out.println("With a ten percent of discount the product price is: " + kitMouseKeyboard.getDiscountByPorcentage(10));
        kitMouseKeyboard.addGiftToTheProduct("Stickers");
    }
}
