package util;

import java.io.Serializable;

public class Ejercicio implements Serializable{
	
	private int id;
	private String pregunta;
	private String consultas;
	private String respuesta;
	private String respuestaAlumno;
	private boolean resultado;
	
	private String query;
	private String queryList;
	
	public Ejercicio(){
		
	}
	
	public String getQueryList() {
		return queryList;
	}

	public void setQueryList(String queryList) {
		this.queryList = queryList;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getRespuestaAlumno() {
		return respuestaAlumno;
	}

	public void setRespuestaAlumno(String respuestaAlumno) {
		this.respuestaAlumno = respuestaAlumno;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getConsultas() {
		return consultas;
	}

	public void setConsultas(String consultas) {
		this.consultas = consultas;
	}

	
	
}
