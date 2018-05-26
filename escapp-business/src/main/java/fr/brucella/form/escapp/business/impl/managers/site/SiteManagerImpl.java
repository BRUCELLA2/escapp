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
 * The Site Manager
 *
 * @author BRUCELLA2
 */
@Component
public class SiteManagerImpl extends AbstractManager implements SiteManager {
    
    // ----- Logger
    
    /**
     * Site Manager logger
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
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see SiteManager#getSiteById(Integer)
     */
    @Override
    public Site getSiteById(final Integer siteId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (siteId == null) {
            throw new FunctionalException("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
        }
        
        try {
            
            return this.getDaoFactory().getSiteDao().getSite(siteId);
            
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see SiteManager#getSearchSitesList(SiteSearch)
     */
    @Override
    public List<Site> getSearchSitesList(final SiteSearch pSiteSearch) throws TechnicalException, NotFoundException, FunctionalException {
        
        
        List<Site> sitesList;
        
        if (pSiteSearch == null) {
            sitesList = this.getAllSitesList();
        }
        else {
            final Set<ConstraintViolation<SiteSearch>> violations = this.getConstraintValidator().validate(pSiteSearch);
            if (!violations.isEmpty()) {
                for (final ConstraintViolation<SiteSearch> violation : violations) {
                    LOG.debug(violation.getMessage());
                }
                throw new FunctionalException("Les critères de recherche ne sont pas valides", new ConstraintViolationException(violations));
            }
            try {
                sitesList = this.getDaoFactory().getSiteDao().getSearchSitesList(pSiteSearch);
            } catch (TechnicalException pException) {
                throw new TechnicalException(pException.getMessage(), pException);
            } catch (NotFoundException pException) {
                throw new NotFoundException(pException.getMessage(), pException);
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
            throw new FunctionalException("Aucune modification n'a été transmise (Site vide) - Echec de la mise à jour");
        }
        
        final Set<ConstraintViolation<Site>> violations = this.getConstraintValidator().validate(site);
        
        if (!violations.isEmpty()) {
            for (final ConstraintViolation<Site> violation : violations) {
                LOG.debug(violation.getMessage());
            }
            throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(violations));
        }
        
        try {
            this.getDaoFactory().getSiteDao().updateSite(site);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see SiteManager#addSite(Site)
     */
    @Override
    public void addSite(final Site site) throws TechnicalException, FunctionalException {
        
        if (site == null) {
            throw new FunctionalException("Aucune site n'a été transmis (Site vide) - Echec de l'ajout");
        }
        
        final Set<ConstraintViolation<Site>> violations = this.getConstraintValidator().validate(site);
        
        if (!violations.isEmpty()) {
            for (final ConstraintViolation<Site> violation : violations) {
                LOG.debug(violation.getMessage());
            }
            throw new FunctionalException("Le site à ajouter n'est pas valide", new ConstraintViolationException(violations));
        }
        
        try {
            final int newSiteId = this.getDaoFactory().getSiteDao().insertSite(site);
            site.setId(newSiteId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
    }
    
    /**
     * @see SiteManager#deleteSite(Integer)
     */
    @Override
    public void deleteSite(final Integer siteId) throws TechnicalException, FunctionalException, NotFoundException {
        
        if (siteId == null) {
            throw new FunctionalException("L'identifiant du site à supprimer est incorrect (Identifiant vide) - Echec de la suppression");
        }
        
        try {
            this.getDaoFactory().getSiteDao().deleteSite(siteId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
        
    }
    
}
