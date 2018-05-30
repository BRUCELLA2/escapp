package fr.brucella.form.escapp.model.beans.user;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Business object which represents the role of an User
 *
 * @author BRUCELLA2
 */
public class RoleUser implements Serializable {
  
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 1926369691569154147L;
  
  /**
   * id of the {@link User}.
   *
   * @see #getUserId()
   * @see #setUserId(Integer)
   */
  @NotNull(message = "L'identifiant de l'utilisateur associé au role doit être renseigné.")
  private Integer           userId;
  
  /**
   * Role of the {@link User}. Value can be one of these : "Admin" or "Moderator".
   *
   * @see #getUserRole()
   * @see #setUserRole(String)
   */
  @NotEmpty(message = "Le role doit être renseigné.")
  private String            userRole;
  
  
  // ===== Getters =====
  
  /**
   * Get the id of the {@link User}
   *
   * @return the {@link Integer} id of the {@link User} Can't be null.
   *
   * @see #userId;
   * @see #setUserId(Integer)
   */
  public Integer getUserId() {
    return this.userId;
  }
  
  /**
   * Get the role of the {@link User}
   *
   * @return the role of the {@link User} Can't be empty.
   *
   * @see #userRole
   * @see #setUserRole(String)
   */
  public String getUserRole() {
    return this.userRole;
  }
  
  
  // ===== Setters =====
  
  /**
   * Set the id of the {@link User}.
   *
   * @param userId {@link Integer} id of the {@link User}. Can't be null.
   *
   * @see #userId
   * @see #getUserId()
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }
  
  /**
   * Set the role of the {@link User}.
   *
   * @param userRole the role of the {@link User}. Can't be null.
   *
   * @see #userRole
   * @see #getUserRole()
   */
  public void setUserRole(final String userRole) {
    this.userRole = userRole;
  }
  
  
  // ===== Methods =====
  
  
  @Override
  public String toString() {
    return "RoleUser [userId=" + this.userId + ", userRole=" + this.userRole + "]";
  }
}
