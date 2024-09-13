CREATE TABLE images (
    id SERIAL PRIMARY KEY,
    url VARCHAR(2048) NOT NULL,
    ad_id INT NOT NULL,
    FOREIGN KEY (ad_id) REFERENCES ads(id) ON DELETE CASCADE
);