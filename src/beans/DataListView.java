package beans;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.EsquemaDAO;
import util.AtributoBean;
import util.EsquemaBean;
import util.RelacionBean;
import util.UserBean;
 
@ManagedBean(name = "dataListView")
@ViewScoped
public class DataListView implements Serializable {
     
    private List<RelacionBean> relacionesBean;     
    private RelacionBean selectedRelacionBean;
 
//    public List<RelacionBean> getRelacionesBean() {
//    	
//    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//		EsquemaBean bdBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
//			    .getELResolver().getValue(elContext, null, "bd");
//		
//		relacionesBean = bdBean.getRelacionesBean();   	
//        return relacionesBean;
//    }

    public List<RelacionBean> getRelacionesBean() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        EsquemaBean bdBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
            .getELResolver().getValue(elContext, null, "bd");
            
        relacionesBean = bdBean.getRelacionesBean();
        
        for (RelacionBean relacion : relacionesBean) {
            // Obtener los atributos para cada relación y asignarlos a la lista de atributos en RelacionBean
            List<AtributoBean> atributos = bdBean.getAtributosParaRelacion(relacion.getNombre());
            relacion.setAtributos(atributos);
        }
        
        return relacionesBean;
    }

    public String cerrarBD(){
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		if(bd != null){
			HttpSession session = Util.getSession();
			
			session.setAttribute("bd",null);
			
			FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Se ha cerrado la base de datos " + bd.getNombre().toUpperCase(),
							""));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
		
		elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		EsquemaDAO.borrarEsquemaLoad(userBean.getRut());
		
		return "home";
    }
 
    public RelacionBean getSelectedRelacionBean() {
        return selectedRelacionBean;
    }
 
    public void setSelectedCar(RelacionBean selectedRelacionBean) {
        this.selectedRelacionBean = selectedRelacionBean;
    }
}