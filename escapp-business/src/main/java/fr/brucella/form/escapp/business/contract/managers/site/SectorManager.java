package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * 
 * @author BRUCELLA2
 */
public interface SectorManager {

	public List<Sector> getSectorsSiteList(Integer pSite) throws TechnicalException, FunctionalException;
	
	public void modifySector(Sector pSector) throws TechnicalException, FunctionalException;
	
	public void deleteSector(Integer pSectorId) throws TechnicalException, FunctionalException;
}
