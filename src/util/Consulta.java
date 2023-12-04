package util;

import java.io.Serializable;

public class Consulta implements Serializable{
	
	private int numero;
	private String query;
	private String error;
	
	public Consulta(){
		
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
		
}
