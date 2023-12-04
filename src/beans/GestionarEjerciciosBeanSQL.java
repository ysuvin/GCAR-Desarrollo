package beans;
	 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import dao.ConsultaDAOSQL;
import util.AtributoBean;
import util.Ejercicio;
import util.EsquemaBean;
import util.RelacionBean;
import util.TuplaBean;
import util.UserBean;
import util.EstadisticasEjecutorSQL;
 
@ManagedBean(name="dtGestionarEjerciciosBeanSQL")
@ViewScoped
public class GestionarEjerciciosBeanSQL implements Serializable {
     
	private String tableName;
	private List<Object[]> data;
	private List<String> columnNames;
	private String query = "";
	private String queryList = "";
	
	private Ejercicio selectedEjercicio;

	private String errorMessage;
	
	private boolean primerCarga = true;
	
    @PreDestroy
    public void destroy(){
//    	Util.reloadBd();
    }
	

	public String getQueryList() {
		return queryList;
	}

	public void setQueryList(String queryList) {
		this.queryList = queryList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Object[]> getData(){
		return data;
	}
	
	public List<String> getColumnNames(){
		return columnNames;
	}
	
	public void setData(List<Object[]> data){
		this.data = data;
	}
	
	public void setColumnNames(List<String> columnNames){
		this.columnNames = columnNames;
	}
    
    public String getQuery(){
    	return query;
    }
    
	public void setQuery(String query) {
		this.query = query;
	}
	
	public boolean isPrimerCarga() {
	    return primerCarga;
	}

	public void setPrimerCarga(boolean primerCarga) {
	    this.primerCarga = primerCarga;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	private boolean clausulaBloqueada(String query){
		if(		/*Revisa si la relacion contiene palabras bloqueadas*/
				
				//DDL
				query.contains("CREATE") ||
				query.contains("create") ||
				query.contains("ALTER") ||
				query.contains("alter") ||
				query.contains("DROP") ||
				query.contains("drop") ||
				query.contains("TRUNCATE") ||
				query.contains("truncate") ||
				query.contains("GRANT") ||
				query.contains("grant") ||
				query.contains("REVOKE") ||
				query.contains("revoke") ||
				
				//DML
				query.contains("INSERT") ||
				query.contains("insert") ||
				query.contains("UPDATE") ||
				query.contains("update") ||
				query.contains("delete") ||
				query.contains("DELETE") 
				
				){
			return false;
		}else{
			return true;
		}
	}
 
	private String obtenerClausulaBloqueada(String query) {
	    String clausulaSQL = null;

	    if (query.contains("CREATE") || query.contains("create") || query.contains("ALTER") || query.contains("alter") || query.contains("DROP") || query.contains("drop") ||
	            query.contains("TRUNCATE") || query.contains("truncate") || query.contains("GRANT") || query.contains("grant") || query.contains("REVOKE") || query.contains("revoke") ||
	            query.contains("INSERT") || query.contains("insert") || query.contains("UPDATE") || query.contains("update") || query.contains("DELETE") || query.contains("delete")) {
	    	
	        if (query.contains("CREATE") || query.contains("create")) {
	            clausulaSQL = "CREATE";
	        } else if (query.contains("ALTER") || query.contains("truncate")) {
	            clausulaSQL = "ALTER";
	        } else if (query.contains("DROP") || query.contains("drop")) {
	            clausulaSQL = "DROP";
	        } else if (query.contains("TRUNCATE") || query.contains("inner join")) {
	            clausulaSQL = "TRUNCATE";
	        } else if (query.contains("GRANT") || query.contains("grant")) {
	            clausulaSQL = "GRANT";
	        } else if (query.contains("REVOKE") || query.contains("revoke") ) {
	            clausulaSQL = "REVOKE";
	        } else if (query.contains("INSERT") || query.contains("insert") ) {
	            clausulaSQL = "INSERT";
	        } else if (query.contains("UPDATE") || query.contains("update") ) {
	            clausulaSQL = "UPDATE";
	        } else if (query.contains("DELETE") || query.contains("delete")) {
	            clausulaSQL = "DELETE";
	        }
	    }

	    return clausulaSQL;
	}

	private String obtenerClausula2(String query) {
	    String clausulaSQL = "SELECT";

	    if (query.contains("WHERE") || query.contains("where") || query.contains("ORDER BY") || query.contains("order by") || query.contains("JOIN") || query.contains("join") ||
	            query.contains("INNER JOIN") || query.contains("inner join") || query.contains("LEFT JOIN") || query.contains("left join") || query.contains("RIGHT JOIN") || query.contains("right join") ||
	            query.contains("FULL JOIN") || query.contains("full join") || query.contains("GROUP BY") || query.contains("group by") || query.contains("HAVING") || query.contains("having")) {
	    	
	        if (query.contains("WHERE") || query.contains("where")) {
	            clausulaSQL = "WHERE";
	        } else if (query.contains("ORDER BY") || query.contains("order by")) {
	            clausulaSQL = "ORDER BY";
	        } else if (query.contains("JOIN") || query.contains("join")) {
	            clausulaSQL = "JOIN";
	        } else if (query.contains("INNER JOIN") || query.contains("inner join")) {
	            clausulaSQL = "INNER JOIN";
	        } else if (query.contains("LEFT JOIN") || query.contains("left join")) {
	            clausulaSQL = "LEFT JOIN";
	        } else if (query.contains("RIGHT JOIN") || query.contains("right join") ) {
	            clausulaSQL = "RIGHT JOIN";
	        } else if (query.contains("FULL JOIN") || query.contains("full join") ) {
	            clausulaSQL = "FULL JOIN";
	        } else if (query.contains("GROUP BY") || query.contains("group by") ) {
	            clausulaSQL = "GROUP BY";
	        } else if (query.contains("HAVING") || query.contains("having")) {
	            clausulaSQL = "HAVING";
	        }
	    }

	    return clausulaSQL;
	}
	
	private String obtenerClausula(String query) {
	    String clausulaSQL = "SELECT";
    	
	        if (query.contains("WHERE") || query.contains("where")) {
	            clausulaSQL = "WHERE";
	        }else if (query.contains("SELECT") && query.contains("DISTINCT") || query.contains("select") && query.contains("distinct")) {
	            clausulaSQL = "DISTINCT"; 
			}else if (query.contains("ORDER BY") || query.contains("order by")) {
	            clausulaSQL = "ORDER BY";	       
	        } else if (query.contains("INNER") && query.contains("JOIN") || query.contains("inner") && query.contains("join")) {
	            clausulaSQL = "INNER JOIN";
	        } else if (query.contains("LEFT") && query.contains("JOIN") || query.contains("left") && query.contains("join")) {
	            clausulaSQL = "LEFT JOIN";
	        } else if (query.contains("RIGHT") && query.contains("JOIN") || query.contains("right") && query.contains("join") ) {
	            clausulaSQL = "RIGHT JOIN";
	        } else if (query.contains("FULL") && query.contains("JOIN") || query.contains("full") && query.contains("join") ) {
	            clausulaSQL = "FULL JOIN";
	        } else if (query.contains("HAVING") || query.contains("having")) {
	            clausulaSQL = "HAVING";
	        }else if (query.contains("GROUP BY") || query.contains("group by") ) {
	            clausulaSQL = "GROUP BY";	        
	        }else if (query.contains("JOIN") || query.contains("join")) {
	            clausulaSQL = "JOIN";
	        }
	    

	    return clausulaSQL;
	}

	private String clasificarError(String errorMessage) {
        if (errorMessage == null) {
            return null;
        }
        if (errorMessage.contains("no tables specified is not valid")) {
            return "Tabla no especificada";
        }
        if (errorMessage.contains("column") && errorMessage.contains("does not exist")) {
            return "Columna inexistente";
        }
        if (errorMessage.contains("column") && errorMessage.contains("specified more than once")) {
            return "Columna duplicada";
        }
        if (errorMessage.contains("relation") && errorMessage.contains("does not exist")) {
            return "Relacion inexistente";
        }
        if (errorMessage.contains("syntax error at or near")) {
            return "Error de sintaxis";
        }
        if (errorMessage.contains("function") && errorMessage.contains("does not exist")) {
            return "Funcion desconocida";
        }
        if (errorMessage.contains("argument of WHERE")&& errorMessage.contains("not type")) {
            return "Argumento Where incorrecto";
        }
        if (errorMessage.contains("column reference") && errorMessage.contains("is ambiguous")) {
            return "Referencia columna ambigua";
        }
        if (errorMessage.contains("No results were returned by the query")) {
            return "Consulta vacia";
        }
        if (errorMessage.contains("syntax error at end of input")) {
            return "Error de sintaxis";
        }
        if (errorMessage.contains("column") && errorMessage.contains("must appear in the")) {
            return "Referencia columna ambigua";
        }
        if (errorMessage.contains("argument") && errorMessage.contains("type boolean")) {
            return "Error de tipo booleano";
        }
        if (errorMessage.contains("operator does not exist") && errorMessage.contains("No operator matches")) {
            return "Error de condicion";
        }
        if (errorMessage.contains("invalid input") && errorMessage.contains("syntax")) {
            return "Error de condicion";
        }
        if (errorMessage.contains("FULL JOIN") && errorMessage.contains("only supported")) {
            return "Full join no compatible";
        }
        return "otro";
}


	
	
	public void ejecutar(){
		
		if(selectedEjercicio != null){
			query = selectedEjercicio.getQuery(); 
		}

		System.out.println("query: " + query);
		

		if(clausulaBloqueada(query) == false){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext(); 
			UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication() 
				    .getELResolver().getValue(elContext, null, "usuario");
			EsquemaBean esquemaActual = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			tableName = "";
			columnNames = null;
			data = null;
			//estadisticas 
			EstadisticasEjecutorSQL estadisticas = new EstadisticasEjecutorSQL();
			estadisticas.setRut(userBean.getRut());
			estadisticas.setBd(esquemaActual.getNombre());
			estadisticas.setQuery(query);
			estadisticas.setClausula(obtenerClausulaBloqueada(query));
			estadisticas.setQuery_correcta(false);
	        estadisticas.setQuery_incorrecta(true);
	        estadisticas.setClasificacion_error("Clausula bloqueada");
	        estadisticas.setDescripcion_error("Clausula no permitida en ejecutor SQL GCAR");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String fechaEjecucion = ConsultaDAOSQL.fechaHora(timestamp);
			estadisticas.setFecha(fechaEjecucion);	
			ConsultaDAOSQL.insertarEstadisticas(estadisticas);
			
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar la consulta","");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			errorMessage = "Las clausulas de modificacion de datos no estan permitidas";
			// Limpia los datos
	        data = null;
	        tableName = "";
	        columnNames = null;

			
			
		}

		else{

			ELContext elContext = FacesContext.getCurrentInstance().getELContext(); 
			UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication() 
				    .getELResolver().getValue(elContext, null, "usuario");
			EsquemaBean esquemaActual = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			
			RelacionBean resultado = new RelacionBean(); 
			
			resultado = ConsultaDAOSQL.ejecutarQuery(query,userBean.getRut()); 
			tableName = resultado.getNombre();
			
			columnNames = new ArrayList<String>();
			data = new ArrayList<Object[]>();
			
			/////INSERT ejecutor_sql
			EstadisticasEjecutorSQL estadisticas = new EstadisticasEjecutorSQL();
			estadisticas.setRut(userBean.getRut());
			estadisticas.setBd(esquemaActual.getNombre());
			estadisticas.setQuery(query);
			estadisticas.setClausula(obtenerClausula(query));
			
				
			if(resultado.getTuplasBean()!= null){
					
					for(AtributoBean a : resultado.getAtributos())
						columnNames.add(a.getNombre());
					
					for(TuplaBean t : resultado.getTuplasBean())
						data.add(t.getAtributos());
					
					if(selectedEjercicio != null){
						queryList = selectedEjercicio.getQueryList();
					}						
					queryList = queryList + query + "\n";
					if(selectedEjercicio != null){
						selectedEjercicio.setQueryList(queryList);
					}
				
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Consulta realizada satisfactoriamente","");
		       		FacesContext.getCurrentInstance().addMessage(null, msg);
		       		errorMessage = null;
		       		primerCarga = false;
		       		
		       	//estadisticas
		       		estadisticas.setQuery_correcta(true);
		            estadisticas.setQuery_incorrecta(false);
		            estadisticas.setClasificacion_error(null);
		            estadisticas.setDescripcion_error(null);
		            
			}else{
					tableName = "";
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar la consulta","");
		       		FacesContext.getCurrentInstance().addMessage(null, msg);
					System.out.println("Error: " + resultado.getNombre());
					
					errorMessage = resultado.getNombre();
					// Limpia los datos
			        data = null;
			        tableName = "";
			        columnNames = null;
			        
				//estadisticas
					estadisticas.setQuery_correcta(false);
		            estadisticas.setQuery_incorrecta(true);
		            estadisticas.setClasificacion_error(clasificarError(resultado.getNombre()));
		            estadisticas.setDescripcion_error(resultado.getNombre());
				}
		//estadisticas
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String fechaEjecucion = ConsultaDAOSQL.fechaHora(timestamp);
			estadisticas.setFecha(fechaEjecucion);	
			ConsultaDAOSQL.insertarEstadisticas(estadisticas);	
			}
		}
	public void limpiarResultados() {
	    data = null;
	    tableName = null;
	    columnNames = null;
	    errorMessage = null;
	    primerCarga = true;
	}



}


