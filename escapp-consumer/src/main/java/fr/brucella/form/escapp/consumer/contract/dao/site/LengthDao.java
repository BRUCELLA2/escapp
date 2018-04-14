package fr.brucella.form.escapp.consumer.contract.dao.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface LengthDao {
	
    Length getLength(Integer pLengthId) throws TechnicalException;

    List<Length> getLengthsList(Integer pRouteId) throws TechnicalException;
    
    void updateLength(Length pLength) throws TechnicalException;
    
    void insertLength(Length pLength) throws TechnicalException;
    
    void deleteLength(Integer pLengthId) throws TechnicalException;

}
