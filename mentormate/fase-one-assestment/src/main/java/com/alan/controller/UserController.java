package com.alan.controller;

import com.alan.Constans;
import com.alan.Response;
import com.alan.entity.*;
import com.alan.repository.AddressRepository;
import com.alan.repository.CityRepository;
import com.alan.repository.CountryRepository;
import com.alan.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private UserRepository userRepository = UserRepository.getInstance();
    private AddressRepository addressRepository = AddressRepository.getInstance();
    private CountryRepository countryRepository = CountryRepository.getInstance();
    private CityRepository cityRepository = CityRepository.getInstance();
    public Response<User> createUser(User user, boolean acceptERUGDPR){
        if(!acceptERUGDPR){
            return new Response<>(false, Constans.MESSAGE_USER_NOT_ACCEPT_ERUGDPR, Constans.CODE_WRONG);
        }
        List<Address> addresses = new ArrayList<>();
        for (Address a: user.getAddresses()) {
            if(!cityRepository.have(a.getCity())){
                return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
            }
            if(!countryRepository.have(a.getCity().getCountry())){
                return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
            }
            Address addressToAdd = new Address(a.getCity(), a.getName(), a.getHomeNumber());
            addressRepository.add(addressToAdd);
            addresses.add(addressToAdd);
        }
        User userToSave = new User(user.getUsername(), user.getAge(),
                user.getGender(), addresses, user.getEmail(),true);
        userRepository.add(userToSave);
        return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, userToSave);
    }

    public Response modifyUser(int id, User user){
        try{
            for (Address a: user.getAddresses()) {
                if(!cityRepository.have(a.getCity())){
                    throw new RuntimeException();
                }
                if(!countryRepository.have(a.getCity().getCountry())){
                    throw new RuntimeException();
                }
            }
            userRepository.modifyById(id, user);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response deleteUser(User user){
        try{
            userRepository.deleteById(user.getId());
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response<List<User>> getUsers(){
        return new Response<>(true ,Constans.MESSAGE_OK, Constans.CODE_OK, userRepository.getEntities());
    }
}
