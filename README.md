# Product Management System API

A RESTful API for product management built with Spring Boot, MongoDB, and JWT Authentication.

## Features

- Product CRUD operations with validation
- User authentication with JWT
- Role-based authorization (Admin/User)
- Pagination and sorting
- Global exception handling
- API documentation with Swagger
- Unit tests

## Technology Stack

- Java 17
- Spring Boot 3.2.0
- Spring Security with JWT
- Spring Data MongoDB
- Swagger/OpenAPI for documentation
- Lombok
- JUnit and Mockito for testing

## Getting Started

### Prerequisites

- Java JDK 17
- Maven
- MongoDB

### Installation

1. Clone the repository
```bash
git clone https://github.com/KetanLuci4/product-management-system
```

2. Configure MongoDB

Make sure MongoDB is running on your system. Update the `application.properties` file if your MongoDB configuration is different from the default.
In admin collection there should be a username : root ( with having admin permission ) and password : root

3. Build the application
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

The API will be available at http://localhost:8081

### API Documentation

Once the application is running, you can access the Swagger UI documentation at:
```
http://localhost:8081/swagger-ui.html
```

## API Endpoints

### Authentication

- `POST /api/auth/signin` - Authenticate a user and get JWT token
- `POST /api/auth/signup` - Register a new user

### Products

- `GET /api/products` - Get all products (paginated)
- `GET /api/products/search?keyword=name` - Search products by name
- `GET /api/products/{id}` - Get a product by ID
- `POST /api/products` - Create a new product (Admin only)
- `PUT /api/products/{id}` - Update a product (Admin only)
- `DELETE /api/products/{id}` - Delete a product (Admin only)

## Default Users

The application comes with two predefined users:

1. Admin User
    - Username: admin
    - Password: admin123
    - Roles: ADMIN, USER

2. Regular User
    - Username: user
    - Password: user123
    - Roles: USER

## Security

The application uses JWT (JSON Web Token) for authentication. To access protected endpoints:

1. Obtain a JWT token by signing in
2. Include the token in the Authorization header of subsequent requests:
   ```
   Authorization: Bearer your_jwt_token
   ```

## Testing

Run tests with Maven:
```bash
mvn test
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.
