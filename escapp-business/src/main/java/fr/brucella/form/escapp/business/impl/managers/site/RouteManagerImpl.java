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
 * The Route Manager.
 *
 * @author BRUCELLA2
 */
@Component
public class RouteManagerImpl extends AbstractManager implements RouteManager {
  
  // ----- Logger
  /**
   * Route Manager logger.
   */
  private static final Log LOG = LogFactory.getLog(RouteManagerImpl.class);
  
  
  // ----- Methods
  
  /**
   * @see RouteManager#getRoutesSectorList(Integer)
   */
  @Override
  public List<Route> getRoutesSectorList(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (sectorId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector id= " + sectorId);
      }
      throw new FunctionalException("L'identifiant du Secteur est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getRouteDao().getRoutesList(sectorId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector id= " + sectorId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see RouteManager#getRouteById(Integer)
   */
  @Override
  public Route getRouteById(final Integer routeId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (routeId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("route id= " + routeId);
      }
      throw new FunctionalException("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getRouteDao().getRoute(routeId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("route id= " + routeId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
    
  }
  
  /**
   * @see RouteManager#modifyRoute(Route)
   */
  @Override
  public void modifyRoute(final Route route) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (route == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("route = null");
      }
      throw new FunctionalException("Aucune modification n'a été transmise (Voie vide) - Echec de la mise à jour");
    }
    
    final Set<ConstraintViolation<Route>> violations = this.getConstraintValidator().validate(route);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Route> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("route = " + route.toString());
      }
      throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(violations));
    }
    
    try {
      this.getDaoFactory().getRouteDao().updateRoute(route);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("route = " + route.toString());
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see RouteManager#addRoute(Route)
   */
  @Override
  public void addRoute(final Route route) throws TechnicalException, FunctionalException {
    
    if (route == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("route = null");
      }
      throw new FunctionalException("Aucune voie n'a été transmise (Voie vide) - Echec de l'ajout");
    }
    
    final Set<ConstraintViolation<Route>> violations = this.getConstraintValidator().validate(route);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Route> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("route = " + route.toString());
      }
      throw new FunctionalException("La voie à ajouter n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      final int newRouteId = this.getDaoFactory().getRouteDao().insertRoute(route);
      route.setId(newRouteId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see RouteManager#deleteRoute(Integer)
   */
  @Override
  public void deleteRoute(final Integer routeId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (routeId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("route id= " + routeId);
      }
      throw new FunctionalException("L'identifiant de la voie à supprimer est incorrect (Identifiant null) - Echec de la suppression");
    }
    
    try {
      this.getDaoFactory().getRouteDao().deleteRoute(routeId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("route id= " + routeId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
    
  }
  
}
