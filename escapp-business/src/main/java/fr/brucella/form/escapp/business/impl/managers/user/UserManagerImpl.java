package fr.brucella.form.escapp.business.impl.managers.user;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.user.UserManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class UserManagerImpl extends AbstractManager implements UserManager{

	@Override
	public User getUserByLogin(String pLogin) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Integer pId) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getConnectUser(String pLogin, String pPassword) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User pUser) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyPassword(User pUser) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

}
