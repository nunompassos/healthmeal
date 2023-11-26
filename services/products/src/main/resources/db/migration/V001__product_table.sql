CREATE TABLE IF NOT EXISTS product(
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    dish_type VARCHAR(255) NOT NULL,
    calories INT NOT NULL,

    CONSTRAINT pk_product PRIMARY KEY(id)
);
