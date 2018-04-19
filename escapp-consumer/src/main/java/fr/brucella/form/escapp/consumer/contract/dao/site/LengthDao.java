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
	 * @param pLengthId {@link Integer} id of the {@link Length}.
	 * 
	 * @return the {@link Length} with the specified id.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Length} is not found.
	 */
    Length getLength(Integer pLengthId) throws TechnicalException, NotFoundException;

    
    /**
     * Get a list of {@link Length} with the specified route id from the datastore.
     * 
     * @param pRouteId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.site.Route} to which the {@link Length} belongs.
     * 
     * @return a list of {@link Length} with the specified route id.
     * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and no {@link Length} is found.
     */
    List<Length> getLengthsList(Integer pRouteId) throws TechnicalException, NotFoundException;
    
    
    /**
     * Update an existing {@link Length} in the datastore.
     * 
     * @param pLength The {@link Length} with the updated informations to save in datastore.
     * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Length} is not found.
     */
    void updateLength(Length pLength) throws TechnicalException, NotFoundException;
    
    
    /**
     * Insert a new {@link Length} in the datastore.
     * 
     * @param pLength The {@link Length} to insert in datastore.
     * 
     * @return the id of the new {@link Length}
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    int insertLength(Length pLength) throws TechnicalException;
    
    
    /**
     * Delete the {@link Length} with the specified id in the datastore.
     * 
     * @param pLengthId {@link Integer} id of the {@link Length}.
     * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Length} is not found.
     */
    void deleteLength(Integer pLengthId) throws TechnicalException, NotFoundException;

}
