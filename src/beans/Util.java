package beans;
 
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.EsquemaBean;
import util.RelacionBean;
import util.TuplaBean;
import util.UserBean;
import dao.EsquemaDAO;
import dao.RelacionDAO;
 
 
public class Util {

//public static boolean reloadBdEjercicios(){
//    	
//    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
//			    .getELResolver().getValue(elContext, null, "usuario");
//		
//		EsquemaBean esquemaActual = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
//			    .getELResolver().getValue(elContext, null, "bd");
//    	
//		HttpSession session = Util.getSession();
//		session.setAttribute("bd",null);
//		
//		EsquemaBean bdBean = EsquemaDAO.cargarBDBean(esquemaActual.getNombre());
//    	boolean band = EsquemaDAO.crearEsquemaLoad(userBean.getRut());
//    	
//    	if(band && bdBean != null){
//
//    		List<RelacionBean> relacionesBean = RelacionDAO.cargarRelacionesBean(esquemaActual, userBean.getRut());
//    		List<RelacionBean> relacionesEjercicios = RelacionDAO.cargarRelacionesEjerciciosBean(esquemaActual, userBean.getRut());
//    		
//    		if(relacionesEjercicios != null){
//    			
//    			
//    			bdBean.setRelaciones(relacionesBean);
//    			bdBean.setRelacionesEjercicios(relacionesEjercicios);
//    			
//    			for(RelacionBean r : relacionesEjercicios){
//    				System.out.println("Nombre Relacion: " + r.getNombre());
//    				for(TuplaBean t : r.getTuplasBean()){
//    					for(Object o : t.getAtributos()){
//    						System.out.print(o + "\t\t\t");
//    					}
//    					System.out.println();
//    				}
//    			}
//    					    	
//		    	session.setAttribute("nombreBd",bdBean.getNombre());
//		    	session.setAttribute("bd",bdBean);
//		    	
////		    	FacesContext context = FacesContext.getCurrentInstance();
////		    	
////		    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Base de Datos recargada","");
////           		FacesContext.getCurrentInstance().addMessage(null, msg);
////           		context.getExternalContext().getFlash().setKeepMessages(true);
//           		return true;
//
//    		}else{
////    			FacesContext context = FacesContext.getCurrentInstance();
////    			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al recargar las relaciones de la base de datos " + esquemaActual.getNombre(),"");
////           		FacesContext.getCurrentInstance().addMessage(null, msg);
////           		context.getExternalContext().getFlash().setKeepMessages(true);
//           		return false;
//    		}
//			
//    	}else{
////    		FacesContext context = FacesContext.getCurrentInstance();
////			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al recargar las relaciones de la base de datos " + esquemaActual.getNombre(),"");
////       		FacesContext.getCurrentInstance().addMessage(null, msg);
////       		context.getExternalContext().getFlash().setKeepMessages(true);
//       		return false;
//    	}    		
//   }
//	
	public static boolean reloadBd(){
    	
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		
		EsquemaBean esquemaActual = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
    	
		HttpSession session = Util.getSession();
		session.setAttribute("bd",null);
		
		EsquemaBean bdBean = EsquemaDAO.cargarBDBean(esquemaActual.getNombre());
    	boolean band = EsquemaDAO.crearEsquemaLoad(userBean.getRut());
    	
    	if(band && bdBean != null){

    		bdBean.setRelacionesAuxiliares(null);
    		bdBean = RelacionDAO.cargarRelaciones(esquemaActual, userBean.getRut());
    		
    		if(bdBean != null){
    			
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
    					    	
		    	session.setAttribute("nombreBd",bdBean.getNombre());
		    	session.setAttribute("bd",bdBean);
		    	
//		    	FacesContext context = FacesContext.getCurrentInstance();
//		    	
//		    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Base de Datos recargada","");
//           		FacesContext.getCurrentInstance().addMessage(null, msg);
//           		context.getExternalContext().getFlash().setKeepMessages(true);
           		return true;

    		}else{
//    			FacesContext context = FacesContext.getCurrentInstance();
//    			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al recargar las relaciones de la base de datos " + esquemaActual.getNombre(),"");
//           		FacesContext.getCurrentInstance().addMessage(null, msg);
//           		context.getExternalContext().getFlash().setKeepMessages(true);
           		return false;
    		}
			
    	}else{
//    		FacesContext context = FacesContext.getCurrentInstance();
//			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al recargar las relaciones de la base de datos " + esquemaActual.getNombre(),"");
//       		FacesContext.getCurrentInstance().addMessage(null, msg);
//       		context.getExternalContext().getFlash().setKeepMessages(true);
       		return false;
    	}    		
   }
	
	public static HttpSession getSession() {
		return (HttpSession)
				FacesContext.
				getCurrentInstance().
				getExternalContext().
				getSession(false);
	}
       
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.
				getCurrentInstance().
				getExternalContext().getRequest();
	}
	
}