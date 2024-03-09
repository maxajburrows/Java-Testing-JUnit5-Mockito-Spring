package com.appsdeveloperblog.tutorials.junit.io;

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

    @Test
    void testFindByEmail_whenGivenCorrectEmail_returnsUserEntity() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setFirstName("Max");
        user.setLastName("Burrows");
        user.setEmail("test@test.com");
        user.setUserId(UUID.randomUUID().toString());
        user.setEncryptedPassword("123456789");
        testEntityManager.persistAndFlush(user);

        // Act
        UserEntity storedUser = usersRepository.findByEmail(user.getEmail());

        // Assert
        assertEquals("test@test.com", storedUser.getEmail(),
                "Returned email address does not match expected value");
    }
}
