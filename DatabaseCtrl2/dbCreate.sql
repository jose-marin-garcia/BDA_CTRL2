-------------------------------------------------------
-- Crear ctrl2bda
-- Database: ctrl2bda
-------------------------------------------------------
DROP DATABASE IF EXISTS "ctrl2bda";

CREATE DATABASE "ctrl2bda"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Chile.1252'
    LC_CTYPE = 'Spanish_Chile.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Creación de la tabla Usuario
CREATE TABLE Usuario (
    id BIGSERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100)
);

-- Creación de la tabla Tarea
CREATE TABLE Tarea (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fechaVencimiento DATE,
    estado VARCHAR(20) CHECK (estado IN ('pendiente', 'completada')) DEFAULT 'pendiente',
    fechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fechaCompletada TIMESTAMP,
    id_usuario BIGINT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE
);

-- Creación de la tabla Notificacion
CREATE TABLE Notificacion (
    id BIGSERIAL PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_tarea BIGINT NOT NULL,
    fechaEnvio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_tarea) REFERENCES Tarea(id) ON DELETE CASCADE
);

-- Índices para mejorar el rendimiento en búsquedas comunes
CREATE INDEX idx_usuario_username ON Usuario(id);
CREATE INDEX idx_tarea_usuarioId ON Tarea(id_usuario);
CREATE INDEX idx_notificacion_usuarioId ON Notificacion(id_usuario);
CREATE INDEX idx_notificacion_tareaId ON Notificacion(id_tarea);