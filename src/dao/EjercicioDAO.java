package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Ejercicio;
import util.EsquemaBean;

public class EjercicioDAO {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static Statement cs = null;
	
	public static boolean crearEjercicios(String rut, EsquemaBean esquema, List<Ejercicio> ejercicios){
		try{
					
			String query = "begin";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ps.execute();
			
			query = "select id from " + esquema.getNombre() + "_" + esquema.getRut() + "._resp";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				query = "drop table " + esquema.getNombre() + "_" + esquema.getRut() + "._respuesta_ejercicio_" + rs.getInt(1);
				System.out.println("Query: " + query);
				
				ps = con.prepareStatement(query);
				ps.execute();
				
				System.out.println("Tabla de respuesta " + rs.getInt(1) + "borrada");
				
			}
					
			
			query = "truncate table " + esquema.getNombre() + "_" + esquema.getRut() + "._resp";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			for(Ejercicio e : ejercicios){
			
				String query1 =	"insert into " + esquema.getNombre() + "_" + esquema.getRut() + "._resp values " +
								"(?,?,?,?)";
				System.out.println("Query: " + query1);
				
				ps = con.prepareStatement(query1);
				ps.setInt(1,e.getId());
				ps.setString(2,e.getPregunta());
				ps.setString(3,"_respuesta_ejercicio_" + e.getId());
				ps.setString(4,e.getConsultas());
				ps.execute();
				
				System.out.println("Indice del ejercicio " + e.getId() + " creado" );
				
				query1 =	"create table " + esquema.getNombre() + "_" + esquema.getRut() + "._respuesta_ejercicio_" + e.getId() + " as " + 
							"select * from load" + rut + "." + e.getRespuesta();
				System.out.println("Query: " + query1);
				
				ps = con.prepareStatement(query1);
				ps.execute();
				
				System.out.println("Tabla del ejercicio " + e.getId() + " creada" );
			}
			
			System.out.println("Ejercicios agregados a la base de datos " + esquema);
			
			query = "update esquemas set  fecha=(select current_timestamp) where rut=? and nombre=?";
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.setString(1,esquema.getRut());
			ps.setString(2,esquema.getNombre());
			ps.execute();
			
			System.out.println("Aviso de actualizacion en esquema" + esquema);
			
			query = "commit";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("listo!");
			
			return true;
			
		} catch (Exception ex) {
			String query = "rollback";
			System.out.println("Query: " + query);
			
			try {
				ps = con.prepareStatement(query);
				ps.execute();
			} catch (Exception e) {
				System.out.println("Error en gestionarEjercicios() intentar rollback --> " + ex.getMessage());
			}
			return false;
		} finally {
			Database.close(con);
		}
	}
	
	public static List<Ejercicio> cargarEjercicios(EsquemaBean esquema){
		try{
					
			String query = "select * from " + esquema.getNombre() + "_" + esquema.getRut() + "._resp";
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
	
	public static boolean borrarEjercicios(EsquemaBean esquema){
		try{
			
			String query = "begin";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ps.execute();
			
			query = "select id from " + esquema.getNombre() + "_" + esquema.getRut() + "._resp";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				query = "drop table " + esquema.getNombre() + "_" + esquema.getRut() + "._respuesta_ejercicio_" + rs.getInt(1);
				System.out.println("Query: " + query);
				
				ps = con.prepareStatement(query);
				ps.execute();
				
				System.out.println("Tabla de respuesta " + rs.getInt(1) + " borrada");
				
			}
					
			
			query = "truncate table " + esquema.getNombre() + "_" + esquema.getRut() + "._resp";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			query = "update esquemas set  fecha=(select current_timestamp) where rut=? and nombre=?";
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.setString(1,esquema.getRut());
			ps.setString(2,esquema.getNombre());
			ps.execute();
			
			System.out.println("Aviso de actualizacion en esquema" + esquema);
			
			query = "commit";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("listo!");
			
			return true;
			
		} catch (Exception ex) {
			String query = "rollback";
			System.out.println("Query: " + query);
			
			try {
				ps = con.prepareStatement(query);
				ps.execute();
			} catch (Exception e) {
				System.out.println("Error en eliminarEjercicios() intentar rollback --> " + ex.getMessage());
			}
			return false;
		} finally {
			Database.close(con);
		}
	}
	
//	public static List<String> obtenerEsquemas(EsquemaBean esquema){
//		try{
//					
//			String query = "select distinct bd";
//			System.out.println("Query: " + query);
//			
//			con = Database.getConnection();	
//			ps = con.prepareStatement(query);
//			ResultSet rs = ps.executeQuery();
//			
//			List<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
//			
//			while(rs.next()){
//				Ejercicio e = new Ejercicio();
//				e.setId(rs.getInt(1));
//				e.setPregunta(rs.getString(2));
//				e.setRespuesta(rs.getString(3));
//				e.setConsultas(rs.getString(4));
//				ejercicios.add(e);
//			}
//			
//			System.out.println("Ejercicios cargados");
//
//			return ejercicios;
//			
//		} catch (Exception ex) {
//			System.out.println("Error en cargarEjercicios() -->" + ex.getMessage());
//			return null;
//		} finally {
//			Database.close(con);
//		}
//	}
}
