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
     * @param pSectorId {@link Integer} id of the
     *        {@link fr.brucella.form.escapp.model.beans.site.Sector} to which the {@link Route}
     *        belongs.
     * 
     * @return the list of {@link Route} with the specified sector id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the sector id is null.
     * @throws NotFoundException - This exception is throws if no {@link Route} is found.
     */
    public List<Route> getRoutesSectorList(Integer pSectorId) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Get the {@link Route} with the specified id.
     * 
     * @param pRouteId {@link Integer} id of the {@link Route}
     * 
     * @return the {@link Route} with the specified id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the route id is null.
     * @throws NotFoundException - This exception is throws if the {@link Route} is not found.
     */
    public Route getRouteById(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Save the modification of the {@link Route}.
     * 
     * @param pRoute the {@link Route} modified to save
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Route} is null. - This
     *         exception is throws if the data in the {@link Route} are not valide.
     * @throws NotFoundException - This exception is throws if the {@link Route} is not found.
     */
    public void modifyRoute(Route pRoute) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Validate and add the {@link Route} to data store. The id will be added to the {@link Route} give
     * in parameter.
     * 
     * @param pRoute the {@link Route} to add.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Route} pRoute is null. -
     *         This exception is throws if the data in the {@link Route} are not valid.
     */
    public void addRoute(Route pRoute) throws TechnicalException, FunctionalException;
    
    /**
     * Delete the {@link Route} with the specified id.
     * 
     * @param pRouteId {@link Integer} id of the {@link Route} to delete.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the route id is null.
     * @throws NotFoundException - This exception is throws if the {@link Route} is not found.
     */
    public void deleteRoute(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException;
}
