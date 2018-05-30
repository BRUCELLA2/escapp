package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for Route Manager
 *
 * @author BRUCELLA2
 */
public interface RouteManager {
  
  /**
   * Get the list of {@link Route} with the specified sector id.
   *
   * @param sectorId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.site.Sector}
   *        to which the {@link Route} belongs.
   *
   * @return the list of {@link Route} with the specified sector id.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the sector id is null.
   * @throws NotFoundException - This exception is throws if no {@link Route} is found.
   */
  List<Route> getRoutesSectorList(Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException;
  
  /**
   * Get the {@link Route} with the specified id.
   *
   * @param routeId {@link Integer} id of the {@link Route}
   *
   * @return the {@link Route} with the specified id.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the route id is null.
   * @throws NotFoundException - This exception is throws if the {@link Route} is not found.
   */
  Route getRouteById(Integer routeId) throws TechnicalException, FunctionalException, NotFoundException;
  
  /**
   * Save the modification of the {@link Route}.
   *
   * @param route the {@link Route} modified to save
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the {@link Route} is null. - This
   *         exception is throws if the data in the {@link Route} are not valide.
   * @throws NotFoundException - This exception is throws if the {@link Route} is not found.
   */
  void modifyRoute(Route route) throws TechnicalException, FunctionalException, NotFoundException;
  
  /**
   * Validate and add the {@link Route} to data store. The id will be added to the {@link Route} give
   * in parameter.
   *
   * @param route the {@link Route} to add.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the {@link Route} route in parameter is
   *         null. - This exception is throws if the data in the {@link Route} are not valid.
   */
  void addRoute(Route route) throws TechnicalException, FunctionalException;
  
  /**
   * Delete the {@link Route} with the specified id.
   *
   * @param routeId {@link Integer} id of the {@link Route} to delete.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the route id is null.
   * @throws NotFoundException - This exception is throws if the {@link Route} is not found.
   */
  void deleteRoute(Integer routeId) throws TechnicalException, FunctionalException, NotFoundException;
}
