package com.intermediate.practices.finalexercise;



public class TheGreatRobberyApp {

    public static void main(String[] args) {
        City asuncion = new City();
        Gang gangChespirito = new Gang();
        Police police = new Police();
        gangChespirito.showGangInfo();
        System.out.println("The chase began");
        do {
            gangChespirito.letsRob(asuncion.getBuildings());
        } while (!police.catchCriminals(gangChespirito));
    }
}
