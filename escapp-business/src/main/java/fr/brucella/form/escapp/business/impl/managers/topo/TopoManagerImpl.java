package fr.brucella.form.escapp.business.impl.managers.topo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.business.impl.managers.site.SiteManagerImpl;
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
    
    Log log = LogFactory.getLog(SiteManagerImpl.class);
    
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
    public List<Topo> getSearchToposList(TopoSearch pTopoSearch) throws TechnicalException, NotFoundException, FunctionalException {
        
        if (pTopoSearch == null) {
            return this.getAllToposList();
        }
        
        Set<ConstraintViolation<TopoSearch>> vViolations = this.getConstraintValidator().validate(pTopoSearch);
        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<TopoSearch> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("Les critères de recherche ne sont pas valides", new ConstraintViolationException(vViolations));
        }
        try {
            return this.getDaoFactory().getTopoDao().getSearchToposList(pTopoSearch);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
        
    }
    
    /**
     * @see TopoManager#getOwnerToposList(Integer)
     */
    @Override
    public List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pOwnerId == null) {
            throw new FunctionalException("L'identifiant du propriétaire des topos est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            return this.getDaoFactory().getTopoDao().getOwnerToposList(pOwnerId);
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
    public List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pBorrowerId == null) {
            throw new FunctionalException("L'identifiant de l'emprunteur des topos est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            return this.getDaoFactory().getTopoDao().getBorrowerToposList(pBorrowerId);
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
    public Topo getTopoById(Integer pTopoId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pTopoId == null) {
            throw new FunctionalException("L'identifiant du topo est incorrect (Indetifiant vide) - Echec de la recherche");
        }
        
        try {
            Topo vTopo = this.getDaoFactory().getTopoDao().getTopo(pTopoId);
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
    public void setBorrowable(Boolean pBorrowable, Integer pUserId, Topo pTopo) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pBorrowable == null) {
            throw new FunctionalException("Aucune modification n'a été transmise (Propriété empruntable vide) - Echec de la mise à jour");
        }
        if (pUserId == null) {
            throw new FunctionalException("L'utilisateur demandant la modification n'a pas été transmis (Utilisateur vide) - Echec de la mise à jour");
        }
        if (pTopo == null) {
            throw new FunctionalException("Le topo à modifier n'a pas été transmis (topo vide) - Echec de la mise à jour");
        }
        
        if (pTopo.getOwner() != pUserId) {
            throw new FunctionalException(
                    "Seul le propriétaire du topo peut le modifier. L'utilisateur n'est pas le propriétaire du topo - Echec de la modification");
        }
        
        Boolean oldBorrowable = pTopo.isIsBorrowable();
        pTopo.setBorrowable(pBorrowable);
        try {
            this.getDaoFactory().getTopoDao().updateTopo(pTopo);
        } catch (TechnicalException pException) {
            pTopo.setBorrowable(oldBorrowable);
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            pTopo.setBorrowable(oldBorrowable);
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#borrowTopo(Topo, Integer, User)
     */
    @Override
    public Topo borrowTopo(Topo pTopo, Integer pNbDays, User pBorrower) throws TechnicalException, FunctionalException, NotFoundException {
        
        int vNbDaysBorrowMax = 14;
        
        if (pTopo == null) {
            throw new FunctionalException("Aucun topo à emprunter n'a été transmis (Topo vide) - Echec de l'emprunt");
        }
        if (pNbDays == null) {
            throw new FunctionalException("Aucune durée d'emprunt n'a été transmise (Durée vide) - Echec de l'emprunt");
        }
        if (pBorrower == null) {
            throw new FunctionalException("Aucun emprunteur n'a été transmis (Emprunteur vide) - Echec de l'emprunt");
        }
        
        if (pNbDays > vNbDaysBorrowMax) {
            throw new FunctionalException("Le topo ne peut être emprunté plus de " + vNbDaysBorrowMax + " jours - Echec de l'emprunt");
        }
        if (pNbDays < 1) {
            throw new FunctionalException("Le nombre de jours d'emprunt est incorrect (inférieur à 1 - Echec de l'emprunt");
        }
        if (pTopo.isIsBorrowable() == false || (pTopo.getEndDateBorrow() != null && LocalDateTime.now().compareTo(pTopo.getEndDateBorrow()) < 0)) {
            throw new FunctionalException("Ce topo ne peut être emprunté - Echec de l'emprunt");
        }
        
        LocalDateTime date = LocalDateTime.now();
        date = date.plusDays(pNbDays);
        
        Topo vTopo = pTopo;
        vTopo.setEndDateBorrow(date);
        vTopo.setBorrower(pBorrower.getId());
        
        try {
            this.getDaoFactory().getTopoDao().updateTopo(vTopo);
            return vTopo;
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
    public void addTopo(Topo pTopo) throws TechnicalException, FunctionalException {
        
        if (pTopo == null) {
            throw new FunctionalException("Aucun topo n'a été transmis (Topo vide) - Echec de l'ajout");
        }
        
        Set<ConstraintViolation<Topo>> vViolations = this.getConstraintValidator().validate(pTopo);
        
        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Topo> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("Le topo à ajouter n'est pas valide", new ConstraintViolationException(vViolations));
        }
        
        try {
            int newTopoId = this.getDaoFactory().getTopoDao().insertTopo(pTopo);
            pTopo.setId(newTopoId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see TopoManager#modifyTopo(Topo, User)
     */
    @Override
    public void modifyTopo(Topo pTopo, User pUser) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pTopo == null) {
            throw new FunctionalException("Aucune modification n'a été transmise (Topo vide) - Echec de la modification");
        }
        
        if (pUser == null) {
            throw new FunctionalException("Aucun utilisateur n'est associé à la modification (Utilisateur vide) - Echec de la modification");
        }
        else if (pUser.getId() != pTopo.getOwner()) {
            throw new FunctionalException("Seul le créateur du Topo peut réaliser une modification - Echec de la modification");
        }
        
        Set<ConstraintViolation<Topo>> vViolations = this.getConstraintValidator().validate(pTopo);
        
        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Topo> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(vViolations));
        }
        
        try {
            this.getDaoFactory().getTopoDao().updateTopo(pTopo);
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
    public void deleteTopo(Integer pTopoId, User pUser) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pTopoId == null) {
            throw new FunctionalException("Aucune identifiant n'a été transmis (id vide) - Echec de la suppression");
        }
        
        if (pUser == null) {
            throw new FunctionalException("Aucun utilisateur n'est associé à la suppression (Utilisateur vide) - Echec de la suppression");
        }
        
        try {
            Topo vTopo = this.getTopoById(pTopoId);
            if (pUser.getId() != vTopo.getOwner()) {
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
            comments = this.getDaoFactory().getCommentDao().getCommentsList("Topo", pTopoId);
        } catch (TechnicalException pException) {
            throw new TechnicalException("Un problème technique empêche la suppression d'un commentaire du topo à supprimer - Echec de la suppression.",
                    pException);
        } catch (NotFoundException pException) {
            comments = null;
        }
        
        try {
            if (comments != null) {
                for (Comment comment : comments) {
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
            this.getDaoFactory().getTopoDao().deleteTopo(pTopoId);
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
     * @param pTopo the {@link Topo} to check and clear if needed.
     * 
     * @return the {@link Topo} checked and cleared if needed.
     * 
     * @throws TechnicalException - - wraps technical exception caused during data access.
     */
    private Topo clearBorrow(Topo pTopo) throws TechnicalException {
        
        Topo vTopo = pTopo;
        if (vTopo != null) {
            if (vTopo.getEndDateBorrow() != null && LocalDateTime.now().compareTo(vTopo.getEndDateBorrow()) > 0) {
                vTopo.setBorrower(null);
                vTopo.setEndDateBorrow(null);
                try {
                    this.getDaoFactory().getTopoDao().updateTopo(vTopo);
                } catch (TechnicalException pException) {
                    throw new TechnicalException("Un problème technique lors de la mise à jour de l'emprunteur du topo est survenu - Echec de la mise à jour");
                } catch (NotFoundException pException) {
                    throw new TechnicalException(
                            "Un problème technique lors de la mise à jour de l'emprunteur du topo est survenu (Topo non trouvé) - Echec de la mise à jour");
                }
            }
        }
        else {
            throw new TechnicalException("Un problème lors de la mise à jour de l'emprunteur du topo est survenu (Topo vide)");
        }
        
        return vTopo;
    }
    
}
