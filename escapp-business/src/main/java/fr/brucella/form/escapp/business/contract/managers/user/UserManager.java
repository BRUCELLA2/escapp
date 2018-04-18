package fr.brucella.form.escapp.business.contract.managers.user;

import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * 
 * @author BRUCELLA2
 */
public interface UserManager {

	public User getUserByLogin(String pLogin) throws TechnicalException, FunctionalException;
	
	public User getUserById(Integer pId) throws TechnicalException, FunctionalException;
	
	public User getConnectUser(String pLogin, String pPassword) throws TechnicalException, FunctionalException;
	
	public void addUser(User pUser) throws TechnicalException, FunctionalException;
	
	public void modifyPassword(User pUser) throws TechnicalException, FunctionalException;
}
