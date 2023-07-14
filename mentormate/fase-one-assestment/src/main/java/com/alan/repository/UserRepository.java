package com.alan.repository;

import com.alan.entity.Address;
import com.alan.entity.Gender;
import com.alan.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository extends Repository<User>{
    private static final UserRepository instance = new UserRepository();

    private UserRepository() {
        AddressRepository addressRepository = AddressRepository.getInstance();

        User alan;
        User julia;
        List<Address> addressesAlan = new ArrayList<>();
        addressesAlan.add(addressRepository.getByName("Italia casi Proceres de Mayo"));

        List<Address> addressesJulia = new ArrayList<>();
        addressesJulia.add(addressRepository.getByName("General Roa casi Ira Proyectada"));
        addressesJulia.add(addressRepository.getByName("Zorrilla casi Campo Via"));

        List<String> emailsAlan = new ArrayList<>();
        emailsAlan.add("alanbarrientosdh@gmail.com");
        List<String> emailsJulia = new ArrayList<>();
        emailsJulia.add("juliaperes@gmail.com");
        emailsJulia.add("peresjulia@gmail.com");
        boolean alanAcceptEUGDPR = true;
        boolean juliaAcceptEUGDPR = true;


        try {
            alan = new User("Alan", 19, Gender.MALE, addressesAlan, emailsAlan, alanAcceptEUGDPR);
            julia = new User("Julia", 23, Gender.FEMALE, addressesJulia, emailsJulia, juliaAcceptEUGDPR);
            entities.add(alan);
            entities.add(julia);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static UserRepository getInstance(){
        return instance;
    }

    public User getByName(String name){
        return entities.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(name))
                .collect(Collectors.toList()).get(0);
    }
}
