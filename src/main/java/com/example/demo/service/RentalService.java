package com.example.demo.service;

import com.example.demo.model.Rental;
import com.example.demo.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> rentalList(){
        return rentalRepository.findAll();
    }

    public Rental rentalGet(Integer id){
        return rentalRepository.findById(id).get();
    }

    public void rentalSave(Rental rental){
        rentalRepository.save(rental);
    }

    public void rentalDelete(Integer id){
        rentalRepository.deleteById(id);
    }
}
