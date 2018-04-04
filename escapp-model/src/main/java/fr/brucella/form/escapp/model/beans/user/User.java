package fr.brucella.form.escapp.model.beans.user;

/**
 * Business object which represents an User
 *
 * @author BRUCELLA2
 */
public class User {
    // @TODO add JSR 380 validation tags

    private Integer id;
    private String login;
    private String password;
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
