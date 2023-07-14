package com.completejavacourse.intermediate.practices.finalexercise;



import java.util.Random;

public class Police {
    private Detective sargentoRefugio;
    Police() {
        Item[] sargentoItems = new Item[2];
        sargentoItems[0] = new Item("handcuffs", 70.5);
        sargentoItems[1] = new Item("gun", 300.0);
        sargentoRefugio = new Detective("Sargento Refugio", "Poli", 1934, "He has kind heard", sargentoItems);
    }
    public boolean catchCriminals(Gang gang) {
        if (areCriminalsCaught()) {
            System.out.println(sargentoRefugio.getName() + " managed to catch the gang");
            if (gang.getSumRobbedValue() > 0) {
                System.out.println("The stolen items are recovered. Their overall value is\n" +
                        " estimated to $" + gang.getSumRobbedValue());
            } else {
                System.out.println("The gang couldn't steal anything.");
            }
            return true;
        } else {
            System.out.println(sargentoRefugio.getName() + " don't manage catch the criminals.");
            System.out.println("They managed to steal items valued $" + gang.getSumRobbedValue());
            return false;
        }
    }
    private boolean areCriminalsCaught() {
        Random random = new Random();
        return random.nextInt(101) < Detective.SUCCESS_PERCENTAGE;
    }
}
