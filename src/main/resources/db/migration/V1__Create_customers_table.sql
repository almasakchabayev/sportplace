CREATE TABLE customers (
                id SERIAL PRIMARY KEY,
                deleted BOOLEAN DEFAULT FALSE,
                first_name VARCHAR(25),
                last_name VARCHAR(25),
                contact_info_id INTEGER,
                password VARCHAR(25),
                birth_date DATE
);