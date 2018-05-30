package fr.brucella.form.escapp.model.beans.site;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Business object which represents a Route. Route is a part of a Sector.
 *
 * @author BRUCELLA2
 */
public class Route implements Serializable {


    private static final long serialVersionUID = -7505857619427747573L;


    /**
     * id of the {@link Route}.
     *
     * @see #getId()
     * @see #setId(Integer)
     */
    private Integer           id;


    /**
     * Name of the {@link Route}. Can't be empty and max size is 100 characters.
     *
     * @see #getName()
     * @see #setName(String)
     */
    @NotEmpty(message = "Une voie doit avoir un nom")
    @Size(min = 1, max = 100, message = "La longueur du nom d'une voie est de {max} caractères maximum")
    private String            name;


    /**
     * Grade of the {@link Route}. The grade is a {@link String} which represents the difficulty. Can't
     * be empty and max size is 2 characters.
     *
     * @see #getGrade()
     * @see #setGrade(String)
     */
    @NotEmpty(message = "Une voie doit avoir une cotation")
    @Size(min = 1, max = 2, message = "La cotation doit être constituée de {max} caractères maximum")
    private String            grade;


    /**
     * id of the {@link Sector} to which the {@link Route} belongs. Can't be null.
     *
     * @see #getSectorId()
     * @see #setSectorId(Integer)
     */
    @NotNull(message = "Une voie doit avoir un secteur de référence")
    private Integer           sectorId;


    /**
     * Number of points for this {@link Route}. Can't be null.
     *
     * @see #getPointsNb()
     * @see #setPointsNb(Integer)
     */
    @NotNull(message = "Le nombre de point doit être mentionné explicitement, 0 s'il y en a pas")
    private Integer           pointsNb;


    /**
     * Description of the {@link Route}. Can be empty. Max size is 10 000 characters.
     *
     * @see #getDescription()
     * @see #setDescription(String)
     */
    @Size(min = 0, max = 10000, message = "La description d'une voie ne peut pas dépasser {max} caractères maximum")
    private String            description;


    // ===== Getters =====


    /**
     * Get the id of the {@link Route}.
     *
     * @return the {@link Integer} id of the {@link Route}.
     *
     * @see #id
     * @see #setId(Integer)
     */
    public Integer getId() {
        return this.id;
    }


    /**
     * Get the name of the {@link Route}.
     *
     * @return the name of the {@link Route}. Can't be empty. Max size is 100 characters.
     *
     * @see #name
     * @see #setName(String)
     */
    public String getName() {
        return this.name;
    }


    /**
     * Get the String which represents the grade of the {@link Route}.
     *
     * @return the {@link String} which represents the grade of the {{@link Route}. Can't be empty. Max
     *         size is 2 characters.
     *
     * @see #grade
     * @see #setGrade(String)
     */
    public String getGrade() {
        return this.grade;
    }


    /**
     * Get the id of the {@link Sector} to which the {@link Route} belongs.
     *
     * @return {@link Integer} id of the {@link Sector} to which the {@link Route} belongs. Can't be
     *         null.
     *
     * @see Route#sectorId
     * @see #setSectorId(Integer)
     */
    public Integer getSectorId() {
        return this.sectorId;
    }


    /**
     * Get the number of points of the {@link Route}.
     *
     * @return the number of points of the {@link Route}. Can't be null.
     *
     * @see #pointsNb
     * @see #setPointsNb(Integer)
     */
    public Integer getPointsNb() {
        return this.pointsNb;
    }
    
    
    /**
     * Get the description of the {@link Route}.
     *
     * @return the description of the {@link Route}. Can be empty. Max size is 10 000 characters.
     *
     * @see #description
     * @see #setDescription(String)
     */
    public String getDescription() {
        return this.description;
    }


    // ===== Setters =====


    /**
     * Set the id of the {@link Route}.
     *
     * @param pId {@link Integer} id of the {@link Route}.
     *
     * @see #id
     * @see #getId()
     */
    public void setId(Integer pId) {
        this.id = pId;
    }


    /**
     * Set the name of the {@link Route}.
     *
     * @param pName the name of the {@link Route}. Can't be empty. Max size is 100 characters.
     *
     * @see #name
     * @see #getName()
     */
    public void setName(String pName) {
        this.name = pName;
    }


    /**
     * Set the String which represents the grade of the {@link Route}.
     *
     * @param pGrade the String which represents the grade of the {@link Route}. Can't be empty. Max
     *        size is 2 characters.
     *
     * @see #grade
     * @see #getGrade()
     */
    public void setGrade(String pGrade) {
        this.grade = pGrade;
    }


    /**
     * Set the id of the {@link Sector} to which the {@link Route} belongs.
     *
     * @param pSectorId the {@link Integer} id of the {@link Sector} to which the {@link Sector}
     *        belongs. Can't be null.
     * @see #sectorId
     * @see #getSectorId()
     */
    public void setSectorId(Integer pSectorId) {
        this.sectorId = pSectorId;
    }


    /**
     * Set the number of points of the {@link Route}.
     *
     * @param pPointsNb the number of points of the {@link Route}. Can't be null.
     *
     * @see #pointsNb
     * @see #getPointsNb()
     */
    public void setPointsNb(Integer pPointsNb) {
        this.pointsNb = pPointsNb;
    }


    /**
     * Set the description of the {@link Route}.
     *
     * @param pDescription the description of the {@link Route}. Can be empty. Max size is 10 000
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
        return "Route{" + "id=" + this.id + ", name='" + this.name + '\'' + ", grade='" + this.grade + '\'' + ", sectorId=" + this.sectorId + ", pointsNb="
                + this.pointsNb + ", description='" + this.description + '\'' + '}';
    }
}
