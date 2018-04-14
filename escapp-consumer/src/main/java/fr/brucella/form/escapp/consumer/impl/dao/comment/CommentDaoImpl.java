package fr.brucella.form.escapp.consumer.impl.dao.comment;

import fr.brucella.form.escapp.consumer.contract.dao.comment.CommentDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.comment.CommentRM;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDaoImpl extends AbstractDao implements CommentDao {

    // TODO Complete methods
    // TODO Add try/catch for Exception

    @Override
    public Comment getComment(Integer pCommentId) throws TechnicalException {
        
        String vSQL = "SELECT * FROM comment WHERE id = :id";

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pCommentId);

        RowMapper<Comment> vRowMapper = new CommentRM();

        return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);   
    }

    @Override
    public List<Comment> getCommentsList(String pTargetType, Integer pIdCommentTarget) throws TechnicalException {

        String vSQL = "SELECT * FROM comment WHERE target_type = :targetType AND id_comment_target = :idCommentTarget";

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("targetType", pTargetType);
        vParams.addValue("idCommentTarget", pIdCommentTarget);

        RowMapper<Comment> vRowMapper = new CommentRM();

        return getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);

    }

    @Override
    public void updateComment(Comment pComment) throws TechnicalException {
    	
        String vSQL = "UPDATE comment SET text = :text, target_type = :targetType, id_comment_target = :idCommentTarget, escapp_user = :escappUser WHERE id = :id";
                
        SqlParameterSource vParams = new BeanPropertySqlParameterSource(pComment);

        getNamedJdbcTemplate().update(vSQL, vParams);
    }

    @Override
    public void insertComment(Comment pComment) throws TechnicalException {
    	
        String vSQL = "INSERT INTO comment (id, text, target_type, id_comment_target, escapp_user) VALUES (DEFAULT, :text, :targetType, :idCommentTarget, :escappUser)";

        SqlParameterSource vParams = new BeanPropertySqlParameterSource(pComment);
        
        getNamedJdbcTemplate().update(vSQL, vParams);
    }

    @Override
    public void deleteComment(Integer pCommentId) throws TechnicalException {
        String vSQL = "DELETE FROM comment WHERE id = :id";

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pCommentId);

        getNamedJdbcTemplate().update(vSQL, vParams);
    }
}
