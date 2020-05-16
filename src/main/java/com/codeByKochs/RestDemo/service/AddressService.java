package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.common.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.util.List;

@RestController
public class AddressService {

    private DatabaseManager databaseManager;

    public AddressService(){
        this.databaseManager = DatabaseManager.getInstance();
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello World!";
    }

    @GetMapping("/addresses")
    public List<Address> getAddresses(){
        return databaseManager.getAddresses();
    }

//    TODO post mapping (databaseManager.saveDatabase())
//    TODO update mapping (databaseManager.saveDatabase())
//    TODO delete (databaseManager.saveDatabase())
}
