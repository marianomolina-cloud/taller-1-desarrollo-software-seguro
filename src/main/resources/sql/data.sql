-- Inserción en departamentos
INSERT INTO departamentos (nombre_departamento) VALUES 
('Talento Humano'),
('Tecnología'),
('Administrativo'),
('Financiero');

-- Inserción en cargos
INSERT INTO cargos (nombre_cargo, salario_base) VALUES 
('Gerente', 5000000.00),
('Analista', 2800000.00),
('Desarrollador', 3200000.00),
('Asistente', 2000000.00);

-- Inserción en funcionarios (datos de la imagen)
INSERT INTO funcionarios (nombre, apellido, documento, correo, telefono, id_cargo, id_departamento) VALUES 
('Juan', 'Pérez', '12345678', 'juan.perez@entidad.com', '3001234567', 1, 1),
('María', 'Gómez', '87654321', 'maria.gomez@entidad.com', '3012345678', 2, 2),
('Carlos', 'López', '11223344', 'carlos.lopez@entidad.com', '3023456789', 3, 2),
('Ana', 'Martínez', '44332211', 'ana.martinez@entidad.com', '3034567890', 4, 3);
