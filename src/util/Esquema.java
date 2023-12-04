package util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Esquema implements Serializable{
	
	private String rut;
	private String nombre;
	private Timestamp fecha;
	private boolean visible;
	private List<Relacion> relaciones;
	
	private String antiguoNombre;
	
	public Esquema(){
		
	}
	
	public Relacion getRelacion(String nombre){
		for(Relacion r : relaciones){
			if(r.getNombre().equals(nombre))
				return r;
		}
		return null;
	}
	

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public List<Relacion> getRelaciones() {
		return relaciones;
	}


	public void setRelaciones(List<Relacion> relaciones) {
		this.relaciones = relaciones;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getAntiguoNombre() {
		return antiguoNombre;
	}

	public void setAntiguoNombre(String antiguoNombre) {
		this.antiguoNombre = antiguoNombre;
	}

	
}
