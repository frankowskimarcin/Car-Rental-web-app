package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CarServiceIntegrationTest {

    @TestConfiguration
    static class CarServiceTestContextConfiguration{
        @Bean
        public CarService carService(){
            return new CarService();
        }
    }

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Before
    public void setUp(){
        Car car = new Car(997);

        Mockito.when(carRepository.findById(car.getId())).thenReturn(java.util.Optional.of(car));
    }

    @Test
    public void testCarServiceFoundCarById(){
        Integer id = 997;
        Car foundCar = carService.carGet(id);

        assertThat(foundCar.getId()).isEqualTo(id);
    }
}