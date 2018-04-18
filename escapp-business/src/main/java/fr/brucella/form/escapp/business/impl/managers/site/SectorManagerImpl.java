package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.SectorManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class SectorManagerImpl extends AbstractManager implements SectorManager{

	@Override
	public List<Sector> getSectorsSiteList(Integer pSite) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifySector(Sector pSector) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSector(Integer pSectorId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

}
