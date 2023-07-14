package com.expert.finalpractice;

import java.util.*;
import java.util.stream.Collectors;

public class StreamExerciseApp {

    public static void main(String[] args) {
        ArrayList<Planet> planets = PlanetRepository.getPlanets();
//        get the number of planets
        System.out.println("Number of distinct planets: " + planets.stream().distinct().count());

//        get the biggest
        Optional<Planet> biggestPlanet = planets.stream()
                .max(Comparator.comparing(Planet::getSize));
        if(biggestPlanet.isPresent()){
            System.out.println("Biggest planet is " + biggestPlanet.get().getName() + " Size: " + biggestPlanet.get().getSize() + "km.");
        }else{
            System.out.println("is Not Present");
        }

//        get the hottest
        Optional<Planet> hottestPlanet = planets.stream()
                .max(Comparator.comparing(Planet::getAvgTemperature));
        if(hottestPlanet.isPresent()){
            System.out.println("Hottest planet is " + hottestPlanet.get().getName() + " Temperature: " + hottestPlanet.get().getAvgTemperature() + "C.");
        }else{
            System.out.println("is Not Present");
        }

//        get the coldest
        Optional<Planet> coldestPlanet = planets.stream()
                .min(Comparator.comparing(Planet::getAvgTemperature));
        if(coldestPlanet.isPresent()){
            System.out.println("Coldest planet is" + coldestPlanet.get().getName() + " Temperature: " + coldestPlanet.get().getAvgTemperature() + "C.");
        }else{
            System.out.println("is Not Present");
        }

//       Top 10 Habitable planets the avg temperature of the earth is 15
        List<Planet> top10Planets = planets.stream()
                .filter(planet -> "solid".equals(planet.getType()))
                .sorted(Comparator.comparing((Planet p) -> Math.abs(p.getAvgTemperature() - 15))
                        .thenComparing(Planet::getDistanceFromTheEarth))
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("Top 10 habitable planets:");
        top10Planets.forEach(planet -> System.out.println(planet.toString()));

//        Planet that is almost at the same distance from its closest star as the Earth from the Sun that is 0.00001581 light years
        Optional<Planet> planetAlmostSameDistanceStar = planets.stream()
                .min(Comparator.comparing(planet -> Math.abs(planet.getDistanceToClosestStar() - 0.00001581)));
        if(planetAlmostSameDistanceStar.isPresent()){
            System.out.println("Planet that is almost at the same distance from its closest star as the Earth from the Sun: "
                    + planetAlmostSameDistanceStar);
        }else{
            System.out.println("is Not Present");
        }
        System.out.println("Planet that is almost at the same distance from its closest star as the Earth from the Sun: "
                + planetAlmostSameDistanceStar);
    }
}
