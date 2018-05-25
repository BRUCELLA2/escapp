package fr.brucella.form.escapp.webapp.action.users;

import java.util.ArrayList;
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

public class UserAction extends ActionSupport implements SessionAware, ServletRequestAware {
    
    private static final long   serialVersionUID = -9137265072794078899L;
    
    // ----- Input
    private String              login;
    private String              password;
    private String              confPwd;
    private String              email;
    
    // ----- Output
    
    // ----- Manager
    @Autowired
    private ManagerFactory      managerFactory;
    
    // ----- Struts Elements
    private Map<String, Object> session;
    private HttpServletRequest  servletRequest;
    
    
    // ===== Getters / Setters =====
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String pLogin) {
        this.login = pLogin;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String pPassword) {
        this.password = pPassword;
    }
    
    public String getConfPwd() {
        return this.confPwd;
    }
    
    public void setConfPwd(String pConfPwd) {
        this.confPwd = pConfPwd;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String pEmail) {
        this.email = pEmail;
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
        
        User vUser = new User();
        vUser.setLogin(this.login);
        vUser.setEmail(this.email);
        vUser.setPassword(this.password);
        
        try {
            vUser = this.managerFactory.getUserManager().addUser(vUser);
        } catch (FunctionalException | TechnicalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        this.session.put("user", vUser);
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
        
        User vUser = new User();
        List<RoleUser> vRoles = new ArrayList<>();
        try {
            vUser = this.managerFactory.getUserManager().getConnectUser(this.login, this.password);
            vRoles = this.managerFactory.getUserManager().getRoleUserList(vUser.getId());
        } catch (FunctionalException | TechnicalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        } catch (NotFoundException pException) {
            this.addFieldError("login", "L'identifiant et/ou le mot de passe sont incorrects - La connexion n'a pu être réalisée");
            this.addFieldError("password", "L'identifiant et/ou le mot de passe sont incorrects - La connexion n'a pu être réalisée");
            return Action.INPUT;
        }
        
        this.session.put("user", vUser);
        this.session.put("roles", vRoles);
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
