package com.oopchallenge;

public class Warrior extends Hero {
    public Warrior(int healthPoints, int attackPoints, int armorPoints, String name) {
        super(healthPoints, attackPoints, armorPoints, name);
    }

    @Override
    public Attack attack(String name) {
        if(random.nextInt(100)+1<=10){
            this.attack.setAttack(super.porcentage(200, super.attackPoints));
//            System.out.println("The Super Attack of " + this.getClass().getSimpleName() + " to " + name + " is " + this.attack.getAttack());
            return this.attack;
        }
        return super.attack(name);
    }
}
