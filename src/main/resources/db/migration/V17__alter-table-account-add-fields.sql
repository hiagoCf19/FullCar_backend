ALTER TABLE account
    ADD COLUMN phone VARCHAR(20),
    ADD COLUMN cep NUMERIC(8) NOT NULL,
    ADD COLUMN street VARCHAR(255),
    ADD COLUMN neighborhood VARCHAR(255),
    ADD COLUMN city VARCHAR(255),
    ADD COLUMN uf VARCHAR(2);
