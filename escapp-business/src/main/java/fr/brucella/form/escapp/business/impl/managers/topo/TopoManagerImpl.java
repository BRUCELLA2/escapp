package fr.brucella.form.escapp.business.impl.managers.topo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.TopoSearch;

/**
 * The Topo Manager.
 *
 * @author BRUCELLA2
 */
@Component
public class TopoManagerImpl extends AbstractManager implements TopoManager {
  
  
  /**
   * Topo Manager logger.
   */
  private static final Log LOG = LogFactory.getLog(TopoManagerImpl.class);
  
  /**
   * @see TopoManager#getAllToposList()
   */
  @Override
  public List<Topo> getAllToposList() throws TechnicalException, NotFoundException {
    
    try {
      return this.getDaoFactory().getTopoDao().getAllToposList();
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see TopoManager#getSearchToposList(TopoSearch)
   */
  @Override
  public List<Topo> getSearchToposList(final TopoSearch topoSearch) throws TechnicalException, NotFoundException, FunctionalException {
    
    List<Topo> toposList;
    
    if (topoSearch == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo search = " + topoSearch);
      }
      toposList = this.getAllToposList();
    }
    else {
      final Set<ConstraintViolation<TopoSearch>> violations = this.getConstraintValidator().validate(topoSearch);
      if (violations.isEmpty() == false) {
        if (LOG.isDebugEnabled()) {
          for (final ConstraintViolation<TopoSearch> violation : violations) {
            LOG.debug(violation.getMessage());
          }
          LOG.debug("topo search = " + topoSearch.toString());
        }
        throw new FunctionalException("Les critères de recherche ne sont pas valides", new ConstraintViolationException(violations));
      }
      try {
        toposList = this.getDaoFactory().getTopoDao().getSearchToposList(topoSearch);
      } catch (TechnicalException exception) {
        LOG.error(exception.getMessage());
        throw new TechnicalException(exception.getMessage(), exception);
      } catch (NotFoundException exception) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("topo search = " + topoSearch.toString());
        }
        LOG.error(exception.getMessage());
        throw new NotFoundException(exception.getMessage(), exception);
      }
    }
    return toposList;
  }
  
  /**
   * @see TopoManager#getOwnerToposList(Integer)
   */
  @Override
  public List<Topo> getOwnerToposList(final Integer ownerId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (ownerId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("owner id = " + ownerId);
      }
      throw new FunctionalException("L'identifiant du propriétaire des topos est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getTopoDao().getOwnerToposList(ownerId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("owner id = " + ownerId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see TopoManager#getBorrowerToposList(Integer)
   */
  @Override
  public List<Topo> getBorrowerToposList(final Integer borrowerId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (borrowerId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("borrower id = " + borrowerId);
      }
      throw new FunctionalException("L'identifiant de l'emprunteur des topos est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getTopoDao().getBorrowerToposList(borrowerId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("borrower id = " + borrowerId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
    
  }
  
  /**
   * @see TopoManager#getTopoById(Integer)
   */
  @Override
  public Topo getTopoById(final Integer topoId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (topoId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo id = " + topoId);
      }
      throw new FunctionalException("L'identifiant du topo est incorrect (Indetifiant vide) - Echec de la recherche");
    }
    
    try {
      final Topo vTopo = this.getDaoFactory().getTopoDao().getTopo(topoId);
      return this.clearBorrow(vTopo);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo id = " + topoId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see TopoManager#setBorrowable(Boolean, Integer, Topo)
   */
  @Override
  public void setBorrowable(final Boolean borrowable, final Integer userId, final Topo topo) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (borrowable == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("borrowable = null");
      }
      throw new FunctionalException("Aucune modification n'a été transmise (Propriété empruntable vide) - Echec de la mise à jour");
    }
    if (userId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("user Id = null");
      }
      throw new FunctionalException("L'utilisateur demandant la modification n'a pas été transmis (Utilisateur vide) - Echec de la mise à jour");
    }
    if (topo == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo = null");
      }
      throw new FunctionalException("Le topo à modifier n'a pas été transmis (topo vide) - Echec de la mise à jour");
    }
    
    if (topo.getOwner() != userId) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo = " + topo.toString());
        LOG.debug("user Id = " + userId);
      }
      throw new FunctionalException(
          "Seul le propriétaire du topo peut le modifier. L'utilisateur n'est pas le propriétaire du topo - Echec de la modification");
    }
    
    final Boolean oldBorrowable = topo.isIsBorrowable();
    topo.setBorrowable(borrowable);
    try {
      this.getDaoFactory().getTopoDao().updateTopo(topo);
    } catch (TechnicalException exception) {
      topo.setBorrowable(oldBorrowable);
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo = " + topo.toString());
        LOG.debug("user Id = " + userId);
        LOG.debug("borrowable = " + borrowable);
      }
      LOG.error(exception.getMessage());
      topo.setBorrowable(oldBorrowable);
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see TopoManager#borrowTopo(Topo, Integer, User)
   */
  @Override
  public Topo borrowTopo(final Topo topoToBorrow, final Integer nbDays, final User borrower) throws TechnicalException, FunctionalException, NotFoundException {
    
    
    if (topoToBorrow == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo to borrow = null");
      }
      throw new FunctionalException("Aucun topo à emprunter n'a été transmis (Topo vide) - Echec de l'emprunt");
    }
    if (nbDays == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("nb days = null");
      }
      throw new FunctionalException("Aucune durée d'emprunt n'a été transmise (Durée vide) - Echec de l'emprunt");
    }
    if (borrower == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("borrower = null");
      }
      throw new FunctionalException("Aucun emprunteur n'a été transmis (Emprunteur vide) - Echec de l'emprunt");
    }
    
    final int nbDaysBorrowMax = 14;
    
    if (nbDays > nbDaysBorrowMax) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("nb days = " + nbDays);
        LOG.debug("nb days borrow max = " + nbDaysBorrowMax);
      }
      throw new FunctionalException("Le topo ne peut être emprunté plus de " + nbDaysBorrowMax + " jours - Echec de l'emprunt");
    }
    
    final int nbDaysBorrowMin = 1;
    
    if (nbDays < nbDaysBorrowMin) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("nb days = " + nbDays);
        LOG.debug("nb days borrow min = " + nbDaysBorrowMin);
      }
      throw new FunctionalException("Le nombre de jours d'emprunt est incorrect (inférieur à 1 - Echec de l'emprunt");
    }
    if (topoToBorrow.isIsBorrowable() == false
        || (topoToBorrow.getEndDateBorrow() != null && LocalDateTime.now().compareTo(topoToBorrow.getEndDateBorrow()) < 0)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo to borrow = " + topoToBorrow);
      }
      throw new FunctionalException("Ce topo ne peut être emprunté - Echec de l'emprunt");
    }
    
    LocalDateTime date = LocalDateTime.now();
    date = date.plusDays(nbDays);
    
    final Topo topo = topoToBorrow;
    topo.setEndDateBorrow(date);
    topo.setBorrower(borrower.getId());
    
    try {
      this.getDaoFactory().getTopoDao().updateTopo(topo);
      return topo;
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo to borrow = " + topoToBorrow);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see TopoManager#addTopo(Topo)
   */
  @Override
  public void addTopo(final Topo topo) throws TechnicalException, FunctionalException {
    
    if (topo == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo = null");
      }
      throw new FunctionalException("Aucun topo n'a été transmis (Topo vide) - Echec de l'ajout");
    }
    
    final Set<ConstraintViolation<Topo>> violations = this.getConstraintValidator().validate(topo);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Topo> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("topo = " + topo.toString());
      }
      throw new FunctionalException("Le topo à ajouter n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      final int newTopoId = this.getDaoFactory().getTopoDao().insertTopo(topo);
      topo.setId(newTopoId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see TopoManager#modifyTopo(Topo, User)
   */
  @Override
  public void modifyTopo(final Topo topo, final User user) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (topo == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo = null");
      }
      throw new FunctionalException("Aucune modification n'a été transmise (Topo vide) - Echec de la modification");
    }
    
    if (user == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("user = null");
      }
      throw new FunctionalException("Aucun utilisateur n'est associé à la modification (Utilisateur vide) - Echec de la modification");
    }
    else if (user.getId() != topo.getOwner()) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo = " + topo.toString());
        LOG.debug("user = " + user.toString());
      }
      throw new FunctionalException("Seul le créateur du Topo peut réaliser une modification - Echec de la modification");
    }
    
    final Set<ConstraintViolation<Topo>> violations = this.getConstraintValidator().validate(topo);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Topo> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("topo = " + topo.toString());
        LOG.debug("user = " + user.toString());
      }
      throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(violations));
    }
    
    try {
      this.getDaoFactory().getTopoDao().updateTopo(topo);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo = " + topo.toString());
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see TopoManager#deleteTopo(Integer, User)
   */
  @Override
  public void deleteTopo(final Integer topoId, final User user) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (topoId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo id = null");
      }
      throw new FunctionalException("Aucune identifiant n'a été transmis (id vide) - Echec de la suppression");
    }
    
    if (user == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("user = null");
      }
      throw new FunctionalException("Aucun utilisateur n'est associé à la suppression (Utilisateur vide) - Echec de la suppression");
    }
    
    try {
      final Topo topo = this.getTopoById(topoId);
      if (user.getId() != topo.getOwner()) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("topo id = " + topoId);
          LOG.debug("user = " + user);
        }
        throw new FunctionalException("Seul le créateur du Topo peut réaliser la suppression - Echec de la suppression");
      }
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo id = " + topoId);
      }
      LOG.error(exception.getMessage());
      throw new FunctionalException("Le Topo a supprimer n'a pu être trouvé - Echec de la suppression", exception);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique empêche de vérifier quel est l'utilisateur ayant créé le commentaire - Echec de la suppression",
          exception);
    }
    
    List<Comment> comments;
    try {
      comments = this.getDaoFactory().getCommentDao().getCommentsList("Topo", topoId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique empêche la suppression d'un commentaire du topo à supprimer - Echec de la suppression.", exception);
    } catch (NotFoundException exception) {
      LOG.debug(exception.getMessage());
      comments = new ArrayList<>();
    }
    
    try {
      if (!comments.isEmpty()) {
        for (final Comment comment : comments) {
          this.getDaoFactory().getCommentDao().deleteComment(comment.getId());
        }
      }
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique empêche la suppression d'un commentaire du topo à supprimer - Echec de la suppression.", exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo id = " + topoId);
        LOG.debug("comments = " + comments);
      }
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un commentaire associé au Topo n'a pas été trouvé - Echec de la suppression", exception);
    }
    
    try {
      this.getDaoFactory().getTopoDao().deleteTopo(topoId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException("Un problème technique empêche la suppression du topo - Echec de la suppression.", exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo id = " + topoId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException("Le topo à supprimer n'a pas été trouvé - Echec de la suppression.", exception);
    }
    
  }
  
  
  /**
   * This method check the {@link Topo} if the {@link Topo} is borrow by someone. If the date of end
   * of borrow has passed, end date of borrow and borrower are clear (modify to null).
   *
   * @param topoToClear the {@link Topo} to check and clear if needed.
   *
   * @return the {@link Topo} checked and cleared if needed.
   *
   * @throws TechnicalException - - wraps technical exception caused during data access.
   */
  private Topo clearBorrow(final Topo topoToClear) throws TechnicalException {
    
    final Topo topo = topoToClear;
    if (topo != null) {
      if (topo.getEndDateBorrow() != null && LocalDateTime.now().compareTo(topo.getEndDateBorrow()) > 0) {
        topo.setBorrower(null);
        topo.setEndDateBorrow(null);
        try {
          this.getDaoFactory().getTopoDao().updateTopo(topo);
        } catch (TechnicalException exception) {
          LOG.error(exception.getMessage());
          throw new TechnicalException("Un problème technique lors de la mise à jour de l'emprunteur du topo est survenu - Echec de la mise à jour", exception);
        } catch (NotFoundException exception) {
          LOG.error(exception.getMessage());
          if (LOG.isDebugEnabled()) {
            LOG.debug("topo to clear = " + topoToClear.toString());
          }
          LOG.error(exception.getMessage());
          throw new TechnicalException(
              "Un problème technique lors de la mise à jour de l'emprunteur du topo est survenu (Topo non trouvé) - Echec de la mise à jour", exception);
        }
      }
    }
    else {
      if (LOG.isDebugEnabled()) {
        LOG.debug("topo to clear = null ");
      }
      throw new TechnicalException("Un problème lors de la mise à jour de l'emprunteur du topo est survenu (Topo vide)");
    }
    
    return topo;
  }
  
}
