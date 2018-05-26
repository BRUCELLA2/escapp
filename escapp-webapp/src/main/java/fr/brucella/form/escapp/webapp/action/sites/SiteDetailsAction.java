package fr.brucella.form.escapp.webapp.action.sites;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public class SiteDetailsAction extends ActionSupport {
    
    private static final long            serialVersionUID = -8899315209160242753L;
    
    // ----- Input
    private Integer                      id;
    
    // ----- Output
    private Site                         site;
    private Sector                       sector;
    private List<Sector>                 sectorsList;
    private Route                        route;
    private List<Route>                  routesList;
    private List<Length>                 lengthsList;
    private int                          nbPoints;
    
    private Integer                      nbCommentsSite;
    private List<Pair<Integer, Integer>> nbCommentsSectorsList;
    private Integer                      nbCommentsRoute;
    private List<Pair<Comment, String>>  commentsSiteList;
    private List<Pair<Comment, String>>  commentsSectorList;
    private List<Pair<Comment, String>>  commentsRouteList;
    
    // ----- Manager
    @Autowired
    private ManagerFactory               managerFactory;
    
    
    // ===== Getters =====
    public Integer getId() {
        return this.id;
    }
    
    public Site getSite() {
        return this.site;
    }
    
    public Sector getSector() {
        return this.sector;
    }
    
    public List<Sector> getSectorsList() {
        return this.sectorsList;
    }
    
    public Route getRoute() {
        return this.route;
    }
    
    public List<Route> getRoutesList() {
        return this.routesList;
    }
    
    public List<Length> getLengthsList() {
        return this.lengthsList;
    }
    
    public int getNbPoints() {
        return this.nbPoints;
    }
    
    public Integer getNbCommentsSite() {
        return this.nbCommentsSite;
    }
    
    public Integer getNbCommentsRoute() {
        return this.nbCommentsRoute;
    }
    
    public List<Pair<Integer, Integer>> getNbCommentsSectorsList() {
        return this.nbCommentsSectorsList;
    }
    
    public List<Pair<Comment, String>> getCommentsSiteList() {
        return this.commentsSiteList;
    }
    
    public List<Pair<Comment, String>> getCommentsSectorList() {
        return this.commentsSectorList;
    }
    
    public List<Pair<Comment, String>> getCommentsRouteList() {
        return this.commentsRouteList;
    }
    
    
    // ===== Setters =====
    
    public void setId(Integer pId) {
        this.id = pId;
    }
    
    
    
    // ===== Methods =====
    
    /**
     * Get Site details with comments
     * 
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doSiteDetails() {
        
        if (this.id == null) {
            this.addActionError("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
            return Action.ERROR;
        }
        
        try {
            this.site = this.managerFactory.getSiteManager().getSiteById(this.id);
        } catch (TechnicalException | NotFoundException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        
        /*
         * Get comment for the site
         */
        try {
            this.commentsSiteList = this.managerFactory.getCommentManager().getCommentsSiteListWithLogin(this.id, "ASC");
            this.nbCommentsSite = this.commentsSiteList.size();
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        } catch (NotFoundException pException) {
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
            for (Sector sectorL : this.sectorsList) {
                this.routesList.addAll(this.managerFactory.getRouteManager().getRoutesSectorList(sectorL.getId()));
                List<Pair<Comment, String>> tempCommentsSectorList =
                        this.managerFactory.getCommentManager().getCommentsSectorListWithLogin(sectorL.getId(), "ASC");
                this.nbCommentsSectorsList.add(new MutablePair<Integer, Integer>(sectorL.getId(), tempCommentsSectorList.size()));
                this.commentsSectorList.addAll(tempCommentsSectorList);
            }
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        } catch (NotFoundException pException) {
            return Action.SUCCESS;
        }
        
        return (this.hasErrors()) ? Action.ERROR : Action.SUCCESS;
        
    }
    
    /**
     * Get route details with comments
     * 
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doRouteDetails() {
        
        if (this.id == null) {
            this.addActionError("L'identifiant de la voie recherché est incorrect (Identifiant vide) - Echec de l'affichage des détails");
            return Action.ERROR;
        }
        else {
            try {
                this.route = this.managerFactory.getRouteManager().getRouteById(this.id);
                // Sector and site are needed to have full details on the route.
                this.sector = this.managerFactory.getSectorManager().getSectorById(this.route.getSectorId());
                this.site = this.managerFactory.getSiteManager().getSiteById(this.sector.getSiteId());
            } catch (TechnicalException | NotFoundException | FunctionalException pException) {
                this.addActionError(pException.getMessage());
                return Action.ERROR;
            }
        }
        
        /*
         * Get length list
         */
        try {
            this.lengthsList = this.managerFactory.getLengthManager().getLengthsRouteList(this.route.getId());
        } catch (TechnicalException | FunctionalException | NotFoundException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        /*
         * Calculate number of points for the route
         */
        this.nbPoints = this.nbPoints(this.lengthsList);
        
        /*
         * Get comments list for the route
         */
        try {
            this.commentsRouteList = this.managerFactory.getCommentManager().getCommentsRouteListWithLogin(this.id, "ASC");
        } catch (TechnicalException | NotFoundException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        this.nbCommentsRoute = this.commentsRouteList.size();
        
        return (this.hasErrors()) ? Action.ERROR : Action.SUCCESS;
    }
    
    /**
     * Calculate the number of points for the route.
     * 
     * @param pLengthsList the list of length of the route
     * 
     * @return the number of points for the route.
     */
    private int nbPoints(List<Length> pLengthsList) {
        int nbPoins = 0;
        
        for (Length length : pLengthsList) {
            nbPoins += length.getPointsNb();
        }
        
        return nbPoins;
    }
}
