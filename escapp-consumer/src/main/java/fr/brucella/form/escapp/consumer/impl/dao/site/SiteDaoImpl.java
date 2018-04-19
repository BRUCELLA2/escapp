package fr.brucella.form.escapp.consumer.impl.dao.site;

import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.SiteRM;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

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

/**
 * Site Data Access Object
 * 
 * @author BRUCELLA2
 */
@Component
public class SiteDaoImpl extends AbstractDao implements SiteDao {
	
    // TODO Complete methods
	// TODO Add log system

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
			
			return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
			
		} catch (EmptyResultDataAccessException pException) {
			pException.printStackTrace();
			throw new NotFoundException("Le site demandé n'a pas été trouvé", pException);
		} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
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
			
			List<Site> allSitesList = getJdbcTemplate().query(vSQL, vRowMapper);
			if(allSitesList.isEmpty()) {
				throw new NotFoundException("Aucun site n'a été trouvé.");
			}else {
				return allSitesList;
			}
			
		} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
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
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("Le site à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
			}
			
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du site n'a pu être réalisée.", pException);
    	} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
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
			
			getNamedJdbcTemplate().update(vSQL, vParams, vKeyHolder, new String[] { "id" });
			return vKeyHolder.getKey().intValue();
			
		} catch (DuplicateKeyException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Un site existe déjà avec cet identifiant", pException);
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la création du site n'a pu être réalisée", pException);	
    	} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
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
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("Le site à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
			}
			
    	} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		}	
	}

}
