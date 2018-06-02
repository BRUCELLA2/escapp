package fr.brucella.form.escapp.webapp.action.sites;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Actions related to site details (Route details included).
 *
 * @author BRUCELLA2
 */
public class SiteDetailsAction extends ActionSupport {
  
  /**
   * Serial ID.
   */
  private static final long            serialVersionUID = -8899315209160242753L;
  
  // ----- Logger
  /**
   * Site details action logger.
   */
  private static final Log             LOG              = LogFactory.getLog(SiteDetailsAction.class);
  
  // ----- Input
  /**
   * Id of the {@link Site} or {@link Route}.
   *
   * @see #getId()
   * @see #setId(Integer)
   */
  private Integer                      id;
  
  // ----- Output
  /**
   * The {@link Site} detailed in the web page.
   *
   * @see #getSite()
   * @see #setSite(Site)
   */
  private Site                         site;
  
  /**
   * The {@link Sector} to which the {@link Route} belongs.
   *
   * @see #getSector()
   * @see #setSector(Sector)
   */
  private Sector                       sector;
  
  /**
   * List of all {@link Sector} of the {@link Site}.
   *
   * @see #getSectorsList()
   * @see #setSectorsList(List)
   */
  private List<Sector>                 sectorsList;
  
  /**
   * The {@link Route} detailed in the web page.
   *
   * @see #getRoute()
   * @see #setRoute(Route)
   */
  private Route                        route;
  
  /**
   * List of all {@link Route} of the {@link Site}.
   *
   * @see #getRoutesList()
   * @see #setRoutesList(List)
   */
  private List<Route>                  routesList;
  
  /**
   * List of all {@link Length} of the {@link Route}.
   *
   * @see #getLengthsList()
   * @see #setLengthsList(List)
   */
  private List<Length>                 lengthsList;
  
  /**
   * Number of points of the {@link Route}.
   *
   * @see #getNbPoints()
   * @see #setNbPoints(Integer)
   */
  private int                          nbPoints;
  
  /**
   * Number of {@link Comment} for the {@link Site}.
   *
   * @see #getNbCommentsSite()
   * @see #setNbCommentsSite(Integer)
   */
  private Integer                      nbCommentsSite;
  
  /**
   * {@link List} of {@link Pair} of id of the {@link Sector} and number of {@link Comment} for this
   * {@link Sector}. To the left, id of the {@link Sector}, to the right number of {@link Comment} for
   * this {@link Sector}.
   *
   * @see #getNbCommentsSectorsList()
   * @see #setNbCommentsSectorsList(List)
   */
  private List<Pair<Integer, Integer>> nbCommentsSectorsList;
  
  /**
   * Number of {@link Comment} for the {@link Route}.
   *
   * @see #getNbCommentsRoute()
   * @see #setNbCommentsRoute(Integer)
   */
  private Integer                      nbCommentsRoute;
  
  /**
   * {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User} who write the
   * {@link Comment} for the {@link Site}.
   *
   * @see #getCommentsSiteList()
   * @see #setCommentsSiteList(List)
   */
  private List<Pair<Comment, String>>  commentsSiteList;
  
  /**
   * {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write the
   * {@link Comment} for all the {@link Sector} of the {@link Site}.
   *
   * @see #getCommentsSectorList()
   * @see #setCommentsSectorList(List)
   */
  private List<Pair<Comment, String>>  commentsSectorList;
  
  /**
   * {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write the
   * {@link Comment} for the {@link Route}.
   *
   * @see #getCommentsRouteList()
   * @see #setCommentsRouteList(List)
   */
  private List<Pair<Comment, String>>  commentsRouteList;
  
  // ----- Manager
  
  /**
   * The Manager Factory Manager Factory allow to get and set business managers.
   */
  @Autowired
  private ManagerFactory               managerFactory;
  
  
  // ===== Getters =====
  
  /**
   * Get the id of the {@link Site} or {@link Route}.
   *
   * @return the id of the {@link Site} or {@link Route}.
   *
   * @see #id
   * @see #setId(Integer)
   */
  public Integer getId() {
    return this.id;
  }
  
  /**
   * Get the {@link Site} detailed in the web page.
   *
   * @return the {@link Site} detailed in the web page.
   *
   * @see #site
   * @see #setSite(Site)
   */
  public Site getSite() {
    return this.site;
  }
  
  /**
   * Get the {@link Sector} to which the {@link Route} belongs.
   *
   * @return the {@link Sector} to which the {@link Route} belongs.
   *
   * @see #sector
   * @see #setSector(Sector)
   */
  public Sector getSector() {
    return this.sector;
  }
  
