package com.pms.product_management_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "myCollection")
public class MyDocument {

    @Id
    private String id;
    private String name;

    // Constructors, Getters, Setters
    public MyDocument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
