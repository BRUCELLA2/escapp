package fr.brucella.form.escapp.business.contract.managers.site;

import java.util.List;

import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for Length Manager
 *
 * @author BRUCELLA2
 */
public interface LengthManager {
  
  /**
   * Get the list of {@link Length} with the specified route id.
   *
   * @param routeId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.site.Route}
   *        to which the {@link Length} belongs.
   *
   * @return the list of {@link Length} with the specified route id.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the route id is null.
   * @throws NotFoundException - This exception is throws if no {@link Length} is found.
   */
  List<Length> getLengthsRouteList(final Integer routeId) throws TechnicalException, FunctionalException, NotFoundException;
  
  /**
   * Save the modification of the {@link Length}.
   *
   * @param length the {@link Length} modified to save.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the {@link Length} length is null. -
   *         This exception is throws if the data in the {@link Length} are not valid.
   * @throws NotFoundException - This exception is throws if the {@link Length} is not found.
   */
  void modifyLength(final Length length) throws TechnicalException, FunctionalException, NotFoundException;
  
  /**
   * Validate and add the {@link Length} to data store. The id will be added to the {@link Length}
   * give in parameter.
   *
   * @param length the {@link Length} to add.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the {@link Length} is null. - This
   *         exception is throws if the data in the {@link Length} are not valid.
   */
  void addLength(final Length length) throws TechnicalException, FunctionalException;
  
  /**
   * Delete the {@link Length} with the specified id.
   *
   * @param lengthId {@link Integer} id of the {@link Length} to delete.
   *
   * @throws TechnicalException - wraps technical exception caused during data access.
   * @throws FunctionalException - This exception is throws if the length id is null.
   * @throws NotFoundException - This exception is throws if the {@link Length} is not found.
   */
  void deleteLength(final Integer lengthId) throws TechnicalException, FunctionalException, NotFoundException;
}
