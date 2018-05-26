package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for Length Data Access Object.
 *
 * @author BRUCELLA2
 */
public interface LengthDao {
    
    /**
     * Get the {@link Length} with the specified id from the datastore.
     * 
     * @param lengthId {@link Integer} id of the {@link Length}.
     * 
     * @return the {@link Length} with the specified id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Length} is not found.
     */
    Length getLength(final Integer lengthId) throws TechnicalException, NotFoundException;
    
    
    /**
     * Get a list of {@link Length} with the specified route id from the datastore.
     *
     * @param routeId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.site.Route}
     *        to which the {@link Length} belongs.
     *
     * @return a list of {@link Length} with the specified route id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Length} is found.
     */
    List<Length> getLengthsList(final Integer routeId) throws TechnicalException, NotFoundException;


    /**
     * Update an existing {@link Length} in the datastore.
     *
     * @param length The {@link Length} with the updated informations to save in datastore.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Length} is not found.
     */
    void updateLength(final Length length) throws TechnicalException, NotFoundException;


    /**
     * Insert a new {@link Length} in the datastore.
     *
     * @param length The {@link Length} to insert in datastore.
     *
     * @return the id of the new {@link Length}
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    int insertLength(final Length length) throws TechnicalException;


    /**
     * Delete the {@link Length} with the specified id in the datastore.
     *
     * @param lengthId {@link Integer} id of the {@link Length}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Length} is not found.
     */
    void deleteLength(final Integer lengthId) throws TechnicalException, NotFoundException;
    
}