  /**
   * Get the list of all {@link Sector} of the {@link Site}.
   *
   * @return the list of all {@link Sector} of the {@link Site}
   *
   * @see #sectorsList
   * @see #setSectorsList(List)
   */
  public List<Sector> getSectorsList() {
    return this.sectorsList;
  }
  
  /**
   * Get the {@link Route} detailed in the web page.
   *
   * @return the {@link Route} detailed in the web page.
   *
   * @see #route
   * @see #setRoute(Route)
   */
  public Route getRoute() {
    return this.route;
  }
  
  /**
   * Get the list of all {@link Route} of the {@link Site}.
   *
   * @return the list of all {@link Route} of the {@link Site}
   *
   * @see #routesList
   * @see #setRoutesList(List)
   */
  public List<Route> getRoutesList() {
    return this.routesList;
  }
  
  /**
   * Get the list of all {@link Length} of the {@link Route}.
   *
   * @return the list of all {@link Length} of the {@link Route}
   *
   * @see #lengthsList
   * @see #setLengthsList(List)
   */
  public List<Length> getLengthsList() {
    return this.lengthsList;
  }
  
  /**
   * Get the number of points of the {@link Route}.
   *
   * @return the number of points of the {@link Route}
   *
   * @see #nbPoints
   * @see #setNbPoints(Integer)
   */
  public int getNbPoints() {
    return this.nbPoints;
  }
  
  /**
   * Get the number of {@link Comment} for the {@link Site}.
   *
   * @return the number of {@link Comment} for the {@link Site}
   *
   * @see #nbCommentsSite
   * @see #setNbCommentsSite(Integer)
   */
  public Integer getNbCommentsSite() {
    return this.nbCommentsSite;
  }
  
  /**
   * Get the number of {@link Comment} for the {@link Route}.
   *
   * @return the number of {@link Comment} for the {@link Route}.
   *
   * @see #nbCommentsRoute
   * @see #setNbCommentsRoute(Integer)
   */
  public Integer getNbCommentsRoute() {
    return this.nbCommentsRoute;
  }
  
  /**
   * Get the {@link List} of {@link Pair} of id of the {@link Sector} and number of {@link Comment}
   * for this {@link Sector}.
   *
   * @return the {@link List} of {@link Pair} of id of the {@link Sector} and number of
   *         {@link Comment} for this {@link Sector}. To the left, id of the {@link Sector}, to the
   *         right number of {@link Comment} for this {@link Sector}.
   *
   * @see #nbCommentsSectorsList
   * @see #setNbCommentsSectorsList(List)
   */
  public List<Pair<Integer, Integer>> getNbCommentsSectorsList() {
    return this.nbCommentsSectorsList;
  }
  
  /**
   * Get the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User} who
   * write the {@link Comment} for the {@link Site}.
   *
   * @return the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User}
   *         who write the {@link Comment} for the {@link Site}.
   *
   * @see #commentsSiteList
   * @see #setCommentsSiteList(List)
   */
  public List<Pair<Comment, String>> getCommentsSiteList() {
    return this.commentsSiteList;
  }
  
  /**
   * Get the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write
   * the {@link Comment} for all the {@link Sector} of the {@link Site}.
   *
   * @return the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who
   *         write the {@link Comment} for all the {@link Sector} of the {@link Site}.
   *
   * @see #commentsSectorList
   * @see #setCommentsSectorList(List)
   */
  public List<Pair<Comment, String>> getCommentsSectorList() {
    return this.commentsSectorList;
  }
  
  /**
   * Get the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write
   * the {@link Comment} for the {@link Route}.
   *
   * @return the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who
   *         write the {@link Comment} for the {@link Route}.
   *
   * @see #commentsRouteList
   * @see #setCommentsRouteList(List)
   */
  public List<Pair<Comment, String>> getCommentsRouteList() {
    return this.commentsRouteList;
  }
  
  
  // ===== Setters =====
  
  
  /**
   * Set the id of the {@link Site} or {@link Route}.
   *
   * @param id the id of the {@link Site} or {@link Route}
   *
   * @see #id
   * @see #getId()
   */
  public void setId(final Integer id) {
    this.id = id;
  }
  
  /**
   * Set the {@link Site} detailed in the web page.
   *
   * @param site the {@link Site} detailed in the web page.
   *
   * @see #site
   * @see #getSite()
   */
  public void setSite(final Site site) {
    this.site = site;
  }
  
