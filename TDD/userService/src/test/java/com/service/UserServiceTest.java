package com.service;

import com.model.User;
import com.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("Empty first name causes correct exception")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
        // Arrange
        UserService userService = new UserServiceImpl();
        String firstName = "";
        String lastName = "Kargopolov";
        String email = "test@test.com";
        String password = "12345678";
        String repeatedPassword = "12345678";
        String expectedExceptionMessage = "User's first name is empty";

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatedPassword);
        }, "Empty first name should have caused an Illegal Argument Exception");

        // Assert
        assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception error message is not correct");
    }
}
