package com.pms.product_management_system.service;

import com.pms.product_management_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Page<Product> searchProducts(String keyword, Pageable pageable);
    Product getProductById(String id);
    Product createProduct(Product product);
    Product updateProduct(String id, Product productDetails);
    void deleteProduct(String id);
}
