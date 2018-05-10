package fr.brucella.form.escapp.business.impl.managers.comment;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.comment.CommentManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

@Component
public class CommentManagerImpl extends AbstractManager implements CommentManager {
	
	// TODO improve addComment methods

	@Override
	public List<Comment> getCommentsSiteList(Integer pSiteId) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pSiteId == null) {
			throw new FunctionalException("L'identifiant du site est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsList(pSiteId, "Site");		
	}
	
	@Override
	public List<Pair<Comment, String>> getCommentsSiteListWithLogin(Integer pSiteId, String pOrder) throws TechnicalException, FunctionalException, NotFoundException{
		
		if(pSiteId == null) {
			throw new FunctionalException("L'identifiant du site est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsListWithLogin(pSiteId, "Site", pOrder);
	}

	@Override
	public List<Comment> getCommentsSectorList(Integer pSectorId) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pSectorId == null) {
			throw new FunctionalException("L'identifiant du secteur est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsList(pSectorId, "Sector");	
	}
	
	@Override
	public List<Pair<Comment, String>> getCommentsSectorListWithLogin(Integer pSectorId, String pOrder) throws TechnicalException, FunctionalException, NotFoundException{
		
		if(pSectorId == null) {
			throw new FunctionalException("L'identifiant du secteur est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsListWithLogin(pSectorId, "Sector", pOrder);
	}

	@Override
	public List<Comment> getCommentsRouteList(Integer pRouteId) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pRouteId == null) {
			throw new FunctionalException("L'identifiant de la voie est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsList(pRouteId, "Route");
	}
	
	@Override
	public List<Pair<Comment, String>> getCommentsRouteListWithLogin(Integer pRouteId, String pOrder) throws TechnicalException, FunctionalException, NotFoundException{
		
		if(pRouteId == null) {
			throw new FunctionalException("L'identifiant de la voie est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsListWithLogin(pRouteId, "Route", pOrder);
	}

	@Override
	public List<Comment> getCommentsLengthList(Integer pLengthId) throws TechnicalException, FunctionalException, NotFoundException {

		if(pLengthId == null) {
			throw new FunctionalException("L'identifiant de la longueur est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsList(pLengthId, "Length");
		
	}
	
	@Override
	public List<Pair<Comment, String>> getCommentsLengthListWithLogin(Integer pLengthId, String pOrder) throws TechnicalException, FunctionalException, NotFoundException{
		
		if(pLengthId == null) {
			throw new FunctionalException("L'identifiant de la longueur est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsListWithLogin(pLengthId, "Length", pOrder);
	}
	
	@Override
	public List<Comment> getCommentsTopoList(Integer pTopoId) throws TechnicalException, FunctionalException, NotFoundException {

		if(pTopoId == null) {
			throw new FunctionalException("L'identifiant du topo est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsList(pTopoId, "Topo");
	}
	
	@Override
	public List<Pair<Comment, String>> getCommentsTopoListWithLogin(Integer pTopoId, String pOrder) throws TechnicalException, FunctionalException, NotFoundException{
		
		if(pTopoId == null) {
			throw new FunctionalException("L'identifiant du topo est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		return getCommentsListWithLogin(pTopoId, "Topo", pOrder);
	}

	@Override
	public Comment getCommentById(Integer pCommentId) throws TechnicalException, FunctionalException, NotFoundException {

		if(pCommentId == null) {
			throw new FunctionalException("L'identifiant du commentaire recherché est incorrect (Identifiant vide) - Echec de la recherche");
		}
		
		try {
			return getDaoFactory().getCommentDao().getComment(pCommentId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}		
	}
	
	@Override
	public void addCommentSite(Comment pComment) throws TechnicalException, FunctionalException {
		
		if(pComment == null) {
			throw new FunctionalException("Aucun commentaire n'a été transmis (Commentaire vide) - Echec de l'ajout");
		}
		
		String pTargetType = "Site";
		
		if(pComment.getTargetType() != null && !pComment.getTargetType().isEmpty() && !pComment.getTargetType().equals(pTargetType)) {
			throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Site) - Echec de l'ajout du commentaire");
		} else {
			pComment.setTargetType(pTargetType);
		}
		
		try {
			getDaoFactory().getSiteDao().getSite(pComment.getIdCommentTarget());
		} catch (NotFoundException pException) {
			throw new FunctionalException("Le site cible du commentaire n'a pas été trouvé - Echec de l'ajout du commentaire.",pException);
		} catch (TechnicalException pException) {
			throw new TechnicalException("Un problème technique pour trouver le site cible est survenu - Echec de l'ajout du commentaire.", pException);
		}
		
		addComment(pComment, pTargetType);
	}

	@Override
	public void addCommentSector(Comment pComment) throws TechnicalException, FunctionalException {
		
		if(pComment == null) {
			throw new FunctionalException("Aucun commentaire n'a été transmis (Commentaire vide) - Echec de l'ajout");
		}
		
		String pTargetType = "Sector";
		
		if(pComment.getTargetType() != null && !pComment.getTargetType().isEmpty() && !pComment.getTargetType().equals(pTargetType)) {
			throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Secteur) - Echec de l'ajout du commentaire");
		} else {
			pComment.setTargetType(pTargetType);
		}
		
		try {
			getDaoFactory().getSectorDao().getSector(pComment.getIdCommentTarget());
		} catch (NotFoundException pException) {
			throw new FunctionalException("Le secteur cible du commentaire n'a pas été trouvé - Echec de l'ajout du commentaire.",pException);
		} catch (TechnicalException pException) {
			throw new TechnicalException("Un problème technique pour trouver le secteur cible est survenu - Echec de l'ajout du commentaire.", pException);
		}
		
		addComment(pComment, pTargetType);
	}

	@Override
	public void addCommentRoute(Comment pComment) throws TechnicalException, FunctionalException {
		
		if(pComment == null) {
			throw new FunctionalException("Aucun commentaire n'a été transmis (Commentaire vide) - Echec de l'ajout");
		}
		
		String pTargetType = "Route";
		
		if(pComment.getTargetType() != null && !pComment.getTargetType().isEmpty() && !pComment.getTargetType().equals(pTargetType)) {
			throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Voie) - Echec de l'ajout du commentaire");
		} else {
			pComment.setTargetType(pTargetType);
		}
		
		try {
			getDaoFactory().getRouteDao().getRoute(pComment.getIdCommentTarget());
		} catch (NotFoundException pException) {
			throw new FunctionalException("La voie cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.",pException);
		} catch (TechnicalException pException) {
			throw new TechnicalException("Un problème technique pour trouver la voie cible est survenu - Echec de l'ajout du commentaire.", pException);
		}
		
		addComment(pComment, pTargetType);
	}

	@Override
	public void addCommentLength(Comment pComment) throws TechnicalException, FunctionalException {
		
		if(pComment == null) {
			throw new FunctionalException("Aucun commentaire n'a été transmis (Commentaire vide) - Echec de l'ajout");
		}
		
		String pTargetType = "Length";
		
		if(pComment.getTargetType() != null && !pComment.getTargetType().isEmpty() && !pComment.getTargetType().equals(pTargetType)) {
			throw new FunctionalException("Le type de la cible du commentaire est différent de celui attendu (Longueur) - Echec de l'ajout du commentaire");
		} else {
			pComment.setTargetType(pTargetType);
		}
		
		try {
			getDaoFactory().getLengthDao().getLength(pComment.getIdCommentTarget());
		} catch (NotFoundException pException) {
			throw new FunctionalException("La longueur cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.",pException);
		} catch (TechnicalException pException) {
			throw new TechnicalException("Un problème technique pour trouver la longueur cible est survenu - Echec de l'ajout du commentaire.", pException);
		}
		
		addComment(pComment, pTargetType);
	}
	
	@Override
	public void addCommentTopo(Comment pComment) throws TechnicalException, FunctionalException{
		
		if(pComment == null) {
			throw new FunctionalException("Aucun commentaire n'a été transmis (Commentaire vide) - Echec de l'ajout");
		}
		
		String pTargetType = "Topo";
		
		if(pComment.getTargetType() != null && !pComment.getTargetType().isEmpty() && !pComment.getTargetType().equals(pTargetType)) {
			throw new FunctionalException("Le type de la cible du commentaire est différent de celui attentdu (Topo) - Echec de l'ajout du commentaire");
		}else {
			pComment.setTargetType(pTargetType);
		}
		
		try {
			getDaoFactory().getTopoDao().getTopo(pComment.getIdCommentTarget());
		}catch (NotFoundException pException) {
			throw new FunctionalException("Le topo cible du commentaire n'a pas été trouvée - Echec de l'ajout du commentaire.",pException);
		}catch (TechnicalException pException) {
			throw new TechnicalException("Un problème technique pour trouver le topo est survenu - Echec de l'ajout du commentaire.", pException);
		}
		
		addComment(pComment, pTargetType);
	}

	@Override
	public void deleteComment(Integer pCommentId) throws TechnicalException, FunctionalException, NotFoundException {
		
		if(pCommentId == null) {
			throw new FunctionalException("L'identifiant du commentaire à supprimer est incorrect (Identifiant vide) - Echec de la suppression");
		}
		
		try {
			getDaoFactory().getCommentDao().deleteComment(pCommentId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}

	@Override
	public void modifyComment(Comment pComment, User pUser) throws TechnicalException, FunctionalException, NotFoundException {
		
		
		if(pComment == null) {
			throw new FunctionalException("Aucune modification n'a été transmise (Commentaire vide) - Echec de la modification");
		}
		
		if(pUser == null) {
			throw new FunctionalException("Aucun utilisateur n'est associé à la modification (Utilisateur vide) - Echec de la modification");
		} else {
			try {
				if(pUser != getDaoFactory().getUserDao().getUserById(pComment.getEscappUser())){
					throw new FunctionalException("L'utilisateur demandant la modification ne correspond pas à celui qui a créé le commentaire - Echec de la modification");
				}
			} catch (NotFoundException pException) {
				throw new FunctionalException("Seul le créateur du commentaire peut réaliser une modification. L'utilisateur ayant créé le commentaire n'a pu être trouvé - Echec de la modification",pException);
			} catch (TechnicalException pException) {
				throw new TechnicalException("Un problème technique empêche de vérifier quel est l'utilisateur ayant créé le commentaire - Echec de la modificaiton",pException);
			}
		}
		
		Set<ConstraintViolation<Comment>> vViolations = getConstraintValidator().validate(pComment);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<Comment> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Les modifications demandées ne sont pas valides",new ConstraintViolationException(vViolations));
		}
		
		try {
			getDaoFactory().getCommentDao().updateComment(pComment);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}
		
	}
	
	private List<Comment> getCommentsList(Integer pIdCommentTarget, String pTargetType) throws TechnicalException, NotFoundException{
		
		try {
			return getDaoFactory().getCommentDao().getCommentsList(pTargetType, pIdCommentTarget);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}
	
	private List<Pair<Comment, String>> getCommentsListWithLogin(Integer pIdCommentTarget, String pTargetType, String pOrder) throws TechnicalException, NotFoundException{
		
		String vOrder;
		if(StringUtils.isEmpty(pOrder)) {
			vOrder = "DESC";
		}else {
			vOrder = pOrder;
		}
		
		try {
			return getDaoFactory().getCommentDao().getCommentsListWithLogin(pTargetType, pIdCommentTarget, vOrder);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}catch (NotFoundException pException) {
			throw new NotFoundException(pException.getMessage(),pException);
		}
	}
	
	private void addComment(Comment pComment, String pTargetType) throws TechnicalException, FunctionalException {
		
		Set<ConstraintViolation<Comment>> vViolations = getConstraintValidator().validate(pComment);
		
		if(!vViolations.isEmpty()) {
			for(ConstraintViolation<Comment> violation : vViolations) {
				System.out.println(violation.getMessage());
			}
			throw new FunctionalException("Le commentaire à ajouter n'est pas valide",new ConstraintViolationException(vViolations));
		}
		
		try {
			int newCommentId = getDaoFactory().getCommentDao().insertComment(pComment);
			pComment.setId(newCommentId);
		}catch (TechnicalException pException) {
			throw new TechnicalException(pException.getMessage(),pException);
		}
	}

}
