package util;
import java.io.Serializable;

public class EstadisticasEjecutorAR implements Serializable {
	
	private int id;
	private String rut;
	private String bd;
	private String fechaBd;
	private String query;
	private String operador;
	private boolean query_correcta;
	private boolean query_incorrecta;
	private String clasificacion_error;
	private String descripcion_error;
	private String fecha;

	
	public EstadisticasEjecutorAR () {
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


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getOperador() {
		return operador;
	}


	public void setOperador(String operador) {
		this.operador = operador;
	}


	public boolean isQuery_correcta() {
		return query_correcta;
	}


	public void setQuery_correcta(boolean query_correcta) {
		this.query_correcta = query_correcta;
	}


	public boolean isQuery_incorrecta() {
		return query_incorrecta;
	}


	public void setQuery_incorrecta(boolean query_incorrecta) {
		this.query_incorrecta = query_incorrecta;
	}


	public String getDescripcion_error() {
		return descripcion_error;
	}


	public void setDescripcion_error(String descripcion_error) {
		this.descripcion_error = descripcion_error;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getClasificacion_error() {
		return clasificacion_error;
	}


	public void setClasificacion_error(String clasificacion_error) {
		this.clasificacion_error = clasificacion_error;
	}
	
}