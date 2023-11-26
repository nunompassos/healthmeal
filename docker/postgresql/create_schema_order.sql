CREATE SCHEMA IF NOT EXISTS "order";

CREATE USER order_user WITH PASSWORD 'order_password';

GRANT ALL ON SCHEMA "order" TO order_user;
GRANT ALL ON SCHEMA "order" TO postgres;

ALTER ROLE order_user SET search_path TO "order";
