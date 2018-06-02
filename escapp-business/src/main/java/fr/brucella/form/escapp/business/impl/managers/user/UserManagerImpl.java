package fr.brucella.form.escapp.business.impl.managers.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.user.UserManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.user.RoleUser;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * The User Manager.
 *
 * @author BRUCELL2
 */
@Component
public class UserManagerImpl extends AbstractManager implements UserManager {
  
  /**
   * Topo Manager logger.
   */
  private static final Log      LOG              = LogFactory.getLog(UserManagerImpl.class);
  
  /**
   * Password Encoder.
   */
  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
  
  // Methods
  
  /**
   * @see UserManager#getUserByLogin(String)
   */
  @Override
  public User getUserByLogin(final String userLogin) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (StringUtils.isEmpty(userLogin)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("login = null");
      }
      throw new FunctionalException("Le login de l'utilisateur recherché est incorrect (Login vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getUserDao().getUserByLogin(userLogin);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("login = " + userLogin);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see UserManager#getUserById(Integer)
   */
  @Override
  public User getUserById(final Integer userId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (userId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("id = null");
      }
      throw new FunctionalException("L'identifiant de l'utilisateur recherché est incorrect (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getUserDao().getUserById(userId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("id = " + userId);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see UserManager#getConnectUser(String, String)
   */
  @Override
  public User getConnectUser(final String userLogin, final String rawUserPassword) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (StringUtils.isAllEmpty(userLogin, rawUserPassword)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("login and raw password = null");
      }
      throw new FunctionalException("Le login et le mot de passe de l'utilisateur sont incorrect (Login et mot de passe vides) - Echec de la connection");
    }
    
    if (StringUtils.isEmpty(userLogin)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("login = null");
      }
      throw new FunctionalException("Le login de l'utilisateur est incorrect (Login vide) - Echec de la connection");
    }
    
    if (StringUtils.isEmpty(rawUserPassword)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("raw password = null");
      }
      throw new FunctionalException("Le mot de passe est incorrect (Mot de passe vide) - Echec de la connection");
    }
    
    try {
      final User user = this.getDaoFactory().getUserDao().getUserByLogin(userLogin);
      if (this.checkPassword(rawUserPassword, user.getPassword())) {
        return user;
      }
      else {
        if (LOG.isDebugEnabled()) {
          LOG.debug("login = " + userLogin);
          LOG.debug("password = wrong");
        }
        throw new NotFoundException("Le login et le mot de passe ne correspondent pas - Echec de la connection");
      }
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("login = " + userLogin);
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
  }
  
  /**
   * @see UserManager#addUser(User)
   */
  @Override
  public User addUser(final User userToAdd) throws TechnicalException, FunctionalException {
    
    if (userToAdd == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("user = null");
      }
      throw new FunctionalException("Aucun utilisateur n'a été transmis (Utilisateur vide) - Echec de l'ajout de l'utilisateur");
    }
    
    if (StringUtils.isEmpty(userToAdd.getPassword())) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("password = null");
      }
      throw new FunctionalException("Le mot de passe associé à l'utilisateur est incorrect (Mot de passe vide) - Echec de l'ajout de l'utilisateur");
    }
    
    
    final User user = userToAdd;
    final String encodedPassword = this.encodePassword(userToAdd.getPassword());
    user.setPassword(encodedPassword);
    
    final Set<ConstraintViolation<User>> violations = this.getConstraintValidator().validate(user);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<User> violation : violations) {
          LOG.debug(violation.getMessage());
        }
      }
      throw new FunctionalException("L'utilisateur à ajouter n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      if (!this.checkLoginDispo(user.getLogin())) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("login = " + user.getLogin());
        }
        throw new FunctionalException("Cette identifiant est déjà utilisé");
      }
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
    
    try {
      final int newUserId = this.getDaoFactory().getUserDao().insertUser(user);
      user.setId(newUserId);
      return user;
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
    
  }
  
  /**
   * @see UserManager#modifyPassword(User, String)
   */
  @Override
  public void modifyPassword(final User userToModify, final String newRawPassword) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (userToModify == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("user = null");
      }
      throw new FunctionalException("L'utilisateur à modifier n'a pas été transmis (Utilisateur vide) - Echec de la modification de mot de passe");
    }
    if (StringUtils.isEmpty(newRawPassword)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("password = null");
      }
      throw new FunctionalException("Le nouveau mot de passe n'a pas été transmis (Mot de passe vide) - Echec de la modificaiton de mot de passe");
    }
    
    final User user = userToModify;
    final String password = this.encodePassword(newRawPassword);
    user.setPassword(password);
    
    final Set<ConstraintViolation<User>> violations = this.getConstraintValidator().validate(user);
    
    if (!violations.isEmpty()) {
      if (LOG.isDebugEnabled()) {
        for (final ConstraintViolation<User> violation : violations) {
          LOG.debug(violation.getMessage());
        }
      }
      throw new FunctionalException("L'utilisateur à modifier n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      this.getDaoFactory().getUserDao().updateUser(user);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("login = " + user.getLogin());
      }
      LOG.error(exception.getMessage());
      throw new NotFoundException(exception.getMessage(), exception);
    }
    
  }
  
  /**
   * @see UserManager#getRoleUserList(Integer)
   */
  @Override
  public List<RoleUser> getRoleUserList(final Integer userId) throws TechnicalException, FunctionalException {
    
    if (userId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("user id = null");
      }
      throw new FunctionalException("L'utilisateur n'a pas été transmis (Utilisateur vide) - Echec de la récupération des roles");
    }
    
    List<RoleUser> rolesUser;
    
    try {
      rolesUser = this.getDaoFactory().getRoleUserDao().getRoleUserList(userId);
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    } catch (NotFoundException exception) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("user id = " + userId);
        LOG.debug(exception.getMessage());
      }
      rolesUser = new ArrayList<>();
    }
    
    return rolesUser;
  }
  
  /**
   * @see UserManager#checkLoginDispo(String)
   */
  @Override
  public boolean checkLoginDispo(final String login) throws TechnicalException {
    
    boolean dispo = true;
    
    try {
      if (this.getDaoFactory().getUserDao().countUserByLogin(login) > 0) {
        dispo = false;
      }
    } catch (TechnicalException exception) {
      LOG.error(exception.getMessage());
      throw new TechnicalException(exception.getMessage(), exception);
    }
    
    return dispo;
  }
  
  /**
   * This method encrypte a raw password with the password encoder {@link #passwordEncoder}.
   *
   * @param rawPassword the raw password to encrypte
   *
   * @return the password encrypted
   */
  private String encodePassword(final String rawPassword) {
    
    return this.passwordEncoder.encode(rawPassword);
    
  }
  
  /**
   * This method check if a raw password is the same than the encrypted password. This method use the
   * password encoder {@link #passwordEncoder}
   *
   * @param rawPassword The raw password
   * @param encodePassword the encrypted password
   *
   * @return true if the raw password and the encrypted password match, false otherwise.
   */
  private boolean checkPassword(final String rawPassword, final String encodePassword) {
    
    return this.passwordEncoder.matches(rawPassword, encodePassword);
    
  }
  
}
