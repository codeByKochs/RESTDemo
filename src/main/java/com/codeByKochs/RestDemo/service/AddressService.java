package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.beans.DBConfiguration;
import com.codeByKochs.RestDemo.common.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST-controller used to answer GET/POST/UPDATE/DELETE calls to database
 *  uses DataSourceManager to realize database changes
 * without further configuration {baseURL} is http://localhost/8080
 * If a misconfigured call is made to any method, framework rejects the call with corresponding Http-Status-Code
 */

@RestController
public class AddressService {

    private DataSourceManager dataSourceManager;

    public AddressService(DBConfiguration dbConfiguration){
        this.dataSourceManager = new DataSourceManager(dbConfiguration);
    }

//    answers a GET request to {baseURL}/api/addresses with a jsonArray of the stored addresses of the JsonDataSourceManager
    @GetMapping("/api/addresses")
    public List<Address> getAddresses(){
        return dataSourceManager.getAddresses();
    }

//    answers a POST request to {baseURL}/api/addresses with an address object, containing the given information in json format
    @PostMapping(value="/api/addresses", consumes = "application/json")
    public Address postAddress(@RequestBody Address newAddress){
        return dataSourceManager.addAddress(newAddress);
    }

//    answers a DELETE request to {baseURL}/api/addresses/{id} with a response message
    @DeleteMapping("/api/addresses/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable UUID id){
        dataSourceManager.deleteAddress(id);
        return new ResponseEntity<>("Addresss has been deleted", HttpStatus.OK);
    }

//    answers a UPDATE request to {baseURL}/api/addresses/{id} with the updated object in json format
    @PutMapping(value="/api/addresses/{id}", consumes="application/json")
    public Address updateAddress(@PathVariable UUID id, @RequestBody Address updatedAddress){
        return dataSourceManager.updateAddress(id, updatedAddress);
    }
}
