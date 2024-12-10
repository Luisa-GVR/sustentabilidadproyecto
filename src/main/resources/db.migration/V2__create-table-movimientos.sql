CREATE TABLE movimientos (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    movimiento VARCHAR(255) NOT NULL
);