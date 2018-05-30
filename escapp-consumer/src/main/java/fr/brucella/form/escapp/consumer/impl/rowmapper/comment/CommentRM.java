package fr.brucella.form.escapp.consumer.impl.rowmapper.comment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.comment.Comment;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Comment} business object
 *
 * @author BRUCELLA2
 */
public class CommentRM implements RowMapper<Comment> {

    /**
     * @see RowMapper#mapRow(ResultSet, int)
     */
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {

        Comment vComment = new Comment();

        vComment.setId(rs.getInt("id"));
        vComment.setIdCommentTarget(rs.getInt("id_comment_target"));
        vComment.setTargetType(rs.getString("target_type"));
        vComment.setText(rs.getString("text"));
        vComment.setEscappUser(rs.getInt("escapp_user"));

        return vComment;
    }
}
