package fr.brucella.form.escapp.consumer.contract;

import fr.brucella.form.escapp.consumer.contract.dao.comment.CommentDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.RouteDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.contract.dao.user.RoleUserDao;
import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;

/**
 * Interface for the Data Access Object Factory.
 *
 * @author BRUCELLA2
 *
 */
public interface DaoFactory {
  
  // ===== Getters =====
  
  /**
   * Get the Comment Data Access Object.
   *
   * @return the Comment Data Access Object
   */
  CommentDao getCommentDao();
  
  /**
   * Get the Site Data Access Object.
   *
   * @return the Site Data Access Object
   */
  SiteDao getSiteDao();
  
  /**
   * Get the Route Data Access Object.
   *
   * @return the Route Data Access Object
   */
  RouteDao getRouteDao();
  
  /**
   * Get the Sector Data Access Object.
   *
   * @return the Sector Data Access Object
   */
  SectorDao getSectorDao();
  
  /**
   * Get the Length Data Access Object.
   *
   * @return the Length Data Access Object
   */
  LengthDao getLengthDao();
  
  /**
   * Get the Topo Data Access Object.
   *
   * @return the Topo Data Access Object
   */
  TopoDao getTopoDao();
  
  /**
   * Get the User Data Access Object.
   *
   * @return the User Data Access Object
   */
  UserDao getUserDao();
  
  /**
   * Get the RoleUser Data Access Object.
   *
   * @return the RoleUser Data Access Object
   */
  RoleUserDao getRoleUserDao();
  
  
  // ===== Setters =====
  
  /**
   * Set the Comment Data Access Object.
   *
   * @param commentDao the Comment Data Access Object
   */
  void setCommentDao(final CommentDao commentDao);
  
  /**
   * Set the Site Data Access Object.
   *
   * @param siteDao the Site Data Access Object
   */
  void setSiteDao(final SiteDao siteDao);
  
  /**
   * Set the Route Data Access Object.
   *
   * @param routeDao the Route Data Access Object
   */
  void setRouteDao(final RouteDao routeDao);
  
  /**
   * Set the Sector Data Access Object.
   *
   * @param sectorDao the Sector Data Access Object
   */
  void setSectorDao(final SectorDao sectorDao);
  
  /**
   * Set the Length Data Access Object.
   *
   * @param lengthDao the Length Data Access Object
   */
  void setLengthDao(final LengthDao lengthDao);
  
  /**
   * Set the Topo Data Access Object.
   *
   * @param topoDao the Topo Data Access Object
   */
  void setTopoDao(final TopoDao topoDao);
  
  /**
   * Set the User Data Access Object.
   *
   * @param userDao the User Data Access Object
   */
  void setUserDao(final UserDao userDao);
  
  /**
   * Set the RoleUser Data Access Object.
   *
   * @param roleUserDao the RoleUser Data Access Object
   */
  void setRoleUserDao(final RoleUserDao roleUserDao);
}
