package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface SectorDao {
	
	Sector getSector(Integer pSectorId) throws TechnicalException, NotFoundException;
	
	List<Sector> getSectorsList(Integer pSiteId) throws TechnicalException, NotFoundException;
	
	void updateSector(Sector pSector) throws TechnicalException, NotFoundException;
	
	void insertSector(Sector pSector) throws TechnicalException;
	
	void deleteSector(Integer pSectorId) throws TechnicalException, NotFoundException;
}
