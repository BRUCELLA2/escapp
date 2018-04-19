package fr.brucella.form.escapp.consumer.impl.dao.site;

import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.SectorRM;
import fr.brucella.form.escapp.model.beans.site.Sector;
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
 * Sector Data Access Object
 * 
 * @author BRUCELLA2
 */
@Component
public class SectorDaoImpl extends AbstractDao implements SectorDao {

    // TODO Complete methods
	// TODO Add log system
	
	/**
	 * @see SectorDao#getSector(Integer)
	 */
	@Override
	public Sector getSector(Integer pSectorId) throws TechnicalException, NotFoundException {
		
		String vSQL = "SELECT * FROM sector WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pSectorId);
		
		RowMapper<Sector> vRowMapper = new SectorRM();
		
		try {
			
			return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
			
		} catch (EmptyResultDataAccessException pException) {
			pException.printStackTrace();
			throw new NotFoundException("Le secteur demandé n'a pas été trouvé", pException);
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
	 * @see SectorDao#getSectorsList(Integer)
	 */
	@Override
	public List<Sector> getSectorsList(Integer pSiteId) throws TechnicalException, NotFoundException {
		
		String vSQL = "SELECT * FROM sector WHERE site_id = :siteId";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("siteId", pSiteId);
		
		RowMapper<Sector> vRowMapper = new SectorRM();
		
		try {
			
			List<Sector> sectorsList = getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
			if(sectorsList.isEmpty()) {
				throw new NotFoundException("Aucun secteur n'a été trouvé.");
			}else {
				return sectorsList;
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
	 * @see SectorDao#updateSector(Sector)
	 */
	@Override
	public void updateSector(Sector pSector) throws TechnicalException, NotFoundException {
		
		String vSQL = "UPDATE sector SET name = :name, description = :description, site_id = :siteId WHERE id = :id";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pSector);
		
		try {
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("Le secteur à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
			}
			
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du secteur n'a pu être réalisée.", pException);
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
	 * @see SectorDao#insertSector(Sector)
	 */
	@Override
	public int insertSector(Sector pSector) throws TechnicalException {

		String vSQL = "INSERT INTO sector (id, name, description, site_id) VALUES (DEFAULT, :name, :description, :siteId)";
		
		KeyHolder vKeyHolder = new GeneratedKeyHolder();
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pSector);

		try {
			
			getNamedJdbcTemplate().update(vSQL, vParams, vKeyHolder, new String[] { "id" });
			return vKeyHolder.getKey().intValue();
			
		} catch (DuplicateKeyException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Un secteur existe déjà avec cet identifiant", pException);
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la création du secteur n'a pu être réalisée", pException);	
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
	 * @see SectorDao#deleteSector(Integer)
	 */
	@Override
	public void deleteSector(Integer pSectorId) throws TechnicalException, NotFoundException {

		String vSQL = "DELETE FROM sector WHERE id = :id";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pSectorId);
		
		try {
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("Le secteur à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
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
