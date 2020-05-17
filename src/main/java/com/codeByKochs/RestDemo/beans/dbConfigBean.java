package com.codeByKochs.RestDemo.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class dbConfigBean {

    @Value("${database.path}")
    private String databasePath;

    public String getDatabasePath() {
        return databasePath;
    }
}
