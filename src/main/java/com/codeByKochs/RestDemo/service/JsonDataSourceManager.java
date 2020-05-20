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
 * Database manager used to manage address database (in this case a .json file)
 * uses dbConfigBean to load path to current database
 */

@Component
public class JsonDataSourceManager implements DataSourceManager{

    private static JsonDataSourceManager instance;
    private List<Address> addresses;
    private dbConfigBean dbConfigBean;

    public static JsonDataSourceManager getInstance(){
        if (JsonDataSourceManager.instance == null){
            JsonDataSourceManager.instance = new JsonDataSourceManager(new dbConfigBean());
        }
        return JsonDataSourceManager.instance;
    }

//    set private to avoid multiple instances (singleton pattern)
    @Autowired
    private JsonDataSourceManager(dbConfigBean dbConfigBean){
        this.dbConfigBean = dbConfigBean;
        this.addresses = new ArrayList<>();
        loadDataBase();
    }

    public Boolean isValidAddress(Address address){
        return address != null && address.getName() != null && address.getCity() != null && address.getStreet() != null && address.getZipcode() != null;
    }

//    TODO handle file not found exception somehow (maybe generate file)
    public void loadDataBase(){
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

    public void saveDataBase(){
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
        newAddress.setId(Address.generateUUID());
        if (isValidAddress(newAddress)) {
            addresses.add(newAddress);
            saveDataBase();
            return newAddress;
        }
        return null;
    }

    public Address updateAddress(UUID id, Address updatedAddress){
        if (isValidAddress(updatedAddress)){
            addresses.removeIf(address -> address.getId().equals(id));
            addresses.add(updatedAddress);
            saveDataBase();
            return updatedAddress;
        }
        return null;
    }

    public void deleteAddress(UUID id){
        addresses.removeIf(address -> address.getId().equals(id));
        saveDataBase();
    }
}
