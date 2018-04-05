package fr.brucella.form.escapp.model.beans.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Business object which represents an User
 *
 * @author BRUCELLA2
 */
public class User {

    // @TODO check messages and write it in resource bundle

    private Integer id;

    @NotEmpty(message = "L'identifiant de l'utilisateur doit être renseigné")
    @Size(min = 1, max = 30, message = "L'identifiant ne peut avoir que {max} caractères au maximum")
    private String login;

    @NotEmpty(message = "Il est obligatoire de renseigner le mot de passe")
    @Size(min = 5, max = 50, message = "Le mot de passe ne peut avoir que {max} caractères au maximum")
    private String password;

    @NotEmpty(message = "L'adresse email ne peut être vide")
    @Email(message = "L'adresse email n'est pas correcte")
    @Size(max = 100, message = "Votre adresse mail est trop longue. Elle ne doit pas contenir plus de {max} caractères")
    private String email;

    // ===== Constructor =====
    /**
     * Constructor
     */
    public User(){

    }

    // ===== Getters =====

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // ===== Setters =====

    public void setId(Integer pId) {
        this.id = pId;
    }

    public void setLogin(String pLogin){
        this.login = pLogin;
    }

    public void setPassword(String pPassword){
        this.password = pPassword;
    }

    public void setEmail(String pEmail){
        this.email = pEmail;
    }


    // ===== Methods =====

    // @TODO make a better toString method

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
