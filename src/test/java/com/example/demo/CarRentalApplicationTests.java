package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.controller.RentalController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarRentalApplicationTests {

	@Autowired
	RentalController rentalController;

	@Test
	void contextLoads() {
	}

	@Test
	void contextLoadsController() throws Exception{
		assertThat(rentalController).isNotNull();
	}



}
