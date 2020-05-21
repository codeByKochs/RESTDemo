package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.beans.DBConfiguration;
import com.codeByKochs.RestDemo.common.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Abstraction to DataSourceAdapter
 * loads DataSourceAdapter according to defined database type and path in DBConfiguration
 * allows for modular setup with different DataSourceAdapters
 */

@Component
public class DataSourceManager {

    private IDataSourceAdapter dataSourceAdapter;

    public DataSourceManager(DBConfiguration dbConfiguration){
//        appropriate DataSourceAdapter is created according to DBConfiguration
        switch(dbConfiguration.getDatabaseType()){
            case "json":
                dataSourceAdapter = new JsonDataSourceAdapter(dbConfiguration.getDatabasePath());
        }
    }

    public List<Address> getAddresses(){
       return dataSourceAdapter.getAddresses();
    }

    public Address addAddress(Address newAddress){
        return dataSourceAdapter.addAddress(newAddress);
    }

    public void deleteAddress(UUID id){
        dataSourceAdapter.deleteAddress(id);
    }

    public Address updateAddress(UUID id, Address updatedAddress){
        return dataSourceAdapter.updateAddress(id, updatedAddress);
    }
}
