package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.common.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import java.util.List;

/**
 * REST-controller used to answer GET/POST/UPDATE/DELETE calls to database
 *  uses DatabaseManager Bean to realize database changes
 * without further configuration base path is http://localhost/8080
 */

@RestController
public class AddressService {

    @Autowired
    private DatabaseManager databaseManager;

    private ResponseStatusException exceptionHandling(HttpStatus httpStatus){
        return new ResponseStatusException(httpStatus);
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello World!";
    }

    @GetMapping("/api/addresses")
    public List<Address> getAddresses(){
        return databaseManager.getAddresses();
    }

    @PostMapping(value="/api/addresses", consumes = "application/json")
    public Address postAddress(@RequestBody Address newAddress){
        newAddress = databaseManager.addAddress(newAddress);
        return newAddress;
    }

    @DeleteMapping(value="/api/addresses/id")
    public void deleteAddress(){
//        TODO read id from path
//        TODO implement databaseManager handling deletion

    }
}
