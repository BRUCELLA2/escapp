package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * 
 * @author BRUCELLA2
 */
public interface LengthManager {

	public List<Length> getLengthsRouteList(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException;
	
	public void modifyLength(Length pLength) throws TechnicalException, FunctionalException, NotFoundException;
	
	public void addLength(Length pLength) throws TechnicalException, FunctionalException;
	
	public void deleteLength(Integer pLengthId) throws TechnicalException, FunctionalException, NotFoundException;
}
