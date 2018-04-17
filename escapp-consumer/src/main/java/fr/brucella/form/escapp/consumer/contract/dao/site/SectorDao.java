package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for the Sector Data Access Object.
 * 
 * @author BRUCELLA2
 */
public interface SectorDao {
	
	
	/**
	 * Get the {@link Sector} with the specified id from the datastore.
	 * 
	 * @param pSectorId {@link Integer} id of the {@link Sector}.
	 * 
	 * @return the {@link Sector} with the specified id.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Sector} is not found.
	 */
	Sector getSector(Integer pSectorId) throws TechnicalException, NotFoundException;
	
	
	/**
	 * Get a list of {@link Sector} with the specified site id from the datastore.
	 * 
	 * @param pSiteId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.site.Site} to which the {@link Sector} belongs.
	 * 
	 * @return a list of {@link Sector} with the specified site id.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and no {@link Sector} is found.
	 */
	List<Sector> getSectorsList(Integer pSiteId) throws TechnicalException, NotFoundException;
	
	
	/**
	 * Update an existing {@link Sector} in the datastore.
	 * 
	 * @param pSector The {@link Sector} with the updated informations to save in datastore.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Sector} is not found.
	 */
	void updateSector(Sector pSector) throws TechnicalException, NotFoundException;
	
	
	/**
	 * Insert a new {@link Sector} in the datastore.
	 * 
	 * @param pSector The {@link Sector} to insert in datastore.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 */
	void insertSector(Sector pSector) throws TechnicalException;
	
	
	/**
	 * Delete the {@link Sector} with the specified id in the datastore.
	 * 
	 * @param pSectorId {@link Integer} id of the {@link Sector}.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link Sector} is not found.
	 */
	void deleteSector(Integer pSectorId) throws TechnicalException, NotFoundException;
}
