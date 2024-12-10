CREATE TABLE Compuestos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    carburante BOOLEAN NOT NULL,
    corrosion BOOLEAN NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    explosivo BOOLEAN NOT NULL,
    inflamable BOOLEAN NOT NULL,
    medio_ambiente BOOLEAN NOT NULL,
    nombre_compuesto VARCHAR(255) NOT NULL,
    organico BOOLEAN NOT NULL,
    peligro_salud BOOLEAN NOT NULL,
    peso FLOAT NOT NULL,
    presion BOOLEAN NOT NULL,
    quimico_nocivo BOOLEAN NOT NULL,
    toxicidad BOOLEAN NOT NULL
);



CREATE TABLE movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    movimiento VARCHAR(255) NOT NULL
);



