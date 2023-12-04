-- Esta query es para crear un usuario Profesor

insert into usuarios values 
('15151515','12345678','Nombre','segundoNombre','Paterno','Materno','profesor@mail.com',1);
-- rut del usuario, contraseña, nombre, segundo nombre, apellido P, apellido M, email, tipo de usuario (1 es para profesor)

insert into profesores values
('15151515','2014');
-- rut del usuario (usar el mismo anterior), año de ingreso del profesor