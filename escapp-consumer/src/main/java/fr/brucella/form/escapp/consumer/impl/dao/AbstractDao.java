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
  
  protected static final String      DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION          = "La connexion à la base de données a échoué.";
  protected static final String      PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE = "Un problème de droits d'accès à la base de données a été détecté.";
  protected static final String      DATA_ACCESS_EXCEPTION_MESSAGE                   = "Un problème technique au niveau de la base de données est survenu.";
  
  @Autowired
  private JdbcTemplate               vJdbcTemplate;
  
  @Autowired
  private NamedParameterJdbcTemplate vNamedJdbcTemplate;
  
  /**
   * Get the JdbcTemplate
   *
   * @return the JdbcTemplate
   */
  public JdbcTemplate getJdbcTemplate() {
    return this.vJdbcTemplate;
  }
  
  /**
   * Get the NamedJdbcTemplate
   *
   * @return the NamedJdbcTemplate
   */
  public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
    return this.vNamedJdbcTemplate;
  }
}
