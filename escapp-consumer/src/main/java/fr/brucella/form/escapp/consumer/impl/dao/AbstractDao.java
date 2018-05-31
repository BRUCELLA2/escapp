package fr.brucella.form.escapp.consumer.impl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Abstract class inherited by all Data Access Object implementations.
 *
 * @author BRUCELLA
 */
@Component
public abstract class AbstractDao {
  
  /**
   * Message for Data access ressource failure.
   */
  protected static final String      DATA_ACCESS_RESOURCE_FAILURE = "La connexion à la base de données a échoué.";
  
  /**
   * Message for data access permission denied.
   */
  protected static final String      PERMISSION_DENIED            = "Un problème de droits d'accès à la base de données a été détecté.";
  
  /**
   * Message for data access exception.
   */
  protected static final String      DATA_ACCESS_EXCEPTION        = "Un problème technique au niveau de la base de données est survenu.";
  
  /**
   * jdbc template.
   *
   * @see #getJdbcTemplate()
   * @see #setJdbcTemplate(JdbcTemplate)
   */
  @Autowired
  private JdbcTemplate               jdbcTemplate;
  
  /**
   * jdbc template with named parameter template.
   *
   * @see #getNamedJdbcTemplate()
   * @see #setNamedJdbcTemplate(NamedParameterJdbcTemplate)
   */
  @Autowired
  private NamedParameterJdbcTemplate namedJdbcTemplate;
  
  
  // ----- Getters -----
  
  /**
   * Get the JdbcTemplate.
   *
   * @return the JdbcTemplate
   *
   * @see #jdbcTemplate
   * @see #setJdbcTemplate(JdbcTemplate)
   */
  public JdbcTemplate getJdbcTemplate() {
    return this.jdbcTemplate;
  }
  
  /**
   * Get the NamedJdbcTemplate.
   *
   * @return the NamedJdbcTemplate
   *
   * @see #namedJdbcTemplate
   * @see #setNamedJdbcTemplate(NamedParameterJdbcTemplate)
   */
  public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
    return this.namedJdbcTemplate;
  }
  
  // ----- Setters -----
  
  /**
   * Set the JdbcTemplate.
   *
   * @param jdbcTemplate the JdbcTemplate.
   *
   * @see #jdbcTemplate
   * @see #getJdbcTemplate()
   */
  public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
  
  /**
   * Set the NamedJdbcTemplate.
   *
   * @param namedJdbcTemplate the NamedJdbcTemplate.
   *
   * @see #namedJdbcTemplate
   * @see #getNamedJdbcTemplate()
   */
  public void setNamedJdbcTemplate(final NamedParameterJdbcTemplate namedJdbcTemplate) {
    this.namedJdbcTemplate = namedJdbcTemplate;
  }
}
