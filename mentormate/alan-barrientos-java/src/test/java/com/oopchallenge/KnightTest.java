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
    private final int default100Attack = 100;
    private final int default10Armor = 10;

    @Mock
    Random random;

    @BeforeEach
    public void initializateKnight(){
        int default100Health = 100;
        knight = new Knight(
                default100Health,
                default100Attack,
                default10Armor,
                "KnightTest",
                random
        );
    }

    @Test
    public void shouldAttack200Percent(){
        //Have 10 percent of make an attack of 200
        int valueToHaveAnAttackOf200 = 9;
        int expected200Attack = 200;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnAttackOf200);

        assertAttack(knight, default100Attack, expected200Attack);
    }
    @Test
    public void shouldAttackRandom80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        int valueToHaveAnAttackBetween80And120 = 11;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnAttackBetween80And120);

        assertAttack(knight, default100Attack, 80, 120);
    }


    @Test
    public void shouldDefendBetween80To120Percent(){
        //the percentage that I pass in the end in Random is 30 because we need a number bettwen 120 and 80, then 30 plus 80 is 110
        int valueToHaveAnDefendBetween80And120 = 21;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnDefendBetween80And120);
        Attack attack = new AttackImpl(default100Attack);

        assertDefence(knight, attack, default10Armor, 80, 120);
    }

    @Test
    public void shouldDodge(){
//        There are 20% that he reject the attack
        int valueToRejectAnAttack = 19;
        int impossibleToDefendExceptRejecting = 2000;

        when(random.nextInt(anyInt())).thenReturn(valueToRejectAnAttack);
        Attack attack = new AttackImpl(impossibleToDefendExceptRejecting);
        knight.defending(attack);

        assertTrue(knight.isStillAlive());
    }

    @Test
    public void shouldBeLive(){
        assertTrue(knight.isStillAlive());
    }
    @Test
    public void shouldDie(){
        int impossibleToDefendExceptRejecting = 2000;
        int valueNotToRejectAnAttack = 21;

        when(random.nextInt(anyInt())).thenReturn(valueNotToRejectAnAttack);
        Attack attack = new AttackImpl(impossibleToDefendExceptRejecting);
        knight.defending(attack);

        assertFalse(knight.isStillAlive());
    }

    @Test
    public void shouldResetHealth(){
        int impossibleToDefendExceptRejecting = 2000;
        int valueToHaveAnDefendBetween80And120 = 21;

        when(random.nextInt(anyInt())).thenReturn(valueToHaveAnDefendBetween80And120);
        Attack attack = new AttackImpl(impossibleToDefendExceptRejecting);
        knight.defending(attack);
        knight.resetHealth();

        assertTrue(knight.isStillAlive());
    }


}