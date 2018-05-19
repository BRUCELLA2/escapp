package fr.brucella.form.escapp.business.impl.managers.user;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.user.UserManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.user.RoleUser;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class UserManagerImpl extends AbstractManager implements UserManager{

	BCryptPasswordEncoder vPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public User getUserByLogin(String pUserLogin) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(StringUtils.isEmpty(pUserLogin)) {
			throw new FunctionalException("Le login de l'utilisateur recherché est incorrect (Login vide) - Echec de la recherche");
		}
		
		try {
			return getDaoFactory().getUserDao().getUserByLogin(pUserLogin);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public User getUserById(Integer pUserId) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pUserId == null) {
			throw new FunctionalException("L'identifiant de l'utilisateur recherché est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		try {
			return getDaoFactory().getUserDao().getUserById(pUserId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public User getConnectUser(String pUserLogin, String pRawUserPassword) throws TechnicalException, FunctionalException, NotFoundException {

		if(StringUtils.isAllEmpty(pUserLogin, pRawUserPassword)) {
			throw new FunctionalException("Le login et le mot de passe de l'utilisateur sont incorrect (Login et mot de passe vides) - Echec de la connection");
		}
		
		if(StringUtils.isEmpty(pUserLogin)) {
			throw new FunctionalException("Le login de l'utilisateur est incorrect (Login vide) - Echec de la connection");
		}
		
		if(StringUtils.isEmpty(pRawUserPassword)) {
			throw new FunctionalException("Le mot de passe est incorrect (Mot de passe vide) - Echec de la connection");
		}
		
		try {
			User vUser = getDaoFactory().getUserDao().getUserByLogin(pUserLogin);
			if(checkPassword(pRawUserPassword, vUser.getPassword())) {
				return vUser;
			}else {
				throw new NotFoundException("Le login et le mot de passe ne correspondent pas - Echec de la connection");
			}
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public User addUser(User pUser) throws TechnicalException, FunctionalException {
		
		if(pUser == null) {
			throw new FunctionalException("Aucun utilisateur n'a été transmis (Utilisateur vide) - Echec de l'ajout de l'utilisateur");
		}
		
		if(StringUtils.isEmpty(pUser.getPassword())) {
			throw new FunctionalException("Le mot de passe associé à l'utilisateur est incorrect (Mot de passe vide) - Echec de l'ajout de l'utilisateur");
		}
		
		
		User vUser = pUser;
		String vEncodedPassword = encodePassword(pUser.getPassword());
		vUser.setPassword(vEncodedPassword);
		
		Set<ConstraintViolation<User>> vViolations = getConstraintValidator().validate(vUser);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<User> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("L'utilisateur à ajouter n'est pas valide",new ConstraintViolationException(vViolations));
		}
		
		try {
			if(!checkLoginDispo(vUser.getLogin())){
				throw new FunctionalException("Cette identifiant est déjà utilisé");
			}
		} catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}
		
		try {
			int newUserId = getDaoFactory().getUserDao().insertUser(vUser);
			vUser.setId(newUserId);
			return vUser;
		} catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}
		
	}

	@Override
	public void modifyPassword(User pUser, String pNewRawPassword) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pUser == null) {
			throw new FunctionalException("L'utilisateur à modifier n'a pas été transmis (Utilisateur vide) - Echec de la modification de mot de passe");
		}
		if(StringUtils.isEmpty(pNewRawPassword)) {
			throw new FunctionalException("Le nouveau mot de passe n'a pas été transmis (Mot de passe vide) - Echec de la modificaiton de mot de passe");
		}
		
		User vUser = pUser;
		String vPassword = encodePassword(pNewRawPassword);
		vUser.setPassword(vPassword);
		
		Set<ConstraintViolation<User>> vViolations = getConstraintValidator().validate(vUser);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<User> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("L'utilisateur à modifier n'est pas valide",new ConstraintViolationException(vViolations));
		}
		
		try {
			getDaoFactory().getUserDao().updateUser(vUser);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
		
	}
	
	@Override
	public List<RoleUser> getRoleUserList(Integer pUserId) throws TechnicalException, FunctionalException {
	  
	  if(pUserId == null) {
	    throw new FunctionalException("L'utilisateur n'a pas été transmis (Utilisateur vide) - Echec de la récupération des roles");
	  }
	  
	  try {
	    return getDaoFactory().getRoleUserDao().getRoleUserList(pUserId);
      }catch (TechnicalException pException) {
        throw new TechnicalException(pException.getMessage(),pException);
      }catch (NotFoundException pException) {
        return null;
      }
	}

  private String encodePassword(String pRawPassword){
    
    String vHashedPassword = vPasswordEncoder.encode(pRawPassword);
    return vHashedPassword;
    
  }

  private boolean checkPassword(String pRawPassword,String pEncodePassword){
    
		if(vPasswordEncoder.matches(pRawPassword, pEncodePassword)) {
			return true;
		}else {
			return false;
		}
  }
  
  public boolean checkLoginDispo(String pLogin) throws TechnicalException {
	  
	try {
		if(getDaoFactory().getUserDao().countUserByLogin(pLogin) > 0) {
			return false;
		}
	} catch (TechnicalException pException) {
		throw new TechnicalException(pException.getMessage(),pException);
	}
	
	return true;
  }

}
