package com.alan.entity;

public class Address extends Entity {
    private City city;
    private String name;
    private int homeNumber;

    public Address(City city, String name, int homeNumber) {
        super();
        this.city = city;
        this.name = name;
        this.homeNumber = homeNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }
}
