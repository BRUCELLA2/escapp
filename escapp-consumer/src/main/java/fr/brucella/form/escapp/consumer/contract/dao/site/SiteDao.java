package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface SiteDao {
	
	Site getSite(Integer pSiteId) throws TechnicalException, NotFoundException;
	
	List<Site> getAllSitesList() throws TechnicalException, NotFoundException;
	
	void updateSite(Site pSite) throws TechnicalException, NotFoundException;
	
	void insertSite(Site pSite) throws TechnicalException;
	
	void deleteSite(Integer pSiteId) throws TechnicalException, NotFoundException;
}
