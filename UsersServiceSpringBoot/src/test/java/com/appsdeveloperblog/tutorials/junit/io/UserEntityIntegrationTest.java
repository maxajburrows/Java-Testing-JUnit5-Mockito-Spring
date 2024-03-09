package com.appsdeveloperblog.tutorials.junit.io;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.PersistenceException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserEntityIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;
    UserEntity userEntity;
    String userId;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userId = UUID.randomUUID().toString();
        userEntity.setUserId(userId);
        userEntity.setFirstName("Max");
        userEntity.setLastName("Burrows");
        userEntity.setEmail("test@test.com");
        userEntity.setEncryptedPassword("12345678");
    }

    @Test
    @Order(1)
    void testUserEntity_whenValidUserDetailsProvided_shouldReturnStoredUserDetails() {
        // Act
        UserEntity storedUserEntity = testEntityManager.persistAndFlush(userEntity);

        // Assert
        assertTrue(storedUserEntity.getId() > 0);
        assertEquals(userEntity.getUserId(), storedUserEntity.getUserId());
        assertEquals(userEntity.getFirstName(), storedUserEntity.getFirstName());
        assertEquals(userEntity.getLastName(), storedUserEntity.getLastName());
        assertEquals(userEntity.getEmail(), storedUserEntity.getEmail());
        assertEquals(userEntity.getEncryptedPassword(), storedUserEntity.getEncryptedPassword());
    }

    @Test
    void testUserEntity_whenFirstNameIsTooLong_shouldThrowException() {
        // Arrange
        userEntity.setFirstName("m".repeat(51));

        // Assert & Act
        assertThrows(PersistenceException.class, ()->{
            testEntityManager.persistAndFlush(userEntity);
        }, "Was expecting a PersistenceException to be thrown.");
    }

    @Test
    void testUserEntity_whenExistingUserIdProvided_shouldThrowException() {
        testEntityManager.persistAndFlush(userEntity);

        UserEntity duplicateEntity = new UserEntity();
        duplicateEntity.setUserId(userId);
        duplicateEntity.setFirstName("Amelia");
        duplicateEntity.setLastName("Burrows");
        duplicateEntity.setEmail("test2@test.com");
        duplicateEntity.setEncryptedPassword("1234");

        assertThrows(PersistenceException.class, ()->{
            testEntityManager.persistAndFlush(duplicateEntity);
        }, "Was expecting a PersistenceException to be thrown.");
    }
}