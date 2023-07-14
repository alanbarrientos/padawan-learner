package com.completejavacourse.intermediate.practices.clasesandobjects;

public class ClasesAndObjectsApp {
    public static void main(String[] args) {
        AmazonProduct amazonProduct = new AmazonProduct("KIT PROGRAMMING, Keyboard and mouse",
                1343,239.99F,2.99F,2.3F,5.2345333,1.56342344,
                4.232332325,true,(byte) 4,(short) 2,'N', false);
        amazonProduct.printDescription();
        System.out.println("Total amount of money of solds product are: $" + amazonProduct.getTotalSolds());
        System.out.println("The volume of the package is: " + amazonProduct.getVolume());
    }
}
