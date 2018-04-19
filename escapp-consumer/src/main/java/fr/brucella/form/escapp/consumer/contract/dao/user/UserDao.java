package fr.brucella.form.escapp.consumer.contract.dao.user;

import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for the User Data Access Object.
 * 
 * @author BRUCELLA2
 */
public interface UserDao {
	
	/**
	 * Get the {@link User} with the specified id from the datastore.
	 * 
	 * @param pUserId {@link Integer} id of the {@link User}.
	 * 
	 * @return the {@link User} with the specified id.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link User} is not found.
	 */
	User getUserById(Integer pUserId) throws TechnicalException, NotFoundException;
	
	
	/**
	 * Get the {@link User} with the specified login from the datastore.
	 * 
	 * @param pUserLogin the login of the {@link User}
	 * 
	 * @return the {@link User} with the specified login.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link User} is not found.
	 */
	User getUserByLogin(String pUserLogin) throws TechnicalException, NotFoundException;
	
	
	/**
	 * Update an existing {@link User} in datastore.
	 * 
	 * @param pUser The {@link User} with the updated informations to save in datastore.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link User} is not found.
	 */
	void updateUser(User pUser) throws TechnicalException, NotFoundException;
	
	
	/**
	 * Insert a new {@link User} in datastore.
	 * 
	 * @param pUser The {@link User} to insert in datastore.
	 * 
	 * @return the id of the new {@link User}.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 */
	int insertUser(User pUser) throws TechnicalException;
	
	
	/**
	 * Delete the {@link User} with the specified id in the datastore.
	 * 
	 * @param pUserId {@link Integer} id of the {@link User}.
	 * 
	 * @throws TechnicalException - wraps technical exception caused during data access.
	 * @throws NotFoundException - This exception is throws if there is no technical exception and the {@link User} is not found.
	 */
	void deleteUser(Integer pUserId) throws TechnicalException, NotFoundException;
}
