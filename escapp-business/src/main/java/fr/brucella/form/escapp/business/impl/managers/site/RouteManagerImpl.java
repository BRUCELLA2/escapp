package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.RouteManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class RouteManagerImpl extends AbstractManager implements RouteManager{

	@Override
	public List<Route> getRoutesSectorList(Integer pSectorId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyRoute(Route pRoute) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoute(Integer pRouteId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

}
