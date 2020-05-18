package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.beans.dbConfigBean;
import com.codeByKochs.RestDemo.common.Address;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Database manager used to manage database (in this case a .json file)
 * uses dbConfigBean to load path to current database
 */

@Component
public class DatabaseManager {

    private static DatabaseManager instance;
    private List<Address> addresses;
    private dbConfigBean dbConfigBean;

    public static DatabaseManager getInstance(){
        if (DatabaseManager.instance == null){
            DatabaseManager.instance = new DatabaseManager(new dbConfigBean());
        }
        return DatabaseManager.instance;
    }

//    set private to avoid multiple instances (singleton pattern)
    @Autowired
    private DatabaseManager(dbConfigBean dbConfigBean){
        this.dbConfigBean = dbConfigBean;
        this.addresses = new ArrayList<>();
        loadDataBase();
    }

    public static Boolean isValidAddress(Address address){
        return address != null && address.getName() != null && address.getCity() != null && address.getStreet() != null && address.getZipcode() != null;
    }

    private UUID generateId(){
        return UUID.randomUUID();
    }


//    TODO handle file not found exception somehow (maybe generate file)
    private void loadDataBase(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        try {
            File jsonFile = new File(dbConfigBean.getDatabasePath());
            addresses = objectMapper.readValue(jsonFile, new TypeReference<List<Address>>() {});
            System.out.println("database loaded");

        } catch (Exception ex) {
                ex.printStackTrace();
        }
    }

    private void saveDataBase(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File(dbConfigBean.getDatabasePath());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, addresses);
            System.out.println("writing data to database");
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public List<Address> getAddresses() { return addresses; }


    public Address addAddress(Address newAddress){
        //TODO handle update request (if street, city and zipCode are the same → update name)
        newAddress.setId(generateId());
        if (isValidAddress(newAddress)) {
            addresses.add(newAddress);
            saveDataBase();
            return newAddress;
        }
        return null;
    }

    public void updateAddress(Address newAddress){
//        for address in list, if id=newAddress.id → update data
    }

    public void deleteAddress(Integer id){
//        for address in list, if id = address.id → delete
    }
}
