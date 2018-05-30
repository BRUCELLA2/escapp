package fr.brucella.form.escapp.consumer.impl.rowmapper.comment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.comment.Comment;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to a Map of {@link Comment} business
 * object with the login of the user who write the comment.
 *
 * @author BRUCELLA
 */
public class CommentWithLoginRM implements RowMapper<Pair<Comment, String>> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Pair<Comment, String> mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    
    final Comment comment = new Comment();
    
    comment.setId(resultSet.getInt("id"));
    comment.setIdCommentTarget(resultSet.getInt("id_comment_target"));
    comment.setTargetType(resultSet.getString("target_type"));
    comment.setText(resultSet.getString("text"));
    comment.setEscappUser(resultSet.getInt("escapp_user"));
    
    final String login = resultSet.getString("login");
    
    return new MutablePair<>(comment, login);
    
  }
}
