A Spring Boot REST API for Inventory & Billing Management with puzzle-themed architecture.

## 📋 Project Overview

This project implements a complete inventory management system with:
- ✅ Level 1: Product CRUD with auto-generated SKU
- ✅ Level 2: Stock management with validation and logging
- ✅ Level 3: Invoice creation with transactional stock deduction
- ✅ Level 4: Analytics dashboard with insights

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- PostgreSQL 15+

### Setup Steps

1. Create PostgreSQL database:
   CREATE DATABASE inventory_enigma;

2. Configure application.properties with your Postgres credentials
spring.application.name=InventoryEnigma

spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_enigma
spring.datasource.username=postgres
spring.datasource.password=your password
spring.datasource.driver-class-name=org.postgresql.Driver



spring.jpa.hibernate.ddl-auto=create
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true


# Swagger/OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html


3. Build and run:
   mvn clean install
   mvn spring-boot:run


## 🎮 API Endpoints

### Level 1: Products
- POST /api/products - Create product
- GET /api/products - Get all products
- GET /api/products/{id} - Get product by ID
- PUT /api/products/{id} - Update product
- DELETE /api/products/{id} - Delete product

### Level 2: Stock
- POST /api/stock/add - Add stock
- POST /api/stock/remove - Remove stock

### Level 3: Invoices
- POST /api/invoices - Create invoice
- GET /api/invoices - Get all invoices
- GET /api/invoices/{id} - Get invoice by ID

### Level 4: Analytics
- GET /api/analytics/summary - Get dashboard summary
- GET /api/analytics/low-stock - Get low stock products

## 🔧 Technology Stack

- Spring Boot 3.5
- PostgreSQL 15+
- Spring Data JPA
- Jakarta Validation
- Swagger/OpenAPI 3
- Lombok

## Postman Collection

