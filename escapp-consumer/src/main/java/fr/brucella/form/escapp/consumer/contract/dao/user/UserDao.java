package fr.brucella.form.escapp.consumer.contract.dao.user;

import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public interface UserDao {
	
	User getUserById(Integer pUserId) throws TechnicalException, NotFoundException;
	
	User getUserByLogin(String pUserLogin) throws TechnicalException, NotFoundException;
	
	void updateUser(User pUser) throws TechnicalException, NotFoundException;
	
	void insertUser(User pUser) throws TechnicalException;
	
	void deleteUser(Integer pUserId) throws TechnicalException, NotFoundException;
}
