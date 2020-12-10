package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.demo.model.Car;
import com.example.demo.model.Customer;
import com.example.demo.model.Rental;
import com.example.demo.service.CarService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.RentalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class RentalControllerTest extends AbstractTest{

    @Autowired
    CarService carService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RentalService rentalService;

    @Override
    @Before
    public void setMvc() {
        super.setMvc();
    }

    @Test
    public void testGetRentalList() throws Exception{
        String uri = "/rentals";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Rental[] rentals = super.mapFromJson(content, Rental[].class);
        assertTrue(rentals.length>0);
    }

    @Test
    public void testCreateRental() throws Exception {
        String uri = "/rentals/1002/102";
        Car myCar = new Car(1002, "Skoda", "Superb", "WE123QW", false);
        Customer myCustomer = new Customer(102, "Alfie", "Solomons");
        Rental rental = new Rental(myCar, myCustomer);

        String inputJson = super.mapToJson(rental);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(405, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }

    @Test
    public void testUpdateRental() throws Exception{
        String uri = "/rentals/1001";
        Car myCar = new Car(1001, "Toyota", "Camry", "WE9BRB9", false);
        Car myCar2 = new Car(1002, "Skoda", "Superb", "WE123QW", false);
        Customer myCustomer = new Customer(101, "Tommy", "Shelby");
        Rental rental = new Rental(myCar, myCustomer);
        rental.setCar(myCar2);

        String inputJson = super.mapToJson(rental);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testDeleteRental() throws Exception{
        String uri = "/rentals/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}