package util;

import java.io.Serializable;

public class Atributo implements Serializable {
	
	private String nombre;
	private String esPrimario;
	private String tipo;
	private int localId;
	
	public Atributo(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEsPrimario() {
		return esPrimario;
	}

	public void setEsPrimario(String esPrimario) {
		this.esPrimario = esPrimario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}
	
}
