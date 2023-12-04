-- Esta primera query es obligación
insert into esquemas values
('18851998','empleados_sv2',(select current_timestamp),true);
-- rut del creador, nombre de la BD, fecha de creación (no se modifica), visibilidad de la base de datos
--El rut del usuario que creea la base de datos, debe existir dentro de la tabla de usuarios


-- aqui se crea el nombre de la base de datos, compuesta por el nombre dela bd y el rut del creador
create schema empleados_sv2_18851998;

-- Desde aqui todo el contenido de la Base de Datos, Creación de las tablas e ingreso de Datos
-- Siga el mismo formato para crear tabla e insertar datos
-- donde: empleados_sv2_18851998.trabajador 
-- empleados_sv2 es el nombre de la BD
-- _18851998 el rut del usuario creador
-- .trabajador el nombre de la tabla
create table empleados_sv2_18851998.trabajador(
	id_trabajador int,
	nom_trabajador text,
	tarifa_sem int,
	oficio text,
	id_supervisor int,
	primary key(id_trabajador)
	);

insert into empleados_sv2_18851998.trabajador values
	(1,'Andres',100,'electricista',11),
	(2, 'Pedro',100,'electricista',11),
	(3,'Josefa',200,'guardia',12),
	(4,'esteban',200,'conserje',12),
	(5,'Romina',150,'conserje', 12);

create table empleados_sv2_18851998.edificio(
	id_edificio int,
	dir_edificio text,
	tipo text,
	nivel_calidad int,
	categoria int,
	primary key(id_edificio)
	);

insert into empleados_sv2_18851998.edificio values
	(2000,'Francia 124','oficina',2,3),
	(1000,'Francia 125','negocio',1,4);

create table empleados_sv2_18851998.asignacion(
	id_asignacion int,
	id_trabajador1 int,
	id_edificio1 int,
	fecha_inicio text,
	primary key(id_asignacion)
	);

insert into empleados_sv2_18851998.asignacion values
	(1,1,5432,'2020-01-01'),
	(2,2,5432,'2020-02-01'),
	(3,3,2000,'2019-12-01'),
	(4,4,2000,'2019-11-01'),
	(5,3,1000,'2019-11-01');

-- Esta tabla es obligación incluirla, Permite la creación de ejercicios para esta BD dentro de la aplicación
create table empleados_sv2_18851998._resp(
	id int,
	pregunta text,
	relacion varchar(60),
	consultas text,
	primary key(id)
	);