USE restaurant;

# Products list - select all active products (paginated)
# OFFSET need to be variable when you make the query
SELECT *
FROM product
WHERE its_soft_deleted = false
ORDER BY id DESC
LIMIT 10 OFFSET 0;


# List of busy/occupied tables
SELECT *
FROM restaurant_table
WHERE is_ocuppied = TRUE;

# List of free tables
SELECT *
FROM restaurant_table
WHERE is_ocuppied = FALSE;

# List orders in progress
SELECT *
FROM `order`
WHERE is_completed = FALSE;

# List completed orders (paginated). Hint: Use of LIMIT and OFFSET
# OFFSET need to be variable when you make the query
SELECT *
FROM `order`
WHERE is_completed = TRUE
ORDER BY id DESC
LIMIT 10 OFFSET 0;

# Search by part of product name - select all matching products
# You can change the part of LIKEN '%anything%' and you will find what you want
SELECT *
FROM product
WHERE name LIKE '%chicken%';

# Select order details by order number - result should contain total price, waiter, date and time
SELECT SUM(oi.price) AS total_price, waiter_user_id, o.created_at
FROM `order` o
JOIN order_item oi on o.id = oi.order_id
WHERE o.order_number = 2
GROUP BY waiter_user_id, o.created_at;

# List of order products by order number - result should contain name, code, price
SELECT p.name, p.code, oi.price
FROM `order` o
JOIN order_item oi on o.id = oi.order_id
JOIN product p on oi.product_id = p.id
WHERE o.order_number = 2;


# List orders per period (start date and time, end date time) - result should contain order total and date
SELECT order_number, SUM(oi.price) AS total_amount, o.created_at
FROM `order` o
JOIN order_item oi on o.id = oi.order_id
WHERE o.created_at >= DATE('2023-05-10 00:00:00') AND o.created_at <= NOW()
GROUP BY o.id, o.created_at;



# List sold products for a given month - result should show aggregated quantity and aggregated price
# for all sales during the search period. Every matching product should exist once into the result.
SELECT p.name, SUM(oi.quantity) AS total_quantity, SUM(oi.price) AS total_price
FROM `order` o
JOIN order_item oi on o.id = oi.order_id
JOIN product p on oi.product_id = p.id
WHERE MONTH(o.created_at) = 5
GROUP BY p.name;
