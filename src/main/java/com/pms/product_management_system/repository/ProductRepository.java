package com.pms.product_management_system.repository;

import com.pms.product_management_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
