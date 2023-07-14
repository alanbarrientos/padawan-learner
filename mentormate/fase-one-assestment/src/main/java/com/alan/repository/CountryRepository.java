package com.alan.repository;

import com.alan.entity.Country;

import java.util.Optional;

public class CountryRepository extends Repository<Country> {
    private static final CountryRepository instance = new CountryRepository();

    private CountryRepository() {
        Country paraguay = new Country("Paraguay");
        Country bulgaria = new Country("Bulgaria");
        Country unitedEstates = new Country("United Estates");
        entities.add(paraguay);
        entities.add(bulgaria);
        entities.add(unitedEstates);
    }
    public static CountryRepository getInstance(){
        return instance;
    }

    public Optional<Country> getByName(String name){
        return entities.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name.toUpperCase()))
                .findFirst();
    }
}
