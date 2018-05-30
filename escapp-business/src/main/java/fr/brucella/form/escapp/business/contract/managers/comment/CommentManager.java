package fr.brucella.form.escapp.business.contract.managers.comment;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Interface for Comment Manager
 *
 * @author BRUCELLA2
 */
public interface CommentManager {

    /**
     * Get the {@link List} of {@link Comment} for the {@link Site} with the specified id.
     *
     * @param siteId {@link Integer} id of the {@link Site}.
     *
     * @return the {@link List} of {@link Comment} for the {@link Site} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the site id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Comment> getCommentsSiteList(final Integer siteId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User} who
     * write the {@link Comment} for the {@link Site} with the specified id.
     *
     * @param siteId {@link Integer} id of the {@link Site}.
     * @param order {@link String} "ASC" or "DESC" for a ASCENDING sort or DESCENDING sort by the id of
     *        the {@link Comment}.
     *
     * @return the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User}
     *         who write the {@link Comment} and sorted by id of the {@link Comment} for the
     *         {@link Site} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if pSiteId is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Pair<Comment, String>> getCommentsSiteListWithLogin(final Integer siteId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Comment} for the {@link Sector} with the specified id.
     *
     * @param sectorId {@link Integer} id of the {@link Sector}.
     *
     * @return the {@link List} of {@link Comment} for the {@link Sector} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the sector id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Comment> getCommentsSectorList(final Integer sectorId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write
     * the {@link Comment} for the {@link Sector} with the specified id.
     *
     * @param sectorId {@link Integer} id of the {@link Sector}.
     * @param order {@link String} "ASC" or "DESC" for a ASCENDING sort or DESCENDING sort by the id of
     *        the {@link Comment}.
     *
     * @return the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User}
     *         who write the {@link Comment} and sorted by id of the {@link Comment} for the
     *         {@link Sector} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the sector id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Pair<Comment, String>> getCommentsSectorListWithLogin(final Integer sectorId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Comment} for the {@link Route} with the specified id.
     *
     * @param routeId {@link Integer} id of the {@link Route}.
     *
     * @return the {@link List} of {@link Comment} for the {@link Route} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the route id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Comment> getCommentsRouteList(final Integer routeId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write
     * the {@link Comment} for the {@link Route} with the specified id.
     *
     * @param routeId {@link Integer} id of the {@link Route}.
     * @param order {@link String} "ASC" or "DESC" for a ASCENDING sort or DESCENDING sort by the id of
     *        the {@link Comment}.
     *
     * @return the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User}
     *         who write the {@link Comment} and sorted by id of the {@link Comment} for the
     *         {@link Route} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the route id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Pair<Comment, String>> getCommentsRouteListWithLogin(final Integer routeId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Comment} for the {@link Length} with the specified id.
     *
     * @param lengthId {@link Integer} id of the {@link Length}.
     *
     * @return the {@link List} of {@link Comment} for the {@link Length} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the length id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Comment> getCommentsLengthList(final Integer lengthId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write
     * the {@link Comment} for the {@link Length} with the specified id.
     *
     * @param lengthId {@link Integer} id of the {@link Length}.
     * @param order {@link String} "ASC" or "DESC" for a ASCENDING sort or DESCENDING sort by the id of
     *        the {@link Comment}.
     *
     * @return the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User}
     *         who write the {@link Comment} and sorted by id of the {@link Comment} for the
     *         {@link Length} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the length id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Pair<Comment, String>> getCommentsLengthListWithLogin(final Integer lengthId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Comment} for the {@link Topo} with the specified id.
     *
     * @param topoId {@link Integer} id of the {@link Topo}.
     *
     * @return the {@link List} of {@link Comment} for the {@link Topo} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the topo id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Comment> getCommentsTopoList(final Integer topoId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write
     * the {@link Comment} for the {@link Topo} with the specified id.
     *
     * @param topoId {@link Integer} id of the {@link Topo}.
     * @param order {@link String} "ASC" or "DESC" for a ASCENDING sort or DESCENDING sort by the id of
     *        the {@link Comment}.
     *
     * @return the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User}
     *         who write the {@link Comment} and sorted by id of the {@link Comment} for the
     *         {@link Topo} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the topo id is null.
     * @throws NotFoundException - This exception is throws if no {@link Comment} is found.
     */
    List<Pair<Comment, String>> getCommentsTopoListWithLogin(final Integer topoId, final String order)
            throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Get the {@link Comment} with the specified id.
     *
     * @param commentId {@link Integer} id of the {@link Comment}.
     *
     * @return the {@link Comment} with the specified id.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the comment id is null.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Comment} is not found.
     */
    Comment getCommentById(final Integer commentId) throws TechnicalException, FunctionalException, NotFoundException;


    /**
     * Add a {@link Comment} to the {@link Site} with the specified id. The target type and the id will
     * be added to the {@link Comment} give in parameter.
     *
     * @param comment the {@link Comment} to add. the target type and the id will be added to the
     *        comment.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Comment} is null. - This
     *         exception is throws if the target of the comment is not a {@link Site}. - This exception
     *         is throws if the target {@link Site} is not found. - This exception is throws if the data
     *         in the {@link Comment} are not valid.
     */
    void addCommentSite(final Comment comment) throws TechnicalException, FunctionalException;

    /**
     * Add a {@link Comment} to the {@link Sector} with the specified id. The target type and the id
     * will be added to the {@link Comment} give in parameter.
     *
     * @param comment the {@link Comment} to add. the target type and the id will be added to the
     *        comment
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Comment} is null. - This
     *         exception is throws if the target of the comment is not a {@link Sector}. - This
     *         exception is throws if the target {@link Sector} is not found. - This exception is throws
     *         if the data in the {@link Comment} are not valid.
     */
    void addCommentSector(final Comment comment) throws TechnicalException, FunctionalException;

    /**
     * Add a {@link Comment} to the {@link Route} with the specified id. The target type and the id will
     * be added to the {@link Comment} give in parameter.
     *
     * @param comment the {@link Comment} to add. the target type and the id will be added to the
     *        comment
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Comment} is null. - This
     *         exception is throws if the target of the comment is not a {@link Route}. - This exception
     *         is throws if the target {@link Route} is not found. - This exception is throws if the
     *         data in the {@link Comment} are not valid.
     */
    void addCommentRoute(final Comment comment) throws TechnicalException, FunctionalException;

    /**
     * Add a {@link Comment} to the {@link Length} with the specified id. The target type and the id
     * will be added to the {@link Comment} give in parameter.
     *
     * @param comment the {@link Comment} to add. the target type and the id will be added to the
     *        comment
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Comment} is null. - This
     *         exception is throws if the target of the comment is not a {@link Length}. - This
     *         exception is throws if the target {@link Length} is not found. - This exception is throws
     *         if the data in the {@link Comment} are not valid.
     */
    void addCommentLength(final Comment comment) throws TechnicalException, FunctionalException;

    /**
     * Add a {@link Comment} to the {@link Topo} with the specified id. The target type and the id will
     * be added to the {@link Comment} give in parameter.
     *
     * @param comment the {@link Comment} to add. the target type and the id will be added to the
     *        comment
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Comment} is null. - This
     *         exception is throws if the target of the comment is not a {@link Topo}. - This exception
     *         is throws if the target {@link Topo} is not found. - This exception is throws if the data
     *         in the {@link Comment} are not valid.
     */
    void addCommentTopo(final Comment comment) throws TechnicalException, FunctionalException;

    /**
     * Delete the {@link Comment} with the specified id.
     *
     * @param commentId {@link Integer} id of the {@link Comment} to delete.
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the comment id is null.
     * @throws NotFoundException - This exception is throws if there is no technical exception and the
     *         {@link Comment} is not found.
     */
    void deleteComment(final Integer commentId) throws TechnicalException, FunctionalException, NotFoundException;

    /**
     * Save the modification of the {@link Comment}
     *
     * @param comment the {@link Comment} modified to save.
     * @param user the {@link User} who modified the {@link Comment}
     *
     * @throws TechnicalException - wraps technical exception caused during data access.
     * @throws FunctionalException - This exception is throws if the {@link Comment} pComment is null. -
     *         This exception is throws if the {@link User} is null. - This exception is throws if the
     *         {@link User} is not the {@link User} who create the {@link Comment}. - This exception is
     *         throws if the {@link User} who create the {@link Comment} is not found. - This exception
     *         is throws if the data in the {@link Comment} are not valid.
     * @throws NotFoundException - This exception is throws if the {@link Comment} is not found.
     */
    void modifyComment(final Comment comment, final User user) throws TechnicalException, FunctionalException, NotFoundException;
}
