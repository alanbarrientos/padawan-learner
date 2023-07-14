package com.intermediate.practices.polymorphism;

public class AmazonProduct extends Product implements Giftable {
        private byte reviewStars;
        private short pieces;
        private int solds;
        private char condition;
        private boolean hasAmazonWarranty;

    public AmazonProduct(String title, byte reviewStars,
                         short pieces, int solds, float price,
                         float deliveryPrice, float weight, double height,
                         double width, double lenght, char condition,
                         boolean isDisponible, boolean hasAmazonWarranty)
    {
        super(title, solds, price, deliveryPrice, weight, height, width, lenght, isDisponible);
        this.reviewStars = reviewStars;
        this.pieces = pieces;
        this.solds = solds;
        this.condition = condition;
        this.hasAmazonWarranty = hasAmazonWarranty;
    }

    public void printDescription(){
        System.out.println("----------------------------Description--------------------------------");
        System.out.println(super.title);
        System.out.println("Pieces: " + pieces);
        System.out.println("Review Stars: " + reviewStars);
        System.out.println("Solds: " + solds);
        System.out.println("Price: $" + super.price);
        System.out.println("Delivery Price: $" + super.deliveryPrice);
        System.out.println("Weith: " + super.weight + "kg.");
        System.out.println("Size: height " + super.height + " x width " + super.width + " x lenght " + super.lenght);
        System.out.println("Condition: " + condition + " (N:new U:used)");
        System.out.println("Is Disponible: " + super.isDisponible);
        System.out.println(hasAmazonWarranty ? "Has Amazon Warranty" : "Don't hava Amazon Warranty");
        System.out.println("-----------------------------------------------------------------------");

    }

    public float getTotalSolds(){
        return solds * price;
    }

    public double getVolume(){
        return width * height * lenght;
    }




    @Override
    public float getWeightInKg() {
        System.out.println("the method getWeightInKg was called");
        return super.getWeightInKg();
    }

    @Override
    public float getDiscountByPorcentage(int porcentage) {
        return price*porcentage/100;
    }

    @Override
    public void addGiftToTheProduct(String nameGift) {
        System.out.println("The product will be sent with " + nameGift +" as gift");
    }
}

