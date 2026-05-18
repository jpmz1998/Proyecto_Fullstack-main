DROP TABLE IF EXISTS calificacion; --script nuevo
CREATE TABLE calificacion (
    id_calificacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    puntos INT NOT NULL,
    fecha_calificacion DATE NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_pelicula BIGINT NOT NULL
);


