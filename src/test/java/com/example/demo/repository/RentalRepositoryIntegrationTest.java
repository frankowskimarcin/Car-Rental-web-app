package com.example.demo.repository;

import com.example.demo.model.Car;
import com.example.demo.model.Customer;
import com.example.demo.model.Rental;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RentalRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RentalRepository rentalRepository;

    @Test
    public void testFindById() throws Exception{
        Car car = new Car();
        Customer customer = new Customer();
        Rental rental = new Rental(car, customer);
        entityManager.persist(rental);

        Optional<Rental> foundRental = rentalRepository.findById(rental.getId());

        assertThat(foundRental.get().getId()).isEqualTo(rental.getId());

    }
}