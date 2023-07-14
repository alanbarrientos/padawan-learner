package com.oopchallenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.oopchallenge.Location.*;

public class Referee {
    private static final int WARRIOR = 1;
    private static final int KNIGHT = 2;
    private static final int ASSASSIN = 3;
    private static final int MONK = 4;

    private static final Path filePath =Path.of("code-challenges\\src\\com\\oopchallenge\\names.txt");
    private List<String> names;
    private HashMap<String, Statistics> summaryHeroFights = new HashMap<>();
    HonorableMention highestDamage = new HonorableMention("Highest Damage Output");
    HonorableMention highestNumberOfRejectedAttacks = new HonorableMention("Highest Number of rejected Attacks");

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    ConsoleFancyOutput cfo = new ConsoleFancyOutput();
//    Hero firstHero;
//    Hero secondHero;
    ArrayList<Hero> heroes;
    public void starGame(){
        System.out.println(cfo.blue("GO FIGHTERS GAME"));
        System.out.println(cfo.cyan("Please enter a even number of heroes that you whant see Fight between 2 and 1000"));
        int entryUser = getEnterValue(2,1000);
        initializeRandomHeroes(entryUser);
        startRounds();
        highestDamage.printHonorableMention();
        highestNumberOfRejectedAttacks.printHonorableMention();
    }

    private void startRounds() {
//        suggestion of Daniel Check the logic of the rounds
//        rounds = heroes.length/2 before
//        for(int i=1; i<=rounds; i++) before
        while(heroes.size()>1){
            int cuantityOfFights = heroes.size()/2;
            for(int i=1; i<=cuantityOfFights; i++){
                Hero firstHero = heroes.get(i-1);
                Hero secondHero = heroes.get(i);
                int winner = starFight(firstHero, secondHero);
                if(heroes.size()==2){
                    System.out.println("-----------------------------\n");
                    System.out.println("Winner Statistics");
                    summaryHeroFights.get((winner==1 ? firstHero.getName() : secondHero.getName())).printStatistics();
                    System.out.println("-----------------------------\n");
                    System.out.println("Runner-up Statistics");
                    summaryHeroFights.get((winner==1 ? secondHero.getName() : firstHero.getName())).printStatistics();
                }
                if(winner==1){
                    heroes.remove(i);
                }else{
                    heroes.remove(i-1);
                }
            }
        }
    }

    private int starFight(Hero firstHero, Hero secondHero) {
        String firtHeroType = firstHero.getClass().getSimpleName();
        String secondHeroType = secondHero.getClass().getSimpleName();
        int rejectedAttacksFirsHero = 0;
        int rejectedAttacksSecondHero = 0;
        int numberOfTurns = 0;
        Location location = getRandomLocation();
        firstHero.changeLocation(location);
        secondHero.changeLocation(location);
        boolean fightContinue = true;
        while(fightContinue){
            numberOfTurns++;
            int damageFirstHero = secondHero.defending(firstHero.attack(secondHeroType));
            highestDamage.checkMaxValue(firstHero, damageFirstHero);
            if(damageFirstHero == 0 && heroes.size() > 2){
                rejectedAttacksFirsHero++;
            }
            if(!secondHero.isStillAlive()){
                highestNumberOfRejectedAttacks.checkMaxValue(firstHero, rejectedAttacksFirsHero);
                firstHero.resetHealth();
                saveSummaryFight(firstHero, secondHero, numberOfTurns, String.valueOf(location));
                return 1;
            }
            int damageSecondHero = secondHero.defending(secondHero.attack(firtHeroType));
            highestDamage.checkMaxValue(secondHero, damageSecondHero);
            numberOfTurns++;
            if(damageSecondHero == 0 && heroes.size() > 2){
                rejectedAttacksSecondHero++;
            }
            if(!secondHero.isStillAlive()){
                highestNumberOfRejectedAttacks.checkMaxValue(secondHero, rejectedAttacksSecondHero);
                secondHero.resetHealth();
                saveSummaryFight(secondHero, firstHero, numberOfTurns, String.valueOf(location));
                return 2;
            }
        }
        return -1;
    }


    private void initializeRandomHeroes(int cuantity){
        try{
            getNamesFromFile();
        }catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<Hero> heroes = new ArrayList<>();
        for(int i=0; i<cuantity; i++){
            Hero newHero = getHero(
                    random.nextInt(4)+1,
                    random.nextInt(30)+51,
                    random.nextInt(10)+11,
                    random.nextInt(5)+6
                    );
            heroes.add(newHero);
        }
        this.heroes = heroes;
    }
    private Location getRandomLocation(){
        int randomNumber = random.nextInt(4)+1;
        switch (randomNumber){
            case 1:
                return TEMPLE;
            case 2:
                return CASTLE;
            case 3:
                return BATTLEFIELD;
            case 4:
                return THE_WOODS;
        }
        return null;
    }

    private int getEnterValue(int min, int max){
        boolean isAValidEnter = false;
        String enteredValue = "";
        do{
            try {
                enteredValue = scanner.nextLine();
                if(Integer.parseInt(enteredValue)<=max && Integer.parseInt(enteredValue)>=min && Integer.parseInt(enteredValue) % 2 == 0){
                    isAValidEnter = true;
                }else{
                    System.out.println(cfo.yellow("Please enter a valid even number between " + min + " and " + max));
                }
            }catch (Exception e){
                System.out.println(cfo.yellow("Please enter a valid even number between " + min + " and " + max));
            }
        }while (!isAValidEnter);
        return Integer.parseInt(enteredValue);
    }

    private Hero getHero(int option, int healthPoints, int attackPoints, int armorPoints){
        switch (option){
            case WARRIOR:
                return new Warrior(healthPoints, attackPoints, armorPoints, getRadmonName(), new Random());
            case KNIGHT:
                return new Knight(healthPoints, attackPoints, armorPoints, getRadmonName(), new Random());
            case ASSASSIN:
                return new Assassin(healthPoints, attackPoints, armorPoints, getRadmonName(), new Random());
            case MONK:
                return new Monk(healthPoints, attackPoints, armorPoints, getRadmonName(), new Random() );
            default:
                return null;
        }
    }

    private String getRadmonName(){
        Random random = new Random();
        String name;
        name = names.get(random.nextInt(names.size())).toUpperCase();
        names.remove(name);
        return name;
    }

    public void saveSummaryFight(Hero winner, Hero defeated, int turnsTaken, String location){
        if(!summaryHeroFights.containsKey(winner.getName())){
            Statistics statistics = new Statistics();
            summaryHeroFights.put(winner.getName(), statistics);
        }
        if(!summaryHeroFights.containsKey(defeated.getName())){
            Statistics statistics = new Statistics();
            summaryHeroFights.put(defeated.getName(), statistics);
        }
        summaryHeroFights.get(winner.getName()).addFight(winner, defeated, turnsTaken, location);
        summaryHeroFights.get(defeated.getName()).addFight(winner, defeated, turnsTaken, location);
    }
    private void getNamesFromFile() throws IOException {
        names = Files.lines(filePath).collect(Collectors.toList());
    }

}
