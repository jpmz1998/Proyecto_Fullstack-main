USE recomendacion_service;
DROP TABLE IF EXISTS recomendacion;
CREATE TABLE recomendacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_pelicula BIGINT NOT NULL,
    mensaje_personalizado VARCHAR(500),
    nivel_confianza INT NOT NULL,
    fecha_recomendacion DATE NOT NULL
);