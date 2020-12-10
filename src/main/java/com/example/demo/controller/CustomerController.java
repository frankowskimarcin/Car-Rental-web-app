package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> listAllCustomers(){
        return customerService.customerList();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
        try {
            Customer customer = customerService.customerGet(id);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.customerDelete(id);
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer){
        customerService.customerSave(customer);
    }


    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id){
        try {
            Customer myCustomer = customerService.customerGet(id);
            customer.setId(id);
            customerService.customerSave(customer);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }
}
