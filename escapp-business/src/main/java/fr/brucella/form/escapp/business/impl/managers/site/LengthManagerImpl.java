package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.LengthManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * The Length Manager.
 *
 * @author BRUCELL2
 */
@Component
public class LengthManagerImpl extends AbstractManager implements LengthManager {
  
  // ----- Logger
  /**
   * Length Manager logger.
   */
  private static final Log LOG = LogFactory.getLog(LengthManagerImpl.class);
  
  /**
   * @see LengthManager#getLengthsRouteList(Integer)
   */
  @Override
  public List<Length> getLengthsRouteList(final Integer routeId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (routeId == null) {
      throw new FunctionalException("L'identifiant de la voie (Identifiant vide) - Echec de la recherche");
    }
    
    try {
      return this.getDaoFactory().getLengthDao().getLengthsList(routeId);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    } catch (NotFoundException pException) {
      throw new NotFoundException(pException.getMessage(), pException);
    }
    
  }
  
  /**
   * @see LengthManager#modifyLength(Length)
   */
  @Override
  public void modifyLength(final Length length) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (length == null) {
      throw new FunctionalException("Aucune modification n'a été transmise (Longueur vide) - Echec de la mise à jour");
    }
    
    final Set<ConstraintViolation<Length>> violations = this.getConstraintValidator().validate(length);
    
    if (!violations.isEmpty()) {
      for (final ConstraintViolation<Length> violation : violations) {
        LOG.debug(violation.getMessage());
      }
      throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(violations));
    }
    
    try {
      this.getDaoFactory().getLengthDao().updateLength(length);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    } catch (NotFoundException pException) {
      throw new NotFoundException(pException.getMessage(), pException);
    }
  }
  
  /**
   * @see LengthManager#addLength(Length)
   */
  @Override
  public void addLength(final Length length) throws TechnicalException, FunctionalException {
    
    if (length == null) {
      throw new FunctionalException("Aucune longueur n'a été transmise (Longueur vide) - Echec de l'ajout");
    }
    
    final Set<ConstraintViolation<Length>> violations = this.getConstraintValidator().validate(length);
    
    if (!violations.isEmpty()) {
      for (final ConstraintViolation<Length> violation : violations) {
        LOG.debug(violation.getMessage());
      }
      throw new FunctionalException("La longueur à ajouter n'est pas valide", new ConstraintViolationException(violations));
    }
    
    try {
      final int newLengthId = this.getDaoFactory().getLengthDao().insertLength(length);
      length.setId(newLengthId);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    }
  }
  
  /**
   * @see LengthManager#deleteLength(Integer)
   */
  @Override
  public void deleteLength(final Integer lengthId) throws TechnicalException, FunctionalException, NotFoundException {
    
    if (lengthId == null) {
      throw new FunctionalException("L'identifiant de la longueur à supprimer est incorrect (Identifiant null) - Echec de la suppression");
    }
    
    try {
      this.getDaoFactory().getLengthDao().deleteLength(lengthId);
    } catch (TechnicalException pException) {
      throw new TechnicalException(pException.getMessage(), pException);
    } catch (NotFoundException pException) {
      throw new NotFoundException(pException.getMessage(), pException);
    }
  }
  
}
