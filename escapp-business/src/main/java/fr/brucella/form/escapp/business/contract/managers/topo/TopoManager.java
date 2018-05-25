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
    public List<Topo> getAllToposList() throws TechnicalException, NotFoundException;
    
    /**
     * Get the list of {@link Topo} that match to search criteria. If no search criteria are provided,
     * the list of all {@link Topo} is returned.
     * 
     * @param pTopoSearch the search criteria {@link TopoSearch}
     * 
     * @return the list of {@link Topo} that match to search criteria or the list of all {@link Topo} if
     *         no search criteria are provided
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if no {@link Topo} is found.
     * @throws FunctionalException - This exception is throws if the data of the search criteria
     *         {@link TopoSearch} are not valide.
     */
    public List<Topo> getSearchToposList(TopoSearch pTopoSearch) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Get the list of {@link Topo} with the specified owner id.
     * 
     * @param pOwnerId {@link Integer} id of the owner of the {@link Topo}
     * 
     * @return the list of {@link Topo} with the specified owner id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the owner id is null.
     * @throws NotFoundException - This exception is throws if no {@link Topo} is found.
     */
    public List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Get the list of {@link Topo} with the specified borrower id.
     * 
     * @param pBorrowerId {@link Integer} id of the borrower of the {@link Topo}
     * 
     * @return the list of {@link Topo} with the specified borrower id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the borrower id is null.
     * @throws NotFoundException - This exception is throws if no {@link Topo} is found.
     */
    public List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Get the {@link Topo} with the specified id.
     * 
     * @param pTopoId {@link Integer} id of the {@link Topo}
     * 
     * @return the {@link Topo} with the specified id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the topo id is null.
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    public Topo getTopoById(Integer pTopoId) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Change the borrowable property of the {@link Topo}. The borrowable property of the Topo give in
     * parameter will be updated.
     * 
     * @param pBorrowable The borrowable property : true is Topo is borrowable, false otherwise
     * @param pUserId {@link Integer} id of the {@link User} who wants to change the property.
     * @param pTopo the {@link Topo} to modify.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the borrowable property is null - This
     *         exception is throws if the {@link User} is null - This exception is throws if the
     *         {@link Topo} is null - This exception is throws if the {@link User} is not the owner of
     *         the {@link Topo}
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    public void setBorrowable(Boolean pBorrowable, Integer pUserId, Topo pTopo) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Indicate that the {@link Topo} is borrowed by a {@link User} borrower.
     * 
     * 
     * 
     * @param pTopo the {@link Topo} that will be borrowed.
     * @param pNbDays {@link Integer} number of days the {@link Topo} will be borrowed
     * @param pBorrower the {@link User} borrower.
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
    public Topo borrowTopo(Topo pTopo, Integer pNbDays, User pBorrower) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Validate and add the {@link Topo} to data store. The id will be added to the {@link Topo} give in
     * parameter.
     * 
     * @param pTopo the {@link Topo} to add.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Topo} pTopo is null. - This
     *         exception is throws if the data in the {@link Topo} are not valid.
     */
    public void addTopo(Topo pTopo) throws TechnicalException, FunctionalException;
    
    /**
     * Save the modification of the {@link Topo}.
     * 
     * @param pTopo the {@link Topo} modified to save
     * @param pUser {@link Integer} id of the {@link User} who wants to modify the {@link Topo}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Topo} is null - This
     *         exception is throws if the {@link User} is null - This exception is throws if the
     *         {@link User} is not the owner of the {@link Topo} - This exception is throws if the data
     *         in the {@link Topo} are not valid.
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    public void modifyTopo(Topo pTopo, User pUser) throws TechnicalException, FunctionalException, NotFoundException;
    
    /**
     * Delete the {@link Topo} with the specified id. Delete also the pdf file and all the
     * {@link fr.brucella.form.escapp.model.beans.comment.Comment} associated to the {@link Topo}
     * 
     * @param pTopoId {@link Integer} id of the {@link Topo} to delete.
     * @param pUser {@link Integer} id of the {@link User} who wants to delete the {@link Topo}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Topo} is null - This
     *         exception is throws if the {@link User} is null - This exception is throws if the
     *         {@link User} is not the owner of the {@link Topo} - This exception is throws if the data
     *         in the {@link Topo} are not valid.
     * @throws NotFoundException - This exception is throws if the {@link Topo} is not found.
     */
    public void deleteTopo(Integer pTopoId, User pUser) throws TechnicalException, FunctionalException, NotFoundException;
}
