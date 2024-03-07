package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
properties = {"server.port=8081","hostname=192.168.0.2"})
public class UsersControllerIntegrationTest {

    @Test
    void contextLoads() {

    }
}
