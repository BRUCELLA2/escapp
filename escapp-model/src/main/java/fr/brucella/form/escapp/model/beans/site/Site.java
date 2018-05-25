package fr.brucella.form.escapp.model.beans.site;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import fr.brucella.form.escapp.model.search.SiteSearch;

/**
 * Business object which represents a Site
 *
 * @author BRUCELLA2
 */
public class Site implements Serializable {
    
    
    private static final long serialVersionUID = -6707683532861567867L;
    
    
    /**
     * id of the {@link Site}.
     * 
     * @see #getId()
     * @see #setId(Integer)
     */
    private Integer           id;
    
    
    /**
     * Name of the {@link Site}. Can't be empty and max size is 100 characters.
     *
     * @see #getName()
     * @see #setName(String)
     */
    @NotEmpty(message = "Un site doit avoir un nom.")
    @Size(min = 1, max = 100, message = "Le nom du site ne peut dépasser {max} caractères.")
    private String            name;
    
    
    /**
     * String representing the department code to which {@link Site} belongs. Can't be empty and size
     * need to be 3 characters. Ex : "034", "030", "02A"
     *
     * @see #getDepartment()
     * @see #setDepartment(String)
     * @see SiteSearch
     */
    @NotEmpty(message = "Un site doit avoir un numéro de département.")
    @Size(min = 3, max = 3, message = "Le numéro de département d'un site doit être constitué de 3 caractères.")
    private String            department;
    
    
    /**
     * Municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
     *
     * @see #getMunicipality()
     * @see #setMunicipality(String)
     */
    @NotEmpty(message = "La commune d'un site ne peut être vide.")
    @Size(min = 1, max = 100, message = "Le nom de la commune associée ne doit pas dépasser {max} caractères.")
    private String            municipality;
    
    
    /**
     * Description of the {@link Site}. Can be empty. Max size is 10 000 characters.
     *
     * @see #getDescription()
     * @see #setDescription(String)
     */
    @Size(min = 0, max = 10000, message = "La description d'un site ne peut dépasser {max} caractères.")
    private String            description;
    
    
    // ===== Getters =====
    
    
    /**
     * Get the id of the {@link Site}.
     *
     * @return the {@link Integer} id of the {@link Site}.
     *
     * @see #id
     * @see #setId(Integer)
     */
    public Integer getId() {
        return this.id;
    }
    
    
    /**
     * Get the name of the {@link Site}.
     *
     * @return the name of the {@link Site}. Can't be empty. Max size is 100 characters.
     *
     * @see #name
     * @see #setName(String)
     */
    public String getName() {
        return this.name;
    }
    
    
    /**
     * Get the {@link String} representing the department code to which {@link Site} belongs.
     *
     * @return the {@link String} representing the department code to which {@link Site} belongs. Can't
     *         be empty Size need to be 3 characters.
     *
     * @see #department
     * @see #setDepartment(String)
     */
    public String getDepartment() {
        return this.department;
    }
    
    
    /**
     * Get the municipality to which {@link Site} belongs.
     * 
     * @return the municipality to which {@link Site} belongs. Can't be empty. Max size is 100
     *         characters.
     *
     * @see #municipality
     * @see #setMunicipality(String)
     */
    public String getMunicipality() {
        return this.municipality;
    }
    
    
    /**
     * Get the description of the {@link Site}.
     *
     * @return the description of the {@link Site}. Can be empty. Max size is 10 000 characters.
     *
     * @see #description
     * @see #setDescription(String)
     */
    public String getDescription() {
        return this.description;
    }
    
    
    // ===== Setters =====
    
    
    /**
     * Set the id of the {@link Site}.
     *
     * @param pId {@link Integer} id of the {@link Site}.
     *
     * @see #id
     * @see #getId()
     */
    public void setId(Integer pId) {
        this.id = pId;
    }
    
    
    /**
     * Set the name of the {@link Site}.
     *
     * @param pName the name of the {@link Site}. Can't be empty. Max size is 100 characters.
     *
     * @see #name
     * @see #getName()
     */
    public void setName(String pName) {
        this.name = pName;
    }
    
    
    /**
     * Set the {@link String} representing the department code to which {@link Site} belongs.
     *
     * @param pDepartment the {@link String} representing the department code to which {@link Site}
     *        belongs. Can't be empty Size need to be 3 characters. Ex : "034", "030", "02A"
     *
     * @see #department
     * @see #getDepartment()
     */
    public void setDepartment(String pDepartment) {
        this.department = pDepartment;
    }
    
    
    /**
     * Set the municipality to which {@link Site} belongs.
     *
     * @param pMunicipality the municipality to which {@link Site} belongs. Can't be empty Max size is
     *        100 characters
     *
     * @see #municipality
     * @see #getMunicipality()
     */
    public void setMunicipality(String pMunicipality) {
        this.municipality = pMunicipality;
    }
    
    
    /**
     * Set the description of the {@link Site}.
     *
     * @param pDescription the description of the {@link Site}. Can be empty. Max size is 10 000
     *        characters.
     *
     * @see #description
     * @see #getDescription()
     */
    public void setDescription(String pDescription) {
        this.description = pDescription;
    }
    
    
    // ===== Methods =====
    
    @Override
    public String toString() {
        return "Site{" + "id=" + this.id + ", name='" + this.name + '\'' + ", department='" + this.department + '\'' + ", municipality='" + this.municipality
                + '\'' + ", description='" + this.description + '\'' + '}';
    }
}
