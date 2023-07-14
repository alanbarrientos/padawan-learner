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
public class AssassinTest extends TestBase {
    Assassin assassin;

    @Mock
    Random random;


    @BeforeEach
    public void initializateHero(){
        assassin = new Assassin(100,100,10,"AssasinTest", random);

    }
    @Test
    public void shouldAttack300PercentInLocationThe_Woods(){
        //its expected that he have and extra 5% percent over the 30% that he have to attack 300
        when(random.nextInt(anyInt())).thenReturn(34);
        assassin.changeLocation(Location.THE_WOODS);
        assertAttack(assassin, 100, 300);
    }
    @Test
    public void shouldAttack450PercentInLocationThe_Woods(){
        //its Expected that he attack in woods with 450 of attack wich is 10 extra percent that he will attack
        when(random.nextInt(anyInt())).thenReturn(36, 9);
        assassin.changeLocation(Location.THE_WOODS);
        assertAttack(assassin, 100, 450);
    }
    @Test
    public void shouldAttackRandom80To120PercentInLocationThe_Woods(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        when(random.nextInt(anyInt())).thenReturn(50, 50, 30);
        assassin.changeLocation(Location.THE_WOODS);
        assertAttack(assassin, 100, 80,120);
    }

    @Test
    public void shouldAttack300Percent(){
        //we have initial 30 percent of make an attack of 300e
        when(random.nextInt(anyInt())).thenReturn(29);
        assertAttack(assassin, 100, 300);
    }

    @Test
    public void shouldAttackRandom80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        when(random.nextInt(anyInt())).thenReturn(49, 30);
        assassin.changeLocation(Location.THE_WOODS);
        assertAttack(assassin, 100, 80,120);
    }

    @Test
    public void shouldDefendBetween80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        when(random.nextInt(anyInt())).thenReturn(30);
        Attack attack = new AttackImpl(100);
        assertDefence(assassin, attack, 10, 80, 120);
    }

    @Test
    public void shouldBeLive(){
        assertTrue(assassin.isStillAlive());
    }

    @Test
    public void shouldBeDied(){
        when(random.nextInt(anyInt())).thenReturn(30);
        Attack attack = new AttackImpl(2000);
        assassin.defending(attack);
        assertFalse(assassin.isStillAlive());
    }

    @Test
    public void shouldResetHealth(){
        when(random.nextInt(anyInt())).thenReturn(30);
        Attack attack = new AttackImpl(2000);
        assassin.defending(attack);
        assassin.resetHealth();
        assertTrue(assassin.isStillAlive());
    }

    
}