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
   * Get the Comment Manager.
   *
   * @return the Comment Manager
   */
  CommentManager getCommentManager();
  
  /**
   * Get the Length Manager.
   *
   * @return the Length Manager
   */
  LengthManager getLengthManager();
  
  /**
   * Get the Route Manager.
   *
   * @return the Route Manager
   */
  RouteManager getRouteManager();
  
  /**
   * Get the Sector Manager.
   *
   * @return the Sector Manager
   */
  SectorManager getSectorManager();
  
  /**
   * Get the Site Manager.
   *
   * @return the Site Manager
   */
  SiteManager getSiteManager();
  
  /**
   * Get the Topo Manager.
   *
   * @return the Topo Manager
   */
  TopoManager getTopoManager();
  
  /**
   * Get the User Manager.
   *
   * @return the User Manager
   */
  UserManager getUserManager();
  
  // ===== Setters =====
  
  /**
   * Set the Comment Manager.
   *
   * @param commentManager the Comment Manager
   */
  void setCommentManager(CommentManager commentManager);
  
  /**
   * Set the Length Manager.
   *
   * @param lengthManager the Length Manager
   */
  void setLengthManager(LengthManager lengthManager);
  
  /**
   * Set the Route Manager.
   *
   * @param routeManager the Route Manager
   */
  void setRouteManager(RouteManager routeManager);
  
  /**
   * Set the Sector Manager.
   *
   * @param sectorManager the Sector Manager
   */
  void setSectorManager(SectorManager sectorManager);
  
  /**
   * Set the Site Manager.
   *
   * @param siteManager the Site Manager
   */
  void setSiteManager(SiteManager siteManager);
  
  /**
   * Set the Topo Manager.
   *
   * @param topoManager the Topo Manager
   */
  void setTopoManager(TopoManager topoManager);
  
  /**
   * Set the User Manager.
   *
   * @param userManager the Topo Manager
   */
  void setUserManager(UserManager userManager);
}
