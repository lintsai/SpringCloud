package com.example.service;

import com.example.springboot.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
@ContextConfiguration
@TestPropertySource(locations = "classpath:application-Junit.properties")
class BasicServiceTest {
    @Value("${test.version}")
    private String version;
    @Autowired
    BasicService basicService;
    @Test
    void version() {
        assertEquals("/version", basicService.version(), version);
    }
}