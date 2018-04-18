package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.SiteManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class SiteManagerImpl extends AbstractManager implements SiteManager{

	@Override
	public List<Site> getAllSitesList() throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site getSiteById(Integer pSiteId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifySite(Site pSite) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSite(Site pSite) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSite(Site pSite) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

}
