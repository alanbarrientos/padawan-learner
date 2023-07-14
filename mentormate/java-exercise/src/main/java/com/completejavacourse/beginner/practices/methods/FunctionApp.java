package com.completejavacourse.beginner.practices.methods;

public class FunctionApp {
    static final String CONDITIONGOOD = "good";
    static final String CONDITIONBAD = "bad";
    static final String CONDITIONDAMAGED = "damaged";

    public static void main(String[] args) {
        sayHello();
        blackboardPunishment(5);
        System.out.println("The price with 10% off: " + getTenPercentOffDiscountPrice(50000));
        System.out.println("The price with 50% off: " + getDiscountPrice(50000, 50));
        System.out.println("The price with 40% off: " + getDiscountPrice(50000.75, 60));
        System.out.println("The energy consumption is " + getEnergyEfficiencyCategory('e'));
        printCarDescription("Toyota 4Runner", 2021, CONDITIONGOOD);
    }

    static void sayHello(){
        System.out.println("Hello there!!!");
    }

    static void blackboardPunishment(int repeatNumber){
        for (int i = 1; i <= repeatNumber; i++){
            System.out.println("BEING RIGHT #@!*!");
        }
    }

    static double getTenPercentOffDiscountPrice(int price){
        return price * 0.9;
    }

    static double getDiscountPrice(int price, int percent){
        return price * percent / 100.0;
    }

    static double getDiscountPrice(double price, int percent){
        return price * percent / 100;
    }

    static String getEnergyEfficiencyCategory(char energyCategory){
        energyCategory = Character.toUpperCase(energyCategory);
        String energyEfficiencyCategory ="";
        switch (energyCategory){
            case 'A':
                energyEfficiencyCategory = "Very low";
                break;
            case 'B':
                energyEfficiencyCategory = "Low";
                break;
            case 'C':
                energyEfficiencyCategory = "Normal";
                break;
            case 'D':
                energyEfficiencyCategory = "Above normal";
                break;
            case 'E':
                energyEfficiencyCategory = "High";
                break;
            case 'F':
                energyEfficiencyCategory = "Very high";
                break;
            case 'G':
                energyEfficiencyCategory = "Extremely high";
                break;
            default:
                energyEfficiencyCategory = "Not define";
                break;
        }
        return energyEfficiencyCategory;
    }

    static void printCarDescription(String model, int productionYear, String condition){

        System.out.println(
                "The Model of the car is " + model +
                "\nManufacturing year: " + productionYear +
                "\nThe condition of the car is " + condition
        );

    }
}
