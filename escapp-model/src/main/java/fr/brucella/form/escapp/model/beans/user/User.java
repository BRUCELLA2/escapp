package fr.brucella.form.escapp.model.beans.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Business object which represents an User
 *
 * @author BRUCELLA2
 */
public class User implements Serializable {
    
    private static final long serialVersionUID = -3400475576749606144L;
    
    
    /**
     * id of the {@link User}.
     * 
     * @see #getId()
     * @see #setId(Integer)
     */
    private Integer           id;
    
    
    /**
     * Login of the {@link User}. Can't be empty and max size is 30 characters.
     *
     * @see #getLogin()
     * @see #setLogin(String)
     */
    @NotEmpty(message = "L'identifiant de l'utilisateur doit être renseigné")
    @Size(min = 1, max = 30, message = "L'identifiant ne peut avoir que {max} caractères au maximum")
    private String            login;
    
    
    /**
     * Password of the {@link User}. Can't be empty. Max size is 60 characters.
     *
     * @see #getPassword()
     * @see #setPassword(String)
     */
    @NotEmpty(message = "Il est obligatoire de renseigner le mot de passe")
    @Size(max = 60, message = "Le mot de passe ne peut avoir que {max} caractères au maximum")
    private String            password;
    
    
    /**
     * Email of the {@link User}. Can't be empty. Max size is 100 characters.
     *
     * @see #getEmail()
     * @see #setEmail(String)
     */
    @NotEmpty(message = "L'adresse email ne peut être vide")
    @Email(message = "L'adresse email n'est pas correcte")
    @Size(max = 100, message = "Votre adresse mail est trop longue. Elle ne doit pas contenir plus de {max} caractères")
    private String            email;
    
    
    // ===== Getters =====
    
    
    /**
     * Get the id of the {@link User}.
     *
     * @return the {@link Integer} id of the {@link User}.
     *
     * @see #id
     * @see #setId(Integer)
     */
    public Integer getId() {
        return this.id;
    }
    
    
    /**
     * Get the login of the {@link User}.
     *
     * @return the login of the {@link User}. Can't be empty. Max size is 30 characters.
     *
     * @see #login
     * @see #setLogin(String)
     */
    public String getLogin() {
        return this.login;
    }
    
    
    /**
     * Get the password of the {@link User}.
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
     * Get the email of the {@link User}.
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
     * Set the id of the {@link User}.
     *
     * @param pId {@link Integer} id of the {@link User}.
     *
     * @see #id
     * @see #getId()
     */
    public void setId(Integer pId) {
        this.id = pId;
    }
    
    
    /**
     * Set the login of the {@link User}.
     *
     * @param pLogin the login of the {@link User}. Can't be empty. Max size is 30 characters.
     * @see #login
     * @see #getLogin()
     */
    public void setLogin(String pLogin) {
        this.login = pLogin;
    }
    
    
    /**
     * Set the password of the {@link User}.
     *
     * @param pPassword the password of the {@link User}. Can't be empty. Max size is 60 characters.
     * @see #password
     * @see #getPassword()
     */
    public void setPassword(String pPassword) {
        this.password = pPassword;
    }
    
    
    /**
     * Set the email of the {@link User}.
     *
     * @param pEmail the email of the {@link User}. Can't be empty. Max size is 100 characters.
     * @see #email
     * @see #getEmail()
     */
    public void setEmail(String pEmail) {
        this.email = pEmail;
    }
    
    
    // ===== Methods =====
    
    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", login='" + this.login + '\'' + ", password='" + this.password + '\'' + ", email='" + this.email + '\'' + '}';
    }
}
