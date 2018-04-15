package fr.brucella.form.escapp.consumer.impl.dao.site;

import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.LengthRM;
import fr.brucella.form.escapp.model.beans.site.Length;
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
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class LengthDaoImpl extends AbstractDao implements LengthDao {
	
    // TODO Complete methods
	// TODO Add log system

	@Override
	public Length getLength(Integer pLengthId) throws TechnicalException, NotFoundException {
		
		String vSQL = "SELECT * FROM length WHERE id = :id";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pLengthId);
		
		RowMapper<Length> vRowMapper = new LengthRM();
		
		try {
			
			return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
			
		} catch (EmptyResultDataAccessException pException) {
			pException.printStackTrace();
			throw new NotFoundException("La longueur demandée n'a pas été trouvée", pException);
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
	

	@Override
	public List<Length> getLengthsList(Integer pRouteId) throws TechnicalException, NotFoundException {

		String vSQL = "SELECT * FROM length WHERE route_id = :routeId";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("routeId", pRouteId);
		
		RowMapper<Length> vRowMapper = new LengthRM();
		
		try {
			
			List<Length> lengthsList = getNamedJdbcTemplate().query(vSQL, vParams,vRowMapper);
			if(lengthsList.isEmpty()) {
				throw new NotFoundException("Aucune longueur n'a été trouvée.");
			} else {
				return lengthsList;
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

	@Override
	public void updateLength(Length pLength) throws TechnicalException, NotFoundException {
		
		String vSQL = "UPDATE length SET length = :length, grade = :grade, points_nb = :pointsNb, description = :description, route_id = :routeId WHERE id = :id";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pLength);
		
		try {
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("La longueur à modifier n'a pas été trouvée. La mise à jour n'a pas été faite.");
			}
			
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la mise à jour de la longueur n'a pu être réalisée.", pException);
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

	@Override
	public void insertLength(Length pLength) throws TechnicalException {
		
		String vSQL = "INSERT INTO length (id, length, grade, points_nb, description, route_id) VALUES (DEFAULT, :length, :grade, :pointsNb, :description, :routeId)";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pLength);
		
		try {
			
			getNamedJdbcTemplate().update(vSQL, vParams);
			
		} catch (DuplicateKeyException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Une longueur existe déjà avec cet identifiant", pException);
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la création de la longueur n'a pu être réalisée", pException);	
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

	@Override
	public void deleteLength(Integer pLengthId) throws TechnicalException, NotFoundException {
		
		String vSQL = "DELETE FROM lenght WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pLengthId);
		
		try {
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("La longueur à supprimer n'a pas été trouvée. La suppression n'a pas été réalisée.");
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
