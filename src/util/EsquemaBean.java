package util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
	
@ManagedBean(name = "bdBean")
@SessionScoped

	public class EsquemaBean implements Serializable {
		
		private String rut;
		private String nombre;
		private Timestamp fecha;
		private boolean visible;
		private List<RelacionBean> relacionesBean;
		private List<RelacionBean> relacionesAuxiliares;
		private List<RelacionBean> relacionesEjercicios;
		
		public EsquemaBean(){
			
		}
		
		public RelacionBean obtenerRelacion(String nombre){
			if(relacionesBean != null){
				for(RelacionBean r : relacionesBean){
					if(r.getNombre().equals(nombre))
						return r;
				}
			}
			if(relacionesAuxiliares != null){
				for(RelacionBean r : relacionesAuxiliares){
					if(r.getNombre().equals(nombre))
						return r;
				}
			}
			if(relacionesEjercicios != null){
				for(RelacionBean r : relacionesEjercicios){
					if(r.getNombre().equals(nombre))
						return r;
				}
			}
			return null;
		}
		
		public RelacionBean getRelacion(String nombre){
			for(RelacionBean r : relacionesBean){
				if(r.getNombre().equals(nombre))
					return r;
			}
			return null;
		}
		
		public RelacionBean getRelacionEjercicio(String nombre){
			for(RelacionBean r : relacionesEjercicios){
				if(r.getNombre().equals(nombre))
					return r;
			}
			return null;
		}
		
		public RelacionBean getRelacionAuxiliar(String nombre){
			if(relacionesAuxiliares != null){
				for(RelacionBean r : relacionesAuxiliares){
					if(r.getNombre().equals(nombre))
						return r;
				}
			}
			return null;
		}
		
		public boolean isRelacion(String nombre){
			for(RelacionBean r : relacionesBean){
				if(r.getNombre().equals(nombre))
					return true;
			}
			return false;
		}
		
		public boolean isRelacionAux(String nombre){
			if(relacionesAuxiliares != null){
				for(RelacionBean r : relacionesAuxiliares){
					if(r.getNombre().equals(nombre))
						return true;
				}
			}
			return false;
		}
		
		public void reemplazarRelacion(RelacionBean in){
			for(RelacionBean r : relacionesAuxiliares){
				if(r.getNombre().equals(in.getNombre())){
					r.setNombre(in.getNombre());
					r.setAtributos(in.getAtributos());
					r.setTuplas(in.getTuplasBean());
					break;
				}		
			}
		}

		public Timestamp getFecha() {
			return fecha;
		}


		public void setFecha(Timestamp fecha) {
			this.fecha = fecha;
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


		public List<RelacionBean> getRelacionesBean() {
			return relacionesBean;
		}


		public void setRelaciones(List<RelacionBean> relacionesBean) {
			this.relacionesBean = relacionesBean;
		}
		
		public List<RelacionBean> getRelacionesEjercicios() {
			return relacionesEjercicios;
		}


		public void setRelacionesEjercicios(List<RelacionBean> relacionesEjercicios) {
			this.relacionesEjercicios = relacionesEjercicios;
		}
		
		public List<RelacionBean> getRelacionesAuxiliares() {
			return relacionesAuxiliares;
		}


		public void setRelacionesAuxiliares(List<RelacionBean> relacionesAuxiliares) {
			this.relacionesAuxiliares= relacionesAuxiliares;
		}
		
		public List<AtributoBean> getAtributosParaRelacion(String nombreRelacion) {

		    List<AtributoBean> atributos = new ArrayList<>();
		    RelacionBean relacion = getRelacion(nombreRelacion);
		    if (relacion != null) {
		        atributos = relacion.getAtributos();
		    }
		    
		    return atributos;
		}
		
		
	}

