package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.RouteManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class RouteManagerImpl extends AbstractManager implements RouteManager{

	@Override
	public List<Route> getRoutesSectorList(Integer pSectorId) throws TechnicalException, FunctionalException, NotFoundException {

		if(pSectorId == null) {
			throw new FunctionalException("L'identifiant du Secteur est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		try {
			return getDaoFactory().getRouteDao().getRoutesList(pSectorId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}
	
	@Override
	public Route getRouteById(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException {
	  
	  if(pRouteId == null) {
	    throw new FunctionalException("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
	  }
	  
	  try {
	    return getDaoFactory().getRouteDao().getRoute(pRouteId);
	  }catch (TechnicalException pException) {
        throw new TechnicalException(pException.getMessage(),pException);
      }catch (NotFoundException pException) {
        throw new NotFoundException(pException.getMessage(),pException);
      }
	  
	}

	@Override
	public void modifyRoute(Route pRoute) throws TechnicalException, FunctionalException, NotFoundException {

		if(pRoute == null) {
			throw new FunctionalException("Aucune modification n'a été transmise (Voie vide) - Echec de la mise à jour");
		}
		
		Set<ConstraintViolation<Route>> vViolations = getConstraintValidator().validate(pRoute);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<Route> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Les modifications demandées ne sont pas valides",new ConstraintViolationException(vViolations));
		}
		
		try {
			getDaoFactory().getRouteDao().updateRoute(pRoute);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}
	
	@Override
	public void addRoute(Route pRoute) throws TechnicalException, FunctionalException {

		if(pRoute == null) {
			throw new FunctionalException("Aucune voie n'a été transmise (Voie vide) - Echec de l'ajout");
		}
		
		Set<ConstraintViolation<Route>> vViolations = getConstraintValidator().validate(pRoute);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<Route> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("La voie à ajouter n'est pas valide",new ConstraintViolationException(vViolations));
		}
		
		try {
			int newRouteId = getDaoFactory().getRouteDao().insertRoute(pRoute);
			pRoute.setId(newRouteId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}
	}	

	@Override
	public void deleteRoute(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException {

		if(pRouteId == null) {
			throw new FunctionalException("L'identifiant de la voie à supprimer est incorrect (Identifiant null) - Echec de la suppression");
		}
		
		try {
			getDaoFactory().getRouteDao().deleteRoute(pRouteId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
		
	}

}
