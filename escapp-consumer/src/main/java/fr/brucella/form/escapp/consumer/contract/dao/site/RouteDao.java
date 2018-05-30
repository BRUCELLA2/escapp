package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for the Route Data Access Object.
 *
 * @author BRUCELLA2
 */
public interface RouteDao {


    /**
     * Get the {@link Route} with the specified id from the datastore.
     *
     * @param routeId {@link Integer} id of the {@link Route}.
     *
     * @return the {@link Route} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Route} is not found.
     */
    Route getRoute(final Integer routeId) throws TechnicalException, NotFoundException;


    /**
     * Get a list of {@link Route} with the specified sector id from the datastore.
     *
     * @param sectorId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.site.Sector}
     *        to which the {@link Route} belongs.
     *
     * @return a list of {@link Route} with the specified sector id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Route} is found.
     */
    List<Route> getRoutesList(final Integer sectorId) throws TechnicalException, NotFoundException;


    /**
     * Update an existing {@link Route} in the datastore.
     *
     * @param route The {@link Route} with the updated informations to save in datastore.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Route} is not found.
     */
    void updateRoute(final Route route) throws TechnicalException, NotFoundException;


    /**
     * Insert a new {@link Route} in the datastore.
     *
     * @param route The {@link Route} to insert in datastore.
     *
     * @return the id of the new {@link Route}
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    int insertRoute(final Route route) throws TechnicalException;


    /**
     * Delete the {@link Route} with the specified id in the datastore.
     *
     * @param routeId {@link Integer} id of the {@link Route}.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Route} is not found.
     */
    void deleteRoute(final Integer routeId) throws TechnicalException, NotFoundException;
}