  /**
   * Set the {@link Sector} to which the {@link Route} belongs.
   *
   * @param sector the {@link Sector} to which the {@link Route} belongs.
   *
   * @see #sector
   * @see #getSector()
   */
  public void setSector(final Sector sector) {
    this.sector = sector;
  }
  
  /**
   * Set the list of all {@link Sector} of the {@link Site}.
   *
   * @param sectorsList the list of all {@link Sector} of the {@link Site}
   *
   * @see #sectorsList
   * @see #getSectorsList()
   */
  public void setSectorsList(final List<Sector> sectorsList) {
    this.sectorsList = sectorsList;
  }
  
  /**
   * Set the {@link Route} detailed in the web page.
   *
   * @param route the {@link Route} detailed in the web page.
   *
   * @see #route
   * @see #getRoute()
   */
  public void setRoute(final Route route) {
    this.route = route;
  }
  
  /**
   * Set the list of all {@link Route} of the {@link Site}.
   *
   * @param routesList the list of all {@link Route} of the {@link Site}
   *
   * @see #routesList
   * @see #getRoutesList()
   */
  public void setRoutesList(final List<Route> routesList) {
    this.routesList = routesList;
  }
  
  /**
   * Set the list of all {@link Length} of the {@link Route}.
   *
   * @param lengthsList the list of all {@link Length} of the {@link Route}
   *
   * @see #lengthsList
   * @see #getLengthsList()
   */
  public void setLengthsList(final List<Length> lengthsList) {
    this.lengthsList = lengthsList;
  }
  
  /**
   * Set the number of points of the {@link Route}.
   *
   * @param nbPoints the number of points of the {@link Route}
   *
   * @see #nbPoints
   * @see #getNbPoints()
   */
  public void setNbPoints(final Integer nbPoints) {
    this.nbPoints = nbPoints;
  }
  
  /**
   * Set the number of {@link Comment} for the {@link Site}.
   *
   * @param nbCommmentsSite the number of {@link Comment} for the {@link Site}
   *
   * @see #nbCommentsSite
   * @see #getNbCommentsSite()
   */
  public void setNbCommentsSite(final Integer nbCommmentsSite) {
    this.nbCommentsSite = nbCommmentsSite;
  }
  
  /**
   * Set the {@link List} of {@link Pair} of id of the {@link Sector} and number of {@link Comment}
   * for this {@link Sector}.
   *
   * @param nbCommentsSectorsList the {@link List} of {@link Pair} of id of the {@link Sector} and
   *        number of {@link Comment} for this {@link Sector}. To the left, id of the {@link Sector},
   *        to the right number of {@link Comment} for this {@link Sector}.
   *
   * @see #nbCommentsSectorsList
   * @see #getNbCommentsSectorsList()
   */
  public void setNbCommentsSectorsList(final List<Pair<Integer, Integer>> nbCommentsSectorsList) {
    this.nbCommentsSectorsList = nbCommentsSectorsList;
  }
  
  /**
   * Set the number of {@link Comment} for the {@link Route}.
   *
   * @param nbCommentsRoute the number of {@link Comment} for the {@link Route}.
   *
   * @see #nbCommentsRoute
   * @see #getNbCommentsRoute()
   */
  public void setNbCommentsRoute(final Integer nbCommentsRoute) {
    this.nbCommentsRoute = nbCommentsRoute;
  }
  
  /**
   * Set the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User} who
   * write the {@link Comment} for the {@link Site}.
   *
   * @param commentsSiteList the {@link List} of {@link Pair} of {@link Comment} with the login of the
   *        {@link User} who write the {@link Comment} for the {@link Site}.
   *
   * @see #commentsSiteList
   * @see #getCommentsSiteList()
   */
  public void setCommentsSiteList(final List<Pair<Comment, String>> commentsSiteList) {
    this.commentsSiteList = commentsSiteList;
  }
  
  /**
   * Set the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write
   * the {@link Comment} for all the {@link Sector} of the {@link Site}.
   *
   * @param commentsSectorList the {@link List} of {@link Pair} of {@link Comment} with the login of
   *        {@link User} who write the {@link Comment} for all the {@link Sector} of the {@link Site}.
   *
   * @see #commentsSectorList
   * @see #getCommentsSectorList()
   */
  public void setCommentsSectorList(final List<Pair<Comment, String>> commentsSectorList) {
    this.commentsSectorList = commentsSectorList;
  }
  
