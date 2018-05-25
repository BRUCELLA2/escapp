package fr.brucella.form.escapp.consumer.impl.dao.comment;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
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

import fr.brucella.form.escapp.consumer.contract.dao.comment.CommentDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.comment.CommentRM;
import fr.brucella.form.escapp.consumer.impl.rowmapper.comment.CommentWithLoginRM;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Comment Data Access Object
 *
 * @author BRUCELLA2
 */
@Component
public class CommentDaoImpl extends AbstractDao implements CommentDao {
    
    // ----- Logger
    private Log log = LogFactory.getLog(CommentDaoImpl.class);
    
    /**
     * @see CommentDao#getComment(Integer)
     */
    @Override
    public Comment getComment(Integer pCommentId) throws TechnicalException, NotFoundException {

        String vSQL = "SELECT * FROM comment WHERE id = :id";
        
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pCommentId);
        
        RowMapper<Comment> vRowMapper = new CommentRM();
        
        try {
            
            return this.getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
            
        } catch (EmptyResultDataAccessException pException) {
            this.log.debug(pException.getStackTrace());
            throw new NotFoundException("Le commentaire demandé n'a pas été trouvé", pException);
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
     * @see CommentDao#getCommentsList(String, Integer)
     */
    @Override
    public List<Comment> getCommentsList(String pTargetType, Integer pIdCommentTarget) throws TechnicalException, NotFoundException {
        
        String vSQL = "SELECT * FROM comment WHERE target_type = :targetType AND id_comment_target = :idCommentTarget";
        
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("targetType", pTargetType);
        vParams.addValue("idCommentTarget", pIdCommentTarget);
        
        RowMapper<Comment> vRowMapper = new CommentRM();
        
        try {
            List<Comment> commentsList = this.getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
            if (commentsList.isEmpty()) {
                throw new NotFoundException("Aucun commentaire n'a été trouvé.");
            }
            else {
                return commentsList;
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
     * @see CommentDao#getCommentsListWithLogin(String, Integer)
     */
    @Override
    public List<Pair<Comment, String>> getCommentsListWithLogin(String pTargetType, Integer pIdCommentTarget, String pOrder)
            throws TechnicalException, NotFoundException {
        
        String vSQL = "SELECT comment.id, comment.text, comment.target_type, comment.id_comment_target, comment.escapp_user, escapp_user.login "
                + "		FROM comment " + "		INNER JOIN escapp_user " + "		ON comment.escapp_user = escapp_user.id "
                + "		WHERE comment.target_type = :targetType AND comment.id_comment_target = :idCommentTarget " + "		ORDER BY comment.id " + pOrder;
        
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("targetType", pTargetType);
        vParams.addValue("idCommentTarget", pIdCommentTarget);
        
        RowMapper<Pair<Comment, String>> vRowMapper = new CommentWithLoginRM();
        
        try {
            List<Pair<Comment, String>> commentsListWithLogin = this.getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
            if (commentsListWithLogin.isEmpty()) {
                throw new NotFoundException("Aucun commentaire n'a été trouvé.");
            }
            else {
                return commentsListWithLogin;
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
     * @see CommentDao#updateComment(Comment)
     */
    @Override
    public void updateComment(Comment pComment) throws TechnicalException, NotFoundException {
        
        String vSQL =
                "UPDATE comment SET text = :text, target_type = :targetType, id_comment_target = :idCommentTarget, escapp_user = :escappUser WHERE id = :id";
        
        SqlParameterSource vParams = new BeanPropertySqlParameterSource(pComment);
        
        try {
            
            int result = this.getNamedJdbcTemplate().update(vSQL, vParams);
            if (result == 0) {
                throw new NotFoundException("Le commentaire à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
            }
            
        } catch (DataIntegrityViolationException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du commentaire n'a pu être réalisée.", pException);
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
     * @see CommentDao#insertComment(Comment)
     */
    @Override
    public int insertComment(Comment pComment) throws TechnicalException {
        
        String vSQL =
                "INSERT INTO comment (id, text, target_type, id_comment_target, escapp_user) VALUES (DEFAULT, :text, :targetType, :idCommentTarget, :escappUser)";
        
        KeyHolder vKeyHolder = new GeneratedKeyHolder();

        SqlParameterSource vParams = new BeanPropertySqlParameterSource(pComment);

        try {
            
            this.getNamedJdbcTemplate().update(vSQL, vParams, vKeyHolder, new String[] {"id"});
            return vKeyHolder.getKey().intValue();
            
        } catch (DuplicateKeyException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException("Un commentaire existe déjà avec cet identifiant", pException);
        } catch (DataIntegrityViolationException pException) {
            this.log.debug(pException.getStackTrace());
            throw new TechnicalException("Les données n'étant pas conformes, la création du commentaire n'a pu être réalisée", pException);
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
     * @see CommentDao#deleteComment(Integer)
     */
    @Override
    public void deleteComment(Integer pCommentId) throws TechnicalException, NotFoundException {
        String vSQL = "DELETE FROM comment WHERE id = :id";
        
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pCommentId);
        
        try {
            
            int result = this.getNamedJdbcTemplate().update(vSQL, vParams);
            if (result == 0) {
                throw new NotFoundException("Le commentaire à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
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
