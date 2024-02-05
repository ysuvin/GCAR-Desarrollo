insert into esquemas values
('18851998','ventas_videojuegos',(select current_timestamp),true);

create schema ventas_videojuegos_18851998;

-- Creación de la tabla Juegos
CREATE TABLE ventas_videojuegos_18851998.juegos (
    Juego_ID SERIAL PRIMARY KEY,
    Titulo VARCHAR(100),
    Genero VARCHAR(50),
    Lanzamiento DATE,
    Desarrollador_ID INT,
    Plataforma_ID INT,
);

-- Creación de la tabla plataformas
CREATE TABLE ventas_videojuegos_18851998.plataformas (
    Plataforma_ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(50)
);

-- Creación de la tabla desarrolladores
CREATE TABLE ventas_videojuegos_18851998.desarrolladores (
    Desarrollador_ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Pais VARCHAR(50)
);

-- Creación de la tabla ventas
CREATE TABLE ventas_videojuegos_18851998.ventas (
    Venta_ID SERIAL PRIMARY KEY,
    Juego_ID INT,
    Ventas_totales INT,
    Ingresos NUMERIC(10, 2),
    Fecha_venta DATE,
);

-- Ejemplo de inserción de datos en la tabla plataformas
INSERT INTO ventas_videojuegos_18851998.plataformas VALUES
    ('PlayStation'),
    ('Xbox'),
    ('Nintendo Switch');

-- Ejemplo de inserción de datos en la tabla desarrolladores
INSERT INTO ventas_videojuegos_18851998.desarrolladores VALUES
    ('Naughty Dog', 'Estados Unidos'),
    ('Rockstar Games', 'Estados Unidos'),
    ('Nintendo', 'Japón');

-- Ejemplo de inserción de datos en la tabla Juegos
INSERT INTO ventas_videojuegos_18851998.juegos VALUES
    ('The Last of Us Part II', 'Acción y Aventura', '2020-06-19', 1, 1),
    ('Red Dead Redemption 2', 'Acción y Aventura', '2018-10-26', 2, 2),
    ('The Legend of Zelda: Breath of the Wild', 'Acción y Aventura', '2017-03-03', 3, 3);

-- Ejemplo de inserción de datos en la tabla ventas
INSERT INTO ventas_videojuegos_18851998.ventas VALUES
    (1, 3500000, 17500000.00, '2023-10-14'),
    (2, 7500000, 60000000.00, '2023-10-14'),
    (3, 8000000, 64000000.00, '2023-10-14'),
    (1, 2000000, 10000000.00, '2023-11-13'),
    (2, 5000000, 40000000.00, '2023-11-13'),
    (3, 4000000, 32000000.00, '2023-11-13'),
    (1, 1800000, 9000000.00, '2023-11-12'),
    (2, 4500000, 36000000.00, '2023-11-12'),
    (3, 5500000, 44000000.00, '2023-11-12'),
    (1, 3000000, 15000000.00, '2023-11-11'),
    (2, 6500000, 52000000.00, '2023-11-11'),
    (3, 7000000, 56000000.00, '2023-12-11'),
    (1, 4200000, 21000000.00, '2023-12-10'),
    (2, 8000000, 64000000.00, '2023-12-10'),
    (3, 6000000, 48000000.00, '2023-12-10');

create table ventas_videojuegos_18851998._resp(
	id int,
	pregunta text,
	relacion varchar(60),
	consultas text,
	primary key(id)
	);