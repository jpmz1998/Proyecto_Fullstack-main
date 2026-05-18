--TABLA NUVEA Y FUNCIONAL (Se usan despues de crear ls BD en PhP en la seccion de sql)
CREATE TABLE pelicula (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    estreno DATE NOT NULL,
    genero VARCHAR(50) NOT NULL,
    descripcion VARCHAR(500),
    director_id BIGINT NOT NULL,
    productora_id BIGINT NOT NULL,
    duracion_minutos INT NOT NULL,
    es_para18 TINYINT(1) NOT NULL
);

CREATE TABLE pelicula ( -- script antiguo
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
     nombre VARCHAR(255),
     estreno DATE,
     genero VARCHAR(255),
     descripcion TEXT,
     director_id BIGINT,
     productora_id BIGINT,
     duracion_minutos INT,
     es_para_18 BOOLEAN
);