# 🎯 Inventory Enigma - Inventory & Billing Management API

A Spring Boot REST API for managing products, inventory, invoices, and analytics. This project solves 4 puzzle challenges to implement a complete inventory management system.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Database Schema](#database-schema)
- [API Documentation](#api-documentation)
- [Testing Guide](#testing-guide)
- [Project Architecture](#project-architecture)
- [Troubleshooting](#troubleshooting)

---

## 🎯 Project Overview

**Inventory Enigma** is a RESTful API that implements an inventory and billing management system through a puzzle-driven approach. The project is structured around 4 levels, each solving a specific business challenge.

### Puzzle Challenges

#### Level 1: The Product Vault 🚪
> *"Four doors lead to product mastery. One creates, one reveals all, one transforms, one destroys."*

**Challenge:** Implement complete CRUD operations for product management  
**Solution:** Four REST endpoints with auto-generated SKU

#### Level 2: The Stock Guardian 📦
> *"What goes up must come down, but never below zero."*

**Challenge:** Manage stock with validation and prevent negative inventory  
**Solution:** Stock add/remove with `InsufficientStockException` and audit logging

#### Level 3: The Invoice Cipher 📄
> *"Many become one, and the vault adjusts itself."*

**Challenge:** Create invoices with multiple products and auto-deduct stock  
**Solution:** Transactional invoice creation with rollback on failure

#### Level 4: The Oracle's Dashboard 📊
> *"Analytics endpoints"*

**Challenge:** Provide business insights and metrics  
**Solution:** Real-time analytics with summary and low-stock alerts

---

## ✨ Features

### Product Management (Level 1)
- ✅ Create products with validation
- ✅ Auto-generate SKU: `FIRST3(category) + last4(timestamp)`
- ✅ View all products or individual product
- ✅ Update product information
- ✅ Delete products (with cascade handling)

### Stock Management (Level 2)
- ✅ Add stock to products
- ✅ Remove stock with validation
- ✅ Prevent negative stock quantities
- ✅ Throw `InsufficientStockException` when needed
- ✅ Log all stock changes in `stock_log` table
- ✅ Real-time inventory tracking

### Invoice Management (Level 3)
- ✅ Create invoices with multiple products
- ✅ Automatic stock deduction
- ✅ Transactional integrity with `@Transactional`
- ✅ Rollback on insufficient stock
- ✅ Auto-calculate total price
- ✅ Generate invoice number: `INV-YYYYMMDD-XXXX`

### Analytics Dashboard (Level 4)
- ✅ Total products count
- ✅ Stock value calculation (purchase_price × stock_qty)
- ✅ Today's invoice count
- ✅ Today's revenue
- ✅ Low stock alerts (stock < 10)

### Additional Features
- ✅ Global exception handling
- ✅ Input validation with `@Valid`
- ✅ Swagger/OpenAPI documentation
- ✅ Comprehensive logging
- ✅ RESTful API design
- ✅ Clean architecture

---

## 🔧 Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java | 17      |
| **Framework** | Spring Boot | 3.5.7   |
| **Database** | PostgreSQL | 15      |
| **ORM** | Spring Data JPA | 6.x     |
| **Validation** | Jakarta Validation | 3.0     |
| **Documentation** | Swagger/OpenAPI | 2.8.13     |
| **Build Tool** | Maven | 3.9+    |
| **Utilities** | Lombok | Latest  |

---

## 📦 Prerequisites

Before starting, ensure you have these installed:

### Required Software

1. **Java Development Kit (JDK) 17 or higher**
   ```bash
   java -version
   # Output: java version "17.0.x"
   ```

2. **Apache Maven 3.6 or higher**
   ```bash
   mvn -version
   # Output: Apache Maven 3.6.x or higher
   ```

3. **PostgreSQL 15 or higher**
   ```bash
   psql --version
   # Output: psql (PostgreSQL) 15.x
   ```

4. **Git**
   ```bash
   git --version
   ```

5. **Postman** (Optional, for API testing)
   - Download: https://www.postman.com/downloads/

---

## 🚀 Installation & Setup

### Step 1: Clone the Repository

```bash
# Clone the project
git clone https://github.com/yourusername/inventory-enigma.git

# Navigate to project directory
cd inventory-enigma
```

### Step 2: Setup PostgreSQL Database

#### Start PostgreSQL Service

**On Linux:**
```bash
sudo systemctl start postgresql
sudo systemctl enable postgresql
```

**On macOS:**
```bash
brew services start postgresql@15
```

**On Windows:**
- PostgreSQL should start automatically after installation
- Or start from Services (services.msc)

#### Create Database

```bash
# Login to PostgreSQL
psql -U postgres

# Or on Linux
sudo -u postgres psql
```

**Inside PostgreSQL console:**
```sql
-- Create database
CREATE DATABASE inventory_enigma;

-- Create user (optional, recommended for production)
CREATE USER inventory_user WITH PASSWORD 'secure_password123';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE inventory_enigma TO inventory_user;

-- Connect to database
\c inventory_enigma

-- Grant schema privileges (PostgreSQL 15+)
GRANT ALL ON SCHEMA public TO inventory_user;

-- Verify database created
\l

-- Exit
\q
```

### Step 3: Configure Application

Edit `src/main/resources/application.properties`:

```properties
# ============================================
# Database Configuration (PostgreSQL)
# ============================================
spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_enigma
spring.datasource.username=postgres
spring.datasource.password=your_password_here
spring.datasource.driver-class-name=org.postgresql.Driver

# ============================================
# JPA/Hibernate Configuration
# ============================================
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# ============================================
# Server Configuration
# ============================================
server.port=8080

# ============================================
# Swagger/OpenAPI Configuration
# ============================================
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

**⚠️ Important:** Replace `your_password_here` with your actual PostgreSQL password!

### Step 4: Build the Project

```bash
# Clean and build
mvn clean install
```

This will:
- Download all dependencies
- Compile the code
- Run tests
- Create executable JAR file

### Step 5: Run the Application

**Option 1: Using Maven**
```bash
mvn spring-boot:run
```

**Option 2: Using JAR file**
```bash
java -jar target/inventory-enigma-1.0.0.jar
```

**Option 3: Using IDE**
- Open `InventoryEnigmaApplication.java`
- Right-click → Run

### Step 6: Verify Application Started

**Check console output:**
```
  _____                      _                   
 |_   _|                    | |                  
   | |  _ ____   _____ _ __ | |_ ___  _ __ _   _ 
   | | | '_ \ \ / / _ \ '_ \| __/ _ \| '__| | | |
  _| |_| | | \ V /  __/ | | | || (_) | |  | |_| |
 |_____|_| |_|\_/ \___|_| |_|\__\___/|_|   \__, |
                                             __/ |
                                            |___/ 

Started InventoryEnigmaApplication in 3.456 seconds
```

**Test with browser:**
```
http://localhost:8080/swagger-ui.html
```

**Test with curl:**
```bash
curl http://localhost:8080/api/products
# Output: [] or list of products
```

---





### SQL Schema

```sql
-- Create products table
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    sku VARCHAR(50) NOT NULL UNIQUE,
    purchase_price DECIMAL(10,2) NOT NULL CHECK (purchase_price > 0),
    sell_price DECIMAL(10,2) NOT NULL CHECK (sell_price > 0),
    stock_qty INT NOT NULL DEFAULT 0 CHECK (stock_qty >= 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_products_sku ON products(sku);
CREATE INDEX idx_products_stock_qty ON products(stock_qty);

-- Create invoice table
CREATE TABLE invoice (
    id BIGSERIAL PRIMARY KEY,
    invoice_number VARCHAR(50) NOT NULL UNIQUE,
    total_price DECIMAL(10,2) NOT NULL CHECK (total_price >= 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_invoice_number ON invoice(invoice_number);
CREATE INDEX idx_invoice_created_at ON invoice(created_at);

-- Create invoice_items table
CREATE TABLE invoice_items (
    id BIGSERIAL PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    qty INT NOT NULL CHECK (qty > 0),
    unit_price DECIMAL(10,2) NOT NULL CHECK (unit_price > 0),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
);

CREATE INDEX idx_invoice_items_invoice_id ON invoice_items(invoice_id);
CREATE INDEX idx_invoice_items_product_id ON invoice_items(product_id);

-- Create stock_log table
CREATE TABLE stock_log (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    change_type VARCHAR(20) NOT NULL CHECK (change_type IN ('ADD', 'REMOVE', 'SALE')),
    quantity INT NOT NULL CHECK (quantity > 0),
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE INDEX idx_stock_log_product_id ON stock_log(product_id);
CREATE INDEX idx_stock_log_timestamp ON stock_log(timestamp);
```

---

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Swagger UI
```
http://localhost:8080/swagger-ui.html
```

---

## 🔌 API Endpoints

### Level 1: Product Management

#### 1. Create Product

**Endpoint:** `POST /api/products`

**Description:** Creates a new product with auto-generated SKU

**Request Body:**
```json
{
  "name": "Dell XPS 15 Laptop",
  "category": "Electronics",
  "purchasePrice": 800.00,
  "sellPrice": 1200.00
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Dell XPS 15 Laptop",
  "category": "Electronics",
  "sku": "ELE8765",
  "purchasePrice": 800.00,
  "sellPrice": 1200.00,
  "stockQty": 0,
  "createdAt": "2025-11-17T10:30:00"
}
```



**Curl Example:**
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dell XPS 15 Laptop",
    "category": "Electronics",
    "purchasePrice": 800.00,
    "sellPrice": 1200.00
  }'
```

---

#### 2. Get All Products

**Endpoint:** `GET /api/products`

**Description:** Retrieves all products in the system

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Dell XPS 15 Laptop",
    "category": "Electronics",
    "sku": "ELE8765",
    "purchasePrice": 800.00,
    "sellPrice": 1200.00,
    "stockQty": 50,
    "createdAt": "2025-11-17T10:30:00"
  },
  {
    "id": 2,
    "name": "Logitech MX Master Mouse",
    "category": "Accessories",
    "sku": "ACC9876",
    "purchasePrice": 50.00,
    "sellPrice": 99.00,
    "stockQty": 100,
    "createdAt": "2025-11-17T10:35:00"
  }
]
```

**Curl Example:**
```bash
curl http://localhost:8080/api/products
```

---

#### 3. Get Product by ID

**Endpoint:** `GET /api/products/{id}`

**Description:** Retrieves a specific product by ID

**Path Parameter:**
- `id` (Long): Product ID

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Dell XPS 15 Laptop",
  "category": "Electronics",
  "sku": "ELE8765",
  "purchasePrice": 800.00,
  "sellPrice": 1200.00,
  "stockQty": 50,
  "createdAt": "2025-11-17T10:30:00"
}
```

**Error Response:** `404 Not Found`
```json
{
  "status": 404,
  "message": "Product not found with id: 999",
  "timestamp": "2025-11-17T10:30:00"
}
```

**Curl Example:**
```bash
curl http://localhost:8080/api/products/1
```

---

#### 4. Update Product

**Endpoint:** `PUT /api/products/{id}`

**Description:** Updates an existing product's information

**Path Parameter:**
- `id` (Long): Product ID

**Request Body:**
```json
{
  "name": "Dell XPS 15 Laptop - Updated",
  "category": "Electronics",
  "purchasePrice": 850.00,
  "sellPrice": 1250.00
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Dell XPS 15 Laptop - Updated",
  "category": "Electronics",
  "sku": "ELE8765",
  "purchasePrice": 850.00,
  "sellPrice": 1250.00,
  "stockQty": 50,
  "createdAt": "2025-11-17T10:30:00"
}
```

**Note:** SKU and stock quantity cannot be updated through this endpoint

**Curl Example:**
```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dell XPS 15 Laptop - Updated",
    "category": "Electronics",
    "purchasePrice": 850.00,
    "sellPrice": 1250.00
  }'
```

---

#### 5. Delete Product

**Endpoint:** `DELETE /api/products/{id}`

**Description:** Deletes a product from the system

**Path Parameter:**
- `id` (Long): Product ID

**Response:** `204 No Content`

**Error Responses:**

`404 Not Found` - Product doesn't exist
```json
{
  "status": 404,
  "message": "Product not found with id: 999",
  "timestamp": "2025-11-17T10:30:00"
}
```

`500 Internal Server Error` - Product has dependencies
```json
{
  "status": 500,
  "message": "Cannot delete product. It has stock history or invoice references.",
  "timestamp": "2025-11-17T10:30:00"
}
```

**Curl Example:**
```bash
curl -X DELETE http://localhost:8080/api/products/1
```

---

### Level 2: Stock Management

#### 6. Add Stock

**Endpoint:** `POST /api/stock/add`

**Description:** Increases stock quantity for a product

**Request Body:**
```json
{
  "productId": 1,
  "quantity": 50
}
```

**Response:** `200 OK`
```
"Stock added successfully"
```



**Error Response:** `404 Not Found`
```json
{
  "status": 404,
  "message": "Product not found with id: 999",
  "timestamp": "2025-11-17T10:30:00"
}
```

**Curl Example:**
```bash
curl -X POST http://localhost:8080/api/stock/add \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "quantity": 50
  }'
```

---

#### 7. Remove Stock

**Endpoint:** `POST /api/stock/remove`

**Description:** Decreases stock quantity for a product

**Request Body:**
```json
{
  "productId": 1,
  "quantity": 10
}
```

**Response:** `200 OK`
```
"Stock removed successfully"
```



**Error Response:** `400 Bad Request` (Insufficient Stock)
```json
{
  "status": 400,
  "message": "Insufficient stock for product: Dell XPS 15 Laptop. Available: 5, Requested: 10",
  "timestamp": "2025-11-17T10:30:00"
}
```

**Curl Example:**
```bash
curl -X POST http://localhost:8080/api/stock/remove \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "quantity": 10
  }'
```

---

### Level 3: Invoice Management

#### 8. Create Invoice

**Endpoint:** `POST /api/invoices`

**Description:** Creates an invoice with multiple products and automatically deducts stock

**Request Body:**
```json
{
  "items": [
    {
      "productId": 1,
      "qty": 2
    },
    {
      "productId": 2,
      "qty": 3
    }
  ]
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "invoiceNumber": "INV-20251117-0001",
  "totalPrice": 2697.00,
  "createdAt": "2025-11-17T10:30:00",
  "items": [
    {
      "id": 1,
      "productId": 1,
      "productName": "Dell XPS 15 Laptop",
      "qty": 2,
      "unitPrice": 1200.00
    },
    {
      "id": 2,
      "productId": 2,
      "productName": "Logitech MX Master Mouse",
      "qty": 3,
      "unitPrice": 99.00
    }
  ]
}
```



**Invoice Number Format:**
```
INV-YYYYMMDD-XXXX
Example: INV-20251117-0001
Where:
  - YYYY: Year (2025)
  - MM: Month (11)
  - DD: Day (17)
  - XXXX: Sequential number (0001, 0002, ...)
```



**Error Response:** `400 Bad Request`
```json
{
  "status": 400,
  "message": "Insufficient stock for product: Dell XPS 15 Laptop. Available: 1, Requested: 2",
  "timestamp": "2025-11-17T10:30:00"
}
```

**Curl Example:**
```bash
curl -X POST http://localhost:8080/api/invoices \
  -H "Content-Type: application/json" \
  -d '{
    "items": [
      {"productId": 1, "qty": 2},
      {"productId": 2, "qty": 3}
    ]
  }'
```

---

#### 9. Get All Invoices

**Endpoint:** `GET /api/invoices`

**Description:** Retrieves all invoices in the system

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "invoiceNumber": "INV-20251117-0001",
    "totalPrice": 2697.00,
    "createdAt": "2025-11-17T10:30:00",
    "items": [
      {
        "id": 1,
        "productId": 1,
        "productName": "Dell XPS 15 Laptop",
        "qty": 2,
        "unitPrice": 1200.00
      },
      {
        "id": 2,
        "productId": 2,
        "productName": "Logitech MX Master Mouse",
        "qty": 3,
        "unitPrice": 99.00
      }
    ]
  }
]
```

**Curl Example:**
```bash
curl http://localhost:8080/api/invoices
```

---

#### 10. Get Invoice by ID

**Endpoint:** `GET /api/invoices/{id}`

**Description:** Retrieves a specific invoice by ID

**Path Parameter:**
- `id` (Long): Invoice ID

**Response:** `200 OK`
```json
{
  "id": 1,
  "invoiceNumber": "INV-20251117-0001",
  "totalPrice": 2697.00,
  "createdAt": "2025-11-17T10:30:00",
  "items": [
    {
      "id": 1,
      "productId": 1,
      "productName": "Dell XPS 15 Laptop",
      "qty": 2,
      "unitPrice": 1200.00
    },
    {
      "id": 2,
      "productId": 2,
      "productName": "Logitech MX Master Mouse",
      "qty": 3,
      "unitPrice": 99.00
    }
  ]
}
```

**Error Response:** `404 Not Found`
```json
{
  "status": 404,
  "message": "Invoice not found with id: 999",
  "timestamp": "2025-11-17T10:30:00"
}
```

**Curl Example:**
```bash
curl http://localhost:8080/api/invoices/1
```

---

### Level 4: Analytics

#### 11. Get Analytics Summary

**Endpoint:** `GET /api/analytics/summary`

**Description:** Provides dashboard metrics and business insights

**Response:** `200 OK`
```json
{
  "totalProducts": 15,
  "stockValue": 45000.00,
  "todayInvoices": 8,
  "todayRevenue": 12500.00
}
```



**Curl Example:**
```bash
curl http://localhost:8080/api/analytics/summary
```

---

#### 12. Get Low Stock Products

**Endpoint:** `GET /api/analytics/low-stock`

**Description:** Returns products with stock quantity less than 10

**Response:** `200 OK`
```json
[
  {
    "id": 3,
    "name": "USB Cable",
    "sku": "ACC2003",
    "stockQty": 5
  },
  {
    "id": 7,
    "name": "Webcam",
    "sku": "ELE1005",
    "stockQty": 8
  }
]
```



**Curl Example:**
```bash
curl http://localhost:8080/api/analytics/low-stock
```

---


