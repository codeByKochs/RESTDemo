package com.codeByKochs.RestDemo.service;

import com.codeByKochs.RestDemo.common.Address;

import java.util.List;
import java.util.UUID;

/**
 * Interface defining structure for a Data Source Manager
 */

public interface IDataSourceAdapter {

//    returns database address entries as list
    List<Address> getAddresses();

//    adds given address to database
    Address addAddress(Address newAddress);

//    updates address in database
    Address updateAddress(UUID id, Address updatedAddress);

//    deletes address with given UUID
    void deleteAddress(UUID id);
}
