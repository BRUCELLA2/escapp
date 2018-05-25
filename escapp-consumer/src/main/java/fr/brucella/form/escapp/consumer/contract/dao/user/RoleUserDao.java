package fr.brucella.form.escapp.consumer.contract.dao.user;

import java.util.List;

import fr.brucella.form.escapp.model.beans.user.RoleUser;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for the RoleUser Data Access Object.
 *
 * @author BRUCELLA2
 */
public interface RoleUserDao {
    
    /**
     * Get the list of {@link RoleUser} with the specified user id from the datastore.
     * 
     * @param pUserId {@link Integer} id of the {@link User}.
     * 
     * @return the list of {@link RoleUser} with the specified user id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link RoleUser} is found.
     */
    List<RoleUser> getRoleUserList(Integer pUserId) throws TechnicalException, NotFoundException;
    
    /**
     * Update an existing {@link RoleUser} in datastore.
     * 
     * @param pRoleUser The {@link RoleUser} with the updated informations to save in datastore.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link RoleUser} is not found.
     */
    void updateRoleUser(RoleUser pRoleUser) throws TechnicalException, NotFoundException;
    
    /**
     * Insert a new {@link RoleUser} in datastore.
     * 
     * @param pRoleUser The {@link RoleUser} to insert in datastore.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    void insertRoleUser(RoleUser pRoleUser) throws TechnicalException;
    
    /**
     * Delete the {@link RoleUser} in the datastore.
     * 
     * @param pRoleUser The {@link RoleUser} to delete
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link RoleUser} is not found.
     */
    void deleteRoleUser(RoleUser pRoleUser) throws TechnicalException, NotFoundException;
}
