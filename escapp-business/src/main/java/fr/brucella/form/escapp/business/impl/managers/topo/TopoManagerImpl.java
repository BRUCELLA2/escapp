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
 * The Topo Manager
 *
 * @author BRUCELLA2
 */
@Component
public class TopoManagerImpl extends AbstractManager implements TopoManager {
    

    /**
     * Topo Manager logger
     */
    private static final Log LOG = LogFactory.getLog(TopoManagerImpl.class);
    
    /**
     * @see TopoManager#getAllToposList()
     */
    @Override
    public List<Topo> getAllToposList() throws TechnicalException, NotFoundException {
        
        try {
            return this.getDaoFactory().getTopoDao().getAllToposList();
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#getSearchToposList(TopoSearch)
     */
    @Override
    public List<Topo> getSearchToposList(final TopoSearch topoSearch) throws TechnicalException, NotFoundException, FunctionalException {
        
        List<Topo> toposList;
        
        if (topoSearch == null) {
            toposList = this.getAllToposList();
        }
        else {
            final Set<ConstraintViolation<TopoSearch>> vViolations = this.getConstraintValidator().validate(topoSearch);
            if (!vViolations.isEmpty()) {
                for (final ConstraintViolation<TopoSearch> violation : vViolations) {
                    LOG.debug(violation.getMessage());
                }
                throw new FunctionalException("Les critères de recherche ne sont pas valides", new ConstraintViolationException(vViolations));
            }
            try {
                toposList = this.getDaoFactory().getTopoDao().getSearchToposList(topoSearch);
            } catch (TechnicalException pException) {
                throw new TechnicalException(pException.getMessage(), pException);
            } catch (NotFoundException pException) {
                throw new NotFoundException(pException.getMessage(), pException);
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
            throw new FunctionalException("L'identifiant du propriétaire des topos est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            return this.getDaoFactory().getTopoDao().getOwnerToposList(ownerId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#getBorrowerToposList(Integer)
     */
    @Override
    public List<Topo> getBorrowerToposList(final Integer borrowerId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (borrowerId == null) {
            throw new FunctionalException("L'identifiant de l'emprunteur des topos est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            return this.getDaoFactory().getTopoDao().getBorrowerToposList(borrowerId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
        
    }
    
    /**
     * @see TopoManager#getTopoById(Integer)
     */
    @Override
    public Topo getTopoById(final Integer topoId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (topoId == null) {
            throw new FunctionalException("L'identifiant du topo est incorrect (Indetifiant vide) - Echec de la recherche");
        }
        
        try {
            final Topo vTopo = this.getDaoFactory().getTopoDao().getTopo(topoId);
            return this.clearBorrow(vTopo);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#setBorrowable(Boolean, Integer, Topo)
     */
    @Override
    public void setBorrowable(final Boolean borrowable, final Integer userId, final Topo topo) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (borrowable == null) {
            throw new FunctionalException("Aucune modification n'a été transmise (Propriété empruntable vide) - Echec de la mise à jour");
        }
        if (userId == null) {
            throw new FunctionalException("L'utilisateur demandant la modification n'a pas été transmis (Utilisateur vide) - Echec de la mise à jour");
        }
        if (topo == null) {
            throw new FunctionalException("Le topo à modifier n'a pas été transmis (topo vide) - Echec de la mise à jour");
        }
        
        if (topo.getOwner() != userId) {
            throw new FunctionalException(
                    "Seul le propriétaire du topo peut le modifier. L'utilisateur n'est pas le propriétaire du topo - Echec de la modification");
        }
        
        final Boolean oldBorrowable = topo.isIsBorrowable();
        topo.setBorrowable(borrowable);
        try {
            this.getDaoFactory().getTopoDao().updateTopo(topo);
        } catch (TechnicalException pException) {
            topo.setBorrowable(oldBorrowable);
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            topo.setBorrowable(oldBorrowable);
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#borrowTopo(Topo, Integer, User)
     */
    @Override
    public Topo borrowTopo(final Topo topoToBorrow, final Integer nbDays, final User borrower) throws TechnicalException, FunctionalException, NotFoundException {

        
        if (topoToBorrow == null) {
            throw new FunctionalException("Aucun topo à emprunter n'a été transmis (Topo vide) - Echec de l'emprunt");
        }
        if (nbDays == null) {
            throw new FunctionalException("Aucune durée d'emprunt n'a été transmise (Durée vide) - Echec de l'emprunt");
        }
        if (borrower == null) {
            throw new FunctionalException("Aucun emprunteur n'a été transmis (Emprunteur vide) - Echec de l'emprunt");
        }
        
        final int nbDaysBorrowMax = 14;
        
        if (nbDays > nbDaysBorrowMax) {
            throw new FunctionalException("Le topo ne peut être emprunté plus de " + nbDaysBorrowMax + " jours - Echec de l'emprunt");
        }
        
        final int nbDaysBorrowMin = 1;
        
        if (nbDays < nbDaysBorrowMin) {
            throw new FunctionalException("Le nombre de jours d'emprunt est incorrect (inférieur à 1 - Echec de l'emprunt");
        }
        if (topoToBorrow.isIsBorrowable() == false || (topoToBorrow.getEndDateBorrow() != null && LocalDateTime.now().compareTo(topoToBorrow.getEndDateBorrow()) < 0)) {
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
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#addTopo(Topo)
     */
    @Override
    public void addTopo(final Topo topo) throws TechnicalException, FunctionalException {
        
        if (topo == null) {
            throw new FunctionalException("Aucun topo n'a été transmis (Topo vide) - Echec de l'ajout");
        }
        
        final Set<ConstraintViolation<Topo>> vViolations = this.getConstraintValidator().validate(topo);
        
        if (!vViolations.isEmpty()) {
            for (final ConstraintViolation<Topo> violation : vViolations) {
                LOG.debug(violation.getMessage());
            }
            throw new FunctionalException("Le topo à ajouter n'est pas valide", new ConstraintViolationException(vViolations));
        }
        
        try {
            final int newTopoId = this.getDaoFactory().getTopoDao().insertTopo(topo);
            topo.setId(newTopoId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#modifyTopo(Topo, User)
     */
    @Override
    public void modifyTopo(final Topo topo, final User user) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (topo == null) {
            throw new FunctionalException("Aucune modification n'a été transmise (Topo vide) - Echec de la modification");
        }
        
        if (user == null) {
            throw new FunctionalException("Aucun utilisateur n'est associé à la modification (Utilisateur vide) - Echec de la modification");
        }
        else if (user.getId() != topo.getOwner()) {
            throw new FunctionalException("Seul le créateur du Topo peut réaliser une modification - Echec de la modification");
        }
        
        final Set<ConstraintViolation<Topo>> vViolations = this.getConstraintValidator().validate(topo);
        
        if (!vViolations.isEmpty()) {
            for (final ConstraintViolation<Topo> violation : vViolations) {
                LOG.debug(violation.getMessage());
            }
            throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(vViolations));
        }
        
        try {
            this.getDaoFactory().getTopoDao().updateTopo(topo);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#deleteTopo(Integer, User)
     */
    @Override
    public void deleteTopo(final Integer topoId, final User user) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (topoId == null) {
            throw new FunctionalException("Aucune identifiant n'a été transmis (id vide) - Echec de la suppression");
        }
        
        if (user == null) {
            throw new FunctionalException("Aucun utilisateur n'est associé à la suppression (Utilisateur vide) - Echec de la suppression");
        }
        
        try {
            final Topo topo = this.getTopoById(topoId);
            if (user.getId() != topo.getOwner()) {
                throw new FunctionalException("Seul le créateur du Topo peut réaliser la suppression - Echec de la suppression");
            }
        } catch (NotFoundException pException) {
            throw new FunctionalException("Le Topo a supprimer n'a pu être trouvé - Echec de la suppression", pException);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique empêche de vérifier quel est l'utilisateur ayant créé le commentaire - Echec de la suppression",
                    pException);
        }
        
        List<Comment> comments;
        try {
            comments = this.getDaoFactory().getCommentDao().getCommentsList("Topo", topoId);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique empêche la suppression d'un commentaire du topo à supprimer - Echec de la suppression.",
                    pException);
        } catch (NotFoundException pException) {
            comments = new ArrayList<>();
        }
        
        try {
            if (!comments.isEmpty()) {
                for (final Comment comment : comments) {
                    this.getDaoFactory().getCommentDao().deleteComment(comment.getId());
                }
            }
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique empêche la suppression d'un commentaire du topo à supprimer - Echec de la suppression.",
                    pException);
        } catch (NotFoundException pException) {
            throw new TechnicalException("Un commentaire associé au Topo n'a pas été trouvé - Echec de la suppression", pException);
        }
        
        try {
            this.getDaoFactory().getTopoDao().deleteTopo(topoId);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique empêche la suppression du topo - Echec de la suppression.", pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException("Le topo à supprimer n'a pas été trouvé - Echec de la suppression.", pException);
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
                } catch (TechnicalException pException) {
                    throw new TechnicalException("Un problème technique lors de la mise à jour de l'emprunteur du topo est survenu - Echec de la mise à jour", pException);
                } catch (NotFoundException pException) {
                    throw new TechnicalException(
                            "Un problème technique lors de la mise à jour de l'emprunteur du topo est survenu (Topo non trouvé) - Echec de la mise à jour", pException);
                }
            }
        }
        else {
            throw new TechnicalException("Un problème lors de la mise à jour de l'emprunteur du topo est survenu (Topo vide)");
        }
        
        return topo;
    }
    
}
