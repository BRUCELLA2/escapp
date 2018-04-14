package fr.brucella.form.escapp.consumer.contract.dao.comment;

import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

public interface CommentDao {

    Comment getComment(Integer pCommentId) throws TechnicalException;

    List<Comment> getCommentsList(String pTargetType, Integer pIdCommentTarget) throws TechnicalException;

    void updateComment(Comment pComment) throws TechnicalException;

    void insertComment(Comment pComment) throws TechnicalException;

    void deleteComment(Integer pCommentId) throws TechnicalException;

}
