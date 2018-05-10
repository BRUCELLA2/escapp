package fr.brucella.form.escapp.business.impl.managers.topo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.TopoSearch;

@Component
public class TopoManagerImpl extends AbstractManager implements TopoManager{

	@Override
	public List<Topo> getAllToposList() throws TechnicalException, FunctionalException, NotFoundException {
		
		try {
			return getDaoFactory().getTopoDao().getAllToposList();
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}
	
	@Override
	public List<Topo> getSearchToposList(TopoSearch pTopoSearch) throws TechnicalException, NotFoundException, FunctionalException{
		
		if(pTopoSearch == null) {
			return getAllToposList();
		}
		
		Set<ConstraintViolation<TopoSearch>> vViolations = getConstraintValidator().validate(pTopoSearch);
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<TopoSearch> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Les critères de recherche ne sont pas valides",new ConstraintViolationException(vViolations));
		}
		try {
			return getDaoFactory().getTopoDao().getSearchToposList(pTopoSearch);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
		
	}

	@Override
	public List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pOwnerId == null) {
			throw new FunctionalException("L'identifiant du propriétaire des topos est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		try {
			return getDaoFactory().getTopoDao().getOwnerToposList(pOwnerId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException, FunctionalException, NotFoundException {

		if(pBorrowerId == null) {
			throw new FunctionalException("L'identifiant de l'emprunteur des topos est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		try {
			return getDaoFactory().getTopoDao().getBorrowerToposList(pBorrowerId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
		
	}

	@Override
	public Topo getTopoById(Integer pTopoId) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pTopoId == null) {
			throw new FunctionalException("L'identifiant du topo est incorrect (Indetifiant vide) - Echec de la recherche");
		}
		
		try {
			Topo vTopo = getDaoFactory().getTopoDao().getTopo(pTopoId);
			return clearBorrow(vTopo);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public void setBorrowable(Boolean pBorrowable, Integer pUserId,  Topo pTopo) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pBorrowable == null) {
			throw new FunctionalException("Aucune modification n'a été transmise (Propriété empruntable vide) - Echec de la mise à jour");
		}
		if(pUserId == null) {
			throw new FunctionalException("L'utilisateur demandant la modification n'a pas été transmis (Utilisateur vide) - Echec de la mise à jour");
		}
		if(pTopo == null) {
			throw new FunctionalException("Le topo à modifier n'a pas été transmis (topo vide) - Echec de la mise à jour");
		}
		
		if(pTopo.getOwner() != pUserId) {
			throw new FunctionalException("Seul le propriétaire du topo peut le modifier. L'utilisateur n'est pas le propriétaire du topo - Echec de la modification");
		}
		
		Boolean oldBorrowable = pTopo.isIsBorrowable();
		pTopo.setBorrowable(pBorrowable);
		try {
			getDaoFactory().getTopoDao().updateTopo(pTopo);
		}catch (TechnicalException pException) {
			pTopo.setBorrowable(oldBorrowable);
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			pTopo.setBorrowable(oldBorrowable);
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public Topo borrowTopo(Topo pTopo, Integer pNbDays, User pBorrower) throws TechnicalException, FunctionalException, NotFoundException {
		
		int vNbDaysBorrowMax = 14;
		
		if(pTopo == null) {
			throw new FunctionalException("Aucun topo à emprunter n'a été transmis (Topo vide) - Echec de l'emprunt");
		}
		if(pNbDays == null) {
			throw new FunctionalException("Aucune durée d'emprunt n'a été transmise (Durée vide) - Echec de l'emprunt");
		}
		if(pBorrower == null) {
			throw new FunctionalException("Aucun emprunteur n'a été transmis (Emprunteur vide) - Echec de l'emprunt");
		}
		
		if(pNbDays > vNbDaysBorrowMax) {
			throw new FunctionalException("Le topo ne peut être emprunté plus de "+ vNbDaysBorrowMax + " jours - Echec de l'emprunt");
		}
		if(pNbDays < 1) {
			throw new FunctionalException("Le nombre de jours d'emprunt est incorrect (inférieur à 1 - Echec de l'emprunt");
		}
		if(pTopo.isIsBorrowable() != true || (pTopo.getEndDateBorrow() != null && LocalDateTime.now().compareTo(pTopo.getEndDateBorrow()) < 0)) {
			throw new FunctionalException("Ce topo ne peut être emprunté - Echec de l'emprunt");
		}/*
		else if (pTopo.getEndDateBorrow() != null && LocalDateTime.now().compareTo(pTopo.getEndDateBorrow()) < 0) {
		}*/
		
	 	LocalDateTime date = LocalDateTime.now();
	 	date = date.plusDays(pNbDays);
	 	
	 	Topo vTopo = pTopo;
	 	vTopo.setEndDateBorrow(date);
	 	vTopo.setBorrower(pBorrower.getId());
	 	
	 	try {
	 		getDaoFactory().getTopoDao().updateTopo(vTopo);
	 		return vTopo;
	 	}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public void addTopo(Topo pTopo) throws TechnicalException, FunctionalException {
		
		if(pTopo == null) {
			throw new FunctionalException("Aucun topo n'a été transmis (Topo vide) - Echec de l'ajout");
		}
		
		Set<ConstraintViolation<Topo>> vViolations = getConstraintValidator().validate(pTopo);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<Topo> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Le topo à ajouter n'est pas valide",new ConstraintViolationException(vViolations));
		}
		
		try {
			int newTopoId = getDaoFactory().getTopoDao().insertTopo(pTopo);
			pTopo.setId(newTopoId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}
	}

	@Override
	public void modifyTopo(Topo pTopo, User pUser) throws TechnicalException, FunctionalException, NotFoundException {

		if(pTopo == null) {
			throw new FunctionalException("Aucune modification n'a été transmise (Topo vide) - Echec de la modification");
		}
		
		if(pUser == null) {
			throw new FunctionalException("Aucun utilisateur n'est associé à la modification (Utilisateur vide) - Echec de la modification");
		}else {
			try {
				if(pUser != getDaoFactory().getUserDao().getUserById(pTopo.getOwner()));
			} catch (NotFoundException pException) {
				throw new FunctionalException("Seul le créateur du Topo peut réaliser une modification. L'utilisateur ayant créé le topo n'a pu être trouvé - Echec de la modification",pException);
			}catch (TechnicalException pException) {
				throw new TechnicalException("Un problème technique empêche de vérifier quel est l'utilisateur ayant créé le commentaire - Echec de la modification",pException);
			}
		}
		
		Set<ConstraintViolation<Topo>> vViolations = getConstraintValidator().validate(pTopo);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<Topo> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Les modifications demandées ne sont pas valides",new ConstraintViolationException(vViolations));
		}
		
		try {
			getDaoFactory().getTopoDao().updateTopo(pTopo);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}
	
	
	private Topo clearBorrow(Topo pTopo) throws TechnicalException {
		
		Topo vTopo = pTopo;
		if(vTopo != null) {
			if(vTopo.getEndDateBorrow() != null && LocalDateTime.now().compareTo(vTopo.getEndDateBorrow()) > 0) {
				vTopo.setBorrower(null);
				vTopo.setEndDateBorrow(null);
				try {
					getDaoFactory().getTopoDao().updateTopo(vTopo);
				}catch (TechnicalException pException) {
					throw new TechnicalException("Un problème technique lors de la mise à jour de l'emprunteur du topo est survenu - Echec de la mise à jour");
				}catch (NotFoundException pException) {
					throw new TechnicalException("Un problème technique lors de la mise à jour de l'emprunteur du topo est survenu (Topo non trouvé) - Echec de la mise à jour");
				}
			}
		}else {
			throw new TechnicalException("Un problème lors de la mise à jour de l'emprunteur du topo est survenu (Topo vide)");
		}
		
		return vTopo;
	}

}
