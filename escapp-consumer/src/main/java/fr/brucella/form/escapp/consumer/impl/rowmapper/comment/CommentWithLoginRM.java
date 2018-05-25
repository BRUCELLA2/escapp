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
    public Pair<Comment, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Comment vComment = new Comment();
        
        vComment.setId(rs.getInt("id"));
        vComment.setIdCommentTarget(rs.getInt("id_comment_target"));
        vComment.setTargetType(rs.getString("target_type"));
        vComment.setText(rs.getString("text"));
        vComment.setEscappUser(rs.getInt("escapp_user"));

        String vLogin = rs.getString("login");

        return new MutablePair<>(vComment, vLogin);
        
    }
}
