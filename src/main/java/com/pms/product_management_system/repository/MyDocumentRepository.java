package com.pms.product_management_system.repository;

import com.pms.product_management_system.model.MyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyDocumentRepository extends MongoRepository<MyDocument, String> {
}
