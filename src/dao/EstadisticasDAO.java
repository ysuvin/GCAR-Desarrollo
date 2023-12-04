package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Ejercicio;
import util.EsquemaBean;
import util.EstadisticaConsulta;
import util.EstadisticaRespuesta;
import util.EstadisticaResultado;
import util.EstadisticaTodo;
import util.EstadisticasEjecutorAR;
import util.EstadisticasEjecutorSQL;


//crear util para EJECUTORAR Y SQL



public class EstadisticasDAO {  //consulta las variables de la BD para enviarseal al bean de estadisticasview
								//AQUI AGREGAR LAS CONSULTAS A LAS TABLAS ejecutor_sql y ejecutor_ar PARA ENVIAR AL VIEW
								//penultimo paso

	static Connection con = null;
    static PreparedStatement ps = null;
    static Statement cs = null;
    
    public static List<EstadisticaConsulta> selectConsultas(){
    	try{
    		
    		String query = 	"select c.id, r.rut, c.numero, c.query, c.error, c.fecha " +
    						"from consultas c, respuestas r " + 
    						"where c.id_respuestas = r.id";
    		
    		System.out.println("Query " + query);
    		
    		con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticaConsulta> consultas = new ArrayList<EstadisticaConsulta>();
			
			while(rs.next()){
				EstadisticaConsulta consulta = new EstadisticaConsulta();
				consulta.setId(rs.getInt(1));
				consulta.setRut(rs.getString(2));
				consulta.setNumero(rs.getInt(3));
				consulta.setQuery(rs.getString(4));
				if(rs.getString(5) == null){
					consulta.setError("");
				}else{
					consulta.setError(rs.getString(5));
				}
				consulta.setFecha(rs.getString(6));
				consultas.add(consulta);
			}
			
			System.out.println("Carga lista de consultas");
			
			return consultas;			
	
    	} catch (Exception e){
    		System.out.println("Error selectConsulta() -> " + e.getMessage());
    		return null;
    	} finally {
    		Database.close(con);
    	}
    }
    
    public static List<EstadisticaRespuesta> selectRespuestas(){
    	try{
    		
    		String query = 	"select * from respuestas";
    		
    		System.out.println("Query " + query);
    		
    		con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticaRespuesta> respuestas = new ArrayList<EstadisticaRespuesta>();
			
			while(rs.next()){
				EstadisticaRespuesta respuesta = new EstadisticaRespuesta();
				respuesta.setId(rs.getInt(1));
				respuesta.setRut(rs.getString(2));
				respuesta.setBd(rs.getString(3));
				respuesta.setFechaBd(rs.getString(4));
				respuesta.setEjercicio(rs.getInt(5));
				respuesta.setResultado(rs.getBoolean(6));
				respuesta.setIntento(rs.getInt(7));
				respuesta.setTiempoEjercicio(rs.getString(8));
				respuesta.setTiempoSesion(rs.getString(9));
				respuesta.setFecha(rs.getString(10));
				respuestas.add(respuesta);
			}
			
			System.out.println("Carga lista de respuestas");
			
			return respuestas;			
	
    	} catch (Exception e){
    		System.out.println("Error selectRespuesta() -> " + e.getMessage());
    		return null;
    	} finally {
    		Database.close(con);
    	}
    }
    
    public static List<EstadisticaResultado> selectResultados(){
    	try{
    		
    		String query = 	"select * from resultados";
    		
    		System.out.println("Query " + query);
    		
    		con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticaResultado> resultados= new ArrayList<EstadisticaResultado>();
			
			while(rs.next()){
				EstadisticaResultado resultado = new EstadisticaResultado();
				resultado.setId(rs.getInt(1));
				resultado.setRut(rs.getString(2));
				resultado.setBd(rs.getString(3));
				resultado.setFechaBd(rs.getString(4));
				resultado.setCantEjercicios(rs.getInt(5));
				resultado.setCantCorrectas(rs.getInt(6));
				resultado.setCantErroneas(rs.getInt(7));
				resultado.setCantOmitidas(rs.getInt(8));
				resultado.setFecha(rs.getString(9));
				resultados.add(resultado);
			}
			
			System.out.println("Carga lista de respuestas");
			
			return resultados;			
	
    	} catch (Exception e){
    		System.out.println("Error selectResultado() -> " + e.getMessage());
    		return null;
    	} finally {
    		Database.close(con);
    	}
    }
    
