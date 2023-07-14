package com.oopchallenge;

import java.util.Random;

public class Knight extends Hero {
    public Knight(int healthPoints, int attackPoints, int armorPoints, String name, Random random) {
        super(healthPoints, attackPoints, armorPoints, name, random);
    }

    @Override
    public Attack attack(String name) {
        if(random.nextInt(100)+1<=10){
            Attack attack = new AttackHero(super.porcentage(200, super.attackPoints));
//            System.out.println("The Super Attack of " + this.getClass().getSimpleName() + " to " + name + " is " + this.attack.getAttack());
            return attack;
        }
        return super.attack(name);
    }

    @Override
    public int defending(Attack attack) {
        if(random.nextInt(100)+1<=20){
//            System.out.println("The attack was blocked");
            attack.reportDamage(0);
            return 0;
        }
        return super.defending(attack);
    }

    @Override
    public void changeLocation(Location location) {
        if(location == Location.CASTLE){
            this.armorPoints = this.armorPoints * 110 / 100;
            this.healthPoints = this.healthPoints * 110 / 100;
        }
        super.changeLocation(location);
    }

}
