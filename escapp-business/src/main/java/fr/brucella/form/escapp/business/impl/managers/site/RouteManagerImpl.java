package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.RouteManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * The Route Manager
 *
 * @author BRUCELLA2
 */
@Component
public class RouteManagerImpl extends AbstractManager implements RouteManager {
    
    // ----- Logger
    private Log log = LogFactory.getLog(RouteManagerImpl.class);
    
    /**
     * @see RouteManager#getRoutesSectorList(Integer)
     */
    @Override
    public List<Route> getRoutesSectorList(Integer pSectorId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pSectorId == null) {
            throw new FunctionalException("L'identifiant du Secteur est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            return this.getDaoFactory().getRouteDao().getRoutesList(pSectorId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see RouteManager#getRouteById(Integer)
     */
    @Override
    public Route getRouteById(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pRouteId == null) {
            throw new FunctionalException("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            return this.getDaoFactory().getRouteDao().getRoute(pRouteId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
        
    }
    
    /**
     * @see RouteManager#modifyRoute(Route)
     */
    @Override
    public void modifyRoute(Route pRoute) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pRoute == null) {
            throw new FunctionalException("Aucune modification n'a été transmise (Voie vide) - Echec de la mise à jour");
        }
        
        Set<ConstraintViolation<Route>> vViolations = this.getConstraintValidator().validate(pRoute);
        
        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Route> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(vViolations));
        }
        
        try {
            this.getDaoFactory().getRouteDao().updateRoute(pRoute);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see RouteManager#addRoute(Route)
     */
    @Override
    public void addRoute(Route pRoute) throws TechnicalException, FunctionalException {
        
        if (pRoute == null) {
            throw new FunctionalException("Aucune voie n'a été transmise (Voie vide) - Echec de l'ajout");
        }
        
        Set<ConstraintViolation<Route>> vViolations = this.getConstraintValidator().validate(pRoute);
        
        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Route> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("La voie à ajouter n'est pas valide", new ConstraintViolationException(vViolations));
        }
        
        try {
            int newRouteId = this.getDaoFactory().getRouteDao().insertRoute(pRoute);
            pRoute.setId(newRouteId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see RouteManager#deleteRoute(Integer)
     */
    @Override
    public void deleteRoute(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (pRouteId == null) {
            throw new FunctionalException("L'identifiant de la voie à supprimer est incorrect (Identifiant null) - Echec de la suppression");
        }
        
        try {
            this.getDaoFactory().getRouteDao().deleteRoute(pRouteId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
        
    }
    
}
