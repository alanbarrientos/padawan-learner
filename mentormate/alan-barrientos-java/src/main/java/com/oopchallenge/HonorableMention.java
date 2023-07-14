package com.oopchallenge;

public class HonorableMention {
    String name;
    Hero hero;
    int value = 0;
    ConsoleFancyOutput cfo = new ConsoleFancyOutput();

    public HonorableMention(String name) {
        this.name = name;
    }

    public void checkMaxValue(Hero hero, int value){
        if(value>this.value){
            this.value = value;
            this.hero = hero;
        }
    }



    public void printHonorableMention(){
        if(hero != null){
            System.out.println(cfo.green("-The Honorable mention of " + name + " with " + value + " its given to " + hero.getClass().getSimpleName() + " " + hero.getName()));
        }else{
            System.out.println(cfo.green("-No one out of the podium win the Honorable mention of " + name));
        }
    }
}
