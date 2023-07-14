package com.alan.controller;

import com.alan.entity.Address;
import com.alan.entity.Gender;
import com.alan.entity.User;
import com.alan.repository.CityRepository;
import com.alan.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UserControllerTest {
    User userDto;
    UserController userController;
    UserRepository userRepository = UserRepository.getInstance();

    @BeforeEach
    public void initializateUserController() throws ParseException {
        List<String> emails = new ArrayList<>();
        emails.add("fernandoalonso@gmail.com");
        List<Address> addresses = new ArrayList<>();
        Address address = new Address(CityRepository.getInstance().getByName("Asuncion"),
                "Mariscal Lopez casi Gral. Santos", 5432);
        addresses.add(address);

        userDto = new User(
                "Fernando Alonso",
                33,
                Gender.MALE,
                addresses,
                emails,
                true
        );
        userController = new UserController();
    }
    @Test
    public void shouldCreateUser(){
        User userCreated = userController.createUser(userDto, true).getBody();
        Assertions.assertTrue(userController.getUsers().getBody().contains(userCreated));
    }

    @Test
    public void shouldDeleteUser(){
        User userCreated = userController.createUser(userDto, true).getBody();
        Assertions.assertTrue(userController.getUsers().getBody().contains(userCreated));
        userController.deleteUser(userCreated);
        Assertions.assertFalse(userController.getUsers().getBody().contains(userCreated));
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToDeleteThatDontExist(){
        Assertions.assertFalse(userController.deleteUser(userDto).isSuccessful());
    }


    @Test
    public void shouldModifyUser(){
        List<String> emails = new ArrayList<>();
        emails.add("alonsofernando@gmail.com");
        User userCreated = userController.createUser(userDto, true).getBody();
        Assertions.assertTrue(userController.getUsers().getBody().contains(userCreated));
        userCreated.setEmail(emails);
        userController.modifyUser(userCreated.getId(), userCreated);
        Assertions.assertEquals(
                userCreated,
                userRepository.getById(userCreated.getId()).orElse(userDto)
        );
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToModifyUserThatDontExist(){
        List<String> emails = new ArrayList<>();
        emails.add("alonsofernando@gmail.com");
        User userCreated = userController.createUser(userDto, true).getBody();
        Assertions.assertTrue(userController.getUsers().getBody().contains(userCreated));
        userCreated.setEmail(emails);
        userController.deleteUser(userCreated);
        Assertions.assertFalse(userController.modifyUser(userCreated.getId(), userCreated).isSuccessful());
    }
}
