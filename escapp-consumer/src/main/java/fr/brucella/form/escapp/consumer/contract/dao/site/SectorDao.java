package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface SectorDao {
	
	Sector getSector(Integer pSectorId) throws TechnicalException;
	
	List<Sector> getSectorsList(Integer pSiteId) throws TechnicalException;
	
	void updateSector(Sector pSector) throws TechnicalException;
	
	void insertSector(Sector pSector) throws TechnicalException;
	
	void deleteSector(Integer pSectorId) throws TechnicalException;
}
