CREATE TABLE Compuestos(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombreCompuesto VARCHAR(255) NOT NULL,
    organico BOOLEAN NOT NULL,
    peso double NOT NULL,
    explosivo BOOLEAN NOT NULL,
    inflamable BOOLEAN NOT NULL,
    carburante BOOLEAN NOT NULL,
    presion BOOLEAN NOT NULL,
    corrosion BOOLEAN NOT NULL,
    toxicidad BOOLEAN NOT NULL,
    quimicoNocivo BOOLEAN NOT NULL,
    medioAmbiente BOOLEAN NOT NULL,
    peligroSalud BOOLEAN NOT NULL,
    descripcion TEXT NULL
);
