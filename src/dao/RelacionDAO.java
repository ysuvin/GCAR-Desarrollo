package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import util.Atributo;
import util.AtributoBean;
import util.EsquemaBean;
import util.RelacionBean;
import util.Relacion;
import util.TuplaBean;

public class RelacionDAO {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static Statement cs = null;
	
	public static List<RelacionBean> cargarRelacionesBean(EsquemaBean esquema, String rut){
		
		try{
			
			String query ="select tablename from pg_tables where schemaname=?";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,esquema.getNombre() + "_" + esquema.getRut());
			ResultSet rs = ps.executeQuery();
			
			List<RelacionBean> relacionesBean = new ArrayList<RelacionBean>();
	
			while(rs.next()){
				Pattern pat1 = Pattern.compile("_respuesta_ejercicio_[0-9]*");
				Pattern pat2 = Pattern.compile("_resp");
			    Matcher mat1 = pat1.matcher(rs.getString(1));
			    Matcher mat2 = pat2.matcher(rs.getString(1));
				if(!mat1.matches() && !mat2.matches()){
					RelacionBean relacionBean = new RelacionBean();
					relacionBean.setNombre(rs.getString(1));
					relacionesBean.add(relacionBean);
				}
			}
			
			for(RelacionBean r : relacionesBean){
				String subquery1 = 	"select column_name, is_nullable, data_type " + 
									"from information_schema.columns " + 
									"where table_name=? and table_schema=? order by is_nullable;";	
				
				System.out.println(subquery1);
				
//				con = Database.getConnection();
				ps = con.prepareStatement(subquery1);
				ps.setString(1,r.getNombre());
				ps.setString(2,esquema.getNombre() + "_" + esquema.getRut());
				ResultSet rs1 = ps.executeQuery();
				
				List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
				while(rs1.next()){
					
					AtributoBean atributoBean = new AtributoBean();
					atributoBean.setNombre(rs1.getString(1));
					if(rs1.getString(2).equals("NO"))
						atributoBean.setEsPrimario("Sí");
					else
						atributoBean.setEsPrimario("No");
					if(rs1.getString(3).equals("integer"))
						atributoBean.setTipo("Entero");
					else if(rs1.getString(3).equals("text"))
						atributoBean.setTipo("Cadena");
					else
						atributoBean.setTipo("Real");
					atributosBean.add(atributoBean);
				}
				r.setAtributos(atributosBean);
				
				String subquery2 = 	"select * from " + esquema.getNombre() + "_" + esquema.getRut() + "." + r.getNombre();
				System.out.println(subquery2);
				
//				con = Database.getConnection();
				ps = con.prepareStatement(subquery2);
				rs1 = ps.executeQuery();
							
				List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
				while(rs1.next()){
					TuplaBean tuplaBean = new TuplaBean();
					Object atributo[] = new Object[r.getAtributos().size()];
					for(int i = 0 ; i < atributo.length ; i++){
						atributo[i] = rs1.getObject(i+1);
					}
					tuplaBean.setAtributos(atributo);
					tuplasBean.add(tuplaBean);
				}
				r.setTuplas(tuplasBean);
				
				String subquery3 = 	"create table load" + rut + "." + r.getNombre() + " as " +
									"select * from " + esquema.getNombre() + "_" + esquema.getRut() + "." + r.getNombre();
				System.out.println(subquery3);
				
				ps = con.prepareStatement(subquery3);
				ps.execute();
				
				System.out.println("Tabla load" + rut + "." + r.getNombre());
				
			}
			
			return relacionesBean;
			
		} catch (Exception ex) {
            System.out.println("Error en cargarRelacionesBean() --> " + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }
		
	}
	
