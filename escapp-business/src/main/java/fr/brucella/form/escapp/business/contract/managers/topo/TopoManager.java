package fr.brucella.form.escapp.business.contract.managers.topo;

import java.util.List;

import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.TopoSearch;

/**
 *
 * @author BRUCELLA2
 */
public interface TopoManager {

    /**
     * Get the list of all {@link Topo}.
     *
     * @return the list of {@link Topo}.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if no {@link Topo} is found.
     */
    List<Topo> getAllToposList() throws TechnicalException, NotFoundException;

    /**
     * Get the list of {@link Topo} that match to search criteria. If no search criteria are provided,
     * the list of all {@link Topo} is returned.
     *
     * @param topoSearch the search criteria {@link TopoSearch}
     *
     * @return the list of {@link Topo} that match to search criteria or the list of all {@link Topo} if
     *         no search criteria are provided
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if no {@link Topo} is found.
     * @throws FunctionalException - This exception is throws if the data of the search criteria
     *         {@link TopoSearch} are not valide.
     */
    List<Topo> getSearchToposList(final TopoSearch topoSearch) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the list of {@link Topo} with the specified owner id.
     *
     * @param ownerId {@link Integer} id of the owner of the {@link Topo}
     *
     * @return the list of {@link Topo} with the specified owner id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the owner id is null.
     * @throws NotFoundException - This exception is throws if no {@link Topo} is found.
     */
    List<Topo> getOwnerToposList(final Integer ownerId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the list of {@link Topo} with the specified borrower id.
     *
     * @param borrowerId {@link Integer} id of the borrower of the {@link Topo}
     *
     * @return the list of {@link Topo} with the specified borrower id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the borrower id is null.
     * @throws NotFoundException - This exception is throws if no {@link Topo} is found.
     */
    List<Topo> getBorrowerToposList(final Integer borrowerId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link Topo} with the specified id.
     *
     * @param topoId {@link Integer} id of the {@link Topo}
     *
     * @return the {@link Topo} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the topo id is null.
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    Topo getTopoById(final Integer topoId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Change the borrowable property of the {@link Topo}. The borrowable property of the Topo give in
     * parameter will be updated.
     *
     * @param borrowable The borrowable property : true is Topo is borrowable, false otherwise
     * @param userId {@link Integer} id of the {@link User} who wants to change the property.
     * @param topo the {@link Topo} to modify.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the borrowable property is null - This
     *         exception is throws if the {@link User} is null - This exception is throws if the
     *         {@link Topo} is null - This exception is throws if the {@link User} is not the owner of
     *         the {@link Topo}
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    void setBorrowable(final Boolean borrowable, final Integer userId, final Topo topo) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Indicate that the {@link Topo} is borrowed by a {@link User} borrower.
     *
     *
     *
     * @param topoToBorrow the {@link Topo} that will be borrowed.
     * @param nbDays {@link Integer} number of days the {@link Topo} will be borrowed
     * @param borrower the {@link User} borrower.
     *
     * @return a {@link Topo} modified with the new values. Borrower property will be updated with the
     *         {@link User} borrower in the {@link Topo} returned. End date of borrow will be updated
     *         with the number of days of borrow in the {@link Topo} returned.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Topo} is null - This
     *         exception is throws if the {@link User} is null - This exception is throws if the number
     *         of days is null - This exception is throws if the number of days is more than 14. - This
     *         exception is throws if the number of days is less than 1. - This exception is throws if
     *         the {@link Topo} is already borrowed by someone.
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    Topo borrowTopo(final Topo topoToBorrow, final Integer nbDays, final User borrower) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Validate and add the {@link Topo} to data store. The id will be added to the {@link Topo} give in
     * parameter.
     *
     * @param topo the {@link Topo} to add.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Topo} topo is null. - This
     *         exception is throws if the data in the {@link Topo} are not valid.
     */
    void addTopo(final Topo topo) throws TechnicalException, FunctionalException;

    /**
     * Save the modification of the {@link Topo}.
     *
     * @param topo the {@link Topo} modified to save
     * @param user {@link Integer} id of the {@link User} who wants to modify the {@link Topo}.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Topo} is null - This
     *         exception is throws if the {@link User} is null - This exception is throws if the
     *         {@link User} is not the owner of the {@link Topo} - This exception is throws if the data
     *         in the {@link Topo} are not valid.
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    void modifyTopo(final Topo topo, final User user) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Delete the {@link Topo} with the specified id. Delete also the pdf file and all the
     * {@link fr.brucella.form.escapp.model.beans.comment.Comment} associated to the {@link Topo}
     *
     * @param topoId {@link Integer} id of the {@link Topo} to delete.
     * @param user {@link Integer} id of the {@link User} who wants to delete the {@link Topo}.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Topo} is null - This
     *         exception is throws if the {@link User} is null - This exception is throws if the
     *         {@link User} is not the owner of the {@link Topo} - This exception is throws if the data
     *         in the {@link Topo} are not valid.
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    void deleteTopo(final Integer topoId, final User user) throws TechnicalException, FunctionalException, NotFoundException;
}
