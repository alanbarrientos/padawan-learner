package com.completejavacourse.hero.optional.orelsethrow;

import com.completejavacourse.hero.optional.ifpresent.User;
import com.completejavacourse.hero.optional.ifpresent.UserRepository;

import java.util.ArrayList;

public class OrElseThrowApp {

    public static void main(String[] args) {
        ArrayList<User> users = UserRepository.getUsers();
        users.forEach(EmailServiceOrElseThrow::sendPromotion);
    }
}
