
CREATE TABLE IF NOT EXISTS orders (
                                      id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                      checkout_date_time TIMESTAMP,
                                      status VARCHAR(50) NOT NULL DEFAULT 'EMPTY',
                                      customer_name VARCHAR(255)
);

CREATE TABLE item (
                      id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      name VARCHAR(255),
                      quantity INT,
                      total_price INT,
                      unit_price INT,
                      order_id BIGINT,
                      dtype VARCHAR(50)
);

ALTER TABLE item ADD CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES orders(id);

CREATE INDEX idx_checkout_date_time ON orders(checkout_date_time);
