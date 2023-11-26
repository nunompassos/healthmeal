CREATE TABLE IF NOT EXISTS product(
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    calories INT NOT NULL,

    CONSTRAINT product_pkey PRIMARY KEY(id),
    CONSTRAINT product_name_dish_type_key UNIQUE(name, type)
);

GRANT SELECT ON TABLE product TO postgres;
