package com.service;

import com.model.User;

public interface UserService {

    User createUser(String firstName,
                    String lastName,
                    String email,
                    String password,
                    String repeatedPassword);
}
