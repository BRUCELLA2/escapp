package fr.brucella.form.escapp.webapp.action.users;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.user.RoleUser;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Actions related to User.
 *
 * @author BRUCELLA2
 */
public class UserAction extends ActionSupport implements SessionAware, ServletRequestAware {
  
  /**
   * Serial ID.
   */
  private static final long   serialVersionUID = -9137265072794078899L;
  
  // ----- Input
  
  /**
   * Login of the {@link User}. Can't be empty and max size is 30 characters.
   *
   * @see #getLogin()
   * @see #setLogin(String)
   */
  private String              login;
  
  /**
   * Password of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @see #getPassword()
   * @see #setPassword(String)
   */
  private String              password;
  
  /**
   * Password confirmation of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @see #getConfPwd()
   * @see #setConfPwd(String)
   */
  private String              confPwd;
  
  /**
   * Email of the {@link User}. Can't be empty. Max size is 100 characters.
   *
   * @see #getEmail()
   * @see #setEmail(String)
   */
  private String              email;
  
  // ----- Output
  
  // ----- Manager
  /**
   * The Manager Factory Manager Factory allow to get and set business managers.
   */
  @Autowired
  private ManagerFactory      managerFactory;
  
  // ----- Struts Elements
  
  /**
   * the user's HTTP session attributes.
   *
   * @see #setSession(Map)
   */
  private Map<String, Object> session;
  
  /**
   * The Http Servlet Request. Used to get session informations.
   *
   * @see HttpServletRequest
   * @see #setServletRequest(HttpServletRequest)
   */
  private HttpServletRequest  servletRequest;
  
  
  // ===== Getters =====
  /**
   * Get the login of the {@link User}. Can't be empty and max size is 30 characters.
   *
   * @return the login of the {@link User}. Can't be empty and max size is 30 characters.
   *
   * @see #login
   * @see #setLogin(String)
   */
  public String getLogin() {
    return this.login;
  }
  
  /**
   * Get the password of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @return the password of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @see #password
   * @see #setPassword(String)
   */
  public String getPassword() {
    return this.password;
  }
  
  /**
   * Get the password confirmation of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @return the password confirmation of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @see #confPwd
   * @see #setConfPwd(String)
   */
  public String getConfPwd() {
    return this.confPwd;
  }
  
  /**
   * Get the email of the {@link User}. Can't be empty. Max size is 100 characters.
   *
   * @return the email of the {@link User}. Can't be empty. Max size is 100 characters.
   *
   * @see #email
   * @see #setEmail(String)
   */
  public String getEmail() {
    return this.email;
  }
  
  // ===== Setters =====
  
  /**
   * Set the login of the {@link User}. Can't be empty and max size is 30 characters.
   *
   * @param login the login of the {@link User}. Can't be empty and max size is 30 characters.
   *
   * @see #login
   * @see #getLogin()
   */
  public void setLogin(final String login) {
    this.login = login;
  }
  
  /**
   * Set the password of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @param password the password of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @see #password
   * @see #getPassword()
   */
  public void setPassword(final String password) {
    this.password = password;
  }
  
  /**
   * Set the password confirmation of the {@link User}. Can't be empty. Max size is 60 characters.
   *
   * @param confPwd the password confirmation of the {@link User}. Can't be empty. Max size is 60
   *        characters.
   *
   * @see #confPwd
   * @see #getConfPwd()
   */
  public void setConfPwd(final String confPwd) {
    this.confPwd = confPwd;
  }
  
  /**
   * Set the email of the {@link User}. Can't be empty. Max size is 100 characters.
   *
   * @param email the email of the {@link User}. Can't be empty. Max size is 100 characters.
   *
   * @see #email
   * @see #setEmail(String)
   */
  public void setEmail(final String email) {
    this.email = email;
  }
  
  /**
   * Set the user's HTTP session attributes.
   *
   * @see #session
   */
  @Override
  public void setSession(final Map<String, Object> session) {
    this.session = session;
  }
  
