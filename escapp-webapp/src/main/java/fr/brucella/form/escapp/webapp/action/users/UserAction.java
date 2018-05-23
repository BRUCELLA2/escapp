package fr.brucella.form.escapp.webapp.action.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.user.RoleUser;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public class UserAction extends ActionSupport implements SessionAware, ServletRequestAware{

	// ----- Input
	private String login;
	private String password;
	private String confPwd;
	private String email;
	
	// ----- Output
	
	// ----- Manager
	@Autowired
	private ManagerFactory managerFactory;
	
	// ----- Struts Elements
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;
	
	
	// ===== Getters / Setters =====
	public  String getLogin() {
		return login;
	}
	
	public void setLogin(String pLogin) {
		login = pLogin;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pPassword) {
		password = pPassword;
	}
	
	public String getConfPwd() {
	  return confPwd;
	}
	
	public void setConfPwd(String pConfPwd) {
	  confPwd = pConfPwd;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String pEmail) {
		email = pEmail;
	}
	
	
	@Override
	public void setSession(Map<String, Object> pSession) {
		this.session = pSession;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest pServletRequest) {
		this.servletRequest = pServletRequest;
		
	}
	
	// ===== Methods =====
	
	/**
	 * Register a user.
	 * After registration, user is add to session.
	 * 
	 * @return ERROR if error occurred
	 * 		   INPUT if the login, password, password confirmaiton and email are emtpy or null
	 * 				 if login is already used by another user.
	 * 		   SUCCESS otherwise
	 */
	public String doRegister() {
		
		if(StringUtils.isAllEmpty(login, password, confPwd, email)){
	       return ActionSupport.INPUT;
		}
		
		if(StringUtils.isEmpty(login)) {
		  this.addFieldError("login", "L'identifiant doit être renseigné");
		} 
		else
			try {
				if(!managerFactory.getUserManager().checkLoginDispo(login)) {
					this.addFieldError("login", "L'identifiant est déjà utilisé");
				}
			} catch (TechnicalException pException) {
	        	this.addActionError(pException.getMessage());
	        	return ActionSupport.ERROR;
			}
		
		if(StringUtils.isEmpty(password)) {
		  this.addFieldError("password", "Le mot de passe doit être renseigné");
		}
		
		if(StringUtils.isEmpty(confPwd)) {
		  this.addFieldError("confPwd", "La confirmation du mot de passe doit être renseigné");
		}
		
		if(StringUtils.isEmpty(email)) {
		  this.addFieldError("email", "L'email doit être renseigné");
		}
		
		if(!StringUtils.equals(password, confPwd)) {
          this.addFieldError("password", "La confirmation du mot de passe ne correspond pas au mot de passe");
		}
		
		
		if(this.hasFieldErrors()) {
		  return ActionSupport.INPUT;
		}
		
		User vUser = new User();
		vUser.setLogin(login);
		vUser.setEmail(email);
		vUser.setPassword(password);
		
		try {
			vUser = managerFactory.getUserManager().addUser(vUser);
        } catch (FunctionalException pException) {
        	this.addActionError(pException.getMessage());
        	return ActionSupport.ERROR;
        } catch (TechnicalException pException) {
			this.addActionError(pException.getMessage());
        	return ActionSupport.ERROR;
        }
		
		this.session.put("user", vUser);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * Log a user.
	 * After login, user is add to session.
	 * 
	 * @return ERROR if error occurred
	 * 		   INPUT if the login, password are null or empty.
	 * 				 if the login and password don't match.
	 * 		   SUCCESS otherwise
	 */
	public String doLogin() {
		
		if(StringUtils.isAllEmpty(login, password)) {
			return ActionSupport.INPUT;
		}
		
		if(StringUtils.isEmpty(login)) {
			this.addFieldError("login", "L'identifiant doit être renseigné");
		}
		
		if(StringUtils.isEmpty(password)) {
			this.addFieldError("password", "Le mot de passe doit être renseigné");
		}
		
		if(hasFieldErrors()) {
			return ActionSupport.INPUT;
		}
		
		User vUser = new User();
		List<RoleUser> vRoles = new ArrayList<>();
		try {
			vUser = managerFactory.getUserManager().getConnectUser(login, password);
			vRoles = managerFactory.getUserManager().getRoleUserList(vUser.getId());
		} catch (FunctionalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (NotFoundException pException) {
			this.addFieldError("login", "L'identifiant et/ou le mot de passe sont incorrects - La connexion n'a pu être réalisée");
			this.addFieldError("password", "L'identifiant et/ou le mot de passe sont incorrects - La connexion n'a pu être réalisée");
			return ActionSupport.INPUT;
		}catch (TechnicalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}
		
		this.session.put("user", vUser);
		this.session.put("roles", vRoles);
		return ActionSupport.SUCCESS;		
	}
	
	/**
	 * Log out the user.
	 * Session is invalidate.
	 * 
	 * @return SUCCESS
	 */
	public String doLogout() {
		
		this.servletRequest.getSession().invalidate();
		return ActionSupport.SUCCESS;
	}
}
