package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    public Customer customerGet(Integer id){
        return customerRepository.findById(id).get();
    }

    public void customerSave(Customer customer){
        customerRepository.save(customer);
    }

    public void customerDelete(Integer id){
        customerRepository.deleteById(id);
    }
}
