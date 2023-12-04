package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import util.Esquema;
import util.EsquemaBean;
import util.UserBean;
import dao.EsquemaDAO;

@ManagedBean(name = "homeView")
@ViewScoped
public class HomeView implements Serializable {

	private List<Esquema> basesDatos;
    
    @PostConstruct
    public void init() {
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
        basesDatos = EsquemaDAO.selectMisBds(userBean.getRut());
   
    }
    
    public List<Esquema> getBasesDatos() {
		return basesDatos;
	}

	public void setBasesDatos(List<Esquema> basesDatos) {
		this.basesDatos = basesDatos;
	}

	public String borrarEsquemasLoad(){
		System.out.println("Borrando Cosito");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		if(bd != null){
			HttpSession session = Util.getSession();			
			session.setAttribute("bd",null);			
		}
		
		elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		EsquemaDAO.borrarEsquemaLoad(userBean.getRut());
		
		String res = EsquemaDAO.borrarEsquemasLoad();
		if(res.equals("ok")){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se han eliminado los esquemas load","");
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ha ocurrido un error al tratar de eliminar los esquemas: " + res,"");
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return "home";
	}
    
}
