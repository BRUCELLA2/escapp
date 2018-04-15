package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface RouteDao {
	
	Route getRoute(Integer pRouteId) throws TechnicalException, NotFoundException;
	
	List<Route> getRoutesList(Integer pSectorId) throws TechnicalException, NotFoundException;
	
	void updateRoute(Route pRoute) throws TechnicalException, NotFoundException;
	
	void insertRoute(Route pRoute) throws TechnicalException;
	
	void deleteRoute(Integer pRouteId) throws TechnicalException, NotFoundException;
}
