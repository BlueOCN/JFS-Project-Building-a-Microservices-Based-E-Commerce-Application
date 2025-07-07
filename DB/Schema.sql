CREATE TABLE Profile (
    profile_id     BIGINT PRIMARY KEY,
    first_name     VARCHAR(100) NOT NULL,
    last_name      VARCHAR(100) NOT NULL,
    email          VARCHAR(255) NOT NULL UNIQUE,
    created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Cart (
    cart_id         BIGINT PRIMARY KEY,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_viewed_at  TIMESTAMP,
    profile_id      BIGINT,
    CONSTRAINT fk_cart_profile FOREIGN KEY (profile_id) REFERENCES Profile(profile_id)
        ON DELETE SET NULL
);

CREATE TABLE Product (
    product_id    BIGINT PRIMARY KEY,
    name          VARCHAR(150) NOT NULL,
    description   TEXT,
    price         DECIMAL(10, 2) NOT NULL,
    stock         INT NOT NULL
);

CREATE TABLE CartProduct (
    cart_id     BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    quantity    INT NOT NULL CHECK (quantity > 0),
    PRIMARY KEY (cart_id, product_id),
    CONSTRAINT fk_cartproduct_cart FOREIGN KEY (cart_id) REFERENCES Cart(cart_id) ON DELETE CASCADE,
    CONSTRAINT fk_cartproduct_product FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

INSERT INTO profiles (profile_id, first_name, last_name, email, created_at, updated_at, user_id) VALUES
(1, 'Sofia', 'Ramirez', 'sofia.ramirez@example.com', NOW(), NOW(), 3),
(2, 'Carlos', 'Luna', 'carlos.luna@example.com', NOW(), NOW(), 5),
(3, 'Maria', 'Hernandez', 'maria.hernandez@example.com', NOW(), NOW(), 1),
(4, 'Luis', 'Garcia', 'luis.garcia@example.com', NOW(), NOW(), 2),
(5, 'Ana', 'Flores', 'ana.flores@example.com', NOW(), NOW(), 9),
(6, 'Jorge', 'Lopez', 'jorge.lopez@example.com', NOW(), NOW(), 4),
(7, 'Elena', 'Vargas', 'elena.vargas@example.com', NOW(), NOW(), 8),
(8, 'Pedro', 'Torres', 'pedro.torres@example.com', NOW(), NOW(), 7),
(9, 'Lucia', 'Castillo', 'lucia.castillo@example.com', NOW(), NOW(), 10),
(10, 'Diego', 'Martinez', 'diego.martinez@example.com', NOW(), NOW(), 6);

INSERT INTO carts (cart_id, created_at, updated_at, last_viewed_at, profile_id) VALUES
(1001, NOW(), NOW(), NOW(), 1),
(1002, NOW(), NOW(), NOW(), 2),
(1003, NOW(), NOW(), NOW(), 3),
(1004, NOW(), NOW(), NOW(), 4),
(1005, NOW(), NOW(), NOW(), 5),
(1006, NOW(), NOW(), NOW(), 6),
(1007, NOW(), NOW(), NOW(), 7),
(1008, NOW(), NOW(), NOW(), 8),
(1009, NOW(), NOW(), NOW(), 9),
(1010, NOW(), NOW(), NOW(), 10);

INSERT INTO products (product_id, product_name, product_description, product_price, product_stock) VALUES
(2001, 'Wireless Mouse', 'Ergonomic wireless mouse with USB receiver', 25.99, 120),
(2002, 'Laptop Stand', 'Adjustable aluminum laptop stand for better posture', 45.50, 80),
(2003, 'Mechanical Keyboard', 'RGB backlit mechanical keyboard with blue switches', 89.90, 60),
(2004, 'Noise Cancelling Headphones', 'Over-ear headphones with active noise cancellation', 129.99, 45),
(2005, 'Webcam HD', '1080p webcam with built-in microphone', 49.95, 70),
(2006, 'USB-C Hub', '7-in-1 USB-C hub with HDMI and card reader', 34.75, 95),
(2007, 'Gaming Chair', 'Reclining ergonomic gaming chair with lumbar support', 199.00, 25),
(2008, 'External SSD 1TB', 'High-speed 1TB portable external SSD', 149.00, 50),
(2009, 'Smartwatch', 'Fitness-tracking smartwatch with heart-rate monitor', 119.49, 40),
(2010, 'Bluetooth Speaker', 'Water-resistant Bluetooth speaker with 360° sound', 59.99, 100);

INSERT INTO cart_product (cart_id, product_id, quantity) VALUES
(1001, 2001, 2),
(1001, 2002, 1),
(1002, 2005, 1),
(1003, 2003, 1),
(1003, 2004, 1),
(1004, 2006, 2),
(1005, 2008, 1),
(1006, 2007, 1),
(1007, 2010, 3),
(1008, 2009, 1),
(1009, 2002, 2),
(1010, 2001, 1);

INSERT INTO ecdbtest3.users (id, enabled, username, ecdbtest3.users.password) VALUES
(1, 1, 'mariaher', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(2, 1, 'luisgar', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(3, 1, 'sofiaram', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(4, 1, 'jorgelop', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(5, 1, 'carloslun', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(6, 1, 'diegomar', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(7, 1, 'pedrotor', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(8, 1, 'elenavar', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(9, 1, 'anaflo', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq'),
(10, 1, 'luciacas', '$2a$10$F1UsBI7b4qfUFdqxzSyHyecGU8q1cS5K./CUYOSNFHw9ELvJNxlYq');

INSERT INTO ecdbtest3.authorities (id, authority, username) VALUES
(1, 'ROLE_CUSTOMER', 'mariaher'),
(2, 'ROLE_CUSTOMER', 'luisgar'),
(3, 'ROLE_CUSTOMER', 'sofiaram'),
(4, 'ROLE_CUSTOMER', 'jorgelop'),
(5, 'ROLE_CUSTOMER', 'carloslun'),
(6, 'ROLE_CUSTOMER', 'diegomar'),
(7, 'ROLE_CUSTOMER', 'pedrotor'),
(8, 'ROLE_ADMIN', 'elenavar'),
(9, 'ROLE_ADMIN', 'anaflo'),
(10, 'ROLE_ADMIN', 'luciacas');
