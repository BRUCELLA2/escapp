package fr.brucella.form.escapp.consumer.impl.rowmapper.comment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.comment.Comment;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Comment} business object.
 *
 * @author BRUCELLA2
 */
public class CommentRM implements RowMapper<Comment> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Comment mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    
    Comment comment = new Comment();
    
    comment.setId(resultSet.getInt("id"));
    comment.setIdCommentTarget(resultSet.getInt("id_comment_target"));
    comment.setTargetType(resultSet.getString("target_type"));
    comment.setText(resultSet.getString("text"));
    comment.setEscappUser(resultSet.getInt("escapp_user"));
    
    return comment;
  }
}
