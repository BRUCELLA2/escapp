package fr.brucella.form.escapp.model.beans.comment;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Business object which represents a Comment.
 *
 * @author BRUCELLA2
 */
public class Comment {

    // @TODO check messages and write it in resource bundle

    private Integer id;

    @NotEmpty(message = "La text ne peut être vide")
    @Size(min = 1, max = 1000, message = "La text ne peut dépasser {max} caractères")
    private String text;

    @NotNull(message = "Un commentaire doit être associé à un utilisateur")
    private Integer userId;

    @NotEmpty(message = "Le type de la cible du commentaire ne peut être null")
    private String target_type;

    @NotNull(message = "Un commentaire doit être associé à un élément à commenter")
    private Integer idCommentTarget;

    // ===== Constructor =====
    /**
     * Constructor
     */
    public Comment(){

    }

    // ===== Getters =====

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTarget_type() { return target_type; }

    public Integer getIdCommentTarget() {
        return idCommentTarget;
    }

    // ===== Setters =====

    public void setId(Integer pId) {
        this.id = pId;
    }

    public void setText(String pText) {
        this.text = pText;
    }

    public void setUserId(Integer pUserId) {
        this.userId = pUserId;
    }

    public void setTarget_type(String pTarget_type) {
        this.target_type = pTarget_type;
    }

    public void setIdCommentTarget(Integer pIdCommentTarget) {
        this.idCommentTarget = pIdCommentTarget;
    }

    // ===== Methods =====

    // @TODO make a better toString method


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                ", target_type='" + target_type + '\'' +
                ", idCommentTarget=" + idCommentTarget +
                '}';
    }
}
