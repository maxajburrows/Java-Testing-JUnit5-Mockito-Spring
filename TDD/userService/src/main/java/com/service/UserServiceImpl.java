package com.service;

import com.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(String firstName,
                           String lastName,
                           String email,
                           String password,
                           String repeatedPassword) {

        return new User(firstName, lastName, email, UUID.randomUUID().toString());
    }
}
