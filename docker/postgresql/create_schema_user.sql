CREATE SCHEMA IF NOT EXISTS "user";

CREATE USER user_user WITH PASSWORD 'user_password';

GRANT ALL ON SCHEMA "user" TO user_user;
GRANT ALL ON SCHEMA "user" TO postgres;

ALTER ROLE user_user SET search_path TO "user";
