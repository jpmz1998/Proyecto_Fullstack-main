USE director_service;
DROP TABLE IF EXISTS director;
CREATE TABLE director (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    apellido VARCHAR(25) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    nacionalidad VARCHAR(25) NOT NULL
);

