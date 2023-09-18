-- src/main/resources/data.sql

CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO customer (id,name) VALUES (1,'Dave');
INSERT INTO customer (id, name) VALUES (2, 'Devika');
