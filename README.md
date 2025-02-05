# Antique Marketplace Management System

## Project Overview

The antique marketplace management system automates the process of buying, selling, 
and evaluating antique items. It replaces outdated trading methods, ensuring transparency, 
security, and efficiency for all market participants.

## Goals

- Optimize the management processes of products, orders, and payments.
- Provide users with the ability to view antique items and make purchases online.
- Ensure detailed record-keeping of orders, users, products, and payments.
  
## Main Features

Authentication
- User registration: POST /auth/registration
- User login: POST /auth/login
  
Product Management
- Add products: POST /products
- View products: GET /products
- Update products: PUT/PATCH /products/{id}
- Delete products: DELETE /products/{id}
- Search products: GET /products/search
- Filter products by categories, price, alphabetical order
  
Order Management
- Create an order: POST /orders
- View orders: GET /orders
- Update order status: PUT /orders/{id}
- View order history: GET /orders/history/{orderId}
  
Payments
- Create a payment: POST /payments
- View payments: GET /payments
- Process successful payment: GET /payments/success/{id}
- Handle declined payments: GET /payments/cancel/{id}
- Create an international payment: POST /payments/international/{orderId}
- Create an internal payment: POST /payments/internal/{orderId}
- Create a cash payment: POST /payments/cash/{orderId}

Category Management
- View all categories: GET /categories
- Get category by ID: GET /categories/{id}
- Create category: POST /categories
- Update category: PUT /categories/{id}
- Delete category: DELETE /categories/{id}
  
Article Management
- View articles: GET /articles
- View article by ID: GET /articles/{id}
- Add an article: POST /articles
- Update an article: PUT /articles/{id}
- Delete an article: DELETE /articles/{id}
  
Exhibition Management
- View exhibitions: GET /exhibitions
- Get exhibition by ID: GET /exhibitions/{id}
- Create an exhibition: POST /exhibitions
- Update an exhibition: PUT /exhibitions/{id}
- Delete an exhibition: DELETE /exhibitions/{id}
  
User Management
- Update profile: PUT /users/me/upd-profile
- Update password: PUT /users/me/upd-pass
- Reset password: PUT /users/me/reset-pass
- Assign user role: PUT /users/upd-role
- Get user profile: GET /users/me
- Delete user profile: DELETE /users/me/delete
  
Tag Management
- View all tags: GET /tags
- Get tag by ID: GET /tags/{id}
- Create tag: POST /tags
- Update tag: PUT /tags/{id}
- Delete tag: DELETE /tags/{id}

Discussion Management
- View discussions: GET /discussions
- Create discussion: POST /discussions
- Add comment: POST /discussions/add-comment/{discussionId}
- View comments: GET /discussions/comments
- Delete comment: DELETE /discussions/comments/{id}

## Tech Stack
This project uses cutting-edge technologies to ensure high performance and a smooth user experience:

Core Application Framework
- Spring Boot: Stable and scalable backend framework.
- Spring Security: Secure authentication and authorization.
- Spring Data JPA: Simplifies database operations.
- Spring Boot Mail: Enables email functionality for the application.
- Spring Boot Validation: Ensures data integrity with built-in validation.

Authentication
- JWT (JSON Web Token): Secure authentication mechanism using:
  jjwt-api, jjwt-impl, and jjwt-jackson.

Database Management
- MySQL: Reliable SQL database for structured data storage.
- Hibernate ORM: Simplifies database interactions.
- Liquibase: Database version control.
- H2 Database: In-memory database for testing.

Data Transformation and Communication
- MapStruct: Automates object mapping to improve efficiency.
- Jakarta EL (Expression Language): Supports dynamic expressions in Java applications.

Development and Documentation
- SpringDoc OpenAPI: Provides API documentation via Swagger UI.
- GlobalExceptionHandler: Manages exceptions gracefully.
  
Build & Code Quality Tools
- Maven Compiler Plugin: Ensures compatibility with Java version settings.
- Checkstyle Plugin: Enforces coding standards and best practices.
- Liquibase Maven Plugin: Manages database migrations within the build process.

## Additional Information
- Base URL for all requests: [API](https://tp136-production.up.railway.app/api)
- Swagger Documentation: [Swagger UI](https://tp136-production.up.railway.app/api/swagger-ui/index.html)


### Thank you for using our system! 🏺

