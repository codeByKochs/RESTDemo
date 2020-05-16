package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.common.Address;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class DatabaseManager {

    private static DatabaseManager instance;
    private List<Address> addresses;

    private DatabaseManager(){
        loadDataBase();
    }

    public static DatabaseManager getInstance(){
        if (DatabaseManager.instance == null){
            DatabaseManager.instance = new DatabaseManager();
        }
        return DatabaseManager.instance;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    private void loadDataBase(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
            addresses = objectMapper.readValue(new File("./src/main/resources/database/db.json"), new TypeReference<List<Address>>() {});

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveDataBase(){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            objectMapper.writeValue(new File("./target/addressExample.json"), addresses);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

//    TODO public void addAddress()
//    TODO public void updateAddress(id), welches Feld wird geupdatet?
//    TODO public void deleteAddress(id)
}
