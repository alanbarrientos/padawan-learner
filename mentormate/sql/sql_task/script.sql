DROP DATABASE IF EXISTS restaurant;

CREATE SCHEMA restaurant;
USE restaurant;

CREATE TABLE role_user (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(50),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           created_by varchar(50),
                           updated_by varchar(50),
                           its_soft_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(50),
                      last_name VARCHAR(50),
                      email VARCHAR(50),
                      role_id INT,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      created_by varchar(50),
                      updated_by varchar(50),
                      its_soft_deleted BOOLEAN DEFAULT FALSE,
                      FOREIGN KEY (role_id) REFERENCES role_user(id)
);

CREATE TABLE restaurant_table (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  table_number INT,
                                  seats INT,
                                  is_ocuppied BOOLEAN DEFAULT FALSE,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  created_by varchar(50),
                                  updated_by varchar(50),
                                  its_soft_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE product_category (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  name VARCHAR(50),
                                  parent_category_id INT,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  created_by varchar(50),
                                  updated_by varchar(50),
                                  its_soft_deleted BOOLEAN DEFAULT FALSE,
                                  FOREIGN KEY (parent_category_id) REFERENCES product_category(id)
);

CREATE TABLE product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(50),
                         code VARCHAR(50),
                         description VARCHAR(150),
                         image_path VARCHAR(100),
                         price DOUBLE,
                         category_id INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         created_by varchar(50),
                         updated_by varchar(50),
                         its_soft_deleted BOOLEAN DEFAULT FALSE,
                         FOREIGN KEY (category_id) REFERENCES product_category(id)
);

CREATE TABLE `order` (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         order_number INT,
                         is_completed BOOLEAN,
                         waiter_user_id INT,
                         id_restaurant_table INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         created_by varchar(50),
                         updated_by varchar(50),
                         its_soft_deleted BOOLEAN DEFAULT FALSE,
                         FOREIGN KEY (waiter_user_id) REFERENCES user(id),
                         FOREIGN KEY (id_restaurant_table) REFERENCES restaurant_table(id)
);

CREATE TABLE order_item (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            product_id INT,
                            order_id INT,
                            price DOUBLE,
                            quantity INT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            created_by varchar(50),
                            updated_by varchar(50),
                            its_soft_deleted BOOLEAN DEFAULT FALSE,
                            FOREIGN KEY (product_id) REFERENCES product(id),
                            FOREIGN KEY (order_id) REFERENCES `order`(id)
);

INSERT INTO role_user (name, created_by)
VALUES ('Administrator', 'Alan Barrientos'),
       ('Bartender', 'Alan Barrientos'),
       ('Waiter', 'Alan Barrientos');


INSERT INTO user (first_name, last_name, email, role_id, created_by)
VALUES ('John', 'Doe', 'johndoe@hotmail.com', 3, 'Alan Barrientos'),
       ('Jane', 'Smith', 'janedoe@gmail.com', 3, 'Alan Barrientos'),
       ('Alejandro', 'Rojas', 'alejandror@gmail.com', 1, 'Alan Barrientos' ),
       ('Alan', 'Barrientos', 'alan@gmail.com', 1, 'Alan Barrientos'),
       ('Joel', 'Cantero', 'joel@gmail.com', 2, 'Ale Rojas');

INSERT INTO restaurant_table (table_number, seats, created_by)
VALUES (1, 4, 'Alan Barrientos'),
       (2, 2, 'Alejandro Rojas'),
       (3, 6, 'Alan Barrientos'),
       (4, 4, 'Alan Barrientos'),
       (5, 8, 'Alejandro Rojas');

