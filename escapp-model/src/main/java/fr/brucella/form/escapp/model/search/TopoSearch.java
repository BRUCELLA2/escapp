package fr.brucella.form.escapp.model.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.brucella.form.escapp.model.beans.topo.Topo;

/**
 * Object which represents topo search criteria.
 *
 * @author BRUCELLA2
 */
public class TopoSearch implements Serializable {
  
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -8234667938852361245L;
  
  
  /**
   * String representing the department code associated to the {@link Topo} searched. Size need to be
   * 3 characters. Ex : "034", "030", "02A"
   *
   * @see #getDepartmentTopo()
   * @see #setDepartmentTopo(String)
   * @see Topo
   */
  @Size(min = 0, max = 3, message = "Le numéro du département doit être sur deux caractères")
  private String            departmentTopo;
  
  
  /**
   * Municipality associated to the {@link Topo} searched. Max size is 100 characters.
   *
   * @see #getMunicipalityTopo()
   * @see #setMunicipalityTopo(String)
   * @see Topo
   */
  @Size(min = 0, max = 100, message = "Le nom de la commune d'un topo ne doit pas dépasser {max} caractères.")
  private String            municipalityTopo;
  
  /**
   * Boolean indicating if the {@link Topo} searched need to be available for a borrow. Can't be null
   *
   * @see #getAvailableTopo()
   * @see #setAvailableTopo(Boolean)
   */
  @NotNull(message = "Il est nécessaire d'indiquer si le topo doit être disponible ou non")
  private Boolean           availableTopo;
  
  
  // ===== Getters =====
  
  /**
   * Get the {@link String} representing the department code associated to the {@link Topo} searched.
   *
   * @return the {@link String} representing the department code associated to the {@link Topo}
   *         searched. Size need to be 3 characters.
   *
   * @see #departmentTopo
   * @see #setDepartmentTopo(String)
   * @see Topo
   */
  public String getDepartmentTopo() {
    return this.departmentTopo;
  }
  
  
  /**
   * Get the municipality associated to the {@link Topo} searched.
   *
   * @return the municipality associated to the {@link Topo} searched. Max size is 100 characters.
   *
   * @see #municipalityTopo
   * @see #setMunicipalityTopo(String)
   * @see Topo
   */
  public String getMunicipalityTopo() {
    return this.municipalityTopo;
  }
  
  /**
   * Indicate if the {@link Topo} searched need to be available for a borrow.
   *
   * @return true if the {@link Topo} searched need to be available, false otherwise. Can't be null
   *
   * @see #availableTopo
   * @see #setAvailableTopo(Boolean)
   */
  public Boolean getAvailableTopo() {
    return this.availableTopo;
  }
  
  
  // ===== Setters =====
  
  /**
   * Set the {@link String} representing the department code associated to {@link Topo} searched.
   *
   * @param departmentTopo the {@link String} representing the department code associated to
   *        {@link Topo} searched. Size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @see #departmentTopo
   * @see #getDepartmentTopo()
   * @see Topo
   */
  public void setDepartmentTopo(final String departmentTopo) {
    this.departmentTopo = departmentTopo;
  }
  
  /**
   * Set the municipality associated to the {@link Topo} searched.
   *
   * @param municipalityTopo the municipality associated to the {@link Topo} searched. Max size is
   *        100 characters
   *
   * @see #municipalityTopo
   * @see #getMunicipalityTopo()
   * @see Topo
   */
  public void setMunicipalityTopo(final String municipalityTopo) {
    this.municipalityTopo = municipalityTopo;
  }
  
  /**
   * Set if the {@link Topo} is available.
   *
   * @param availableTopo true if the {@link Topo} is available to be borrow, false otherwise. Can't
   *        be null
   *
   * @see #availableTopo
   * @see #getAvailableTopo()
   */
  public void setAvailableTopo(final Boolean availableTopo) {
    this.availableTopo = availableTopo;
  }
  
  
  // ===== Methods =====
  
  @Override
  public String toString() {
    return "TopoSearch [departmentTopo=" + this.departmentTopo + ", municipalityTopo=" + this.municipalityTopo + ", availableTopo=" + this.availableTopo + "]";
  }
  
}
