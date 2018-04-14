package fr.brucella.form.escapp.consumer.contract.dao.topo;

import java.util.List;

import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface TopoDao {
	
	Topo getTopo(Integer pTopoId) throws TechnicalException;
	
	List<Topo> getAllToposList() throws TechnicalException;
	
	List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException;
	
	List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException;
	
	void updateTopo(Topo pTopo) throws TechnicalException;
	
	void insertTopo(Topo pTopo) throws TechnicalException;
	
	void deleteTopo(Integer pTopoId) throws TechnicalException;
}
