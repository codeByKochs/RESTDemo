package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.RestDemoApplication;
import com.codeByKochs.RestDemo.common.Address;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Adapter for database in .json format, implements IDataSourceAdapter
 */

public class JsonDataSourceAdapter implements IDataSourceAdapter {

    private static JsonDataSourceAdapter instance;
    private List<Address> addresses;
    private String pathToDatabase;

    public JsonDataSourceAdapter(String pathToDatabase){
        this.pathToDatabase = pathToDatabase;
        this.addresses = new ArrayList<>();
        loadDataBaseFromFile();
    }

//    TODO handle file not found exception somehow (maybe generate file)
//    loads database from .json file
    private void loadDataBaseFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

//        try to use path from application.properties
        try {
            File jsonFile = new File(pathToDatabase);
            addresses = objectMapper.readValue(jsonFile, new TypeReference<List<Address>>() {
            });
        } catch (Exception ex) {
//            try to find db.json file using search in runtime environment
            try {
                File jsonFile = new File(RestDemoApplication.class.getClassLoader().getResource("db.json").getFile());
                addresses = objectMapper.readValue(jsonFile, new TypeReference<List<Address>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    saves database to .json file
    private void saveDataBaseToFile(){
        ObjectMapper objectMapper = new ObjectMapper();
//        try to use path from application.properties
        try {
            File jsonFile = new File(pathToDatabase);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, addresses);
        } catch (Exception ex) {
//            try to find db.json file using search in runtime environment
            try {
                File jsonFile = new File(RestDemoApplication.class.getClassLoader().getResource("db.json").getFile());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

//    implements method from IDataSourceAdapter interface
    public List<Address> getAddresses() { return addresses; }

//    implements method from IDataSourceAdapter interface
    public Address addAddress(Address newAddress){
        newAddress.setId(Address.generateUUID());
        if (Address.isValidAddress(newAddress)) {
            addresses.add(newAddress);
            saveDataBaseToFile();
            return newAddress;
        }
        return null;
    }

//    implements method from IDataSourceAdapter interface
    public Address updateAddress(UUID id, Address updatedAddress){
        if (Address.isValidAddress(updatedAddress)){
            addresses.removeIf(address -> address.getId().equals(id));
            addresses.add(updatedAddress);
            saveDataBaseToFile();
            return updatedAddress;
        }
        return null;
    }

//    implements method from IDataSourceAdapter interface
    public void deleteAddress(UUID id){
        addresses.removeIf(address -> address.getId().equals(id));
        saveDataBaseToFile();
    }
}
