-- #development #IDEA
-- Make sure your connection now
-- is to database "hibernate" !

CREATE SCHEMA IF NOT EXISTS public;
SET SEARCH_PATH TO public;

CREATE TABLE IF NOT EXISTS customer (
  id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('customer_id_seq'::regclass),
  lastname CHARACTER VARYING(255),
  name CHARACTER VARYING(255),
  phone CHARACTER VARYING(255)
);


CREATE TABLE IF NOT EXISTS customer_role (
  customer_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (customer_id, role_id),
  FOREIGN KEY (customer_id) REFERENCES public.customer (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (role_id) REFERENCES public.role (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS order2 (
  id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('order_id_seq'::regclass),
  date TIMESTAMP WITHOUT TIME ZONE,
  price NUMERIC(19,2),
  customer_id BIGINT,
  status_id BIGINT,
  FOREIGN KEY (status_id) REFERENCES public.status_orders (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (customer_id) REFERENCES public.customer (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS orders_items (
  id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('orders_items_id_seq'::regclass),
  qnt INTEGER,
  order_id BIGINT,
  product_id BIGINT,
  FOREIGN KEY (order_id) REFERENCES public.order2 (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (product_id) REFERENCES public.product (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS product (
  id BIGINT PRIMARY KEY NOT NULL,
  description CHARACTER VARYING(255) NOT NULL,
  price NUMERIC(19,2) NOT NULL,
  title CHARACTER VARYING(16) NOT NULL,
  product_category_id BIGINT NOT NULL,
  FOREIGN KEY (product_category_id) REFERENCES public.product_category (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS product_category (
  id BIGINT PRIMARY KEY NOT NULL,
  title CHARACTER VARYING(255)
);

CREATE TABLE IF NOT EXISTS role (
  id BIGINT PRIMARY KEY NOT NULL,
  title CHARACTER VARYING(255)
);

CREATE TABLE IF NOT EXISTS status_orders (
  id BIGINT PRIMARY KEY NOT NULL,
  description CHARACTER VARYING(255)
);