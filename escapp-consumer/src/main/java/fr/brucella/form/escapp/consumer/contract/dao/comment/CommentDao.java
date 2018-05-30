package fr.brucella.form.escapp.consumer.contract.dao.comment;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;


/**
 * Interface for Comment Data Access Object.
 *
 * @author BRUCELLA2
 */
public interface CommentDao {

    /**
     * Get the {@link Comment} with the specified id from the datastore.
     *
     * @param commentId {@link Integer} id of the {@link Comment}.
     *
     * @return the {@link Comment} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Comment} is not found.
     */
    Comment getComment(final Integer commentId) throws TechnicalException, NotFoundException;


    /**
     * Get a list of {@link Comment} with the specified target's type and id from the datastore.
     *
     * @param targetType {@link String} which represents the Target Type Value can be one of these :
     *        "Length", "Route", "Sector", "Site" or "Topo".
     * @param idCommentTarget {@link Integer} id of the target which is commented.
     *
     * @return a list of {@link Comment} with the specified target's type and id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Comment} is found.
     */
    List<Comment> getCommentsList(final String targetType, final Integer idCommentTarget) throws TechnicalException, NotFoundException;


    /**
     * Get a list of {@link Pair} of {@link Comment} with the login of the user who write the comment.
     * Sorted by comment id.
     *
     * @param targetType {@link String} which represents the Target Type Value can be one of these :
     *        "Length", "Route", "Sector", "Site" or "Topo".
     * @param idCommentTarget {@link Integer} id of the target which is commented.
     * @param order {@link String} "ASC" or "DESC" for a ASCENDING sort or DESCENDING sort by the id of
     *        the comment
     *
     * @return a list of {@link Pair} of {@link Comment} with the login of the user who write the
     *         comment and sorted by id of the comment
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Comment} is found.
     */
    List<Pair<Comment, String>> getCommentsListWithLogin(final String targetType, final Integer idCommentTarget, final String order)
            throws TechnicalException, NotFoundException;
    
    /**
     * Update an existing {@link Comment} in the datastore.
     *
     * @param comment The {@link Comment} with the updated informations to save in datastore.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Comment} is not found in datastore.
     */
    void updateComment(final Comment comment) throws TechnicalException, NotFoundException;


    /**
     * Insert a new {@link Comment} in the datastore.
     *
     * @param comment The {@link Comment} to insert in datastore.
     *
     * @return the id of the new {@link Comment}
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    int insertComment(final Comment comment) throws TechnicalException;


    /**
     * Delete the {@link Comment} with the specified id in the datastore.
     *
     * @param commentId {@link Integer} id of the {@link Comment}.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Comment} is not found in datastore.
     */
    void deleteComment(final Integer commentId) throws TechnicalException, NotFoundException;

}