  /**
   * Set the Http Servlet Request.
   *
   * @see #servletRequest
   */
  @Override
  public void setServletRequest(final HttpServletRequest servletRequest) {
    this.servletRequest = servletRequest;
    
  }
  
  // ===== Methods =====
  
  /**
   * Register a user. After registration, user is add to session.
   *
   * @return ERROR if error occurred INPUT if the login, password, password confirmaiton and email are
   *         emtpy or null if login is already used by another user. SUCCESS otherwise
   */
  public String doRegister() {
    
    if (StringUtils.isAllEmpty(this.login, this.password, this.confPwd, this.email)) {
      return Action.INPUT;
    }
    
    if (StringUtils.isEmpty(this.login)) {
      this.addFieldError("login", "L'identifiant doit être renseigné");
    }
    else {
      try {
        if (!this.managerFactory.getUserManager().checkLoginDispo(this.login)) {
          this.addFieldError("login", "L'identifiant est déjà utilisé");
        }
      } catch (TechnicalException pException) {
        this.addActionError(pException.getMessage());
        return Action.ERROR;
      }
    }
    
    if (StringUtils.isEmpty(this.password)) {
      this.addFieldError("password", "Le mot de passe doit être renseigné");
    }
    
    if (StringUtils.isEmpty(this.confPwd)) {
      this.addFieldError("confPwd", "La confirmation du mot de passe doit être renseigné");
    }
    
    if (StringUtils.isEmpty(this.email)) {
      this.addFieldError("email", "L'email doit être renseigné");
    }
    
    if (!StringUtils.equals(this.password, this.confPwd)) {
      this.addFieldError("password", "La confirmation du mot de passe ne correspond pas au mot de passe");
    }
    
    
    if (this.hasFieldErrors()) {
      return Action.INPUT;
    }
    
    User user = new User();
    user.setLogin(this.login);
    user.setEmail(this.email);
    user.setPassword(this.password);
    
    try {
      user = this.managerFactory.getUserManager().addUser(user);
    } catch (FunctionalException | TechnicalException pException) {
      this.addActionError(pException.getMessage());
      return Action.ERROR;
    }
    
    this.session.put("user", user);
    return Action.SUCCESS;
  }
  
  /**
   * Log a user. After login, user is add to session.
   *
   * @return ERROR if error occurred INPUT if the login, password are null or empty. if the login and
   *         password don't match. SUCCESS otherwise
   */
  public String doLogin() {
    
    if (StringUtils.isAllEmpty(this.login, this.password)) {
      return Action.INPUT;
    }
    
    if (StringUtils.isEmpty(this.login)) {
      this.addFieldError("login", "L'identifiant doit être renseigné");
    }
    
    if (StringUtils.isEmpty(this.password)) {
      this.addFieldError("password", "Le mot de passe doit être renseigné");
    }
    
    if (this.hasFieldErrors()) {
      return Action.INPUT;
    }
    
    try {
      final User user = this.managerFactory.getUserManager().getConnectUser(this.login, this.password);
      final List<RoleUser> roles = this.managerFactory.getUserManager().getRoleUserList(user.getId());
      this.session.put("user", user);
      this.session.put("roles", roles);
    } catch (FunctionalException | TechnicalException pException) {
      this.addActionError(pException.getMessage());
      return Action.ERROR;
    } catch (NotFoundException pException) {
      this.addFieldError("login", "L'identifiant et/ou le mot de passe sont incorrects - La connexion n'a pu être réalisée");
      this.addFieldError("password", "L'identifiant et/ou le mot de passe sont incorrects - La connexion n'a pu être réalisée");
      return Action.INPUT;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Log out the user. Session is invalidate.
   *
   * @return SUCCESS
   */
  public String doLogout() {
    
    this.servletRequest.getSession().invalidate();
    return Action.SUCCESS;
  }
}
