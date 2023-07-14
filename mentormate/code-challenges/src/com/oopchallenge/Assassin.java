package com.oopchallenge;

public class Assassin extends Hero {
    public Assassin(int healthPoints, int attackPoints, int armorPoints, String name) {
        super(healthPoints, attackPoints, armorPoints, name);
    }

    @Override
    public Attack attack(String name) {
        if(random.nextInt(100)+1<=(location == Location.THE_WOODS ? 35 : 30)){
            this.attack.setAttack(super.porcentage(300, super.attackPoints));
//            System.out.println("The Super Attack of " + this.getClass().getSimpleName() + " to " + name + " is " + this.attack.getAttack());
            return attack;
        }
        if(random.nextInt(100)+1<=10){
            this.attack.setAttack(super.porcentage(450, super.attackPoints));
//            System.out.println("The Super Attack of " + this.getClass().getSimpleName() + " to " + name + " is " + this.attack.getAttack());
            return attack;
        }
        return super.attack(name);
    }

}
