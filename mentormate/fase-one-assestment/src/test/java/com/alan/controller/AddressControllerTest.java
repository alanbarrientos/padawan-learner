package com.alan.controller;

import com.alan.entity.Address;
import com.alan.repository.AddressRepository;
import com.alan.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressControllerTest {
    Address addressDto;
    AddressController addressController;
    AddressRepository addressRepository = AddressRepository.getInstance();
    CityRepository cityRepository = CityRepository.getInstance();
    @BeforeEach
    public void initializateAddressController(){
        addressDto = new Address(cityRepository.getByName("Asuncion"),"Gral Santos casi Proceres de Mayo", 8123);
        addressController = new AddressController();
    }
    @Test
    public void shouldCreateAddress(){
        Address addressCreated = addressController.createAddress(addressDto).getBody();

        Assertions.assertTrue(addressController.getAddress().getBody().contains(addressCreated));
    }


    @Test
    public void shouldDeleteAddress(){
        Address addressCreated = addressController.createAddress(addressDto).getBody();

        Assertions.assertTrue(addressController.getAddress().getBody().contains(addressCreated));
        addressController.deleteAddress(addressCreated);
        Assertions.assertFalse(addressController.getAddress().getBody().contains(addressCreated));
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToDeleteThatDontExist(){
        Assertions.assertFalse(addressController.deleteAddress(addressDto).isSuccessful());
    }


    @Test
    public void shouldModifyAddress(){
        Address addressCreated = addressController.createAddress(addressDto).getBody();
        Assertions.assertTrue(addressController.getAddress().getBody().contains(addressCreated));
        addressCreated.setName("AnotherPlace");
        addressController.modifyAddress(addressCreated.getId(), addressCreated);
        Assertions.assertEquals("AnotherPlace",
                addressRepository.getById(addressCreated.getId()).orElse( new Address(cityRepository.getByName("Asuncion"),"Not Found", 0000)).getName()
        );
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToModifyAddressThatDontExist(){
        Address addressCreated = addressController.createAddress(addressDto).getBody();
        Assertions.assertTrue(addressController.getAddress().getBody().contains(addressCreated));
        addressCreated.setName("AnotherPlace");
        addressController.deleteAddress(addressCreated);
        Assertions.assertFalse(addressController.modifyAddress(addressCreated.getId(), addressCreated).isSuccessful());
    }
}
