package util;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Relacion implements Serializable {
	
	private String nombre;
	private List<Atributo> atributos;
	private List<Tupla> tuplas;
	
	public Relacion(){
		
	}	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Atributo> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}
	public List<Tupla> getTuplas() {
		return tuplas;
	}

	public void setTuplas(List<Tupla> tuplas) {
		this.tuplas = tuplas;
	}
	
	
}
