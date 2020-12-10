package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String brand;

    private String model;

    private String registrationPlate;

    private boolean rentStatus;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(Integer id, String brand, String model, String registrationPlate, boolean rentStatus) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.registrationPlate = registrationPlate;
        this.rentStatus = rentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public boolean isRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
    }
}
