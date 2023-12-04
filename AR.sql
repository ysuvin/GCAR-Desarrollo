create table usuarios(
	rut varchar(45),
	pass varchar(255),
	nombre1 varchar(45),
	nombre2 varchar(45),
	paterno varchar(45),
	materno varchar(45),
	mail varchar(45),
	tipo int,
	is_hashed boolean,
	primary key(rut)
	);

insert into usuarios values 
('11111111','pass','Nombre1','Nombre2','Paterno','Materno','alumno@mail.com',2,false),
('22222222','pass','Nombre1','Nombre2','Paterno','Materno','alumno@mail.com',2,false),
('33333333','pass','Nombre1','Nombre2','Paterno','Materno','alumno@mail.com',2,false),
('17134298','85638020','Sebastian','Andres','Segovia','Cordero','s.segovia.c@gmail.com',1,false),
('18851998','12345678','Yian','Suvin','Vera','Soto','yian.vera@gmail.com',1,false),
('44444444','12345678','Profesor','nombre 2','Paterno','Materno','profebd@uv.cl',1,false),
('11112222','12345678','Eliana','Eliana','Providel','Godoy','eliana.providel@uv.cl',1,false),
('33334444','12345678','Marcelo','Marcelo','Estay','Estay','marcelo.estay@alumnos.uv.cl',1,false),
('22333444','t1','Yian2','Suvin2','Vera2','Soto2','yian.vera2@gmail.com',3,false);
------------------------------
create table alumnos(
	rut varchar(45),
	ano_ingreso varchar(45),
	edad varchar(45),
	primary key(rut),
	foreign key(rut) references usuarios(rut) on delete cascade on update cascade
	);
	
insert into alumnos values
('11111111','2017','1998'),
('22222222','2009','1996'),
('33333333','2019','2000');
-----------------------------------
	
create table profesores(
	rut varchar(45),
	ano_curso varchar(45),
	primary key(rut),
	foreign key(rut) references usuarios(rut) on delete cascade on update cascade
	);

insert into profesores values
('11111111','2014'),
('44444444','2014'),
('17134298','2014'),
('11112222','2014'),
('33334444','2017'),
('18851998','2016');
-----------------------------------------
create table ayudantes(
	rut varchar(45),
	ano_ingreso varchar(45),
	primary key(rut),
	foreign key(rut) references usuarios(rut) on delete cascade on update cascade
	);
	
insert into ayudantes values
('22333444','2017');



-----------------------------------------

create table interacciones(

	id varchar(45),
	rut varchar (45),
	id_ejercicio int,
	respuesta_correctas boolean,
	respuesta_incorrectas boolean,
	respuesta_omitidas boolean,
	tiempo_respuesta time,
	primary key(id)
);
	
insert into interacciones values
('1','11111111','1','1','0','0','00:05:20'),
('2','11111111','2','0','1','0','00:03:20'),
('3','11111111','3','1','0','0','00:01:20'),
('4','11111111','4','0','0','1','00:02:14'),
('5','11111111','5','1','0','0','00:01:10'),

('6','22222222','1','1','0','0','00:01:30'),
('7','22222222','2','0','1','0','00:02:40'),
('8','22222222','3','1','0','0','00:01:03'),
('9','22222222','4','0','0','1','00:00:60'),
('10','22222222','5','0','1','0','00:03:20'),

('11','33333333','1','0','1','0','00:04:20'),
('12','33333333','2','1','0','0','00:03:20'),
('13','33333333','3','0','1','0','00:02:22'),
('14','33333333','4','1','0','0','00:03:30'),
('15','33333333','5','1','0','0','00:01:20'),

('16','44444444','1','1','0','0','00:03:20'),
('17','44444444','2','1','0','0','00:02:22'),
('18','44444444','3','1','0','0','00:01:20'),
('19','44444444','4','1','0','0','00:02:25'),
('20','44444444','5','1','0','0','00:03:20'),

('21','55555555','1','1','0','0','00:04:27'),
('22','55555555','2','0','1','0','00:01:23'),
('23','55555555','3','0','1','0','00:03:20'),
('24','55555555','4','0','1','0','00:01:25'),
('25','55555555','5','1','0','0','00:02:13');



-----------------------------------------------------	
create table esquemas(
	rut varchar(45),
	nombre varchar(45),
	fecha timestamp,
	visible boolean,
	primary key(rut,nombre),
	foreign key(rut) references usuarios(rut) on delete cascade on update cascade
	);


insert into esquemas values
('17134298','ejemplo',(select current_timestamp),true);

insert into esquemas values
('18851998','empleados_sv',(select current_timestamp),true);

--Esquema ejemplo
create schema ejemplo_17134298;

create table ejemplo_17134298.edificios_emp(
	edificio text,
	supmediadesp int,
	primary key(edificio)
	);

insert into ejemplo_17134298.edificios_emp values
	('Marina',15),
	('Diagonal',10);

create table ejemplo_17134298.despachos(
	edificio text,
	numero int,
	superficie int,
	primary key(edificio,numero)
	);

insert into ejemplo_17134298.despachos values
	('Marina',120,10),
	('Marina',230,20),
	('Diagonal',120,10),
	('Diagonal',440,10);

