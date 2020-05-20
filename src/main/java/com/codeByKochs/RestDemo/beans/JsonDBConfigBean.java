package com.codeByKochs.RestDemo.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * configuration bean used to return path to current json Database
 * the path variable is automatically loaded from application.properties on startup
 */

@Component
public class JsonDBConfigBean {

    @Value("${database.path}")
    private String databasePath;

    public String getDatabasePath() {
        return databasePath;
    }
}
