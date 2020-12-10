package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.model.Customer;
import com.example.demo.model.Rental;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.RentalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RentalServiceIntegrationTest {

    @TestConfiguration
    static class RentalServiceTestContextConfiguration{
        @Bean
        public RentalService rentalService(){
            return new RentalService();
        }
    }

    @Autowired
    private RentalService rentalService;

    @MockBean
    private RentalRepository rentalRepository;

    @Before
    public void setUp(){
        Rental rental = new Rental(213);
        Car car = new Car();
        Customer customer = new Customer();

        Mockito.when(rentalRepository.findById(rental.getId())).thenReturn(java.util.Optional.of(rental));
    }

    @Test
    public void testCarServiceFoundCarById(){
        Integer id = 213;
        Rental foundRental = rentalService.rentalGet(id);

        assertThat(foundRental.getId()).isEqualTo(id);
    }
}