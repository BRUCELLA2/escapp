package fr.brucella.form.escapp.business.contract;

import fr.brucella.form.escapp.business.contract.managers.comment.CommentManager;
import fr.brucella.form.escapp.business.contract.managers.site.LengthManager;
import fr.brucella.form.escapp.business.contract.managers.site.RouteManager;
import fr.brucella.form.escapp.business.contract.managers.site.SectorManager;
import fr.brucella.form.escapp.business.contract.managers.site.SiteManager;
import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.contract.managers.user.UserManager;

/**
 * Interface for Manager Factory. Manager Factory allow to get and set business managers.
 *
 * @author BRUCELLA2
 */
public interface ManagerFactory {
    
    // ===== Getters =====
    
    /**
     * Get the Comment Manager
     * 
     * @return the Comment Manager
     */
    CommentManager getCommentManager();
    
    /**
     * Get the Length Manager
     * 
     * @return the Length Manager
     */
    LengthManager getLengthManager();
    
    /**
     * Get the Route Manager
     * 
     * @return the Route Manager
     */
    RouteManager getRouteManager();
    
    /**
     * Get the Sector Manager
     * 
     * @return the Sector Manager
     */
    SectorManager getSectorManager();
    
    /**
     * Get the Site Manager
     * 
     * @return the Site Manager
     */
    SiteManager getSiteManager();
    
    /**
     * Get the Topo Manager
     * 
     * @return the Topo Manager
     */
    TopoManager getTopoManager();
    
    /**
     * Get the User Manager
     * 
     * @return the User Manager
     */
    UserManager getUserManager();
    
    // ===== Setters =====
    
    /**
     * Set the Comment Manager
     * 
     * @param pCommentManager the Comment Manager
     */
    void setCommentManager(CommentManager pCommentManager);
    
    /**
     * Set the Length Manager
     * 
     * @param pLengthManager the Length Manager
     */
    void setLengthManager(LengthManager pLengthManager);
    
    /**
     * Set the Route Manager
     * 
     * @param pRouteManager the Route Manager
     */
    void setRouteManager(RouteManager pRouteManager);
    
    /**
     * Set the Sector Manager
     * 
     * @param pSectorManager the Sector Manager
     */
    void setSectorManager(SectorManager pSectorManager);
    
    /**
     * Set the Site Manager
     * 
     * @param pSiteManager the Site Manager
     */
    void setSiteManager(SiteManager pSiteManager);
    
    /**
     * Set the Topo Manager
     * 
     * @param pTopoManager the Topo Manager
     */
    void setTopoManager(TopoManager pTopoManager);
    
    /**
     * Set the User Manager
     * 
     * @param pUserManager the Topo Manager
     */
    void setUserManager(UserManager pUserManager);
}
