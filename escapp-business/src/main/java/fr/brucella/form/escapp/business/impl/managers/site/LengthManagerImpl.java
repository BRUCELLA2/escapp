package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.LengthManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class LengthManagerImpl extends AbstractManager implements LengthManager{

	@Override
	public List<Length> getLengthsRouteList(Integer pRouteId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyLength(Length pLength) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLength(Integer pLengthId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

}
