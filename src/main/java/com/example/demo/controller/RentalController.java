package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.model.Customer;
import com.example.demo.model.Rental;
import com.example.demo.service.CarService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/rentals")
    public List<Rental> listAllRentals(){
        return rentalService.rentalList();
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRental(@PathVariable int id){
        try {
            Rental rental = rentalService.rentalGet(id);
            return new ResponseEntity<Rental>(rental, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Rental>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/rentals/{id}")
    public void deleteRental(@PathVariable int id) {
        Rental rental = rentalService.rentalGet(id);
        Integer carId = rental.getCar().getId();
        rentalService.rentalDelete(id);
        returnCar(carId);
    }

    @GetMapping("/rentals/{carId}/{customerId}")
    public void addRental(@PathVariable Integer carId, @PathVariable Integer customerId){

        try{
            Car myCar = carService.carGet(carId);
            Customer myCustomer = customerService.customerGet(customerId);
            if (!myCar.isRentStatus()){
                myCar.setRentStatus(true);
                carService.carSave(myCar);
                Rental rental = new Rental(myCar, myCustomer);
                rentalService.rentalSave(rental);
            }else{
                throw new RuntimeException("The car with id: " + carId + " is already rented!");
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


    @PutMapping("/rentals/{id}")
    public void updateRentalCar(@RequestBody Car car, @PathVariable Integer id){

        try{
            Rental myRental = rentalService.rentalGet(id);
            Integer idCar = car.getId();
            Optional<Car> optionalCar = Optional.ofNullable(carService.carGet(idCar));
            Integer idCustomer = myRental.getCustomer().getId();
            Optional<Customer> optionalCustomer = Optional.ofNullable(customerService.customerGet(idCustomer));
            deleteRental(id);
            addRental(idCar, idCustomer);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


    public void returnCar(Integer id){
        try{
            Car myCar = carService.carGet(id);
            if (myCar.isRentStatus()){
                myCar.setRentStatus(false);
                carService.carSave(myCar);
            } else{
                throw new RuntimeException("The car with id: " + id + " is already returned!");
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}
