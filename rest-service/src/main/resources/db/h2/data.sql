INSERT INTO orders (checkout_date_time, status)
VALUES ('2024-12-06 12:30:00', 'ACCEPTED');
INSERT INTO orders (checkout_date_time, status)
VALUES ('2024-12-03 14:40:00', 'ACCEPTED');

INSERT INTO item (name, quantity, total_price, unit_price, order_id, dtype)
VALUES
    ('Cupcake', 3, 900, 300, 1, 'LineItem'),
    ('Cinnamon roll', 1, 200, 200, 1, 'LineItem'),
    ('Scone', 1, 200, 200, 1, 'LineItem');

INSERT INTO item (name, quantity, total_price, unit_price, order_id, dtype)
VALUES
    ('Black Forest Cake', 1, 10440, 10440, 2, 'LineItem'),
    ('Baklava', 5,  250, 50, 2, 'LineItem');