CREATE TABLE MenuItems (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    item_description TEXT,
    item_price DECIMAL(10, 2) NOT NULL,
    item_category VARCHAR(50)
);
CREATE TABLE restraunt_table (
    table_id INT AUTO_INCREMENT PRIMARY KEY,
    table_number INT NOT NULL,
    table_status ENUM('vacant','occupied') DEFAULT 'vacant'
);
CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    table_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (table_id) REFERENCES restraunt_table(table_id)
);

CREATE TABLE OrderedItems (
    ordered_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    item_id INT,
    quantity INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (item_id) REFERENCES MenuItems(item_id)
);
