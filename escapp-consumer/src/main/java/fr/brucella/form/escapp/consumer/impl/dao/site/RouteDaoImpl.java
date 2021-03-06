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

import fr.brucella.form.escapp.consumer.contract.dao.site.RouteDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.RouteRM;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Route Data Access Object.
 *
 * @author BRUCELLA2
 */
@Component
public class RouteDaoImpl extends AbstractDao implements RouteDao {
  
  /**
   * Route DAO logger.
   */
  private static final Log LOG = LogFactory.getLog(RouteDaoImpl.class);
  
  /**
   * @see RouteDao#getRoute(Integer)
   */
  @Override
  public Route getRoute(final Integer routeId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM route WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", routeId);
    
    final RowMapper<Route> rowMapper = new RouteRM();
    
    try {
      
      return this.getNamedJdbcTemplate().queryForObject(sql, params, rowMapper);
      
    } catch (EmptyResultDataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + routeId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException("La voie demandée n'a pas été trouvée", exception);
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
   * @see RouteDao#getRoutesList(Integer)
   */
  @Override
  public List<Route> getRoutesList(final Integer sectorId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM route WHERE sector_id = :sectorId";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("sectorId", sectorId);
    
    final RowMapper<Route> rowMapper = new RouteRM();
    
    try {
      
      final List<Route> routesList = this.getNamedJdbcTemplate().query(sql, params, rowMapper);
      if (routesList.isEmpty()) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("id = " + sectorId);
        }
        throw new NotFoundException("Aucune voie n'a été trouvée.");
      }
      else {
        return routesList;
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
        LOG.debug("id = " + sectorId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see RouteDao#updateRoute(Route)
   */
  @Override
  public void updateRoute(final Route route) throws TechnicalException, NotFoundException {
    
    final String sql = "UPDATE route SET sector_id = :sectorId, name = :name, grade = :grade, points_nb = :pointsNb, description = :description WHERE id = :id";
    final SqlParameterSource params = new BeanPropertySqlParameterSource(route);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("Route = " + route.toString());
        }
        throw new NotFoundException("La voie à modifier n'a pas été trouvée. La mise à jour n'a pas été faite.");
      }
      
    } catch (DataIntegrityViolationException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Route = " + route.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Les données n'étant pas conformes, la mise à jour de la voie n'a pu être réalisée.", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Route = " + route.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see RouteDao#insertRoute(Route)
   */
  @Override
  public int insertRoute(final Route route) throws TechnicalException {
    
    final String sql =
        "INSERT INTO route (id, sector_id, name, grade, points_nb, description) VALUES (DEFAULT, :sectorId, :name, :grade, :pointsNb, :description)";
    
    final KeyHolder keyHolder = new GeneratedKeyHolder();
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(route);
    
    try {
      
      this.getNamedJdbcTemplate().update(sql, params, keyHolder, new String[] {"id"});
      return keyHolder.getKey().intValue();
      
    } catch (DuplicateKeyException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Route = " + route.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Une voie existe déjà avec cet identifiant", exception);
    } catch (DataIntegrityViolationException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Route = " + route.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Les données n'étant pas conformes, la création de la voie n'a pu être réalisée", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Route = " + route.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see RouteDao#deleteRoute(Integer)
   */
  @Override
  public void deleteRoute(final Integer routeId) throws TechnicalException, NotFoundException {
    
    final String sql = "DELETE FROM route WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", routeId);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("Id = " + routeId);
        }
        throw new NotFoundException("La voie à supprimer n'a pas été trouvée. La suppression n'a pas été réalisée.");
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
        LOG.debug("Id = " + routeId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
}
