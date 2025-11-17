-- Insert Products
INSERT INTO products (name, category, sku, purchase_price, selling_price, stock_qty, created_date)
VALUES
    ('Laptop', 'Electronics', 'ELE1001', 500.00, 800.00, 50, NOW()),
    ('Mouse', 'Accessories', 'ACC1002', 10.00, 25.00, 100, NOW()),
    ('Keyboard', 'Accessories', 'ACC1003', 20.00, 45.00, 75, NOW()),
    ('Monitor', 'Electronics', 'ELE1004', 200.00, 350.00, 30, NOW()),
    ('Headphones', 'Electronics', 'ELE1005', 50.00, 120.00, 60, NOW()),
    ('USB Cable', 'Accessories', 'ACC1006', 5.00, 15.00, 200, NOW()),
    ('Webcam', 'Electronics', 'ELE1007', 40.00, 85.00, 45, NOW()),
    ('Mouse Pad', 'Accessories', 'ACC1008', 3.00, 10.00, 150, NOW()),
    ('External HDD', 'Electronics', 'ELE1009', 80.00, 150.00, 25, NOW()),
    ('Desk Lamp', 'Furniture', 'FUR1010', 25.00, 60.00, 40, NOW());

-- Insert Stock Logs for initial stock
INSERT INTO stock_log (product_id, change_type, quantity, timestamp)
VALUES
    (1, 'ADD', 50, NOW()),
    (2, 'ADD', 100, NOW()),
    (3, 'ADD', 75, NOW()),
    (4, 'ADD', 30, NOW()),
    (5, 'ADD', 60, NOW()),
    (6, 'ADD', 200, NOW()),
    (7, 'ADD', 45, NOW()),
    (8, 'ADD', 150, NOW()),
    (9, 'ADD', 25, NOW()),
    (10, 'ADD', 40, NOW());
