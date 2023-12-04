package beans;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import util.UserBean;
import dao.UserDAO;

@ManagedBean(name = "registrarseBean")
public class RegistrarseBean {

	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String registrarse() {

		Calendar inicioSesion = new GregorianCalendar();

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
				.getELResolver().getValue(elContext, null, "userBean");

		userBean = UserDAO.registrarAlumno(userBean);
		if (userBean != null) {
			if(userBean.getNombre1().equals("duplicado")){

				userBean = null;
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Este usuario ya tiene un cuenta",""));
				context.getExternalContext().getFlash().setKeepMessages(true);

				return "login";

			}else{

				// get Http Session and store username
				HttpSession session = Util.getSession();

				userBean.setInicioSesion(inicioSesion);

				session.setAttribute("rut", userBean.getRut());
				session.setAttribute("nombre", userBean.getNombre1());
				session.setAttribute("pass", userBean.getPass());
				session.setAttribute("tipo", userBean.getTipo());
				session.setAttribute("usuario", userBean);

				System.out.println("Sesion iniciada con: "+session.getAttribute("nombre"));
				System.out.println("Tipo: " + session.getAttribute("tipo"));

				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Bienvenido " + userBean.getNombre1() + " " + userBean.getPaterno(),
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);
				
				if (!userBean.isIs_hashed()) {
		            // Si la contraseña ingresada coincide con la contraseña en la base de datos
		            
		                System.out.println("###########ACTUALIZANDO CONTRASEÑA A HASH##########");
		                String hashedPassword = BCrypt.hashpw(userBean.getPass(), BCrypt.gensalt());

		                // Actualizar la contraseña en el objeto userBean y en la base de datos
		                userBean.setPass(hashedPassword);
		                userBean.setIs_hashed(true);
		                UserDAO.actualizarPassword(userBean.getRut(), hashedPassword);

		                //System.out.println("Contraseña actualizada a hash: " + hashedPassword);

		                UserDAO.estadoHash(userBean.getRut(), userBean);
		                //System.out.println("al finalizar hasheo userBean HASH: " + userBean.isIs_hashed());
		            
		        }
				

				return "home";

			}
		} else {
			FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ha habido un problema al crear la nueva cuenta",""));
			context.getExternalContext().getFlash().setKeepMessages(true);
			return "login";
		}

	}
}