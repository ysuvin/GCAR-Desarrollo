package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import util.Esquema;
import util.EsquemaBean;
import util.RelacionBean;
import util.TuplaBean;
import util.UserBean;
import dao.EsquemaDAO;
import dao.RelacionDAO;
import dao.UserDAO;
 
@ManagedBean(name="gestionarEsquemasBean")
@ViewScoped
public class GestionarEsquemasBean implements Serializable {
     
    private List<Esquema> bds;
    private Esquema selectedBd;
    private Esquema oldBd;
    private List<Esquema> filteredBds;
    
    private Esquema nuevo = new Esquema();
    private String search = "";
    
   
    
    public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Esquema getOldBd() {
		return oldBd;
	}

	public void setOldBd(Esquema oldBd) {
		this.oldBd = oldBd;
	}

	public Esquema getNuevo() {
		return nuevo;
	}

	public void setNuevo(Esquema nuevo) {
		this.nuevo.setVisible(true);
		this.nuevo = nuevo;
	}
	
	@PostConstruct
	public void init(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		if(userBean.getTipo() == 1){
			bds = EsquemaDAO.selectAllBds();
		}else{
			if(FacesContext.getCurrentInstance().getViewRoot().getViewId().equals("/eliminarBD.xhtml")){
				bds = EsquemaDAO.selectMisBds(userBean.getRut());
			}else if(FacesContext.getCurrentInstance().getViewRoot().getViewId().equals("/modificarBD.xhtml")){
				bds = EsquemaDAO.selectMisBds(userBean.getRut());
			}else{
				bds = EsquemaDAO.selectAllBds(userBean.getRut());
			}
		}
	}

	public List<Esquema> getBds() {
//		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
//			    .getELResolver().getValue(elContext, null, "usuario");
//		if(userBean.getTipo() == 1){
//			bds = EsquemaDAO.selectAllBds();
//		}else{
//			if(FacesContext.getCurrentInstance().getViewRoot().getViewId().equals("/eliminarBD.xhtml")){
//				bds = EsquemaDAO.selectMisBds(userBean.getRut());
//			}else if(FacesContext.getCurrentInstance().getViewRoot().getViewId().equals("/modificarBD.xhtml")){
//				bds = EsquemaDAO.selectMisBds(userBean.getRut());
//			}else{
//				bds = EsquemaDAO.selectAllBds(userBean.getRut());
//			}
//		}
		return bds;
    }
     
    public Esquema getSelectedBd() {
        return selectedBd;
    }
 
    public void setSelectedBd(Esquema selectedBd) {
        this.selectedBd = selectedBd;
    }
    
    public List<Esquema> getFilteredBds() {
        return filteredBds;
    }
 
    public void setFilteredBds(List<Esquema> filteredBds) {
        this.filteredBds = filteredBds;
    }
    
    public String cargar(){
    	
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		
		EsquemaBean bdBean = EsquemaDAO.cargarBDBean(selectedBd.getNombre());
    	boolean band = EsquemaDAO.crearEsquemaLoad(userBean.getRut());
    	
    	if(band && bdBean != null){

//    		List<RelacionBean> relacionesBean = RelacionDAO.cargarRelacionesBean(bdBean, userBean.getRut());
    		bdBean.setRelacionesAuxiliares(null);
    		bdBean = RelacionDAO.cargarRelaciones(bdBean, userBean.getRut());
    		
    		if(bdBean != null){
    			
//    			bdBean.setRelaciones(relacionesBean);
    			
    			for(RelacionBean r : bdBean.getRelacionesBean()){
    				System.out.println("Nombre Relacion: " + r.getNombre());
    				for(TuplaBean t : r.getTuplasBean()){
    					for(Object o : t.getAtributos()){
    						System.out.print(o + "\t\t\t");
    					}
    					System.out.println();
    				}
    			}
    			
    			for(RelacionBean r : bdBean.getRelacionesEjercicios()){
    				System.out.println("Nombre Relacion: " + r.getNombre());
    				for(TuplaBean t : r.getTuplasBean()){
    					for(Object o : t.getAtributos()){
    						System.out.print(o + "\t\t\t");
    					}
    					System.out.println();
    				}
    			}
    			
		    	HttpSession session = Util.getSession();
		    	
		    	session.setAttribute("nombreBd",bdBean.getNombre());
		    	session.setAttribute("bd",bdBean);
		    	
		    	FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Se ha cargado la Base de Datos " + selectedBd.getNombre(),""));
				context.getExternalContext().getFlash().setKeepMessages(true);
				
				return "home";
    		}else{
    			
    			FacesContext  context = FacesContext.getCurrentInstance();
    			context.addMessage(
    					null,
    					new FacesMessage(FacesMessage.SEVERITY_ERROR,
    							"Error al cargar las relaciones de la base de datos " + selectedBd.getNombre(),""));
    			context.getExternalContext().getFlash().setKeepMessages(true);
    			
    			return "home";
    			
    		}
			
    	}else{
    		
    		FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al cargar la base de datos " + selectedBd.getNombre(),""));
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			return "home";
    		
    	}

    		
    }
    
    public String crear(){
    	System.out.println("Creando Esquema");
    	
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		
		if(userBean.getTipo() != 1){
			nuevo.setVisible(true);
		}
		
		if(EsquemaDAO.crearNuevoEsquema(nuevo,userBean.getRut())){
		
			FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Se ha creado la Base de Datos " + nuevo.getNombre(),""));
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			return "home";
			
		}else{
			
			FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear la base de datos " + nuevo.getNombre(),""));
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			return "home";
			
		}
    	
    }
    
    public void borrar(){
    	
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		EsquemaBean bdBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		
		if(bdBean == null){
			bdBean = new EsquemaBean();
			bdBean.setNombre("");
		}

		if(!bdBean.getNombre().toLowerCase().equals(selectedBd.getNombre().toLowerCase())){
	    	if(EsquemaDAO.borrarEsquema(selectedBd)){
	    		search = "";
	        	for(int i = 0 ; i < bds.size() ; i++){
	        		if(bds.get(i).getRut().equals(selectedBd.getRut()) && bds.get(i).getNombre().equals(selectedBd.getNombre())){
	        			bds.remove(bds.get(i));
	        		}
	        	}  	
	        	filteredBds = bds;
	    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha eliminado la base de datos " + selectedBd.getNombre(),"");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	       	}else{
	       		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al eliminar la base de datos","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes cerrar esta base de datos para eliminarla","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    		
    }
    
    public void modificar(){
    	
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		
		if(esquemaBean != null && esquemaBean.getNombre().equals(selectedBd.getAntiguoNombre())){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se puede modificar una Base de Datos Cargada","");
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
	    	
	    	if(EsquemaDAO.modificarEsquema(selectedBd)){
	    		search = ""; 	
	        	filteredBds = bds;
	    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha modificado la base de datos " + selectedBd.getNombre(),"");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	       	}else{
	       		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar la base de datos","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
		}
    }
    
    public void onRowSelect(SelectEvent event){
    	selectedBd = (Esquema) event.getObject();
    }
}