package fr.brucella.form.escapp.model.beans.site;

/**
 * Business object which represents a Length.
 * A Length is a part of a Route.
 *
 * @author BRUCELLA2
 */
public class Length {
    // @TODO add JSR 380 validation tags

    private Integer id;
    private Integer length;
    private String grade;
    private Integer pointsNb;
    private String description;
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
