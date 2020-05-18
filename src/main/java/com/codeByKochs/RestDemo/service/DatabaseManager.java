package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.beans.dbConfigBean;
import com.codeByKochs.RestDemo.common.Address;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Database manager used to manage database (.json file)
 * uses dbConfigBean to load path to current database
 */

@Component
public class DatabaseManager {

    private static DatabaseManager instance;
    private List<Address> addresses;

    @Autowired
    private dbConfigBean dbConfigBean;

    public static DatabaseManager getInstance(){
        if (DatabaseManager.instance == null){
            DatabaseManager.instance = new DatabaseManager();
        }
        return DatabaseManager.instance;
    }

//    set private to avoid multiple instances (singleton pattern)
    private DatabaseManager(){
        this.addresses = new ArrayList<>();
        loadDataBase();
    }

    public static Boolean isValidAddress(Address address){
//        TODO check if id is unique
        return address != null && address.getName() != null && address.getCity() != null && address.getStreet() != null && address.getZipcode() != null;
    }

    private Integer generateId(){
//        this is not a recommended way to implement id generation, but it works for this demo purpose
        return (int) (Math.random() * 9999);
    }


//    TODO handle file not found exception somehow (maybe generate file)
    private void loadDataBase(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

//            try block is only used for development. Relative path to database is given in string
//            (File jsonFile = new File(dbConfigBean.getDatabasePath()) does not work here)
        try {
            String jsonFilePath = "./src/main/resources/database/db.json";
            File jsonFile = new File(jsonFilePath);
            addresses = objectMapper.readValue(jsonFile, new TypeReference<List<Address>>() {});
            System.out.println("database loaded from project resources");

        } catch (Exception ex) {
//            try block executed in packed .jar
            try {
                File jsonFile = new File(dbConfigBean.getDatabasePath());
                addresses = objectMapper.readValue(jsonFile, new TypeReference<List<Address>>() {});
                System.out.println("database loaded from external resource");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveDataBase(){
        ObjectMapper objectMapper = new ObjectMapper();
//        try block is only used for development. Relative path to database is given in string
//        (File jsonFile = new File(dbConfigBean.getDatabasePath()) does not work here)
        try {
            String jsonFilePath = "./src/main/resources/database/db.json";
            File jsonFile = new File(jsonFilePath);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, addresses);
            System.out.println("Writing data to file in resources");
        } catch (Exception e) {

//          try block executed in packed .jar
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dbConfigBean.getDatabasePath()), addresses);
                System.out.println("Writing data to external file");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Address> getAddresses() { return addresses; }


    public Address addAddress(Address newAddress){
        //TODO handle update request (if street, city and zipcode are the same → update name)
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
