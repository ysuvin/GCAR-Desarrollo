package beans;
	 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import dao.ConsultaDAO;
import parser.AlgebraRelacionalLexer;
import parser.AlgebraRelacionalParser;
import util.AtributoBean;
import util.EsquemaBean;
import util.RelacionBean;
import util.TuplaBean;
import util.UserBean;
 
@ManagedBean(name="dtHacerConsultaBean")
@ViewScoped
public class HacerConsultaBean implements Serializable {
     
	//Ejemplo
	private String tableName;
	private List<Object[]> data;
	private List<String> columnNames;
	private String query = "";
	private String queryList = "";
	
	
	
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
	
	@PostConstruct
	public void init(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
       
		if(esquemaBean == null){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
			} catch (Exception e) {
				System.out.println("No pude redireccionar");
			}
		}
	}
	
	@PreDestroy
	public void destroy(){
//		Util.reloadBd();
	}
	
	private boolean nombreValido(String query){
		if(		/*Revisa si la relacion auxiliar contiene palabras reservadas*/
				query.contains("seleccionar") || query.contains("SELECCIONAR") ||
				query.contains("proyectar") ||  query.contains("PROYECTAR") ||
				query.contains("union") || query.contains("UNION") ||
				query.contains("inter") || query.contains("INTER") ||
				query.contains("diferencia") || query.contains("DIFERECIA") ||
				query.contains("cruz") || query.contains("CRUZ") ||
				query.contains("reunion") || query.contains("REUNION") ||
				query.contains("reunion_natural") || query.contains("REUNION_NATURAL") ||
				query.contains("reunion_ext_izq") || query.contains("REUNION_EXT_IZQ") ||
				query.contains("reunion_ext_der") || query.contains("REUNION_EXT_DER") ||
				query.contains("reunion_ext_full") || query.contains("REUNION_EXT_FULL") ||
				query.contains("agrupar") || query.contains("AGRUPAR") ||
				query.contains("ordenar") || query.contains("ORDENAR") ||
				query.contains(":=") ||
				/*Revisa si la relacion auxiliar es igual a una etiqueta del lenguaje*/
				query.equals("st") || query.equals("asg") || query.equals("con") || query.equals("bin") ||
				query.equals("select") || query.equals("proy") || query.equals("renom1") ||
				query.equals("renom2") || query.equals("renom3") || query.equals("join") || query.equals("groupby") ||
				query.equals("orderby") || query.equals("agregation")|| query.equals("orders")|| query.equals("order")||
				query.equals("fagregas") ||query.equals("fagrega") || query.equals("atts") || query.equals("conds1") || query.equals("conds2") ||
				query.equals("con1") || query.equals("con2") || query.equals("con3") || query.equals("cons") ||
				query.equals("num") || query.equals("att") || query.equals("rel") || query.equals("NUM") ||
				query.equals("CAD") || query.equals("WS")		
				){
			return false;
		}else{
			return true;
		}
	}

	// Filtra consulta y luego las ejecuta
	public void ejecutar(){
		
		AlgebraRelacionalLexer lexer = new AlgebraRelacionalLexer(new ANTLRStringStream(query));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		AlgebraRelacionalParser parser = new AlgebraRelacionalParser(tokens);
		try{
			
			// Ejecuta la consulta
			parser.st();
			
			// Revisa si tiene errores de sintaxis
			if(parser.getNumberOfSyntaxErrors() == 0){
				System.out.println("Query AR: " + query);
				
				ELContext elContext = FacesContext.getCurrentInstance().getELContext();
				UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
					    .getELResolver().getValue(elContext, null, "usuario");
				EsquemaBean esquema = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
					    .getELResolver().getValue(elContext, null, "bd");
				RelacionBean resultado = new RelacionBean();
				
				if(query.contains(":=")){ // Revisa si es una consulta de asignacion
					boolean band = false;
					
					String test[] = query.split(":=");
					test[0] = test[0].replaceAll(" ", "");
					if(!nombreValido(test[0])){
						resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						band = true;
					}else if(esquema.isRelacion(test[0])){
						resultado.setNombre("ERROR: no puedes reasignar una relaci�n de la lista de Relaciones");
						band = true;
					}
					
					//Asignar Reunion Natural
					if(!band && (query.contains("reunion_natural") || query.contains("REUNION_NATURAL"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");	
						
						String aux2[] = aux1[1].split("reunion_natural|REUNION_NATURAL");
						aux2[0] = aux2[0].replaceAll(" ", "");
						aux2[1] = aux2[1].replaceAll(" ", "");
						
						aux2[1] = aux2[1].replaceAll("\\(", "");
						
						String aux3[] = aux2[1].split("\\)");
						System.out.println(aux1[0]);
						System.out.println(aux2[0]);
						System.out.println(aux3[0]);
						System.out.println(aux3[1]);
						
						if(aux1[0].equals(aux2[0]) || aux1[0].equals(aux3[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.reunionNatural(aux1[0],aux2[0],aux3[1],aux3[0],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;
					}
					//Asignar Reunion Externa Full
					if(!band && (query.contains("reunion_ext_full") || query.contains("REUNION_EXT_FULL"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");	
						
						String aux2[] = aux1[1].split("reunion_ext_full|REUNION_EXT_FULL");
						aux2[0] = aux2[0].replaceAll(" ", "");
						aux2[1] = aux2[1].replaceAll(" ", "");
						
						aux2[1] = aux2[1].replaceAll("\\(", "");
						
						String aux3[] = aux2[1].split("\\)");
						System.out.println(aux1[0]);
						System.out.println(aux2[0]);
						System.out.println(aux3[0]);
						System.out.println(aux3[1]);
						
						if(aux1[0].equals(aux2[0]) || aux1[0].equals(aux3[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.reunionExtFull(aux1[0],aux2[0],aux3[1],aux3[0],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;
					}
					
					//Asignar Reunion Izquierda Externa
					if(!band && (query.contains("reunion_ext_izq") || query.contains("REUNION_EXT_IZQ"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");
						
						String aux2[] = aux1[1].split("reunion_ext_izq|REUNION_EXT_IZQ");
						aux2[0] = aux2[0].replaceAll(" ", "");
						aux2[1] = aux2[1].replaceAll(" ", "");
						
						aux2[1] = aux2[1].replaceAll("\\(", "");
						
						String aux3[] = aux2[1].split("\\)");
						System.out.println(aux1[0]);
						System.out.println(aux2[0]);
						System.out.println(aux3[0]);
						System.out.println(aux3[1]);
						
						if(aux1[0].equals(aux2[0]) || aux1[0].equals(aux3[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.reunionExtIzq(aux1[0],aux2[0],aux3[1],aux3[0],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;
					}
					
					//Asignar Reunion Derecha Externa
					if(!band && (query.contains("reunion_ext_der") || query.contains("REUNION_EXT_DER"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");
						
						String aux2[] = aux1[1].split("reunion_ext_der|REUNION_EXT_DER");
						aux2[0] = aux2[0].replaceAll(" ", "");
						aux2[1] = aux2[1].replaceAll(" ", "");
						
						aux2[1] = aux2[1].replaceAll("\\(", "");
						
						String aux3[] = aux2[1].split("\\)");
						System.out.println(aux1[0]);
						System.out.println(aux2[0]);
						System.out.println(aux3[0]);
						System.out.println(aux3[1]);
						
						if(aux1[0].equals(aux2[0]) || aux1[0].equals(aux3[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.reunionExtDer(aux1[0],aux2[0],aux3[1],aux3[0],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}

						band = true;
					}
					
					// Asignar Reunion
					if(!band && (query.contains("reunion") || query.contains("REUNION"))){
						//query = "(relacion) REUNION (Lista_de_condiciones_de_reunion) (relacion) "
						String aux[] = query.split(":=");
						
						aux[0] = aux[0].replaceAll(" ","");
						
						String aux1[] = aux[1].split("reunion|REUNION");
						aux1[0] = aux1[0].replaceAll(" ", "");
						aux1[0] = aux1[0].replaceAll("\\)","");
						aux1[0] = aux1[0].replaceAll("\\(","");
						
						
						String aux2[] = aux1[1].split("\\(");
						aux2[1] = aux2[1].replaceAll("\\)","");
						aux2[2] = aux2[2].replaceAll("\\)","");
						aux2[2] = aux2[2].replaceAll(" ","");
						
						System.out.println(aux[0]);
						System.out.println(aux1[0]);
						System.out.println(aux2[2]);
						System.out.println(aux2[1]);
						
												
						if(aux[0].equals(aux1[0]) || aux[0].equals(aux2[2])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.reunion(aux[0],aux1[0],aux2[2],aux2[1],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}

						band = true;
					}

					//Asignar Union
					if(!band && (query.contains("union") || query.contains("UNION"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");	
						
						String aux2[] = aux1[1].split("union|UNION");
						aux2[0] = aux2[0].replaceAll(" ", "");
						aux2[1] = aux2[1].replaceAll(" ", "");
						
						System.out.println(aux1[0]);
						System.out.println(aux2[0]);
						System.out.println(aux2[1]);
						
						if(aux1[0].equals(aux2[0]) || aux1[0].equals(aux2[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.union(aux1[0],aux2[0],aux2[1],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}

						band = true;
			
					}
					
					//Asignar Interseccion	
					if(!band && (query.contains("inter") || query.contains("INTER"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");	
						
						String aux2[] = aux1[1].split("inter|INTER");
						aux2[0] = aux2[0].replaceAll(" ", "");
						aux2[1] = aux2[1].replaceAll(" ", "");
						
						System.out.println(aux1[0]);
						System.out.println(aux2[0]);
						System.out.println(aux2[1]);
						
						if(aux1[0].equals(aux2[0]) || aux1[0].equals(aux2[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.inter(aux1[0],aux2[0],aux2[1],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;
					}
					
					//Asignar Diferencia
					if(!band && (query.contains("diferencia") || query.contains("DIFERENCIA"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");	
						
						String aux2[] = aux1[1].split("diferencia|DIFERENCIA");
						aux2[0] = aux2[0].replaceAll(" ", "");
						aux2[1] = aux2[1].replaceAll(" ", "");
						
						System.out.println(aux1[0]);
						System.out.println(aux2[0]);
						System.out.println(aux2[1]);
						
						if(aux1[0].equals(aux2[0]) || aux1[0].equals(aux2[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.diferencia(aux1[0],aux2[0],aux2[1],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;
					}	
					//Asignar Producto Cruz
					if(!band && (query.contains("cruz") || query.contains("CRUZ"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");	
						
						String aux2[] = aux1[1].split("cruz|CRUZ");
						aux2[0] = aux2[0].replaceAll(" ", "");
						aux2[1] = aux2[1].replaceAll(" ", "");
						
						System.out.println(aux1[0]);
						System.out.println(aux2[0]);
						System.out.println(aux2[1]);
						
						if(aux1[0].equals(aux2[0]) || aux1[0].equals(aux2[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.cruz(aux1[0],aux2[0],aux2[1],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;						
					}
					
					// Asignar Proyeccion
					if(!band && (query.contains("proyectar") || query.contains("PROYECTAR"))){
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");
						
						String aux[] = aux1[1].split("\\(");
						aux[1] = aux[1].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll("\\)","");
						
						aux[2] = aux[2].replaceAll(" ", "");
						aux[2] = aux[2].replaceAll("\\)","");
						
						System.out.println(aux[1]);
						System.out.println(aux[2]);
						
						if(aux1[0].equals(aux[2])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.proyectar(aux1[0],aux[1],aux[2],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;
					}
					
					// Asignar Seleccionar
					if(!band && (query.contains("seleccionar") || query.contains("SELECCIONAR"))){
						//query = "SELECCIONAR (Lista_de_condiciones) (relacion) "
						
						String aux1[] = query.split(":=");
						aux1[0] = aux1[0].replaceAll(" ","");
						
						String aux[] = aux1[1].split("\\(");
						aux[1] = aux[1].replaceAll("\\)","");

						aux[2] = aux[2].replaceAll(" ", "");
						aux[2] = aux[2].replaceAll("\\)","");
						
						System.out.println(aux[1]);
						System.out.println(aux[2]);
						
						if(aux1[0].equals(aux[2])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.seleccionar(aux1[0],aux[1],aux[2],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}

						band = true;
					}
					
					
					//Asignar Division
					if(!band && (query.contains("division") || query.contains("DIVISION"))){
						
						String aux20[] = query.split(":=");
						aux20[0] = aux20[0].replaceAll(" ","");	//relacion asignacion
						
						String auxdiv[] = query.split("division|DIVISION");
						
						System.out.println(auxdiv[0]);
						System.out.println(auxdiv[1]);
						
						String auxdiv1[] = auxdiv[0].split("\\("); //auxdiv1[0]= AtributoY,AtribbutoX) y auxdiv1[1]= relacion)    
						String auxdiv2[] = auxdiv[1].split("\\("); //auxdiv2[0]= AtribbutoX) y  auxdiv2[1]= relacion) 
						System.out.println(auxdiv1[1]);
						System.out.println(auxdiv1[2]);// en el log se imprimen espacios. auxdiv1[1] hay un espacio dentro. la relacion esta en auxdiv1[2]
						System.out.println(auxdiv2[1]);
						System.out.println(auxdiv2[2]);
						
						auxdiv1[1] = auxdiv1[1].replaceAll("\\)","");//auxdiv1[1]= AtributoY,AtribbutoX
						auxdiv1[2] = auxdiv1[2].replaceAll("\\)","");//auxdiv1[2]= relacion R
						auxdiv1[2] = auxdiv1[2].replaceAll(" ","");
						System.out.println("------------  RELACIONES ------------");
						System.out.println("atributos de R: "+auxdiv1[1]);
						System.out.println("relacion: "+auxdiv1[2]);
						
						String auxdiv11[] = auxdiv1[1].split(","); //auxdiv11[0]= AtributoY  y  auxdiv11[1]= AtributoX ambos de R
						System.out.println("atributo Y de R: "+auxdiv11[0]);
						System.out.println("atributo X de R: "+auxdiv11[1]);
						
						auxdiv2[1] = auxdiv2[1].replaceAll("\\)","");//auxdiv2[0]= AtributoX de S
						auxdiv2[2] = auxdiv2[2].replaceAll("\\)","");//auxdiv2[1]= relacion S
						System.out.println("atributo X de S: "+auxdiv2[1]);
						System.out.println("relacion: "+auxdiv2[2]);
						
						System.out.println("--------------------------------------");
						
						if(aux20[0].equals(auxdiv[0]) || aux20[0].equals(auxdiv[1])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux20[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.division(aux20[0],auxdiv11[0],auxdiv11[1],auxdiv1[2],auxdiv2[1],auxdiv2[2],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;
					}
						
					
					// Asignar AGRUPAR
					if(!band && (query.contains("agrupar") || query.contains("AGRUPAR"))){
						//query = RELACION := "(Lista_de_atributos) AGRUPAR (lista_de_funciones_de_agregacion) (relacion) "
						
							System.out.println("query asignar Agrupar: "+query);
							String aux11[] = query.split(":=");
							aux11[0] = aux11[0].replaceAll(" ","");//RELACION ASIGNACION
					
							String aux12[] = aux11[1].split("agrupar|AGRUPAR");
							
							aux12[0] = aux12[0].replaceAll(" ", "");
							aux12[0] = aux12[0].replaceAll("\\)","");
							aux12[0] = aux12[0].replaceAll("\\(","");//aux1[0]=Lista_de_atributos
							System.out.println("|"+aux12[0]+"|");
							System.out.println(band);
							
							String aux13[] = aux12[1].split("\\(");
							aux13[1] = aux13[1].replaceAll("\\)","");//aux2[1]=funcion_de_agregacion
							aux13[1] = aux13[1].replaceAll(" ", "");
							aux13[1] = aux13[0]+"("+aux13[1]+")";
							
							
							aux13[2] = aux13[2].replaceAll("\\)","");
							aux13[2] = aux13[2].replaceAll(" ","");//aux2[2]=relacion
							System.out.println("|"+aux13[2]+"|");
							
							System.out.println(aux12[0]);
							System.out.println(aux13[1]);
							System.out.println(aux13[2]);
						
						
							if(aux11[0].equals(aux13[2])){
								resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
							}else if(!nombreValido(aux11[0])){
								resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							}else{
								resultado = ConsultaDAO.agrupar(aux11[0],aux12[0],aux13[1],aux13[2],userBean.getRut());
								System.out.println(resultado.getNombre());
								tableName = resultado.getNombre().toLowerCase();
							}
	
							band = true;
					}
					
						//Asignar funcion de agregacion
						if(!band && (query.contains("count") || query.contains("COUNT") || query.contains("min") || query.contains("MIN") || query.contains("max") 
								|| query.contains("MAX") || query.contains("sum") || query.contains("SUM") ||  query.contains("avg") || query.contains("AVG"))){
							
							System.out.println("query asignar funcion de agregacion: "+query);
							
							String aux30[] = query.split(":=");
							aux30[0] = aux30[0].replaceAll(" ","");	//relacion asignacion
							System.out.println(aux30[0]);
							
							String aux10[] = aux30[1].split("\\(");
							
							String aux9[] = aux10[0].split(" ");

							
							aux10[1] = aux10[1].replaceAll("\\)","");//aux[1] = atributo

							
							System.out.println(aux9[1]);
							System.out.println(aux9[2]);
							System.out.println(aux10[1]);
						
							if(aux30[0].equals(aux10[1])){
								resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
							}else if(!nombreValido(aux30[0])){
								resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							}else{
								resultado = ConsultaDAO.funcionAgregacion(aux30[0],aux9[1],aux9[2],aux10[1],userBean.getRut());
								System.out.println(resultado.getNombre());
								tableName = resultado.getNombre().toLowerCase();
							}
							
							band = true;
						}
						
					
					// Asignar ORDENAR
					if(!band && (query.contains("ordenar") || query.contains("ORDENAR"))){
						//query = "ORDENAR atributo(asc/desc) (relacion) ";
						
						String aux15[] = query.split(":=");
						aux15[0] = aux15[0].replaceAll(" ","");//RELACION ASIGNACION
								
						String aux16[] = aux15[1].split("\\(");
						System.out.println(aux16[0]);
						
						String aux166[] = aux16[0].split("ORDENAR|ordenar");
						aux166[0] = aux166[0].replaceAll(" ","");
						System.out.println(aux166[1]);
						
						aux16[1] = aux16[1].replaceAll(" ", "");
						aux16[1] = aux16[1].replaceAll("\\)","");// atributo
						System.out.println(aux16[1]);
						aux16[2] = aux16[2].replaceAll(" ", "");
						aux16[2] = aux16[2].replaceAll("\\)",""); // order asc o desc
						System.out.println(aux16[2]);

						
						if(aux15[0].equals(aux16[2])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
						}else if(!nombreValido(aux15[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						}else{
							resultado = ConsultaDAO.ordenar(aux15[0],aux166[1],aux16[1],aux16[2],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}

						band = true;
					}
										
					
					// Asignar Renombrar
					if(!band && (query.contains("renombrar") || query.contains("RENOMBRAR"))){
					
						String aux3[] = query.split(":=");
						aux3[0] = aux3[0].replaceAll(" ","");
						
						String aux[] = aux3[1].split("renombrar|RENOMBRAR");
						String aux1[] = aux[1].split("\\(");
						aux1[0] = aux1[0].replaceAll(" ","");	
						aux1[1] = aux1[1].replaceAll("\\)","");
						aux1[1] = aux1[1].replaceAll(" ","");
						
						if(aux1[0].equals("")){
							// Solo renombra los atributos
							
							aux1[2] = aux1[2].replaceAll("\\)","");
							aux1[2] = aux1[2].replaceAll(" ","");
							
							EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
								    .getELResolver().getValue(elContext, null, "bd");
							
							RelacionBean relacion = esquemaBean.getRelacion(aux1[2]);
							String[] atributos = aux1[1].split(",");
							
							if(relacion != null && atributos.length == relacion.getAtributos().size()){
								
								String att = "";
								for(int i = 0 ; i < atributos.length ; i++){
									atributos[i] = relacion.getAtributos().get(i).getNombre() + " as " + atributos[i];		
									att = att + atributos[i] + ",";
								}
								att = att.substring(0,att.length()-1);
								
								System.out.println(aux3[0]);
								System.out.println(relacion.getNombre());
								System.out.println(att);
								
								if(aux3[0].equals(relacion.getNombre())){
									resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
								}else if(!nombreValido(aux3[0])){
									resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
								}else{
									//resultado = ConsultaDAO.renombrar(aux3[0],relacion.getNombre(),att,userBean.getRut());
									tableName = resultado.getNombre().toLowerCase();
								}
								
							}else{
								resultado.setNombre("ERROR: La relacion no existe o la cantidad de atributos a renombrar no concuerda");
							}
								
						}else{
							// Renombra relacion	
							if(aux1.length == 3){
								
								
								aux1[2] = aux1[2].replaceAll("\\)","");
								aux1[2] = aux1[2].replaceAll(" ","");
								
								EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
									    .getELResolver().getValue(elContext, null, "bd");
								
								RelacionBean relacion = esquemaBean.getRelacion(aux1[2]);
								String[] atributos = aux1[1].split(",");
								
								if(relacion != null && atributos.length == relacion.getAtributos().size()){
									
									System.out.println(aux3[0]);
									System.out.println(aux1[0]);
									//System.out.println(att);
									System.out.println(relacion.getNombre());
									
									if(aux3[0].equals(relacion.getNombre())){
										resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
									}else if(!nombreValido(aux3[0])){
										resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
									}else{
										resultado = ConsultaDAO.asigRenombrarRelAtt(aux3[0],relacion.getNombre(),aux1[0],atributos,userBean.getRut());
										tableName = aux3[0].toLowerCase();
									}
									
								}else{
									resultado.setNombre("ERROR: La relacion no existe o la cantidad de atributos a renombrar no concuerda");
								}
															
							}else{
								
								EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
									    .getELResolver().getValue(elContext, null, "bd");
								
								RelacionBean relacion = esquemaBean.getRelacion(aux1[1]);
								
								if(relacion!= null){
									
									System.out.println(aux3[0]);
									System.out.println(aux1[0]);
									System.out.println(relacion.getNombre());
									
									if(aux3[0].equals(relacion.getNombre())){
										resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");	
									}else if(!nombreValido(aux3[0])){
										resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
									}else{
										resultado = ConsultaDAO.verRelacion(aux3[0],relacion.getNombre(),userBean.getRut());
										tableName = aux3[0].toLowerCase();
									}
									
								}else{
									resultado.setNombre("ERROR: La relacion " + aux1[1] + " no existe");
								}
							}						
						}
						
						band = true;
						
					}
					
					
					
					
					
					
					columnNames = new ArrayList<String>();
					data = new ArrayList<Object[]>();
					// Revisa el resultado de la consulta
					if(resultado.getTuplasBean()!= null){
						
						EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
							    .getELResolver().getValue(elContext, null, "bd");
						
						List<RelacionBean> relacionesAuxiliares = esquemaBean.getRelacionesAuxiliares();
						
						if(relacionesAuxiliares == null){
							relacionesAuxiliares = new ArrayList<RelacionBean>();
						}
						
						// Revisa si la relacion nueva ya existe
						if(esquemaBean.isRelacionAux(resultado.getNombre())){
							esquemaBean.reemplazarRelacion(resultado);
						
						}else{
							// Si no existe, simplemente la agrega
							relacionesAuxiliares.add(resultado);
						}
						esquemaBean.setRelacionesAuxiliares(relacionesAuxiliares);
						
						HttpSession session = Util.getSession();
				    	
				    	session.setAttribute("nombreBd",esquemaBean.getNombre());
				    	session.setAttribute("bd",esquemaBean);
						
						for(AtributoBean a : resultado.getAtributos())
							columnNames.add(a.getNombre());
						
						for(TuplaBean t : resultado.getTuplasBean())
							data.add(t.getAtributos());

						queryList = queryList + query + "\n";
						
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Consulta realizada satisfactoriamente","");
			       		FacesContext.getCurrentInstance().addMessage(null, msg);
					}else{
						tableName = null;
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar la consulta: " + resultado.getNombre(),"");
			       		FacesContext.getCurrentInstance().addMessage(null, msg);
						System.out.println("Error: " + resultado.getNombre());
					}
					
				}else{					  // Consulta normal
					
					boolean band1 = false;
					//Reunion Natural
					if(!band1 && (query.contains("reunion_natural") || query.contains("REUNION_NATURAL"))){
						String aux[] = query.split("reunion_natural|REUNION_NATURAL");
						
						aux[0] = aux[0].replaceAll(" ", "");
						System.out.println("aux(0): "+aux[0]);//listo relacion1
						
						aux[1] = aux[1].replaceAll(" ", "");
						System.out.println("aux(1): "+aux[1]);
						
						aux[1] = aux[1].replaceAll("\\(", "");
						System.out.println("aux(1): "+aux[1]);
						
						String aux2[] = aux[1].split("\\)");
						System.out.println("aux2(0): "+aux2[0]);//listo condiciones
						System.out.println("aux2(1): "+aux2[1]);//listo rel2
						
						
						resultado = ConsultaDAO.reunionNatural(aux[0],aux2[1],aux2[0],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					//Reunion Completa Externa
					if(!band1 && (query.contains("reunion_ext_full") || query.contains("REUNION_EXT_FULL"))){
						String aux[] = query.split("reunion_ext_full|REUNION_EXT_FULL");
						aux[0] = aux[0].replaceAll(" ", "");
						System.out.println("aux(0): "+aux[0]);//listo relacion1
						
						aux[1] = aux[1].replaceAll(" ", "");
						System.out.println("aux(1): "+aux[1]);
						
						aux[1] = aux[1].replaceAll("\\(", "");
						System.out.println("aux(1): "+aux[1]);
						
						String aux2[] = aux[1].split("\\)");
						System.out.println("aux2(0): "+aux2[0]);//listo condiciones
						System.out.println("aux2(1): "+aux2[1]);//listo rel2
						
						
						resultado = ConsultaDAO.reunionExtFull(aux[0],aux2[1],aux2[0],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					//Reunion Izquierda Externa
					if(!band1 && (query.contains("reunion_ext_izq") || query.contains("REUNION_EXT_IZQ"))){
						String aux[] = query.split("reunion_ext_izq|REUNION_EXT_IZQ");
						aux[0] = aux[0].replaceAll(" ", "");
						System.out.println("aux(0): "+aux[0]);//listo relacion1
						
						aux[1] = aux[1].replaceAll(" ", "");
						System.out.println("aux(1): "+aux[1]);
						
						aux[1] = aux[1].replaceAll("\\(", "");
						System.out.println("aux(1): "+aux[1]);
						
						String aux2[] = aux[1].split("\\)");
						System.out.println("aux2(0): "+aux2[0]);//listo condiciones
						System.out.println("aux2(1): "+aux2[1]);//listo rel2
						
						
						resultado = ConsultaDAO.reunionExtIzq(aux[0],aux2[1],aux2[0],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					//Reunion Derecha Externa
					if(!band1 && (query.contains("reunion_ext_der") || query.contains("REUNION_EXT_DER"))){
						String aux[] = query.split("reunion_ext_der|REUNION_EXT_DER");
						aux[0] = aux[0].replaceAll(" ", "");
						System.out.println("aux(0): "+aux[0]);//listo relacion1
						
						aux[1] = aux[1].replaceAll(" ", "");
						System.out.println("aux(1): "+aux[1]);
						
						aux[1] = aux[1].replaceAll("\\(", "");
						System.out.println("aux(1): "+aux[1]);
						
						String aux2[] = aux[1].split("\\)");
						System.out.println("aux2(0): "+aux2[0]);//listo condiciones
						System.out.println("aux2(1): "+aux2[1]);//listo rel2
						
						
						resultado = ConsultaDAO.reunionExtDer(aux[0],aux2[1],aux2[0],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					// Proyeccion
					if(!band1 && (query.contains("proyectar") || query.contains("PROYECTAR"))){
						String aux[] = query.split("\\(");
						aux[1] = aux[1].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll("\\)","");
						
						aux[2] = aux[2].replaceAll(" ", "");
						aux[2] = aux[2].replaceAll("\\)","");
						
						System.out.println(aux[1]);
						System.out.println(aux[2]);
						
						resultado = ConsultaDAO.proyectar(aux[1],aux[2],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					// Seleccionar
					if(!band1 && (query.contains("seleccionar") || query.contains("SELECCIONAR"))){
						//query = "SELECCIONAR (Lista_de_condiciones) (relacion) "
						String aux[] = query.split("\\(");
						aux[1] = aux[1].replaceAll("\\)","");
						
						aux[2] = aux[2].replaceAll(" ", "");
						aux[2] = aux[2].replaceAll("\\)","");
						
						System.out.println(aux[1]);
						System.out.println(aux[2]);

						resultado = ConsultaDAO.seleccionar(aux[1],aux[2],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					// Reunion
					if(!band1 && (query.contains("reunion") || query.contains("REUNION"))){
						//query = "(relacion) REUNION (Lista_de_condiciones_de_reunion) (relacion)"
						String aux1[] = query.split("reunion|REUNION");
						aux1[0] = aux1[0].replaceAll(" ", "");
						aux1[0] = aux1[0].replaceAll("\\)","");
						aux1[0] = aux1[0].replaceAll("\\(","");
						
						System.out.println(band1);
						
						String aux2[] = aux1[1].split("\\(");
						aux2[1] = aux2[1].replaceAll("\\)","");
						aux2[2] = aux2[2].replaceAll("\\)","");
						aux2[2] = aux2[2].replaceAll(" ","");
						
						System.out.println(aux1[0]);
						System.out.println(aux2[2]);
						System.out.println(aux2[1]);
						
						resultado = ConsultaDAO.reunion(aux1[0],aux2[2],aux2[1],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					//Union
					if(!band1 && (query.contains("union") || query.contains("UNION"))){ 
						String aux[] = query.split("union|UNION");
						
						aux[0] = aux[0].replaceAll(" ", "");//Relacion1
						aux[1] = aux[1].replaceAll(" ", "");//Relacion2
						
						System.out.println(aux[0]);
						System.out.println(aux[1]);
						
						resultado = ConsultaDAO.union(aux[0],aux[1],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					//Interseccion	
					if(!band1 && (query.contains("inter") || query.contains("INTER"))){
						String aux[] = query.split("inter|INTER");
						
						aux[0] = aux[0].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll(" ", "");
						
						System.out.println(aux[0]);
						System.out.println(aux[1]);
						
						resultado = ConsultaDAO.inter(aux[0],aux[1],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					//Diferencia
					if(!band1 && (query.contains("diferencia") || query.contains("DIFERENCIA"))){
						String aux[] = query.split("diferencia|DIFERENCIA");
						
						aux[0] = aux[0].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll(" ", "");
						
						System.out.println(aux[0]);
						System.out.println(aux[1]);
						
						resultado = ConsultaDAO.diferencia(aux[0],aux[1],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}	
					//Producto Cruz
					if(!band1 && (query.contains("cruz") || query.contains("CRUZ"))){
						String aux[] = query.split("cruz|CRUZ");
						//query = "relacion CRUZ relacion ";
						aux[0] = aux[0].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll(" ", "");
						
						System.out.println(aux[0]);
						System.out.println(aux[1]);
						
						resultado = ConsultaDAO.cruz(aux[0],aux[1],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;						
					}
					
					
					
					//Division
					if(!band1 && (query.contains("division") || query.contains("DIVISION"))){
						
						//query = "(atributoY,atributoX) (relacion R) DIVISION (atributoX) (relacion S)";
						String auxdiv[] = query.split("division|DIVISION");
						
						System.out.println(auxdiv[0]);
						System.out.println(auxdiv[1]);
						
						String auxdiv1[] = auxdiv[0].split("\\("); //auxdiv1[0]= AtributoY,AtribbutoX) y auxdiv1[1]= relacion)    
						String auxdiv2[] = auxdiv[1].split("\\("); //auxdiv2[0]= AtribbutoX) y  auxdiv2[1]= relacion) 
						System.out.println(auxdiv1[1]);
						System.out.println(auxdiv1[2]);// en el log se imprimen espacios. auxdiv1[1] hay un espacio dentro. la relacion esta en auxdiv1[2]
						System.out.println(auxdiv2[1]);
						System.out.println(auxdiv2[2]);
						
						auxdiv1[1] = auxdiv1[1].replaceAll("\\)","");//auxdiv1[1]= AtributoY,AtribbutoX
						auxdiv1[2] = auxdiv1[2].replaceAll("\\)","");//auxdiv1[2]= relacion R
						auxdiv1[2] = auxdiv1[2].replaceAll(" ","");
						System.out.println("------------  RELACIONES ------------");
						System.out.println("atributos de R: "+auxdiv1[1]);
						System.out.println("relacion: "+auxdiv1[2]);
						
						String auxdiv11[] = auxdiv1[1].split(","); //auxdiv11[0]= AtributoY  y  auxdiv11[1]= AtributoX ambos de R
						System.out.println("atributo Y de R: "+auxdiv11[0]);
						System.out.println("atributo X de R: "+auxdiv11[1]);
						
						auxdiv2[1] = auxdiv2[1].replaceAll("\\)","");//auxdiv2[0]= AtributoX de S
						auxdiv2[2] = auxdiv2[2].replaceAll("\\)","");//auxdiv2[1]= relacion S
						System.out.println("atributo X de S: "+auxdiv2[1]);
						System.out.println("relacion: "+auxdiv2[2]);
						
						System.out.println("--------------------------------------");
						
						resultado = ConsultaDAO.division(auxdiv11[0],auxdiv11[1],auxdiv1[2],auxdiv2[1],auxdiv2[2],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;						
					}
					
					//Agrupar
					if(!band1 && (query.contains("agrupar") || query.contains("AGRUPAR"))){
						System.out.println("Query agrupar: " + query);
						//query = "(Lista_de_atributos) AGRUPAR (lista_de_funciones_de_agregacion) (relacion) "
						String aux12[] = query.split("agrupar|AGRUPAR");
						
						aux12[0] = aux12[0].replaceAll(" ", "");
						aux12[0] = aux12[0].replaceAll("\\)","");
						aux12[0] = aux12[0].replaceAll("\\(","");//aux1[0]=Lista_de_atributos
						System.out.println("|"+aux12[0]+"|");
						System.out.println(band1);
						
						String aux13[] = aux12[1].split("\\(");
						aux13[1] = aux13[1].replaceAll("\\)","");//aux2[1]=funcion_de_agregacion
						aux13[1] = aux13[1].replaceAll(" ", "");
						aux13[1] = aux13[0]+"("+aux13[1]+")";
						
						
						aux13[2] = aux13[2].replaceAll("\\)","");
						aux13[2] = aux13[2].replaceAll(" ","");//aux2[2]=relacion
						System.out.println("|"+aux13[2]+"|");
						
						System.out.println(aux12[0]);
						System.out.println(aux13[1]);
						System.out.println(aux13[2]);
						
						resultado = ConsultaDAO.agrupar(aux12[0],aux13[1],aux13[2],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;						
					}
					
					// Funcion de Agregacion
					if(!band1 && (query.contains("count") || query.contains("COUNT") || query.contains("min") || query.contains("MIN") || query.contains("max") 
							|| query.contains("MAX") || query.contains("sum") || query.contains("SUM") ||  query.contains("avg") || query.contains("AVG"))){
						//"funcion_de_agregacion (atributo) (relacion) "
						String aux10[] = query.split("\\(");
						
						String aux9[] = aux10[0].split(" ");
						//aux9[0] = aux9[0].replaceAll("\\)","");//aux[0] = funcion agregada
						System.out.println("|"+aux9[0]+"|");
						System.out.println("|"+aux9[1]+"|");
						
						aux10[1] = aux10[1].replaceAll("\\)","");//aux[1] = atributo
						System.out.println("|"+aux10[1]+"|");

						
						System.out.println(aux9[0]);
						System.out.println(aux9[1]);
						System.out.println(aux10[1]);

						resultado = ConsultaDAO.funcionAgregacion(aux9[0],aux9[1],aux10[1],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;
					}
					
					
					//order
					if(!band1 && (query.contains("ordenar") || query.contains("ORDENAR"))){
						//query = "ORDENAR (atributo) (order) (relacion) "
						System.out.println("Query order: " + query);
						
					
						String aux14[] = query.split("\\(");
						System.out.println("Query split: " + query);
						System.out.println("|"+aux14[0]+"|");
						System.out.println("|"+aux14[1]+"|");
						System.out.println("|"+aux14[2]+"|");
						
						aux14[0] = aux14[0].replaceAll("ORDENAR ", "");
						aux14[0] = aux14[0].replaceAll("\\)","");// atributo
						System.out.println("Query aux 1: " + query);
						System.out.println("|"+aux14[0]+"|");
						System.out.println("|"+aux14[1]+"|");
						System.out.println("|"+aux14[2]+"|");
						
						
						aux14[1] = aux14[1].replaceAll(" ", "");
						aux14[1] = aux14[1].replaceAll("\\)",""); // order asc o desc
						System.out.println("|"+aux14[0]+"|");
						System.out.println("|"+aux14[1]+"|");
						System.out.println("|"+aux14[2]+"|");
						
						
						aux14[2] = aux14[2].replaceAll(" ", "");
						aux14[2] = aux14[2].replaceAll("\\)","");// relacion
						
						System.out.println("Query aux3: " + query);
						System.out.println(aux14[0]);
						System.out.println(aux14[1]);
						System.out.println(aux14[2]);
						
						System.out.println("----------------");
						System.out.println(aux14[0]);
						System.out.println(aux14[1]);
						System.out.println(aux14[2]);
						
						resultado = ConsultaDAO.ordenar(aux14[0],aux14[1],aux14[2],userBean.getRut());
						tableName = resultado.getNombre();
						band1 = true;						
					}
					
					
		
					
					//Renombrar
					if(!band1 && (query.contains("renombrar") || query.contains("RENOMBRAR"))){
						
						String aux[] = query.split("renombrar|RENOMBRAR");
						String aux1[] = aux[1].split("\\(");
						aux1[0] = aux1[0].replaceAll(" ","");	
						aux1[1] = aux1[1].replaceAll("\\)","");
						aux1[1] = aux1[1].replaceAll(" ","");
						
						if(aux1[0].equals("")){
							// Solo renombra los atributos
							
							aux1[2] = aux1[2].replaceAll("\\)","");
							aux1[2] = aux1[2].replaceAll(" ","");
							
							EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
								    .getELResolver().getValue(elContext, null, "bd");
							
							RelacionBean relacion = esquemaBean.getRelacion(aux1[2]);
							String[] atributos = aux1[1].split(",");
							
							if(relacion != null && atributos.length == relacion.getAtributos().size()){
								
								String att = "";
								for(int i = 0 ; i < atributos.length ; i++){
									atributos[i] = relacion.getAtributos().get(i).getNombre() + " as " + atributos[i];		
									att = att + atributos[i] + ",";
								}
								att = att.substring(0,att.length()-1);
								
								System.out.println(relacion.getNombre());
								System.out.println(att);

								resultado = ConsultaDAO.renombrarRelAtt(relacion.getNombre(),aux1[0],atributos,userBean.getRut());
								tableName = resultado.getNombre();
								
							}else{
								resultado.setNombre("ERROR: La relacion no existe o la cantidad de atributos a renombrar no concuerda");
							}
								
						}else{
							// Renombra relacion	
							if(aux1.length == 3){
								
								aux1[2] = aux1[2].replaceAll("\\)","");
								aux1[2] = aux1[2].replaceAll(" ","");
								
								EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
									    .getELResolver().getValue(elContext, null, "bd");
								
								RelacionBean relacion = esquemaBean.getRelacion(aux1[2]);
								String[] atributos = aux1[1].split(",");
								
								if(relacion != null && atributos.length == relacion.getAtributos().size()){
																		
									System.out.println(aux1[0]);
									System.out.println(relacion.getNombre());
									
									resultado = ConsultaDAO.renombrarRelAtt(relacion.getNombre(),aux1[0],atributos,userBean.getRut());
									tableName = aux1[0].toLowerCase();
									
								}else{
									resultado.setNombre("ERROR: La relacion no existe o la cantidad de atributos a renombrar no concuerda");
								}
															
							}else{
								
								EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
									    .getELResolver().getValue(elContext, null, "bd");
								
								RelacionBean relacion = esquemaBean.getRelacion(aux1[1]);
								
								if(relacion!= null){
									
									System.out.println(aux1[0]);
									System.out.println(relacion.getNombre());
									
									resultado = ConsultaDAO.renombrarRel(relacion.getNombre(),aux1[0],userBean.getRut());
									tableName = aux1[0].toLowerCase();
									
								}								
							}						
						}
						band1 = true;
					}
			
					columnNames = new ArrayList<String>();
					data = new ArrayList<Object[]>();

					// Revisa el resultado de la consulta
					if(resultado.getTuplasBean()!= null){
						
						for(AtributoBean a : resultado.getAtributos())
							columnNames.add(a.getNombre());
						
						for(TuplaBean t : resultado.getTuplasBean())
							data.add(t.getAtributos());
						
						queryList = queryList + query + "\n";
					
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Consulta realizada satisfactoriamente","");
			       		FacesContext.getCurrentInstance().addMessage(null, msg);
					}else{
						tableName = "";
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar la consulta: " + resultado.getNombre(),"");
			       		FacesContext.getCurrentInstance().addMessage(null, msg);
						System.out.println("Error: " + resultado.getNombre());
					}
					
					
				}
			
			// Error de sintaxis de consultas
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de sintaxis en consulta de Algebra Relacional","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		catch (RecognitionException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error Interno al ejecutar la consulta","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	

	}
	
	public void ayudaSeleccionar(){
		query = "SELECCIONAR (Lista_de_condiciones) (relacion) ";
	}
	
	public void ayudaProyectar(){
		query = "PROYECTAR (Lista_de_atributos) (relacion) ";
	}

	public void ayudaRenombrar(){
		query = "RENOMBRAR nuevo_nombre(lista_de_atributos) (relacion) ";
	}
	
	public void ayudaUnion(){
		query = "relacion UNION relacion ";
	}
	
	public void ayudaInterseccion(){
		query = "relacion INTER relacion ";
	}
	
	public void ayudaDiferencia(){
		query = "relacion DIFERENCIA relacion ";
	}
	
	public void ayudaCruz(){
		query = "relacion CRUZ relacion ";
	}
	
	public void ayudaReunion(){
		query = "(relacion) REUNION (Lista_de_condiciones_de_reunion) (relacion) ";
	}
	
	public void ayudaReunionNatural(){
		query = "relacion REUNION_NATURAL (condicion_de_igualdad) relacion ";
	}
	
	public void ayudaReunionExtIzq(){
		query = "relacion REUNION_EXT_IZQ (condicion_de_igualdad) relacion ";
	}
	
	public void ayudaReunionExtDer(){
		query = "relacion REUNION_EXT_DER (condicion_de_igualdad) relacion ";
	}
	
	public void ayudaReunionExtComp(){
		query = "relacion REUNION_EXT_FULL (condicion_de_igualdad) relacion ";
	}
	
	public void ayudaAsignar(){
		query = "relacion := consulta ";
	}
	
	public void ayudaDivision(){
		query = "relacion DIVISION relacion ";
	}
	
	public void ayudaAgrupar(){
		query = "(Lista_de_atributos) AGRUPAR agregacion(atributo) (relacion) ";
	}

	public void ayudaOrdenar(){
		query = "ORDENAR atributo(asc/desc) (relacion) ";
	}
	
	public void ayudaFuncionAgregacion(){
		query = "funcion_agregacion atributo (relacion) ";
	}
}


