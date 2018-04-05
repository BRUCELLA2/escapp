package fr.brucella.form.escapp.model.beans.site;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Business object which represents a Sector
 * Sector is a part of Site.
 *
 * @author BRUCELLA2
 */
public class Sector {

    // @TODO check messages and write it in resource bundle

    private Integer id;

    @NotEmpty(message = "Un secteur doit avoir un nom")
    @Size(min = 1, max = 100, message = "Le nom d'un secteur doit contenir au maximum {max} caractères")
    private String name;

    @Size(min = 0, max = 10000, message = "La description d'un secteur doit contenir au maximum {max} caractères")
    private String description;

    @NotNull(message = "Un secteur doit avoir un identifiant de site associé")
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
