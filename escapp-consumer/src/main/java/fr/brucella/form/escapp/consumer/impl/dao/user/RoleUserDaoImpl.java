package fr.brucella.form.escapp.consumer.impl.dao.user;

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
public class RoleUserDaoImpl extends AbstractDao implements RoleUserDao {

    /**
     * RoleUser DAO logger
     */
    private static final Log LOG = LogFactory.getLog(RoleUserDaoImpl.class);

    /**
     * @see RoleUseDaor#getRoleUserList(Integer)
     */
    @Override
    public List<RoleUser> getRoleUserList(final Integer userId) throws NotFoundException, TechnicalException {

        final String sql = "SELECT * FROM role_user where escapp_user = :userId";

        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);

        final RowMapper<RoleUser> rowMapper = new RoleUserRM();

        try {
            return this.getNamedJdbcTemplate().query(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException pException) {
            LOG.debug(pException.getStackTrace());
            throw new NotFoundException("L'utilisateur n'a aucun role", pException);
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
     * @see RoleUserDao#updateRoleUser(Integer)
     */
    @Override
    public void updateRoleUser(final RoleUser roleUser) throws TechnicalException, NotFoundException {

        final String sql = "UPDATE role_user SET escapp_user = :userId, role = :role";

        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", roleUser.getUserId());
        params.addValue("role", roleUser.getUserRole());

        try {
            int result = this.getNamedJdbcTemplate().update(sql, params);
            if (result == 0) {
                throw new NotFoundException("Le role associé à l'utilisateur n'a pas été trouvé. La mise à jour n'a pas été faite.");
            }
        } catch (DataIntegrityViolationException pException) {
            LOG.debug(pException.getStackTrace());
            throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du role n'a pu être réalisée.", pException);
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
     * @see RoleUserDao#insertRoleUser(RoleUser)
     */
    @Override
    public void insertRoleUser(final RoleUser roleUser) throws TechnicalException {

        final String sql = "INSERT INTO role_user (escapp_user, role) VALUES (:userId, :role)";

        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", roleUser.getUserId());
        params.addValue("role", roleUser.getUserRole());

        try {
            this.getNamedJdbcTemplate().update(sql, params);
        } catch (DuplicateKeyException pException) {
            LOG.debug(pException.getStackTrace());
            throw new TechnicalException("Un role identique pour cet utilisateur existe déjà.", pException);
        } catch (DataIntegrityViolationException pException) {
            LOG.debug(pException.getStackTrace());
            throw new TechnicalException("Les données n'étant pas conformes, la création du role pour cet utilisateur n'a pu être réalisée", pException);
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
     * @see RoleUserDao#deleteRoleUser(RoleUserDao)
     */
    @Override
    public void deleteRoleUser(final RoleUser roleUser) throws TechnicalException, NotFoundException {

        final String sql = "DELETE FROM route_user WHERE escapp_user = :userId AND role = :role";

        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", roleUser.getUserId());
        params.addValue("role", roleUser.getUserRole());

        try {
            final int result = this.getNamedJdbcTemplate().update(sql, params);
            if (result == 0) {
                throw new NotFoundException("Le role à supprimer pour cet utilisateur n'a pas été toruvé. La suppresion n'a pas été réalisée.");
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
