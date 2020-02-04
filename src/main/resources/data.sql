insert into client values (1, 'some name 1');
insert into client values (2, 'some name 2');
insert into client values (3, 'some name 3');
insert into client values (4, 'some name 4');
insert into client values (5, 'some name 5');

insert into product values (1, 'some product 1', 1.00);
insert into product values (2, 'some product 2', 2.00);
insert into product values (3, 'some product 3', 1.15);
insert into product values (4, 'some product 4', 1.25);
insert into product values (5, 'some product 5', 2.50);

insert into request (id, date, description, total_value, client_id) values (1, '2019-08-16', 'some order 1', 3.00, 3);
insert into request (id, date, description, total_value, client_id) values (2, '2019-09-09', 'some order 2', 2.00, 2);
insert into request (id, date, description, total_value, client_id) values (3, '2019-12-25', 'some order 3', 2.40, 5);
insert into request (id, date, description, total_value, client_id) values (4, '2020-01-01', 'some order 4', 2.50, 1);
insert into request (id, date, description, total_value, client_id) values (5, '2020-02-01', 'some order 5', 5.50, 4);

insert into request_details (id, quantity, value, order_id, product_id) values (1, 1, 1.00, 1, 1);
insert into request_details (id, quantity, value, order_id, product_id) values (2, 1, 2.00, 1, 2);
insert into request_details (id, quantity, value, order_id, product_id) values (3, 1, 2.00, 2, 2);
insert into request_details (id, quantity, value, order_id, product_id) values (4, 1, 1.15, 3, 3);
insert into request_details (id, quantity, value, order_id, product_id) values (5, 1, 1.25, 3, 4);
insert into request_details (id, quantity, value, order_id, product_id) values (6, 1, 2.50, 4, 5);
insert into request_details (id, quantity, value, order_id, product_id) values (7, 1, 1.00, 5, 1);
insert into request_details (id, quantity, value, order_id, product_id) values (8, 1, 2.00, 5, 2);
insert into request_details (id, quantity, value, order_id, product_id) values (9, 1, 2.50, 5, 5);

