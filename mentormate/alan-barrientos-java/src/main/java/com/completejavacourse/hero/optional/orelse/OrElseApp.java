package com.completejavacourse.hero.optional.orelse;

import com.completejavacourse.hero.optional.ifpresent.User;
import com.completejavacourse.hero.optional.ifpresent.UserRepository;

import java.util.ArrayList;

public class OrElseApp {

    public static void main(String[] args) {
        ArrayList<User> users = UserRepository.getUsers();
        users.forEach(EmailServiceOrElse::sendPromotion);
    }
}