	public static List<RelacionBean> cargarRelacionesEjerciciosBean(EsquemaBean esquema, String rut){
		
		try{
			
			String query ="select tablename from pg_tables where schemaname=?";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,esquema.getNombre() + "_" + esquema.getRut());
			ResultSet rs = ps.executeQuery();
			
			List<RelacionBean> relacionesBean = new ArrayList<RelacionBean>();
	
			while(rs.next()){
				Pattern pat1 = Pattern.compile("_respuesta_ejercicio_[0-9]*");
				Pattern pat2 = Pattern.compile("_resp");
			    Matcher mat1 = pat1.matcher(rs.getString(1));
			    Matcher mat2 = pat2.matcher(rs.getString(1));
				if(mat1.matches() && !mat2.matches()){
					RelacionBean relacionBean = new RelacionBean();
					relacionBean.setNombre(rs.getString(1));
					relacionesBean.add(relacionBean);
				}
			}
			
			for(RelacionBean r : relacionesBean){
				String subquery1 = 	"select column_name, is_nullable, data_type " + 
									"from information_schema.columns " + 
									"where table_name=? and table_schema=? order by is_nullable;";	
				
				System.out.println(subquery1);
				
//				con = Database.getConnection();
				ps = con.prepareStatement(subquery1);
				ps.setString(1,r.getNombre());
				ps.setString(2,esquema.getNombre() + "_" + esquema.getRut());
				ResultSet rs1 = ps.executeQuery();
				
				List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
				while(rs1.next()){
					
					AtributoBean atributoBean = new AtributoBean();
					atributoBean.setNombre(rs1.getString(1));
					if(rs1.getString(2).equals("NO"))
						atributoBean.setEsPrimario("Sí");
					else
						atributoBean.setEsPrimario("No");
					if(rs1.getString(3).equals("integer"))
						atributoBean.setTipo("Entero");
					else if(rs1.getString(3).equals("text"))
						atributoBean.setTipo("Cadena");
					else
						atributoBean.setTipo("Real");
					atributosBean.add(atributoBean);
				}
				r.setAtributos(atributosBean);
				
				String subquery2 = 	"select * from " + esquema.getNombre() + "_" + esquema.getRut() + "." + r.getNombre();
				System.out.println(subquery2);
				
//				con = Database.getConnection();
				ps = con.prepareStatement(subquery2);
				rs1 = ps.executeQuery();
							
				List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
				while(rs1.next()){
					TuplaBean tuplaBean = new TuplaBean();
					Object atributo[] = new Object[r.getAtributos().size()];
					for(int i = 0 ; i < atributo.length ; i++){
						atributo[i] = rs1.getObject(i+1);
					}
					tuplaBean.setAtributos(atributo);
					tuplasBean.add(tuplaBean);
				}
				r.setTuplas(tuplasBean);
				
				String subquery3 = 	"create table load" + rut + "." + r.getNombre() + " as " +
									"select * from " + esquema.getNombre() + "_" + esquema.getRut() +"." + r.getNombre();
				System.out.println(subquery3);
				
				ps = con.prepareStatement(subquery3);
				ps.execute();
				
				System.out.println("Tabla load" + rut + "." + r.getNombre());
				
			}
			
			return relacionesBean;
			
		} catch (Exception ex) {
            System.out.println("Error en cargarRelacionesEjerciciosBean() --> " + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }
		
	}

