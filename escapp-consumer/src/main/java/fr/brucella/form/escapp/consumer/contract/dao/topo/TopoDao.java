package fr.brucella.form.escapp.consumer.contract.dao.topo;

import java.util.List;

import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.TopoSearch;

/**
 * Interface for the Topo Data Access Object.
 *
 * @author BRUCELLA2
 */
public interface TopoDao {
    
    /**
     * Get the {@link Topo} with the specified id from the datastore.
     * 
     * @param pTopoId {@link Integer} id of the {@link Topo}.
     * 
     * @return the {@link Topo} with the specified id.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Topo} is not found.
     */
    Topo getTopo(Integer pTopoId) throws TechnicalException, NotFoundException;
    
    
    /**
     * Get the list of all {@link Topo} from the datastore.
     * 
     * @return the list of all {@link Topo}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Topo} is found.
     */
    List<Topo> getAllToposList() throws TechnicalException, NotFoundException;
    
    /**
     * Get the list of {@link Topo} searched from the datastore.
     * 
     * @param pTopoSearch the {@link TopoSearch} which represents the topo searching criteria.
     * 
     * @return the list of {@link Topo} searched.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws of there is no technical exception and no
     *         {@link Topo} is found.
     */
    List<Topo> getSearchToposList(TopoSearch pTopoSearch) throws TechnicalException, NotFoundException;
    
    /**
     * Get a list of {@link Topo} with the specified owner from the datastore.
     * 
     * @param pOwnerId {@link Integer} id of the {@link fr.brucella.form.escapp.model.beans.user.User}
     *        who owns the {@link Topo}.
     * 
     * @return a list of {@link Topo} with the specified owner.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Topo} is found.
     */
    List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException, NotFoundException;
    
    
    /**
     * Get a list of {@link Topo} with the specified borrower from the datastore.
     * 
     * @param pBorrowerId {@link Integer} id of the
     *        {@link fr.brucella.form.escapp.model.beans.user.User} who borrowed the {@link Topo}.
     * 
     * @return a list of {@link Topo} with the specified borrower.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and no
     *         {@link Topo} is found.
     */
    List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException, NotFoundException;
    
    
    /**
     * Update an existing {@link Topo} in the datastore.
     * 
     * @param pTopo The {@link Topo} with the updated informations to save in datastore.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Topo} is not found.
     */
    void updateTopo(Topo pTopo) throws TechnicalException, NotFoundException;
    
    
    /**
     * Insert a new {@link Topo} in datastore.
     * 
     * @param pTopo The {@link Topo} to insert in datastore.
     * 
     * @return the id of the new {@link Topo}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     */
    int insertTopo(Topo pTopo) throws TechnicalException;
    
    
    /**
     * Delete the {@link Topo} with the specified id in the datastore.
     * 
     * @param pTopoId {@link Integer} id of the {@link Topo}.
     * 
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Topo} is not found.
     */
    void deleteTopo(Integer pTopoId) throws TechnicalException, NotFoundException;
}
