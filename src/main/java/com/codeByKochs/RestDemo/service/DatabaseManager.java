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

    public List<Address> getAddresses() {
        return addresses;
    }

    // TODO make private
    public void loadDataBase(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        try {

            addresses = objectMapper.readValue(new File(dbConfigBean.getDatabasePath()), new TypeReference<List<Address>>() {});

//            URL jsonUrl = getClass().getResource("/db.json");
//            File jsonFile = new File (jsonUrl.getFile());
//            addresses = objectMapper.readValue(getClass().getResource("/db.json")
//                    , new TypeReference<List<Address>>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }


//            String relativePath = "META-INF/somebinaryfile.bmp";
//            URL resource = getClass().getClassLoader().getResource(relativePath).toURI();
//
//            File jsonFile = ResourceUtils.getFile("classpath:config/db.json");

//            URL jsonUrl = this.getClass().getClassLoader().getResource("/db.json");
//

//            addresses = objectMapper.readValue(jsonFile, new TypeReference<List<Address>>() {});

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
