package com.pms.product_management_system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pms.product_management_system.service.MyDocumentService;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Product Management System API",
				version = "1.0",
				description = "REST API for managing products"
		)
)
public class ProductManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private MyDocumentService documentService;

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// This will create the database and collection
		documentService.createDocument();
	}

}
