package com.oopchallenge;

import javax.xml.namespace.QName;

public class Knight extends Hero {
    public Knight(int healthPoints, int attackPoints, int armorPoints, String name) {
        super(healthPoints, attackPoints, armorPoints,name);
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

    @Override
    public int defending(Attack attack) {
        if(random.nextInt(100)+1<=20){
//            System.out.println("The attack was blocked");
            attack.reportDamage(0);
            return 0;
        }
        if(location == Location.CASTLE){
            int damage = attack.getAttack() - randomPorcentage(80,120, armorPoints*110/100);
            attack.reportDamage(damage);
            healthPoints = healthPoints - damage;
            if(healthPoints>0){
//            System.out.println("The " + this.getClass().getSimpleName() + " health is " + healthPoints);
            }
            return damage;
        }
        return super.defending(attack);
    }

    @Override
    public void heals(int damage) {
        if(location == Location.BATTLEFIELD && damage>0){
//            heals 5%
            this.healthPoints = this.porcentage(105, this.healthPoints);
        }
    }
}
