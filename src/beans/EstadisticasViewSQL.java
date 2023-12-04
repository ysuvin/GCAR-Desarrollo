package beans;

import javax.annotation.PostConstruct;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Collections;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.HashMap;


import dao.EstadisticasDAO;
import dao.ResultadoDAO;
import dao.ConsultaDAO;
import dao.ConsultaDAOSQL;

import util.EstadisticasEjecutorAR;
import util.EstadisticasEjecutorSQL;
import util.EstadisticaConsulta;
import util.EstadisticaRespuesta;
import util.EstadisticaResultado;
import util.EstadisticaTodo;
import util.Respuesta;
import util.Resultado;
import util.User;


import java.util.concurrent.ThreadLocalRandom; //borrar, librearia de prueba

 
@ManagedBean(name = "estadisticasViewSQL")
@ViewScoped
public class EstadisticasViewSQL implements Serializable {

   private boolean ver = false;
   
   private List<EstadisticasEjecutorSQL> datasql;
   
   private DecimalFormat df = new DecimalFormat("##.##");
     
   private String selectedBd;
   private String selectedRut;
   private String selectedFecha;
   private String fecha;
   private String rut;
   
   private int cantQueryEjec;
   private int cantQueryCorrectas;
   private int cantQueryIncorrectas;
   private String cantQueryCorrecta;
   private String cantQueryIncorrecta;
   private String porcQueryCorrecta;
   private String porcQueryIncorrecta;
   private String promQueryCorrecta;
   private String promQueryIncorrecta;
   
   private List<String> clasifError;
   private List<String> clausulasUtilizadas;
   private List<String> ruts;
   private List<String> fechas;
   private List<String> bds;
   private List<User> nombres;
   private User usuario;

   private BarChartModel errorModel;
   private BarChartModel barClausulas;
   private PieChartModel pieClausulas;
   private PieChartModel pieConsultas;
   
   
   private boolean descargar = false;
   private StreamedContent file;
   private String path;
   private String contentType;


	public boolean getVer(){
	   return ver;
   }
	public void setVer(boolean ver){
	   this.ver = ver;
   }
	public List<String> getBds() {
	   return bds;
	}
	public void setBds(List<String> bds) {
		bds = this.bds;
	}
	public String getSelectedBd() {
		return selectedBd;
	}
	public void setSelectedBd(String selectedBd) {
		this.selectedBd = selectedBd;
	}
	
	
	public List<String> getRuts() {
		return ruts;
	}
	public void setRuts(List<String> ruts) {
	    this.ruts = ruts;
	}
	public String getSelectedRut() {
		return selectedRut;
	}
	public void setSelectedRut(String selectedRut) {
		this.selectedRut = selectedRut;
	}
	

	// ESTADISTICAS EJECUTOR SQL
	public int getCantQueryEjec() {
		return cantQueryEjec;
	}
	public void setCantQueryEjec(int cantQueryEjec) {
		this.cantQueryEjec = cantQueryEjec;
	}
	public String getPromQueryCorrecta() {
		return promQueryCorrecta;
	}
	public void setPromQueryCorrecta(String promQueryCorrecta) {
		this.promQueryCorrecta = promQueryCorrecta;
	}
	public String getPromQueryIncorrecta() {
		return promQueryIncorrecta;
	}
	public void setPromQueryIncorrecta(String promQueryIncorrecta) {
		this.promQueryIncorrecta = promQueryIncorrecta;
	}
	public String getCantQueryCorrecta() {
		return cantQueryCorrecta;
	}
	public void setCantQueryCorrecta(String cantQueryCorrecta) {
		this.cantQueryCorrecta = cantQueryCorrecta;
	}
	public String getCantQueryIncorrecta() {
		return cantQueryIncorrecta;
	}
	public void setCantQueryIncorrecta(String cantQueryIncorrecta) {
		this.cantQueryIncorrecta = cantQueryIncorrecta;
	}
	public String getPorcQueryCorrecta() {
		return porcQueryCorrecta;
	}
	public void setPorcQueryCorrecta(String porcQueryCorrecta) {
		this.porcQueryCorrecta = porcQueryCorrecta;
	}
	public String getPorcQueryIncorrecta() {
		return porcQueryIncorrecta;
	}
	public void setPorcQueryIncorrecta(String porcQueryIncorrecta) {
		this.porcQueryIncorrecta = porcQueryIncorrecta;
	}
	public List<String> getClasifError() {
		return clasifError;
	}
	public void setClasifError(List<String> clasifError) {
		this.clasifError = clasifError;
	}
	public List<String> getClausulasUtilizadas(){
		return clausulasUtilizadas;
	}
	
