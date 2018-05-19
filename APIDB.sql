
CREATE DATABASE IF NOT EXISTS demo_api;

use demo_api;

CREATE TABLE users 
	(id INT AUTO_INCREMENT, email VARCHAR(40), username VARCHAR(30), password VARCHAR(30),
		PRIMARY KEY(id));
CREATE TABLE carts
	(id INT AUTO_INCREMENT, total_price INT, fk_user_id INT,
		PRIMARY KEY(id), FOREIGN KEY(fk_user_id) REFERENCES users(id));
CREATE TABLE products
	(id INT AUTO_INCREMENT, name VARCHAR(40), price FLOAT, stock INT,
		PRIMARY KEY(id));
CREATE TABLE categories
	(id INT AUTO_INCREMENT, name VARCHAR(30), PRIMARY KEY(id));
CREATE TABLE product_lines
	(pfk_product_id INT, pfk_cart_id INT,
		PRIMARY KEY (pfk_product_id,pfk_cart_id),
		FOREIGN KEY(pfk_product_id) REFERENCES products(id),
		FOREIGN KEY(pfk_cart_id) REFERENCES carts(id));
CREATE TABLE categories_x_products
	(pfk_product_id INT, pfk_category_id INT,
			PRIMARY KEY (pfk_product_id,pfk_category_id),
			FOREIGN KEY(pfk_product_id) REFERENCES products(id),
			FOREIGN KEY(pfk_category_id) REFERENCES categories(id));
CREATE TABLE statuses
	(id INT AUTO_INCREMENT, name VARCHAR(30), PRIMARY KEY(id));
CREATE TABLE payment_methods
	(id INT AUTO_INCREMENT, name VARCHAR(30), PRIMARY KEY(id));
CREATE TABLE deliver_methods
	(id INT AUTO_INCREMENT, name VARCHAR(30), PRIMARY KEY(id));
CREATE TABLE purchases
	(id INT AUTO_INCREMENT, final_price INT,
		fk_cart_id INT, fk_status_id INT, fk_payment_method_id INT, fk_deliver_method_id INT,
		PRIMARY KEY(id),
		FOREIGN KEY(fk_cart_id) REFERENCES carts(id),
		FOREIGN KEY(fk_status_id) REFERENCES statuses(id),
		FOREIGN KEY(fk_payment_method_id) REFERENCES payment_methods(id),
		FOREIGN KEY(fk_deliver_method_id) REFERENCES deliver_methods(id));