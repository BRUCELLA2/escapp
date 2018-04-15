package fr.brucella.form.escapp.consumer.contract.dao.comment;

import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

public interface CommentDao {

    Comment getComment(Integer pCommentId) throws TechnicalException, NotFoundException;

    List<Comment> getCommentsList(String pTargetType, Integer pIdCommentTarget) throws TechnicalException, NotFoundException;

    void updateComment(Comment pComment) throws TechnicalException, NotFoundException;

    void insertComment(Comment pComment) throws TechnicalException;

    void deleteComment(Integer pCommentId) throws TechnicalException, NotFoundException;

}
