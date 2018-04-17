package fr.brucella.form.escapp.consumer.impl.dao.user;

import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.user.UserRM;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

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
import org.springframework.stereotype.Component;

/**
 * User Data Access Object
 * 
 * @author BRUCELLA2
 */
@Component
public class UserDaoImpl extends AbstractDao implements UserDao {
	
    // TODO Complete methods
	// TODO Add log system

	/**
	 * @see UserDao#getUserById(Integer)
	 */
	@Override
	public User getUserById(Integer pUserId) throws TechnicalException, NotFoundException {
		
		String vSQL = "SELECT * FROM escapp_user WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pUserId);
		
		RowMapper<User> vRowMapper = new UserRM();
		
		try {
			
			return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
			
		} catch (EmptyResultDataAccessException pException) {
			pException.printStackTrace();
			throw new NotFoundException("L'utilisateur demandé n'a pas été trouvé", pException);
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
	 * @see UserDao#getUserByLogin(String)
	 */
	@Override
	public User getUserByLogin(String pUserLogin) throws TechnicalException, NotFoundException {
		
		String vSQL = "SELECT * FROM escapp_user WHERE login = :login";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("login", pUserLogin);
		
		RowMapper<User> vRowMapper = new UserRM();
		
		try {
			return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
		} catch (EmptyResultDataAccessException pException) {
			pException.printStackTrace();
			throw new NotFoundException("L'utilisateur demandé n'a pas été trouvé", pException);
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
	 * @see UserDao#updateUser(User)
	 */
	@Override
	public void updateUser(User pUser) throws TechnicalException, NotFoundException {

		String vSQL = "UPDATE escapp_user SET login = :login, email = :email, password = :password WHERE id = :id";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pUser);
		
		try {
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("L'utilisateur à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
			}
			
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la mise à jour de l'utilisateur n'a pu être réalisée.", pException);
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
	 * @see UserDao#insertUser(User)
	 */
	@Override
	public void insertUser(User pUser) throws TechnicalException {

		String vSQL = "INSERT INTO escapp_user (id, login, email, password) VALUES (DEFAULT, :login, :email, :password)";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pUser);
		
		try {
			
			getNamedJdbcTemplate().update(vSQL, vParams);
			
		} catch (DuplicateKeyException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Un utilisateur existe déjà avec cet identifiant technique.", pException);
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la création de l'utilisateur n'a pu être réalisée", pException);	
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
	 * @see UserDao#deleteUser(Integer)
	 */
	@Override
	public void deleteUser(Integer pUserId) throws TechnicalException, NotFoundException {
		
		String vSQL = "DELETE FROM escapp_user WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pUserId);
		
		try {
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("L'utilisateur à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
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
