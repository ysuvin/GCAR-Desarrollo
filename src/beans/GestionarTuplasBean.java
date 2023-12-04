package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.ConsultaDAO;
import dao.EsquemaDAO;
import dao.RelacionDAO;
import util.AtributoBean;
import util.EsquemaBean;
import util.RelacionBean;
import util.TuplaBean;
import util.UserBean;
 
@ManagedBean(name="gestionarTuplasBean")
@ViewScoped
public class GestionarTuplasBean implements Serializable {
	
	private List<RelacionBean> relaciones = new ArrayList<RelacionBean>();
	private List<RelacionBean> filteredRelaciones;
	private RelacionBean selectedRelacion;
	private String nombreRelacion;	
	private TuplaBean selectedTupla;
	private TuplaBean oldTupla;

	@PostConstruct
    public void init(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
    	
    	if(esquemaBean == null){
    		try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
			} catch (Exception e) {
				System.out.println("No pude redireccionar");
			}
    	}
    }
	
	@PreDestroy
	public void destroy(){
//		Util.reloadBd();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		
		HttpSession session = Util.getSession();
		session.setAttribute("bd",null);
		
		esquemaBean = EsquemaDAO.cargarBDBean(esquemaBean.getNombre());
    	boolean band = EsquemaDAO.crearEsquemaLoad(userBean.getRut());
    	
    	if(band && esquemaBean != null){

    		esquemaBean.setRelacionesAuxiliares(null);
    		esquemaBean = RelacionDAO.cargarRelaciones(esquemaBean, userBean.getRut());
    		
    		if(esquemaBean != null){
    			
    			for(RelacionBean r : esquemaBean.getRelacionesBean()){
    				System.out.println("Nombre Relacion: " + r.getNombre());
    				for(TuplaBean t : r.getTuplasBean()){
    					for(Object o : t.getAtributos()){
    						System.out.print(o + "\t\t\t");
    					}
    					System.out.println();
    				}
    			}
    			for(RelacionBean r : esquemaBean.getRelacionesEjercicios()){
    				System.out.println("Nombre Relacion: " + r.getNombre());
    				for(TuplaBean t : r.getTuplasBean()){
    					for(Object o : t.getAtributos()){
    						System.out.print(o + "\t\t\t");
    					}
    					System.out.println();
    				}
    			}    	    	
    					    	
		    	session.setAttribute("nombreBd",esquemaBean.getNombre());
		    	session.setAttribute("bd",esquemaBean);
    		}
    		
    	}   			
	}
	
	public String getNombreRelacion() {
		return nombreRelacion;
	}

	public void setNombreRelacion(String nombreRelacion) {
		this.nombreRelacion = nombreRelacion;
	}

	public TuplaBean getSelectedTupla() {
		return selectedTupla;
	}

	public void setSelectedTupla(TuplaBean selectedTupla) {
		this.selectedTupla = selectedTupla;
		if(selectedTupla != null){
			for(Object o : selectedTupla.getAtributos()){
				System.out.print(o + "\t");
			}
			System.out.println();
		}
	}

	public List<RelacionBean> getRelaciones() {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
    	
    	if(esquemaBean != null){
    	
    		relaciones = esquemaBean.getRelacionesBean();
    	
    	}
		return relaciones;
	}
	
	public void setRelaciones(List<RelacionBean> relaciones) {
		this.relaciones = relaciones;
	}
	
	public List<RelacionBean> getFilteredRelaciones() {
		return filteredRelaciones;
	}
	
	public void setFilteredRelaciones(List<RelacionBean> filteredRelaciones) {
		this.filteredRelaciones = filteredRelaciones;
	}
	
	public RelacionBean getSelectedRelacion() {
		return selectedRelacion;
	}
	
	public void setSelectedRelacion(RelacionBean selectedRelacion) {
		if(selectedRelacion != null){
			System.out.println(selectedRelacion.getNombre());
			nombreRelacion = selectedRelacion.getNombre();
			
			List<AtributoBean> atributos = selectedRelacion.getAtributos();
			for(AtributoBean a : atributos){
				if(a.getEsPrimario().equals("Sí")){
					if(!a.getNombre().contains("(PK)")){
						a.setNombre(a.getNombre().toUpperCase() + " (PK)");
					}
				}
			}
			
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "usuario");
			
			selectedRelacion = ConsultaDAO.verRelacion(selectedRelacion.getNombre(), userBean.getRut());
			selectedRelacion.setAtributos(atributos);
			this.selectedRelacion = selectedRelacion;
		}else{
			FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar una Relación",""));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}
	
	public void agregarTupla(){
		System.out.println("Agregar Tupla");
		selectedTupla = null;
		List<TuplaBean> tuplas = selectedRelacion.getTuplasBean();
		if(tuplas == null){
			tuplas = new ArrayList<TuplaBean>();
		}
		TuplaBean tupla = new TuplaBean();
		tupla.setAtributos(new Object[selectedRelacion.getAtributos().size()]);	
		tuplas.add(tupla);
		selectedRelacion.setTuplas(tuplas);
	}
	
	public void eliminarTupla(){
		System.out.println("Eliminar Tupla");
		if(selectedTupla != null){
			List<TuplaBean> tuplas = selectedRelacion.getTuplasBean();
			tuplas.remove(selectedTupla);
			selectedRelacion.setTuplas(tuplas);
			
			selectedTupla = null;
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar una Tupla para poder eliminarla", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void aceptar(){
		System.out.println("Aceptar");
		boolean band = false;
		for(TuplaBean t : selectedRelacion.getTuplasBean()){
			band = false;
			for(int i = 0 ; i < t.getAtributos().length ; i++){
				if(t.getAtributos()[i] == null){
					Object[] valores = t.getAtributos();
					valores[i] = "";
					t.setAtributos(valores);
				}
				Pattern pat = Pattern.compile("\\s*");
				Matcher mat = pat.matcher(t.getAtributos()[i].toString());
				if(!mat.matches()){
					if(selectedRelacion.getAtributos().get(i).getTipo().equals("Entero")){
						try{
							Integer.parseInt(t.getAtributos()[i].toString());
						}catch(Exception e){
							FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El valor " + t.getAtributos()[i] + " del Atributo " + selectedRelacion.getAtributos().get(i).getNombre() + " no es un Entero", "");
				            FacesContext.getCurrentInstance().addMessage(null, msg);
				            band = true;
						}
					}else if(selectedRelacion.getAtributos().get(i).getTipo().equals("Real")){
						try{
							Float.parseFloat(t.getAtributos()[i].toString());
						}catch(Exception e){
							FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El valor " + t.getAtributos()[i] + " del Atributo " + selectedRelacion.getAtributos().get(i).getNombre() + " no es un Real", "");
				            FacesContext.getCurrentInstance().addMessage(null, msg);
				            band = true;
						}
					}
				}else{
					Object[] valores = t.getAtributos();
					valores[i] = null;
					t.setAtributos(valores);
				}
				if(band){
					break;
				}
			}
			if(band){
				break;
			}
		}
		if(!band){
			
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			
			String res;
			
			if(selectedRelacion.getTuplasBean().isEmpty()){
				res = RelacionDAO.guardarTuplasVacias(bd, selectedRelacion, nombreRelacion);
			}else{
				res = RelacionDAO.guardarTuplas(bd, selectedRelacion, nombreRelacion);
			}
								
			if(res.equals("ok")){
				
				Util.reloadBd();
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se han guardado los cambios de la Relación " + nombreRelacion, "");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
			}else if(res.equals("fatal")){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al guardar los cambios en la Relación " + nombreRelacion + ". Puede que se hayan perdidos algunos datos de la Relación", "");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al guardar los cambios en la Relación " + nombreRelacion + ". " + res, "");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al guardar los cambios en la Relación " + nombreRelacion, "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		selectedTupla = null;
	}
	
	public String cancelar(){
		System.out.println("Cancelar");
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se han cancelado los cambios en la Relación " + nombreRelacion, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "verRelacion";
	}  
	
	public void ventanaModificarTupla(){
		System.out.println("Modificar");
		if(selectedTupla == null){
			FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar una Tupla para poder modificarla",""));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}else{
			oldTupla = selectedTupla;
		}
	}
	
	public void modificarTupla(){
		System.out.println("Aceptar Modificación");
		selectedRelacion.getTuplasBean().set(selectedRelacion.getTuplasBean().indexOf(oldTupla),selectedTupla);
		selectedTupla = null;
	}
	
}