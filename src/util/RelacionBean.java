package util;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "relacionBean")
@SessionScoped

public class RelacionBean implements Serializable {
	
	private String nombre;
	private List<AtributoBean> atributos;
	private List<TuplaBean> tuplas;
	
	public RelacionBean(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AtributoBean> getAtributos() {
		return atributos;
	}
	
	public List<AtributoBean> getAtributosAr() {
	    if (atributos == null) {
	        atributos = new ArrayList<>();
	    }
	    return atributos;
	}

	public void setAtributos(List<AtributoBean> atributos) {
		this.atributos = atributos;
	}

	public List<TuplaBean> getTuplasBean() {
		return tuplas;
	}

	public void setTuplas(List<TuplaBean> tuplas) {
		this.tuplas = tuplas;
	}
	
}
