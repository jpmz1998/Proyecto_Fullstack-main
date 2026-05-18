USE suscripciones_service;
DROP TABLE IF EXISTS suscripcion;
CREATE TABLE suscripcion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_plan VARCHAR(50) NOT NULL,
    monto INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    usuario_id BIGINT NOT NULL
);