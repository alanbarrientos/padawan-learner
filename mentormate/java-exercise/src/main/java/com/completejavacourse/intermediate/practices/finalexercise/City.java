package com.completejavacourse.intermediate.practices.finalexercise;

public class City {
    private Building[] buildings = new Building[4];
    public City() {
        Item[] computerMarkedItems = new Item[2];
        Item[] jewelryItems = new Item[2];
        Item[] InstrumentsMarkedItems = new Item[2];
        Item[] coffeeShop = new Item[2];

        computerMarkedItems[0] = new Item("Notebook Gamer", 500.0);
        computerMarkedItems[1] = new Item("Mechanical Keyboard", 33.2);

        jewelryItems[0] = new Item("Golden Ring", 199.9);
        jewelryItems[1] = new Item("silver bracelet", 99.9);

        InstrumentsMarkedItems[0] = new Item("Electric Guitar", 75.5);
        InstrumentsMarkedItems[1] = new Item("Ukelele", 35.0);

        coffeeShop[0] = new Item("Starbucks coffe 200g", 20.3);
        coffeeShop[1] = new Item("Coffe Grinder", 100.0);

        buildings[0] = new Building("Computer Marked", computerMarkedItems);
        buildings[1] = new Building("Jewelry", jewelryItems);
        buildings[2] = new Building("Instrument Marked", InstrumentsMarkedItems);
        buildings[3] = new Building("Coffee shop", coffeeShop);
    }
    public Building[] getBuildings() {
        return buildings;
    }
}
