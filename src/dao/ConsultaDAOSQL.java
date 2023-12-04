package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
 
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import util.AtributoBean;
import util.EsquemaBean;
import util.RelacionBean;
import util.Resultado;
import util.TuplaBean;
import util.EstadisticasEjecutorSQL;
import util.User;

public class ConsultaDAOSQL {
	
	static Connection con = null;
	//static Connection conn = null;
	static PreparedStatement ps = null;
	static PreparedStatement ps0 = null;
	static Statement cs = null;

	public static RelacionBean ejecutarQuery(String rel, String bd){
		try{
			
			String query0 = "SET search_path TO 'load"+bd+"'"; 
			
			String query = rel; 
			
			System.out.println("Schema a cargar: load" + bd);
			System.out.println("Query a ejecutar: " + query);
			
			con = Database.getConnection();
			//String schema = con.getSchema();
		    //System.out.println("El esquema actual es: " + schema);
			ps0 = con.prepareStatement(query0); //ejecuta query0 que establece schema actual 
			ps0.execute();
			
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
			System.out.println("Error en verRespuesta() -->" + ex.getMessage());
			RelacionBean err = new RelacionBean();
			err.setNombre(ex.getMessage().toString());
            return err;
        } finally {
            Database.close(con);
        }
	}
	

	public static void insertarEstadisticas(EstadisticasEjecutorSQL estadisticas){
	    try {
	        con = Database.getConnection();
	        

	        String insertSQL = "INSERT INTO ejecutor_sql(rut,bd, query_ejecutada, clausulas_sql, query_correcta, query_incorrecta, clasificacion_error, descripcion_error, fecha_hora_ejecucion) "
	        					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, (select current_timestamp))";
	        PreparedStatement preparedStatement = con.prepareStatement(insertSQL);

	        preparedStatement.setString(1, estadisticas.getRut());
	        preparedStatement.setString(2, estadisticas.getBd());
	        preparedStatement.setString(3, estadisticas.getQuery());
	        preparedStatement.setString(4, estadisticas.getClausula());
	        preparedStatement.setBoolean(5, estadisticas.isQuery_correcta());
	        preparedStatement.setBoolean(6, estadisticas.isQuery_incorrecta());
	        preparedStatement.setString(7, estadisticas.getClasificacion_error());
	        preparedStatement.setString(8, estadisticas.getDescripcion_error());
	        preparedStatement.executeUpdate();
	        
	    } catch (Exception ex) {
	        System.out.println("Error en insertarEstadisticasSQL(): " + ex.getMessage());
	    } finally {
	        Database.close(con);
	    }
	}
	
	
	public static List<EstadisticasEjecutorSQL> cargarEstEjecSQL(){
		try{
			String query = 	"select rut,bd, query_ejecutada, clausulas_sql, query_correcta, "
							+ "query_incorrecta, clasificacion_error, "
							+ "fecha_hora_ejecucion from ejecutor_sql";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticasEjecutorSQL> statsql = new ArrayList<EstadisticasEjecutorSQL>();
			
			while(rs.next()){
				EstadisticasEjecutorSQL e = new EstadisticasEjecutorSQL();
				e.setRut(rs.getString(1));
				e.setBd(rs.getString(2));
				e.setQuery(rs.getString(3));
				e.setClausula(rs.getString(4));
				e.setQuery_correcta(rs.getBoolean(5));
				e.setQuery_incorrecta(rs.getBoolean(6));
				e.setClasificacion_error(rs.getString(7));
				e.setFecha(rs.getString(8));
				statsql.add(e);
			}
			
			System.out.println("Datos Ejecutor SQL cargados");
			
			return statsql;
		} catch (Exception ex) {
			System.out.println("Error en cargarEstEjecSQL() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}
	
	public static List<EstadisticasEjecutorSQL> cargarEstEjecSQLRut(String rut){
		try{
			String query = 	"select bd, query_ejecutada, clausulas_sql, query_correcta, "
							+ "query_incorrecta, clasificacion_error, "
							+ "fecha_hora_ejecucion from ejecutor_sql"
							+ " where rut = '"+ rut+"'";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			//ps.setString(2,rut);
			ResultSet rs = ps.executeQuery();
			
			List<EstadisticasEjecutorSQL> statsql = new ArrayList<EstadisticasEjecutorSQL>();
			
			while(rs.next()){
				EstadisticasEjecutorSQL e = new EstadisticasEjecutorSQL();
				//e.setId(rs.getInt(1));
				//e.setRut(rs.getString(2));
				e.setBd(rs.getString(1));
				e.setQuery(rs.getString(2));
				e.setClausula(rs.getString(3));
				e.setQuery_correcta(rs.getBoolean(4));
				e.setQuery_incorrecta(rs.getBoolean(5));
				e.setClasificacion_error(rs.getString(6));
				//e.setDescripcion_error(rs.getString(9));
				e.setFecha(rs.getString(7));
				statsql.add(e);
			}
			
			System.out.println("Datos Ejecutor SQL cargados");
			
			return statsql;
		} catch (Exception ex) {
			System.out.println("Error en cargarEstEjecSQL() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}
	
	
	public static List<User> cargarDatosUser(String rut){
		try{
			String query = 	"select nombre1, paterno, materno"
							+ " from usuarios"
							+ " where rut = '"+ rut+"'";
			System.out.println("Query: " + query);
			
			con = Database.getConnection();
			ps = con.prepareStatement(query);
			//ps.setString(2,rut);
			ResultSet rs = ps.executeQuery();
			
			List<User> nombreUser = new ArrayList<User>();
			
			while(rs.next()){
				User e = new User();
				e.setNombre1(rs.getString(1));
				e.setPaterno(rs.getString(2));
				e.setMaterno(rs.getString(3));
				nombreUser.add(e);
			}
			
			System.out.println("Datos del usuario cargados");
			
			return nombreUser;
		} catch (Exception ex) {
			System.out.println("Error en cargarDatosUserL() -->" + ex.getMessage());
			return null;
        } finally {
        	Database.close(con);
        }
	}
	
	
	public static String fechaHora(Timestamp timestamp) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return dateFormat.format(timestamp);
	}
	
}
