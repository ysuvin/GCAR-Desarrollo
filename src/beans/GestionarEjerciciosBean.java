package beans;
	 
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import dao.ConsultaDAOSQL;
import dao.EjercicioDAO;
import dao.EsquemaDAO;
import dao.RelacionDAO;
import dao.ResultadoDAO;

import parser.AlgebraRelacionalLexer;
import parser.AlgebraRelacionalParser;
import util.AtributoBean;
import util.Consulta;
import util.Ejercicio;
import util.EsquemaBean;
import util.RelacionBean;
import util.Respuesta;
import util.Resultado;
import util.TuplaBean;
import util.UserBean;
import util.EstadisticasEjecutorAR;
 
@ManagedBean(name="dtGestionarEjerciciosBean")
@ViewScoped
public class GestionarEjerciciosBean implements Serializable {
     
	//Ejemplo
	private String tableName;
	private List<Object[]> data;
	private List<String> columnNames;
	private String query = "";
	private String queryList = "";
	
	private List<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
	private Ejercicio selectedEjercicio;
	private int cantEjercicios;
	
	private List<Respuesta> respuestas = new ArrayList<Respuesta>();	
	private Respuesta respuesta;
	private List<Consulta> consultas;
	private Consulta consulta;
	
	private String errorMessage;
	
	private boolean primerCarga = true;
	
    @PreDestroy
    public void destroy(){
//    	Util.reloadBd();
    }
	
