package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * 
 * @author BRUCELLA2
 */
public interface SiteManager {

	public List<Site> getAllSitesList() throws TechnicalException, FunctionalException;
	
	public Site getSiteById(Integer pSiteId) throws TechnicalException, FunctionalException;
	
	public void modifySite(Site pSite) throws TechnicalException, FunctionalException;
	
	public void addSite(Site pSite) throws TechnicalException, FunctionalException;
	
	public void deleteSite(Site pSite) throws TechnicalException, FunctionalException;
}
