package com.alan.entity;

public class City extends Entity {
    private Country country;
    private String name;


    public City(Country country, String name) {
        super();
        this.country = country;
        this.name = name;
    }


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
