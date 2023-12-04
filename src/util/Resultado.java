package util;

import java.io.Serializable;
import java.sql.Timestamp;

public class Resultado implements Serializable {
	
	private String rut;
	private String bd;
	private Timestamp fechaBd;
	private int cantEjercicios;
	private int cantCorrectas;
	private int cantErroneas;
	private int cantOmitidas;

	public Resultado() {

	}
	
	

	public Timestamp getFechaBd() {
		return fechaBd;
	}



	public void setFechaBd(Timestamp fechaBd) {
		this.fechaBd = fechaBd;
	}



	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
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



	public String getRut() {
		return rut;
	}



	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
}
