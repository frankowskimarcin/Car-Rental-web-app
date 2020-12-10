package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rental implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumns({
            @JoinColumn(name="car_id", referencedColumnName="id"),
            @JoinColumn(name="car_registration_plate", referencedColumnName="registrationPlate")
    })
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumns({
            @JoinColumn(name="customer_id", referencedColumnName="id"),
            @JoinColumn(name="customer_surname", referencedColumnName="surname")
    })
    private Customer customer;

    public Rental() {
    }

    public Rental(Integer id) {
        this.id = id;
    }

    public Rental(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }


    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
