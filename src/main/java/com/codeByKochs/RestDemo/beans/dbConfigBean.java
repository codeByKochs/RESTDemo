package com.codeByKochs.RestDemo.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * configuration bean used to load information of the current path to the database (.json file) from application.properties
 */

@Component
public class dbConfigBean {

    @Value("${database.path}")
    private String databasePath;

    public String getDatabasePath() {
        return databasePath;
    }
}
