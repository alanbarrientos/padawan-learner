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
    @Mock
    Random random;

    @BeforeEach
    public void initializateWarrior(){
        warrior = new Warrior(100,100,10,"WarriorTest", random);
    }


    @Test
    public void shouldAttack150PercentInLocationBattlefield(){
        when(random.nextInt(anyInt())).thenReturn(9);
        warrior.changeLocation(Location.BATTLEFIELD);
        assertAttack(warrior, 100, 80, 120, 150);
    }
    @Test
    public void shouldAttackRandom80To120PercentInLocationBattlefield(){
        when(random.nextInt(anyInt())).thenReturn(49, 30);
        warrior.changeLocation(Location.BATTLEFIELD);
        assertAttack(warrior, 100, 80, 120, 150);
    }

    @Test
    public void shouldAttackOrRandom80To120Percent(){
        when(random.nextInt(anyInt())).thenReturn(49, 30);
        assertAttack(warrior, 100, 80, 120);
    }

    @Test
    public void shouldDefendBetween80To120Percent(){
        when(random.nextInt(anyInt())).thenReturn(30);
        Attack attack = new AttackImpl(100);
        assertDefence(warrior, attack, 10, 80, 120);
    }

    @Test
    public void shouldBeLive(){
        assertTrue(warrior.isStillAlive());
    }

    @Test
    public void shouldBeDied(){
        when(random.nextInt(anyInt())).thenReturn(30);
        Attack attack = new AttackImpl(2000);
        warrior.defending(attack);
        assertFalse(warrior.isStillAlive());
    }

    @Test
    public void shouldResetHealth(){
        when(random.nextInt(anyInt())).thenReturn(30);
        Attack attack = new AttackImpl(2000);
        warrior.defending(attack);
        warrior.resetHealth();
        assertTrue(warrior.isStillAlive());
    }


}