  /**
   * Set the {@link List} of {@link Pair} of {@link Comment} with the login of {@link User} who write
   * the {@link Comment} for the {@link Route}.
   *
   * @param commentsRouteList the {@link List} of {@link Pair} of {@link Comment} with the login of
   *        {@link User} who write the {@link Comment} for the {@link Route}.
   *
   * @see #commentsRouteList
   * @see #getCommentsRouteList()
   */
  public void setCommentsRouteList(final List<Pair<Comment, String>> commentsRouteList) {
    this.commentsRouteList = commentsRouteList;
  }
  
  // ===== Methods =====
  
  /**
   * Prepare the site details with comments.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doSiteDetails() {
    
    if (this.id == null) {
      LOG.error("Site id null - site details failure");
      this.addActionError("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
      return Action.ERROR;
    }
    
    try {
      this.site = this.managerFactory.getSiteManager().getSiteById(this.id);
    } catch (TechnicalException | NotFoundException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    
    /*
     * Get comment for the site
     */
    try {
      this.commentsSiteList = this.managerFactory.getCommentManager().getCommentsSiteListWithLogin(this.id, "ASC");
      this.nbCommentsSite = this.commentsSiteList.size();
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    } catch (NotFoundException exception) {
      this.nbCommentsSite = 0;
      this.addActionMessage("Aucun commentaire");
    }
    
    /*
     * Get all sector list for the site Get all route list for the site Get comments list for the sector
     */
    try {
      this.sectorsList = this.managerFactory.getSectorManager().getSectorsSiteList(this.site.getId());
      this.routesList = new ArrayList<>();
      this.commentsSectorList = new ArrayList<>();
      this.nbCommentsSectorsList = new ArrayList<>();
      
      List<Pair<Comment, String>> tempCommentsSectorList;
      for (final Sector sectorL : this.sectorsList) {
        this.routesList.addAll(this.managerFactory.getRouteManager().getRoutesSectorList(sectorL.getId()));
        tempCommentsSectorList = this.managerFactory.getCommentManager().getCommentsSectorListWithLogin(sectorL.getId(), "ASC");
        this.nbCommentsSectorsList.add(new MutablePair<Integer, Integer>(sectorL.getId(), tempCommentsSectorList.size()));
        this.commentsSectorList.addAll(tempCommentsSectorList);
      }
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    } catch (NotFoundException exception) {
      return Action.SUCCESS;
    }
    
    return this.hasErrors() ? Action.ERROR : Action.SUCCESS;
    
  }
  
  /**
   * Prepare the route details with comments.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doRouteDetails() {
    
    if (this.id == null) {
      LOG.error("route id null - route details failure");
      this.addActionError("L'identifiant de la voie recherché est incorrect (Identifiant vide) - Echec de l'affichage des détails");
      return Action.ERROR;
    }
    else {
      try {
        this.route = this.managerFactory.getRouteManager().getRouteById(this.id);
        // Sector and site are needed to have full details on the route.
        this.sector = this.managerFactory.getSectorManager().getSectorById(this.route.getSectorId());
        this.site = this.managerFactory.getSiteManager().getSiteById(this.sector.getSiteId());
      } catch (TechnicalException | NotFoundException | FunctionalException exception) {
        this.addActionError(exception.getMessage());
        LOG.error(exception.getMessage());
        return Action.ERROR;
      }
    }
    
    /*
     * Get length list
     */
    try {
      this.lengthsList = this.managerFactory.getLengthManager().getLengthsRouteList(this.route.getId());
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    } catch (NotFoundException exception) {
      this.lengthsList = new ArrayList<>();
    }
    
    /*
     * Calculate number of points for the route
     */
    this.nbPoints = this.sumNbPoints(this.lengthsList);
    
    /*
     * Get comments list for the route
     */
    try {
      this.commentsRouteList = this.managerFactory.getCommentManager().getCommentsRouteListWithLogin(this.id, "ASC");
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    } catch (NotFoundException exception) {
      this.addActionMessage("Aucun commentaire");
      this.commentsRouteList = new ArrayList<>();
    }
    
    this.nbCommentsRoute = this.commentsRouteList.size();
    
    return this.hasErrors() ? Action.ERROR : Action.SUCCESS;
  }
  
  /**
   * Calculate the number of points for the route.
   *
   * @param lengthsList the list of length of the route
   *
   * @return the number of points for the route.
   */
  private int sumNbPoints(final List<Length> lengthsList) {
    int nbPoins = 0;
    
    for (final Length length : lengthsList) {
      nbPoins += length.getPointsNb();
    }
    return nbPoins;
  }
}
