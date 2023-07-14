package com.oopchallenge;

public class AttackImpl implements Attack {
    int attack = 0;

    public AttackImpl(int valueAttack){
        this.attack = valueAttack;
    }
    @Override
    public int getValueAttack() {
        return attack;
    }

    @Override
    public void reportDamage(int damage) {
    }
}