create table ejemplo_17134298.empleados_adm(
	id int,
	nombre text,
	apellido text,
	edificiodesp text,
	numerodesp int,
	primary key(id)
	);

insert into ejemplo_17134298.empleados_adm values
	(40444255,'Juan','Garcia','Marina',120),
	(33567711,'Marta','Roca','Marina',120);

create table ejemplo_17134298.empleados_prod(
	id int,
	nombreemp text,
	apellidoemp text,
	edificiodesp text,
	numerodesp int,
	primary key(id)
	);

insert into ejemplo_17134298.empleados_prod values
	(33567711,'Marta','Roca','Marina',120),
	(55898425,'Carlos','Buendia','Diagonal',120),
	(77232144,'Elena','Pla','Marina',230),
	(21335245,'Jorge','Soler',null,null),
	(88999210,'Pedro','Gonzalez',null,null);

create table ejemplo_17134298._resp(
	id int,
	pregunta text,
	relacion varchar(60),
	consultas text,
	primary key(id)
	);
----------------- ejemplo Division	
---tabla R
create table ejemplo_17134298.R(
	Y varchar(5),
	X varchar(5)
	);	
insert into ejemplo_17134298.R values
	('s1','p1'),
	('s1','p2'),
	('s1','p3'),
	('s1','p4'),
	('s2','p1'),
	('s2','p2'),
	('s3','p2'),
	('s4','p2'),
	('s4','p4');
	
---tabla S
create table ejemplo_17134298.S(
	X varchar(5)
	);
insert into ejemplo_17134298.S values
	('p1'),
	('p2'),
	('p4');	
----------------fin schema 17134298---------------------------------

-----------schema o bd control2---------------------

create schema empleados_sv_18851998;

create table empleados_sv_18851998.trabajador(
	id_trabajador int,
	nom_trabajador text,
	tarifa_sem int,
	oficio text,
	id_supervisor int,
	primary key(id_trabajador)
	);

insert into empleados_sv_18851998.trabajador values
	(1,'Andres',100,'electricista',11),
	(2, 'Pedro',100,'electricista',11),
	(3,'Josefa',200,'guardia',12),
	(4,'esteban',200,'conserje',12),
	(5,'Romina',150,'conserje', 12);

create table empleados_sv_18851998.edificio(
	id_edificio int,
	dir_edificio text,
	tipo text,
	nivel_calidad int,
	categoria int,
	primary key(id_edificio)
	);

insert into empleados_sv_18851998.edificio values
	(2000,'Francia 124','oficina',2,3),
	(1000,'Francia 125','negocio',1,4);

create table empleados_sv_18851998.asignacion(
	id_asignacion int,
	id_trabajador1 int,
	id_edificio1 int,
	fecha_inicio text,
	primary key(id_asignacion)
	);

insert into empleados_sv_18851998.asignacion values
	(1,1,5432,'2020-01-01'),
	(2,2,5432,'2020-02-01'),
	(3,3,2000,'2019-12-01'),
	(4,4,2000,'2019-11-01'),
	(5,3,1000,'2019-11-01');

create table empleados_sv_18851998._resp(
	id int,
	pregunta text,
	relacion varchar(60),
	consultas text,
	primary key(id)
	);

--------------- Respuestas---------

create sequence sec_resultados;
	
create table resultados(
	id int default nextval('sec_resultados'),
	rut varchar(60),
	bd varchar(60),
	fecha_bd timestamp,
	cant_ejercicios int,
	cant_correctas int,
	cant_erroneas int,
	cant_omitidas int,
	fecha timestamp,
	primary key(id)
	);	
	
create sequence sec_respuestas;
	
create table respuestas(
	id int default nextval('sec_respuestas'),
	rut varchar(60),
	bd varchar(60),
	fecha_bd timestamp,
	ejercicio int,
	resultado boolean,
	intento int,
	tiempo_ejercicio time,
	tiempo_sesion time,
	fecha timestamp,
	primary key(id)
	);
	
create sequence sec_consultas;
	
create table consultas(
	id int default nextval('sec_consultas'),
	id_respuestas int,
	numero int,
	query text,
	error text,
	fecha timestamp,
	primary key(id),
	foreign key(id_respuestas) references respuestas(id) on delete cascade on update cascade
	);

create sequence sec_ejecutorsql;
create table ejecutor_sql(
	id int default nextval('sec_ejecutorsql'),
	rut VARCHAR(255) NOT NULL,
	bd VARCHAR(255) NOT NULL,
  	query_ejecutada TEXT,
  	clausulas_sql TEXT,
  	query_correcta BOOLEAN,
  	query_incorrecta BOOLEAN,
	clasificacion_error TEXT,
  	descripcion_error TEXT,
  	fecha_hora_ejecucion TIMESTAMP,
	primary key(id)
);

create sequence sec_ejecutorar;
create table ejecutor_ar(
	id int default nextval('sec_ejecutorar'),
	rut VARCHAR(255) NOT NULL,
	bd VARCHAR(255) NOT NULL,
  	query_ejecutada TEXT,
  	operador TEXT,
  	query_correcta BOOLEAN,
  	query_incorrecta BOOLEAN,
	clasificacion_error TEXT,
  	descripcion_error TEXT,
  	fecha_hora_ejecucion TIMESTAMP,
	primary key(id)
);	
	