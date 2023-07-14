package com.completejavacourse.expert.finalpractice;

import java.util.Objects;

public class  Planet {
    private String name;
    private int size;
    private double distanceToClosestStar;
    private int avgTemperature;
    private String type;
    private double distanceFromTheEarth;
    public Planet(String name, int size, double closestStarDistance, int temperature, String type, double distanceFromEarth) {
        this.name = name;
        this.size = size;
        this.distanceToClosestStar = closestStarDistance;
        this.avgTemperature = temperature;
        this.type = type;
        this.distanceFromTheEarth = distanceFromEarth;
    }
    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public double getDistanceToClosestStar() {
        return distanceToClosestStar;
    }
    public int getAvgTemperature() {
        return avgTemperature;
    }
    public String getType() {
        return type;
    }
    public double getDistanceFromTheEarth() {
        return distanceFromTheEarth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return size == planet.size && Double.compare(planet.distanceToClosestStar, distanceToClosestStar) == 0 && avgTemperature == planet.avgTemperature && Double.compare(planet.distanceFromTheEarth, distanceFromTheEarth) == 0 && Objects.equals(name, planet.name) && Objects.equals(type, planet.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, distanceToClosestStar, avgTemperature, type, distanceFromTheEarth);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", distanceToClosestStar=" + distanceToClosestStar +
                ", avgTemperature=" + avgTemperature +
                ", type='" + type + '\'' +
                ", distanceFromTheEarth=" + distanceFromTheEarth +
                '}';
    }
}
