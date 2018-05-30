package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for Sector Manager
 *
 * @author BRUCELLA2
 */
public interface SectorManager {
  
  /**
   * Get the list of {@link Sector} with the specified site id.
   *
   * @param siteId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.site.Site} to
   *        which the {@link Sector} belongs.
   *
   * @return the list of {@link Sector} with the specified sector id.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the sector id is null.
   * @throws NotFoundException - This exception is throws if no {@link Sector} is found.
   */
  List<Sector> getSectorsSiteList(final Integer siteId) throws TechnicalException, FunctionalException, NotFoundException;
  
  /**
   * Get the {@link Sector} with the specified id.
   *
   * @param sectorId {@link Integer} id of the {@link Sector}
   *
   * @return the {@link Sector} with the specified id.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the route id is null.
   * @throws NotFoundException - This exception is throws if the {@link Sector} is not found.
   */
  Sector getSectorById(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException;
  
  /**
   * Save the modification of the {@link Sector}.
   *
   * @param sector the {@link Sector} modified to save
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the {@link Sector} sector is null. -
   *         This exception is throws if the data in the {@link Sector} are not valid.
   * @throws NotFoundException - This exception is throws if the {@link Sector} is not found.
   */
  void modifySector(final Sector sector) throws TechnicalException, FunctionalException, NotFoundException;
  
  /**
   * Validate and add the {@link Sector} to data store. The id will be added to the {@link Sector}
   * give in parameter.
   *
   * @param sector the {@link Sector} to add.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the {@link Sector} sector is null. -
   *         This exception is throws if the data in the {@link Sector} are not valid.
   */
  void addSector(final Sector sector) throws TechnicalException, FunctionalException;
  
  /**
   * Delete the {@link Sector} with the specified id.
   *
   * @param sectorId {@link Integer} id of the {@link Sector} to delete.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the route id is null.
   * @throws NotFoundException - This exception is throws if the {@link Sector} is not found.
   */
  void deleteSector(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException;
}
