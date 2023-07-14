package com.oopchallenge;

import java.util.Random;

public class Warrior extends Hero {
    public Warrior(int healthPoints, int attackPoints, int armorPoints, String name, Random random) {
        super(healthPoints, attackPoints, armorPoints, name, random);
    }

    @Override
    public Attack attack(String name) {
        if(random.nextInt(100)+1<=10 && location == Location.BATTLEFIELD){
            Attack attack = new AttackHero(super.porcentage(150, super.attackPoints));
//            System.out.println("The Super Attack of " + this.getClass().getSimpleName() + " to " + name + " is " + this.attack.getAttack());
            return attack;
        }
        return super.attack(name);
    }

    @Override
    protected void heals(int damage) {
        if(damage > 0 && location == Location.BATTLEFIELD){
            this.healthPoints = this.healthPoints + (damage * 5 / 100);
        }
    }
}
