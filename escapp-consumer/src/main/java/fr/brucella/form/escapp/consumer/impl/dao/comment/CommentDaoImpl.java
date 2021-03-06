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
 * Comment Data Access Object.
 *
 * @author BRUCELLA2
 */
@Component
public class CommentDaoImpl extends AbstractDao implements CommentDao {
  
  /**
   * Comment Manager logger.
   */
  private static final Log LOG = LogFactory.getLog(CommentDaoImpl.class);
  
  /**
   * @see CommentDao#getComment(Integer)
   */
  @Override
  public Comment getComment(final Integer commentId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM comment WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", commentId);
    
    final RowMapper<Comment> rowMapper = new CommentRM();
    
    try {
      
      return this.getNamedJdbcTemplate().queryForObject(sql, params, rowMapper);
      
    } catch (EmptyResultDataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + commentId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException("Le commentaire demandé n'a pas été trouvé", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("id = " + commentId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see CommentDao#getCommentsList(String, Integer)
   */
  @Override
  public List<Comment> getCommentsList(final String targetType, final Integer idCommentTarget) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM comment WHERE target_type = :targetType AND id_comment_target = :idCommentTarget";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("targetType", targetType);
    params.addValue("idCommentTarget", idCommentTarget);
    
    final RowMapper<Comment> rowMapper = new CommentRM();
    
    try {
      final List<Comment> commentsList = this.getNamedJdbcTemplate().query(sql, params, rowMapper);
      if (commentsList.isEmpty()) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("targetType = " + targetType);
          LOG.debug("idCommentTarget = " + idCommentTarget);
        }
        throw new NotFoundException("Aucun commentaire n'a été trouvé.");
      }
      else {
        return commentsList;
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
        LOG.debug("targetType = " + targetType);
        LOG.debug("idCommentTarget = " + idCommentTarget);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
    
  }
  
  /**
   * @see CommentDao#getCommentsListWithLogin(String, Integer)
   */
  @Override
  public List<Pair<Comment, String>> getCommentsListWithLogin(final String targetType, final Integer idCommentTarget, final String order)
      throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT comment.id, comment.text, comment.target_type, comment.id_comment_target, comment.escapp_user, escapp_user.login "
        + "		FROM comment " + "		INNER JOIN escapp_user " + "		ON comment.escapp_user = escapp_user.id "
        + "		WHERE comment.target_type = :targetType AND comment.id_comment_target = :idCommentTarget " + " ORDER BY comment.id " + order;
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("targetType", targetType);
    params.addValue("idCommentTarget", idCommentTarget);
    
    final RowMapper<Pair<Comment, String>> rowMapper = new CommentWithLoginRM();
    
    try {
      final List<Pair<Comment, String>> commentsListWithLogin = this.getNamedJdbcTemplate().query(sql, params, rowMapper);
      if (commentsListWithLogin.isEmpty()) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("targetType = " + targetType);
          LOG.debug("idCommentTarget = " + idCommentTarget);
          LOG.debug("order = " + order);
        }
        throw new NotFoundException("Aucun commentaire n'a été trouvé.");
      }
      else {
        return commentsListWithLogin;
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
        LOG.debug("targetType = " + targetType);
        LOG.debug("idCommentTarget = " + idCommentTarget);
        LOG.debug("order = " + order);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
    
  }
  
  /**
   * @see CommentDao#updateComment(Comment)
   */
  @Override
  public void updateComment(final Comment comment) throws TechnicalException, NotFoundException {
    
    final String sql =
        "UPDATE comment SET text = :text, target_type = :targetType, id_comment_target = :idCommentTarget, escapp_user = :escappUser WHERE id = :id";
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("Comment = " + comment.toString());
        }
        throw new NotFoundException("Le commentaire à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
      }
      
    } catch (DataIntegrityViolationException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du commentaire n'a pu être réalisée.", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see CommentDao#insertComment(Comment)
   */
  @Override
  public int insertComment(final Comment comment) throws TechnicalException {
    
    final String sql =
        "INSERT INTO comment (id, text, target_type, id_comment_target, escapp_user) VALUES (DEFAULT, :text, :targetType, :idCommentTarget, :escappUser)";
    
    final KeyHolder keyHolder = new GeneratedKeyHolder();
    
    final SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
    
    try {
      
      this.getNamedJdbcTemplate().update(sql, params, keyHolder, new String[] {"id"});
      return keyHolder.getKey().intValue();
      
    } catch (DuplicateKeyException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un commentaire existe déjà avec cet identifiant", exception);
    } catch (DataIntegrityViolationException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Les données n'étant pas conformes, la création du commentaire n'a pu être réalisée", exception);
    } catch (PermissionDeniedDataAccessException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(PERMISSION_DENIED, exception);
    } catch (DataAccessResourceFailureException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, exception);
    } catch (DataAccessException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("SQL : " + sql);
        LOG.debug("Comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
  
  /**
   * @see CommentDao#deleteComment(Integer)
   */
  @Override
  public void deleteComment(final Integer commentId) throws TechnicalException, NotFoundException {
    
    final String sql = "DELETE FROM comment WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", commentId);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("SQL : " + sql);
          LOG.debug("id = " + commentId);
        }
        throw new NotFoundException("Le commentaire à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
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
        LOG.debug("id = " + commentId);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, exception);
    }
  }
}
