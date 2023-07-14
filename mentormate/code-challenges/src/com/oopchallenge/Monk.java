package com.oopchallenge;

public class Monk extends Hero {
    public Monk(int healthPoints, int attackPoints, int armorPoints, String name) {
        super(healthPoints, attackPoints, armorPoints, name);
    }

    @Override
    public int defending(Attack attack) {
        if(location == Location.TEMPLE && random.nextInt(100)+1<=10){
//            System.out.println("The attack was blocked");
            attack.reportDamage(0);
            return 0;
        }
        if(random.nextInt(100)+1<=30){
//            System.out.println("The attack was blocked");
            attack.reportDamage(0);
            return 0;
        }
        return super.defending(attack);
    }
}
