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
    private final int default100OfAttack = 100;
    private final int default10Armor = 10;

    @Mock
    Random random;

    @BeforeEach
    public void initializateHero(){
        int default100Health = 100;

        assassin = new Assassin(
                default100Health,
                default100OfAttack,
                default10Armor,
                "AssasinTest",
                random
        );

    }
    @Test
    public void shouldAttack300PercentInLocationThe_Woods(){
        //its expected that he have and extra 5% percent over the 30% that he have to attack 300
        int valueToHaveAnAttackOf300 = 34;
        int expected300OfAttack = 300;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnAttackOf300);

        assassin.changeLocation(Location.THE_WOODS);
        assertAttack(assassin, default100OfAttack, expected300OfAttack);
    }
    @Test
    public void shouldAttack450PercentInLocationThe_Woods(){
        //its Expected that he attack in woods with 450 of attack wich is 10 extra percent that he will attack
        int valueNotToUse35PercentOfMake300attack = 36;
        int valueToHaveAnAttackOf450 = 9;
        int expected450Attack = 450;

        when(random.nextInt(anyInt())).thenReturn(valueNotToUse35PercentOfMake300attack, valueToHaveAnAttackOf450);
        assassin.changeLocation(Location.THE_WOODS);

        assertAttack(assassin, default100OfAttack, expected450Attack);
    }
    @Test
    public void shouldAttackRandom80To120PercentInLocationThe_Woods(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        int valueNotToUse35PercentOfMake300Attack = 36;
        int valueNotToUse10ExtraPercentOfMake450Attack = 11;
        int valueToMakeAnAttackBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(
                valueNotToUse35PercentOfMake300Attack,
                valueNotToUse10ExtraPercentOfMake450Attack,
                valueToMakeAnAttackBetween80And120
        );
        assassin.changeLocation(Location.THE_WOODS);

        assertAttack(assassin, default100OfAttack, 80,120);
    }

    @Test
    public void shouldAttack300Percent(){
        //we have initial 30 percent of make an attack of 300
        int valueToHaveAnAttackOf300 = 29;
        int expected300Attack = 300;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnAttackOf300);

        assertAttack(assassin, default100OfAttack, expected300Attack);
    }

    @Test
    public void shouldAttackRandom80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        int valueNotToUse30PercentOfMake300Attack = 31;
        int valueToMakeAnAttackBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(
                valueNotToUse30PercentOfMake300Attack,
                valueToMakeAnAttackBetween80And120);

         assertAttack(assassin, default100OfAttack, 80,120);
    }

    @Test
    public void shouldDefendBetween80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        int valueToMakeADefenceBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueToMakeADefenceBetween80And120);
        Attack attack = new AttackImpl(100);

        assertDefence(assassin, attack, default10Armor, 80, 120);
    }

    @Test
    public void shouldBeLive(){
        assertTrue(assassin.isStillAlive());
    }

    @Test
    public void shouldBeDied(){
        int impossibleToDefend = 2000;
        int valueToMakeADefenceBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueToMakeADefenceBetween80And120);
        Attack attack = new AttackImpl(impossibleToDefend);
        assassin.defending(attack);

        assertFalse(assassin.isStillAlive());
    }

    @Test
    public void shouldResetHealth(){
        int impossibleToDefend = 2000;
        int valueToMakeADefenceBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueToMakeADefenceBetween80And120);
        Attack attack = new AttackImpl(impossibleToDefend);
        assassin.defending(attack);
        assassin.resetHealth();

        assertTrue(assassin.isStillAlive());
    }

    
}