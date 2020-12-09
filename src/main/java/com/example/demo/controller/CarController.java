package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public List<Car> listAllCars(){
        return carRepository.findAll();
    }

    @GetMapping("/cars/{id}")
    public Car getCar(@PathVariable int id) throws NotFoundException {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) throw new NotFoundException("id: " + id + " not found");
        return car.get();
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        carRepository.deleteById(id);
    }

    @PostMapping("/cars")
    public void addCar(@RequestBody Car car){
        carRepository.save(car);
    }

    @PutMapping("/cars/{id}")
    public void updateCar(@RequestBody Car car, @PathVariable Integer id) throws NotFoundException {
        Optional<Car> myCar = carRepository.findById(id);
        if (myCar.isEmpty()) throw new NotFoundException("id: " + id + " not found");
        car.setId(id);
        carRepository.save(car);
    }

//    @PutMapping("/cars/rent/{id}")
//    public void rentCar(@PathVariable Integer id) throws NotFoundException {
//        Optional<Car> myCar = carRepository.findById(id);
//        if (myCar.isEmpty()) throw new NotFoundException("id: " + id + " not found");
//        myCar.
//    }
}
