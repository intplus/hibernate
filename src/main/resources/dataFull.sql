SET SEARCH_PATH TO public;

INSERT INTO status_orders VALUES (1, 'Новый заказ'),
  (2, 'Жду оплату'),
  (3, 'Оплачен'),
  (4, 'Нужен звонок'),
  (5, 'Не оплачен');

INSERT INTO product_category VALUES
  (1, 'ActiveCam'),
  (2, 'Servers Trassir'),
  (3, 'Hikvision');


INSERT INTO product VALUES
  (1, 'server Trassir', '530', 'ServerTrassir 16AF', '2'),
  (2, 'server Trassir', '430', 'MiniNVR 16AF', '2'),
  (3, 'video camera Hikvision', '73', 'DS-2CD2132-I', '3'),
  (4, 'video camera Hikvision', '53', 'DS-2CD2012-I', '3'),
  (5, 'video camera ActiveCam', '53', 'AC-D2141IR3', '1'),
  (6, 'video camera ActiveCam', '45', 'AC-D2101IR3', '1'),
  (7, 'video camera ActiveCam', '55', 'AC-D2121IR3', '1'),
  (8, 'video camera ActiveCam', '75', 'AC-D2421IR3', '1');

INSERT INTO customer VALUES
  (DEFAULT, 'petrov', 'ivan', '455666'),
  (DEFAULT, 'sidorov', 'petr', '5673'),
  (DEFAULT, 'ivankov', 'ostap', '87473'),
  (DEFAULT, 'stepanova', 'lena', '37281'),
  (DEFAULT, 'yasinskij', 'karl', '99872');

INSERT INTO order2 VALUES
  (DEFAULT, '2016-07-31 21:43:08.277939', '740', '1', '1'),
  (DEFAULT, '2016-07-31 21:44:08.277939', '490', '5', '2'),
  (DEFAULT, '2016-07-31 21:45:08.277939', '2580', '4', '3'),
  (DEFAULT, '2016-07-31 21:46:08.277939', '118', '3', '5'),
  (DEFAULT, '2016-07-31 21:47:08.277939', '1060', '2', '4'),
  (DEFAULT, '2016-07-31 21:48:08.277939', '915', '4', '5'),
  (DEFAULT, '2016-07-31 21:49:08.277939', '45', '3', '2'),
  (DEFAULT, '2016-07-31 21:50:08.277939', '150', '2', '1'),
  (DEFAULT, '2016-07-31 21:53:08.277939', '330', '1', '3'),
  (DEFAULT, '2016-07-31 21:55:08.277939', '663', '5', '5');


INSERT INTO orders_items VALUES
  (DEFAULT, '4', '1', '1'),
  (DEFAULT, '8', '1', '2'),
  (DEFAULT, '2', '2', '4'),
  (DEFAULT, '3', '2', '5'),
  (DEFAULT, '3', '2', '1'),
  (DEFAULT, '6', '3', '7'),
  (DEFAULT, '1', '4', '6'),
  (DEFAULT, '1', '4', '3'),
  (DEFAULT, '2', '5', '8'),
  (DEFAULT, '4', '6', '2'),
  (DEFAULT, '5', '6', '4'),
  (DEFAULT, '1', '6', '7'),
  (DEFAULT, '1', '7', '3'),
  (DEFAULT, '2', '8', '1'),
  (DEFAULT, '6', '9', '2'),
  (DEFAULT, '7', '10', '5'),
  (DEFAULT, '4', '10', '6');


