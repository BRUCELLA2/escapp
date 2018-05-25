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
 * Site Data Access Object
 *
 * @author BRUCELLA2
 */
@Component
public class SiteDaoImpl extends AbstractDao implements SiteDao {
    
    // ----- Logger
    private Log log = LogFactory.getLog(SiteDaoImpl.class);
    
    /**
     * @see SiteDao#getSite(Integer)
     */
    @Override
    public Site getSite(Integer pSiteId) throws TechnicalException, NotFoundException {
        
        String vSQL = "SELECT * FROM site WHERE id = :id";
        
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pSiteId);
        
        RowMapper<Site> vRowMapper = new SiteRM();
        
        try {
            
            return this.getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
            
        } catch (EmptyResultDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new NotFoundException("Le site demandé n'a pas été trouvé", pException);
        } catch (PermissionDeniedDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        } catch (DataAccessResourceFailureException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
        } catch (DataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        }
    }
    
    /**
     * @see SiteDao#getAllSitesList()
     */
    @Override
    public List<Site> getAllSitesList() throws TechnicalException, NotFoundException {
        
        String vSQL = "SELECT * FROM site";
        
        RowMapper<Site> vRowMapper = new SiteRM();
        
        try {
            
            List<Site> allSitesList = this.getJdbcTemplate().query(vSQL, vRowMapper);
            if (allSitesList.isEmpty()) {
                throw new NotFoundException("Aucun site n'a été trouvé.");
            }
            else {
                return allSitesList;
            }
            
        } catch (PermissionDeniedDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        } catch (DataAccessResourceFailureException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
        } catch (DataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        }
    }
    
    /**
     * @see SiteDao#getSearchSiteList(SiteSearch)
     */
    @Override
    public List<Site> getSearchSitesList(SiteSearch pSiteSearch) throws TechnicalException, NotFoundException {
        
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        
        StringBuilder vSQL = new StringBuilder(
                "SELECT DISTINCT site.id, site.name, site.department, site.municipality, site.description " + " 	FROM site " + " 	INNER JOIN sector "
                        + " 	ON sector.site_id = site.id " + " 		INNER JOIN route " + " 		ON route.sector_id = sector.id " + " 	WHERE 1 = 1 ");
        
        if (pSiteSearch != null) {
            if (!StringUtils.isEmpty(pSiteSearch.getDepartmentSite())) {
                vSQL.append(" AND UPPER(site.department) = UPPER(:departmentSite) ");
                vParams.addValue("departmentSite", pSiteSearch.getDepartmentSite());
            }
            if (!StringUtils.isEmpty(pSiteSearch.getMunicipalitySite())) {
                vSQL.append(" AND UPPER(site.municipality) = UPPER(:municipalitySite) ");
                vParams.addValue("municipalitySite", pSiteSearch.getMunicipalitySite());
            }
            if (!StringUtils.isEmpty(pSiteSearch.getMinGradeRoute())) {
                vSQL.append(" AND UPPER(route.grade) >= UPPER(:minGradeRoute) ");
                vParams.addValue("minGradeRoute", pSiteSearch.getMinGradeRoute());
            }
            if (!StringUtils.isEmpty(pSiteSearch.getMaxGradeRoute())) {
                vSQL.append(" AND UPPER(route.grade) <= UPPER(:maxGradeRoute) ");
                vParams.addValue("maxGradeRoute", pSiteSearch.getMaxGradeRoute());
            }
        }
        
        RowMapper<Site> vRowMapper = new SiteRM();
        
        try {
            return this.getNamedJdbcTemplate().query(vSQL.toString(), vParams, vRowMapper);
        } catch (EmptyResultDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new NotFoundException("Aucun site ne correspond à votre recherche", pException);
        } catch (PermissionDeniedDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        } catch (DataAccessResourceFailureException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
        } catch (DataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        }
    }
    
    /**
     * @see SiteDao#updateSite(Site)
     */
    @Override
    public void updateSite(Site pSite) throws TechnicalException, NotFoundException {
        
        String vSQL = "UPDATE site SET name = :name, department = :department, municipality = :municipality, description = :description WHERE id = :id";
        
        SqlParameterSource vParams = new BeanPropertySqlParameterSource(pSite);
        
        try {
            
            int result = this.getNamedJdbcTemplate().update(vSQL, vParams);
            if (result == 0) {
                throw new NotFoundException("Le site à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
            }
            
        } catch (DataIntegrityViolationException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du site n'a pu être réalisée.", pException);
        } catch (PermissionDeniedDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        } catch (DataAccessResourceFailureException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
        } catch (DataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        }
    }
    
    /**
     * @see SiteDao#insertSite(Site)
     */
    @Override
    public int insertSite(Site pSite) throws TechnicalException {
        
        String vSQL = "INSERT INTO site (id, name, department, municipality, description) VALUES (DEFAULT, :name, :department, :municipality, :description)";
        
        KeyHolder vKeyHolder = new GeneratedKeyHolder();
        
        SqlParameterSource vParams = new BeanPropertySqlParameterSource(pSite);
        
        try {
            
            this.getNamedJdbcTemplate().update(vSQL, vParams, vKeyHolder, new String[] {"id"});
            return vKeyHolder.getKey().intValue();
            
        } catch (DuplicateKeyException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException("Un site existe déjà avec cet identifiant", pException);
        } catch (DataIntegrityViolationException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException("Les données n'étant pas conformes, la création du site n'a pu être réalisée", pException);
        } catch (PermissionDeniedDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        } catch (DataAccessResourceFailureException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
        } catch (DataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        }
    }
    
    /**
     * @see SiteDao#deleteSite(Integer)
     */
    @Override
    public void deleteSite(Integer pSiteId) throws TechnicalException, NotFoundException {
        
        String vSQL = "DELETE FROM site WHERE id = :id";
        
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pSiteId);
        
        try {
            
            int result = this.getNamedJdbcTemplate().update(vSQL, vParams);
            if (result == 0) {
                throw new NotFoundException("Le site à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
            }
            
        } catch (PermissionDeniedDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        } catch (DataAccessResourceFailureException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
        } catch (DataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
        }
    }
    
}
