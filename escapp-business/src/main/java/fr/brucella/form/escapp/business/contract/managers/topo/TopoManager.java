package fr.brucella.form.escapp.business.contract.managers.topo;

import java.util.List;

import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * 
 * @author BRUCELLA2
 */
public interface TopoManager {

	public List<Topo> getAllToposList() throws TechnicalException, FunctionalException;
	
	public List<Topo> getToposOwnerList(Integer pOwnerId) throws TechnicalException, FunctionalException;
	
	public List<Topo> geToposBorrowerList(Integer pBorrowerId) throws TechnicalException, FunctionalException;
	
	public Topo getTopoById(Integer pTopoId) throws TechnicalException, FunctionalException;
	
	public void setBorrowable(Boolean pBorrowable) throws TechnicalException, FunctionalException;
	
	public void borrowTopo(Topo pTopo, Integer pNbDays) throws TechnicalException, FunctionalException;
	
	public void addTopo(Topo pTopo) throws TechnicalException, FunctionalException;
	
	public void modifyTopo(Topo pTopo, User pUser) throws TechnicalException, FunctionalException;
}