    public static List<EstadisticaTodo> selectTodo(){
    	try{
    		
    		String query = 	"select	result.id as \"id_resultado\", result.rut, result.bd, result.fecha_bd, " +
    						"result.cant_ejercicios, result.cant_correctas, result.cant_erroneas, " +
				    		"result.cant_omitidas, result.fecha as \"fecha_resultado\", " +
				    		"resp.id as \"id_respuesta\", resp.rut as \"rut_alumno\", resp.ejercicio, " + 
				    		"resp.resultado as \"resultado_ejercicio\" , " + 
				    		"resp.intento as \"intento_ejercicio\", resp.tiempo_ejercicio, " + 
				    		"resp.tiempo_sesion, resp.fecha as \"fecha_respuesta\", " + 
				    		"con.id as \"id_consulta\", con.numero as \"numero_consulta\", " +
				    		"con.query, con.error as \"error_query\", con.fecha as \"fecha_consulta\" " +
		    				"from resultados result, respuestas resp, consultas con " +
		    				"where	result.bd = resp.bd and " +
				    		"result.fecha_bd = resp.fecha_bd and " +
				    		"resp.id = con.id_respuestas";
    		
    		System.out.println("Query " + query);
    		
    		con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			System.out.println("-----------Query EJECUTADA ");
			List<EstadisticaTodo> todos= new ArrayList<EstadisticaTodo>();
			
			while(rs.next()){
				EstadisticaTodo todo = new EstadisticaTodo();
				todo.setIdResultado(rs.getInt(1));
				todo.setRut(rs.getString(2));
				todo.setBd(rs.getString(3));
				todo.setFechaBd(rs.getString(4));
				todo.setCantEjercicios(rs.getInt(5));
				todo.setCantCorrectas(rs.getInt(6));
				todo.setCantErroneas(rs.getInt(7));
				todo.setCantOmitidas(rs.getInt(8));
				todo.setFechaResultado(rs.getString(9));
				todo.setIdRespuesta(rs.getInt(10));
				todo.setRut(rs.getString(11));
				todo.setEjercicio(rs.getInt(12));
				todo.setResultadoEjercicio(rs.getBoolean(13));
				todo.setIntentoEjercicio(rs.getInt(14));
				todo.setTiempoEjercicio(rs.getString(15));
				todo.setTiempoSesion(rs.getString(16));
				todo.setFechaEjercicio(rs.getString(17));
				todo.setIdConsulta(rs.getInt(18));
				todo.setNumeroConsulta(rs.getInt(19));
				todo.setQuery(rs.getString(20));
				if(rs.getString(5) == null){
					todo.setErrorQuery("");
				}else{
					todo.setErrorQuery(rs.getString(21));
				}
				todo.setFechaConsulta(rs.getString(22));
				todos.add(todo);
			}
			
			System.out.println("Carga lista de todo");
			
			return todos;			
	
    	} catch (Exception e){
    		System.out.println("Error selectTodo() -> " + e.getMessage());
    		return null;
    	} finally {
    		Database.close(con);
    	}
    }


    public static List<EstadisticasEjecutorAR> selectEjecutorAR(){
    	try{
    		
    		String query = "select * from ejecutor_ar";
    		
    		System.out.println("Query " + query);
    		
    		con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticasEjecutorAR> estadisticasAR = new ArrayList<EstadisticasEjecutorAR>();
			
			while(rs.next()){
				EstadisticasEjecutorAR estadistica = new EstadisticasEjecutorAR();
				estadistica.setId(rs.getInt(1));
				estadistica.setRut(rs.getString(2));
				estadistica.setBd(rs.getString(3));
				estadistica.setQuery(rs.getString(4));
				estadistica.setOperador(rs.getString(5));
				estadistica.setQuery_correcta(rs.getBoolean(6));
				estadistica.setQuery_incorrecta(rs.getBoolean(7));
				estadistica.setClasificacion_error(rs.getString(8));
				estadistica.setDescripcion_error(rs.getString(9));
				estadistica.setFecha(rs.getString(10));
				estadisticasAR.add(estadistica);
			}
			
			System.out.println("Carga: lista de estadísticas de ejecutor AR");
			
			return estadisticasAR;			
	
    	} catch (Exception e){
    		System.out.println("Error selectEjecutorAR() -> " + e.getMessage());
    		return null;
    	} finally {
    		Database.close(con);
    	}
    }

    public static List<EstadisticasEjecutorSQL> selectEjecutorSQL(){
    	try{
    		
    		String query = "select * from ejecutor_sql";
    		
    		System.out.println("Query " + query);
    		
    		con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticasEjecutorSQL> estadisticasSQL = new ArrayList<EstadisticasEjecutorSQL>();
			
			while(rs.next()){
				EstadisticasEjecutorSQL estadistica = new EstadisticasEjecutorSQL();
				estadistica.setId(rs.getInt(1));
				estadistica.setRut(rs.getString(2));
				estadistica.setBd(rs.getString(3));
				estadistica.setQuery(rs.getString(4));
				estadistica.setClausula(rs.getString(5));
				estadistica.setQuery_correcta(rs.getBoolean(6));
				estadistica.setQuery_incorrecta(rs.getBoolean(7));
				estadistica.setClasificacion_error(rs.getString(8));
				estadistica.setDescripcion_error(rs.getString(9));
				estadistica.setFecha(rs.getString(10));
				estadisticasSQL.add(estadistica);
			}
			
			System.out.println("Carga: lista de estadisticas de ejecutor SQL");
			
			return estadisticasSQL;			
	
    	} catch (Exception e){
    		System.out.println("Error selectEjecutorSQL() -> " + e.getMessage());
    		return null;
    	} finally {
    		Database.close(con);
    		}
    	}

    
    
    

}
