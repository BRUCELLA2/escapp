package fr.brucella.form.escapp.model.beans.site;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Business object which represents a Sector Sector is a part of Site.
 *
 * @author BRUCELLA2
 */
public class Sector implements Serializable {


    private static final long serialVersionUID = -4544026296319665451L;


    /**
     * id of the {@link Sector}.
     *
     * @see #getId()
     * @see #setId(Integer)
     */
    private Integer           id;


    /**
     * Name of the {@link Sector}. Can't be empty and max size is 100 characters.
     *
     * @see #getName()
     * @see #setName(String)
     */
    @NotEmpty(message = "Un secteur doit avoir un nom")
    @Size(min = 1, max = 100, message = "Le nom d'un secteur doit contenir au maximum {max} caractères")
    private String            name;


    /**
     * Description of the {@link Sector}. Can be empty. Max size is 10 000 characters.
     *
     * @see #getDescription()
     * @see #setDescription(String)
     */
    @Size(min = 0, max = 10000, message = "La description d'un secteur doit contenir au maximum {max} caractères")
    private String            description;


    /**
     * id of the site to which the {@link Sector} belongs. Can't be null.
     *
     * @see #getSiteId()
     * @see #setSiteId(Integer)
     */
    @NotNull(message = "Un secteur doit avoir un identifiant de site associé")
    private Integer           siteId;


    // ===== Getters =====


    /**
     * Get the id of the {@link Sector}.
     *
     * @return the {@link Integer} id of the {@link Sector}.
     *
     * @see #id
     * @see #setId(Integer)
     */
    public Integer getId() {
        return this.id;
    }


    /**
     * Get the name of the {@link Sector}.
     *
     * @return the name of the {@link Sector}. Can't be empty. Max size is 100 characters.
     *
     * @see #name
     * @see #setName(String)
     */
    public String getName() {
        return this.name;
    }


    /**
     * Get the description of the {@link Sector}.
     *
     * @return the description of the {@link Sector}. Can be empty. Max size is 10 000 characters.
     *
     * @see #description
     * @see #setDescription(String)
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Get the id of the {@link Site} to which the {@link Sector} belongs.
     *
     * @return {@link Integer} id of the {@link Site} to which the {@link Sector} belongs. Can't be
     *         null.
     *
     * @see #siteId
     * @see #setSiteId(Integer)
     */
    public Integer getSiteId() {
        return this.siteId;
    }


    // ===== Setters =====


    /**
     * Set the id of the {@link Sector}.
     *
     * @param pId {@link Integer} id of the {@link Sector}.
     *
     * @see #id
     * @see #getId()
     */
    public void setId(Integer pId) {
        this.id = pId;
    }


    /**
     * Set the name of the {@link Sector}.
     *
     * @param pName the name of the {@link Sector}. Can't be empty. Max size is 100 characters.
     *
     * @see #name
     * @see #getName()
     */
    public void setName(String pName) {
        this.name = pName;
    }


    /**
     * Set the description of the {@link Sector}.
     *
     * @param pDescription the description of the {@link Sector}. Can be empty. Max size is 10 000
     *        characters.
     *
     * @see #description
     * @see #getDescription()
     */
    public void setDescription(String pDescription) {
        this.description = pDescription;
    }


    /**
     * Set the id of the {@link Site} to which the {@link Sector} belongs.
     *
     * @param pSiteId the {@link Integer} id of the {@link Site} to which the {@link Sector} belongs
     *        Can't be null.
     *
     * @see #siteId
     * @see #getSiteId()
     */
    public void setSiteId(Integer pSiteId) {
        this.siteId = pSiteId;
    }


    // ===== Methods =====


    @Override
    public String toString() {
        return "Sector{" + "id=" + this.id + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + ", siteId=" + this.siteId + '}';
    }
}
