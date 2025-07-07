INSERT INTO inventory (product_id, stock, unit_price) VALUES
  (456, 5, 100.00),
  (789, 2, 250.00),
  (321, 10, 50.00);

INSERT INTO orders (order_id, customer_id, total_amount, status, payment_method) VALUES
  (nextval('orders_order_id_seq'), 123, 450.00, 'PAID', 'CREDIT_CARD');

INSERT INTO order_items (id, order_id, product_id, quantity, unit_price) VALUES
  (nextval('order_items_id_seq'), 1, 456, 2, 100.00),
  (nextval('order_items_id_seq'), 1, 789, 1, 250.00);