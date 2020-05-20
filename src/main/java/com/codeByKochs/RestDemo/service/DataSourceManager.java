package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.common.Address;

import java.util.List;
import java.util.UUID;

public interface DataSourceManager {

    Boolean isValidAddress(Address address);
    void loadDataBase();
    void saveDataBase();
    List<Address> getAddresses();
    Address addAddress(Address newAddress);
    Address updateAddress(UUID id, Address updatedAddress);
    void deleteAddress(UUID id);
}
