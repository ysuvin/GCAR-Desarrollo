package util;

import java.io.Serializable;

public class EstadisticaTodo implements Serializable {

	private int idResultado;
	private String bd;
	private String fechaBd;
	private int cantEjercicios;
	private int cantCorrectas;
	private int cantErroneas;
	private int cantOmitidas;
	private String fechaResultado;
	private int idRespuesta;
	private String rut;
	private int ejercicio;
	private boolean resultadoEjercicio;
	private int intentoEjercicio;
	private String tiempoEjercicio;
	private String tiempoSesion;
	private String fechaEjercicio;
	private int idConsulta;
	private int numeroConsulta;
	private String query;
	private String errorQuery;
	private String fechaConsulta;
	
	
	public EstadisticaTodo() {
		super();
	}
	public int getIdResultado() {
		return idResultado;
	}
	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}
	public String getBd() {
		return bd;
	}
	public void setBd(String bd) {
		this.bd = bd;
	}
	public String getFechaBd() {
		return fechaBd;
	}
	public void setFechaBd(String fechaBd) {
		this.fechaBd = fechaBd;
	}
	public int getCantEjercicios() {
		return cantEjercicios;
	}
	public void setCantEjercicios(int cantEjercicios) {
		this.cantEjercicios = cantEjercicios;
	}
	public int getCantCorrectas() {
		return cantCorrectas;
	}
	public void setCantCorrectas(int cantCorrectas) {
		this.cantCorrectas = cantCorrectas;
	}
	public int getCantErroneas() {
		return cantErroneas;
	}
	public void setCantErroneas(int cantErroneas) {
		this.cantErroneas = cantErroneas;
	}
	public int getCantOmitidas() {
		return cantOmitidas;
	}
	public void setCantOmitidas(int cantOmitidas) {
		this.cantOmitidas = cantOmitidas;
	}
	public String getFechaResultado() {
		return fechaResultado;
	}
	public void setFechaResultado(String fechaResultado) {
		this.fechaResultado = fechaResultado;
	}
	public int getIdRespuesta() {
		return idRespuesta;
	}
	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public int getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}
	public boolean isResultadoEjercicio() {
		return resultadoEjercicio;
	}
	public void setResultadoEjercicio(boolean resultadoEjercicio) {
		this.resultadoEjercicio = resultadoEjercicio;
	}
	public int getIntentoEjercicio() {
		return intentoEjercicio;
	}
	public void setIntentoEjercicio(int intentoEjercicio) {
		this.intentoEjercicio = intentoEjercicio;
	}
	public String getTiempoEjercicio() {
		return tiempoEjercicio;
	}
	public void setTiempoEjercicio(String tiempoEjercicio) {
		this.tiempoEjercicio = tiempoEjercicio;
	}
	public String getTiempoSesion() {
		return tiempoSesion;
	}
	public void setTiempoSesion(String tiempoSesion) {
		this.tiempoSesion = tiempoSesion;
	}
	public String getFechaEjercicio() {
		return fechaEjercicio;
	}
	public void setFechaEjercicio(String fechaEjercicio) {
		this.fechaEjercicio = fechaEjercicio;
	}
	public int getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}
	public int getNumeroConsulta() {
		return numeroConsulta;
	}
	public void setNumeroConsulta(int numeroConsulta) {
		this.numeroConsulta = numeroConsulta;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getErrorQuery() {
		return errorQuery;
	}
	public void setErrorQuery(String errorQuery) {
		this.errorQuery = errorQuery;
	}
	public String getFechaConsulta() {
		return fechaConsulta;
	}
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	
	
	
	
}