USE productora_service;
DROP TABLE IF EXISTS productora;
CREATE TABLE productora (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais_ubicacion VARCHAR(50) NOT NULL,
    anio_fundacion INT NOT NULL
);