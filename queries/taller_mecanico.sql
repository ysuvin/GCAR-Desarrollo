insert into esquemas values
('18851998','taller_mecanico',(select current_timestamp),true);

create schema taller_mecanico_18851998;

-- Creación de la tabla mecanico
CREATE TABLE taller_mecanico_18851998.mecanico (
    CI INT PRIMARY KEY,
    nombre VARCHAR(50),
    cargo VARCHAR(50)
);

-- Inserción de datos en la tabla mecanico
INSERT INTO taller_mecanico_18851998.mecanico VALUES
    (486, 'Juan', 'Llantas'),
    (399, 'Pedro', 'Motor'),
    (125, 'Federico', 'Gerente');

-- Creación de la tabla vehiculo
CREATE TABLE taller_mecanico_18851998.vehiculo (
    placa INT PRIMARY KEY,
    descripcion VARCHAR(50),
    marca VARCHAR(50),
    modelo VARCHAR(50)
);

-- Inserción de datos en la tabla vehiculo
INSERT INTO taller_mecanico_18851998.vehiculo VALUES
    (55667, 'Rojo', 'Toyota', 'Mustang'),
    (15554, 'Verde', 'Chevrolet', 'Nova'),
    (16653, 'Negro', 'Chevrolet', 'Spark'),
    (16654, 'Blanco', 'Chevrolet', 'Aveo'),
    (14456, 'Azul', 'Mitsubishi', 'Lancer');

-- Creación de la tabla servicio
CREATE TABLE taller_mecanico_18851998.servicio (
    codigo INT PRIMARY KEY,
    nombre VARCHAR(50),
    tipo VARCHAR(50),
    precio NUMERIC(10, 2)
);

-- Inserción de datos en la tabla servicio
INSERT INTO taller_mecanico_18851998.servicio VALUES
    (1, 'Cauchos', 'Cambio', 150),
    (2, 'Motor', 'Cambio de Aceite', 90),
    (3, 'Motor', 'Reparacion', 250),
    (4, 'Mantenimiento', 'Limpieza', 60),
    (5, 'Latoneria', 'Externo', 200),
    (6, 'Pintura', 'Externo', 185);

-- Creación de la tabla reparacion
CREATE TABLE taller_mecanico_18851998.reparacion (
    placa INT,
    CI INT,
    codigo INT,
    fecha_rep DATE,
    horas_tra INT
);

-- Inserción de datos en la tabla reparacion
INSERT INTO taller_mecanico_18851998.reparacion VALUES
    (15554, 399, 1, '2016-02-16', 6),
    (15554, 399, 2, '2016-02-17', 5),
    (15554, 399, 3, '2016-02-18', 7),
    (15554, 399, 4, '2016-02-19', 3),
    (15554, 399, 5, '2016-02-20', 8),
    (15554, 399, 6, '2016-02-21', 5),
    (16653, 486, 1, '2013-03-05', 6),
    (16653, 486, 2, '2014-03-05', 5),
    (16653, 486, 3, '2015-03-05', 4),
    (16653, 486, 4, '2016-03-05', 8),
    (16653, 486, 5, '2013-03-06', 7),
    (16653, 486, 6, '2013-03-07', 6),
    (16654, 486, 5, '2013-03-08', 5),
    (55667, 125, 5, '2015-02-09', 6),
    (55667, 125, 6, '2015-02-10', 6),
    (14456, 125, 5, '2016-02-09', 6),
    (14456, 125, 6, '2016-02-10', 6);

-- Esta tabla es obligación incluirla, Permite la creación de ejercicios para esta BD dentro de la aplicación
create table taller_mecanico_18851998._resp(
	id int,
	pregunta text,
	relacion varchar(60),
	consultas text,
	primary key(id)
	);