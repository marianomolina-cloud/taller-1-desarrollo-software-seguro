-- Creación de la base de datos (SQLite no usa CREATE DATABASE, se crea por el archivo)

-- Tabla departamentos
CREATE TABLE IF NOT EXISTS departamentos (
    id_departamento INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_departamento VARCHAR(100) NOT NULL
);

-- Tabla cargos
CREATE TABLE IF NOT EXISTS cargos (
    id_cargo INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_cargo VARCHAR(100) NOT NULL,
    salario_base DECIMAL(12,2) NOT NULL
);

-- Tabla funcionarios
CREATE TABLE IF NOT EXISTS funcionarios (
    id_funcionario INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    documento VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    id_cargo INTEGER NOT NULL,
    id_departamento INTEGER NOT NULL,
    FOREIGN KEY (id_cargo) REFERENCES cargos(id_cargo),
    FOREIGN KEY (id_departamento) REFERENCES departamentos(id_departamento)
);
