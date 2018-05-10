package fr.brucella.form.escapp.consumer.contract.dao.comment;

import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;


/**
 * Interface for Comment Data Access Object.
 * 
 * @author BRUCELLA2
 */
public interface CommentDao {

	/**
	 * Get the {@link Comment} with the specified id from the datastore.
	 * 
	 * @param pCommentId {@link Integer} id of the {@link Comment}.
	 * 
	 * @return the {@link Comment} with the specified id.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Comment} is not found.
	 */
    Comment getComment(Integer pCommentId) throws TechnicalException, NotFoundException;

    
    /**
     * Get a list of {@link Comment} with the specified target's type and id from the datastore.
     * 
     * @param pTargetType {@link String} which represents the Target Type
     * 					  Value can be one of these : "Length", "Route", "Sector", "Site" or "Topo".
     * @param pIdCommentTarget {@link Integer} id of the target which is commented.
     * 
     * @return a list of {@link Comment} with the specified target's type and id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no {@link Comment} is found.
     */
    List<Comment> getCommentsList(String pTargetType, Integer pIdCommentTarget) throws TechnicalException, NotFoundException;

    
    /**
     * Get a list of {@link Pair} of {@link Comment} with the login of the user who write the comment.
     * Sorted by comment id.
     * 
     * @param pTargetType {@link String} which represents the Target Type
     * 					  Value can be one of these : "Length", "Route", "Sector", "Site" or "Topo".
     * @param pIdCommentTarget {@link Integer} id of the target which is commented.
     * @param pOrder {@link String} "ASC" or "DESC" for a ASCENDING sort or DESCENDING sort by the id of the comment
     * 
     * @return a list of {@link Pair} of {@link Comment} with the login of the user who write the comment and sorted by id of the comment
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no {@link Comment} is found.
     */
    List <Pair<Comment, String>> getCommentsListWithLogin(String pTargetType, Integer pIdCommentTarget, String pOrder) throws TechnicalException, NotFoundException;
    
    /**
     * Update an existing {@link Comment} in the datastore.
     * 
     * @param pComment The {@link Comment} with the updated informations to save in datastore.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Comment} is not found in datastore.
     */
    void updateComment(Comment pComment) throws TechnicalException, NotFoundException;

    
    /**
     * Insert a new {@link Comment} in the datastore.
     * 
     * @param pComment The {@link Comment} to insert in datastore.
     * 
     * @return the id of the new {@link Comment}
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    int insertComment(Comment pComment) throws TechnicalException;

    
    /**
     * Delete the {@link Comment} with the specified id in the datastore.
     * 
     * @param pCommentId {@link Integer} id of the {@link Comment}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Comment} is not found in datastore.
     */
    void deleteComment(Integer pCommentId) throws TechnicalException, NotFoundException;

}
