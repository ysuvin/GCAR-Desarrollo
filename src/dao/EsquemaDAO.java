package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.Esquema;
import util.EsquemaBean;

public class EsquemaDAO {
	
	static Connection con = null;
    static PreparedStatement ps = null;
    static Statement cs = null;
	
	public static List<Esquema> selectAllBds(){
		try{
			
			String query = "SELECT * from esquemas";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			cs = con.createStatement();
			ResultSet rs = cs.executeQuery(query);
			
			ArrayList<Esquema> bds = new ArrayList<Esquema>();
			while(rs.next()){
				Esquema bd = new Esquema();
				bd.setRut(rs.getString(1));
				bd.setNombre(rs.getString(2));
				bd.setFecha(rs.getTimestamp(3));
				bd.setVisible(rs.getBoolean(4));
				bd.setAntiguoNombre(bd.getNombre());
				bds.add(bd);
			}
			
			System.out.println("Carga de BDs exitosa");
			return bds;
						
		} catch (Exception ex) {
			System.out.println("Error en selectBds() -->" + ex.getMessage());
			return null;
		} finally {
			Database.close(con);
		}
	}

	public static List<Esquema> selectAllBds(String rut){
		try{
			String query = 	"select esquemas.* from esquemas,usuarios " +
							"where esquemas.visible = true and usuarios.rut = esquemas.rut and usuarios.tipo = 1 " +
							"union " +
							"select * from esquemas where esquemas.rut = ?";
			
			System.out.println("Query: " + query);
				
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,rut);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Esquema> bds = new ArrayList<Esquema>();
			while(rs.next()){
				Esquema bd = new Esquema();
				if(rs.getString(1).equals(rut)){
					bd.setRut(rs.getString(1));
				}else{
					bd.setRut("Profesor");
				}
				bd.setNombre(rs.getString(2));
				bd.setFecha(rs.getTimestamp(3));
				bd.setVisible(rs.getBoolean(4));
				bd.setAntiguoNombre(bd.getNombre());
				bds.add(bd);
			}
			
			System.out.println("Carga de BDs exitosa");
			return bds;
						
		} catch (Exception ex) {
			System.out.println("Error en selectBds() -->" + ex.getMessage());
			return null;
		} finally {
			Database.close(con);
		}
	}
	
	public static List<Esquema> selectMisBds(String rut){
		try{
			String query = 	"select * from esquemas where esquemas.rut = ?";
			
			System.out.println("Query: " + query);
				
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,rut);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Esquema> bds = new ArrayList<Esquema>();
			while(rs.next()){
				Esquema bd = new Esquema();
				if(rs.getString(1).equals(rut)){
					bd.setRut(rs.getString(1));
				}else{
					bd.setRut("Profesor");
				}
				bd.setNombre(rs.getString(2));
				bd.setFecha(rs.getTimestamp(3));
				bd.setVisible(rs.getBoolean(4));
				bd.setAntiguoNombre(bd.getNombre());
				bds.add(bd);
			}
			
			System.out.println("Carga de BDs exitosa");
			return bds;
						
		} catch (Exception ex) {
			System.out.println("Error en selectBds() -->" + ex.getMessage());
			return null;
		} finally {
			Database.close(con);
		}
	}
	
	public static EsquemaBean cargarBDBean(String nombre){
		try{
			
			String query = "SELECT * from esquemas WHERE nombre=?";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,nombre);
			ResultSet rs = ps.executeQuery();
			
			EsquemaBean bd = new EsquemaBean();

			if(rs.next()){
				bd.setRut(rs.getString(1));
				bd.setNombre(rs.getString(2));
				bd.setFecha(rs.getTimestamp(3));
				bd.setVisible(rs.getBoolean(4));
			}
			
			System.out.println("Carga de BDs exitosa");
			return bd;
						
		} catch (Exception ex) {
			System.out.println("Error en selectBds() -->" + ex.getMessage());
			return null;
		} finally {
			Database.close(con);
		}
	}
	
	public static boolean crearEsquemaLoad(String rut){
		try{
			
			String query = "DROP SCHEMA IF EXISTS load" + rut + " CASCADE";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Eliminacion esquema load");
			
			query = "CREATE SCHEMA load" + rut;
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Creación de Esquema de carga exitosa");
			return true;
						
		} catch (Exception ex) {
			System.out.println("Error en crearEsquemaLoad() -->" + ex.getMessage());
			return false;
		} finally {
			Database.close(con);
		}
	}
	
	public static boolean borrarEsquemaLoad(String rut){
		try{
			
			String query = "DROP SCHEMA IF EXISTS load" + rut + " CASCADE";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Eliminacion esquema load");
			return true;
						
		} catch (Exception ex) {
			System.out.println("Error en borrarEsquemaLoad() -->" + ex.getMessage());
			return false;
		} finally {
			Database.close(con);
		}
	}
	
	public static boolean crearNuevoEsquema(Esquema esquema, String user){
		try{
			
			String query = "insert into esquemas values (?,?,(select current_timestamp),?)";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,user);
			ps.setString(2,esquema.getNombre().toLowerCase());
			ps.setBoolean(3,esquema.isVisible());
			ps.execute();
			
			System.out.println("Esquema " + esquema.getNombre() + " agregado a la tabla Esquemas");
			
			query = "create schema " + esquema.getNombre() + "_" + user;
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Se ha creado el Esquema " + esquema.getNombre());
			
			query =	"create table " + esquema.getNombre() + "_" + user + "._resp( " +
					"id int, pregunta text, relacion varchar(60), consultas text, primary key(id) )";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Se ha creado la tabla resp en el Esquema " + esquema.getNombre());
			
			return true;
			
						
		} catch (Exception ex) {
			System.out.println("Error en crearNuevoEsquema() -->" + ex.getMessage());
			return false;
		} finally {
			Database.close(con);
		}
		
		
	}
	
	public static boolean borrarEsquema(Esquema esquema){
		try{
			
			String query = "delete from esquemas where rut = ? and nombre = ?";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,esquema.getRut());
			ps.setString(2,esquema.getNombre());
			ps.execute();
			
			System.out.println("Esquema " + esquema.getNombre() + " eliminado de la tabla Esquemas");
			
			query = "drop schema " + esquema.getNombre() + "_" + esquema.getRut() + " cascade";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Se ha eliminado el Esquema " + esquema.getNombre());
			return true;
						
		} catch (Exception ex) {
			System.out.println("Error en borrarEsquema() -->" + ex.getMessage());
			return false;
		} finally {
			Database.close(con);
		}
		
	}

	public static boolean modificarEsquema(Esquema esquema) {
		try{
			
			String query = "update esquemas set nombre=?, fecha=(select current_timestamp), visible=? where rut=? and nombre=?";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,esquema.getNombre());
			ps.setBoolean(2,esquema.isVisible());
			ps.setString(3,esquema.getRut());
			ps.setString(4,esquema.getAntiguoNombre());
			ps.execute();
			
			System.out.println("Esquema " + esquema.getNombre() + " modificado de la tabla Esquemas");
			
			if(!esquema.getNombre().equals(esquema.getAntiguoNombre())){
				
				query = "alter schema " + esquema.getAntiguoNombre() + "_" + esquema.getRut() + 
						" rename to " + esquema.getNombre() + "_" + esquema.getRut();
				System.out.println("Query: " + query);
				
				ps = con.prepareStatement(query);
				ps.execute();
				
				System.out.println("Se ha renombrado el Esquema " + esquema.getNombre());
			}
			return true;
						
		} catch (Exception ex) {
			System.out.println("Error en modificarEsquema() -->" + ex.getMessage());
			return false;
		} finally {
			Database.close(con);
		}
		
	}
	
	public static String borrarEsquemasLoad(){
		try{
			
			String query = "SELECT n.nspname FROM pg_namespace n WHERE n.nspname ~ '^load'";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);			
			ResultSet rs = ps.executeQuery();
			
			List<String> esquemas = new ArrayList<String>();
			
			while(rs.next()){
				String s = rs.getString(1);
				esquemas.add(s);
			}
			
			System.out.println("Esquemas Load obtenidos");

			for(String s: esquemas){
				query = "DROP SCHEMA " + s + " CASCADE";
				ps = con.prepareStatement(query);
				ps.executeQuery();
				
				System.out.println("Esquema " + s + " eliminado");
			}		
			
			return "ok";			
		} catch (Exception ex) {
			System.out.println("Error en modificarEsquema() -->" + ex.getMessage());
			return ex.getMessage();
		} finally {
			Database.close(con);
		}
	}

}