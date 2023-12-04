package util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tuplaBean")
@SessionScoped
public class TuplaBean implements Serializable {
	
	Object[] atributos;
	
	public TuplaBean(){
		
	}
	
	public TuplaBean(Object[] atributos) {
		super();
		this.atributos = atributos;
	}
	
	public Object[] getAtributos() {
		return atributos;
	}

	public void setAtributos(Object[] atributos) {
		this.atributos = atributos;
	}
	
}
