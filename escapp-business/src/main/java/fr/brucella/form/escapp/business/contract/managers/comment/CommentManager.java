package fr.brucella.form.escapp.business.contract.managers.comment;

import java.util.List;

import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * 
 * @author BRUCELLA2
 */
public interface CommentManager {

	public List<Comment> getCommentsSiteList(Integer pSiteId) throws TechnicalException, FunctionalException;
	
	public List<Comment> getCommentsSectorList(Integer pSectorId) throws TechnicalException, FunctionalException;
	
	public List<Comment> getCommentsRouteList(Integer pRouteId) throws TechnicalException, FunctionalException;
	
	public List<Comment> getCommentsLengthList(Integer pLengthId) throws TechnicalException, FunctionalException;
	
	public Comment getCommentById(Integer pCommentId) throws TechnicalException, FunctionalException;
	
	
	public void addCommentSite(Comment pComment) throws TechnicalException, FunctionalException;
	
	public void addCommentSector(Comment pComment) throws TechnicalException, FunctionalException;
	
	public void addCommentRoute(Comment pComment) throws TechnicalException, FunctionalException;
	
	public void addCommentLength(Comment pComment) throws TechnicalException, FunctionalException;
	
	public void deleteComment(Integer pCommentId) throws TechnicalException, FunctionalException;
	
	public void modifyComment(Comment pComment, User pUser) throws TechnicalException, FunctionalException;
}
