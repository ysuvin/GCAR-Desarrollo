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

import org.mindrot.jbcrypt.BCrypt;

import util.User;
import util.UserBean;
import dao.UserDAO;

@ManagedBean(name="gestionarCuentasBean")
@ViewScoped
public class GestionarCuentasBean implements Serializable {
     
    private List<User> users;
    private User selectedUser;
    private List<User> filteredUsers;
    
    private User user = new User();
    
    private UserBean userBean = new UserBean();
    
    private String search = "";
 
    @PostConstruct
	public void init(){
    	users = UserDAO.selectUsuarios();
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
    }
                
    public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<User> getUsers() {
        return users;
    }
     
    public User getSelectedUser() {
        return selectedUser;
    }
 
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    public List<User> getFilteredUsers() {
        return filteredUsers;
    }
 
    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }
    
    public void delete(){
    	  	
    	if(UserDAO.eliminarUsuario(selectedUser)){
        	search = "";
        	for(int i = 0 ; i < users.size() ; i++){
        		if(users.get(i).getRut().equals(selectedUser.getRut())){
        			users.remove(users.get(i));
        		}
        	}   	
        	filteredUsers = users;  
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha eliminado el Alumno " + selectedUser.getNombre1() + " " + selectedUser.getPaterno(),"");
            FacesContext.getCurrentInstance().addMessage(null, msg);
       	}else{
       		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al eliminar el Alumno","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    		
    }
    
    public void deseleccionar(){
    	selectedUser = null;
    }
    
    public String update(){
    	System.out.println(selectedUser.getNombre1());
    	
    	if(selectedUser.getNombre1() != null || selectedUser.getPaterno() != null || selectedUser.getMaterno() != null ||
    			selectedUser.getAnyo() != null){
    	
	    	if(UserDAO.modificarUsuario(selectedUser)){
	    		search = "";  	
	        	filteredUsers = users;  
	    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha modificado el Alumno " + selectedUser.getNombre1() + " " + selectedUser.getPaterno(),"");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            
                selectedUser = null;
                return "modificarCuenta";
                
	       	}else{
	       		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al modificar el Alumno","");
	       		FacesContext.getCurrentInstance().addMessage(null, msg);
	       		
	       		selectedUser = null;
                return "modificarCuenta";
	        }	    	
	    	
    	}else{
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Los campos: Primer Nombre, Apellido Paterno, "
    				+ "Apellido Materno o Año de Ingreso no deben ser vacíos","");
       		FacesContext.getCurrentInstance().addMessage(null, msg);
       		
       		selectedUser = null;
            return "modificarCuenta";
    	}
    	
    		
    }
    
    public String save() {
    	
    	user = UserDAO.crearUsuario(user);
    	if(user != null){
    		if(user.getNombre1().equals("duplicado")){
				
				FacesContext  context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Este usuario ya tiene un cuenta",""));
				context.getExternalContext().getFlash().setKeepMessages(true);
				
				return "crearCuenta";
				
    		}else{
                FacesContext  context = FacesContext.getCurrentInstance();
    			context.addMessage(
    					null,
    					new FacesMessage(FacesMessage.SEVERITY_INFO,
    							"Se ha creado el usuario " + user.getNombre1() + " " + user.getPaterno(),""));
    			context.getExternalContext().getFlash().setKeepMessages(true);
    			
    			//HASHHHHHHHHH
    			System.out.println("user RUT: " + user.getRut());
    			//System.out.println("user pass (ingresada): " + user.getPass());
    			//System.out.println("userBean RUT: " + userBean.getRut());
    			//System.out.println("userBean pass (ingresada): " + userBean.getPass());
    			System.out.println("###########ACTUALIZANDO CONTRASEÑA A HASH##########");
    			
                String hashedPassword = BCrypt.hashpw(user.getPass(), BCrypt.gensalt());
                // Actualizar la contraseña en el objeto userBean y en la base de datos
                user.setPass(hashedPassword);
                user.setIs_hashed(true);
                UserDAO.actualizarPassword(user.getRut(), hashedPassword);

                //System.out.println("Contraseña actualizada a hash: " + hashedPassword);

                UserDAO.estadoHash(user.getRut(), userBean);
                //System.out.println("al finalizar hasheo userBean HASH: " + user.isIs_hashed());
    			
                
                return "home";
    		}        
            
    	}else{
            
            FacesContext  context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ha sido imposible crear el usuario",""));
			context.getExternalContext().getFlash().setKeepMessages(true);
            
            return "crearCuenta";
            
    	}

    }

	public String modificarProfesor() {
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		User user = new User();
		
		System.out.println(userBean.getRut());
		
		user.setRut(userBean.getRut());
		user.setPass(userBean.getPass());
		user.setNombre1(userBean.getNombre1());
		user.setNombre2(userBean.getNombre2());
		user.setPaterno(userBean.getPaterno());
		user.setMaterno(userBean.getMaterno());
		user.setMail(userBean.getMail());
		user.setTipo(userBean.getTipo());
		user.setAnyo(userBean.getAnyo());
		//user.setVezCursando(userBean.getVezCursando());
		user.setEdad(userBean.getEdad());
		
		UserDAO.actualizarIsHashed(userBean.getRut());
		user.setIs_hashed(userBean.isIs_hashed());
		
		if(UserDAO.modificarUsuario(user)){
			
			HttpSession session = Util.getSession();
			System.out.println("###########HTTPSESION##########");
			
			session.setAttribute("rut", userBean.getRut());
			session.setAttribute("nombre", userBean.getNombre1());

			session.setAttribute("tipo", userBean.getTipo());
			
            System.out.println("###########ACTUALIZANDO CONTRASEÑA A HASH##########");
            String hashedPassword = BCrypt.hashpw(userBean.getPass(), BCrypt.gensalt());

            // Actualizar la contraseña en el objeto userBean y en la base de datos
            userBean.setPass(hashedPassword);
            userBean.setIs_hashed(true);
            UserDAO.actualizarPassword(userBean.getRut(), hashedPassword);

            //System.out.println("Contraseña actualizada a hash: " + hashedPassword);

            UserDAO.estadoHash(userBean.getRut(), userBean);
            //System.out.println("al finalizar hasheo userBean HASH: " + userBean.isIs_hashed());

            session.setAttribute("pass", userBean.getPass());
            session.setAttribute("usuario", userBean); 

			
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha creado el usuario :" + user.getNombre1() + " " + user.getPaterno(),"");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return "home";
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ha sido imposible modificar la cuenta","");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return null;
		}
	
	}

	public String modificarAlumno() {
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "usuario");
		User user = new User();
		
		System.out.println(userBean.getRut());
		
		user.setRut(userBean.getRut());
		user.setPass(userBean.getPass());
		user.setNombre1(userBean.getNombre1());
		user.setNombre2(userBean.getNombre2());
		user.setPaterno(userBean.getPaterno());
		user.setMaterno(userBean.getMaterno());
		user.setMail(userBean.getMail());
		user.setTipo(userBean.getTipo());
		user.setAnyo(userBean.getAnyo());
		//user.setVezCursando(userBean.getVezCursando());
		user.setEdad(userBean.getEdad());
		
		UserDAO.actualizarIsHashed(userBean.getRut());
		user.setIs_hashed(userBean.isIs_hashed());
		
		
		if(UserDAO.modificarUsuario(user)){
			
			HttpSession session = Util.getSession();
			 System.out.println("###########HTTPSESION##########");
			session.setAttribute("rut", userBean.getRut());
			session.setAttribute("nombre", userBean.getNombre1());
			
			session.setAttribute("tipo", userBean.getTipo());
				
			

	            
	                System.out.println("###########ACTUALIZANDO CONTRASEÑA A HASH##########");
	                String hashedPassword = BCrypt.hashpw(userBean.getPass(), BCrypt.gensalt());

	                // Actualizar la contraseña en el objeto userBean y en la base de datos
	                userBean.setPass(hashedPassword);
	                userBean.setIs_hashed(true);
	                UserDAO.actualizarPassword(userBean.getRut(), hashedPassword);

	                System.out.println("Contraseña actualizada a hash: " + hashedPassword);

	                UserDAO.estadoHash(userBean.getRut(), userBean);
	                System.out.println("al finalizar hasheo userBean HASH: " + userBean.isIs_hashed());

			session.setAttribute("pass", userBean.getPass());
			session.setAttribute("usuario", userBean); 
			
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha modificado la cuenta","" );
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        

	        
	        return "home";
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ha sido imposible modificar la cuenta","");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return "modificarCuentaAlumno";
		}
	
	}
     
}