	@PostConstruct
    public void init() {
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
    	
    	if(esquemaBean != null){
			ejercicios = EjercicioDAO.cargarEjercicios(esquemaBean);
			if(ejercicios == null){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar los ejercicios","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Ejercicios Cargados","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			cantEjercicios = ejercicios.size();
    	}
  		
    }
	
	public Ejercicio getSelectedEjercicio() {
		return selectedEjercicio;
	}

	public void setSelectedEjercicio(Ejercicio selectedEjercicio) {
		this.selectedEjercicio = selectedEjercicio;
	}

	public int getCantEjercicios() {
		return cantEjercicios;
	}

	public void setCantEjercicios(int cantEjercicios) {
		this.cantEjercicios = cantEjercicios;
//		System.out.println(cantEjercicios);
//		if(cantEjercicios == 0){
//			System.out.println("Borro todo");
//			ejercicios.clear();
//			this.cantEjercicios = cantEjercicios;
//		}else if(cantEjercicios < 0){
//			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cantidad de atributos no puede ser negativa","");
//       		FacesContext.getCurrentInstance().addMessage(null, msg);
//		}else if(cantEjercicios <= this.cantEjercicios){
//			this.cantEjercicios = cantEjercicios;
//		}else{
//			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//			EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
//				    .getELResolver().getValue(elContext, null, "bd");
//			
//			boolean band = false;
//	    	Pattern pat = Pattern.compile("\\s*");
//	
//	    	for(Ejercicio e : ejercicios){
//	    		if(e.getPregunta () == null || e.getRespuesta() == null || e.getRespuesta() == null ){
//	    			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Imposible agregar ejercicios con preguntas o respuestas vacias. Revisar ejercicio " + (e.getId()+1),"");
//		       		FacesContext.getCurrentInstance().addMessage(null, msg);
//		       		band = true;
//		       		break;
//	    		}else{
//	        		Matcher matPregunta = pat.matcher(e.getPregunta());
//	        		Matcher matRespuesta = pat.matcher(e.getRespuesta());
//	        		Matcher matConsultas = pat.matcher(e.getConsultas());
//		    		if(matPregunta.matches() || matRespuesta.matches() || matConsultas.matches()){
//						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Imposible agregar ejercicios con preguntas o respuestas vacias. Revisar ejercicio " + (e.getId()+1),"");
//			       		FacesContext.getCurrentInstance().addMessage(null, msg);
//			       		band = true;
//			       		break;
//					}else if(esquemaBean.getRelacion(e.getRespuesta()) == null && esquemaBean.getRelacionEjercicio(e.getRespuesta()) == null && esquemaBean.getRelacionAuxiliar(e.getRespuesta()) == null ){
//						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La relacion " + e.getRespuesta() + " no existe en la lista de Relaciones, Relaciones Auxiliares o de Respuestas de Ejercicios. Revisar ejercicio " + (e.getId()+1),"");
//			       		FacesContext.getCurrentInstance().addMessage(null, msg);
//			       		band = true;
//			       		break;
//					}else{
//						String respAlumno = e.getConsultas();
//			    		respAlumno = respAlumno.replaceAll("[\n\r]", ";");
//				    	respAlumno = respAlumno.replaceAll(";+", ";");
//				    	e.setConsultas(respAlumno);
//					}
//	    		}
//	    	}
//	    	if(!band){
//				this.cantEjercicios = cantEjercicios;
//	    	}
//		}
	}

	public List<Ejercicio> getEjercicios() {
		if(ejercicios.size() == 0){
			for(int i = 0 ; i < cantEjercicios ; i++){
				System.out.println("Hola");
				Ejercicio ejercicio = new Ejercicio();
				ejercicios.add(ejercicio);
			}
		}else if(ejercicios.size() < cantEjercicios){
			for(int i = ejercicios.size() ; i < cantEjercicios ; i++){
				Ejercicio ejercicio = new Ejercicio();
				ejercicio.setId(i);
				ejercicios.add(ejercicio);
			}
		}else if(ejercicios.size() > cantEjercicios){
			for(int i = ejercicios.size(); i > cantEjercicios ; i--){
				ejercicios.remove(i-1);
			}
			
		}
		for(int i = 0 ; i < ejercicios.size() ; i++){
			Ejercicio ejercicio = ejercicios.get(i);
			ejercicio.setId(i);
			ejercicios.set(i,ejercicio);
		}
		return ejercicios;
	}

	public void setEjercicios(List<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
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
	
	
	/* ##############################################
	 * #             Parte de Ejercicio				#
	 * ##############################################
	 */
	
	private void imprimirEjercicios(){
		for(Ejercicio e : ejercicios){
			System.out.println(e.getId());
			System.out.println(e.getPregunta());
			System.out.println(e.getRespuesta());
			System.out.println();
		}
	}
	
	public void agregarEjercicio(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		
		boolean band = false;
    	Pattern pat = Pattern.compile("\\s*");

    	for(Ejercicio e : ejercicios){
    		if(e.getPregunta () == null || e.getRespuesta() == null || e.getRespuesta() == null || e.getQueryList() == null ){
    			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Imposible guardar ejercicios con preguntas o respuestas vacias. Revisar ejercicio " + (e.getId()+1),"");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
	       		band = true;
	       		break;
    		}else{
        		Matcher matPregunta = pat.matcher(e.getPregunta());
        		Matcher matRespuesta = pat.matcher(e.getRespuesta());
        		Matcher matConsultas = pat.matcher(e.getConsultas());
        		Matcher matQueryList = pat.matcher(e.getQueryList());
	    		if(matPregunta.matches() || matRespuesta.matches() || matConsultas.matches() || matQueryList.matches()){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Imposible guardar ejercicios con preguntas o respuestas vac�as. Revisar ejercicio " + (e.getId()+1),"");
		       		FacesContext.getCurrentInstance().addMessage(null, msg);
		       		band = true;
		       		break;
				}else if(esquemaBean.getRelacion(e.getRespuesta()) == null && esquemaBean.getRelacionEjercicio(e.getRespuesta()) == null && esquemaBean.getRelacionAuxiliar(e.getRespuesta()) == null ){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La relacion " + e.getRespuesta() + " no existe en la lista de Relaciones, Relaciones Auxiliares o de Respuestas de Ejercicios. Revisar ejercicio " + (e.getId()+1),"");
		       		FacesContext.getCurrentInstance().addMessage(null, msg);
		       		band = true;
		       		break;
				}else{
					String respAlumno = e.getConsultas();
		    		respAlumno = respAlumno.replaceAll("[\n\r]", ";");
			    	respAlumno = respAlumno.replaceAll(";+", ";");
			    	e.setConsultas(respAlumno);
				}
    		}
    	}
    	if(!band){
    		this.cantEjercicios = this.cantEjercicios + 1;
    	}
	}
	
	public void quitarEjercicio(){
		if(cantEjercicios - 1 < 0){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cantidad de atributos no puede ser negativa","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			if(cantEjercicios - 1 == 0){
				ejercicios.clear();
			}
			this.cantEjercicios = this.cantEjercicios - 1;
		}
	}
	
	public void eliminar(){
		ejercicios.remove(selectedEjercicio.getId());
		for(int i = 0 ; i< ejercicios.size() ; i++){
			Ejercicio e = ejercicios.get(i);
			e.setId(i);
			ejercicios.set(i,e);
		}
		cantEjercicios = ejercicios.size();
		selectedEjercicio = null;
		imprimirEjercicios();
	}
	
	public void aceptar(){
		System.out.println("Acepto");
		copiarRespuesta();
		ejercicios.set(selectedEjercicio.getId(),selectedEjercicio);	
		selectedEjercicio = null;
		imprimirEjercicios();
	}
	
	public String crear(){
		System.out.println("Creando Ejercicios");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
    	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
    	
		if(ejercicios.size() == 0){
			if(EjercicioDAO.borrarEjercicios(esquemaBean)){
				
	    		// Recarga la Base de Datos
    			HttpSession session = Util.getSession();
    			session.setAttribute("bd",null);
    			
    			esquemaBean = EsquemaDAO.cargarBDBean(esquemaBean.getNombre());
    	    	boolean band = EsquemaDAO.crearEsquemaLoad(userBean.getRut());
    	    	
    	    	if(band && esquemaBean != null){

    	    		esquemaBean.setRelacionesAuxiliares(null);
    	    		esquemaBean = RelacionDAO.cargarRelaciones(esquemaBean, userBean.getRut());
    	    		
    	    		if(esquemaBean != null){
    	    			
    	    			for(RelacionBean r : esquemaBean.getRelacionesBean()){
    	    				System.out.println("Nombre Relacion: " + r.getNombre());
    	    				for(TuplaBean t : r.getTuplasBean()){
    	    					for(Object o : t.getAtributos()){
    	    						System.out.print(o + "\t\t\t");
    	    					}
    	    					System.out.println();
    	    				}
    	    			}
    	    			for(RelacionBean r : esquemaBean.getRelacionesEjercicios()){
    	    				System.out.println("Nombre Relacion: " + r.getNombre());
    	    				for(TuplaBean t : r.getTuplasBean()){
    	    					for(Object o : t.getAtributos()){
    	    						System.out.print(o + "\t\t\t");
    	    					}
    	    					System.out.println();
    	    				}
    	    			}
    	    			
    	    			
    	    					    	
    			    	session.setAttribute("nombreBd",esquemaBean.getNombre());
    			    	session.setAttribute("bd",esquemaBean);
    	    		}
    	    		   	    	    		
    	    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se han guardado los cambios","");
    	       		FacesContext.getCurrentInstance().addMessage(null, msg);
    	    	}else{
    				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al re cargar la base de datos","");
    	       		FacesContext.getCurrentInstance().addMessage(null, msg);    	    		
    	    	}
				
				
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al guardar los cambios","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			   	
	    	boolean band = false;
	    	Pattern pat = Pattern.compile("\\s*");
	
	    	for(Ejercicio e : ejercicios){
	    		if(e.getPregunta () == null || e.getRespuesta() == null || e.getRespuesta() == null || e.getQueryList() == null ){
	    			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Imposible guardar ejercicios con preguntas o respuestas vacias. Revisar ejercicio " + (e.getId()+1),"");
		       		FacesContext.getCurrentInstance().addMessage(null, msg);
		       		band = true;
		       		break;
	    		}else{
	        		Matcher matPregunta = pat.matcher(e.getPregunta());
	        		Matcher matRespuesta = pat.matcher(e.getRespuesta());
	        		Matcher matConsultas = pat.matcher(e.getConsultas());
	        		Matcher matQueryList = pat.matcher(e.getQueryList());
		    		if(matPregunta.matches() || matRespuesta.matches() || matConsultas.matches() || matQueryList.matches()){
//						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Imposible guardar ejercicios con preguntas o respuestas vacias. Revisar ejercicio " + (e.getId()+1),"");
//			       		FacesContext.getCurrentInstance().addMessage(null, msg);
			       		band = true;
			       		break;
					}else if(esquemaBean.getRelacion(e.getRespuesta()) == null && esquemaBean.getRelacionEjercicio(e.getRespuesta()) == null && esquemaBean.getRelacionAuxiliar(e.getRespuesta()) == null ){
//						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La relacion " + e.getRespuesta() + " no existe en la lista de Relaciones, Relaciones Auxiliares o de Respuestas de Ejercicios. Revisar ejercicio " + (e.getId()+1),"");
//			       		FacesContext.getCurrentInstance().addMessage(null, msg);
			       		band = true;
			       		break;
					}else{
						String respAlumno = e.getConsultas();
			    		respAlumno = respAlumno.replaceAll("[\n\r]", ";");
				    	respAlumno = respAlumno.replaceAll(";+", ";");
				    	e.setConsultas(respAlumno);
					}
	    		}
	    	}
	    	if(!band){
		    	if(EjercicioDAO.crearEjercicios(userBean.getRut(),esquemaBean, ejercicios)){
		    	    
		    		// Recarga la Base de Datos
	    			HttpSession session = Util.getSession();
	    			session.setAttribute("bd",null);
	    			
	    			esquemaBean = EsquemaDAO.cargarBDBean(esquemaBean.getNombre());
	    	    	band = EsquemaDAO.crearEsquemaLoad(userBean.getRut());
	    	    	
	    	    	if(band && esquemaBean != null){
	
	    	    		esquemaBean.setRelacionesAuxiliares(null);
	    	    		esquemaBean = RelacionDAO.cargarRelaciones(esquemaBean, userBean.getRut());
	    	    		
	    	    		if(esquemaBean != null){
	    	    			
	    	    			for(RelacionBean r : esquemaBean.getRelacionesBean()){
	    	    				System.out.println("Nombre Relacion: " + r.getNombre());
	    	    				for(TuplaBean t : r.getTuplasBean()){
	    	    					for(Object o : t.getAtributos()){
	    	    						System.out.print(o + "\t\t\t");
	    	    					}
	    	    					System.out.println();
	    	    				}
	    	    			}
	    	    			for(RelacionBean r : esquemaBean.getRelacionesEjercicios()){
	    	    				System.out.println("Nombre Relacion: " + r.getNombre());
	    	    				for(TuplaBean t : r.getTuplasBean()){
	    	    					for(Object o : t.getAtributos()){
	    	    						System.out.print(o + "\t\t\t");
	    	    					}
	    	    					System.out.println();
	    	    				}
	    	    			}    	    	
	    	    					    	
	    			    	session.setAttribute("nombreBd",esquemaBean.getNombre());
	    			    	session.setAttribute("bd",esquemaBean);
	    	    		}    				
	    	    	} 		
		    	   
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se han guardado los cambios","");
		       		FacesContext.getCurrentInstance().addMessage(null, msg);
	//	       		Util.reloadBd();
		       		
		       		return "gestionarEjercicios";
		       		
				}else{
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al guardar los cambios","");
		       		FacesContext.getCurrentInstance().addMessage(null, msg);
		       				       		
				}
	    	}
//	    	else{
//	    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Ha ocurrido un error interno al guardar los ejercicios","");
//	       		FacesContext.getCurrentInstance().addMessage(null, msg);
//	    	}
		}
		return "";
	}
	
	/* ##############################################
	 * #             Parte de Respuetas				#
	 * ##############################################
	 */
	
	private int buscarIntento(Respuesta resp){
		for(Respuesta r : respuestas){
			if(r.getEjercicio() == resp.getEjercicio()){
				return r.getEjercicio() + 1;		
			}
		}
		return 1;
	}
	
	public void responder(){
		
		System.out.println("Respondiendo");
		Calendar tiempoInicio = new GregorianCalendar();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		EsquemaBean esquemaActual = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		
		consultas = new ArrayList<Consulta>();
		
		respuesta = new Respuesta();		
		respuesta.setRut(userBean.getRut());
		respuesta.setBd(esquemaActual.getNombre() + "_" + esquemaActual.getRut());
		respuesta.setFechaBd(esquemaActual.getFecha());
		respuesta.setEjercicio(selectedEjercicio.getId());
		respuesta.setTiempoEjercicio(tiempoInicio);
		respuesta.setIntento(buscarIntento(respuesta));
		System.out.println();
	}
	
	public void cancelarRespuesta(){
		System.out.println("cancelo");
		selectedEjercicio = null;
		imprimirEjercicios();
	}
	
	public void aceptarRespuesta(){
		
		System.out.println("Respondido");
		
		Calendar tiempoEjercicio = new GregorianCalendar();
		Calendar tiempoSesion = new GregorianCalendar();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");	
		
		tiempoEjercicio.add(Calendar.MILLISECOND,-respuesta.getTiempoEjercicio().get(Calendar.MILLISECOND));
		tiempoEjercicio.add(Calendar.SECOND,-respuesta.getTiempoEjercicio().get(Calendar.SECOND));
		tiempoEjercicio.add(Calendar.MINUTE,-respuesta.getTiempoEjercicio().get(Calendar.MINUTE));
		tiempoEjercicio.add(Calendar.HOUR_OF_DAY,-respuesta.getTiempoEjercicio().get(Calendar.HOUR_OF_DAY));
		
		tiempoSesion.add(Calendar.MILLISECOND,-userBean.getInicioSesion().get(Calendar.MILLISECOND));
		tiempoSesion.add(Calendar.SECOND,-userBean.getInicioSesion().get(Calendar.SECOND));
		tiempoSesion.add(Calendar.MINUTE,-userBean.getInicioSesion().get(Calendar.MINUTE));
		tiempoSesion.add(Calendar.HOUR_OF_DAY,-userBean.getInicioSesion().get(Calendar.HOUR_OF_DAY));
				
		System.out.println("Tiempo Ejercicio: " + tiempoEjercicio.get(Calendar.HOUR_OF_DAY) + ":" + tiempoEjercicio.get(Calendar.MINUTE) + ":" + tiempoEjercicio.get(Calendar.SECOND));
		System.out.println("Tiempo Sesion: " + tiempoSesion.get(Calendar.HOUR_OF_DAY) + ":" + tiempoSesion.get(Calendar.MINUTE) + ":" + tiempoSesion.get(Calendar.SECOND));
		
		
		respuesta = ResultadoDAO.revisarRespuesta(respuesta,selectedEjercicio);
		respuesta.setTiempoEjercicio(tiempoEjercicio);
		respuesta.setTiempoSesion(tiempoSesion);
		respuesta.setConsultas(consultas);
		
		selectedEjercicio.setResultado(respuesta.isResultado());
		ejercicios.set(selectedEjercicio.getId(),selectedEjercicio);
		
		respuestas.add(respuesta);

		imprimirEjercicios();
		selectedEjercicio = null;
	}
	
	public String obtenerCalificacion(){

		if(ResultadoDAO.guardarRespuestas(respuestas)){
			
			// Preparando el conteo de respuestas
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "usuario");
			
			int cantEjercicios = ejercicios.size();
			int cantCorrectas = 0;
			int cantErroneas = 0;
			int cantOmitidas = 0;
			
			//Contando respuestas
			for(Ejercicio e : ejercicios){
				if(e.getRespuestaAlumno() == null){
					cantOmitidas++;
				}else{
					Pattern pat = Pattern.compile("\\s*");
				    Matcher mat = pat.matcher(e.getRespuestaAlumno());
				    if(mat.matches()) {
				    	cantOmitidas++;
				    }else if(e.isResultado()){
						cantCorrectas++;
					}else{
						cantErroneas++;
					}
				}		
			}
			
			Resultado resultado = new Resultado();
			resultado.setBd(esquemaBean.getNombre() + "_" + esquemaBean.getRut());
			resultado.setFechaBd(esquemaBean.getFecha());
			resultado.setCantCorrectas(cantCorrectas);
			resultado.setCantEjercicios(cantEjercicios);
			resultado.setCantErroneas(cantErroneas);
			resultado.setCantOmitidas(cantOmitidas);
			
			if(ResultadoDAO.guardarResultado(resultado)){
			
				userBean.setEjercicios(ejercicios);
				userBean.setResultado(resultado);
				
				HttpSession session = Util.getSession();
				session.setAttribute("usuario", userBean);
				
				return "obtenerCalificacion";
			
			}else{
				//Error al guardar resultados
//				Util.reloadBd();
				return "responderEjercicios";
			}
		}else{
			//Error al guardar respuestas
//			Util.reloadBd();
			return "responderEjercicios";
		}
		
	}
	
	
	/* ##############################################
	 * #             Parte de Consultas				#
	 * ##############################################
	 */
	private void copiarRespuesta(){
		selectedEjercicio.setConsultas(selectedEjercicio.getQueryList());
	}
	
	public void verRespuesta(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		
		
    	RelacionBean relacionBean = ConsultaDAO.verRespuesta("_respuesta_ejercicio_" + selectedEjercicio.getId(),esquemaBean.getNombre() + "_" + esquemaBean.getRut());
		if(relacionBean != null){
			
			columnNames = new ArrayList<String>();
			data = new ArrayList<Object[]>();

			if(relacionBean.getTuplasBean()!= null){

				tableName = relacionBean.getNombre();
				
				for(AtributoBean a : relacionBean.getAtributos())
					columnNames.add(a.getNombre());
				
				for(TuplaBean t : relacionBean.getTuplasBean())
					data.add(t.getAtributos());
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Respuesta cargada","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				tableName = null;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar el resultado","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}
			
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
				query.contains("division") || query.contains("DIVISION") ||
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

	
	private String obtenerOperador(String query) {
	    String OperadorAR = null;   		    	
	    	
	        if (query.contains(":=")) {
	        	OperadorAR = "ASIGNAR";
	        } else if (query.contains("PROYECTAR") || query.contains("proyectar")) {
	        	OperadorAR = "PROYECTAR";
	        } else if (query.contains("RENOMBRAR") || query.contains("renombrar")) {
	        	OperadorAR = "RENOMBRAR";
	        } else if (query.contains("INTER") || query.contains("inter")) {
	        	OperadorAR = "INTERSECCION";
	        } else if (query.contains("DIFERENCIA") || query.contains("diferencia") ) {
	        	OperadorAR = "DIFERENCIA";
	        } else if (query.contains("CRUZ") || query.contains("cruz") ) {
	        	OperadorAR = "PRODUCTO CRUZ";
	        } else if (query.contains("DIVISION") || query.contains("division") ) {
	        	OperadorAR = "DIVISION";
	        } else if (query.contains("REUNION_NATURAL") || query.contains("reunion_natural")) {
	        	OperadorAR = "REUNION NATURAL";
	        } else if (query.contains("REUNION_EXT_IZQ") || query.contains("reunion_ext_izq")) {
	        	OperadorAR = "REUNION_EXT_IZQ";
	        } else if (query.contains("REUNION_EXT_DER") || query.contains("reunion_ext_der")) {
	        	OperadorAR = "REUNION_EXT_DER";
	        } else if (query.contains("REUNION_EXT_FULL") || query.contains("reunion_ext_full")) {
	        	OperadorAR = "REUNION_EXT_FULL";
	        } else if (query.contains("UNION") || query.contains("union")) {
	        	OperadorAR = "UNION";
	        } else if (query.contains("SELECCIONAR") || query.contains("seleccionar")) {
	        	OperadorAR = "SELECCIONAR";
	        } else if (query.contains("ORDENAR") || query.contains("ordenar")) {
	        	OperadorAR = "ORDENAR POR";
	        } else if (query.contains("AGRUPAR") || query.contains("agrupar")) {
	        	OperadorAR = "AGRUPAR POR";
	        } else if (query.contains("funcion_agregacion") ||query.contains("MAX") || query.contains("max") || query.contains("MIN") || query.contains("min") ||
	        		query.contains("SUM") || query.contains("sum") || query.contains("COUNT") || query.contains("count")) {
	        	OperadorAR = "FUNCION_AGREGACION";
	        }

	    return OperadorAR;
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
            return "Error de Sintaxis";
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
	
	
	// Filtra consulta y luego las ejecuta
	public void ejecutar(){
		
		
		
		errorMessage = null;
		
		if(selectedEjercicio != null){
			query = selectedEjercicio.getQuery();
		}
		
		System.out.println("query: " + query);
		
		AlgebraRelacionalLexer lexer = new AlgebraRelacionalLexer(new ANTLRStringStream(query));
		System.out.println("querylexer: " + query);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		System.out.println("querytoken: " + query);
		AlgebraRelacionalParser parser = new AlgebraRelacionalParser(tokens);
		System.out.println("queryparser: " + query);
		try{
			
			// Ejecuta la consulta
			parser.st();
			System.out.println("query st: " + query);
			
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "usuario");
			EsquemaBean esquema = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			RelacionBean resultado = new RelacionBean();
			
			
			EstadisticasEjecutorAR estadisticas = new EstadisticasEjecutorAR();
			estadisticas.setRut(userBean.getRut());
			estadisticas.setBd(esquema.getNombre());
			
			estadisticas.setQuery(query); //guarda la query sin modificar, modifiedquery la altera para evitar problemas de formato dentro de la base de datos y los archivos para weka
			//String modifiedQuery = query.replace(",", " ").replace(";", " ").replace("\n", " ").replace("\r", "");
			//estadisticas.setQuery(modifiedQuery);

			estadisticas.setOperador(obtenerOperador(query));
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String fechaEjecucion = ConsultaDAOSQL.fechaHora(timestamp);
			estadisticas.setFecha(fechaEjecucion);
			
			
			// Revisa si tiene errores de sintaxis
			if(parser.getNumberOfSyntaxErrors() == 0){
				System.out.println("Query AR: " + query);
				
				System.out.println("Query AR elcontext: " + query);
				
				if(query.contains(":=")){ // Revisa si es una consulta de asignacion
					boolean band = false;
					System.out.println("Query  asignacion: " + query);
					String test[] = query.split(":=");
					test[0] = test[0].replaceAll(" ", "");
					if(!nombreValido(test[0])){
						resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
						band = true;
						errorMessage = resultado.getNombre();
				        data = null;
				        tableName = "";
				        columnNames = null;
			       		
					}else if(esquema.isRelacion(test[0])){
						resultado.setNombre("ERROR: no puedes reasignar una relacion de la lista de Relaciones");
						band = true;
						errorMessage = resultado.getNombre();
				        data = null;
				        tableName = "";
				        columnNames = null;
				        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
							
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else{
							resultado = ConsultaDAO.reunionExtDer(aux1[0],aux2[0],aux3[1],aux3[0],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}

						band = true;
					}
					
					// Asignar Reunion
					if(!band && (query.contains("reunion") || query.contains("REUNION"))){
						
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
						
						System.out.println(aux[0]); //nombre asignacion
						System.out.println(aux1[0]); // relacion 1
						System.out.println(aux2[2]); // relacion 2
						System.out.println(aux2[1]); // condicion
						
												
						if(aux[0].equals(aux1[0]) || aux[0].equals(aux2[2])){
							resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else{
							resultado = ConsultaDAO.proyectar(aux1[0],aux[1],aux[2],userBean.getRut());
							System.out.println(resultado.getNombre());
							tableName = resultado.getNombre().toLowerCase();
						}
						
						band = true;
					}
					
					// Asignar Seleccionar
					if(!band && (query.contains("seleccionar") || query.contains("SELECCIONAR"))){
						
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux1[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux20[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else{
							//resultado = ConsultaDAO.division(auxdiv11[0],auxdiv11[1],auxdiv1[2],auxdiv2[1],auxdiv2[2],userBean.getRut());
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux11[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux30[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
						}else if(!nombreValido(aux15[0])){
							resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
							errorMessage = resultado.getNombre();
					        data = null;
					        tableName = "";
					        columnNames = null;
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
						
						System.out.println("aux1[0] ===="+aux1[0]);
						System.out.println("aux1[1] ===="+aux1[1]);
						
						
						if(aux1[0].equals("")){
							// Solo renombra los atributos
							
							aux1[2] = aux1[2].replaceAll("\\)","");
							aux1[2] = aux1[2].replaceAll(" ","");
							
							EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
								    .getELResolver().getValue(elContext, null, "bd");
							
							RelacionBean relacion = esquemaBean.obtenerRelacion(aux1[2]);
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
									errorMessage = resultado.getNombre();
							        data = null;
							        tableName = "";
							        columnNames = null;
								}else if(!nombreValido(aux3[0])){
									resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
									errorMessage = resultado.getNombre();
							        data = null;
							        tableName = "";
							        columnNames = null;
								}else{
									//resultado = ConsultaDAO.renombrar(aux3[0],relacion.getNombre(),att,userBean.getRut());
									tableName = resultado.getNombre().toLowerCase();
								}
								
							}else{
								resultado.setNombre("ERROR: La relacion no existe o la cantidad de atributos a renombrar no concuerda");
								errorMessage = resultado.getNombre();
						        data = null;
						        tableName = "";
						        columnNames = null;
							}
								
						}else{
				// Renombra relacion	
							if(aux1.length == 3){
								
								
								aux1[2] = aux1[2].replaceAll("\\)","");
								aux1[2] = aux1[2].replaceAll(" ","");
								
								EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
									    .getELResolver().getValue(elContext, null, "bd");
								
								RelacionBean relacion = esquemaBean.obtenerRelacion(aux1[2]);
								String[] atributos = aux1[1].split(",");
								
								if(relacion != null && atributos.length == relacion.getAtributos().size()){
									
									 
									System.out.println(aux3[0]);
									System.out.println(aux1[0]);
									//System.out.println(att);
									System.out.println(relacion.getNombre());
									
									if(aux3[0].equals(relacion.getNombre())){
										resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");
										errorMessage = resultado.getNombre();
								        data = null;
								        tableName = "";
								        columnNames = null;
									}else if(!nombreValido(aux3[0])){
										resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
										errorMessage = resultado.getNombre();
								        data = null;
								        tableName = "";
								        columnNames = null;
									}else{
										resultado = ConsultaDAO.asigRenombrarRelAtt(aux3[0],relacion.getNombre(),aux1[0],atributos,userBean.getRut());
										tableName = aux3[0].toLowerCase();
									}
									
								}else{
									resultado.setNombre("ERROR: La relacion no existe o la cantidad de atributos a renombrar no concuerda");
									errorMessage = resultado.getNombre();
							        data = null;
							        tableName = "";
							        columnNames = null;
								}
															
							}else{
								
								EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
									    .getELResolver().getValue(elContext, null, "bd");
								
								RelacionBean relacion = esquemaBean.obtenerRelacion(aux1[1]);
								
								if(relacion!= null){
									
									System.out.println(aux3[0]);
									System.out.println(aux1[0]);
									System.out.println(relacion.getNombre());
									
									if(aux3[0].equals(relacion.getNombre())){
										resultado.setNombre("ERROR: no puedes asignar recursivamente una variable");
										errorMessage = resultado.getNombre();
								        data = null;
								        tableName = "";
								        columnNames = null;
									}else if(!nombreValido(aux3[0])){
										resultado.setNombre("ERROR: no puedes usar etiquetas del lenguaje como nombre");
										errorMessage = resultado.getNombre();
								        data = null;
								        tableName = "";
								        columnNames = null;
									}else{
										resultado = ConsultaDAO.verRelacion(aux3[0],relacion.getNombre(),userBean.getRut());
										tableName = aux3[0].toLowerCase();
									}
									
								}else{
									resultado.setNombre("ERROR: La relacion " + aux1[1] + " no existe");
									errorMessage = resultado.getNombre();
							        data = null;
							        tableName = "";
							        columnNames = null;
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

						if(selectedEjercicio != null){
							queryList = selectedEjercicio.getQueryList();
						}
						queryList = queryList + query + "\n";
						if(selectedEjercicio != null){
							selectedEjercicio.setQueryList(queryList);
						}
						estadisticas.setQuery_correcta(true);
			            estadisticas.setQuery_incorrecta(false);
			            estadisticas.setDescripcion_error(null);
			            ConsultaDAO.insertarEstadisticas(estadisticas);
						

						
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Consulta realizada satisfactoriamente","");
			       		FacesContext.getCurrentInstance().addMessage(null, msg);
			       		errorMessage = null;
			       		primerCarga = false;
				        

					}else{
						tableName = null;
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar la consulta: ","");
			       		FacesContext.getCurrentInstance().addMessage(null, msg);
						System.out.println("Error: " + resultado.getNombre());
						errorMessage = resultado.getNombre();
						estadisticas.setQuery_correcta(false);
			            estadisticas.setQuery_incorrecta(true);
			            estadisticas.setClasificacion_error(clasificarError(resultado.getNombre()));
			            String descripcionError = resultado.getNombre().replace("\n", " ").replace("\r", "");
			            estadisticas.setDescripcion_error(descripcionError);
			            ConsultaDAO.insertarEstadisticas(estadisticas);
					}
					
					
					
					
					
					
					
					
					
				}else{		// Consulta normal
					System.out.println("Query normal: " + query);
					boolean band = false;
					
					
					
					//Reunion Natural: relacion1 REUNION_NATURAL (condicion_de_igualdad) relacion2
					if(!band && (query.contains("reunion_natural") || query.contains("REUNION_NATURAL"))){
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
						band = true;
					}
					//Reunion Completa Externa
					if(!band && (query.contains("reunion_ext_full") || query.contains("REUNION_EXT_FULL"))){
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
						band = true;
					}
					//Reunion Izquierda Externa
					if(!band && (query.contains("reunion_ext_izq") || query.contains("REUNION_EXT_IZQ"))){
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
						band = true;
					}
					//Reunion Derecha Externa
					if(!band && (query.contains("reunion_ext_der") || query.contains("REUNION_EXT_DER"))){
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
						band = true;
					}
					// Proyeccion
					if(!band && (query.contains("proyectar") || query.contains("PROYECTAR"))){
						String aux[] = query.split("\\(");
						aux[1] = aux[1].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll("\\)",""); //atributos separados por comas ej: casa,id,ventanas
						
						aux[2] = aux[2].replaceAll(" ", "");
						aux[2] = aux[2].replaceAll("\\)",""); // relacion
						
						System.out.println(aux[1]);
						System.out.println(aux[2]);
						
						resultado = ConsultaDAO.proyectar(aux[1],aux[2],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;
					}
					// Seleccionar
					if(!band && (query.contains("seleccionar") || query.contains("SELECCIONAR"))){
						String aux[] = query.split("\\(");
						aux[1] = aux[1].replaceAll("\\)","");

						aux[2] = aux[2].replaceAll(" ", "");
						aux[2] = aux[2].replaceAll("\\)","");
						
						System.out.println(aux[1]);
						System.out.println(aux[2]);

						resultado = ConsultaDAO.seleccionar(aux[1],aux[2],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;
					}
					// Reunion
					if(!band && (query.contains("reunion") || query.contains("REUNION"))){
						String aux1[] = query.split("reunion|REUNION");
						aux1[0] = aux1[0].replaceAll(" ", "");
						aux1[0] = aux1[0].replaceAll("\\)","");
						aux1[0] = aux1[0].replaceAll("\\(","");
						
						System.out.println(band);
						
						String aux2[] = aux1[1].split("\\(");
						aux2[1] = aux2[1].replaceAll("\\)","");
						aux2[2] = aux2[2].replaceAll("\\)","");
						aux2[2] = aux2[2].replaceAll(" ","");
						
						System.out.println(aux1[0]);
						System.out.println(aux2[2]);
						System.out.println(aux2[1]);
						
						resultado = ConsultaDAO.reunion(aux1[0],aux2[2],aux2[1],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;
					}
					//Union
					if(!band && (query.contains("union") || query.contains("UNION"))){ 
						String aux[] = query.split("union|UNION");
						
						aux[0] = aux[0].replaceAll(" ", "");//Relacion1
						aux[1] = aux[1].replaceAll(" ", "");//Relacion2
						
						System.out.println(aux[0]);
						System.out.println(aux[1]);
						
						resultado = ConsultaDAO.union(aux[0],aux[1],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;
					}
					//Interseccion	
					if(!band && (query.contains("inter") || query.contains("INTER"))){
						String aux[] = query.split("inter|INTER");
						
						aux[0] = aux[0].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll(" ", "");
						
						System.out.println(aux[0]);
						System.out.println(aux[1]);
						
						resultado = ConsultaDAO.inter(aux[0],aux[1],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;
					}
					//Diferencia
					if(!band && (query.contains("diferencia") || query.contains("DIFERENCIA"))){
						String aux[] = query.split("diferencia|DIFERENCIA");
						
						aux[0] = aux[0].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll(" ", "");
						
						System.out.println(aux[0]);
						System.out.println(aux[1]);
						
						resultado = ConsultaDAO.diferencia(aux[0],aux[1],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;
					}	
					//Producto Cruz
					if(!band && (query.contains("cruz") || query.contains("CRUZ"))){
						String aux[] = query.split("cruz|CRUZ");
						
						aux[0] = aux[0].replaceAll(" ", "");
						aux[1] = aux[1].replaceAll(" ", "");
						
						System.out.println(aux[0]);
						System.out.println(aux[1]);
						
						resultado = ConsultaDAO.cruz(aux[0],aux[1],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;						
					}
					
					//Division
					if(!band && (query.contains("division") || query.contains("DIVISION"))){
						
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
						band = true;						
					}
					
					//Agrupar
					if(!band && (query.contains("agrupar") || query.contains("AGRUPAR"))){
						System.out.println("Query agrupar: " + query);
						//query = "(Lista_de_atributos) AGRUPAR (lista_de_funciones_de_agregacion) (relacion) "
						String aux12[] = query.split("agrupar|AGRUPAR");
						
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
						
						resultado = ConsultaDAO.agrupar(aux12[0],aux13[1],aux13[2],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;						
					}
					
					
					
					// Funcion de Agregacion
					if(!band && (query.contains("count") || query.contains("COUNT") || query.contains("min") || query.contains("MIN") || query.contains("max") 
							|| query.contains("MAX") || query.contains("sum") || query.contains("SUM") ||  query.contains("avg") || query.contains("AVG"))){
						//"funcion_de_agregacion (atributo) (relacion) "
						System.out.println("QUERY FUNCION DE AGREGACION: "+query);
						String aux10[] = query.split("\\(");
						
						String aux9[] = aux10[0].split(" ");
						//aux9[0] = aux9[0].replaceAll("\\)","");//aux[0] = funcion agregada
						System.out.println(aux9[0]);
						System.out.println(aux9[1]);
						
						aux10[1] = aux10[1].replaceAll("\\)","");//aux[1] = atributo
						System.out.println(aux10[1]);

						
						System.out.println(aux9[0]);
						System.out.println(aux9[1]);
						System.out.println(aux10[1]);

						resultado = ConsultaDAO.funcionAgregacion(aux9[0],aux9[1],aux10[1],userBean.getRut());
						tableName = resultado.getNombre();
						band = true;
					}
					
					
					//order
					if(!band && (query.contains("ordenar") || query.contains("ORDENAR"))){
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
						band = true;						
					}
					
					
					
/////////////	//Renombrar
					if(!band && (query.contains("renombrar") || query.contains("RENOMBRAR"))){
						//"RENOMBRAR nuevo_nombre(lista_de_atributos) (relacion) "
						String aux[] = query.split("renombrar|RENOMBRAR");
						String aux1[] = aux[1].split("\\(");
						aux1[0] = aux1[0].replaceAll(" ","");	
						aux1[1] = aux1[1].replaceAll("\\)","");
						aux1[1] = aux1[1].replaceAll(" ","");
						
						System.out.println("aux1[0] ===="+aux1[0]);
						System.out.println("aux1[1] ===="+aux1[1]);
						
						if(aux1[0].equals("")){
							// Solo renombra los atributos
							System.out.println(" ==== primer if");
							aux1[2] = aux1[2].replaceAll("\\)","");
							aux1[2] = aux1[2].replaceAll(" ","");
							
							EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
								    .getELResolver().getValue(elContext, null, "bd");
							
							RelacionBean relacion = esquemaBean.obtenerRelacion(aux1[2]);
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
								errorMessage = resultado.getNombre();
						        data = null;
						        tableName = "";
						        columnNames = null;
							}
								
						}else{
							// Renombra relacion	Y Atributos
							if(aux1.length == 3){
								System.out.println(" ==== segundo if");
								
								aux1[2] = aux1[2].replaceAll("\\)","");
								aux1[2] = aux1[2].replaceAll(" ","");
								
								EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
									    .getELResolver().getValue(elContext, null, "bd");
								
								RelacionBean relacion = esquemaBean.obtenerRelacion(aux1[2]);
								String[] atributos = aux1[1].split(",");
								
								if(relacion != null && atributos.length == relacion.getAtributos().size()){
									
									System.out.println(aux1[0]);
									System.out.println(relacion.getNombre());
									
									resultado = ConsultaDAO.renombrarRelAtt(relacion.getNombre(),aux1[0],atributos,userBean.getRut());
									tableName = aux1[0].toLowerCase();
									
								}else{
									resultado.setNombre("ERROR: La relacion no existe o la cantidad de atributos a renombrar no concuerda");
									errorMessage = resultado.getNombre();
							        data = null;
							        tableName = "";
							        columnNames = null;
								}
															
							}else{
								
								EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
									    .getELResolver().getValue(elContext, null, "bd");
								
								RelacionBean relacion = esquemaBean.obtenerRelacion(aux1[1]);
								
								if(relacion!= null){
									System.out.println(" ==== RENOMBRA SOLO RELACION");
									System.out.println(aux1[0]);
									System.out.println(relacion.getNombre());
									
									resultado = ConsultaDAO.renombrarRel(relacion.getNombre(),aux1[0],userBean.getRut());
									tableName = aux1[0].toLowerCase();
									
								}else{
									resultado.setNombre("ERROR: La relacion no existe o la cantidad de atributos a renombrar no concuerda");
									errorMessage = resultado.getNombre();
							        data = null;
							        tableName = "";
							        columnNames = null;
								}							
							}						
						}
						band = true;
					}
			
					columnNames = new ArrayList<String>();
					data = new ArrayList<Object[]>();

					// Revisa el resultado de la consulta
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
						
						estadisticas.setQuery_correcta(true);
			            estadisticas.setQuery_incorrecta(false);
			            estadisticas.setClasificacion_error(null);
			            estadisticas.setDescripcion_error(null);
			            ConsultaDAO.insertarEstadisticas(estadisticas);
					
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Consulta realizada satisfactoriamente","");
			       		FacesContext.getCurrentInstance().addMessage(null, msg);
			       		errorMessage = null;
			       		primerCarga = false;
					}else{
						tableName = "";
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar la consulta ","");
			       		FacesContext.getCurrentInstance().addMessage(null, msg);
						System.out.println("Error: " + resultado.getNombre());
						errorMessage = resultado.getNombre();
				        data = null;
				        tableName = "";
				        columnNames = null;
						
						estadisticas.setQuery_correcta(false);
			            estadisticas.setQuery_incorrecta(true);
			            estadisticas.setClasificacion_error(clasificarError(resultado.getNombre()));
			            String descripcionError = resultado.getNombre().replace("\n", " ").replace("\r", "");
			            estadisticas.setDescripcion_error(descripcionError);
			            ConsultaDAO.insertarEstadisticas(estadisticas);
					}
					
					
				}
			
			// Error de sintaxis de consultas
			}else{
				tableName = "";
				columnNames = null;
				data = null;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de sintaxis en consulta de Algebra Relacional","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
	       		
	       		errorMessage = "Error de sintaxis en consulta de Algebra Relacional";
		        data = null;
		        tableName = "";
		        columnNames = null;
	       		
	       		
	       		estadisticas.setQuery_correcta(false);
	            estadisticas.setQuery_incorrecta(true);
	            estadisticas.setClasificacion_error("Error de Sintaxis");
	            estadisticas.setDescripcion_error("Error de sintaxis en consulta de Algebra Relacional");
	            
	            ConsultaDAO.insertarEstadisticas(estadisticas);
			}
		} catch (RecognitionException e) {
			tableName = "";
			columnNames = null;
			data = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error Interno al ejecutar la consulta","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
       		
		}

	}
	
	public void limpiarResultados() {
	    data = null;
	    tableName = null;
	    columnNames = null;
	    errorMessage = null;
	    //query = null;
	    primerCarga = true;
	}
	
	
	public void ayudaSeleccionar(){
		if(selectedEjercicio != null)
			selectedEjercicio.setQuery("SELECCIONAR (Lista_de_condiciones) (relacion) ");
		else
			query = "SELECCIONAR (Lista_de_condiciones) (relacion) ";
	}
	
	public void ayudaProyectar(){
		if(selectedEjercicio != null)
			selectedEjercicio.setQuery("PROYECTAR (Lista_de_atributos) (relacion) ");
		else
			query = "PROYECTAR (Lista_de_atributos) (relacion) ";
	}

	public void ayudaRenombrar(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("RENOMBRAR nuevo_nombre(lista_de_atributos) (relacion) ");
		else
		query = "RENOMBRAR nuevo_nombre(lista_de_atributos) (relacion) ";
	}
	
	public void ayudaUnion(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion UNION relacion ");
		else
		query = "relacion UNION relacion ";
	}
	
	public void ayudaInterseccion(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion INTER relacion ");
		else
		query = "relacion INTER relacion";
	}
	
	public void ayudaDiferencia(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion DIFERENCIA relacion ");
		else
		query = "relacion DIFERENCIA relacion";
	}
	
	public void ayudaCruz(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion CRUZ relacion ");
		else
		query = "relacion CRUZ relacion";
	}
	
	public void ayudaReunion(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("(relacion) REUNION (Lista_de_condiciones_de_reunion) (relacion) ");
		else
		query = "(relacion) REUNION (Lista_de_condiciones_de_reunion) (relacion) ";
	}
	
	public void ayudaReunionNatural(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion REUNION_NATURAL (condicion_de_igualdad) relacion ");
		else
		query = "relacion REUNION_NATURAL (condicion_de_igualdad) relacion";
	}
	
	public void ayudaReunionExtIzq(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion REUNION_EXT_IZQ (condicion_de_igualdad) relacion ");
		else
		query = "relacion REUNION_EXT_IZQ (condicion_de_igualdad) relacion";
	}
	
	public void ayudaReunionExtDer(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion REUNION_EXT_DER (condicion_de_igualdad) relacion ");
		else
		query = "relacion REUNION_EXT_DER (condicion_de_igualdad) relacion";
	}
	
	public void ayudaReunionExtComp(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion REUNION_EXT_FULL (condicion_de_igualdad) relacion ");
		else
		query = "relacion REUNION_EXT_FULL (condicion_de_igualdad) relacion";
	}
	
	public void ayudaAsignar(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("relacion := consulta ");
		else
		query = "relacion := consulta ";
	}
	
	public void ayudaDivision(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("(atributo1,atributo2) (relacion1) DIVISION (atributo2) (relacion2)");
		else
		query = "(atributo1,atributo2) (relacion1) DIVISION (atributo2) (relacion2)";
	}
	
	public void ayudaAgrupar(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("(Lista_de_atributos) AGRUPAR agregacion(atributo) (relacion) ");
		else
		query = "(Lista_de_atributos) AGRUPAR agregacion(atributo) (relacion) ";
	}
	
	public void ayudaOrdenar(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("ORDENAR atributo(asc/desc) (relacion)");
		else
		query = "ORDENAR atributo(asc/desc) (relacion) ";
	}
	
	public void ayudaFuncionAgregacion(){
		if(selectedEjercicio != null)
		selectedEjercicio.setQuery("funcion_agregacion atributo (relacion)");
		else
		query = "funcion_agregacion atributo (relacion)";
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}


