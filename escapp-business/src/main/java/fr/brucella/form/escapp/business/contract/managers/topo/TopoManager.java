package fr.brucella.form.escapp.business.contract.managers.topo;

import java.util.List;

import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.TopoSearch;

/**
 * 
 * @author BRUCELLA2
 */
public interface TopoManager {

	public List<Topo> getAllToposList() throws TechnicalException, FunctionalException, NotFoundException;
	
	public List<Topo> getSearchToposList(TopoSearch pTopoSearch) throws TechnicalException, FunctionalException, NotFoundException;
	
	public List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException, FunctionalException, NotFoundException;
	
	public List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException, FunctionalException, NotFoundException;
	
	public Topo getTopoById(Integer pTopoId) throws TechnicalException, FunctionalException, NotFoundException;
	
	public void setBorrowable(Boolean pBorrowable, Integer pUserId,  Topo pTopo) throws TechnicalException, FunctionalException, NotFoundException;
	
	public Topo borrowTopo(Topo pTopo, Integer pNbDays, User pBorrower) throws TechnicalException, FunctionalException, NotFoundException;
	
	public void addTopo(Topo pTopo) throws TechnicalException, FunctionalException;
	
	public void modifyTopo(Topo pTopo, User pUser) throws TechnicalException, FunctionalException, NotFoundException;
	
	public void deleteTopo(Integer pTopoId, User pUser) throws TechnicalException, FunctionalException, NotFoundException;
}
