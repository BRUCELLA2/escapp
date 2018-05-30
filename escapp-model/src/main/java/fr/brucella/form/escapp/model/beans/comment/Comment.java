package fr.brucella.form.escapp.model.beans.comment;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Business object which represents a Comment.
 *
 * @author BRUCELLA2
 */
public class Comment implements Serializable {
  
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 430229845493717046L;
  
  
  /**
   * id of the {@link Comment}.
   *
   * @see #getId()
   * @see #setId(Integer)
   */
  private Integer           id;
  
  
  /**
   * Text of the {@link Comment} (write by an user). Can't by empty and max size is 1000 characters.
   *
   * @see #getText()
   * @see #setText(String)
   */
  @NotEmpty(message = "Le texte ne peut être vide")
  @Size(min = 1, max = 1000, message = "Le texte ne peut dépasser {max} caractères")
  private String            text;
  
  
  /**
   * id of the {@link fr.brucella.form.escapp.model.beans.user.User} who write the {@link Comment}.
   * Can't be null.
   *
   * @see #getEscappUser()
   * @see #setEscappUser(Integer)
   */
  @NotNull(message = "Un commentaire doit être associé à un utilisateur")
  private Integer           escappUser;
  
  
  /**
   * String that represents the target's type of the {@link Comment} Value can be one of these :
   * "Length", "Route", "Sector", "Site" or "Topo". Can't be empty.
   *
   * @see #getTargetType()
   * @see #setTargetType(String)
   */
  @NotEmpty(message = "Le type de la cible du commentaire ne peut être null")
  private String            targetType;
  
  
  /**
   * id of the target which is commented. Can't be null.
   *
   * @see #getIdCommentTarget()
   * @see #setIdCommentTarget(Integer)
   */
  @NotNull(message = "Un commentaire doit être associé à un élément à commenter")
  private Integer           idCommentTarget;
  
  
  
  // ===== Getters =====
  
  
  /**
   * Get the id of the {@link Comment}.
   *
   * @return the {@link Integer} id of the {@link Comment}.
   *
   * @see #id
   * @see #setId(Integer)
   */
  public Integer getId() {
    return this.id;
  }
  
  
  /**
   * Get the text of the {@link Comment}.
   *
   * @return the {@link String} text of the {@link Comment}. Max size is 1000 characters. Can't be
   *         empty.
   *
   * @see #text
   * @see #setText(String)
   */
  public String getText() {
    return this.text;
  }
  
  
  /**
   * Get the id of the user who write the {@link Comment}.
   *
   * @return the {@link Integer} id of the user who write the {@link Comment}. Can't be null.
   *
   * @see #escappUser
   * @see #setEscappUser(Integer)
   */
  public Integer getEscappUser() {
    return this.escappUser;
  }
  
  
  /**
   * Get the {@link String} that represents the target's type of the {@link Comment}.
   *
   * @return the {@link String} that represents the target's type of the {@link Comment}. Can't be
   *         empty. Values can be one of these : "Length", "Route", "Sector", "Site" or "Topo".
   *
   * @see #targetType
   * @see #setTargetType(String)
   */
  public String getTargetType() {
    return this.targetType;
  }
  
  
  /**
   * Get the id of the target which is commented.
   *
   * @return the {@link Integer} of the target which is commented. Can't be null.
   *
   * @see #idCommentTarget
   * @see #setIdCommentTarget(Integer)
   */
  public Integer getIdCommentTarget() {
    return this.idCommentTarget;
  }
  
  
  // ===== Setters =====
  
  
  /**
   * Set the id of the {@link Comment}.
   *
   * @param id {@link Integer} id of the {@link Comment}.
   *
   * @see #id
   * @see #getId()
   */
  public void setId(final Integer id) {
    this.id = id;
  }
  
  
  /**
   * Set the text of the {@link Comment}.
   *
   * @param text {@link String} text of the {@link Comment} Can't be empty. Max size is 1000
   *        characters.
   *
   * @see #text
   * @see #getText()
   */
  public void setText(final String text) {
    this.text = text;
  }
  
  
  /**
   * Set the id of the user who writes the {@link Comment}.
   *
   * @param escappUser {@link Integer} id of the user who writes the {@link Comment}. Can't be null
   *
   * @see #escappUser
   * @see #setEscappUser(Integer)
   */
  public void setEscappUser(final Integer escappUser) {
    this.escappUser = escappUser;
  }
  
  
  /**
   * Set the {@link String} that represents the target's type of the {@link Comment}.
   *
   * @param targetType {@link String} that represents the target's type of the {@link Comment}. Can't
   *        be empty. Values can be one of these : "Length", "Route", "Sector", "Site" or "Topo".
   *
   * @see #targetType
   * @see #getTargetType()
   */
  public void setTargetType(final String targetType) {
    this.targetType = targetType;
  }
  
  
  /**
   * Set the id of the target which is commented.
   *
   * @param idCommentTarget {@link Integer} id of the target which is commented. Can't be null
   *
   * @see #idCommentTarget
   * @see #setIdCommentTarget(Integer)
   */
  public void setIdCommentTarget(final Integer idCommentTarget) {
    this.idCommentTarget = idCommentTarget;
  }
  
  // ===== Methods =====
  
  
  @Override
  public String toString() {
    return "Comment{" + "id=" + this.id + ", text='" + this.text + '\'' + ", escappUser=" + this.escappUser + ", targetType='" + this.targetType + '\''
        + ", idCommentTarget=" + this.idCommentTarget + '}';
  }
}
