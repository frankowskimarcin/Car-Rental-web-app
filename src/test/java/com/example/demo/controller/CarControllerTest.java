package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.model.Customer;
import com.example.demo.model.Rental;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CarControllerTest extends AbstractTest{

    @Override
    @Before
    public void setMvc() {
        super.setMvc();
    }

    @Test
    public void testGetCarList() throws Exception{
        String uri = "/cars";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Car[] cars = super.mapFromJson(content, Car[].class);
        assertTrue(cars.length>0);
    }

    @Test
    public void testCreateCar() throws Exception {
        String uri = "/cars";
        Car car = new Car(1004, "Skoda", "Superb", "WE123QW", false);

        String inputJson = super.mapToJson(car);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testUpdateCar() throws Exception {
        String uri = "/cars/1003";
        Car car = new Car();
        car.setBrand("Volkswagen");
        car.setModel("Passat");
        car.setRegistrationPlate("PZ10987A");
        String inputJson = super.mapToJson(car);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "{\"id\":1003,\"brand\":\"Volkswagen\",\"model\":\"Passat\",\"registrationPlate\":\"PZ10987A\",\"rentStatus\":false}");
    }
    @Test
    public void testDeleteCar() throws Exception {
        String uri = "/cars/1003";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }
}