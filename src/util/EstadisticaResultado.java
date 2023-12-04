package util;

import java.io.Serializable;

public class EstadisticaResultado implements Serializable {

	private int id;
	private String rut;
	private String bd;
	private String fechaBd;
	private int cantEjercicios;
	private int cantCorrectas;
	private int cantErroneas;
	private int cantOmitidas;
	private String fecha;
	
	public EstadisticaResultado() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
	
}