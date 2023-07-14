package com.oopchallenge;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WarriorTest extends TestBase {
    Warrior warrior;
    private final int default100Attack = 100;
    private final int default10Armor = 10;
    @Mock
    Random random;

    @BeforeEach
    public void initializateWarrior(){
        int defauld100Health = 100;

        warrior = new Warrior(
                defauld100Health,
                default100Attack,
                default10Armor,
                "WarriorTest",
                random
        );
    }


    @Test
    public void shouldAttack150PercentInLocationBattlefield(){
        int expected150Attack = 150;
        int valueToHaveAnAttackOf150 = 9;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnAttackOf150);
        warrior.changeLocation(Location.BATTLEFIELD);

        assertAttack(warrior, default100Attack, expected150Attack);
    }
    @Test
    public void shouldAttackRandom80To120PercentInLocationBattlefield(){
        int valueNotToHaveAnAttackOf150 = 11;
        int valueForAttackBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueNotToHaveAnAttackOf150, valueForAttackBetween80And120);
        warrior.changeLocation(Location.BATTLEFIELD);

        assertAttack(warrior, default100Attack, 80, 120);
    }

    @Test
    public void shouldAttackOrRandom80To120Percent(){
        int valueNotToHaveAnAttackOf150 = 11;
        int valueForAttackBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueNotToHaveAnAttackOf150, valueForAttackBetween80And120);

        assertAttack(warrior, default100Attack, 80, 120);
    }

    @Test
    public void shouldDefendBetween80To120Percent(){
        int valueForDefendBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueForDefendBetween80And120);
        Attack attack = new AttackImpl(default100Attack);

        assertDefence(warrior, attack, default10Armor, 80, 120);
    }

    @Test
    public void shouldBeDied(){
        int impossibleToDefend = 2000;
        int valueForDefendBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueForDefendBetween80And120);
        Attack attack = new AttackImpl(impossibleToDefend);
        warrior.defending(attack);

        assertFalse(warrior.isStillAlive());
    }

    @Test
    public void shouldBeLive(){
        assertTrue(warrior.isStillAlive());
    }

    @Test
    public void shouldResetHealth(){
        int impossibleToDefend = 2000;
        int valueForDefendBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueForDefendBetween80And120);
        Attack attack = new AttackImpl(impossibleToDefend);
        warrior.defending(attack);
        warrior.resetHealth();

        assertTrue(warrior.isStillAlive());
    }

}