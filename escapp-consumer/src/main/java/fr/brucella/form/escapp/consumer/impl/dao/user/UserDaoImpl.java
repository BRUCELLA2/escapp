package fr.brucella.form.escapp.consumer.impl.dao.user;

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

import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.user.UserRM;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * User Data Access Object
 *
 * @author BRUCELLA2
 */
@Component
public class UserDaoImpl extends AbstractDao implements UserDao {
  
  /**
   * User DAO logger
   */
  private static final Log LOG = LogFactory.getLog(UserDaoImpl.class);
  
  
  /**
   * @see UserDao#getUserById(Integer)
   */
  @Override
  public User getUserById(final Integer userId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM escapp_user WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", userId);
    
    final RowMapper<User> rowMapper = new UserRM();
    
    try {
      
      return this.getNamedJdbcTemplate().queryForObject(sql, params, rowMapper);
      
    } catch (EmptyResultDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new NotFoundException("L'utilisateur demandé n'a pas été trouvé", pException);
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
   * @see UserDao#getUserByLogin(String)
   */
  @Override
  public User getUserByLogin(final String userLogin) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM escapp_user WHERE login = :login";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("login", userLogin);
    
    final RowMapper<User> rowMapper = new UserRM();
    
    try {
      return this.getNamedJdbcTemplate().queryForObject(sql, params, rowMapper);
    } catch (EmptyResultDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new NotFoundException("L'utilisateur demandé n'a pas été trouvé", pException);
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
   * @see UserDao#countUserByLogin(String)
   */
  @Override
  public Integer countUserByLogin(final String userLogin) throws TechnicalException {
    
    final String sql = "SELECT COUNT(login) FROM escapp_user WHERE login = :login";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("login", userLogin);
    
    try {
      return this.getNamedJdbcTemplate().queryForObject(sql, params, Integer.class);
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
   * @see UserDao#updateUser(User)
   */
  @Override
  public void updateUser(final User user) throws TechnicalException, NotFoundException {
    
    final String sql = "UPDATE escapp_user SET login = :login, email = :email, password = :password WHERE id = :id";
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(user);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        throw new NotFoundException("L'utilisateur à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
      }
      
    } catch (DataIntegrityViolationException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Les données n'étant pas conformes, la mise à jour de l'utilisateur n'a pu être réalisée.", pException);
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
   * @see UserDao#insertUser(User)
   */
  @Override
  public int insertUser(final User user) throws TechnicalException {
    
    final String sql = "INSERT INTO escapp_user (id, login, email, password) VALUES (DEFAULT, :login, :email, :password)";
    
    final KeyHolder keyHolder = new GeneratedKeyHolder();
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(user);
    
    try {
      
      this.getNamedJdbcTemplate().update(sql, params, keyHolder, new String[] {"id"});
      return keyHolder.getKey().intValue();
      
    } catch (DuplicateKeyException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Un utilisateur existe déjà avec cet identifiant technique.", pException);
    } catch (DataIntegrityViolationException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Les données n'étant pas conformes, la création de l'utilisateur n'a pu être réalisée", pException);
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
   * @see UserDao#deleteUser(Integer)
   */
  @Override
  public void deleteUser(final Integer userId) throws TechnicalException, NotFoundException {
    
    final String sql = "DELETE FROM escapp_user WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", userId);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        throw new NotFoundException("L'utilisateur à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
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
