package com.pms.product_management_system.service;

import com.pms.product_management_system.model.MyDocument;
import com.pms.product_management_system.repository.MyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyDocumentService {

    @Autowired
    private MyDocumentRepository repository;

    public void createDocument() {
        // Create a new document
        MyDocument document = new MyDocument("Sample Data");
        // Insert the document, triggering MongoDB to create the database and collection
        repository.save(document);
    }
}
