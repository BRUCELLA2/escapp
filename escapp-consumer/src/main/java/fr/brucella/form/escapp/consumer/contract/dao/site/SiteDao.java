package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.SiteSearch;

/**
 * Interface for the Site Data Access Object.
 *
 * @author BRUCELLA2
 */
public interface SiteDao {
    
    /**
     * Get the {@link Site} with the specified id from the datastore.
     * 
     * @param siteId {@link Integer} id of the {@link Site}.
     * 
     * @return the {@link Site} with the specified id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Site} is not found.
     */
    Site getSite(final Integer siteId) throws TechnicalException, NotFoundException;
    
    /**
     * Get the list of all {@link Site} from the datastore.
     * 
     * @return the list of all {@link Site}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Site} is found.
     */
    List<Site> getAllSitesList() throws TechnicalException, NotFoundException;
    
    
    /**
     * Get the list of {@link Site} searched from the datastore.
     * 
     * @param siteSearch the {@link SiteSearch} which represents the site searching criteria.
     * 
     * @return the list of {@link Site} searched.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Site} is found.
     */
    List<Site> getSearchSitesList(final SiteSearch siteSearch) throws TechnicalException, NotFoundException;
    
    /**
     * Update an existing {@link Site} in the datastore.
     * 
     * @param site The {@link Site} with the updated informations to save in datastore.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Site} is not found.
     */
    void updateSite(final Site site) throws TechnicalException, NotFoundException;
    
    
    /**
     * Insert a new {@link Site} in datastore.
     * 
     * @param site The {@link Site} to insert in datastore.
     * 
     * @return the id of the new {@link Site}
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    int insertSite(final Site site) throws TechnicalException;
    
    
    /**
     * Delete the {@link Site} with the specified id in the datastore.
     * 
     * @param siteId {@link Integer} id of the {@link Site}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Site} is not found.
     */
    void deleteSite(final Integer siteId) throws TechnicalException, NotFoundException;
}
