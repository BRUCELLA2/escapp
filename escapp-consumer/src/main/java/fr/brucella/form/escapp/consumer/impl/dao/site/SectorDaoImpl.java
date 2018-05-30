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

import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.SectorRM;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Sector Data Access Object
 *
 * @author BRUCELLA2
 */
@Component
public class SectorDaoImpl extends AbstractDao implements SectorDao {
  
  
  /**
   * Sector DAO logger
   */
  private static final Log LOG = LogFactory.getLog(SectorDaoImpl.class);
  
  
  /**
   * @see SectorDao#getSector(Integer)
   */
  @Override
  public Sector getSector(final Integer sectorId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM sector WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", sectorId);
    
    final RowMapper<Sector> rowMapper = new SectorRM();
    
    try {
      
      return this.getNamedJdbcTemplate().queryForObject(sql, params, rowMapper);
      
    } catch (EmptyResultDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new NotFoundException("Le secteur demandé n'a pas été trouvé", pException);
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    }
  }
  
  /**
   * @see SectorDao#getSectorsList(Integer)
   */
  @Override
  public List<Sector> getSectorsList(final Integer siteId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM sector WHERE site_id = :siteId";
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("siteId", siteId);
    
    final RowMapper<Sector> rowMapper = new SectorRM();
    
    try {
      
      final List<Sector> sectorsList = this.getNamedJdbcTemplate().query(sql, params, rowMapper);
      if (sectorsList.isEmpty()) {
        throw new NotFoundException("Aucun secteur n'a été trouvé.");
      }
      else {
        return sectorsList;
      }
      
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    }
  }
  
  /**
   * @see SectorDao#updateSector(Sector)
   */
  @Override
  public void updateSector(final Sector sector) throws TechnicalException, NotFoundException {
    
    final String sql = "UPDATE sector SET name = :name, description = :description, site_id = :siteId WHERE id = :id";
    final SqlParameterSource params = new BeanPropertySqlParameterSource(sector);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        throw new NotFoundException("Le secteur à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
      }
      
    } catch (DataIntegrityViolationException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du secteur n'a pu être réalisée.", pException);
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    }
  }
  
  /**
   * @see SectorDao#insertSector(Sector)
   */
  @Override
  public int insertSector(final Sector sector) throws TechnicalException {
    
    final String sql = "INSERT INTO sector (id, name, description, site_id) VALUES (DEFAULT, :name, :description, :siteId)";
    
    final KeyHolder keyHolder = new GeneratedKeyHolder();
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(sector);
    
    try {
      
      this.getNamedJdbcTemplate().update(sql, params, keyHolder, new String[] {"id"});
      return keyHolder.getKey().intValue();
      
    } catch (DuplicateKeyException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Un secteur existe déjà avec cet identifiant", pException);
    } catch (DataIntegrityViolationException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Les données n'étant pas conformes, la création du secteur n'a pu être réalisée", pException);
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    }
  }
  
  /**
   * @see SectorDao#deleteSector(Integer)
   */
  @Override
  public void deleteSector(final Integer sectorId) throws TechnicalException, NotFoundException {
    
    final String sql = "DELETE FROM sector WHERE id = :id";
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", sectorId);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        throw new NotFoundException("Le secteur à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
      }
      
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
    }
  }
}
