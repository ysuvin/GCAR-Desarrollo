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
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;

import java.util.HashMap;


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
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import org.primefaces.model.chart.CategoryAxis;

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
import util.Ejercicio;
import util.User;



 
@ManagedBean(name = "estadisticasView")
@ViewScoped
public class EstadisticasView implements Serializable {
 
   private String nombreBd;
   private int cantidadResp;
   private String promedioAcertadas;
   private String promedioErroneas;
   private String promedioOmitidas;
   private String promedioTiempoRespuestas;
   private String promedioIntentosRespuestas;
   private String promedioPorcentaje;
   private boolean ver = false;
   private List<Integer> maxEjercicios; //lista que contiene el id de cada ejercicio
   private List<Double> promedioTiempos; // lista que contiene el tiempo promedio de cada ejercicio
   
   private List<Integer> ejerciciosYTiempoSegundos = new ArrayList<>();
   private Map<Integer, Double> promediosTiemposPorEjercicio = new HashMap<>();


   
   private List<Respuesta> respuestas;
   private List<Resultado> resultados;
   private List<Ejercicio> ejercicios;
   private List<EstadisticasEjecutorSQL> datasql;
   
   
   private DecimalFormat df = new DecimalFormat("##.##");
   
   private BarChartModel animatedModel2;
   private LineChartModel chartTiempoEjercicio;
   private LineChartModel lineModel;
   
   private List<String> bds;
   private String selectedBd;
   private List<String> ruts;
   private String selectedRut;
   private List<String> fechas;
   private String selectedFecha;
   private String fecha;
   private String rut;
   private List<User> nombres;
   private User usuario;
   
   private StreamedContent file;
   private String path;
   private String contentType;
   private boolean descargar = false;
   
   private String tipoEstadisticaSeleccionada = "ejerciciosAR";
   private int cantQueryEjec;
   private String promQueryCorrecta;
   private String promQueryIncorrecta;
   
   
   
   
   
   
   
   public String getTipoEstadisticaSeleccionada() {
       return tipoEstadisticaSeleccionada;
   }

   public void setTipoEstadisticaSeleccionada(String tipoEstadisticaSeleccionada) {
       this.tipoEstadisticaSeleccionada = tipoEstadisticaSeleccionada;
   }
   
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
	public List<String> getFechas() {
		return fechas;
	}
	public void setFechas(List<String> fechas) {
		fechas = this.fechas;
	}
	public String getSelectedFecha() {
		return selectedFecha;
	}
	public void setSelectedFecha(String selectedFecha) {
		this.selectedFecha = selectedFecha;
	}
	
	
	public List<String> getRuts() {
		return ruts;
	}
	public void setRuts(List<String> ruts) {
		this.ruts = ruts;
	}
	public String getSelectedRut() {
		return selectedFecha;
	}
	public void setSelectedRut(String selectedRut) {
		this.selectedRut = selectedRut;
	}
	
	public List<User> getNombres() {
		return nombres;
	}

	public void setNombres(List<User> nombres) {
		this.nombres = nombres;
	}

