INSERT INTO brand (id, code, name) VALUES (1, 'ZA', 'ZARA');
INSERT INTO brand (id, code, name) VALUES (2, 'PAB', 'PULL AND BEAR');
INSERT INTO brand (id, code, name) VALUES (3, 'BE', 'BERSHKA');

INSERT INTO product (id, name, size) VALUES (35455, 't-shirt', 37);
INSERT INTO product (id, name, size) VALUES (35456, 'jeans', 40);
INSERT INTO product (id, name, size) VALUES (35457, 'belt', 5);

INSERT INTO price (id, start_date, end_date, price_list, product_id, priority, price, curr, brand_id) VALUES (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR', 1);
INSERT INTO price (id, start_date, end_date, price_list, product_id, priority, price, curr, brand_id) VALUES (2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 0, 25.45, 'EUR', 2);
INSERT INTO price (id, start_date, end_date, price_list, product_id, priority, price, curr, brand_id) VALUES (3, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 0, 30.50, 'EUR', 3);
INSERT INTO price (id, start_date, end_date, price_list, product_id, priority, price, curr, brand_id) VALUES (4, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 0, 38.95, 'EUR', 1);