package beans;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.EsquemaDAO;
import util.EsquemaBean;
import util.UserBean;
	 
	@ManagedBean(name = "menuView")
	public class MenuView {
	     
		public String gestionarEjercicio(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes Gestionar Ejercicios si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "gestionarEjercicios";
			}
		}
		
		public String responderEjercicio(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes Responder Ejercicios si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "responderEjercicios";
			}
		}
				
		public String obtenerCalificacion(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes obtener calificaciones si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "obtenerCalificacion";
			}
		}
				
		public String verRelacion(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes ver aún si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "verRelacion";
			}
		}
		
		public String modificarRelacion(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes modificar aún si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "modificarRelacion";
			}
		}
		
		public String eliminarRelacion(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes eliminar una Relación si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "eliminarRelacion";
			}
		}
		
		public String agregarRelacion(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes agregar aún si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "agregarRelacion";
			}
		}
		
		public String hacerConsulta(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes hacer una Consulta si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "hacerConsulta";
			}
		}
		
		public String hacerConsultaSQL(){
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EsquemaBean bd = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "bd");
			if(bd == null){
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"No puedes hacer una Consulta si no has cargado una Base de Datos",
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
           		return "home";
			}else{
				return "hacerConsultaSQL";
			}
		}
				
		public String logout() {
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
				    .getELResolver().getValue(elContext, null, "usuario");
			if(userBean != null){
				EsquemaDAO.borrarEsquemaLoad(userBean.getRut());
			}
			
			HttpSession session = Util.getSession();			
			session.invalidate();
			return "login";
		}
		

	}
