package fr.brucella.form.escapp.model.beans.site;

/**
 * Business object which represents a Site
 *
 * @author BRUCELLA2
 */
public class Site {
    // @TODO add JSR 380 validation tags

    private Integer id;
    private String name;
    private String department;
    private String municipality;
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
