package fr.brucella.form.escapp.business.contract.managers.user;

import java.util.List;

import fr.brucella.form.escapp.model.beans.user.RoleUser;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 *
 * @author BRUCELLA2
 */
public interface UserManager {
    
    /**
     * Get the {@link User} with the specified login.
     * 
     * @param pUserLogin the login of the {@link User}
     * 
     * @return the {@link User} with the specified login.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the login is null or empty.
     * @throws NotFoundException - This exception is throws if the {@link User} is not found.
     */
    User getUserByLogin(String pUserLogin) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Get the {@link User} with the specified user id.
     * 
     * @param pUserId {@link Integer} id of the {@link User}
     * 
     * @return the {@link User} with the specified user id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the user id is null.
     * @throws NotFoundException - This exception is throws if the {@link User} is not found.
     */
    User getUserById(Integer pUserId) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Get a {@link User} authentified by login and password. If login and password don't match, this
     * method throws a not found exception. If login and password match, this method return the
     * {@link User}
     * 
     * @param pUserLogin the login of the {@link User}
     * @param pRawUserPassword the raw password, without encryption.
     * 
     * @return the {@link User}
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the login is null or empty. - This
     *         exception is throws if the password is null or empty. - This exception is throws if the
     *         password and the login are null or empty.
     * @throws NotFoundException - This exception is throws if the {@link User} is not found. - This
     *         exception is throws if the login and password don't match.
     */
    User getConnectUser(String pUserLogin, String pRawUserPassword) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Validate and add the {@link User} to data store. The password property of the {@link User} need
     * to be raw password. This raw password will be encrypted. A new {@link User} with an user id and
     * password encrypted will be returned by this method.
     * 
     * @param pUser the {@link User} to add.
     * 
     * @return the {@link User} with user id and password encrypted
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link User} is null. - This
     *         exception is throws if the password of the {@link User} is not null or empty. - This
     *         exception is throws if the data in the {@link User} are not valid.
     */
    User addUser(User pUser) throws TechnicalException, FunctionalException;
    
    /**
     * Modify the password of the {@link User} give in parameter. The new password need to be raw
     * password. This new password will be encrypted and add to the {@link User}
     * 
     * @param pUser the {@link User} to modify
     * @param pNewRawPassword the new raw password, without encryption.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link User} is null. - This
     *         exception is throws if the new password is null. - This exception is throws if the data
     *         in the {@link User} are not valid.
     * @throws NotFoundException - This exception is throws if the {@link User} is not found in data
     *         store.
     */
    void modifyPassword(User pUser, String pNewRawPassword) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Check if the login is disponible and not already used.
     * 
     * @param pLogin the login to check
     * 
     * @return true if the login is not used and false otherwise
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    boolean checkLoginDispo(String pLogin) throws TechnicalException;
    
    /**
     * Get the list of the role {@link RoleUser} of the {@link User} with the specified id.
     * 
     * @param pUserId {@link Integer} id of the {@link User}
     * 
     * @return the list of {@link RoleUser} of the {@link User} with the specified id. empty list if the
     *         {@link User} has no role.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the user id is null.
     */
    List<RoleUser> getRoleUserList(Integer pUserId) throws TechnicalException, FunctionalException;
}
