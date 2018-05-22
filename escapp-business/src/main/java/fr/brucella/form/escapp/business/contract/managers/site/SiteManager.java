package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.SiteSearch;

/**
 * Interface for Site Manager
 * 
 * @author BRUCELLA2
 */
public interface SiteManager {

	/**
	 * Get the list of all {@link Site}.
	 * 
	 * @return the list of {@link Site}.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if no {@link Site} is found.
	 */
	public List<Site> getAllSitesList() throws TechnicalException, NotFoundException;
	
	/**
	 * Get the {@link Site} with the specified id. 
	 * 
	 * @param pSiteId {@link Integer} id of the {@link Site}
	 * 
	 * @return the {@link Site} with the specified id.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws FunctionalException - This exception is throws if the site id is null.
	 * @throws NotFoundException - This exception is throws if the {@link Site} is not found.
	 */
	public Site getSiteById(Integer pSiteId) throws TechnicalException, FunctionalException, NotFoundException;
	
	/**
	 * Get the list of {@link Site} that match to search criteria.
	 * If no search criteria are provided, the list of all {@link Site} is returned.
	 * 
	 * @param pSiteSearch the search criteria {@link SiteSearch}
	 * 
	 * @return the list of {@link Site} that match to search criteria or the list of all {@link Site} if no search criteria are provided
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if no {@link Site} is found.
	 * @throws FunctionalException - This exception is throws if the data of the search criteria {@link SiteSearch} are not valid.
	 */
	public List<Site> getSearchSitesList(SiteSearch pSiteSearch) throws TechnicalException, NotFoundException, FunctionalException;
	
	/**
	 * Save the modification of the {@link Site}.
	 * 
	 * @param pSite the {@link Site} modified to save
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws FunctionalException - This exception is throws if the {@link Site} is null.
	 * 							   - This exception is throws if the data in the {@link Site} are not valid.
	 * @throws NotFoundException - This exception is throws if the {@link Site} is not found.
	 */
	public void modifySite(Site pSite) throws TechnicalException, FunctionalException, NotFoundException;
	
	/**
	 * Validate and add the {@link Site} to data store.
	 * The id will be added to the {@link Site} give in parameter.
	 * 
	 * @param pSite the {@link Site} to add.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws FunctionalException - This exception is throws if the {@link Site} pSite is null.
	 * 							   - This exception is throws if the data in the {@link Site} are not valid.
	 */
	public void addSite(Site pSite) throws TechnicalException, FunctionalException;
	
	/**
	 * Delete the {@link Site} with the specified id.
	 * 
	 * @param pSiteId {@link Integer} id of the {@link Site} to delete.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws FunctionalException - This exception is throws if the site id is null.
	 * @throws NotFoundException - This exception is throws if the {@link Site} is not found.
	 */
	public void deleteSite(Integer pSiteId) throws TechnicalException, FunctionalException, NotFoundException;
}