INSERT INTO product_category (name, parent_category_id, created_by)
VALUES
    ('Meat', NULL, 'Alan Barrientos'),
    ('Veal', 1, 'Alejandro Rojas'),
    ('Pork', 1, 'Alan Barrientos'),
    ('Chicken', 1, 'Alan Barrientos'),
    ('Vegetables', NULL, 'Alan Barrientos'),
    ('Carrots', 5, 'Alejandro Rojas'),
    ('Cucumbers', 5, 'Alan Barrientos'),
    ('Spinach', 5, 'Alan Barrientos'),
    ('Alcohol', NULL, 'Alejandro Rojas'),
    ('Whisky', 9, 'Alan Barrientos'),
    ('Soft Drinks', NULL, 'Alan Barrientos'),
    ('Lemonade', 11, 'Alejandro Rojas');

INSERT INTO product (name, code, description, image_path, price, category_id, created_by)
VALUES
    ('Beef Tenderloin', 'BFT1', 'Premium cut of beef', 'images/beef_tenderloin.jpg', 25.99, 2, 'Alan Barrientos'),
    ('Pork Chop', 'PCH1', 'Bone-in pork chop, juicy and flavorful', 'images/pork_chop.jpg', 18.99, 3, 'Alejandro Rojas'),
    ('Grilled Chicken', 'GCB1', 'chicken breast', 'images/grilled_chicken.jpg', 14.99, 4, 'Alan Barrientos'),
    ('Carrots', 'CRT1', 'Fresh and crunchy carrots', 'images/carrots.jpg', 3.99, 6, 'Alan Barrientos'),
    ('Sanwitch', 'CUC1', 'Fresh', 'images/cucumber_salad.jpg', 6.99, 7, 'Alejandro Rojas'),
    ('Spinach Dip', 'SPD1', 'Creamy and delicious spinach dip', 'images/spinach_dip.jpg', 9.99, 8, 'Alan Barrientos'),
    ('Jack Daniel\'s Whiskey', 'JDW1', 'Whiskey, smooth and smoky', 'images/jack_daniels_whiskey.jpg', 35.99, 10, 'Alan Barrientos'),
    ('Coca Cola', 'COC1', 'Classic Coca Cola refreshing', 'images/coca_cola.jpg', 1.99, 12, 'Alejandro Rojas'),
    ('Sprite', 'SPT1', 'Lemon-lime flavored soda', 'images/sprite.jpg', 1.99, 12, 'Alan Barrientos');

INSERT INTO `order` (order_number, is_completed, waiter_user_id, id_restaurant_table, created_by)
VALUES
    (1, false, 3, 1, 'John Doe'),
    (2, true, 3, 3, 'John Doe'),
    (3, false, 2, 2, 'Jane Smith');

INSERT INTO order_item (product_id, order_id, price, quantity, created_by)
VALUES
    (1, 1, 25.99, 2, 'John Doe'),
    (2, 1, 18.99, 1, 'John Doe'),
    (4, 2, 3.99, 3, 'John Doe'),
    (5, 2, 6.99, 2, 'Jane Smith'),
    (6, 3, 9.99, 1, 'Jane Smith'),
    (8, 3, 35.99, 1, 'Jane Smith');

CREATE INDEX order_idx_created_at ON `order` (created_at);
CREATE INDEX order_idx_updated_at ON `order` (updated_at);
CREATE INDEX order_item_idx_created_at ON order_item (created_at);
CREATE INDEX order_item_idx_updated_at ON order_item (updated_at);
CREATE INDEX product_idx_created_at ON product (name);
CREATE INDEX product_idx_updated_at ON product (updated_at);

UPDATE product
SET its_soft_deleted = true,
    updated_by = 'Alan Barrientos'
WHERE id = 3;

UPDATE product
SET its_soft_deleted = true,
    updated_by = 'Alan Barrientos'
WHERE id = 7;

UPDATE restaurant_table
SET is_ocuppied = true,
    updated_by = 'Alan Barrientos'
WHERE table_number = 1;

UPDATE restaurant_table
SET is_ocuppied = true,
    updated_by = 'Alan Barrientos'
WHERE table_number = 2;