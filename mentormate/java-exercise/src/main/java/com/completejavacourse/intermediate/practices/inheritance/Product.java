package com.completejavacourse.intermediate.practices.inheritance;

public class Product {
    protected String title;
    protected int solds;
    protected float price;
    protected float deliveryPrice;
    protected float weight; //the weight is in pounds
    protected double height;
    protected double width;
    protected double lenght;
    protected boolean isDisponible;

    public Product(String title, int solds, float price, float deliveryPrice,
                   float weight, double height, double width,
                   double lenght, boolean isDisponible)
    {
        this.title = title;
        this.solds = solds;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.lenght = lenght;
        this.isDisponible = isDisponible;
    }

    public float getTotalSolds(){
        return solds * price;
    }

    public double getVolume(){
        return width * height * lenght;
    }

    public float getWeightInKg(){
        return weight / 2.205f;
    }


}
