package util;

import java.io.Serializable;

public class EstadisticaConsulta implements Serializable {
	
	private int id;
	private String rut;
	private int numero;
	private String query;
	private String error;
	private String fecha;
	
	
	public EstadisticaConsulta(int id, String rut, int numero, String query,
			String error, String fecha) {
		super();
		this.id = id;
		this.rut = rut;
		this.numero = numero;
		this.query = query;
		this.error = error;
		this.fecha = fecha;
	}
	
	public EstadisticaConsulta(){
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}


