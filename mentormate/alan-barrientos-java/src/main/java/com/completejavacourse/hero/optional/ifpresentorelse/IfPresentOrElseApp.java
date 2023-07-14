package com.completejavacourse.hero.optional.ifpresentorelse;

import com.completejavacourse.hero.optional.ifpresent.User;
import com.completejavacourse.hero.optional.ifpresent.UserRepository;

import java.util.ArrayList;

public class IfPresentOrElseApp {

    public static void main(String[] args) {
        ArrayList<User> users = UserRepository.getUsers();
        users.forEach(EmailService::sendPromotion);
    }

}
