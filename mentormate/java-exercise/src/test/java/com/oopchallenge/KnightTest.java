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
public class KnightTest extends TestBase{
    Knight knight;

    @Mock
    Random random;

    @BeforeEach
    public void initializateKnight(){
        knight = new Knight(100,100,10,"KnightTest", random);
    }

    @Test
    public void shouldAttack200Percent(){
        //Have 10 percent of make an attack of 200
        when(random.nextInt(anyInt())).thenReturn(9);
        assertAttack(knight, 100, 200);
    }
    @Test
    public void shouldAttackRandom80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        when(random.nextInt(anyInt())).thenReturn(30);
        assertAttack(knight, 100, 80, 120);
    }


    @Test
    public void shouldDefendBetween80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        when(random.nextInt(anyInt())).thenReturn(30);
        Attack attack = new AttackImpl(100);
        assertDefence(knight, attack, 10, 80, 120);
    }

    @Test
    public void shouldBeLive(){
        assertTrue(knight.isStillAlive());
    }

    @Test
    public void shouldDodge(){
//        There are 20% that he reject the attack
        when(random.nextInt(anyInt())).thenReturn(15);
        Attack attack = new AttackImpl(2000);
        knight.defending(attack);
        assertTrue(knight.isStillAlive());
    }
    @Test
    public void shouldDie(){
        when(random.nextInt(anyInt())).thenReturn(21);
        Attack attack = new AttackImpl(2000);
        knight.defending(attack);
        assertFalse(knight.isStillAlive());
    }

    @Test
    public void shouldResetHealth(){
        when(random.nextInt(anyInt())).thenReturn(30);
        Attack attack = new AttackImpl(2000);
        knight.defending(attack);
        knight.resetHealth();
        assertTrue(knight.isStillAlive());
    }


}