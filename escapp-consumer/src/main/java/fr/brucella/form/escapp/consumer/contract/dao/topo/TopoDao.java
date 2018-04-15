package fr.brucella.form.escapp.consumer.contract.dao.topo;

import java.util.List;

import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface TopoDao {
	
	Topo getTopo(Integer pTopoId) throws TechnicalException, NotFoundException;
	
	List<Topo> getAllToposList() throws TechnicalException, NotFoundException;
	
	List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException, NotFoundException;
	
	List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException, NotFoundException;
	
	void updateTopo(Topo pTopo) throws TechnicalException, NotFoundException;
	
	void insertTopo(Topo pTopo) throws TechnicalException;
	
	void deleteTopo(Integer pTopoId) throws TechnicalException, NotFoundException;
}
