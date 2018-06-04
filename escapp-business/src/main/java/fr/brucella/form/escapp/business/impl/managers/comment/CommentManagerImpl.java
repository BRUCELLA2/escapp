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
 * The Comment Manager.
 *
 * @author BRUCELLA2
 */
@Component
public class CommentManagerImpl extends AbstractManager implements CommentManager {
  
  // ----- Logger
  /**
   * Comment Manager logger.
   */
  private static final Log    LOG             = LogFactory.getLog(CommentManagerImpl.class);
  
  // ----- STRING CONSTANTS
  private static final String SITE            = "Site";
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("site id = " + siteId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("site id = " + siteId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector id = " + sectorId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector id = " + sectorId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("route id = " + routeId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("route id = " + routeId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("length id = " + lengthId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("length id = " + lengthId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo id = " + topoId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo id = " + topoId);
      }
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment id = " + commentId);
      }
      throw new FunctionalException("L'identifiant du commentaire recherché est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getCommentDao().getComment(commentId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment id = " + commentId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see CommentManager#addCommentSite(Comment)
   * @see #addComment(Comment, String)
   */
  @Override
  public void addCommentSite(final Comment comment) throws TechnicalException, FunctionalException {
    
    if (comment == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = null");
      }
      throw new FunctionalException(NO_COMMENT_SEND);
    }
    
    final String pTargetType = SITE;
    
    if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(pTargetType)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
      }
      throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Site) - Echec de l'ajout du commentaire");
    }
    else {
      comment.setTargetType(pTargetType);
    }
    
    try {
      this.getDaoFactory().getSiteDao().getSite(comment.getIdCommentTarget());
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new FunctionalException("Le site cible du commentaire n'a pas été trouvé - Echec de l'ajout du commentaire.", exception);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique pour trouver le site cible est survenu - Echec de l'ajout du commentaire.", exception);
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = null");
      }
      throw new FunctionalException(NO_COMMENT_SEND);
    }
    
    final String targetType = SECTOR;
    
