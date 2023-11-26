CREATE TABLE IF NOT EXISTS "user"(
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    total_meals INT NOT NULL DEFAULT 0,
    total_calories INT NOT NULL DEFAULT 0,

    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_name_key UNIQUE (name)
);

GRANT SELECT ON TABLE "user" TO postgres;
