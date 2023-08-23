package com.smartcode.web;

import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryImpl;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.service.user.impl.UserServiceImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        UserRepository userRepository = new UserRepositoryImpl();
//
//        User user = new User(null, "Mark", "Setrakyan", null, "mark123", 20, "pass", new BigDecimal("1000.00"));
//        User user2 = new User(null, "Hakob", "Dyumejyan", null, "hakob123", 20, "pass", new BigDecimal("1000.00"));
//
//
//        userRepository.create(user);
//        userRepository.create(user2);

        User mark = userRepository.getById(1);
        User hakob = userRepository.getById(2);

        UserService userService = new UserServiceImpl(userRepository);


//        userService.transfer(mark, hakob, new BigDecimal(200));


    }

}