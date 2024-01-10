package com.service;

import com.model.User;
import com.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        // Arrange
        UserService userService = new UserServiceImpl();
        String firstName = "Sergey";
        String lastName = "Kargopolov";
        String email = "test@test.com";
        String password = "12345678";
        String repeatedPassword = "12345678";

        // Act
        User user = userService.createUser(firstName, lastName, email, password, repeatedPassword);

        // Assert
        assertNotNull(user, "The createUser() method should not have returned null");
        assertEquals(firstName, user.getFirstName(), "User's first name isn't correct");
        assertEquals(lastName, user.getLastName(), "User's last name isn't correct");
        assertEquals(email, user.getEmail(), "User's email isn't correct");
        assertNotNull(user.getId(), "User id is missing");
    }
}
