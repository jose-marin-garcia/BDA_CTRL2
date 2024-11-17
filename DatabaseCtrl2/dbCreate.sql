-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS "ctrl2bda";

-- Crear la base de datos
CREATE DATABASE "ctrl2bda"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Chile.1252'
    LC_CTYPE = 'Spanish_Chile.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    TEMPLATE template0;

-- Cambiar a la base de datos recién creada
\c ctrl2bda

-- Crear la tabla Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id BIGSERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL
);

-- Crear la tabla Tarea
CREATE TABLE IF NOT EXISTS Tarea (
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

-- Crear la tabla Notificacion
CREATE TABLE IF NOT EXISTS Notificacion (
    id BIGSERIAL PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_tarea BIGINT NOT NULL,
    fechaEnvio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_tarea) REFERENCES Tarea(id) ON DELETE CASCADE
);

-- Crear índices
CREATE INDEX IF NOT EXISTS idx_usuario_username ON Usuario(id);
CREATE INDEX IF NOT EXISTS idx_tarea_usuarioId ON Tarea(id_usuario);
CREATE INDEX IF NOT EXISTS idx_notificacion_usuarioId ON Notificacion(id_usuario);
CREATE INDEX IF NOT EXISTS idx_notificacion_tareaId ON Notificacion(id_tarea);