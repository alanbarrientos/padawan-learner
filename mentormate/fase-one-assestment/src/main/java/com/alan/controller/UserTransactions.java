package com.alan.controller;

import com.alan.entities.Address;
import com.alan.entities.Gender;
import com.alan.entities.User;

import java.util.List;
import java.util.Scanner;

public class UserTransactions {
    Scanner scanner;
    public UserTransactions(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addUser(List<User> users, String name, int age, Gender gender, List<Address> addresses, List<String> emails) throws Exception {
//        System.out.println("Ready to create an User \n" +
//                            "Insert The name:");
//        String name = scanner.nextLine();
//        System.out.println("Insert the age:");
//        int age = scanner.nextInt();
//        System.out.println("Insert M If is Male or F for Female:");
//        String genderEntry = scanner.nextLine();
//        Gender gender;
//        if("F".equals(genderEntry.toUpperCase())){
//            gender = Gender.FEMALE;
//        }
//        if("M".equals(genderEntry.toUpperCase())){
//            gender = Gender.MALE;
//        }
//        System.out.println("Add you Address");
//        String addressName = scanner.nextLine();
//
        User user = new User(name, age, gender, addresses,emails,AcceptERUGDPR());
        users.add(user);
    }

    private boolean AcceptERUGDPR() {
        System.out.println("To Accept Data Manage Condition enter: yes other ways no");
        if("yes".equals(scanner.nextLine().toLowerCase())){
            return true;
        }
        return false;
    }


}
