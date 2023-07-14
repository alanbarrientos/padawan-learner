package com.intermediate.practices.clasesandobjects;

public class AmazonProduct{
        private String title;
        private int solds;
        private float price;
        private float deliveryPrice;
        private float weight; //the weight is in pounds
        private double height;
        private double width;
        private double lenght;
        private boolean isDisponible;
        private byte reviewStars;
        private short pieces;
        private char condition;
        private boolean hasAmazonWarranty;

    public AmazonProduct(String title, int solds, float price, float deliveryPrice,
                         float weight, double height, double width, double lenght,
                         boolean isDisponible, byte reviewStars, short pieces, char condition,
                         boolean hasAmazonWarranty) {
        this.title = title;
        this.deliveryPrice = deliveryPrice;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.lenght = lenght;
        this.isDisponible = isDisponible;
        this.reviewStars = reviewStars;
        this.pieces = pieces;
        this.solds = solds;
        this.price = price;
        this.condition = condition;
        this.hasAmazonWarranty = hasAmazonWarranty;
    }

    public void printDescription(){
        System.out.println(title);
        System.out.println("Pieces: " + pieces);
        System.out.println("Review Stars: " + reviewStars);
        System.out.println("Solds: " + solds);
        System.out.println("Price: $" + price);
        System.out.println("Delivery Price: $" + deliveryPrice);
        System.out.println("Weith: " + weight + "kg.");
        System.out.println("Size: height " + height + " x width " + width + " x lenght " + lenght);
        System.out.println("Condition: " + condition + " (N:new U:used)");
        System.out.println("Is Disponible: " + isDisponible);
        System.out.println(hasAmazonWarranty ? "Has Amazon Warranty" : "Don't hava Amazon Warranty");
    }

    public float getTotalSolds(){
        return solds * price;
    }

    public double getVolume(){
        return width * height * lenght;
    }
}

