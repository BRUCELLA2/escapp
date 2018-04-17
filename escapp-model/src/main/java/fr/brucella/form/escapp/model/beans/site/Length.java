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

	/**
	 * id of the {@link Length}.
	 * 
	 * @see #getId()
	 * @see #setId(Integer)
	 */
    private Integer id;

    
    /**
     * Length in meters of the {@link Length}.
     * Can't be null.
     * 
     * @see #getLength()
     * @see #setLength(Integer)
     */
    @NotNull(message = "La longueur ne peut être absente")
    private Integer length;

    
    /**
     * Grade of the {@link Length}. The grade is a {@link String} which represents the difficulty.
     * Can't be empty and max size is 2 characters.
     * 
     * @see #getGrade()
     * @see #setGrade(String)
     */
    @NotEmpty(message = "Une longueur doit avoir une cotation")
    @Size(min = 1, max = 2, message = "La cotation doit être composée de {max} caractères maximum")
    private String grade;

    
    /**
     * Number of points for this {@link Length}.
     * Can't be null.
     * 
     * @see #getPointsNb()
     * @see #setPointsNb(Integer)
     */
    @NotNull(message = "Une longueur doit avoir un nombre de point")
    private Integer pointsNb;

    
    /**
     * Description of the {@link Length}.
     * Can be empty.
     * Max size is 10 000 characters.
     * 
     * @see #getDescription()
     * @see #setDescription(String)
     */
    @Size(min = 0, max = 10000, message = "La description d'une longueur doit être composée de {max} caractères maximum")
    private String description;

    
    /**
     * Id of the {@link Route} to which the {@link Length} belongs.
     * Can't be null.
     * 
     * @see #getRouteId()
     * @see #setRouteId(Integer)
     */
    @NotNull(message="Une longueur doit avoir une voie de référence")
    private Integer routeId;

    
    // ===== Constructor =====

    
    /**
     * Constructor
     */
    public Length(){

    }

    
    // ===== Getters =====

    /**
     * Get the id of the {@link Length}.
     * 
     * @return the {@link Integer} id of the {@link Length}.
     * 
     * @see #id
     * @see #setId(Integer)
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the length in meters of the {@link Length}.
     * 
     * @return the length in meters of the {@link Length}.
     * 			Can't be null
     * 
     * @see #length
     * @see #setLength(Integer)
     */
    public Integer getLength() {
        return length;
    }

    
    /**
     * Get the String which represents the grade of the {@link Length}.
     * 
     * @return the {@link String} which represents the grade of the {@link Length}.
     * 			Can't be empty.
     * 			Max size is 2 characters.
     * 
     * @see #grade
     * @see #setGrade(String)
     */
    public String getGrade() {
        return grade;
    }

    
    /**
     * Get the number of points of the {@link Length}.
     * 
     * @return the number of points of the {@link Length}.
     * 			Can't be null.
     * 
     * @see #pointsNb
     * @see #setPointsNb(Integer)
     */
    public Integer getPointsNb() {
        return pointsNb;
    }

    
    /**
     * Get the description of the {@link Length}.
     * 
     * @return the description of the {@link Length}.
     * 			Can be empty.
     * 			Max size is 10 000 characters.
     * 
     * @see #description
     * @see #setDescription(String)
     */
    public String getDescription() {
        return description;
    }
    

    /**
     * Get the id of the {@link Route} to which the {@link Length} belongs.
     * 
     * @return {@link Integer} id of the {@link Route} to which the {@link Length} belongs.
     * 			Can't be null.
     * 
     * @see #routeId
     * @see #setRouteId(Integer)
     */
    public Integer getRouteId() {
        return routeId;
    }

    
    // ===== Setters =====

    
    /**
     * Set the id of the {@link Length}.
     * 
     * @param pId {@link Integer} id of the {@link Length}.
     * 
     * @see #id
     * @see #getId()
     */
    public void setId(Integer pId) {
        this.id = pId;
    }

    
    /**
     * Set the length in meters of the {@link Length}. 
     * 
     * @param pLength the length in meters of the {@link Length}. 
     * 				  Can't be null.
     * 
     * @see #length
     * @see #getLength()
     */
    public void setLength(Integer pLength) {
        this.length = pLength;
    }
    

    /**
     * Set the String which represents the grade of the {@link Length}. 
     * 
     * @param pGrade the String which represents the grade of the {@link Length}.
     * 				 Can't be empty.
     * 				 Max size is 2 characters.
     * 
     *  @see #grade
     *  @see #getGrade()
     */
    public void setGrade(String pGrade) {
        this.grade = pGrade;
    }

    
    /**
     * Set the number of points of the {@link Length}. 
     * 
     * @param pPointsNb the number of points of the {@link Length}. 
     * 					Can't be null.
     * 
     * @see #pointsNb
     * @see #getPointsNb()
     */
    public void setPointsNb(Integer pPointsNb) {
        this.pointsNb = pPointsNb;
    }
    

    /**
     * Set the description of the {@link Length}. 
     * 
     * @param pDescription the description of the {@link Length}. 
     * 					   Can be empty.
     * 					   Max size is 10 000 characters.
     * 
     * @see #description
     * @see #getDescription()
     */
    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    
    /**
     * Set the id of the {@link Route} to which the {@link Length} belongs.
     * 
     * @param pRouteId the {@link Integer} id of the {@link Route} to which the {@link Length} belongs.
     * 
     * @see #routeId
     * @see #getRouteId()
     */
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
