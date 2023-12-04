package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.Consulta;
import util.Ejercicio;
import util.Respuesta;
import util.Resultado;

@ManagedBean(name = "resultado")
@SessionScoped
public class ResultadoDAO {

	static Connection con = null;
    static PreparedStatement ps = null;
    static Statement cs = null;
    
	public static Respuesta revisarRespuesta(Respuesta respuesta, Ejercicio ejercicio){
		try{
			String query = 	"(select * from "+ respuesta.getBd() + "." + ejercicio.getRespuesta() +
							" except " +
							"select * from load" + respuesta.getRut() + "." + ejercicio.getRespuestaAlumno() + ")" +
							" union " +
							"(select * from load" + respuesta.getRut() + "." + ejercicio.getRespuestaAlumno() +
							" except " +
							"select * from " + respuesta.getBd() + "." + ejercicio.getRespuesta() + ")";
			System.out.println("revisarRespuesta Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()){
				respuesta.setResultado(true);
			}else{
				respuesta.setResultado(false);
			}
			
			return respuesta;
		} catch (Exception ex) {
			respuesta.setResultado(false);
			System.out.println("Error en revisarRespuesta() -->" + ex.getMessage());
			return respuesta;
        } finally {
        	Database.close(con);
        }
	}
	
	public static boolean guardarResultado(Resultado resultado){
		try{
			String query = 	"insert into resultados (bd,rut,fecha_bd,cant_ejercicios,cant_correctas,cant_erroneas,cant_omitidas,fecha) values " +
							"(?, ?, ?,?,?,?,?,(select current_timestamp))";
			System.out.println("guardarResultado Query: " + query);
			System.out.println("guardarResultado RUT  : " + resultado.getRut());
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,resultado.getBd());
			ps.setString(2,resultado.getRut());
			ps.setTimestamp(3,resultado.getFechaBd());
			ps.setInt(4,resultado.getCantEjercicios());
			ps.setInt(5,resultado.getCantCorrectas());
			ps.setInt(6,resultado.getCantErroneas());
			ps.setInt(7,resultado.getCantOmitidas());
			ps.execute();
			
			return true;
		} catch (Exception ex) {
			System.out.println("Error en guardarResultado() -->" + ex.getMessage());
			return false;
        } finally {
        	Database.close(con);
        }
	}
	
	public static boolean guardarRespuestas(List<Respuesta> respuestas){
		try{
			
			for(Respuesta r : respuestas){
			
				String query = 	"insert into respuestas (rut,bd,fecha_bd,ejercicio,resultado,intento,tiempo_ejercicio,tiempo_sesion,fecha) values " +
						"(?,?,?,?,?,?,?,?,(select current_timestamp))";
				System.out.println("guardarRespuestas Query: " + query);
				
				Time tiempoEjercicio = new Time(r.getTiempoEjercicio().getTimeInMillis());
				Time tiempoSesion = new Time(r.getTiempoSesion().getTimeInMillis());
				
				con = Database.getConnection();
				ps = con.prepareStatement(query);
				ps.setString(1,r.getRut());
				ps.setString(2,r.getBd());
				ps.setTimestamp(3,r.getFechaBd());
				ps.setInt(4,r.getEjercicio());
				ps.setBoolean(5,r.isResultado());
				ps.setInt(6,r.getIntento());
				ps.setTime(7,tiempoEjercicio);
				ps.setTime(8,tiempoSesion);
				ps.execute();
				
				System.out.println("Respuesta " + r.getEjercicio() + "." + r.getIntento() + "guardada");
				
				for(Consulta c : r.getConsultas()){
					
					query = "insert into consultas (id_respuestas,numero,query,error,fecha) values " +
							"((select last_value from sec_respuestas),?,?,?,(select current_timestamp))";
					System.out.println("Query: " + query);
					
					ps = con.prepareStatement(query);
					ps.setInt(1,c.getNumero());
					ps.setString(2,c.getQuery());
					ps.setString(3,c.getError());
					ps.execute();
					
					System.out.println("Consulta " + c.getQuery() + "insertada ");
					
				}
				
			}
			
			System.out.println("Respuestas guardadas");

			return true;
			
		}catch(Exception ex){
			System.out.println("Error en guardarRespuestas() -->" + ex.getMessage());
			return false;
        }finally{
        	Database.close(con); 
        }
	}
	
	
	public static List<Resultado> cargarResultado(String bd, String fecha){
		try{
			String query = 	"select id,rut,bd,fecha_bd,cant_ejercicios,cant_correctas,cant_erroneas,cant_omitidas from resultados where bd = ? and fecha_bd = '" + fecha + "'";
			System.out.println("cargarResultado Query: " + query);
			System.out.println("--------cargarRespuestas: GLOBAL");
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,bd);
			ResultSet rs = ps.executeQuery();
			
			List<Resultado> resultados = new ArrayList<Resultado>();
			
			while(rs.next()){
				Resultado r = new Resultado();
				r.setRut(rs.getString(2));
				r.setBd(rs.getString(3));
				r.setFechaBd(rs.getTimestamp(4));
				r.setCantEjercicios(rs.getInt(5));
				r.setCantCorrectas(rs.getInt(6));
				r.setCantErroneas(rs.getInt(7));
				r.setCantOmitidas(rs.getInt(8));
				resultados.add(r);
			}
			
			System.out.println("Resultados Cargados");
			
			return resultados;
		} catch (Exception ex) {
			System.out.println("Error en cargarResultados() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}
	
	public static List<Respuesta> cargarRespuestas(String bd, String fecha){
		try{
			String query = 	"select * from respuestas where bd = ? and fecha_bd = '" + fecha + "'";
			System.out.println("--------cargarRespuestas Query: " + query);
			System.out.println("--------cargarRespuestas      : GLOBAL");
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,bd);
			ResultSet rs = ps.executeQuery();
			
			List<Respuesta> respuestas = new ArrayList<Respuesta>();
			
			while(rs.next()){
				Respuesta r = new Respuesta(); 
				r.setRut(rs.getString(2));
				r.setBd(rs.getString(3));
				r.setFechaBd(rs.getTimestamp(4));
				r.setEjercicio(rs.getInt(5));
				r.setResultado(rs.getBoolean(6));
				r.setIntento(rs.getInt(7));
				
				Time tiempoEjercicio = rs.getTime(8);
				Calendar tiempoEjercicioCal = new GregorianCalendar();
				tiempoEjercicioCal.setTimeInMillis(tiempoEjercicio.getTime());
				r.setTiempoEjercicio(tiempoEjercicioCal);
				
				Time tiempoSesion = rs.getTime(9);
				Calendar tiempoSesionCal = new GregorianCalendar();
				tiempoSesionCal.setTimeInMillis(tiempoSesion.getTime());
				r.setTiempoEjercicio(tiempoEjercicioCal);
				
				query = "select * from consultas where id_respuestas = ?";
				System.out.println("Query: " + query);
				
				ps = con.prepareStatement(query);
				ps.setInt(1,rs.getInt(1));
				ResultSet rs1 = ps.executeQuery();
				
				List<Consulta> consultas = new ArrayList<Consulta>();
				
				while(rs1.next()){
					Consulta c = new Consulta();
					c.setNumero(rs1.getInt(3));
					c.setQuery(rs1.getString(4));
					c.setError(rs1.getString(5));
					consultas.add(c);
				}
				
				System.out.println("Consultas Cargadas");
				r.setConsultas(consultas);
				respuestas.add(r);
			}
			
			System.out.println("Respuestas Cargados");
			
			return respuestas;
		} catch (Exception ex) {
			System.out.println("Error en guardarResultado() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}
	
	public static List<Resultado> cargarResultadoRut(String bd, String fecha, String rut){
		try{
			String query = 	"select id,bd,fecha_bd,cant_ejercicios,cant_correctas,cant_erroneas,cant_omitidas from resultados "
					+ "where bd = ? and fecha_bd = '" + fecha + "' and rut = '"+rut+"'";
			System.out.println("cargarResultadoRut Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,bd);
			ResultSet rs = ps.executeQuery();
			
			List<Resultado> resultados = new ArrayList<Resultado>();
			
			while(rs.next()){
				Resultado r = new Resultado();
				r.setBd(rs.getString(2));
				r.setFechaBd(rs.getTimestamp(3));
				r.setCantEjercicios(rs.getInt(4));
				r.setCantCorrectas(rs.getInt(5));
				r.setCantErroneas(rs.getInt(6));
				r.setCantOmitidas(rs.getInt(7));
				resultados.add(r);
			}
			
			System.out.println("Resultados Cargados");
			
			return resultados;
		} catch (Exception ex) {
			System.out.println("Error en cargarResultados() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}
	
	public static List<Respuesta> cargarRespuestasRut(String bd, String fecha, String rut){
		try{
			String query = 	"select * from respuestas "
					+ "where bd = ? and fecha_bd = '" + fecha + "' and rut = '"+ rut+"'";
			System.out.println("cargarRespuestasRut Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,bd);
			ResultSet rs = ps.executeQuery();
			
			List<Respuesta> respuestas = new ArrayList<Respuesta>();
			
			while(rs.next()){
				Respuesta r = new Respuesta(); 
				r.setRut(rs.getString(2));
				r.setBd(rs.getString(3));
				r.setFechaBd(rs.getTimestamp(4));
				r.setEjercicio(rs.getInt(5));
				r.setResultado(rs.getBoolean(6));
				r.setIntento(rs.getInt(7));
				
				Time tiempoEjercicio = rs.getTime(8);
				Calendar tiempoEjercicioCal = new GregorianCalendar();
				tiempoEjercicioCal.setTimeInMillis(tiempoEjercicio.getTime());
				r.setTiempoEjercicio(tiempoEjercicioCal);
				
				Time tiempoSesion = rs.getTime(9);
				Calendar tiempoSesionCal = new GregorianCalendar();
				tiempoSesionCal.setTimeInMillis(tiempoSesion.getTime());
				r.setTiempoEjercicio(tiempoEjercicioCal);
				
				query = "select * from consultas where id_respuestas = ?";
				System.out.println("Query: " + query);
				
				ps = con.prepareStatement(query);
				ps.setInt(1,rs.getInt(1));
				ResultSet rs1 = ps.executeQuery();
				
				List<Consulta> consultas = new ArrayList<Consulta>();
				
				while(rs1.next()){
					Consulta c = new Consulta();
					c.setNumero(rs1.getInt(3));
					c.setQuery(rs1.getString(4));
					c.setError(rs1.getString(5));
					consultas.add(c);
				}
				
				System.out.println("Consultas Cargadas");
				r.setConsultas(consultas);
				respuestas.add(r);
			}
			
			System.out.println("Respuestas Cargados");
			
			return respuestas;
		} catch (Exception ex) {
			System.out.println("Error en guardarResultado() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}

	
	public static List<String> obtenerEsquemas(){
		try{
			String query = "select distinct bd from resultados";
			System.out.println("obtenerEsquemas Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<String> bds = new ArrayList<String>();
			
			while(rs.next()){
				String bd = rs.getString(1);
				bds.add(bd);
			}
			
			System.out.println("Cargado todos los nombres de BDs");

			return bds;
		}catch (Exception ex){
			System.out.println("Error en obtenerEsquemas() -->" + ex.getMessage());
			return null;
		}finally{
			Database.close(con);
		}
	}
	
	public static List<String> obtenerFechas(String bd){
		try{
			String query = "select distinct fecha_bd from resultados where bd=?";
			System.out.println("obtenerFechas Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,bd);
			ResultSet rs = ps.executeQuery();
			
			List<String> fechas = new ArrayList<String>();
			
			while(rs.next()){
				String fecha = rs.getString(1);
				fechas.add(fecha);
			}
			
			System.out.println("Cargado todos las fechas");

			return fechas;
		}catch (Exception ex){
			System.out.println("Error en obtenerFechas() -->" + ex.getMessage());
			return null;
		}finally{
			Database.close(con);
		}
	}
	
	public static List<String> obtenerRuts(String fechabd){
		try{
			String query = "select distinct rut from respuestas where fecha_bd= '"+fechabd+"'";
			System.out.println("obtenerRuts Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			//ps.setString(1,fechabd);
			ResultSet rs = ps.executeQuery();
			
			List<String> ruts = new ArrayList<String>();
			
			while(rs.next()){
				String rut = rs.getString(1);
				ruts.add(rut);
			}
			
			System.out.println("Cargado todos los ruts");

			return ruts;
		}catch (Exception ex){
			System.out.println("Error en obtenerRut() -->" + ex.getMessage());
			return null;
		}finally{
			Database.close(con);
		}
	}	

	public static List<Ejercicio> ejerciciosList(String bd){ //incompleto, la idea es obtener el nombre de los ejercicios y mezclar esta info con los resultados
		try{
					
			String query = "select * from " + bd + "._resp";
			System.out.println("Query: " + query); 
			
			con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
			
			while(rs.next()){
				Ejercicio e = new Ejercicio();
				e.setId(rs.getInt(1));
				e.setPregunta(rs.getString(2));
				e.setRespuesta(rs.getString(3));
				e.setConsultas(rs.getString(4));
				e.setQueryList(e.getConsultas());
				ejercicios.add(e);
			}
			
			System.out.println("Ejercicios cargados");

			return ejercicios;
			
		} catch (Exception ex) {
			System.out.println("Error en cargarEjercicios() -->" + ex.getMessage());
			return null;
		} finally {
			Database.close(con);
		}
	}
}