{
"collection": {
"info": {
"_postman_id": "b4342400-a6ee-4623-8c68-74a4207e52d4",
"name": "Inventory_Enigma",
"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
"updatedAt": "2025-11-17T07:52:54.000Z",
"createdAt": "2025-11-16T14:21:29.000Z",
"lastUpdatedBy": "46450046",
"uid": "46450046-b4342400-a6ee-4623-8c68-74a4207e52d4"
},
"item": [
{
"name": "Products Management",
"item": [
{
"name": "CreateProduct",
"id": "de284de8-a05a-4d6b-ba50-a6de3a4f0019",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "POST",
"header": [],
"body": {
"mode": "raw",
"raw": "{\r\n  \"name\": \"Coffee\",\r\n  \"category\": \"Grocery\",\r\n  \"purchasePrice\": 195,\r\n  \"sellPrice\": 250\r\n}\r\n\r\n\r\n",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "http://localhost:8080/api/products",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"products"
]
}
},
"response": [],
"uid": "46450046-de284de8-a05a-4d6b-ba50-a6de3a4f0019"
},
{
"name": "GetAllProducts",
"id": "c1bbb77d-f8d6-470e-b3c4-4b7acca2263e",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "http://localhost:8080/api/products",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"products"
]
}
},
"response": [],
"uid": "46450046-c1bbb77d-f8d6-470e-b3c4-4b7acca2263e"
},
{
"name": "GetProductById",
"id": "1440ab17-bbb0-4b73-834b-a0ba4e9435d1",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "http://localhost:8080/api/products/2",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"products",
"2"
]
}
},
"response": [],
"uid": "46450046-1440ab17-bbb0-4b73-834b-a0ba4e9435d1"
},
{
"name": "UpdateProducts",
"id": "f63f437c-9340-4d88-ade8-d8d02ee07c9b",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "PUT",
"header": [],
"body": {
"mode": "raw",
"raw": "{\r\n    \"name\": \"Milk Powder\",\r\n    \"category\": \"Grocery\",\r\n    \"purchasePrice\": 500.00,\r\n    \"sellPrice\": 550.00\r\n}",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "http://localhost:8080/api/products/2",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"products",
"2"
]
}
},
"response": [],
"uid": "46450046-f63f437c-9340-4d88-ade8-d8d02ee07c9b"
},
{
"name": "DeleteProduct",
"id": "a78e12a9-6c93-4452-bce4-c66e25a034c1",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "DELETE",
"header": [],
"url": {
"raw": "http://localhost:8080/api/products/4",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"products",
"4"
]
}
},
"response": [],
"uid": "46450046-a78e12a9-6c93-4452-bce4-c66e25a034c1"
}
],
"id": "e5fdbc77-e7fd-40c3-95d1-5eb443d9776f",
"uid": "46450046-e5fdbc77-e7fd-40c3-95d1-5eb443d9776f"
},
{
"name": "Stock Management",
"item": [
{
"name": "AddStock",
"id": "019bc35c-860f-4c9b-9232-da327a102ede",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "POST",
"header": [],
"body": {
"mode": "raw",
"raw": "{\r\n  \"productId\": 1,\r\n  \"qty\": 10\r\n}",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "http://localhost:8080/api/stock/add",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"stock",
"add"
]
}
},
"response": [],
"uid": "46450046-019bc35c-860f-4c9b-9232-da327a102ede"
},
{
"name": "RemoveStock",
"id": "97f7ab9c-1b3e-4dcb-b4e4-4ddef6981c56",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "POST",
"header": [],
"body": {
"mode": "raw",
"raw": "{\r\n  \"productId\": 1,\r\n  \"qty\": 5\r\n}",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "http://localhost:8080/api/stock/remove",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"stock",
"remove"
]
}
},
"response": [],
"uid": "46450046-97f7ab9c-1b3e-4dcb-b4e4-4ddef6981c56"
}
],
"id": "f7d14fd9-b5fe-45b4-b904-9d1a95c4a4c3",
"uid": "46450046-f7d14fd9-b5fe-45b4-b904-9d1a95c4a4c3"
},
{
"name": "Invoice Management",
"item": [
{
"name": "GetAllInvoices",
"id": "1c634e36-e5f9-47df-bcfe-a1bb4f054106",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "GET",
"header": [],
"body": {
"mode": "raw",
"raw": "{\r\n  \"productId\": 1,\r\n  \"qty\": 5\r\n}"
},
"url": {
"raw": "http://localhost:8080/api/invoices",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"invoices"
]
}
},
"response": [],
"uid": "46450046-1c634e36-e5f9-47df-bcfe-a1bb4f054106"
},
{
"name": "CreateInvoice",
"id": "c1529c4e-a9c7-44af-b4a8-c825a566658d",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "POST",
"header": [],
"body": {
"mode": "raw",
"raw": "{\r\n  \"items\": [\r\n    {\r\n      \"productId\": 1,\r\n      \"qty\": 2\r\n    },\r\n    {\r\n      \"productId\": 2,\r\n      \"qty\": 3\r\n    }\r\n  ]\r\n}",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "http://localhost:8080/api/invoices",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"invoices"
]
}
},
"response": [],
"uid": "46450046-c1529c4e-a9c7-44af-b4a8-c825a566658d"
},
{
"name": "GetInvoicesById",
"id": "1cf03652-f5cd-496d-8d4a-41e6f1f49cd3",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "http://localhost:8080/api/invoices/1",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"invoices",
"1"
]
}
},
"response": [],
"uid": "46450046-1cf03652-f5cd-496d-8d4a-41e6f1f49cd3"
}
],
"id": "bf9412c8-a9c1-4d0a-a8e4-fe711289f630",
"uid": "46450046-bf9412c8-a9c1-4d0a-a8e4-fe711289f630"
},
{
"name": "Dashboard",
"item": [
{
"name": "GetAnalyticsSummary",
"id": "1fe2b52c-14c9-4dd7-8796-1104971b2d2c",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "http://localhost:8080/api/analytics/summary",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"analytics",
"summary"
]
}
},
"response": [],
"uid": "46450046-1fe2b52c-14c9-4dd7-8796-1104971b2d2c"
},
{
"name": "GetLowStockProduct",
"id": "c590f601-914b-4b9d-a597-1dba5e1486d4",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "http://localhost:8080/api/analytics/low-stock",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"api",
"analytics",
"low-stock"
]
}
},
"response": [],
"uid": "46450046-c590f601-914b-4b9d-a597-1dba5e1486d4"
}
],
"id": "fa2d8608-dd59-417d-b57e-19e8f79fa7c2",
"uid": "46450046-fa2d8608-dd59-417d-b57e-19e8f79fa7c2"
}
]
}
}