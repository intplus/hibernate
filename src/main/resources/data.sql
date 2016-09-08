SET SEARCH_PATH TO public;

INSERT INTO status_orders VALUES (DEFAULT , 'Новый заказ'),
  (DEFAULT, 'Жду оплату'),
  (DEFAULT, 'Оплачен'),
  (DEFAULT, 'Нужен звонок'),
  (DEFAULT, 'Не оплачен');

INSERT INTO product_category VALUES
  (DEFAULT, 'ActiveCam'),
  (DEFAULT, 'Servers Trassir'),
  (DEFAULT, 'Hikvision');


INSERT INTO product VALUES
  (DEFAULT, 'server Trassir', '530', 'ServerTrassir 16AF', '2'),
  (DEFAULT, 'server Trassir', '430', 'MiniNVR 16AF', '2'),
  (DEFAULT, 'video camera Hikvision', '73', 'DS-2CD2132-I', '3'),
  (DEFAULT, 'video camera Hikvision', '53', 'DS-2CD2012-I', '3'),
  (DEFAULT, 'video camera ActiveCam', '53', 'AC-D2141IR3', '1'),
  (DEFAULT, 'video camera ActiveCam', '45', 'AC-D2101IR3', '1'),
  (DEFAULT, 'video camera ActiveCam', '55', 'AC-D2121IR3', '1'),
  (DEFAULT, 'video camera ActiveCam', '75', 'AC-D2421IR3', '1');