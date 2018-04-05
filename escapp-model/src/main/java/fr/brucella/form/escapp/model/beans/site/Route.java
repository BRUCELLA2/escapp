package fr.brucella.form.escapp.model.beans.site;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Business object which represents a Route.
 * Route is a part of a Sector.
 *
 * @author BRUCELLA2
 */
public class Route {
    // @TODO check messages and write it in resource bundle

    private Integer id;

    @NotEmpty(message = "Une voie doit avoir un nom")
    @Size(min = 1, max = 100, message = "La longueur du nom d'une voie est de {max} caractères maximum")
    private String name;

    @NotEmpty(message = "Une voie doit avoir une cotation")
    @Size(min = 1, max = 2, message = "La cotation doit être constituée de {max} caractères maximum")
    private String grade;

    @NotNull(message = "Une voie doit avoir un secteur de référence")
    private Integer SectorId;

    @NotNull(message = "Le nombre de point doit être mentionné explicitement, 0 s'il y en a pas")
    private Integer pointsNb;

    @Size(min = 0, max = 10000, message = "La description d'une voie ne peut pas dépasser {max} caractères maximum")
    private String description;

    // ===== Constructor =====
    /**
     * Constructor
     */
    public Route(){

    }

    // ===== Getters =====

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public Integer getSectorId() {
        return SectorId;
    }

    public Integer getPointsNb() {
        return pointsNb;
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

    public void setGrade(String pGrade) {
        this.grade = pGrade;
    }

    public void setSectorId(Integer pSectorId) {
        SectorId = pSectorId;
    }

    public void setPointsNb(Integer pPointsNb) {
        this.pointsNb = pPointsNb;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    // ===== Methods =====

    // @TODO make a better toString method

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", SectorId=" + SectorId +
                ", pointsNb=" + pointsNb +
                ", description='" + description + '\'' +
                '}';
    }
}
