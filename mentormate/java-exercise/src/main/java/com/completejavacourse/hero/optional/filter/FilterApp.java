package com.completejavacourse.hero.optional.filter;

import com.completejavacourse.hero.optional.ifpresent.User;
import com.completejavacourse.hero.optional.ifpresent.UserRepository;

import java.util.ArrayList;

public class FilterApp {

    public static void main(String[] args) {
        ArrayList<User> users = UserRepository.getUsers();
        users.forEach(EmailServiceFilter::sendPromotion);
    }
}
