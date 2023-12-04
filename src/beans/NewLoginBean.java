package beans;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.el.ELContext;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import util.EsquemaBean;
import util.UserBean;
import dao.EsquemaDAO;
import dao.UserDAO;

import org.mindrot.jbcrypt.BCrypt;



@ManagedBean(name = "newLoginBean")
public class NewLoginBean {

	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}	

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String loginUser() {

		try { //crear dos logins, uno para entrar de forma normal y actualizar al hash y otro cuado falle el primer login que me permita entrar con el hash
			 System.out.println("///////////////////////INICIO LOGIN//////////////////////////");
			
			HttpSession session = Util.getSession();
	        
	        Calendar inicioSesion = new GregorianCalendar();

	        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	        userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
	                .getELResolver().getValue(elContext, null, "userBean");
	        
	        //System.out.println("userBean: " + userBean);
	        //System.out.println("userBean RUT: " + userBean.getRut());
	        //System.out.println("userBean pass (ingresada): " + userBean.getPass());
	        String plaintextPassword = userBean.getPass();
	        //System.out.println("userBean HASH: " + userBean.isIs_hashed());
	        UserDAO.estadoHash(userBean.getRut(), userBean);
	        //System.out.println("userBean HASH: " + userBean.isIs_hashed());


	        UserDAO.obtenerPass(userBean.getRut(), userBean);
	        //System.out.println("userBean pass (BASE DE DATOS): " + userBean.getPass());
	        String actualPassword = userBean.getPass();
	        //System.out.println("userBean pass (variable actualpassword): " + actualPassword);

	        if (!userBean.isIs_hashed()) {
	            // Si la contraseña ingresada coincide con la contraseña en la base de datos
	            if (plaintextPassword.equals(actualPassword)) {
	                System.out.println("###########ACTUALIZANDO CONTRASEÑA A HASH##########");
	                String hashedPassword = BCrypt.hashpw(userBean.getPass(), BCrypt.gensalt());

	                // Actualizar la contraseña en el objeto userBean y en la base de datos
	                userBean.setPass(hashedPassword);
	                userBean.setIs_hashed(true);
	                UserDAO.actualizarPassword(userBean.getRut(), hashedPassword);

	                //System.out.println("Contraseña actualizada a hash: " + hashedPassword);

	                UserDAO.estadoHash(userBean.getRut(), userBean);
	                //System.out.println("al finalizar hasheo userBean HASH: " + userBean.isIs_hashed());
	            } else {
	                System.out.println("La contraseña ingresada no coincide con la almacenada en la base de datos.");
	                // Manejar el caso en el que la contraseña no coincide (podría mostrar un mensaje de error o realizar alguna acción)
	                FacesContext  context = FacesContext.getCurrentInstance();
					context.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Datos inválidos. Por favor, intente denuevo.",""));
					context.getExternalContext().getFlash().setKeepMessages(true);

					session.invalidate();
					return "login";
	            }
	        }

	        
	        UserDAO.obtenerPass(userBean.getRut(), userBean);
	        //System.out.println("userBean pass (BASE DE DATOS): "+userBean.getPass());
	        //System.out.println("userBean pass (INGRESADA en variable): "+plaintextPassword);
	        //System.out.println("userBean HASH: "+userBean.isIs_hashed());
	        // Verificar si la contraseña proporcionada es un hash
	        if (!BCrypt.checkpw(plaintextPassword, userBean.getPass())) {
	            // Convertir la contraseña proporcionada a un hash
	            String hashedPassword = BCrypt.hashpw(userBean.getPass(), BCrypt.gensalt());
	            userBean.setPass(hashedPassword);
	        }

	        // Intentar iniciar sesión con las credenciales ingresadas (texto plano)
	        userBean = UserDAO.login(userBean.getRut(), userBean.getPass());
	        
			if (userBean != null) {

				
				
				
				userBean.setInicioSesion(inicioSesion);


				session.setAttribute("rut", userBean.getRut());
				session.setAttribute("nombre", userBean.getNombre1());
				session.setAttribute("pass", userBean.getPass());
				session.setAttribute("tipo", userBean.getTipo());
				session.setAttribute("usuario", userBean);				

				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Bienvenido " + userBean.getNombre1() + " " + userBean.getPaterno(),
								""));
				context.getExternalContext().getFlash().setKeepMessages(true);		
				
				System.out.println("Bienvenido " + userBean.getNombre1() + " " + userBean.getPaterno()
						+ "	");
				System.out.println("userBean RUT: "+userBean.getRut());
				
				UserDAO.estadoHash(userBean.getRut(), userBean);
				//System.out.println("userBean HASH: "+userBean.isIs_hashed());
				
				return "home";

			} else {
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Datos inválidos. Por favor, intente denuevo.",""));
				context.getExternalContext().getFlash().setKeepMessages(true);

				session.invalidate();
				return "login";
			}
		} catch (Exception ex) {
			System.err.println("EXCEPTION IN LOGIN USER");
			ex.printStackTrace();
			return "login";
		}
	}

	
	public String logout() {

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
				.getELResolver().getValue(elContext, null, "usuario");
		EsquemaDAO.borrarEsquemaLoad(userBean.getRut());

		HttpSession session = Util.getSession();
		session.setAttribute("userBean", null);
		session.invalidate();
		UserDAO.estadoHash(userBean.getRut(), userBean);
		System.out.println("userBean HASH: "+userBean.isIs_hashed());
		return "login";
	}	

	public void isAdmin(ComponentSystemEvent event){
		HttpSession session = Util.getSession();
		if ((Integer)session.getAttribute("tipo") != 1){
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
					fc.getApplication().getNavigationHandler();
			nav.performNavigation("error");
		}
	}

	public void isAlumno(ComponentSystemEvent event){
		HttpSession session = Util.getSession();
		if ((Integer)session.getAttribute("tipo") != 2){
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
					fc.getApplication().getNavigationHandler();
			nav.performNavigation("error");
		}
	}

	public void loadBD(ComponentSystemEvent event){
		HttpSession session = Util.getSession();
		if ((EsquemaBean)session.getAttribute("bd") == null){
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
					fc.getApplication().getNavigationHandler();
			nav.performNavigation("error");
		}
	}

	public void isMyBd(ComponentSystemEvent event){
		HttpSession session = Util.getSession();
		EsquemaBean bd = (EsquemaBean)session.getAttribute("bd");
		UserBean user = (UserBean)session.getAttribute("usuario");
		if(user.getTipo() == 2 && !user.getRut().equals(bd.getRut())){
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
					fc.getApplication().getNavigationHandler();
			nav.performNavigation("error");
		}
	}

	//EL SIGUIENTE EVENTO NOS PERMITE LLAMAR AL METODO ISADMIN() DESDE
	// UN XHTML PARA MOSTRAR LA PAGINA SI CORRESPONDE AL ADMINISTRADOR -->
	//<f:event listener="#{userBean.isAdmin}" type="preRenderView" />	

	public String registrarse(){
		return "registrarse";
	}
}
