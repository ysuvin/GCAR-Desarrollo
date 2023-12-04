Ver todas las tablas de un Esquema:
	select tablename from pg_tables where schemaname=NOMBRE_ESQUEMA;
	
Ver todos los esquemas;
	select distinct(schemaname) from pg_tables where tableowner='seba';
	
	SELECT n.nspname FROM pg_namespace n;
	
Ver atributos de tabla:
	select column_name, is_nullable, data_type 
	from information_schema.columns 
	where table_name='despachos' and table_schema='ejemplo' order by is_nullable;
  
select	result.id as "id_resultado", result.bd, result.fecha_bd, 
		result.cant_ejercicios, result.cant_correctas, result.cant_erroneas,
		result.cant_omitidas, result.fecha as "fecha_resultado",
		resp.id as "id_respuesta", resp.rut as "rut_alumno", resp.ejercicio, 
		resp.resultado as "resultado_ejercicio" , 
		resp.intento as "intento_ejercicio", resp.tiempo_ejercicio, 
		resp.tiempo_sesion, resp.fecha as "fecha_respuesta", 
		con.id as "id_consulta", con.numero as "numero_consulta",
		con.query, con.error as "error_query", con.fecha as "fecha_consulta"
from	resultados result, respuestas resp, consultas con
where	result.bd = resp.bd and
		result.fecha_bd = resp.fecha_bd and
		resp.id = con.id_respuestas