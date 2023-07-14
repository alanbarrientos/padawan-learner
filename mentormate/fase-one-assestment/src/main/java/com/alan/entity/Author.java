package com.alan.entity;

import java.util.Date;

public class Author extends Entity {
    private String name;
    private Country country;
    private Date dateOfBirth;
    private Date dateOfDeath;

    public Author(String name, Country country, Date dateOfBirth, Date dateOfDeath) {
        super();
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
}
