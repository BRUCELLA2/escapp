package fr.brucella.form.escapp.consumer.impl.dao.user;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.consumer.contract.dao.user.RoleUserDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.user.RoleUserRM;
import fr.brucella.form.escapp.model.beans.user.RoleUser;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * RoleUser Data Access Object
 * 
 * @author BRUCELLA2
 */
@Component
public class RoleUserDaoImpl extends AbstractDao implements RoleUserDao{

	
	/**
	 * @see RoleUseDaor#getRoleUserList(Integer)
	 */
	@Override
	public List<RoleUser> getRoleUserList(Integer pUserId) throws NotFoundException, TechnicalException{
		
		String vSQL = "SELECT * FROM role_user where escapp_user = :userId";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("userId", pUserId);
		
		RowMapper<RoleUser> vRowMapper = new RoleUserRM();
		
		try {
			 return getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
		} catch (EmptyResultDataAccessException pException) {
			pException.printStackTrace();
			throw new NotFoundException("L'utilisateur n'a aucun role", pException);
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
	 * @see RoleUserDao#updateRoleUser(Integer)
	 */
	@Override
	public void updateRoleUser(RoleUser pRoleUser) throws TechnicalException, NotFoundException {
	  
	  String vSQL = "UPDATE role_user SET escapp_user = :userId, role = :role";
	  
	  MapSqlParameterSource vParams = new MapSqlParameterSource();
	  vParams.addValue("userId", pRoleUser.getUserId());
	  vParams.addValue("role", pRoleUser.getUserRole());   
	
	  try {
	    int result = getNamedJdbcTemplate().update(vSQL, vParams);
	    if(result == 0) {
	      throw new NotFoundException("Le role associé à l'utilisateur n'a pas été trouvé. La mise à jour n'a pas été faite.");
	    }
      } catch (DataIntegrityViolationException pException) {
          pException.printStackTrace();
          throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du role n'a pu être réalisée.", pException);
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
	 * @see RoleUserDao#insertRoleUser(RoleUser)
	 */
	@Override
	public void insertRoleUser(RoleUser pRoleUser) throws TechnicalException {
		
	  String vSQL = "INSERT INTO role_user (escapp_user, role) VALUES (:userId, :role)";
	  
	   MapSqlParameterSource vParams = new MapSqlParameterSource();
	   vParams.addValue("userId", pRoleUser.getUserId());
	   vParams.addValue("role", pRoleUser.getUserRole()); 
	
	   try {
	     getNamedJdbcTemplate().update(vSQL, vParams);
       } catch (DuplicateKeyException pException) {
           pException.printStackTrace();
           throw new TechnicalException("Un role identique pour cet utilisateur existe déjà.", pException);
       } catch (DataIntegrityViolationException pException) {
           pException.printStackTrace();
           throw new TechnicalException("Les données n'étant pas conformes, la création du role pour cet utilisateur n'a pu être réalisée", pException);   
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
	 * @see RoleUserDao#deleteRoleUser(RoleUserDao)
	 */
	@Override
	public void deleteRoleUser(RoleUser pRoleUser) throws TechnicalException, NotFoundException {

	  String vSQL ="DELETE FROM route_user WHERE escapp_user = :userId AND role = :role";
	  
	  MapSqlParameterSource vParams = new MapSqlParameterSource();
	  vParams.addValue("userId", pRoleUser.getUserId());
	  vParams.addValue("role", pRoleUser.getUserRole()); 
	  
	  try {
	    int result = getNamedJdbcTemplate().update(vSQL, vParams);
	    if(result == 0) {
	      throw new NotFoundException("Le role à supprimer pour cet utilisateur n'a pas été toruvé. La suppresion n'a pas été réalisée.");
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
