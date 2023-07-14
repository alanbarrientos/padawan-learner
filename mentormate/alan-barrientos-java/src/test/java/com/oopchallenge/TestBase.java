package com.oopchallenge;

import org.junit.jupiter.api.Assertions;

public class TestBase {
    protected static void assertAttack(Hero hero, int attackPoints, int startPercentage, int endPercentage, int  ... extraPercentages){
        Attack attackOfWarrior = hero.attack("AnotherFighter");
        int valueAttack = attackOfWarrior.getValueAttack();
        if(valueAttack >= attackPoints * startPercentage / 100 && valueAttack <= attackPoints * endPercentage / 100){
            Assertions.assertTrue(true);
            return;
        }
        if(extraPercentages != null){
            for (int extraPercentage: extraPercentages){
                if(valueAttack == attackPoints * extraPercentage / 100){
                    Assertions.assertTrue(true);
                    return;
                }
            }
        }
        Assertions.assertTrue(false);
    }
    protected static void assertAttack(Hero hero, int attackPoints, int expectedPercentages){
        Attack attackOfWarrior = hero.attack("AnotherFighter");
        int valueAttack = attackOfWarrior.getValueAttack();
        if(valueAttack == attackPoints * expectedPercentages / 100){
            Assertions.assertTrue(true);
            return;
        }
        Assertions.assertTrue(false);
    }

    protected static void assertDefence(Hero hero, Attack attack, int armorPoints, int startPercentage, int endPercentage) {
        int attackValue = attack.getValueAttack();
        int damage = hero.defending(attack);
        if(damage <= attackValue - armorPoints * startPercentage / 100 && damage >= attackValue - armorPoints * endPercentage / 100){
            Assertions.assertTrue(true);
            return;
        }
        Assertions.assertTrue(false);
    }

}
