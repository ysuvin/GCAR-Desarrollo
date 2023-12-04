package util;

import java.io.Serializable;
import java.util.List;

public class Analisis implements Serializable{

	private String tecnicaMineria;
	private String dataset;
	private String parametrosAnalisis;
	
	public Analisis() {
		
	}
	
	
	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}



	public String getTecnicaMineria() {
		return tecnicaMineria;
	}

	public void setTecnicaMineria(String tecnicaMineria) {
		this.tecnicaMineria = tecnicaMineria;
	}


	public String getParametrosAnalisis() {
		return parametrosAnalisis;
	}


	public void setParametrosAnalisis(String parametrosAnalisis) {
		this.parametrosAnalisis = parametrosAnalisis;
	}
	
	
}
