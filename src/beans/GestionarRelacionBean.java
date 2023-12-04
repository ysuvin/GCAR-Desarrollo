package beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
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

import org.primefaces.event.CellEditEvent;

import dao.EjercicioDAO;
import dao.EsquemaDAO;
import dao.RelacionDAO;
import util.Atributo;
import util.AtributoBean;
import util.EsquemaBean;
import util.Relacion;
import util.RelacionBean;
import util.TuplaBean;
import util.UserBean;
 
@ManagedBean(name="gestionarRelacionBean")
@ViewScoped
public class GestionarRelacionBean implements Serializable {
     
	private Relacion relacion = new Relacion();
	private int cant;
	private Atributo selectedAtributo = new Atributo();
    private List<Atributo> atributos = new ArrayList<Atributo>();
    
    private List<RelacionBean> relaciones;
    private List<RelacionBean> filteredRelaciones;
    private RelacionBean selectedRelacion;
    
    private int cantSelected;
    private List<AtributoBean> atributosBean = new ArrayList<AtributoBean>();
    
    private List<String> primarios = new ArrayList<String>();
    
    private String oldName;
    
    public List<String> getPrimarios() {
		return primarios;
	}

	public void setPrimarios(List<String> primarios) {
		this.primarios = primarios;
	}
	
	@PreDestroy
	public void destroy(){
		Util.reloadBd();
		
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
    				System.out.println("Nombre Relaci�n: " + r.getNombre());
    				for(TuplaBean t : r.getTuplasBean()){
    					for(Object o : t.getAtributos()){
    						System.out.print(o + "\t\t\t");
    					}
    					System.out.println();
    				}
    			}
    			for(RelacionBean r : esquemaBean.getRelacionesEjercicios()){
    				System.out.println("Nombre Relaci�n: " + r.getNombre());
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
	
    
    public List<AtributoBean> getAtributosBean() {
		if(atributosBean.size() == 0){
			for(int i = 0 ; i < cantSelected ; i++){
				AtributoBean atributo = new AtributoBean();
				atributosBean.add(atributo);
			}
		}else if(atributosBean.size() < cantSelected){
			for(int i = atributosBean.size() ; i < cantSelected ; i++){
				AtributoBean atributo = new AtributoBean();
				atributo.setLocalId(i);
				atributosBean.add(atributo);
			}
		}else if(atributosBean.size() > cantSelected){
			for(int i = atributosBean.size(); i > cantSelected ; i--){
				atributosBean.remove(i-1);
			}
		}
		for(int i = 0 ; i < atributosBean.size() ; i++){
			AtributoBean atributo = atributosBean.get(i);
			atributo.setLocalId(i);
			atributosBean.set(i,atributo);
		}
        return atributosBean;
    }
    
    public int getCantSelected() {
		return cantSelected;
	}

	public void setCantSelected(int cantSelected) {
		if(cantSelected < 0){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cantidad de atributos no puede ser negativa","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			this.cantSelected = cantSelected;
		}
	}

	public RelacionBean getSelectedRelacion() {
		return selectedRelacion;
	}

	public void setSelectedRelacion(RelacionBean selectedRelacion) {
		this.selectedRelacion = selectedRelacion;
		this.cantSelected = this.selectedRelacion.getAtributos().size();
		this.atributosBean = this.selectedRelacion.getAtributos();
		oldName = selectedRelacion.getNombre();
	}

	public List<RelacionBean> getFilteredRelaciones() {
		return filteredRelaciones;
	}

	public void setFilteredRelaciones(List<RelacionBean> filteredRelaciones) {
		this.filteredRelaciones = filteredRelaciones;
	}

	public List<RelacionBean> getRelaciones() {
    	
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
    	
    	if(esquemaBean != null){
    	
	    	relaciones = esquemaBean.getRelacionesBean();
	    	
//	    	for (RelacionBean r : relaciones){
//	    		//System.out.println("Nombre: " + r.getNombre());
//	    	}
    	}
	    	
		return relaciones;
	}

	public void setRelaciones(List<RelacionBean> relaciones) {
		this.relaciones = relaciones;
	}

	public Relacion getRelacion() {
		return relacion;
	}

	public void setRelacion(Relacion relacion) {
		this.relacion = relacion;
	}

	public Atributo getSelectedAtributo() {
        return selectedAtributo;
    }
 
    public void setSelectedAtributo(Atributo selectedAtributo) {
        this.selectedAtributo = selectedAtributo;
    }
    
    public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		if(cant < 0){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cantidad de atributos no puede ser negativa","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			this.cant = cant;
		}
	}

