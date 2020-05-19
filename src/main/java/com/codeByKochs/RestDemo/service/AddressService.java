package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.common.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * REST-controller used to answer GET/POST/UPDATE/DELETE calls to database
 *  uses DatabaseManager Bean to realize database changes
 * without further configuration base url is http://localhost/8080
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
        return databaseManager.addAddress(newAddress);
    }

    @DeleteMapping("/api/addresses/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable UUID id){
        databaseManager.deleteAddress(id);
        return new ResponseEntity<String>("Addresss has been deleted", HttpStatus.OK);
    }

    @PutMapping(value="/api/addresses/{id}", consumes="application/json")
    public Address updateAddress(@PathVariable UUID id, @RequestBody Address updatedAddress){
        return databaseManager.updateAddress(id, updatedAddress);
    }
}
