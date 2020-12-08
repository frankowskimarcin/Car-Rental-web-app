package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Integer id;

    private String brand;

    private String model;

    private String registrationPlate;

    private boolean rentStatus;

    public Car() {
    }
}