	public List<Atributo> getAtributos() {
		if(atributos.size() == 0){
			for(int i = 0 ; i < cant ; i++){
				Atributo atributo = new Atributo();
				atributos.add(atributo);
			}
		}else if(atributos.size() < cant){
			for(int i = atributos.size() ; i < cant ; i++){
				Atributo atributo = new Atributo();
				atributo.setLocalId(i);
				atributos.add(atributo);
			}
		}else if(atributos.size() > cant){
			for(int i = atributos.size(); i > cant ; i--){
				atributos.remove(i-1);
			}
		}
		for(int i = 0 ; i < atributos.size() ; i++){
			Atributo atributo = atributos.get(i);
			atributo.setLocalId(i);
			atributos.set(i,atributo);
		}
        return atributos;
    }
	
	public void setAtributo(){
		atributos.set(selectedAtributo.getLocalId(),selectedAtributo);
	}
	
	public String agregarRelacion(){
		
		relacion.setAtributos(atributos);
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		
		if(RelacionDAO.crearRelacion(esquemaBean, relacion)){
//            Util.reloadBd();
			
			elContext = FacesContext.getCurrentInstance().getELContext();
        	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
    			    .getELResolver().getValue(elContext, null, "usuario");
    		
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
	    	System.out.println("=================== Se cre ocon exito la nueva relación ");
	    	FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Se ha agregado la Relaci�n " + relacion.getNombre(),""));
			context.getExternalContext().getFlash().setKeepMessages(true);
			//FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha agregado la Relaci�n " + relacion.getNombre(),"");
            //FacesContext.getCurrentInstance().addMessage(null, msg);
            return "home";
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear la Relación", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "agregarRelacion";
		}
		
	}
	
	public String eliminarRelacion(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
    	
    	if(RelacionDAO.eliminarRelacion(esquemaBean, selectedRelacion)){
    		
//    		Util.reloadBd();
    		
    		elContext = FacesContext.getCurrentInstance().getELContext();
        	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
    			    .getELResolver().getValue(elContext, null, "usuario");
    		
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
	    		
    		
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha eliminado la Relación","");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "eliminarRelacion";
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al eliminar la Relación", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "eliminarRelacion";
		}

	}
	
	public String modificarRelacion(){
		
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
    	
    	boolean band = false;
    	for(AtributoBean a: selectedRelacion.getAtributos()){ // control de errores
    		if(a.getNombre() == null){
    			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El Nombre del Atributo no debe ser vacío","");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                band = true;
                break;
    		}else{
    			Pattern pat1 = Pattern.compile("\\s*");
    			Pattern pat2 = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*");
    			Matcher mat1 = pat1.matcher(a.getNombre());
    			Matcher mat2 = pat2.matcher(a.getNombre());
    			if(mat1.matches()){
    				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El Nombre del Atributo no debe ser vacío","");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    band = true;
                    break;
    			}
    			if(!mat2.matches()){
    				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El Atributo debe comenzar con una letra y solo debe contener caracteres alfanúmericos o el guion bajo","");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    band = true;
                    break;
    			}
    		}
    	}
		if(!band){
			if(RelacionDAO.modificarRelacion(esquemaBean, selectedRelacion, oldName)){
				
//	    		Util.reloadBd();
	    		
	    		elContext = FacesContext.getCurrentInstance().getELContext();
	        	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
	    			    .getELResolver().getValue(elContext, null, "usuario");
	    		
	    		HttpSession session = Util.getSession();
				session.setAttribute("bd",null);
				
				esquemaBean = EsquemaDAO.cargarBDBean(esquemaBean.getNombre());
		    	band = EsquemaDAO.crearEsquemaLoad(userBean.getRut());
		    	
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
		    			}System.out.print("====modrel===== FIN PRIMER FOR BEAN");
		    			for(RelacionBean r : esquemaBean.getRelacionesEjercicios()){
		    				System.out.println("Nombre Relacion: " + r.getNombre());
		    				for(TuplaBean t : r.getTuplasBean()){
		    					for(Object o : t.getAtributos()){
		    						System.out.print(o + "\t\t\t");
		    					}
		    					System.out.println();
		    				}
		    			}  System.out.print("====modrel===== FIN SEGUNDO FOR BEAN");  	    	
		    					    	
				    	session.setAttribute("nombreBd",esquemaBean.getNombre());
				    	session.setAttribute("bd",esquemaBean);
				    	
		    		}
		    		
		    	}
		    					
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha modificado la Relación " + selectedRelacion.getNombre(),"");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al modificar la Relación", "");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		return "modificarRelacion";
		
	}
	
}