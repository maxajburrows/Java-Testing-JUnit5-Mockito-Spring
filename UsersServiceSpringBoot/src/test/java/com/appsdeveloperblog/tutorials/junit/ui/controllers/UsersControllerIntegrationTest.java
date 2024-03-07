package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "/application-test.properties",
//        properties = "server.port=8081")
//@TestPropertySource(locations = "/application-test.properties")
public class UsersControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("User can be created")
    void testCreateUser_whenValidDetailsProvided_returnsUserDetails() throws JSONException {
        // Arrange
        JSONObject userDetailsRequestJson = new JSONObject();
        userDetailsRequestJson.put("firstName", "Max");
        userDetailsRequestJson.put("lastName", "Burrows");
        userDetailsRequestJson.put("email", "test@test.com");
        userDetailsRequestJson.put("password", "12345678");
        userDetailsRequestJson.put("repeatPassword", "12345678");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(userDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<UserRest> createdUserDetailsEntity = testRestTemplate.postForEntity("/users",
                request,
                UserRest.class);
        UserRest createdUserDetails = createdUserDetailsEntity.getBody();

        // Assert
        assertEquals(HttpStatus.OK, createdUserDetailsEntity.getStatusCode());
        assertEquals(userDetailsRequestJson.getString("firstName"),
                createdUserDetails.getFirstName(),
                "Returned user's first name seems to be incorrect");
        assertEquals(userDetailsRequestJson.getString("lastName"),
                createdUserDetails.getLastName(),
                "Returned user's last name seems to be incorrect");
        assertEquals(userDetailsRequestJson.getString("email"),
                createdUserDetails.getEmail(),
                "Returned user's email seems to be incorrect");
        assertFalse(createdUserDetails.getUserId().trim().isEmpty(),
                "User id should not be empty");
    }

}
