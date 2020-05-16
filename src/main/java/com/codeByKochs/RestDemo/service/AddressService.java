package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.common.Address;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class AddressService {

    private List<Address> addresses;

    public AddressService(){
        loadDataBase();
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello World!";
    }

//    TODO get mapping for addresses (response as json file), look mapObject
//    @GetMapping("/addresses")
//    public getAddresses(){
//    }
//    TODO set to private
    public void loadDataBase(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
            addresses = objectMapper.readValue(new File("./src/main/resources/database/db.json"), new TypeReference<List<Address>>() {});

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
//    TODO change to private?
    public void mapObjects(List<Address> list){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            objectMapper.writeValue(new File("./target/addressExample.json"), list);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
