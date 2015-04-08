-- CREATE TABLE address (
--                 id SERIAL PRIMARY KEY,
--                 deleted BOOLEAN DEFAULT FALSE,
--                 country TEXT,
--                 city TEXT,
--                 address_line_1 TEXT,
--                 address_line_2 TEXT,
--                 zipcode TEXT
-- );
-- CREATE TABLE phone_number (
--                 phone_number TEXT,
--                 contact_info_id INT REFERENCES contact_info (id)
-- );
CREATE TABLE contact_info (
                id BIGSERIAL PRIMARY KEY,
                deleted BOOLEAN DEFAULT FALSE,
                email TEXT
);
CREATE TABLE customer (
                id BIGSERIAL PRIMARY KEY,
                deleted BOOLEAN DEFAULT FALSE,
                first_name TEXT,
                last_name VARCHAR(25),
                contact_info_id INT REFERENCES contact_info (id),
                password VARCHAR(25),
                birth_date DATE
);