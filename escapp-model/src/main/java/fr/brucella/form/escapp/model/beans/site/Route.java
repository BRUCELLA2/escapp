package fr.brucella.form.escapp.model.beans.site;

/**
 * Business object which represents a Route.
 * Route is a part of a Sector.
 *
 * @author BRUCELLA2
 */
public class Route {
    // @TODO add JSR 380 validation tags

    private Integer id;
    private String name;
    private String grade;
    private Integer SectorId;
    private Integer pointsNb;
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
