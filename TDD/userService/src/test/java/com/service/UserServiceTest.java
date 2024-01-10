package com.service;

import com.model.User;
import com.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        // Arrange
        UserService userService = new UserServiceImpl();
        String firstName = "Sergey";
        String lastName = "Kargopolov";
        String email = "test@test.com";
        String password = "12345678";
        String repeatedPassword = "12345678";

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatedPassword);

        //Assert
        assertNotNull(user, "The createUser() method should not have returned null");
    }
}
