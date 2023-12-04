package util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class Respuesta implements Serializable{
	
	private String rut;
	private String bd;
	private Timestamp fechaBd;
	private int ejercicio;
	private boolean resultado;
	private int intento;
	private Calendar tiempoEjercicio;
	private Calendar tiempoSesion;
	private List<Consulta> consultas;
	
	public Respuesta(){
	
	}

	public Timestamp getFechaBd() {
		return fechaBd;
	}



	public void setFechaBd(Timestamp fechaBd) {
		this.fechaBd = fechaBd;
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

	public Calendar getTiempoEjercicio() {
		return tiempoEjercicio;
	}

	public void setTiempoEjercicio(Calendar tiempoInicio) {
		this.tiempoEjercicio = tiempoInicio;
	}

	public Calendar getTiempoSesion() {
		return tiempoSesion;
	}

	public void setTiempoSesion(Calendar tiempoSesion) {
		this.tiempoSesion = tiempoSesion;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	
	
}
