package fr.brucella.form.escapp.business.impl.managers.comment;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.comment.CommentManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * The Comment Manager
 *
 * @author BRUCELLA2
 */
@Component
public class CommentManagerImpl extends AbstractManager implements CommentManager {
    
    // ----- Logger
    /**
     * Comment Manager logger
     */
    private static final Log                 log             = LogFactory.getLog(CommentManagerImpl.class);
    
    // ----- STRING CONSTANTS
    private static final String SITE            = "site";
    private static final String SECTOR          = "Sector";
    private static final String ROUTE           = "Route";
    private static final String LENGTH          = "Length";
    private static final String TOPO            = "Topo";
    
    /**
     * This string is the message send when no comments is transmetted in parametter.
     */
    private static final String NO_COMMENT_SEND = "Aucun commentaire n'a été transmis (Commentaire vide) - Echec de l'ajout.";
    
    
    // ----- Methods
    
    /**
     * @see CommentManager#getCommentsSiteList(Integer)
     * @see #getCommentsList(Integer, String)
     */
    @Override
    public List<Comment> getCommentsSiteList(final Integer siteId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (siteId == null) {
            throw new FunctionalException("L'identifiant du site est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsList(siteId, SITE);
    }
    
    /**
     * @see CommentManager#getCommentsSiteListWithLogin(Integer, String)
     * @see #getCommentsListWithLogin(Integer, String, String)
     */
    @Override
    public List<Pair<Comment, String>> getCommentsSiteListWithLogin(final Integer siteId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException {
        
        if (siteId == null) {
            throw new FunctionalException("L'identifiant du site est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsListWithLogin(siteId, SITE, order);
    }
    
    /**
     * @see CommentManager#getCommentsSectorList(Integer)
     * @see #getCommentsList(Integer, String)
     */
    @Override
    public List<Comment> getCommentsSectorList(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (sectorId == null) {
            throw new FunctionalException("L'identifiant du secteur est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsList(sectorId, SECTOR);
    }
    
    /**
     * @see CommentManager#getCommentsSectorListWithLogin(Integer, String)
     * @see #getCommentsListWithLogin(Integer, String, String)
     */
    @Override
    public List<Pair<Comment, String>> getCommentsSectorListWithLogin(final Integer sectorId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException {
        
        if (sectorId == null) {
            throw new FunctionalException("L'identifiant du secteur est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsListWithLogin(sectorId, SECTOR, order);
    }
    
    /**
     * @see CommentManager#getCommentsRouteList(Integer)
     * @see #getCommentsList(Integer, String)
     */
    @Override
    public List<Comment> getCommentsRouteList(final Integer routeId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (routeId == null) {
            throw new FunctionalException("L'identifiant de la voie est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsList(routeId, ROUTE);
    }
    
    /**
     * @see CommentManager#getCommentsRouteListWithLogin(Integer, String)
     * @see #getCommentsListWithLogin(Integer, String, String)
     */
    @Override
    public List<Pair<Comment, String>> getCommentsRouteListWithLogin(final Integer routeId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException {
        
        if (routeId == null) {
            throw new FunctionalException("L'identifiant de la voie est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsListWithLogin(routeId, ROUTE, order);
    }
    
    /**
     * @see CommentManager#getCommentsLengthList(Integer)
     * @see #getCommentsList(Integer, String)
     */
    @Override
    public List<Comment> getCommentsLengthList(final Integer lengthId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (lengthId == null) {
            throw new FunctionalException("L'identifiant de la longueur est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsList(lengthId, LENGTH);
        
    }
    
    /**
     * @see CommentManager#getCommentsRouteListWithLogin(Integer, String)
     * @see #getCommentsListWithLogin(Integer, String, String)
     */
    @Override
    public List<Pair<Comment, String>> getCommentsLengthListWithLogin(final Integer lengthId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException {
        
        if (lengthId == null) {
            throw new FunctionalException("L'identifiant de la longueur est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsListWithLogin(lengthId, LENGTH, order);
    }
    
    /**
     * @see CommentManager#getCommentsTopoList(Integer)
     * @see #getCommentsList(Integer, String)
     */
    @Override
    public List<Comment> getCommentsTopoList(final Integer topoId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (topoId == null) {
            throw new FunctionalException("L'identifiant du topo est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsList(topoId, TOPO);
    }
    
    /**
     * @see CommentManager#getCommentsTopoListWithLogin(Integer, String)
     * @see #getCommentsListWithLogin(Integer, String, String)
     */
    @Override
    public List<Pair<Comment, String>> getCommentsTopoListWithLogin(final Integer topoId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException {
        
        if (topoId == null) {
            throw new FunctionalException("L'identifiant du topo est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        return this.getCommentsListWithLogin(topoId, TOPO, order);
    }
    
    /**
     * @see CommentManager#getCommentById(Integer)
     */
    @Override
    public Comment getCommentById(final Integer commentId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (commentId == null) {
            throw new FunctionalException("L'identifiant du commentaire recherché est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            return this.getDaoFactory().getCommentDao().getComment(commentId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see CommentManager#addCommentSite(Comment)
     * @see #addComment(Comment, String)
     */
    @Override
    public void addCommentSite(final Comment comment) throws TechnicalException, FunctionalException {
        
        if (comment == null) {
            throw new FunctionalException(NO_COMMENT_SEND);
        }
        
        final String pTargetType = SITE;
        
        if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(pTargetType)) {
            throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Site) - Echec de l'ajout du commentaire");
        }
        else {
            comment.setTargetType(pTargetType);
        }
        
        try {
            this.getDaoFactory().getSiteDao().getSite(comment.getIdCommentTarget());
        } catch (NotFoundException pException) {
            throw new FunctionalException("Le site cible du commentaire n'a pas été trouvé - Echec de l'ajout du commentaire.", pException);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique pour trouver le site cible est survenu - Echec de l'ajout du commentaire.", pException);
        }
        
        this.addComment(comment);
    }
    
    /**
     * @see CommentManager#addCommentSector(Comment)
     * @see #addComment(Comment, String)
     */
    @Override
    public void addCommentSector(final Comment comment) throws TechnicalException, FunctionalException {
        
        if (comment == null) {
            throw new FunctionalException(NO_COMMENT_SEND);
        }
        
        final String pTargetType = SECTOR;
        
        if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(pTargetType)) {
            throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Secteur) - Echec de l'ajout du commentaire");
        }
        else {
            comment.setTargetType(pTargetType);
        }
        
        try {
            this.getDaoFactory().getSectorDao().getSector(comment.getIdCommentTarget());
        } catch (NotFoundException pException) {
            throw new FunctionalException("Le secteur cible du commentaire n'a pas été trouvé - Echec de l'ajout du commentaire.", pException);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique pour trouver le secteur cible est survenu - Echec de l'ajout du commentaire.", pException);
        }
        
        this.addComment(comment);
    }
    
    /**
     * @see CommentManager#addCommentRoute(Comment)
     * @see #addComment(Comment, String)
     */
    @Override
    public void addCommentRoute(final Comment comment) throws TechnicalException, FunctionalException {
        
        if (comment == null) {
            throw new FunctionalException(NO_COMMENT_SEND);
        }
        
        final String pTargetType = ROUTE;
        
        if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(pTargetType)) {
            throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Voie) - Echec de l'ajout du commentaire");
        }
        else {
            comment.setTargetType(pTargetType);
        }
        
        try {
            this.getDaoFactory().getRouteDao().getRoute(comment.getIdCommentTarget());
        } catch (NotFoundException pException) {
            throw new FunctionalException("La voie cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.", pException);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique pour trouver la voie cible est survenu - Echec de l'ajout du commentaire.", pException);
        }
        
        this.addComment(comment);
    }
    
    /**
     * @see CommentManager#addCommentLength(Comment)
     * @see #addComment(Comment, String)
     */
    @Override
    public void addCommentLength(final Comment comment) throws TechnicalException, FunctionalException {
        
        if (comment == null) {
            throw new FunctionalException(NO_COMMENT_SEND);
        }
        
        final String pTargetType = LENGTH;
        
        if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(pTargetType)) {
            throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Longueur) - Echec de l'ajout du commentaire");
        }
        else {
            comment.setTargetType(pTargetType);
        }
        
        try {
            this.getDaoFactory().getLengthDao().getLength(comment.getIdCommentTarget());
        } catch (NotFoundException pException) {
            throw new FunctionalException("La longueur cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.", pException);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique pour trouver la longueur cible est survenu - Echec de l'ajout du commentaire.", pException);
        }
        
        this.addComment(comment);
    }
    
    /**
     * @see CommentManager#addCommentTopo(Comment)
     * @see #addComment(Comment, String)
     */
    @Override
    public void addCommentTopo(final Comment comment) throws TechnicalException, FunctionalException {
        
        if (comment == null) {
            throw new FunctionalException(NO_COMMENT_SEND);
        }
        
        final String pTargetType = TOPO;
        
        if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(pTargetType)) {
            throw new FunctionalException("Le type de la cible du commentaire est différent de celui attentdu (Topo) - Echec de l'ajout du commentaire");
        }
        else {
            comment.setTargetType(pTargetType);
        }
        
        try {
            this.getDaoFactory().getTopoDao().getTopo(comment.getIdCommentTarget());
        } catch (NotFoundException pException) {
            throw new FunctionalException("Le topo cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.", pException);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique pour trouver le topo est survenu - Echec de l'ajout du commentaire.", pException);
        }
        
        this.addComment(comment);
    }
    
    /**
     * @see CommentManager#deleteComment(Integer)
     */
    @Override
    public void deleteComment(final Integer commentId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (commentId == null) {
            throw new FunctionalException("L'identifiant du commentaire à supprimer est incorrect (Identifiant vide) - Echec de la suppression");
        }
        
        try {
            this.getDaoFactory().getCommentDao().deleteComment(commentId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see CommentManager#modifyComment(Comment, User)
     */
    @Override
    public void modifyComment(final Comment comment, final User user) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (comment == null) {
            throw new FunctionalException(NO_COMMENT_SEND);
        }
        
        if (user == null) {
            throw new FunctionalException("Aucun utilisateur n'est associé à la modification (Utilisateur vide) - Echec de la modification");
        }
        else {
            try {
                if (user != this.getDaoFactory().getUserDao().getUserById(comment.getEscappUser())) {
                    throw new FunctionalException(
                            "L'utilisateur demandant la modification ne correspond pas à celui qui a créé le commentaire - Echec de la modification");
                }
            } catch (NotFoundException pException) {
                throw new FunctionalException(
                        "Seul le créateur du commentaire peut réaliser une modification. L'utilisateur ayant créé le commentaire n'a pu être trouvé - Echec de la modification",
                        pException);
            } catch (TechnicalException pException) {
                throw new TechnicalException(
                        "Un problème technique empêche de vérifier quel est l'utilisateur ayant créé le commentaire - Echec de la modificaiton", pException);
            }
        }
        
        final Set<ConstraintViolation<Comment>> vViolations = this.getConstraintValidator().validate(comment);
        
        if (!vViolations.isEmpty()) {
            for (final ConstraintViolation<Comment> violation : vViolations) {
                log.debug(violation.getMessage());
            }
            throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(vViolations));
        }
        
        try {
            this.getDaoFactory().getCommentDao().updateComment(comment);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
        
    }
    
    /**
     * Get the {@link List} of the {@link Comment} for the specified Target Type and id.
     * 
     * @param idCommentTarget {@link Integer} id of the target which is commented.
     * @param targetType {@link String} which represents the Target Type Value can be one of these :
     *        "Length", "Route", "Sector", "Site" or "Topo".
     * 
     * @return a list of {@link Comment} with the specified target's type and id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Comment} is found.
     */
    private List<Comment> getCommentsList(final Integer idCommentTarget, final String targetType) throws TechnicalException, NotFoundException {
        
        try {
            return this.getDaoFactory().getCommentDao().getCommentsList(targetType, idCommentTarget);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * Get a list of {@link Pair} of {@link Comment} with the login of the user who write the comment.
     * 
     * @param idCommentTarget {@link Integer} id of the target which is commented.
     * @param targetType {@link String} which represents the Target Type Value can be one of these :
     *        "Length", "Route", "Sector", "Site" or "Topo".
     * @param order {@link String} "ASC" or "DESC" for a ASCENDING sort or DESCENDING sort by the id of
     *        the comment
     * 
     * @return a list of {@link Pair} of {@link Comment} with the login of the user who write the
     *         comment and sorted by id of the comment.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Comment} is found.
     */
    private List<Pair<Comment, String>> getCommentsListWithLogin(final Integer idCommentTarget, final String targetType, final String order)
            throws TechnicalException, NotFoundException {
        
        String vOrder;
        if (StringUtils.isEmpty(order)) {
            vOrder = "DESC";
        }
        else {
            vOrder = order;
        }
        
        try {
            return this.getDaoFactory().getCommentDao().getCommentsListWithLogin(targetType, idCommentTarget, vOrder);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * Validate and add the {@link Comment} to the data store.
     * 
     * @param comment the {@link Comment} to add.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - this exception is throws if the data in the {@link Comment} are not
     *         valide.
     */
    private void addComment(final Comment comment) throws TechnicalException, FunctionalException {
        
        final Set<ConstraintViolation<Comment>> vViolations = this.getConstraintValidator().validate(comment);
        
        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Comment> violation : vViolations) {
                log.debug(violation.getMessage());
            }
            throw new FunctionalException("Le commentaire à ajouter n'est pas valide", new ConstraintViolationException(vViolations));
        }
        
        try {
            final int newCommentId = this.getDaoFactory().getCommentDao().insertComment(comment);
            comment.setId(newCommentId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
    }
    
}
