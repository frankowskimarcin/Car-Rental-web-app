package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> carList(){
        return carRepository.findAll();
    }

    public Car carGet(Integer id){
        return carRepository.findById(id).get();
    }

    public void carSave(Car car){
        carRepository.save(car);
    }

    public void carDelete(Integer id){
        carRepository.deleteById(id);
    }
}
