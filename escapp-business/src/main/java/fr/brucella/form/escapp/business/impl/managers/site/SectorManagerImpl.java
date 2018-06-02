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
      if (LOG.isDebugEnabled()) {
        LOG.debug("site id= " + siteId);
      }
      throw new FunctionalException("L'identifiant du site est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getSectorDao().getSectorsList(siteId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("site id= " + siteId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see SectorManager#getSectorById(Integer)
   */
  @Override
  public Sector getSectorById(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (sectorId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector id= " + sectorId);
      }
      throw new FunctionalException("L'identifiant du secteur recherché est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getSectorDao().getSector(sectorId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector id= " + sectorId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see SectorManager#modifySector(Sector)
   */
  @Override
  public void modifySector(final Sector sector) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (sector == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector = null");
      }
      throw new FunctionalException("Aucune modification n'a été transmise (Secteur vide) - Echec de la mise à jour");
    }
    
    final Set<ConstraintViolation<Sector>> violations = this.getConstraintValidator().validate(sector);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Sector> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("length = " + sector.toString());
      }
      throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(violations));
    }
    
    try {
      this.getDaoFactory().getSectorDao().updateSector(sector);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector = " + sector.toString());
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see SectorManager#addSector(Sector)
   */
  @Override
  public void addSector(final Sector sector) throws TechnicalException, FunctionalException {
    
    if (sector == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector = null");
      }
      throw new FunctionalException("Aucun secteur n'a été transmis (Secteur vide) - Echec de l'ajout");
    }
    
    final Set<ConstraintViolation<Sector>> violations = this.getConstraintValidator().validate(sector);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Sector> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("length = " + sector.toString());
      }
      throw new FunctionalException("Le secteur à ajouter n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      final int newSectorId = this.getDaoFactory().getSectorDao().insertSector(sector);
      sector.setId(newSectorId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see SectorManager#deleteSector(Integer)
   */
  @Override
  public void deleteSector(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (sectorId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector id= " + sectorId);
      }
      throw new FunctionalException("L'identifiant du sector à supprimer est incorrect (Identifiant null) - Echec de la suppression");
    }
    
    try {
      this.getDaoFactory().getSectorDao().deleteSector(sectorId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("sector id = " + sectorId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
}
