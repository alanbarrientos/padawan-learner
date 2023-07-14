package com.alan.entity;

import java.util.List;

public class User extends Entity {
    private String username;
    private int age;
    private Gender gender;
    private List<Address> addresses;
    private List<String> email;
    private List<Integer> activeBorrowBooks;
    private boolean itsAcceptedEUGDPR;

    public User(String username, int age, Gender gender, List<Address> addresses, List<String> email, boolean itsAcceptedEUGDPR){
        super();
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.addresses = addresses;
        this.email = email;
        this.itsAcceptedEUGDPR = itsAcceptedEUGDPR;
    }

    public List<Integer> getActiveBorrowBooks() {
        return activeBorrowBooks;
    }

    public void setActiveBorrowBooks(List<Integer> activeBorrowBooks) {
        this.activeBorrowBooks = activeBorrowBooks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public boolean isItsAcceptedEUGDPR() {
        return itsAcceptedEUGDPR;
    }

}
