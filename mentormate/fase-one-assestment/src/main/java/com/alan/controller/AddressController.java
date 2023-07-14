package com.alan.controller;

import com.alan.Constans;
import com.alan.Response;
import com.alan.entity.Address;
import com.alan.repository.AddressRepository;
import com.alan.repository.CityRepository;

import java.util.List;

public class AddressController {
    private AddressRepository addressRepository = AddressRepository.getInstance();
    private CityRepository cityRepository = CityRepository.getInstance();
    public Response<Address> createAddress(Address address){
        try{
            Address addressToSave = new Address(address.getCity(), address.getName(), address.getHomeNumber());
            addressRepository.add(addressToSave);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, addressToSave);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response modifyAddress(int id, Address address){
        try{
            addressRepository.modifyById(address.getId(), address);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response deleteAddress(Address address){
        try{
            addressRepository.deleteById(address.getId());
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }
    public Response<List<Address>> getAddress(){
        return new Response<>(true ,Constans.MESSAGE_OK, Constans.CODE_OK, addressRepository.getEntities());
    }
}
