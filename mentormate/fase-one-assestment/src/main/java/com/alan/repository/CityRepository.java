package com.alan.repository;

import com.alan.entity.City;

import java.util.stream.Collectors;

public class CityRepository extends Repository<City>{
    private static final CityRepository instance = new CityRepository();


    private CityRepository() {
        CountryRepository countryRepository = CountryRepository.getInstance();
        City asuncion = new City(countryRepository.getByName("Paraguay").orElseThrow(RuntimeException::new), "Asuncion");
        City caacupe = new City(countryRepository.getByName("Paraguay").orElseThrow(RuntimeException::new), "Caacupe");
        City sofia = new City(countryRepository.getByName("Bulgaria").orElseThrow(RuntimeException::new), "Sofia");
        entities.add(asuncion);
        entities.add(caacupe);
        entities.add(sofia);
    }
    public static CityRepository getInstance(){
        return instance;
    }

    public City getByName(String name){
        return entities.stream()
                .filter(c -> c.getName().toUpperCase().equalsIgnoreCase(name))
                .collect(Collectors.toList()).get(0);
    }
}
