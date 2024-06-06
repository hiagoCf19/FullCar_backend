CREATE TABLE ads (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(500) NOT NULL,
    user_id INT NOT NULL,
    user_price NUMERIC(10, 2) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    code_fipe VARCHAR(20) NOT NULL,
    fuel VARCHAR(50) NOT NULL,
    model VARCHAR(255) NOT NULL,
    model_year CHAR(4) NOT NULL,
    fipe_price NUMERIC(10,2) NOT NULL,
    reference_month VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account(id)
)