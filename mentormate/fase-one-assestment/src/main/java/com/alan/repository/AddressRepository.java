package com.alan.repository;

import com.alan.entity.Address;

import java.util.stream.Collectors;

public class AddressRepository extends Repository<Address>{
    private static final AddressRepository instance = new AddressRepository();

    private AddressRepository() {
        CityRepository cityRepository = CityRepository.getInstance();
        Address italia1234 = new Address(CityRepository.getInstance().getByName("Asuncion"),
                "Italia casi Proceres de Mayo", 1234);
        Address generalRoa4321 = new Address(CityRepository.getInstance().getByName("Caacupe"),
                "General Roa casi Ira Proyectada", 4321);
        Address zorrilla3214 = new Address(CityRepository.getInstance().getByName("Asuncion"),
                "Zorrilla casi Campo Via", 3214);
        entities.add(italia1234);
        entities.add(generalRoa4321);
        entities.add(zorrilla3214);
    }

    public static AddressRepository getInstance(){
        return instance;
    }

    public Address getByName(String name){
        return entities.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList()).get(0);
    }



}
