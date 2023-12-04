package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;
import java.util.List;

import util.AtributoBean;
import util.EsquemaBean;
import util.RelacionBean;
import util.TuplaBean;
import util.EstadisticasEjecutorAR;
import util.EstadisticasEjecutorSQL;

public class ConsultaDAO {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static Statement cs = null;

	public static RelacionBean verRelacion(String rel1, String rut){
		try{
			
			String query = "select * from load" + rut + "." + rel1;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean verRelacion(String res, String rel1, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
			
			String query = "create  table load" + rut + "." + res + " as " + 
						   "select * from load" + rut + "." + rel1;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}

	
	public static RelacionBean union(String rel1, String rel2, String rut){
		try{
					
			String query = "select * from load" + rut + "." + rel1 +
						   " union " +
						   "select * from load" + rut + "." + rel2;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean union(String res, String rel1, String rel2, String rut){
		try{
					
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
			
			String query = "create  table load" + rut + "." + res + " as " + 
						   "select * from load" + rut + "." + rel1 +
						   " union " +
						   "select * from load" + rut + "." + rel2;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}

	
	public static RelacionBean inter(String rel1, String rel2, String rut){
		try{
					
			String query = "select * from load" + rut + "." + rel1 +
						   " intersect " +
						   "select * from load" + rut + "." + rel2;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean inter(String res, String rel1, String rel2, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = "create  table load" + rut + "." + res + " as " +
						   "select * from load" + rut + "." + rel1 +
						   " intersect " +
						   "select * from load" + rut + "." + rel2;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean diferencia(String rel1, String rel2, String rut){
		try{
					
			String query = "select * from load" + rut + "." + rel1 +
						   " except " +
						   "select * from load" + rut + "." + rel2;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean diferencia(String res, String rel1, String rel2, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = "create  table load" + rut + "." + res + " as " +
						   "select * from load" + rut + "." + rel1 +
						   " except " +
						   "select * from load" + rut + "." + rel2;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean cruz(String rel1, String rel2, String rut){
		try{
					
			String query = "select * from load" + rut + "." + rel1 +
						   " , load" +
						   rut + "." + rel2;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean cruz(String res, String rel1, String rel2, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = "create  table load" + rut + "." + res + " as " +
						   "select * from load" + rut + "." + rel1 +
						   " , load" +
						   rut + "." + rel2;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}

	
	public static RelacionBean reunionNatural(String rel1, String rel2, String cond, String rut){
		try{
					
			String query = "select * from load" + rut+ "." + rel1 +
						   " natural join load" + rut + "." + rel2
						   + " WHERE "+ cond;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean reunionNatural(String res, String rel1, String rel2,String cond, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = "create  table  load" + rut + "." + res + " as " + 
						   "select * from load" + rut + "." + rel1 +
						   " natural join load" +
						   rut + "." + rel2 + " WHERE "+ cond;
			System.out.println("Query: " + query);
				
			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean reunionExtFull(String rel1, String rel2, String cond, String rut){
		try{
					
			String query = "select * from load" + rut + "." + rel1 +
					       " natural full outer join load" +
						   rut + "." + rel2 +  " WHERE "+ cond;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean reunionExtFull(String res, String rel1,  String rel2, String cond, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = "create  table load" + rut + "." + res + " as " +
						   "select * from load" + rut + "." + rel1 +
					       " natural full outer join load" +
						   rut + "." + rel2+ " WHERE "+ cond;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	 
	
	public static RelacionBean reunionExtIzq(String rel1, String rel2, String cond, String rut){
		try{
					
			String query = "select * from load" + rut + "." + rel1 +
					       " natural left outer join load" +
						   rut + "." + rel2+ " WHERE "+ cond;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean reunionExtIzq(String res, String rel1, String rel2, String cond, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = "create  table load" + rut + "." + res + " as " +
						   "select * from load" + rut + "." + rel1 +
					       " natural left outer join load" +
						   rut + "." + rel2 + " WHERE "+ cond;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean reunionExtDer(String rel1, String rel2, String cond, String rut){
		try{
					
			String query = "select * from load" + rut + "." + rel1 +
					       " natural right outer join load" +
						   rut + "." + rel2+ " WHERE "+ cond;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean reunionExtDer(String res, String rel1, String rel2, String cond, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = "create  table load" + rut + "." + res + " as " +
						   "select * from load" + rut + "." + rel1 +
					       " natural right outer join load" +
						   rut + "." + rel2+ " WHERE "+ cond;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean proyectar(String atributosProy, String rel, String rut){
		try{
					
			String query = "select " + atributosProy + " from load" + rut + "." + rel;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean proyectar(String res, String atributosProy, String rel, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query =	"create  table load" + rut + "." + res + " as " +
							"select " + atributosProy + " from load" + rut + "." + rel;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean seleccionar(String condiciones, String rel, String rut){
		try{
					
			String query = "select * from load" + rut + "." + rel + " where " + condiciones;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean seleccionar(String res, String condiciones, String rel, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query =	"create  table load" + rut + "." + res + " as " +
							"select * from load" + rut + "." + rel + " where " + condiciones;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean reunion(String rel1, String rel2, String condiciones, String rut){
		try{
					
			String query = 	"select * from load" + rut + "." + rel1 + " inner join load" +
							rut + "." + rel2 + " on " + condiciones;

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean reunion(String res, String rel1, String rel2, String condiciones, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = 	"create  table load" + rut + "." + res + " as " +
							"select * from load" + rut + "." + rel1 + " inner join load" +
							rut + "." + rel2 + " on " + condiciones;

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();
			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean renombrarRel(String rel, String nRel, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + nRel;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
					
			String query = 	"create  table load" + rut + "." + nRel + " as " +
							"select * from load" + rut + "." + rel;

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + nRel;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs2 = ps.executeQuery();
			ResultSetMetaData rsmd = rs2.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs2.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs2.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean renombrarRelAtt(String rel,String nRel, String[] atributos, String rut){ 
		try{
			
			String query2 = "drop table if exists load" + rut + "." + nRel;

			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
			System.out.println("query ejecutada: "+query2);		
			String query = 	"create  table load" + rut + "." + nRel + " as " +
							"select * from load" + rut + "." + rel;

			ps = con.prepareStatement(query);
			ps.execute();
			System.out.println("query ejecutada: "+query); 
			
			for (int i = 0; i < atributos.length; i++) {
				String query3 = "ALTER TABLE load" + rut + "." + nRel + " RENAME COLUMN " + atributos[i] + " TO " + nRel+"_"+atributos[i] + ";";
				ps = con.prepareStatement(query3);
				ps.execute();
				System.out.println("query ejecutada: "+query3);

			}
			
			String query1 = "select * from load" + rut + "." + nRel;
			System.out.println("Query ejecutada: " + query1);
			ps = con.prepareStatement(query1);
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean asigRenombrarRelAtt(String res, String rel,String nRel, String[] atributos, String rut){
		try{
			
			String query0 = "drop table if exists load" + rut + "." + res;
			System.out.println(query0);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query0);
			ps.execute();
			System.out.println("query ejecutada: "+query0);	
			
			String query =  "create table load" + rut + "." + res + " as " +
							"select * from load" + rut + "." + rel;

			ps = con.prepareStatement(query);
			ps.execute();
			System.out.println("query ejecutada: "+query);	
			
			for (int i = 0; i < atributos.length; i++) {
				String query1 = "ALTER TABLE load" + rut + "." + res + " RENAME COLUMN " + atributos[i] + " TO " + nRel+"_"+atributos[i] + ";";
				ps = con.prepareStatement(query1);
				ps.execute();
				System.out.println("query ejecutada: "+query1);

			}
						
			
			String query2 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query2);
			ps = con.prepareStatement(query2);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean asigRenombrarRel(String res, String rel, String atributos, String rut){
		try{
			
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
			
			String query =  "create table load" + rut + "." + res + " as " +
							"select " + atributos + " from load" + rut + "." + rel;

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();

			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean division(String adiv11Y, String adiv11X, String rel1, String adiv2X, String rel2, String rut){
		try{  // adiv11Y= atributo R.Y ; adiv11X = atributo R.X ; rel1 = R ; adiv2X = atributo S.X; rel2 = S
		
			String query =  "select " + adiv11Y + " from load"+rut+"."+rel1 + 
						   	    " where " +adiv11X+ " in (select " + adiv2X+ " from load"+rut+"."+rel2+")"+
						   	    " group by "+ adiv11Y +
						   	    " having count(*) = (select count(*) from load"+rut+"."+rel2+")"
						   			;
			
			System.out.println(" proceso de ejecucion Query: " + query);
			
		con = Database.getConnection();
		ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println(" query ejecutada");
		
		RelacionBean relacionBean = new RelacionBean();
		List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
					
		while(rs.next()){
			TuplaBean tuplaBean = new TuplaBean();
			Object atributo[] = new Object[rsmd.getColumnCount()];
			for(int i = 0 ; i < atributo.length ; i++){
				atributo[i] = rs.getObject(i+1);
			}
			tuplaBean.setAtributos(atributo);
			tuplasBean.add(tuplaBean);
		}
		
		List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
		for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
			AtributoBean atributoBean = new AtributoBean();
			atributoBean.setNombre(rsmd.getColumnName(i+1));
			if(rsmd.getColumnTypeName(i+1).equals("text"))
				atributoBean.setTipo("Cadena");
			else if(rsmd.getColumnTypeName(i+1).equals("float8"))
				atributoBean.setTipo("Real");
			else
				atributoBean.setTipo("Entero");
			System.out.println(atributoBean.getTipo());
			atributosBean.add(atributoBean);
		}
		
		relacionBean.setTuplas(tuplasBean);
		relacionBean.setAtributos(atributosBean);
	
		return relacionBean;
		
		} catch (Exception ex) {
		RelacionBean err = new RelacionBean();
		err.setNombre(ex.getMessage().toString());
        return err;
		} finally {
        Database.close(con);
    	}
		
	}
	
	public static RelacionBean division(String res, String adiv11Y, String adiv11X, String rel1, String adiv2X, String rel2, String rut){
		try{
					
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
			
			String query =  "create table load" + rut + "." + res + " as " +
							"select " + adiv11Y + " from load"+rut+"."+rel1 + 
			   	    		" where " +adiv11X+ " in (select " + adiv2X+ " from load"+rut+"."+rel2+")"+
			   	    		" group by "+ adiv11Y +
			   	    		" having count(*) = (select count(*) from load"+rut+"."+rel2+")";

			ps = con.prepareStatement(query);
			ps.execute();
			System.out.println("Query ejecutada: " + query);
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean agrupar(String atributosAg, String fagrega,String rel1, String rut){
		try{
		String query = "select " + atributosAg +","+ " "+ fagrega+ " from load" + rut + "." + rel1+ " "+
				       "group by" + " "+ atributosAg;
		System.out.println("Query: " + query);
		
		
		
		con = Database.getConnection();
		ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		
		RelacionBean relacionBean = new RelacionBean();
		List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
					
		while(rs.next()){
			TuplaBean tuplaBean = new TuplaBean();
			Object atributo[] = new Object[rsmd.getColumnCount()];
			for(int i = 0 ; i < atributo.length ; i++){
				atributo[i] = rs.getObject(i+1);
			}
			tuplaBean.setAtributos(atributo);
			tuplasBean.add(tuplaBean);
		}
		
		List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
		for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
			AtributoBean atributoBean = new AtributoBean();
			atributoBean.setNombre(rsmd.getColumnName(i+1));
			if(rsmd.getColumnTypeName(i+1).equals("text"))
				atributoBean.setTipo("Cadena");
			else if(rsmd.getColumnTypeName(i+1).equals("float8"))
				atributoBean.setTipo("Real");
			else
				atributoBean.setTipo("Entero");
			System.out.println(atributoBean.getTipo());
			atributosBean.add(atributoBean);
		}
		
		relacionBean.setTuplas(tuplasBean);
		relacionBean.setAtributos(atributosBean);
	
		return relacionBean;
		
		} catch (Exception ex) {
		RelacionBean err = new RelacionBean();
		err.setNombre(ex.getMessage().toString());
        return err;
		} finally {
        Database.close(con);
    	}
		
	}
	
	public static RelacionBean agrupar(String res, String atributosAg, String fagrega,String rel1, String rut){
		try{
					
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
			
			String query = "create  table load" + rut + "." + res + " as " + 
						   "select " + atributosAg +","+ " "+ fagrega+ " from load" + rut + "." + rel1+ " "+
						   "group by" + " "+ atributosAg;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean ordenar(String atributosOr, String tipoOr, String rel1, String rut){
		try{
		String query = "select *" + " from load" + rut + "." + rel1+
				   " order by " + atributosOr + " " + tipoOr;
		System.out.println("Query: " + query);
		
		
		
		con = Database.getConnection();
		ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		
		RelacionBean relacionBean = new RelacionBean();
		List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
					
		while(rs.next()){
			TuplaBean tuplaBean = new TuplaBean();
			Object atributo[] = new Object[rsmd.getColumnCount()];
			for(int i = 0 ; i < atributo.length ; i++){
				atributo[i] = rs.getObject(i+1);
			}
			tuplaBean.setAtributos(atributo);
			tuplasBean.add(tuplaBean);
		}
		
		List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
		for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
			AtributoBean atributoBean = new AtributoBean();
			atributoBean.setNombre(rsmd.getColumnName(i+1));
			if(rsmd.getColumnTypeName(i+1).equals("text"))
				atributoBean.setTipo("Cadena");
			else if(rsmd.getColumnTypeName(i+1).equals("float8"))
				atributoBean.setTipo("Real");
			else
				atributoBean.setTipo("Entero");
			System.out.println(atributoBean.getTipo());
			atributosBean.add(atributoBean);
		}
		
		relacionBean.setTuplas(tuplasBean);
		relacionBean.setAtributos(atributosBean);
	
		return relacionBean;
		
		} catch (Exception ex) {
		RelacionBean err = new RelacionBean();
		err.setNombre(ex.getMessage().toString());
        return err;
		} finally {
        Database.close(con);
    	}
		
	}
	
	public static RelacionBean ordenar(String res, String atributosOr, String tipoOr, String rel1, String rut){
		try{
					
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
			
			String query = "create  table load" + rut + "." + res + " as " + 
						   "select *"+ " from load" + rut + "." + rel1+
						   " order by" + atributosOr +" "+ tipoOr;
			System.out.println("Query: " + query);

			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean funcionAgregacion(String agregacion, String atributoFa,String rel1, String rut){
		try{
			String query;
			if(agregacion.equalsIgnoreCase("avg")) {
				query = "select ROUND(" + agregacion +"("+ atributoFa +"),2) AS avg"+ " from load" + rut + "." + rel1;
				System.out.println("Query: " + query);
				
			}else {
				query = "select " + agregacion +"("+ atributoFa +")"+ " from load" + rut + "." + rel1;
				System.out.println("Query: " + query);
				
			}
			
								
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
		
			return relacionBean;
		
		} catch (Exception ex) {
		RelacionBean err = new RelacionBean();
		err.setNombre(ex.getMessage().toString());
        return err;
		} finally {
        Database.close(con);
    	}
		
	}
	
	public static RelacionBean funcionAgregacion(String res, String agregacion, String atributoFa,String rel1, String rut){
		try{
			String query;		
			String query2 = "drop table if exists load" + rut + "." + res;
			System.out.println(query2);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query2);
			ps.execute();
			
			if(agregacion.equalsIgnoreCase("avg")) {
				query = "create  table load" + rut + "." + res + " as " + 
						   "select ROUND(" + agregacion +"("+ atributoFa +"),2) AS avg"+ 
						   " from load" + rut + "." + rel1;
				System.out.println("Query: " + query);
				
			}else {
				query = "create  table load" + rut + "." + res + " as " + 
						   "select " + agregacion +"("+ atributoFa +")"+ 
						   " from load" + rut + "." + rel1;
				System.out.println("Query: " + query);
				
			}
			
			//SELECT ROUND(SUM(columna), 2) FROM tabla;
			
			ps = con.prepareStatement(query);
			ps.execute();
			
			String query1 = "select * from load" + rut + "." + res;
			System.out.println("Query: " + query1);
			ps = con.prepareStatement(query1);
			ResultSet rs1 = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs1.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs1.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs1.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(res);
		
			return relacionBean;
			
		} catch (Exception ex) {
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	
	
	public static RelacionBean verRespuesta(String rel, String bd){
		try{
			
			String query = "select * from " + bd + "." + rel;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(rel);
			return relacionBean;
			
		} catch (Exception ex) {
			System.out.println("Error en verRespuesta() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }
	}
	
	public static RelacionBean verRespuestaEsperada(String rel, EsquemaBean esquema){
		try{
			
			String query = "select * from " + esquema.getNombre() + "_" + esquema.getRut() + "." + rel;
			System.out.println("Query: " + query);

			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			RelacionBean relacionBean = new RelacionBean();
			List<TuplaBean> tuplasBean = new ArrayList<TuplaBean>();
						
			while(rs.next()){
				TuplaBean tuplaBean = new TuplaBean();
				Object atributo[] = new Object[rsmd.getColumnCount()];
				for(int i = 0 ; i < atributo.length ; i++){
					atributo[i] = rs.getObject(i+1);
				}
				tuplaBean.setAtributos(atributo);
				tuplasBean.add(tuplaBean);
			}
			
			List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
			for(int i = 0 ; i < rsmd.getColumnCount() ; i++){
				AtributoBean atributoBean = new AtributoBean();
				atributoBean.setNombre(rsmd.getColumnName(i+1));
				if(rsmd.getColumnTypeName(i+1).equals("text"))
					atributoBean.setTipo("Cadena");
				else if(rsmd.getColumnTypeName(i+1).equals("float8"))
					atributoBean.setTipo("Real");
				else
					atributoBean.setTipo("Entero");
				System.out.println(atributoBean.getTipo());
				atributosBean.add(atributoBean);
			}
			
			relacionBean.setTuplas(tuplasBean);
			relacionBean.setAtributos(atributosBean);
			relacionBean.setNombre(rel);
			return relacionBean;
			
		} catch (Exception ex) {
			System.out.println("Error en verRespuesta() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }
	}

	public static void insertarEstadisticas(EstadisticasEjecutorAR estadisticas){
	    try {
	        con = Database.getConnection();
	        

	        String insertSQL = "INSERT INTO ejecutor_ar(rut, bd, query_ejecutada, operador, query_correcta, query_incorrecta, clasificacion_error, descripcion_error, fecha_hora_ejecucion) "
	        					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, (select current_timestamp))";
	        PreparedStatement preparedStatement = con.prepareStatement(insertSQL);

	        preparedStatement.setString(1, estadisticas.getRut());
	        preparedStatement.setString(2, estadisticas.getBd());
	        preparedStatement.setString(3, estadisticas.getQuery());
	        preparedStatement.setString(4, estadisticas.getOperador());
	        preparedStatement.setBoolean(5, estadisticas.isQuery_correcta());
	        preparedStatement.setBoolean(6, estadisticas.isQuery_incorrecta());
	        preparedStatement.setString(7, estadisticas.getClasificacion_error());
	        preparedStatement.setString(8, estadisticas.getDescripcion_error());
	        preparedStatement.executeUpdate();
	        
	    } catch (Exception ex) {
	        System.out.println("Error en insertarEstadisticasAR(): " + ex.getMessage());
	    } finally {
	        Database.close(con);
	    }
	}
	
	public static List<EstadisticasEjecutorAR> cargarEstEjecAR(){
		try{
			String query = 	"select rut,bd, query_ejecutada, operador, query_correcta, "
							+ "query_incorrecta, clasificacion_error, "
							+ "fecha_hora_ejecucion from ejecutor_ar";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticasEjecutorAR> statsar = new ArrayList<EstadisticasEjecutorAR>();
			
			while(rs.next()){
				EstadisticasEjecutorAR e = new EstadisticasEjecutorAR();
				e.setRut(rs.getString(1));
				e.setBd(rs.getString(2));
				e.setQuery(rs.getString(3));
				e.setOperador(rs.getString(4));
				e.setQuery_correcta(rs.getBoolean(5));
				e.setQuery_incorrecta(rs.getBoolean(6));
				e.setClasificacion_error(rs.getString(7));
				e.setFecha(rs.getString(8));
				statsar.add(e);
			}
			
			System.out.println("Datos Ejecutor AR cargados");
			
			return statsar;
		} catch (Exception ex) {
			System.out.println("Error en cargarEstEjecAR() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}
	
	public static List<EstadisticasEjecutorAR> cargarEstEjecARRut(String rut){
		try{
			String query = 	"select bd, query_ejecutada, operador, query_correcta, "
							+ "query_incorrecta, clasificacion_error, "
							+ "fecha_hora_ejecucion from ejecutor_ar"
							+ " where rut = '"+ rut+"'";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			//ps.setString(2,rut);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticasEjecutorAR> statsar = new ArrayList<EstadisticasEjecutorAR>();
			
			while(rs.next()){
				EstadisticasEjecutorAR e = new EstadisticasEjecutorAR();
				//e.setId(rs.getInt(1));
				//e.setRut(rs.getString(2));
				e.setBd(rs.getString(1));
				e.setQuery(rs.getString(2));
				e.setOperador(rs.getString(3));
				e.setQuery_correcta(rs.getBoolean(4));
				e.setQuery_incorrecta(rs.getBoolean(5));
				e.setClasificacion_error(rs.getString(6));
				//e.setDescripcion_error(rs.getString(9));
				e.setFecha(rs.getString(7));
				statsar.add(e);
			}
			
			System.out.println("Datos Ejecutor AR cargados");
			
			return statsar;
		} catch (Exception ex) {
			System.out.println("Error en cargarEstEjecARRUT() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}
	
	
}
