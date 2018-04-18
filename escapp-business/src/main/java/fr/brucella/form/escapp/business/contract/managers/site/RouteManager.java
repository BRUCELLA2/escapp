package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * 
 * @author BRUCELLA2
 */
public interface RouteManager {

	public List<Route> getRoutesSectorList(Integer pSectorId) throws TechnicalException, FunctionalException;
	
	public void modifyRoute(Route pRoute) throws TechnicalException, FunctionalException;
	
	public void deleteRoute(Integer pRouteId) throws TechnicalException, FunctionalException;
}
