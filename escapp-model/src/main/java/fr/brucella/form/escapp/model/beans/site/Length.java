package fr.brucella.form.escapp.model.beans.site;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Business object which represents a Length.
 * A Length is a part of a Route.
 *
 * @author BRUCELLA2
 */
public class Length {

    // @TODO check messages and write it in resource bundle

    private Integer id;

    @NotNull(message = "La longueur ne peut être absente")
    private Integer length;

    @NotEmpty(message = "Une longueur doit avoir une cotation")
    @Size(min = 1, max = 2, message = "La cotation doit être composée de {max} caractères maximum")
    private String grade;

    @NotNull(message = "Une longueur doit avoir un nombre de point")
    private Integer pointsNb;

    @Size(min = 0, max = 10000, message = "La description d'une longueur doit être composée de {max} caractères maximum")
    private String description;

    @NotNull(message="Une longueur doit avoir une voie de référence")
    private Integer routeId;

    // ===== Constructor =====

    /**
     * Constructor
     */
    public Length(){

    }

    // ===== Getters =====

    public Integer getId() {
        return id;
    }

    public Integer getLength() {
        return length;
    }

    public String getGrade() {
        return grade;
    }

    public Integer getPointsNb() {
        return pointsNb;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRouteId() {
        return routeId;
    }

    // ===== Setters =====

    public void setId(Integer pId) {
        this.id = pId;
    }

    public void setLength(Integer pLength) {
        this.length = pLength;
    }

    public void setGrade(String pGrade) {
        this.grade = pGrade;
    }

    public void setPointsNb(Integer pPointsNb) {
        this.pointsNb = pPointsNb;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    public void setRouteId(Integer pRouteId) {
        this.routeId = pRouteId;
    }

    // ===== Methods =====

    // @TODO make a better toString method

    @Override
    public String toString() {
        return "Length{" +
                "id=" + id +
                ", length=" + length +
                ", grade='" + grade + '\'' +
                ", pointsNb=" + pointsNb +
                ", description='" + description + '\'' +
                ", routeId=" + routeId +
                '}';
    }
}
