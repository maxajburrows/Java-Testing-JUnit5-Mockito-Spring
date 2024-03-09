package com.appsdeveloperblog.tutorials.junit.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UsersRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UsersRepository usersRepository;
    UserEntity user;
    @BeforeEach
    void setUp(){
        user = new UserEntity();
        user.setFirstName("Max");
        user.setLastName("Burrows");
        user.setEmail("test@test.com");
        user.setUserId(UUID.randomUUID().toString());
        user.setEncryptedPassword("123456789");
        testEntityManager.persistAndFlush(user);
    }

    @Test
    void testFindByEmail_whenGivenCorrectEmail_returnsUserEntity() {
        // Act
        UserEntity storedUser = usersRepository.findByEmail(user.getEmail());

        // Assert
        assertEquals(user.getEmail(), storedUser.getEmail(),
                "Returned email address does not match expected value");
    }

    @Test
    void testFindByUserId_whenGivenCorrectId_returnsUserEntity() {
        // Act
        UserEntity storedUser = usersRepository.findByUserId(user.getUserId());

        // Assert
        assertEquals(user.getUserId(), storedUser.getUserId(),
                "Returned userID does not match expected value");
    }
}
