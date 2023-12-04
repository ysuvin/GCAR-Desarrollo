package util;

import java.io.Serializable;

public class EstadisticaRespuesta implements Serializable {
	
	private int id;
	private String rut;
	private String bd;
	private String fechaBd;
	private int ejercicio;
	private boolean resultado;
	private int intento;
	private String tiempoEjercicio;
	private String tiempoSesion;
	private String fecha;
	
	
	public EstadisticaRespuesta() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
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
	public int getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}
	public boolean isResultado() {
		return resultado;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	public int getIntento() {
		return intento;
	}
	public void setIntento(int intento) {
		this.intento = intento;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
}