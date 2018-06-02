package fr.brucella.form.escapp.consumer.impl.dao.site;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.SiteRM;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.SiteSearch;

/**
 * Site Data Access Object.
 *
 * @author BRUCELLA2
 */
@Component
public class SiteDaoImpl extends AbstractDao implements SiteDao {
  
  /**
   * Site DAO logger.
   */
  private static final Log LOG = LogFactory.getLog(SiteDaoImpl.class);
  
  
  /**
   * @see SiteDao#getSite(Integer)
   */
  @Override
  public Site getSite(final Integer siteId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM site WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", siteId);
    
    final RowMapper<Site> rowMapper = new SiteRM();
    
    try {
      
      return this.getNamedJdbcTemplate().queryForObject(sql, params, rowMapper);
      
    } catch (EmptyResultDataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + siteId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException("Le site demandé n'a pas été trouvé", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + siteId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see SiteDao#getAllSitesList()
   */
  @Override
  public List<Site> getAllSitesList() throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM site";
    
    final RowMapper<Site> rowMapper = new SiteRM();
    
    try {
      
      final List<Site> allSitesList = this.getJdbcTemplate().query(sql, rowMapper);
      if (allSitesList.isEmpty()) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
        }
        throw new NotFoundException("Aucun site n'a été trouvé.");
      }
      else {
        return allSitesList;
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
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see SiteDao#getSearchSiteList(SiteSearch)
   */
  @Override
  public List<Site> getSearchSitesList(final SiteSearch siteSearch) throws TechnicalException, NotFoundException {
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    
    final StringBuilder sql = new StringBuilder(49);
    sql.append("SELECT DISTINCT site.id, site.name, site.department, site.municipality, site.description FROM site ");
    
    if (siteSearch != null) {
      
      if (!StringUtils.isEmpty(siteSearch.getMinGradeRoute()) || !StringUtils.isEmpty(siteSearch.getMaxGradeRoute())) {
        sql.append(" INNER JOIN sector ON sector.site_id = site.id INNER JOIN route ON route.sector_id = sector.id WHERE 1 = 1 ");
      }
      else {
        sql.append(" WHERE 1 = 1 ");
      }
      
      
      if (!StringUtils.isEmpty(siteSearch.getDepartmentSite())) {
        sql.append(" AND UPPER(site.department) = UPPER(:departmentSite) ");
        params.addValue("departmentSite", siteSearch.getDepartmentSite());
      }
      if (!StringUtils.isEmpty(siteSearch.getMunicipalitySite())) {
        sql.append(" AND UPPER(site.municipality) = UPPER(:municipalitySite) ");
        params.addValue("municipalitySite", siteSearch.getMunicipalitySite());
      }
      if (!StringUtils.isEmpty(siteSearch.getMinGradeRoute())) {
        sql.append(" AND UPPER(route.grade) >= UPPER(:minGradeRoute) ");
        params.addValue("minGradeRoute", siteSearch.getMinGradeRoute());
      }
      if (!StringUtils.isEmpty(siteSearch.getMaxGradeRoute())) {
        sql.append(" AND UPPER(route.grade) <= UPPER(:maxGradeRoute) ");
        params.addValue("maxGradeRoute", siteSearch.getMaxGradeRoute());
      }
    }
    
    final RowMapper<Site> rowMapper = new SiteRM();
    
    try {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("search = " + siteSearch.toString());
      }
      return this.getNamedJdbcTemplate().query(sql.toString(), params, rowMapper);
    } catch (EmptyResultDataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("search = " + siteSearch.toString());
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException("Aucun site ne correspond à votre recherche", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("search = " + siteSearch.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("search = " + siteSearch.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see SiteDao#updateSite(Site)
   */
  @Override
  public void updateSite(final Site site) throws TechnicalException, NotFoundException {
    
    final String sql = "UPDATE site SET name = :name, department = :department, municipality = :municipality, description = :description WHERE id = :id";
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(site);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("site = " + site.toString());
        }
        throw new NotFoundException("Le site à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
      }
      
    } catch (DataIntegrityViolationException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("site = " + site.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du site n'a pu être réalisée.", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("site = " + site.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see SiteDao#insertSite(Site)
   */
  @Override
  public int insertSite(final Site site) throws TechnicalException {
    
    final String sql = "INSERT INTO site (id, name, department, municipality, description) VALUES (DEFAULT, :name, :department, :municipality, :description)";
    
    final KeyHolder keyHolder = new GeneratedKeyHolder();
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(site);
    
    try {
      
      this.getNamedJdbcTemplate().update(sql, params, keyHolder, new String[] {"id"});
      return keyHolder.getKey().intValue();
      
    } catch (DuplicateKeyException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("site = " + site.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un site existe déjà avec cet identifiant", exception);
    } catch (DataIntegrityViolationException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("site = " + site.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Les données n'étant pas conformes, la création du site n'a pu être réalisée", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("site = " + site.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see SiteDao#deleteSite(Integer)
   */
  @Override
  public void deleteSite(final Integer siteId) throws TechnicalException, NotFoundException {
    
    final String sql = "DELETE FROM site WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", siteId);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("id = " + siteId);
        }
        throw new NotFoundException("Le site à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
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
        LOG.debug("id = " + siteId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
}
