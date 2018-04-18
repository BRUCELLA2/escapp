package fr.brucella.form.escapp.business.impl.managers.topo;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class TopoManagerImpl extends AbstractManager implements TopoManager{

	@Override
	public List<Topo> getAllToposList() throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topo> getToposOwnerList(Integer pOwnerId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topo> geToposBorrowerList(Integer pBorrowerId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topo getTopoById(Integer pTopoId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBorrowable(Boolean pBorrowable) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrowTopo(Topo pTopo, Integer pNbDays) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTopo(Topo pTopo) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyTopo(Topo pTopo, User pUser) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

}
