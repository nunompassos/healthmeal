CREATE TABLE IF NOT EXISTS order_table(
    id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    entry VARCHAR(255) NULL,
    entry_calories INT NULL,
    main_course VARCHAR(255) NULL,
    main_course_calories INT NULL,
    beverage VARCHAR(255) NULL,
    beverage_calories INT NULL,

    CONSTRAINT order_pkey PRIMARY KEY (id)
);

GRANT SELECT ON TABLE order_table TO postgres;
