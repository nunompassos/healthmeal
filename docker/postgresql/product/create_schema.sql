CREATE SCHEMA IF NOT EXISTS product;

CREATE USER product_user WITH PASSWORD 'product_password';

GRANT ALL ON SCHEMA product TO product_user;
GRANT ALL ON SCHEMA product TO postgres;

ALTER ROLE product_user SET search_path TO product;
