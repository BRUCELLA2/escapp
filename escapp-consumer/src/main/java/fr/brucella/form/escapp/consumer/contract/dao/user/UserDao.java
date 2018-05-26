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
     * @param userId {@link Integer} id of the {@link User}.
     * 
     * @return the {@link User} with the specified id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link User} is not found.
     */
    User getUserById(final Integer userId) throws TechnicalException, NotFoundException;
    
    
    /**
     * Get the {@link User} with the specified login from the datastore.
     * 
     * @param userLogin the login of the {@link User}
     * 
     * @return the {@link User} with the specified login.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link User} is not found.
     */
    User getUserByLogin(final String userLogin) throws TechnicalException, NotFoundException;
    
    
    /**
     * Count the number of {@link User} with the specified login from the datastore.
     * 
     * @param userLogin the login of the {@link User}
     * 
     * @return the number of {@link User} with the specified login
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    Integer countUserByLogin(final String userLogin) throws TechnicalException;
    
    
    /**
     * Update an existing {@link User} in datastore.
     * 
     * @param user The {@link User} with the updated informations to save in datastore.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link User} is not found.
     */
    void updateUser(final User user) throws TechnicalException, NotFoundException;
    
    
    /**
     * Insert a new {@link User} in datastore.
     * 
     * @param user The {@link User} to insert in datastore.
     * 
     * @return the id of the new {@link User}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    int insertUser(final User user) throws TechnicalException;
    
    
    /**
     * Delete the {@link User} with the specified id in the datastore.
     * 
     * @param userId {@link Integer} id of the {@link User}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link User} is not found.
     */
    void deleteUser(final Integer userId) throws TechnicalException, NotFoundException;
}