	public static boolean crearRelacion(EsquemaBean bd, Relacion relacion){		
		try{
			
			String query = "begin";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ps.execute();
			
			query = "create table " + bd.getNombre() + "_" + bd.getRut() + "." + relacion.getNombre() + "(\n";
			for(Atributo a : relacion.getAtributos()){
				query = query + a.getNombre() + " ";
				if(a.getTipo().equals("Entero")){
					query = query + "int,\n";
				}else if(a.getTipo().equals("Real")){
					query = query + "float,\n";
				}else if(a.getTipo().equals("Cadena")){
					query = query + "text,\n";
				}else{
					return false;
				}
			}
			query = query + "primary key(";
			for(Atributo a : relacion.getAtributos()){
				if(a.getEsPrimario().equals("Sí")){ 
					query = query + a.getNombre() + ",";
				}
			}
			query = query.substring(0,query.length()-1);
			query = query + ")\n)";
			
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			query = "update esquemas set  fecha=(select current_timestamp) where rut=? and nombre=?";
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.setString(1,bd.getRut());
			ps.setString(2,bd.getNombre());
			ps.execute();
		
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
				System.out.println("Error en crearRelacion() intentar rollback --> " + ex.getMessage());
			}
			return false;
		} finally {
			Database.close(con);
		}
			
		
	}
	
	public static boolean eliminarRelacion(EsquemaBean bd, RelacionBean relacion){		
		try{
			
			String query = "begin";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ps.execute();
			
			query = "drop table " + bd.getNombre() + "_" + bd.getRut() + "." + relacion.getNombre();
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
		
			query = "update esquemas set  fecha=(select current_timestamp) where rut=? and nombre=?";
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.setString(1,bd.getRut());
			ps.setString(2,bd.getNombre());
			ps.execute();
		
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
				System.out.println("Error en eliminarRelacion() intentar rollback --> " + ex.getMessage());
			}
			return false;
		} finally {
			Database.close(con);
		}
			
		
	}
	
	public static boolean modificarRelacion(EsquemaBean bd, RelacionBean relacion, String oldName){		
		try{
			
			String query = "begin";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();	
			ps = con.prepareStatement(query);
			ps.execute();
			
//			query = "ALTER TABLE " + bd.getNombre() + "_" + bd.getRut() + "." + oldName + 
//					" RENAME TO "+ relacion.getNombre() ;
//					// + bd.getNombre() + "_" + bd.getRut() + "." + relacion.getNombre()
//			System.out.println("Query: " + query);
//			
//			ps = con.prepareStatement(query);
//			ps.executeUpdate();
			
			query = "drop table " + bd.getNombre() + "_" + bd.getRut() + "." + oldName;
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			System.out.println("Eliminando Relacion");
			
			query = "create table " + bd.getNombre() + "_" + bd.getRut() + "." + relacion.getNombre() + "(\n";
			for(AtributoBean a : relacion.getAtributos()){
				System.out.println(a);
				System.out.println("Query: " + query);
				query = query + a.getNombre() + " ";
				if(a.getTipo().equals("Entero")){
					query = query + "int,\n";
				}else if(a.getTipo().equals("Real")){
					query = query + "float,\n";
				}else if(a.getTipo().equals("Cadena")){
					query = query + "text,\n";
				}else{
					return false;
				}
			}
			System.out.println("=========== FIN PRIMER FOR");
			
			query = query + "primary key(";
			for(AtributoBean a : relacion.getAtributos()){
				System.out.println("Query: " + query);
				if(a.getEsPrimario().equals("Sí")){
					query = query + a.getNombre() + ",";
				}
			}
			System.out.println("=========== FIN SEGUNDO FOR");
			
			query = query.substring(0,query.length()-1);
			query = query + ")\n)";
			
			System.out.println("Query a ejecutar: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Creando Relacion");
		
			query = "update esquemas set  fecha=(select current_timestamp) where rut=? and nombre=?";
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.setString(1,bd.getRut());
			ps.setString(2,bd.getNombre());
			ps.execute();
		
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
				System.out.println("Error en eliminarRelacion() intentar rollback --> " + ex.getMessage());
			}
			return false;
		} finally {
			Database.close(con);
		}

	}
	
	public static String guardarTuplas(EsquemaBean bd, RelacionBean selectedRelacion, String nombreRelacion){
		con = Database.getConnection();
		try{
			
			String query = "begin";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			query = "truncate table " + bd.getNombre() + "_" + bd.getRut() + "." + nombreRelacion;
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Tabla vaciada");
			
			query = "insert into " + bd.getNombre() + "_" + bd.getRut() + "." + nombreRelacion + " values\n";
						
			for(TuplaBean t : selectedRelacion.getTuplasBean()){
				query = query + "(";
				for(int i = 0 ; i < t.getAtributos().length ; i++){
					if(t.getAtributos()[i] == null){
						query = query + "null" + ",";
					}else if(selectedRelacion.getAtributos().get(i).getTipo().equals("Entero")){
						query = query + t.getAtributos()[i].toString() + ",";
					}else if(selectedRelacion.getAtributos().get(i).getTipo().equals("Real")){
						query = query + t.getAtributos()[i].toString() + ",";
					}else if(selectedRelacion.getAtributos().get(i).getTipo().equals("Cadena")){
						query = query + "'" + t.getAtributos()[i].toString() + "'" + ",";
					}
				}
				query = query.substring(0,query.length()-1);
				query = query + "),\n";
				
			}
			query = query.substring(0,query.length()-2);		
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			query = "update esquemas set  fecha=(select current_timestamp) where rut=? and nombre=?";
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.setString(1,bd.getRut());
			ps.setString(2,bd.getNombre());
			ps.execute();
			
			query = "commit";
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Nuevos datos insertados");

			return "ok";
			
		}catch (Exception ex) {
			System.out.println("Error en guardarTuplas() --> " + ex.getMessage());
			String query = "rollback";
			System.out.println("Query: " + query);
			
			try {
				ps = con.prepareStatement(query);
				ps.execute();
			} catch (Exception e) {
				System.out.println("Error en guardarTuplas() intentar rollback --> " + ex.getMessage());
				return "fatal";
			}
			return ex.getMessage();
		} finally {
			Database.close(con);
		}
	}
	
	public static String guardarTuplasVacias(EsquemaBean bd, RelacionBean selectedRelacion, String nombreRelacion){
		con = Database.getConnection();
		try{
					
			String query = "truncate table " + bd.getNombre() + "_" + bd.getRut() + "." + nombreRelacion;
			System.out.println("Query: " + query);
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			System.out.println("Tabla vaciada");
						
			query = "update esquemas set  fecha=(select current_timestamp) where rut=? and nombre=?";
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.setString(1,bd.getRut());
			ps.setString(2,bd.getNombre());
			ps.execute();

			return "ok";
			
		}catch (Exception ex) {
			System.out.println("Error en guardarTuplas() --> " + ex.getMessage());
			return "error";
		} finally {
			Database.close(con);
		}
	}
		
	public static EsquemaBean cargarRelaciones(EsquemaBean esquema, String rut){
		
		try{
			
			String query ="select tablename from pg_tables where schemaname=?";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,esquema.getNombre() + "_" + esquema.getRut());
			ResultSet rs = ps.executeQuery();
			
			List<RelacionBean> relacionesBean = new ArrayList<RelacionBean>();
			List<RelacionBean> relacionesEjercicios = new ArrayList<RelacionBean>();
	
			while(rs.next()){
				Pattern pat1 = Pattern.compile("_respuesta_ejercicio_[0-9]*");
				Pattern pat2 = Pattern.compile("_resp");
			    Matcher mat1 = pat1.matcher(rs.getString(1));
			    Matcher mat2 = pat2.matcher(rs.getString(1));
				if(!mat2.matches()){
					RelacionBean relacionBean = new RelacionBean();
					relacionBean.setNombre(rs.getString(1));
					if(mat1.matches()){
						relacionesEjercicios.add(relacionBean);
					}else{
						relacionesBean.add(relacionBean);
					}
				}
			}
			
			for(RelacionBean r : relacionesBean){
				String subquery1 = 	"select column_name, is_nullable, data_type " + 
									"from information_schema.columns " + 
									"where table_name=? and table_schema=? order by is_nullable;";	
				
				System.out.println(subquery1);
				
//				con = Database.getConnection();
				ps = con.prepareStatement(subquery1);
				ps.setString(1,r.getNombre());
				ps.setString(2,esquema.getNombre() + "_" + esquema.getRut());
				ResultSet rs1 = ps.executeQuery();
				
				List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
				while(rs1.next()){
					
					AtributoBean atributoBean = new AtributoBean();
					atributoBean.setNombre(rs1.getString(1));
					if(rs1.getString(2).equals("NO"))
						atributoBean.setEsPrimario("Sí");
					else
						atributoBean.setEsPrimario("No");
					if(rs1.getString(3).equals("integer"))
						atributoBean.setTipo("Entero");
					else if(rs1.getString(3).equals("text"))
						atributoBean.setTipo("Cadena");
					else
						atributoBean.setTipo("Real");
					atributosBean.add(atributoBean);
				}
				r.setAtributos(atributosBean);
				
				String subquery2 = 	"select * from " + esquema.getNombre() + "_" + esquema.getRut() + "." + r.getNombre();
				System.out.println(subquery2);
				
//				con = Database.getConnection();
				ps = con.prepareStatement(subquery2);
				rs1 = ps.executeQuery();
							
				List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
				while(rs1.next()){
					TuplaBean tuplaBean = new TuplaBean();
					Object atributo[] = new Object[r.getAtributos().size()];
					for(int i = 0 ; i < atributo.length ; i++){
						atributo[i] = rs1.getObject(i+1);
					}
					tuplaBean.setAtributos(atributo);
					tuplasBean.add(tuplaBean);
				}
				r.setTuplas(tuplasBean);
				
				String subquery3 = 	"create table load" + rut + "." + r.getNombre() + " as " +
									"select * from " + esquema.getNombre() + "_" + esquema.getRut() + "." + r.getNombre();
				System.out.println(subquery3);
				
				ps = con.prepareStatement(subquery3);
				ps.execute();
				
				System.out.println("Tabla load" + rut + "." + r.getNombre());
				
			}
			
			for(RelacionBean r : relacionesEjercicios){
				String subquery1 = 	"select column_name, is_nullable, data_type " + 
									"from information_schema.columns " + 
									"where table_name=? and table_schema=? order by is_nullable;";	
				
				System.out.println(subquery1);
				
//				con = Database.getConnection();
				ps = con.prepareStatement(subquery1);
				ps.setString(1,r.getNombre());
				ps.setString(2,esquema.getNombre() + "_" + esquema.getRut());
				ResultSet rs1 = ps.executeQuery();
				
				List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
				while(rs1.next()){
					
					AtributoBean atributoBean = new AtributoBean();
					atributoBean.setNombre(rs1.getString(1));
					if(rs1.getString(2).equals("NO"))
						atributoBean.setEsPrimario("Sí");
					else
						atributoBean.setEsPrimario("No");
					if(rs1.getString(3).equals("integer"))
						atributoBean.setTipo("Entero");
					else if(rs1.getString(3).equals("text"))
						atributoBean.setTipo("Cadena");
					else
						atributoBean.setTipo("Real");
					atributosBean.add(atributoBean);
				}
				r.setAtributos(atributosBean);
				
				String subquery2 = 	"select * from " + esquema.getNombre() + "_" + esquema.getRut() + "." + r.getNombre();
				System.out.println(subquery2);
				
//				con = Database.getConnection();
				ps = con.prepareStatement(subquery2);
				rs1 = ps.executeQuery();
							
				List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
				while(rs1.next()){
					TuplaBean tuplaBean = new TuplaBean();
					Object atributo[] = new Object[r.getAtributos().size()];
					for(int i = 0 ; i < atributo.length ; i++){
						atributo[i] = rs1.getObject(i+1);
					}
					tuplaBean.setAtributos(atributo);
					tuplasBean.add(tuplaBean);
				}
				r.setTuplas(tuplasBean);
				
				String subquery3 = 	"create table load" + rut + "." + r.getNombre() + " as " +
									"select * from " + esquema.getNombre() + "_" + esquema.getRut() + "." + r.getNombre();
				System.out.println(subquery3);
				
				ps = con.prepareStatement(subquery3);
				ps.execute();
				
				System.out.println("Tabla load" + rut + "." + r.getNombre());
				
			}
			
			esquema.setRelaciones(relacionesBean);
			esquema.setRelacionesEjercicios(relacionesEjercicios);
			
			return esquema;
			
		} catch (Exception ex) {
            System.out.println("Error en cargarRelacionesBean() --> " + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }
		
	}

}
