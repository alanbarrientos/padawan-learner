package com.completejavacourse.intermediate.practices.finalexercise;
import java.util.Random;
public class Gang {
    private Criminal[] criminals = new Criminal[2];
    private Random random = new Random();
    private double sumRobbedValue;
    Gang() {
        Item[] chompiraItems = new Item[2];
        Item[] botijaItems = new Item[2];
        chompiraItems[0] = new Item("Stone", 10.5);
        chompiraItems[1] = new Item("cigarette", 0.0);
        botijaItems[0] = new Item("bag", 20.0);
        botijaItems[1] = new Item("beret", 0.5);
        Criminal chompira = new Criminal("Chompira", "Chompi", 1929, "make laugh people", chompiraItems);
        Criminal botija = new Criminal("Botija", "Boti", 1948, "distract", botijaItems);
        criminals[0] = chompira;
        criminals[1] = botija;
    }
    public double getSumRobbedValue() {
        return sumRobbedValue;
    }
    public void showGangInfo() {
        for (int i=0; i<criminals.length; i++) {
            criminals[i].printBioData();
        }
    }
    private boolean isSuccessfulRobbery() {
        int randomNumber = random.nextInt(101);
        int totalSuccessPercentage = criminals.length * Criminal.SUCCESS_PERCENTAGE;
        return randomNumber < totalSuccessPercentage;
    }
    public void letsRob(Building[] buildings) {
        int randomIndexOfBuilding = random.nextInt(buildings.length);
        if (isSuccessfulRobbery()) {
            System.out.println("The gang managed to rob the following items from the " + buildings[randomIndexOfBuilding].getName() + ":");
            for(int i=0; i<buildings[randomIndexOfBuilding].getItems().length; i++){
                sumRobbedValue = sumRobbedValue + buildings[randomIndexOfBuilding].getItems()[i].getValue();
                System.out.println(buildings[randomIndexOfBuilding].getItems()[i].getName());
            }
        } else {
            System.out.println("The gang tried to rob the " + buildings[randomIndexOfBuilding].getName() + " but they failed.");
        }
    }
}