	public BarChartModel getErrorModel() {
	    return errorModel;
	}
	public BarChartModel getBarClausulas() {
		return barClausulas;
	}
	public void setBarClausulas(BarChartModel barClausulas) {
		this.barClausulas = barClausulas;
	}
	public List<String> getFechas() {
		return fechas;
	}
	public void setFechas(List<String> fechas) {
		this.fechas = fechas;
	}
	public String getSelectedFecha() {
		return selectedFecha;
	}
	public void setSelectedFecha(String selectedFecha) {
		this.selectedFecha = selectedFecha;
	}
	public List<User> getNombres() {
		return nombres;
	}
	public void setNombres(List<User> nombres) {
		this.nombres = nombres;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public PieChartModel getPieClausulas() {
        return pieClausulas;
    }

    public void setPieClausulas(PieChartModel pieClausulas) {
        this.pieClausulas = pieClausulas;
    }
    public PieChartModel getPieConsultas() {
		return pieConsultas;
	}
	public void setPieConsultas(PieChartModel pieConsultas) {
		this.pieConsultas = pieConsultas;
	}
	public int getCantQueryCorrectas() {
		return cantQueryCorrectas;
	}
	public void setCantQueryCorrectas(int cantQueryCorrectas) {
		this.cantQueryCorrectas = cantQueryCorrectas;
	}
	public int getCantQueryIncorrectas() {
		return cantQueryIncorrectas;
	}
	public void setCantQueryIncorrectas(int cantQueryIncorrectas) {
		this.cantQueryIncorrectas = cantQueryIncorrectas;
	}
	
	//DESCARGAS
	public boolean isDescargar() {
   		return descargar;
	}
	public void setDescargar(boolean descargar) {
		this.descargar = descargar;
	}
	public StreamedContent getFile() {
		return file;
	}
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
	@PostConstruct
	public void init(){	   
		System.out.println("INICIO INIT EJECUTOR SQL");
		datasql = new ArrayList<EstadisticasEjecutorSQL>();
		cantQueryEjec = datasql.size();
		datasql = ConsultaDAOSQL.cargarEstEjecSQL();
		
		this.cantQueryCorrecta = df.format(0);
		this.cantQueryIncorrecta = df.format(0);
		this.porcQueryCorrecta = df.format(0);
		this.porcQueryIncorrecta = df.format(0);
		
		ruts = datasql.stream()
			    .map(EstadisticasEjecutorSQL::getRut)
			    .filter(Objects::nonNull)
			    .distinct()
			    .collect(Collectors.toList());
			ruts.add(0, "GLOBAL");
		ruts.forEach(System.out::println);

		selectedRut = "GLOBAL";
				
		clasifError = new ArrayList<>();
		clausulasUtilizadas = new ArrayList<>();
		isGlobalSelected();
		verEstadisticas();


	}
	
	public void verEstadisticas(){
		
		if ("GLOBAL".equals(selectedRut)) {
	        datasql = ConsultaDAOSQL.cargarEstEjecSQL();
	    }
	    else {
	        datasql = ConsultaDAOSQL.cargarEstEjecSQLRut(selectedRut);
	        cargarDatosUsuario();
	        isGlobalSelected();
	    }
		
		if(datasql != null){			
				cantQueryEjec = datasql.size();
				ver = true;
				
				int cantQueryCorrecta = 0;
				int cantQueryIncorrecta = 0;
				float porcQueryCorrecta = 0;
				float porcQueryIncorrecta = 0;
				int cantQueryCorrectas = 0;
		        int cantQueryIncorrectas = 0;

				for(EstadisticasEjecutorSQL e : datasql){
					cantQueryCorrecta = cantQueryCorrecta + (e.isQuery_correcta() ? 1 : 0);
					cantQueryIncorrecta = cantQueryIncorrecta + (e.isQuery_incorrecta() ? 1 : 0);	
				}				
				this.cantQueryCorrecta = df.format(cantQueryCorrecta);
				this.cantQueryIncorrecta = df.format(cantQueryIncorrecta);
				this.porcQueryCorrecta = df.format((cantQueryCorrecta * 100)/datasql.size());
				this.porcQueryIncorrecta = df.format((cantQueryIncorrecta * 100)/datasql.size());
				this.cantQueryCorrectas = cantQueryCorrecta;
		        this.cantQueryIncorrectas = cantQueryIncorrecta;
		        
				createErrorModel();
				createBarClausulas(); 
				createPieClausulas();
				createPieConsultas();

			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar las estadisticas del ejecutor sql","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}		
	}
	
	
	
	public String cargarDatosUsuario() {
	    List<User> nombres = ConsultaDAOSQL.cargarDatosUser(selectedRut);
	    if (!nombres.isEmpty()) {
	        usuario = nombres.get(0);
	        return usuario.getNombre1() + " " + usuario.getPaterno() + " " + usuario.getMaterno();
	    } else {
	        return "Usuario no encontrado";
	    }
	}



	
	private void createErrorModel() {
		clasifError.clear();
		for(EstadisticasEjecutorSQL e : datasql){
		    if(e.getClasificacion_error() != null){
		        clasifError.add(e.getClasificacion_error());
		    }
		}
		Collections.sort(clasifError);		
	    errorModel = new BarChartModel();

	    ChartSeries errors = new ChartSeries();
	    errors.setLabel("Errores");
	    for (String errorType : clasifError) {
	        errors.set(errorType, Collections.frequency(clasifError, errorType));
	    }
	    //errorModel = initErrorModel();
	    errorModel.setAnimate(true);
	    errorModel.addSeries(errors);
	    //errorModel.setTitle("Clasificacion de Errores");
	    errorModel.setLegendPosition("ne");
	    errorModel.setSeriesColors("1484e6");

	    Axis xAxis = errorModel.getAxis(AxisType.X);
	    xAxis.setLabel("Tipo de Error");
	    xAxis.setTickAngle(-35);

	    Axis yAxis = errorModel.getAxis(AxisType.Y);
	    yAxis.setLabel("Cantidad de Errores");
	    yAxis.setMin(0);
	    System.out.println("ERRORMODEL CREADO");	
	}
	
	private void createBarClausulas() {
		clausulasUtilizadas.clear();
		for (EstadisticasEjecutorSQL data : datasql) {
	    	if(data.getClausula() != null){
	    		clausulasUtilizadas.add(data.getClausula());
	    	}
	    }
		Collections.sort(clausulasUtilizadas);		
	    barClausulas = new BarChartModel();

	    ChartSeries clau = new ChartSeries();
	    clau.setLabel("Clausulas");
	    for (String clauType : clausulasUtilizadas) {
	    	clau.set(clauType, Collections.frequency(clausulasUtilizadas, clauType));
	    }
	    //clau.set("color", "'background-color: #FF0000;'");
	    barClausulas.setAnimate(true);
	    barClausulas.addSeries(clau);
	    //barClausulas.setTitle("Clausulas Ejecutadas");
	    barClausulas.setLegendPosition("ne");
	    barClausulas.setSeriesColors("1484e6");

	    Axis xAxis = barClausulas.getAxis(AxisType.X);
	    xAxis.setLabel("Clausula");
	    xAxis.setTickAngle(-35);

	    Axis yAxis = barClausulas.getAxis(AxisType.Y);
	    yAxis.setLabel("Cantidad de Ejecuciones");
	    yAxis.setMin(0);
	    System.out.println("CREATEBARCLAUSULAS CREADO");
	}
	
	
	private void createPieClausulas() {
	    clausulasUtilizadas.clear();
	    for (EstadisticasEjecutorSQL data : datasql) {
	        if(data.getClausula() != null){
	            clausulasUtilizadas.add(data.getClausula());
	        }
	    }
	    Collections.sort(clausulasUtilizadas);        
	    pieClausulas = new PieChartModel();

	    for (String clauType : clausulasUtilizadas) {
	        int frequency = Collections.frequency(clausulasUtilizadas, clauType);
	        pieClausulas.set(clauType, frequency);
	    }

	    //pieClausulas.setTitle("Clausulas Ejecutadas");
	    pieClausulas.setLegendPosition("ne");
	    //pieClausulas.setFill(false);
	    pieClausulas.setShowDataLabels(true);
	    pieClausulas.setDiameter(300);
	    pieClausulas.setShadow(true);

	    System.out.println("PIECLAUSULAS CREADO");
	}

	private void createPieConsultas() {
	    pieConsultas = new PieChartModel();

	    pieConsultas.set("Correctas", cantQueryCorrectas);
	    pieConsultas.set("Incorrectas", cantQueryIncorrectas);

	    //pieConsultas.setTitle("Consultas Ejecutadas");
	    pieConsultas.setLegendPosition("ne");
	    pieConsultas.setShowDataLabels(true);
	    pieConsultas.setDiameter(300);
	    pieConsultas.setSeriesColors("1484e6, ff7675");

	    System.out.println("PIE desempeño creado");
	}
	
	
	
	
	
	
    public boolean isGlobalSelected() {
        return "GLOBAL".equalsIgnoreCase(selectedRut);
    }

    public void generarDescarga(){
    	System.out.println("Preparando Descarga datos Ejecutor SQL");
    	
	    FileWriter f = null;
	    PrintWriter pw = null;
	    List<EstadisticasEjecutorSQL> todos = EstadisticasDAO.selectEjecutorSQL();
    	
    	if(todos != null){    
		    try{
		    	 			    	
		    	f = new FileWriter("C:\\Repositorios\\Segovia\\EjecutorSQL.arff");
		    	pw = new PrintWriter(f);  	
		    	
		    	pw.println("@RELATION ejecutorSQL");
		    	pw.println("");
		    	pw.println("@ATTRIBUTE idQuerySQL NUMERIC");
		    	pw.println("@ATTRIBUTE rutUsuario STRING");		    	
		    	pw.println("@ATTRIBUTE bd STRING");		  
		    	pw.println("@ATTRIBUTE queryEjecutada STRING");
		    	pw.println("@ATTRIBUTE clausulaSQL STRING");
		    	pw.println("@ATTRIBUTE queryCorrecta {true,false}");
		    	pw.println("@ATTRIBUTE queryIncorrecta {true,false}");
		    	pw.println("@ATTRIBUTE clasificacionError STRING");
		    	pw.println("@ATTRIBUTE descripcionError STRING");
		    	pw.println("@ATTRIBUTE fechaEjecucion DATE \"yyyy-MM-dd HH:mm:ss\"");	
		    	pw.println("");
		    	pw.println("@DATA");
		    	for (EstadisticasEjecutorSQL t : todos) {
		    	    String newQuery = (t.getQuery() != null) ? t.getQuery().replace(",", "").replace(";", "").replace("\n", " ").replace("\r", "").replace("\"", "") : null;
		    	    String newDescripcionError = (t.getDescripcion_error() != null) ? t.getDescripcion_error().replace(",", "").replace(";", "").replace("\n", " ").replace("\r", "").replace("\"", "") : null;

		    	    // Imprime los valores
		    	    pw.print(t.getId() + ",'" + t.getRut() + "',\"" + t.getBd() + "\"," +"\""+
		    	    		newQuery +"\""+ "," + "\"" + t.getClausula() + "\"" + "," +
		    	             t.isQuery_correcta() + "," + t.isQuery_incorrecta() + ",\"" +
		    	             t.getClasificacion_error() + "\"," +"\""+ newDescripcionError +"\""+ ",'" + t.getFecha() + "\"");
		    	    pw.println(""); // Agregar un salto de línea al final de cada línea
		    	}

		      	
		    }catch(Exception e){
		      	System.out.println("Error crear archivo de todo -> " + e.getMessage());
		      	
		      	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear el archivo de todas las estadisticas","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
		    }finally{
		      	try{
		      		if(f != null)
		      			f.close();
		    	}catch(Exception e2){
		      		System.out.println("Error cerrar archivo de todas las estadisticas -> " + e2.getMessage());
		      	}
		    }
    	}else{
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar todas las estadisticas","");
    		
       		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    	
    	descargar = true;
    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se han generado todas las Estadisticas","");
   		FacesContext.getCurrentInstance().addMessage(null, msg);	
    }
    public void descargarEjecutorSQL() throws IOException{
    	path = "C:\\Repositorios\\Segovia\\EjecutorSQL.arff";  
    	contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
    	file = new DefaultStreamedContent(new FileInputStream(path), contentType, "EjecutorSQL.arff");
    }
	
    
    
    
    
	
	

    
}