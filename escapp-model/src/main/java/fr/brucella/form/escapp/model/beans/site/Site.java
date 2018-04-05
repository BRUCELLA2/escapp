package fr.brucella.form.escapp.model.beans.site;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Business object which represents a Site
 *
 * @author BRUCELLA2
 */
public class Site {

    // @TODO check messages and write it in resource bundle

    private Integer id;

    @NotEmpty(message = "Un site doit avoir un nom")
    @Size(min = 1, max = 100, message = "Le nom du site ne peut dépasser {max} caractères.")
    private String name;

    @NotEmpty(message = "Un site doit avoir un numéro de département")
    @Size(min = 3, max = 3, message = "Le numéro de département d'un site doit être constitué de 2 caractères.")
    private String department;

    @NotEmpty(message = "La commune d'un site ne peut être vide")
    @Size(min = 1, max = 100, message = "Le nom de la commune associée ne doit pas dépasser {max} caractères.")
    private String municipality;

    @Size(min = 0, max = 10000, message = "La description d'un site ne peut dépasser {max} caractères.")
    private String description;

    // ===== Constructor =====
    /**
     * Constructor
     */
    public Site(){

    }

    // ===== Getters =====

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getDescription() {
        return description;
    }

    // ===== Setters =====

    public void setId(Integer pId) {
        this.id = pId;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public void setDepartment(String pDepartment) {
        this.department = pDepartment;
    }

    public void setMunicipality(String pMunicipality) {
        this.municipality = pMunicipality;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    // ===== Methods =====

    // @TODO make a better toString method

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", municipality='" + municipality + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
