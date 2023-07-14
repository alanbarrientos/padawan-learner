package com.completejavacourse.hero.optional.orelseget;

import com.completejavacourse.hero.optional.ifpresent.User;
import com.completejavacourse.hero.optional.ifpresent.UserRepository;

import java.util.ArrayList;

public class OrElseGetApp {

    public static void main(String[] args) {
        ArrayList<User> users = UserRepository.getUsers();
        users.forEach(EmailServiceOrElseGet::sendPromotion);
    }
}
