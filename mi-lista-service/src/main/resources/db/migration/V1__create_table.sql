USE mi_lista_service;
DROP TABLE IF EXISTS item_lista;
CREATE TABLE item_lista (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_pelicula BIGINT NOT NULL,
    fecha_agregado DATE NOT NULL
);