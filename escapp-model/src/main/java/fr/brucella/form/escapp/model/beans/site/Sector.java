package fr.brucella.form.escapp.model.beans.site;

/**
 * Business object which represents a Sector
 * Sector is a part of Site.
 *
 * @author BRUCELLA2
 */
public class Sector {
    // @TODO add JSR 380 validation tags

    private Integer id;
    private String name;
    private String description;
    private Integer siteId;

    // ===== Constructor =====
    /**
     * Constructor
     */
    public Sector(){

    }

    // ===== Getters =====

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getSiteId() {
        return siteId;
    }

    // ===== Setters =====

    public void setId(Integer pId) {
        this.id = pId;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    public void setSiteId(Integer pSiteId) {
        this.siteId = pSiteId;
    }

    // ===== Methods =====

    // @TODO make a better toString method

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", siteId=" + siteId +
                '}';
    }
}
