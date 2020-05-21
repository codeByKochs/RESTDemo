package com.codeByKochs.RestDemo.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * configuration bean used to return path to current json Database
 * the path variable is automatically loaded from application.properties on startup
 * parameters should be changed in application.properties
 */

@Component
public class DBConfiguration {

//    this reads the value from database.path in the config file application.properties
    @Value("${database.path}")
    private String databasePath;

//    this reads the value from database.type in the config file application.properties
    @Value("${database.type}")
    private String databaseType;

    public String getDatabasePath() {
        return databasePath;
    }
    public String getDatabaseType() {
        return databaseType;
    }
}
