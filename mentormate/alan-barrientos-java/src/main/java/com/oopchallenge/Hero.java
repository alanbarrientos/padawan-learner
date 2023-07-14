package com.oopchallenge;

import java.util.Random;

public abstract class Hero{
    protected int healthPoints;
    protected int attackPoints;
    protected int armorPoints;
    protected String name;
    private int healthPointsBAC;
    protected Location location;
    protected Random random;


    public Hero(int healthPoints, int attackPoints, int armorPoints, String name, Random random) {
        this.healthPoints = healthPoints;
        this.healthPointsBAC = healthPoints;
        this.attackPoints = attackPoints;
        this.armorPoints = armorPoints;
        this.name = name;
        this.random = random;

    }

    public Attack attack(String name){
        int attackPower = randomPorcentage(80,120, attackPoints);
//        System.out.println("The attack of " + this.getClass().getSimpleName() + " to " + name + " is " + attackPower);
        Attack attack = new AttackHero(attackPower);
        return attack;
    }
    public int defending(Attack attack){
        int damage = attack.getValueAttack() - randomPorcentage(80,120, armorPoints);
        attack.reportDamage(damage);
        healthPoints = healthPoints - damage;
        if(healthPoints>0){
//            System.out.println("The " + this.getClass().getSimpleName() + " health is " + healthPoints);
        }
        return damage;
    }
    protected int randomPorcentage(int starRange, int endRange, int number){
        return (starRange+(random.nextInt(endRange-starRange+1)))*number/100;
    }
    protected int porcentage(int porcentage, int number){
        return number * porcentage / 100;
    }

//    public void printHeroDescription(){
//        System.out.println(this.getClass().getSimpleName());
//        System.out.println(
//                "HealthPoints: " + healthPoints +
//                "\nAttackPoints: " + attackPoints +
//                "\n ArmorPoint: " + armorPoints
//        );
//    }

    public void resetHealth(){
        healthPoints = healthPointsBAC;
    }

    protected void heals(int damage){

    }

    public void changeLocation(Location location) {
        this.location = location;
    }

    public boolean isStillAlive(){
        return healthPoints>0;
    }
    class AttackHero implements Attack {
        int attack = 0;

        public AttackHero(int valueAttack){
            this.attack = valueAttack;
        }
        @Override
        public int getValueAttack() {
            return attack;
        }

        @Override
        public void reportDamage(int damage) {
            heals(damage);
        }
    }
    public String getName(){
        return this.name;
    }
}
