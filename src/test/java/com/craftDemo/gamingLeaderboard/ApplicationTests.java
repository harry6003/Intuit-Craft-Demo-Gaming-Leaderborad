package com.craftDemo.gamingLeaderboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private Application application; // Assuming Application is the main class of your Spring Boot application

	@Test
	void contextLoads() {
		assertNotNull(application, "The Spring Boot application context should not be null ");
	}
}
