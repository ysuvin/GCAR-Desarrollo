insert into esquemas values
('18851998','ventas_espana',(select current_timestamp),true);

create schema ventas_espana_18851998;

-- Creación de la tabla proveedores
CREATE TABLE ventas_espana_18851998.proveedores (
    P_id VARCHAR(5) PRIMARY KEY,
    PNOMBRE VARCHAR(50),
    CATEGORIA INT,
    CIUDAD VARCHAR(50)
);

-- Inserción de datos en la tabla proveedores
INSERT INTO ventas_espana_18851998.proveedores VALUES
    ('P1', 'CARLOS', 20, 'SEVILLA'),
    ('P2', 'JUAN', 10, 'MADRID'),
    ('P3', 'JOSE', 30, 'SEVILLA'),
    ('P4', 'INMA', 20, 'SEVILLA'),
    ('P5', 'EVA', 30, 'CACERES');

-- Creación de la tabla componentes
CREATE TABLE ventas_espana_18851998.componentes (
    C_id VARCHAR(5) PRIMARY KEY,
    CNOMBRE VARCHAR(50),
    COLOR VARCHAR(50),
    PESO INT,
    CIUDAD VARCHAR(50)
);

-- Inserción de datos en la tabla componentes
INSERT INTO ventas_espana_18851998.componentes VALUES
    ('C1', 'X3A', 'ROJO', 12, 'SEVILLA'),
    ('C2', 'B85', 'VERDE', 17, 'MADRID'),
    ('C3', 'C4B', 'AZUL', 17, 'MALAGA'),
    ('C4', 'C4B', 'ROJO', 14, 'SEVILLA'),
    ('C5', 'VT8', 'AZUL', 12, 'MADRID'),
    ('C6', 'C30', 'ROJO', 19, 'SEVILLA');

-- Creación de la tabla articulos
CREATE TABLE ventas_espana_18851998.articulos (
    T_id VARCHAR(5) PRIMARY KEY,
    TNOMBRE VARCHAR(50),
    CIUDAD VARCHAR(50)
);

-- Inserción de datos en la tabla articulos
INSERT INTO ventas_espana_18851998.articulos VALUES
    ('T1', 'CLASIFICADORA', 'MADRID'),
    ('T2', 'PERFORADORA', 'MALAGA'),
    ('T3', 'LECTORA', 'CACERES'),
    ('T4', 'CONSOLA', 'CACERES'),
    ('T5', 'MEZCLADORA', 'SEVILLA'),
    ('T6', 'TERMINAL', 'BARCELONA'),
    ('T7', 'CINTA', 'SEVILLA');

-- Creación de la tabla envios
CREATE TABLE ventas_espana_18851998.envios (
    P_id VARCHAR(5),
    C_id VARCHAR(5),
    T_id VARCHAR(5),
    CANTIDAD INT
);

-- Inserción de datos en la tabla envios
INSERT INTO ventas_espana_18851998.envios VALUES
    ('P1', 'C1', 'T1', 200),
    ('P1', 'C1', 'T4', 700),
    ('P2', 'C3', 'T1', 400),
    ('P2', 'C3', 'T2', 200),
    ('P2', 'C3', 'T3', 200),
    ('P2', 'C3', 'T4', 500),
    ('P2', 'C3', 'T5', 600),
    ('P2', 'C3', 'T6', 400),
    ('P2', 'C3', 'T7', 800),
    ('P2', 'C5', 'T2', 100),
    ('P3', 'C3', 'T1', 200),
    ('P3', 'C4', 'T2', 500),
    ('P4', 'C6', 'T3', 300),
    ('P4', 'C6', 'T7', 300),
    ('P5', 'C2', 'T2', 200),
    ('P5', 'C2', 'T4', 100),
    ('P5', 'C5', 'T4', 500),
    ('P5', 'C5', 'T7', 100),
    ('P5', 'C6', 'T2', 200),
    ('P5', 'C1', 'T4', 100),
    ('P5', 'C4', 'T4', 800),
    ('P5', 'C5', 'T5', 400),
    ('P5', 'C6', 'T4', 500);


create table ventas_espana_18851998._resp(
	id int,
	pregunta text,
	relacion varchar(60),
	consultas text,
	primary key(id)
	);