    if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(targetType)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
        LOG.debug("target type = " + targetType);
      }
      throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Secteur) - Echec de l'ajout du commentaire");
    }
    else {
      comment.setTargetType(targetType);
    }
    
    try {
      this.getDaoFactory().getSectorDao().getSector(comment.getIdCommentTarget());
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new FunctionalException("Le secteur cible du commentaire n'a pas été trouvé - Echec de l'ajout du commentaire.", exception);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique pour trouver le secteur cible est survenu - Echec de l'ajout du commentaire.", exception);
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = null");
      }
      throw new FunctionalException(NO_COMMENT_SEND);
    }
    
    final String targetType = ROUTE;
    
    if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(targetType)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
        LOG.debug("target type = " + targetType);
      }
      throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Voie) - Echec de l'ajout du commentaire");
    }
    else {
      comment.setTargetType(targetType);
    }
    
    try {
      this.getDaoFactory().getRouteDao().getRoute(comment.getIdCommentTarget());
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new FunctionalException("La voie cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.", exception);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique pour trouver la voie cible est survenu - Echec de l'ajout du commentaire.", exception);
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = null");
      }
      throw new FunctionalException(NO_COMMENT_SEND);
    }
    
    final String targetType = LENGTH;
    
    if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(targetType)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
        LOG.debug("target type = " + targetType);
      }
      throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Longueur) - Echec de l'ajout du commentaire");
    }
    else {
      comment.setTargetType(targetType);
    }
    
    try {
      this.getDaoFactory().getLengthDao().getLength(comment.getIdCommentTarget());
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new FunctionalException("La longueur cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.", exception);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique pour trouver la longueur cible est survenu - Echec de l'ajout du commentaire.", exception);
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
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = null");
      }
      throw new FunctionalException(NO_COMMENT_SEND);
    }
    
    final String targetType = TOPO;
    
    if (comment.getTargetType() != null && !comment.getTargetType().isEmpty() && !comment.getTargetType().equals(targetType)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
        LOG.debug("target type = " + targetType);
      }
      throw new FunctionalException("Le type de la cible du commentaire est différent de celui attentdu (Topo) - Echec de l'ajout du commentaire");
    }
    else {
      comment.setTargetType(targetType);
    }
    
    try {
      this.getDaoFactory().getTopoDao().getTopo(comment.getIdCommentTarget());
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
      }
      LOG.error(exception.getMessage());
      throw new FunctionalException("Le topo cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.", exception);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique pour trouver le topo est survenu - Echec de l'ajout du commentaire.", exception);
    }
    
    this.addComment(comment);
  }
  
  /**
   * @see CommentManager#deleteComment(Integer)
   */
  @Override
  public void deleteComment(final Integer commentId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (commentId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment id = " + commentId);
      }
      throw new FunctionalException("L'identifiant du commentaire à supprimer est incorrect (Identifiant vide) - Echec de la suppression");
    }
    
    try {
      this.getDaoFactory().getCommentDao().deleteComment(commentId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment id = " + commentId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see CommentManager#modifyComment(Comment, User)
   */
  @Override
  public void modifyComment(final Comment comment, final User user) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (comment == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = null");
      }
      throw new FunctionalException(NO_COMMENT_SEND);
    }
    
    if (user == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("user =  null");
      }
      throw new FunctionalException("Aucun utilisateur n'est associé à la modification (Utilisateur vide) - Echec de la modification");
    }
    else {
      try {
        if (user != this.getDaoFactory().getUserDao().getUserById(comment.getEscappUser())) {
          if (LOG.isDebugEnabled()) {
            LOG.debug("comment = " + comment.toString());
            LOG.debug("user = " + user.toString());
          }
          throw new FunctionalException(
              "L'utilisateur demandant la modification ne correspond pas à celui qui a créé le commentaire - Echec de la modification");
        }
      } catch (NotFoundException exception) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("comment = " + comment.toString());
          LOG.debug("user = " + user.toString());
        }
        LOG.error(exception.getMessage());
        throw new FunctionalException(
            "Seul le créateur du commentaire peut réaliser une modification. L'utilisateur ayant créé le commentaire n'a pu être trouvé - Echec de la modification",
            exception);
      } catch (TechnicalException exception) {
        LOG.error(exception.getMessage());
        throw new TechnicalException("Un problème technique empêche de vérifier quel est l'utilisateur ayant créé le commentaire - Echec de la modificaiton",
            exception);
      }
    }
    
    final Set<ConstraintViolation<Comment>> violations = this.getConstraintValidator().validate(comment);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Comment> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("comment = " + comment.toString());
        LOG.debug("user = " + user.toString());
      }
      throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(violations));
    }
    
    try {
      this.getDaoFactory().getCommentDao().updateComment(comment);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment = " + comment.toString());
        LOG.debug("user = " + user.toString());
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
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
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment target id = " + idCommentTarget);
        LOG.debug("target type = " + targetType);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
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
    
    String commentOrder;
    if (StringUtils.isEmpty(order)) {
      commentOrder = "DESC";
    }
    else {
      commentOrder = order;
    }
    
    try {
      return this.getDaoFactory().getCommentDao().getCommentsListWithLogin(targetType, idCommentTarget, commentOrder);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("comment target id = " + idCommentTarget);
        LOG.debug("target type = " + targetType);
        LOG.debug("order = " + order);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * Validate and add the {@link Comment} to the data store.
   *
   * @param comment the {@link Comment} to add.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - this exception is throws if the data in the {@link Comment} are not
   *         valid.
   */
  private void addComment(final Comment comment) throws TechnicalException, FunctionalException {
    
    final Set<ConstraintViolation<Comment>> violations = this.getConstraintValidator().validate(comment);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Comment> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("comment = " + comment.toString());
      }
      throw new FunctionalException("Le commentaire à ajouter n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      final int newCommentId = this.getDaoFactory().getCommentDao().insertComment(comment);
      comment.setId(newCommentId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
  }
  
}
