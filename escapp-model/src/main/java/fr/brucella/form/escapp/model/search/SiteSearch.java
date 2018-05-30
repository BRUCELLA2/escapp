package fr.brucella.form.escapp.model.search;

import java.io.Serializable;

import javax.validation.constraints.Size;

import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.beans.site.Site;

/**
 * Object which represents site search criteria.
 *
 * @author BRUCELLA2
 */
public class SiteSearch implements Serializable {
  
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 1243057213802380805L;
  
  
  /**
   * String representing the department code to which {@link Site} searched belongs. Size need to be 3
   * characters. Ex : "034", "030", "02A"
   *
   * @see #getDepartmentSite()
   * @see #setDepartmentSite(String)
   * @see Site
   */
  @Size(min = 0, max = 3, message = "Le numéro de département d'un site doit être constitué de 3 caractères.")
  private String            departmentSite;
  
  
  /**
   * Municipality to which {@link Site} searched belongs. max size is 100 characters.
   *
   * @see #getMunicipalitySite()
   * @see #setMunicipalitySite(String)
   * @see Site
   */
  @Size(min = 0, max = 100, message = "Le nom de la commune d'un site ne doit pas dépasser {max} caractères.")
  private String            municipalitySite;
  
  
  /**
   * Min grade of a {@link Route}. The min grade is a {@link String} which represents the difficulty
   * minimum for the sites searched. Max size is 2 characters.
   *
   * @see #getMinGradeRoute()
   * @see #setMinGradeRoute(String)
   * @see Route
   */
  @Size(min = 0, max = 2, message = "La cotation minimum doit être constituée d'au plus {max} caractères.")
  private String            minGradeRoute;
  
  /**
   * Max grade of a {@link Route}. The max grade is a {@link String} which represents the difficulty
   * maximum for the sites searched. Max size is 2 characters.
   *
   * @see #getMaxGradeRoute()
   * @see #setMaxGradeRoute(String)
   * @see Route
   */
  @Size(min = 0, max = 2, message = "La cotation maximum doit être constituée d'au plus {max} caractères.")
  private String            maxGradeRoute;
  
  
  // ===== Getters =====
  
  /**
   * Get the {@link String} representing the department code to which {@link Site} searched belongs.
   *
   * @return the {@link String} representing the department code to which {@link Site} searched
   *         belongs. Size need to be 3 characters.
   *
   * @see #departmentSite
   * @see #setDepartmentSite(String)
   * @see Site
   */
  public String getDepartmentSite() {
    return this.departmentSite;
  }
  
  /**
   * Get the municipality to which {@link Site} searched belongs.
   *
   * @return the municipality to which {@link Site} searched belongs. Max size is 100 characters.
   *
   * @see #municipalitySite
   * @see #setMunicipalitySite(String)
   * @see Site
   */
  public String getMunicipalitySite() {
    return this.municipalitySite;
  }
  
  /**
   * Get the String which represents the minimum grade of the {@link Route} for the sites searched.
   *
   * @return the {@link String} which represents the minimum grade of the {{@link Route} for the sites
   *         searched. Max size is 2 characters.
   *
   * @see #minGradeRoute
   * @see #setMinGradeRoute(String)
   * @see Route
   */
  public String getMinGradeRoute() {
    return this.minGradeRoute;
  }
  
  /**
   * Get the String which represents the maximum grade of the {@link Route} for the sites searched.
   *
   * @return the {@link String} which represents the maximum grade of the {{@link Route} for the sites
   *         searched. Max size is 2 characters.
   *
   * @see #maxGradeRoute
   * @see #setMaxGradeRoute(String)
   * @see Route
   */
  public String getMaxGradeRoute() {
    return this.maxGradeRoute;
  }
  
  
  // ===== Setters =====
  
  /**
   * Set the {@link String} representing the department code to which {@link Site} searched belongs.
   *
   * @param departmentSite the {@link String} representing the department code to which {@link Site}
   *        searched belongs. Size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @see #departmentSite
   * @see #getDepartmentSite()
   * @see Site
   */
  public void setDepartmentSite(final String departmentSite) {
    this.departmentSite = departmentSite;
  }
  
  /**
   * Set the municipality to which {@link Site} searched belongs.
   *
   * @param municipalitySite the municipality to which {@link Site} searched belongs. Max size is 100
   *        characters
   *
   * @see #municipalitySite
   * @see #getMunicipalitySite()
   * @see Site
   */
  public void setMunicipalitySite(final String municipalitySite) {
    this.municipalitySite = municipalitySite;
  }
  
  /**
   * Set the String which represents the minimum grade of the {@link Route} for the site searched.
   *
   * @param minGradeRoute the String which represents the minimum grade of the {@link Route} for the
   *        site searched. Max size is 2 characters.
   *
   * @see #minGradeRoute
   * @see #getMinGradeRoute()
   * @see Route
   */
  public void setMinGradeRoute(final String minGradeRoute) {
    this.minGradeRoute = minGradeRoute;
  }
  
  /**
   * Set the String which represents the maximum grade of the {@link Route} for the site searched.
   *
   * @param maxGradeRoute the String which represents the maximum grade of the {@link Route} for the
   *        site searched. Max size is 2 characters.
   *
   * @see #maxGradeRoute
   * @see #getMaxGradeRoute()
   * @see Route
   */
  public void setMaxGradeRoute(final String maxGradeRoute) {
    this.maxGradeRoute = maxGradeRoute;
  }
  
  // ===== Methods =====
  
  @Override
  public String toString() {
    return "SiteSearch [departmentSite=" + this.departmentSite + ", municipalitySite=" + this.municipalitySite + ", minGradeRoute=" + this.minGradeRoute
        + ", maxGradeRoute=" + this.maxGradeRoute + "]";
  }
  
  
}
