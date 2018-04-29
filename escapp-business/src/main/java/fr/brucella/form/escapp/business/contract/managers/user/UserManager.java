package fr.brucella.form.escapp.business.contract.managers.user;

import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * 
 * @author BRUCELLA2
 */
public interface UserManager {

	public User getUserByLogin(String pUserLogin) throws TechnicalException, FunctionalException, NotFoundException;
	
	public User getUserById(Integer pUserId) throws TechnicalException, FunctionalException, NotFoundException;
	
	public User getConnectUser(String pUserLogin, String pRawUserPassword) throws TechnicalException, FunctionalException, NotFoundException;
	
	public User addUser(User pUser) throws TechnicalException, FunctionalException;
	
	public void modifyPassword(User pUser, String pNewRawPassword) throws TechnicalException, FunctionalException, NotFoundException;
	
	public boolean checkLoginDispo(String pLogin) throws TechnicalException;
}
