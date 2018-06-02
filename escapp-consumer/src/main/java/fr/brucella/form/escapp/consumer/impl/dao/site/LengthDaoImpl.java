package fr.brucella.form.escapp.consumer.impl.dao.site;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.LengthRM;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Length Data Access Object.
 *
 * @author BRUCELLA2
 */
@Component
public class LengthDaoImpl extends AbstractDao implements LengthDao {
  
  /**
   * Length DAO logger.
   */
  private static final Log LOG = LogFactory.getLog(LengthDaoImpl.class);
  
  /**
   * @see LengthDao#getLength(Integer)
   */
  @Override
  public Length getLength(final Integer lengthId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM length WHERE id = :id";
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", lengthId);
    
    final RowMapper<Length> rowMapper = new LengthRM();
    
    try {
      
      return this.getNamedJdbcTemplate().queryForObject(sql, params, rowMapper);
      
    } catch (EmptyResultDataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + lengthId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException("La longueur demandée n'a pas été trouvée", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + lengthId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see LengthDao#getLengthsList(Integer)
   */
  @Override
  public List<Length> getLengthsList(final Integer routeId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM length WHERE route_id = :routeId";
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("routeId", routeId);
    
    final RowMapper<Length> rowMapper = new LengthRM();
    
    try {
      
      final List<Length> lengthsList = this.getNamedJdbcTemplate().query(sql, params, rowMapper);
      if (lengthsList.isEmpty()) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("id = " + routeId);
        }
        throw new NotFoundException("Aucune longueur n'a été trouvée.");
      }
      else {
        return lengthsList;
      }
      
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + routeId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see LengthDao#updateLength(Length)
   */
  @Override
  public void updateLength(final Length length) throws TechnicalException, NotFoundException {
    
    final String sql =
        "UPDATE length SET length = :length, grade = :grade, points_nb = :pointsNb, description = :description, route_id = :routeId WHERE id = :id";
    final SqlParameterSource params = new BeanPropertySqlParameterSource(length);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("Length = " + length.toString());
        }
        throw new NotFoundException("La longueur à modifier n'a pas été trouvée. La mise à jour n'a pas été faite.");
      }
      
    } catch (DataIntegrityViolationException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Length = " + length.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Les données n'étant pas conformes, la mise à jour de la longueur n'a pu être réalisée.", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Length = " + length.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see LengthDao#insertLength(Length)
   */
  @Override
  public int insertLength(final Length length) throws TechnicalException {
    
    final String sql =
        "INSERT INTO length (id, length, grade, points_nb, description, route_id) VALUES (DEFAULT, :length, :grade, :pointsNb, :description, :routeId)";
    
    final KeyHolder keyHolder = new GeneratedKeyHolder();
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(length);
    
    try {
      
      this.getNamedJdbcTemplate().update(sql, params, keyHolder, new String[] {"id"});
      return keyHolder.getKey().intValue();
      
    } catch (DuplicateKeyException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Length = " + length.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Une longueur existe déjà avec cet identifiant", exception);
    } catch (DataIntegrityViolationException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Length = " + length.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Les données n'étant pas conformes, la création de la longueur n'a pu être réalisée", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Length = " + length.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see LengthDao#deleteLength(Integer)
   */
  @Override
  public void deleteLength(final Integer lengthId) throws TechnicalException, NotFoundException {
    
    final String sql = "DELETE FROM lenght WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", lengthId);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("id = " + lengthId);
        }
        throw new NotFoundException("La longueur à supprimer n'a pas été trouvée. La suppression n'a pas été réalisée.");
      }
      
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + lengthId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
}
