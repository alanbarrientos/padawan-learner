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
public class MonkTest extends TestBase {
    Monk monk;
    private final int default100Attack = 100;
    private final int default10Armor = 10;

    @Mock
    Random random;

    @BeforeEach
    public void initializateMonk(){
        int default100Health = 100;

        monk = new Monk(
                default100Health,
                default100Attack,
                default10Armor,
                "MonkTest",
                random
        );

    }

    @Test
    public void shouldAttackRandom80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        int valueToHaveAnAttackBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnAttackBetween80And120);

        assertAttack(monk, default100Attack, 80, 120);
    }

    @Test
    public void shouldDefendBetween80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        int valueToHaveAnDefendBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnDefendBetween80And120);
        Attack attack = new AttackImpl(100);

        assertDefence(monk, attack, 10, 80, 120);
    }

    @Test
    public void shouldDodge(){
//        There are 20% that he reject the attack
        int valueToRejectAttack = 29;
        int impossibleToDefendExceptRejecting = 2000;


        when(random.nextInt(anyInt())).thenReturn(valueToRejectAttack);
        Attack attack = new AttackImpl(impossibleToDefendExceptRejecting);
        monk.defending(attack);

        assertTrue(monk.isStillAlive());

    }
    @Test
    public void shouldDodgeInTemple(){
//        There are 20% that he reject the attack
        int valueToRejectAttack = 9;
        int impossibleToDefendExceptRejecting = 2000;


        when(random.nextInt(anyInt())).thenReturn(valueToRejectAttack);
        Attack attack = new AttackImpl(impossibleToDefendExceptRejecting);
        monk.changeLocation(Location.TEMPLE);
        monk.defending(attack);

        assertTrue(monk.isStillAlive());

    }
    @Test
    public void shouldDie(){
        int impossibleToDefend = 2000;
        int valueNotToReject = 35;

        when(random.nextInt(anyInt())).thenReturn(valueNotToReject);
        Attack attack = new AttackImpl(impossibleToDefend);
        monk.defending(attack);

        assertFalse(monk.isStillAlive());
    }


    @Test
    public void shouldResetHealth(){
        int impossibleToDefend = 2000;
        int valueToDefendBetween80And120 = 30;

        when(random.nextInt(anyInt())).thenReturn(valueToDefendBetween80And120);
        Attack attack = new AttackImpl(impossibleToDefend);
        monk.defending(attack);
        monk.resetHealth();

        assertTrue(monk.isStillAlive());
    }

}