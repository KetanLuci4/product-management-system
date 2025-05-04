package com.pms.product_management_system.config;

import com.pms.product_management_system.model.Product;
import com.pms.product_management_system.model.User;
import com.pms.product_management_system.repository.ProductRepository;
import com.pms.product_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        log.info("Initializing sample data...");

        // Initialize users if they don't exist
        if (userRepository.count() == 0) {
            initUsers();
        }

        // Initialize products if they don't exist
        if (productRepository.count() == 0) {
            initProducts();
        }

        log.info("Sample data initialization completed!");
    }

    private void initUsers() {
        log.info("Creating sample users...");

        // Create admin user
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add("ADMIN");
        adminRoles.add("USER");
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        // Create regular user
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user123"));
        Set<String> userRoles = new HashSet<>();
        userRoles.add("USER");
        user.setRoles(userRoles);
        userRepository.save(user);

        log.info("Sample users created successfully!");
    }

    private void initProducts() {
        log.info("Creating sample products...");

        List<Product> sampleProducts = Arrays.asList(
                new Product(null, "Laptop", "High-performance laptop with 16GB RAM", 1299.99, 10),
                new Product(null, "Smartphone", "Latest model with 5G capabilities", 899.99, 15),
                new Product(null, "Headphones", "Noise-cancelling wireless headphones", 299.99, 20),
                new Product(null, "Tablet", "10-inch tablet with retina display", 499.99, 8),
                new Product(null, "Smartwatch", "Fitness tracking and notifications", 199.99, 12)
        );

        productRepository.saveAll(sampleProducts);

        log.info("Sample products created successfully!");
    }
}