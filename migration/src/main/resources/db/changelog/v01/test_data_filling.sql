INSERT INTO images
values (1, 'file_extension', 'file_path', 10, 'media_type');
INSERT INTO address
values (2, 'building', 'city', 'street');
INSERT INTO payments
values (3, 5000, 'card_number');
INSERT INTO couriers
values (4, 22, '89200000001', 'ACTIVE');
INSERT INTO customers
values (5, 4, 'name', 2);
INSERT INTO restaurants
values (6, 5, 'name', 2, 3);
INSERT INTO restaurant_menu_items
values (7, 'description', 'name', 5000, 1, 6);
INSERT INTO orders
values (8, 'ACTIVE', '2023-10-10 17:31:53', 4, 5, 6);
INSERT INTO items
values (9, 'description', 'name', 5000, 5, 1, 8);
INSERT INTO delivery
values (10, 'ACCEPT', 5, 8, 3, 6);