package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public List<Car> listAllCars(){
        return carService.carList();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable int id){
        try {
            Car car = carService.carGet(id);
            return new ResponseEntity<Car>(car, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        carService.carDelete(id);
    }

    @PostMapping("/cars")
    public void addCar(@RequestBody Car car){
        carService.carSave(car);
    }


    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@RequestBody Car car, @PathVariable Integer id){
        try {
            Car myCar = carService.carGet(id);
            car.setId(id);
            carService.carSave(car);
            return new ResponseEntity<Car>(car, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
        }
    }


}
