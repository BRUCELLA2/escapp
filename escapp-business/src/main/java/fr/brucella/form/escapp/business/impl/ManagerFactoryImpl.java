package fr.brucella.form.escapp.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.business.contract.managers.comment.CommentManager;
import fr.brucella.form.escapp.business.contract.managers.site.LengthManager;
import fr.brucella.form.escapp.business.contract.managers.site.RouteManager;
import fr.brucella.form.escapp.business.contract.managers.site.SectorManager;
import fr.brucella.form.escapp.business.contract.managers.site.SiteManager;
import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.contract.managers.user.UserManager;

/**
 * Manager Factory allow to get and set business managers.
 *
 * @author BRUCELLA2
 */
@Component
public class ManagerFactoryImpl implements ManagerFactory {
  
  /**
   * Comment Manager.
   *
   * @see CommentManager
   */
  @Autowired
  private CommentManager commentManager;
  
  /**
   * Length Manager.
   *
   * @see LengthManager
   */
  @Autowired
  private LengthManager  lengthManager;
  
  /**
   * Route Manager.
   *
   * @see RouteManager
   */
  @Autowired
  private RouteManager   routeManager;
  
  /**
   * Sector Manager.
   *
   * @see SectorManager
   */
  @Autowired
  private SectorManager  sectorManager;
  
  /**
   * Site Manager.
   *
   * @see SiteManager
   */
  @Autowired
  private SiteManager    siteManager;
  
  /**
   * Topo Manager.
   *
   * @see TopoManager
   */
  @Autowired
  private TopoManager    topoManager;
  
  /**
   * User Manager.
   *
   * @see UserManager
   */
  @Autowired
  private UserManager    userManager;
  
  
  // ===== Getters =====
  
  /**
   * @see ManagerFactory#getCommentManager()
   */
  @Override
  public CommentManager getCommentManager() {
    return this.commentManager;
  }
  
  /**
   * @see ManagerFactory#getLengthManager()
   */
  @Override
  public LengthManager getLengthManager() {
    return this.lengthManager;
  }
  
  /**
   * @see ManagerFactory#getRouteManager()
   */
  @Override
  public RouteManager getRouteManager() {
    return this.routeManager;
  }
  
  /**
   * @see ManagerFactory#getSectorManager()
   */
  @Override
  public SectorManager getSectorManager() {
    return this.sectorManager;
  }
  
  /**
   * @see ManagerFactory#getSiteManager()
   */
  @Override
  public SiteManager getSiteManager() {
    return this.siteManager;
  }
  
  /**
   * @see ManagerFactory#getTopoManager()
   */
  @Override
  public TopoManager getTopoManager() {
    return this.topoManager;
  }
  
  /**
   * @see ManagerFactory#getUserManager()
   */
  @Override
  public UserManager getUserManager() {
    return this.userManager;
  }
  
  
  // ===== Setters =====
  
  /**
   * @see ManagerFactory#setCommentManager(CommentManager)
   */
  @Override
  public void setCommentManager(final CommentManager commentManager) {
    this.commentManager = commentManager;
  }
  
  /**
   * @see ManagerFactory#setLengthManager(LengthManager)
   */
  @Override
  public void setLengthManager(final LengthManager lengthManager) {
    this.lengthManager = lengthManager;
  }
  
  /**
   * @see ManagerFactory#setRouteManager(RouteManager)
   */
  @Override
  public void setRouteManager(final RouteManager routeManager) {
    this.routeManager = routeManager;
  }
  
  /**
   * @see ManagerFactory#setSectorManager(SectorManager)
   */
  @Override
  public void setSectorManager(final SectorManager sectorManager) {
    this.sectorManager = sectorManager;
  }
  
  /**
   * @see ManagerFactory#setSiteManager(SiteManager)
   */
  @Override
  public void setSiteManager(final SiteManager siteManager) {
    this.siteManager = siteManager;
  }
  
  /**
   * @see ManagerFactory#setTopoManager(TopoManager)
   */
  @Override
  public void setTopoManager(final TopoManager topoManager) {
    this.topoManager = topoManager;
  }
  
  /**
   * @see ManagerFactory#setUserManager(UserManager)
   */
  @Override
  public void setUserManager(final UserManager userManager) {
    this.userManager = userManager;
  }
  
}
