package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.SiteManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.SiteSearch;

/**
 * The Site Manager.
 *
 * @author BRUCELLA2
 */
@Component
public class SiteManagerImpl extends AbstractManager implements SiteManager {
  
  // ----- Logger
  
  /**
   * Site Manager logger.
   */
  private static final Log LOG = LogFactory.getLog(SiteManagerImpl.class);
  
  
  // ----- Methods
  
  /**
   * @see SiteManager#getAllSitesList()
   */
  @Override
  public List<Site> getAllSitesList() throws TechnicalException, NotFoundException {
    
    try {
      return this.getDaoFactory().getSiteDao().getAllSitesList();
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see SiteManager#getSiteById(Integer)
   */
  @Override
  public Site getSiteById(final Integer siteId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (siteId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("site id= " + siteId);
      }
      throw new FunctionalException("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      
      return this.getDaoFactory().getSiteDao().getSite(siteId);
      
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
   * @see SiteManager#getSearchSitesList(SiteSearch)
   */
  @Override
  public List<Site> getSearchSitesList(final SiteSearch siteSearch) throws TechnicalException, NotFoundException, FunctionalException {
    
    
    List<Site> sitesList;
    
    if (siteSearch == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("site search = null");
      }
      sitesList = this.getAllSitesList();
    }
    else {
      final Set<ConstraintViolation<SiteSearch>> violations = this.getConstraintValidator().validate(siteSearch);
      if (!violations.isEmpty()) {
        if (LOG.isDebugEnabled()) {
          for (final ConstraintViolation<SiteSearch> violation : violations) {
            LOG.debug(violation.getMessage());
          }
          LOG.debug("site search = " + siteSearch.toString());
        }
        throw new FunctionalException("Les critères de recherche ne sont pas valides", new ConstraintViolationException(violations));
      }
      try {
        sitesList = this.getDaoFactory().getSiteDao().getSearchSitesList(siteSearch);
      } catch (TechnicalException exception) {
        LOG.error(exception.getMessage());
        throw new TechnicalException(exception.getMessage(), exception);
      } catch (NotFoundException exception) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("site search = " + siteSearch.toString());
        }
        LOG.error(exception.getMessage());
        throw new NotFoundException(exception.getMessage(), exception);
      }
    }
    
    return sitesList;
    
  }
  
  /**
   * @see SiteManager#modifySite(Site)
   */
  @Override
  public void modifySite(final Site site) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (site == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("site = null");
      }
      throw new FunctionalException("Aucune modification n'a été transmise (Site vide) - Echec de la mise à jour");
    }
    
    final Set<ConstraintViolation<Site>> violations = this.getConstraintValidator().validate(site);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Site> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("site = " + site.toString());
      }
      throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(violations));
    }
    
    try {
      this.getDaoFactory().getSiteDao().updateSite(site);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("site = " + site.toString());
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see SiteManager#addSite(Site)
   */
  @Override
  public void addSite(final Site site) throws TechnicalException, FunctionalException {
    
    if (site == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("site = null");
      }
      throw new FunctionalException("Aucune site n'a été transmis (Site vide) - Echec de l'ajout");
    }
    
    final Set<ConstraintViolation<Site>> violations = this.getConstraintValidator().validate(site);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<Site> violation : violations) {
          LOG.debug(violation.getMessage());
        }
        LOG.debug("site = " + site.toString());
      }
      throw new FunctionalException("Le site à ajouter n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      final int newSiteId = this.getDaoFactory().getSiteDao().insertSite(site);
      site.setId(newSiteId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see SiteManager#deleteSite(Integer)
   */
  @Override
  public void deleteSite(final Integer siteId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (siteId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("site id= " + siteId);
      }
      throw new FunctionalException("L'identifiant du site à supprimer est incorrect (Identifiant vide) - Echec de la suppression");
    }
    
    try {
      this.getDaoFactory().getSiteDao().deleteSite(siteId);
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
  
}
