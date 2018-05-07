package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.SiteManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.SiteSearch;

@Component
public class SiteManagerImpl extends AbstractManager implements SiteManager{
	
	@Override
	public List<Site> getAllSitesList() throws TechnicalException, NotFoundException {
		try {
			return getDaoFactory().getSiteDao().getAllSitesList();
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(), pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public Site getSiteById(Integer pSiteId) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pSiteId == null) {
			throw new FunctionalException("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
		}

		try {
			
			return getDaoFactory().getSiteDao().getSite(pSiteId);
			
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}
	
	@Override
	public List<Site> getSearchSitesList(SiteSearch pSiteSearch) throws TechnicalException, NotFoundException, FunctionalException {
		
		if(pSiteSearch == null) {
			return getAllSitesList();
		}
		
		Set<ConstraintViolation<SiteSearch>> vViolations = getConstraintValidator().validate(pSiteSearch);
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<SiteSearch> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Les critères de recherche de sont pas valides",new ConstraintViolationException(vViolations));
		}
		try {
			return getDaoFactory().getSiteDao().getSearchSitesLIst(pSiteSearch);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
		
	}

	@Override
	public void modifySite(Site pSite) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pSite == null) {
			throw new FunctionalException("Aucune modification n'a été transmise (Site vide) - Echec de la mise à jour");
		}
		
		Set<ConstraintViolation<Site>> vViolations = getConstraintValidator().validate(pSite);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<Site> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Les modifications demandées ne sont pas valides",new ConstraintViolationException(vViolations));
		}
		
		try {
			getDaoFactory().getSiteDao().updateSite(pSite);
		} catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(), pException);
		} catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(), pException);
		}
	}

	@Override
	public void addSite(Site pSite) throws TechnicalException, FunctionalException {
		
		if(pSite == null) {
			throw new FunctionalException("Aucune site n'a été transmis (Site vide) - Echec de l'ajout");
		}
		
		Set<ConstraintViolation<Site>> vViolations = getConstraintValidator().validate(pSite);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<Site> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Le site à ajouter n'est pas valide",new ConstraintViolationException(vViolations));
		}
		
		try {
			int newSiteId = getDaoFactory().getSiteDao().insertSite(pSite);
			pSite.setId(newSiteId);
		} catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(), pException);
		}
	}

	@Override
	public void deleteSite(Integer pSiteId) throws TechnicalException, FunctionalException, NotFoundException {

		if(pSiteId == null) {
			throw new FunctionalException("L'identifiant du site à supprimer est incorrect (Identifiant vide) - Echec de la suppression");
		}
		
		try {
			getDaoFactory().getSiteDao().deleteSite(pSiteId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
		
	}

}
