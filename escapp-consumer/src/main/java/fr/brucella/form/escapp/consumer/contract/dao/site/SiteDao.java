package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface SiteDao {
	
	Site getSite(Integer pSiteId) throws TechnicalException;
	
	List<Site> getAllSitesList() throws TechnicalException;
	
	void updateSite(Site pSite) throws TechnicalException;
	
	void insertSite(Site pSite) throws TechnicalException;
	
	void deleteSite(Integer pSiteId) throws TechnicalException;
}
