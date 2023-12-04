package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import util.AtributoBean;
import util.Ejercicio;
import util.EsquemaBean;
import util.RelacionBean;
import util.TuplaBean;
import util.UserBean;
import dao.ConsultaDAO;
import dao.EsquemaDAO;

@ManagedBean(name="dtObtenerCalificacionBean")
@ViewScoped
public class ObtenerCalificacionBean implements Serializable {
	
	private String tableName;
	private List<Object[]> data;
	private List<String> columnNames;
	private Ejercicio selectedEjercicio;
	
	@PostConstruct
	public void init(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
    	
//    	if(userBean.getEjercicios() == null || userBean.getResultado() == null){
//    		try {
//				FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
//			} catch (Exception e) {
//				System.out.println("No pude redireccionar");
//			}
//    	}
    	
	}
	
	@PreDestroy
	public void destroy(){
		
//		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//    	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
//			    .getELResolver().getValue(elContext, null, "usuario");
//		
//		userBean.setEjercicios(null);
//		userBean.setResultado(null);
//		
//		HttpSession session = Util.getSession();
//		session.setAttribute("usuario", userBean);
//		Util.reloadBd();
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Object[]> getData() {
		return data;
	}
	public void setData(List<Object[]> data) {
		this.data = data;
	}
	public List<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
	public Ejercicio getSelectedEjercicio() {
		return selectedEjercicio;
	}
	public void setSelectedEjercicio(Ejercicio selectedEjercicio) {
		this.selectedEjercicio = selectedEjercicio;
	}
	
	
	public void verRespuestaEsperada(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	EsquemaBean esquemaBean = (EsquemaBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "bd");
		
		RelacionBean relacionBean = ConsultaDAO.verRespuestaEsperada("_respuesta_ejercicio_" + selectedEjercicio.getId(),esquemaBean);
		
		if(relacionBean != null){
			
			columnNames = new ArrayList<String>();
			data = new ArrayList<Object[]>();

			if(relacionBean.getTuplasBean()!= null){

				tableName = relacionBean.getNombre();
				
				for(AtributoBean a : relacionBean.getAtributos())
					columnNames.add(a.getNombre());
				
				for(TuplaBean t : relacionBean.getTuplasBean())
					data.add(t.getAtributos());
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Respuesta cargada","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				tableName = null;
				columnNames = new ArrayList<String>();
				data = new ArrayList<Object[]>();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar el resultado","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}else{
			tableName = null;
			columnNames = null;
			data = null;
		}
			
	}
	
	public void verRespuesta(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		
		RelacionBean relacionBean = ConsultaDAO.verRespuesta(selectedEjercicio.getRespuestaAlumno(),"load" + userBean.getRut());
		
		if(relacionBean != null){
			
			columnNames = new ArrayList<String>();
			data = new ArrayList<Object[]>();

			if(relacionBean.getTuplasBean()!= null){

				tableName = relacionBean.getNombre();
				
				for(AtributoBean a : relacionBean.getAtributos())
					columnNames.add(a.getNombre());
				
				for(TuplaBean t : relacionBean.getTuplasBean())
					data.add(t.getAtributos());
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Respuesta cargada","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				tableName = null;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar el resultado","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}else{
			tableName = null;
			columnNames = null;
			data = null;
		}
			
	}

}
