package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.beans.dbConfigBean;
import com.codeByKochs.RestDemo.common.Address;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
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

//    set private to avoid multiple instances
    private DatabaseManager(){}

    public List<Address> getAddresses() {
        return addresses;
    }


//    TODO make private?
    public void loadDataBase(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        try {
//            try block is only used for development. Relative path to database is given in string (see Todo below)
//            TODO File jsonFile = new File(dbConfigBean.getDatabasePath()) does not seem to work
            File jsonFile = new File("./src/main/resources/database/db.json");
            addresses = objectMapper.readValue(jsonFile, new TypeReference<List<Address>>() {
            });
        } catch (Exception ex) {
            try {
//                try block executed in packed .jar
                File jsonFile = new File(dbConfigBean.getDatabasePath());
                addresses = objectMapper.readValue(jsonFile, new TypeReference<List<Address>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveDataBase(){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            objectMapper.writeValue(new File(dbConfigBean.getDatabasePath()), addresses);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

//    TODO public void addAddress()
//    TODO public void updateAddress(id), welches Feld wird geupdatet?
//    TODO public void deleteAddress(id)
}