	public List<Ejercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(List<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

	
	// ESTADISTICAS EJERCICIOS AR
	public String getNombreBd() {
		return nombreBd;
	}
	public void setNombreBd(String nombreBd) {
		this.nombreBd = nombreBd;
	}
	public int getCantidadResp() {
		return cantidadResp;
	}
	public void setCantidadResp(int cantidadResp) {
		this.cantidadResp = cantidadResp;
	}
	public String getPromedioAcertadas() {
		return promedioAcertadas;
	}
	public void setPromedioAcertadas(String promedioAcertadas) {
		this.promedioAcertadas = promedioAcertadas;
	}
	public String getPromedioErroneas() {
		return promedioErroneas;
	}
	public void setPromedioErroneas(String primedioErroneas) {
		this.promedioErroneas = primedioErroneas;
	}
	public String getPromedioOmitidas() {
		return promedioOmitidas;
	}
	public void setPromedioOmitidas(String primedioOmitidas) {
		this.promedioOmitidas = primedioOmitidas;
	}
	public String getPromedioTiempoRespuestas() {
		return promedioTiempoRespuestas;
	}
	public void setPromedioTiempoRespuestas(String promedioTiempoRespuestas) {
		this.promedioTiempoRespuestas = promedioTiempoRespuestas;
	}
	public String getPromedioIntentosRespuestas() {
		return promedioIntentosRespuestas;
	}
	public void setPromedioIntentosRespuestas(String promedioIntentosRespuestas) {
		this.promedioIntentosRespuestas = promedioIntentosRespuestas;
	}
	public String getPromedioPorcentaje() {
		return promedioPorcentaje;
	}
	public void setPromedioPorcentaje(String promedioPorcentaje) {
		this.promedioPorcentaje = promedioPorcentaje;
	}
	public List<Respuesta> getRespuesta() {
		return respuestas;
	}
	public void setRespuesta(List<Respuesta> respuesta) {
		this.respuestas = respuesta;
	}
	public List<Resultado> getResultados() {
		return resultados;
	}
	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}
	public void setAnimatedModel2(BarChartModel animatedModel2) {
		this.animatedModel2 = animatedModel2;
	}
	
	public LineChartModel getChartTiempoEjercicio() {
	    return chartTiempoEjercicio;
	}

