package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.SectorManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * The Sector Manager.
 *
 * @author BRUCELLA2
 */
@Component
public class SectorManagerImpl extends AbstractManager implements SectorManager {
  
  // ----- Logger
  /**
   * Sector Manager logger.
   */
  private static final Log LOG = LogFactory.getLog(SectorManagerImpl.class);
  
  /**
   * @see SectorManager#getSectorsSiteList(Integer)
   */
  @Override
  public List<Sector> getSectorsSiteList(final Integer siteId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (siteId == null) {
      throw new FunctionalException("L'identifiant du site est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getSectorDao().getSectorsList(siteId);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    } catch (NotFoundException pException) {
      throw new NotFoundException(pException.getMessage(), pException);
    }
  }
  
  /**
   * @see SectorManager#getSectorById(Integer)
   */
  @Override
  public Sector getSectorById(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (sectorId == null) {
      throw new FunctionalException("L'identifiant du secteur recherché est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getSectorDao().getSector(sectorId);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    } catch (NotFoundException pException) {
      throw new NotFoundException(pException.getMessage(), pException);
    }
  }
  
  /**
   * @see SectorManager#modifySector(Sector)
   */
  @Override
  public void modifySector(final Sector sector) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (sector == null) {
      throw new FunctionalException("Aucune modification n'a été transmise (Secteur vide) - Echec de la mise à jour");
    }
    
    final Set<ConstraintViolation<Sector>> violations = this.getConstraintValidator().validate(sector);
    
    if (!violations.isEmpty()) {
      for (final ConstraintViolation<Sector> violation : violations) {
        LOG.debug(violation.getMessage());
      }
      throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(violations));
    }
    
    try {
      this.getDaoFactory().getSectorDao().updateSector(sector);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    } catch (NotFoundException pException) {
      throw new NotFoundException(pException.getMessage(), pException);
    }
  }
  
  /**
   * @see SectorManager#addSector(Sector)
   */
  @Override
  public void addSector(final Sector sector) throws TechnicalException, FunctionalException {
    
    if (sector == null) {
      throw new FunctionalException("Aucun secteur n'a été transmis (Secteur vide) - Echec de l'ajout");
    }
    
    final Set<ConstraintViolation<Sector>> violations = this.getConstraintValidator().validate(sector);
    
    if (!violations.isEmpty()) {
      for (final ConstraintViolation<Sector> violation : violations) {
        LOG.debug(violation.getMessage());
      }
      throw new FunctionalException("Le secteur à ajouter n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      final int newSectorId = this.getDaoFactory().getSectorDao().insertSector(sector);
      sector.setId(newSectorId);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    }
  }
  
  /**
   * @see SectorManager#deleteSector(Integer)
   */
  @Override
  public void deleteSector(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (sectorId == null) {
      throw new FunctionalException("L'identifiant du sector à supprimer est incorrect (Identifiant null) - Echec de la suppression");
    }
    
    try {
      this.getDaoFactory().getSectorDao().deleteSector(sectorId);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    } catch (NotFoundException pException) {
      throw new NotFoundException(pException.getMessage(), pException);
    }
  }
  
}
