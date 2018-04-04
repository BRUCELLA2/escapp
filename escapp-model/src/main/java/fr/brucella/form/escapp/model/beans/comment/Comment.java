package fr.brucella.form.escapp.model.beans.comment;

/**
 * Business object which represents a Comment.
 *
 * @author BRUCELLA2
 */
public class Comment {
    // @TODO add JSR 380 validation tags

    private Integer id;
    private String description;
    private Integer userId;
    private Integer siteId;

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

    public String getDescription() {
        return description;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    // ===== Setters =====

    public void setId(Integer pId) {
        this.id = pId;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    public void setUserId(Integer pUserId) {
        this.userId = pUserId;
    }

    public void setSiteId(Integer pSiteId) {
        this.siteId = pSiteId;
    }

    // ===== Methods =====

    // @TODO make a better toString method

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", siteId=" + siteId +
                '}';
    }
}