	public void setChartTiempoEjercicio(LineChartModel chartTiempoEjercicio) {
	    this.chartTiempoEjercicio = chartTiempoEjercicio;
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
	
	
	
	@PostConstruct
	public void init(){	
		bds = ResultadoDAO.obtenerEsquemas();         
			
		nombreBd = "";		
		resultados = new ArrayList<Resultado>();
		respuestas = new ArrayList<Respuesta>();
		ejercicios = new ArrayList<Ejercicio>();
		cantidadResp = resultados.size();

		
		this.promedioPorcentaje = df.format(0);
		this.promedioAcertadas = df.format(0);
		this.promedioErroneas = df.format(0);
		this.promedioOmitidas = df.format(0);
		this.promedioIntentosRespuestas = df.format(0);
		this.promedioTiempoRespuestas =	"0:0:0";
		
		selectedRut = "GLOBAL";
		//isGlobalSelected();
		createAnimatedModels();

		
	}
		
	
	
	public void onBdChangue(){
		System.out.println(selectedBd);
		fechas = ResultadoDAO.obtenerFechas(selectedBd);
		
	}
	public void onFechaBdChangue(){
		System.out.println(selectedFecha);
		ruts = ResultadoDAO.obtenerRuts(selectedFecha);
		LinkedHashSet<String> rutsSet = new LinkedHashSet<String>();
		rutsSet.add("GLOBAL"); 
		rutsSet.addAll(ruts); 
		ruts.clear(); 
		ruts.addAll(rutsSet); 
		ruts.forEach(System.out::println);
		
	}
		
	public void verEstadisticas(){
		
		nombreBd = selectedBd;
		fecha = selectedFecha;
		
		resultados = ResultadoDAO.cargarResultado(selectedBd,selectedFecha);
		
		
		if(resultados != null){
			
			if (!"GLOBAL".equals(selectedRut)) {
			    respuestas = ResultadoDAO.cargarRespuestasRut(selectedBd, selectedFecha, selectedRut);
			    resultados = ResultadoDAO.cargarResultadoRut(selectedBd,selectedFecha, selectedRut);
			    cargarDatosUsuario();
		        isGlobalSelected();
			}else {	
				respuestas = ResultadoDAO.cargarRespuestas(selectedBd,selectedFecha);
			}
			
			if(respuestas != null){
								
				cantidadResp = resultados.size();
				ver = true;
				
				float promedioAcertadas = 0;
				float promedioErroneas = 0;
				float promedioOmitidas = 0;
				
				int totales = 0;
				for(Resultado r : resultados){
					promedioAcertadas = promedioAcertadas + r.getCantCorrectas();
					promedioErroneas = promedioErroneas + r.getCantErroneas();
					promedioOmitidas = promedioOmitidas + r.getCantOmitidas();
					totales = totales + r.getCantEjercicios();
														
					
				}
				this.promedioPorcentaje = df.format((promedioAcertadas * 100)/totales); //se queda
				this.promedioAcertadas = df.format(promedioAcertadas); // /resultados.size()); // se rehace a cantidad acertadas ya no es promedio
				this.promedioErroneas = df.format(promedioErroneas); // /resultados.size()); // se rehace a cantidad aerroneas ya no es promedio
				this.promedioOmitidas = df.format(promedioOmitidas); // /resultados.size());  // se rehace a cantidad omitidas ya no es promedio
				
				float promedioIntentosRespuestas = 0;
				Calendar tiempoR = new GregorianCalendar();
				promediosTiemposPorEjercicio.clear();
				Map<Integer, List<Double>> tiemposPorEjercicio = new HashMap<>();
				long tiempoRespuesta = 0;
				for(Respuesta r : respuestas){				
					tiempoRespuesta = tiempoRespuesta + r.getTiempoEjercicio().getTimeInMillis();
					promedioIntentosRespuestas = promedioIntentosRespuestas + r.getConsultas().size();
					
			        
			        
			        int ejercicio = r.getEjercicio();
			        long tiempoEjercicio = r.getTiempoEjercicio().getTimeInMillis();
			        // Convierte el tiempo de tipo long a Double.
			        Double tiempoEjercicioDouble = (double) tiempoEjercicio;
			        // Agrega el tiempo del ejercicio actual a la lista correspondiente en el mapa.
			        tiemposPorEjercicio.computeIfAbsent(ejercicio, k -> new ArrayList<>()).add(tiempoEjercicioDouble);
				}
				
				
				
				
				for (Map.Entry<Integer, List<Double>> entry : tiemposPorEjercicio.entrySet()) {
			        int ejercicio = entry.getKey();
			        List<Double> tiempos = entry.getValue();
			        double promedio = tiempos.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
			        promediosTiemposPorEjercicio.put(ejercicio, promedio);
			    }			    						

				
				
				if(respuestas.size() != 0){
					this.promedioIntentosRespuestas = df.format(promedioIntentosRespuestas); // /respuestas.size()); // se rehace a cantidad INTENTOS
					tiempoR.setTimeInMillis((long)tiempoRespuesta/respuestas.size());
					promedioTiempoRespuestas =	Integer.toString(tiempoR.get(Calendar.HOUR_OF_DAY)) + ":" +
												Integer.toString(tiempoR.get(Calendar.MINUTE)) + ":" +
												Integer.toString(tiempoR.get(Calendar.SECOND));
					
				}
				createAnimatedModels();


				
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar las respuestas","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar los resultados","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
    public BarChartModel getAnimatedModel2() {
    	return animatedModel2;
    }
 
    private void createAnimatedModels() {
         
        animatedModel2 = initBarModel();
        //animatedModel2.setTitle("Grafico de Respuestas");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        animatedModel2.setSeriesColors("1484e6,ff7675,e69700,fcde4c");
        
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        Axis xAxis = animatedModel2.getAxis(AxisType.X);
        
        yAxis.setMin(0);
               
        xAxis.setLabel("Ejercicios");
        
    }
     
    private BarChartModel initBarModel() {
       
    	BarChartModel model = new BarChartModel();
        
        int max = 0;
        if(respuestas != null){
	        for(Respuesta r : respuestas)
	        	if(r.getEjercicio() > 0)
	        		max = r.getEjercicio();
        }
        
        ChartSeries acertadas = new ChartSeries();
        acertadas.setLabel("Intento Acertado");
        
        ChartSeries erroneas = new ChartSeries();
        erroneas.setLabel("Intento Erroneo");
        
        ChartSeries cantRes = new ChartSeries();
        cantRes.setLabel("Total de Respuestas");
        
        ChartSeries intentos = new ChartSeries();
        intentos.setLabel("Consultas Ejecutadas");
                
        if(respuestas != null){
	        for(int i = 0 ; i <= max ; i++){
	        	int acert = 0;
	        	int resp = 0;
	        	int intent = 0;
	        	int erro = 0;
	        	for(Respuesta r : respuestas){
	        		if(r.getEjercicio() == i){
	        			resp++;
	        			if(r.getConsultas() != null){
	        				intent = intent + r.getConsultas().size();
	        			}
	        			if(r.isResultado()){
	        				acert++;
	        				
	        			}else {
	        			    erro++;
	        			}
	        		}
	        	}
	        	acertadas.set((i+1),acert);
	        	cantRes.set((i+1),resp);
	        	intentos.set((i+1),intent);
	        	erroneas.set((i+1),erro);
	        }
        }
        
        model.addSeries(acertadas);
        model.addSeries(erroneas);
        model.addSeries(cantRes);
        model.addSeries(intentos);
        
        return model;
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
       
    public boolean isGlobalSelected() {
        return "GLOBAL".equalsIgnoreCase(selectedRut);
    }
  
    public void generarDescarga(){
    	System.out.println("Preparando Descarga");
    	
	    FileWriter f = null;
	    PrintWriter pw = null;
	    
    	List<EstadisticaConsulta> consultas = EstadisticasDAO.selectConsultas();
    	
    	if(consultas != null){    
		    try{
		    	 			    	
		    	f = new FileWriter("C:\\Repositorios\\Segovia\\Consultas.arff");
		    	pw = new PrintWriter(f);  	
		    	
		    	pw.println("@RELATION Consultas");
		    	pw.println("");
		    	pw.println("@ATTRIBUTE id NUMERIC");
		    	pw.println("@ATTRIBUTE rut STRING");
		    	pw.println("@ATTRIBUTE numero NUMERIC");
		    	pw.println("@ATTRIBUTE query STRING");
		    	pw.println("@ATTRIBUTE error STRING");
		    	pw.println("@ATTRIBUTE fecha DATE \"yyyy-MM-dd HH:mm:ss\"");
		    	pw.println("");
		    	pw.println("@DATA");
		    	for(EstadisticaConsulta e : consultas){
		    		pw.println(e.getId() + ",'" + e.getRut() + "'," + e.getNumero() + ",'" + e.getQuery() + "','" + e.getError() + "',\"" + e.getFecha() + "\"");
		    	}
		      	
		    }catch(Exception e){
		      	System.out.println("Error crear archivo de consultas -> " + e.getMessage());
		      	
		      	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear el archivo de estadisticas de consultas","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
		    }finally{
		      	try{
		      		if(f != null)
		      			f.close();
		    	}catch(Exception e2){
		      		System.out.println("Error cerrar archivo de consultas -> " + e2.getMessage());
		      	}
		    }
    	}else{
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar las estadisticas de las consultas","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    	
    	List<EstadisticaRespuesta> respuestas = EstadisticasDAO.selectRespuestas();
    	
    	if(respuestas != null){    
		    try{
		    	 			    	
		    	f = new FileWriter("C:\\Repositorios\\Segovia\\Respuestas.arff");
		    	pw = new PrintWriter(f);  	
		    	
		    	pw.println("@RELATION Respuestas");
		    	pw.println("");
		    	pw.println("@ATTRIBUTE id NUMERIC");
		    	pw.println("@ATTRIBUTE rut STRING");
		    	pw.println("@ATTRIBUTE bd STRING");
		    	pw.println("@ATTRIBUTE fechaBd DATE \"yyyy-MM-dd HH:mm:ss\"");
		    	pw.println("@ATTRIBUTE ejercicio NUMERIC");
		    	pw.println("@ATTRIBUTE resultado {true,false}");
		    	pw.println("@ATTRIBUTE intento NUMERIC");
		    	pw.println("@ATTRIBUTE tiempoEjercicio DATE \"HH:mm:ss\"");
		    	pw.println("@ATTRIBUTE tiempoSesion DATE \"HH:mm:ss\"");
		    	pw.println("@ATTRIBUTE fecha DATE \"yyyy-MM-dd HH:mm:ss\"");
		    	pw.println("");
		    	pw.println("@DATA");
		    	for(EstadisticaRespuesta r : respuestas){
		    		pw.println(r.getId() + ",'" + r.getRut() + "','" + r.getBd() + "',\"" + r.getFechaBd() + "\"," + r.getEjercicio() + "," + r.isResultado() + "," + r.getIntento() + ",\"" + r.getTiempoEjercicio() + "\",\"" + r.getTiempoSesion() + "\",\"" + r.getFecha() + "\"");
		    	}
		      	
		    }catch(Exception e){
		      	System.out.println("Error crear archivo de respuestas -> " + e.getMessage());
		      	
		      	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear el archivo de estadisticas de respuestas","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
		    }finally{
		      	try{
		      		if(f != null)
		      			f.close();
		    	}catch(Exception e2){
		      		System.out.println("Error cerrar archivo de respuestas -> " + e2.getMessage());
		      	}
		    }
    	}else{
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar las estadisticas de las respuestas","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    	
    	List<EstadisticaResultado> resultados = EstadisticasDAO.selectResultados();
    	
    	if(resultados != null){    
		    try{
		    	 			    	
		    	f = new FileWriter("C:\\Repositorios\\Segovia\\Resultados.arff");
		    	pw = new PrintWriter(f);  	
		    	
		    	pw.println("@RELATION Resultados");
		    	pw.println("");
		    	pw.println("@ATTRIBUTE id NUMERIC");
		    	pw.println("@ATTRIBUTE bd STRING");
		    	pw.println("@ATTRIBUTE fechaBd DATE \"yyyy-MM-dd HH:mm:ss\"");
		    	pw.println("@ATTRIBUTE cantEjercicios NUMERIC");
		    	pw.println("@ATTRIBUTE cantCorrectas NUMERIC");
		    	pw.println("@ATTRIBUTE cantErroneas NUMERIC");
		    	pw.println("@ATTRIBUTE cantOmitidas NUMERIC");
		    	pw.println("@ATTRIBUTE fecha DATE \"yyyy-MM-dd HH:mm:ss\"");
		    	pw.println("");
		    	pw.println("@DATA");
		    	for(EstadisticaResultado r : resultados){
		    		pw.println(r.getId() + ",'" + r.getBd() + "',\"" + r.getFechaBd() + "\"," + r.getCantEjercicios() + "," + r.getCantCorrectas() + "," + r.getCantErroneas() + "," + r.getCantOmitidas() + ",\"" + r.getFecha() + "\"");
		    	}
		      	
		    }catch(Exception e){
		      	System.out.println("Error crear archivo de resultados -> " + e.getMessage());
		      	
		      	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear el archivo de estadisticas de resultados","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
		    }finally{
		      	try{
		      		if(f != null)
		      			f.close();
		    	}catch(Exception e2){
		      		System.out.println("Error cerrar archivo de resultados -> " + e2.getMessage());
		      	}
		    }
    	}else{
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar las estadisticas de las resultados","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    	
    	List<EstadisticaTodo> todos = EstadisticasDAO.selectTodo();
    	
    	if(todos != null){    
		    try{
		    	 			    	
		    	f = new FileWriter("C:\\Repositorios\\Segovia\\Todos.arff");
		    	pw = new PrintWriter(f);  	
		    	
		    	pw.println("@RELATION Todo");
		    	pw.println("");
		    	pw.println("@ATTRIBUTE idResultado NUMERIC");
		    	pw.println("@ATTRIBUTE bd STRING");
		    	pw.println("@ATTRIBUTE fechaBd DATE \"yyyy-MM-dd HH:mm:ss\"");		    	
		    	pw.println("@ATTRIBUTE cantEjercicios NUMERIC");
		    	pw.println("@ATTRIBUTE cantCorrectas NUMERIC");
		    	pw.println("@ATTRIBUTE cantErroneas NUMERIC");
		    	pw.println("@ATTRIBUTE cantOmitidas NUMERIC");
		    	pw.println("@ATTRIBUTE fechaResultado DATE \"yyyy-MM-dd HH:mm:ss\"");
		    	pw.println("@ATTRIBUTE idRespuesta NUMERIC");
		    	pw.println("@ATTRIBUTE rutAlumno STRING");
		    	pw.println("@ATTRIBUTE numeroEjercicio NUMERIC");
		    	pw.println("@ATTRIBUTE resultadoEjercicio {true,false}");
		    	pw.println("@ATTRIBUTE intentoEjercicio NUMERIC");
		    	pw.println("@ATTRIBUTE tiempoEjercicio DATE \"HH:mm:ss\"");
		    	pw.println("@ATTRIBUTE tiempoSesion DATE \"HH:mm:ss\"");
		    	pw.println("@ATTRIBUTE fechaEjercicio DATE \"yyyy-MM-dd HH:mm:ss\"");
		    	pw.println("@ATTRIBUTE idConsulta NUMERIC");
		    	pw.println("@ATTRIBUTE numeroConsulta NUMERIC");
		    	pw.println("@ATTRIBUTE query STRING");
		    	pw.println("@ATTRIBUTE errorQuery STRING");
		    	pw.println("@ATTRIBUTE fechaConsulta DATE \"yyyy-MM-dd HH:mm:ss\"");    	
		    	pw.println("");
		    	pw.println("@DATA");
		    	for(EstadisticaTodo t : todos){
		    		pw.println(t.getIdResultado() + ",'" + t.getBd() + "',\"" + t.getFechaBd() + "\"," + 
		    				t.getCantEjercicios() + "," + t.getCantCorrectas() + "," + 
		    				t.getCantErroneas() + "," + t.getCantOmitidas() + ",\"" + 
		    				t.getFechaResultado() + "\"," + t.getIdRespuesta() + ",'" + t.getRut() + "'," + 
		    				t.getEjercicio() + "," + t.isResultadoEjercicio() + "," + 
		    				t.getIntentoEjercicio() + ",\"" + t.getTiempoEjercicio() + "\",\"" + 
		    				t.getTiempoSesion() + "\",\"" + t.getFechaEjercicio() + "\"," + t.getIdConsulta() + "," +
		    				t.getNumeroConsulta() + ",'" + t.getQuery() + "','" + t.getErrorQuery() + "',\"" + 
		    				t.getFechaConsulta() + "\"");
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
    
    public void descargarConsultas() throws IOException{
    	path = "C:\\Repositorios\\Segovia\\Consultas.arff";  
    	contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
    	file = new DefaultStreamedContent(new FileInputStream(path), contentType, "Consultas.arff");
    }
    
    public void descargarResultados() throws IOException{
    	path = "C:\\Repositorios\\Segovia\\Resultados.arff";  
    	contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
    	file = new DefaultStreamedContent(new FileInputStream(path), contentType, "Resultados.arff");
    }
    
    public void descargarRespuestas() throws IOException{
    	path = "C:\\Repositorios\\Segovia\\Respuestas.arff";  
    	contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
    	file = new DefaultStreamedContent(new FileInputStream(path), contentType, "Respuestas.arff");
    }
    
    public void descargarTodo() throws IOException{
    	path = "C:\\Repositorios\\Segovia\\Todos.arff";  
    	contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
    	file = new DefaultStreamedContent(new FileInputStream(path), contentType, "Todos.arff");
    }

	public List<Integer> getEjerciciosYTiempoSegundos() {
		return ejerciciosYTiempoSegundos;
	}

	public void setEjerciciosYTiempoSegundos(List<Integer> ejerciciosYTiempoSegundos) {
		this.ejerciciosYTiempoSegundos = ejerciciosYTiempoSegundos;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public Map<Integer, Double> getPromediosTiemposPorEjercicio() {
		return promediosTiemposPorEjercicio;
	}

	public void setPromediosTiemposPorEjercicio(Map<Integer, Double> promediosTiemposPorEjercicio) {
		this.promediosTiemposPorEjercicio = promediosTiemposPorEjercicio;
	}



    
}