package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.LengthManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * The Length Manager
 *
 * @author BRUCELL2
 */
@Component
public class LengthManagerImpl extends AbstractManager implements LengthManager {
    
    // ----- Logger
    private Log log = LogFactory.getLog(LengthManagerImpl.class);
    
    /**
     * @see LengthManager#getLengthsRouteList(Integer)
     */
    @Override
    public List<Length> getLengthsRouteList(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pRouteId == null) {
            throw new FunctionalException("L'identifiant de la voie (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            return this.getDaoFactory().getLengthDao().getLengthsList(pRouteId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
        
    }
    
    /**
     * @see LengthManager#modifyLength(Length)
     */
    @Override
    public void modifyLength(Length pLength) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pLength == null) {
            throw new FunctionalException("Aucune modification n'a été transmise (Longueur vide) - Echec de la mise à jour");
        }
        
        Set<ConstraintViolation<Length>> vViolations = this.getConstraintValidator().validate(pLength);
        
        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Length> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(vViolations));
        }
        
        try {
            this.getDaoFactory().getLengthDao().updateLength(pLength);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see LengthManager#addLength(Length)
     */
    @Override
    public void addLength(Length pLength) throws TechnicalException, FunctionalException {
        
        if (pLength == null) {
            throw new FunctionalException("Aucune longueur n'a été transmise (Longueur vide) - Echec de l'ajout");
        }
        
        Set<ConstraintViolation<Length>> vViolations = this.getConstraintValidator().validate(pLength);
        
        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Length> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("La longueur à ajouter n'est pas valide", new ConstraintViolationException(vViolations));
        }
        
        try {
            int newLengthId = this.getDaoFactory().getLengthDao().insertLength(pLength);
            pLength.setId(newLengthId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see LengthManager#deleteLength(Integer)
     */
    @Override
    public void deleteLength(Integer pLengthId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pLengthId == null) {
            throw new FunctionalException("L'identifiant de la longueur à supprimer est incorrect (Identifiant null) - Echec de la suppression");
        }
        
        try {
            this.getDaoFactory().getLengthDao().deleteLength(pLengthId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
}
