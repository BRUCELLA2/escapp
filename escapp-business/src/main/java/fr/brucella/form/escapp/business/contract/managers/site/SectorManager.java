package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for Sector Manager
 *
 * @author BRUCELLA2
 */
public interface SectorManager {
    
    /**
     * Get the list of {@link Sector} with the specified site id.
     * 
     * @param pSiteId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.site.Site} to
     *        which the {@link Sector} belongs.
     * 
     * @return the list of {@link Sector} with the specified sector id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the sector id is null.
     * @throws NotFoundException - This exception is throws if no {@link Sector} is found.
     */
    public List<Sector> getSectorsSiteList(Integer pSiteId) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Get the {@link Sector} with the specified id
     * 
     * @param pSectorId {@link Integer} id of the {@link Sector}
     * 
     * @return the {@link Sector} with the specified id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the route id is null.
     * @throws NotFoundException - This exception is throws if the {@link Sector} is not found.
     */
    public Sector getSectorById(Integer pSectorId) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Save the modification of the {@link Sector}.
     * 
     * @param pSector the {@link Sector} modified to save
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Sector} pSector is null. -
     *         This exception is throws if the data in the {@link Sector} are not valid.
     * @throws NotFoundException - This exception is throws if the {@link Sector} is not found.
     */
    public void modifySector(Sector pSector) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Validate and add the {@link Sector} to data store. The id will be added to the {@link Sector}
     * give in parameter.
     * 
     * @param pSector the {@link Sector} to add.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Sector} pSector is null. -
     *         This exception is throws if the data in the {@link Sector} are not valid.
     */
    public void addSector(Sector pSector) throws TechnicalException, FunctionalException;
    
    /**
     * Delete the {@link Sector} with the specified id.
     * 
     * @param pSectorId {@link Integer} id of the {@link Sector} to delete.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the route id is null.
     * @throws NotFoundException - This exception is throws if the {@link Sector} is not found.
     */
    public void deleteSector(Integer pSectorId) throws TechnicalException, FunctionalException, NotFoundException;
}
