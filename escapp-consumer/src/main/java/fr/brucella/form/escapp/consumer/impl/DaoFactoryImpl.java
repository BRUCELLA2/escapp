package fr.brucella.form.escapp.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.consumer.contract.DaoFactory;
import fr.brucella.form.escapp.consumer.contract.dao.comment.CommentDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.RouteDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.contract.dao.user.RoleUserDao;
import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;

/**
 * The Data Access Object Factory.
 *
 * @author BRUCELLA2
 *
 */
@Component
public class DaoFactoryImpl implements DaoFactory {
  
  
  
  /**
   * Comment Data Access Object.
   *
   * @see CommentDao
   */
  @Autowired
  private CommentDao  commentDao;
  
  /**
   * Site Data Access Object.
   *
   * @see SiteDao
   */
  @Autowired
  private SiteDao     siteDao;
  
  /**
   * Topo Data Access Object.
   *
   * @see TopoDao
   */
  @Autowired
  private TopoDao     topoDao;
  
  /**
   * User Data Access Object.
   *
   * @see UserDao
   */
  @Autowired
  private UserDao     userDao;
  
  /**
   * RoleUser Data Access Object.
   *
   * @see RoleUserDao
   */
  @Autowired
  private RoleUserDao roleUserDao;
  
  /**
   * Route Data Access Object.
   *
   * @see RouteDao
   */
  @Autowired
  private RouteDao    routeDao;
  
  /**
   * Sector Data Access Object.
   *
   * @see SectorDao
   */
  @Autowired
  private SectorDao   sectorDao;
  
  /**
   * Length Data Access Object.
   *
   * @see LengthDao
   */
  @Autowired
  private LengthDao   lengthDao;
  
  
  // ===== Getters =====
  
  /**
   * @see DaoFactory#getCommentDao()
   */
  @Override
  public CommentDao getCommentDao() {
    return this.commentDao;
  }
  
  /**
   * @see DaoFactory#getSiteDao()
   */
  @Override
  public SiteDao getSiteDao() {
    return this.siteDao;
  }
  
  /**
   * @see DaoFactory#getRouteDao()
   */
  @Override
  public RouteDao getRouteDao() {
    return this.routeDao;
  }
  
  /**
   * @see DaoFactory#getSectorDao()
   */
  @Override
  public SectorDao getSectorDao() {
    return this.sectorDao;
  }
  
  /**
   * @see DaoFactory#getLengthDao()
   */
  @Override
  public LengthDao getLengthDao() {
    return this.lengthDao;
  }
  
  /**
   * @see DaoFactory#getTopoDao()
   */
  @Override
  public TopoDao getTopoDao() {
    return this.topoDao;
  }
  
  /**
   * @see DaoFactory#getUserDao()
   */
  @Override
  public UserDao getUserDao() {
    return this.userDao;
  }
  
  /**
   * @see DaoFactory#getRoleUserDao()
   */
  @Override
  public RoleUserDao getRoleUserDao() {
    return this.roleUserDao;
  }
  
  // ===== Setters =====
  
  /**
   * @see DaoFactory#setCommentDao(CommentDao)
   */
  @Override
  public void setCommentDao(final CommentDao commentDao) {
    this.commentDao = commentDao;
  }
  
  /**
   * @see DaoFactory#setSiteDao(SiteDao)
   */
  @Override
  public void setSiteDao(final SiteDao siteDao) {
    this.siteDao = siteDao;
  }
  
  /**
   * @see DaoFactory#setRouteDao(RouteDao)
   */
  @Override
  public void setRouteDao(final RouteDao routeDao) {
    this.routeDao = routeDao;
  }
  
  /**
   * @see DaoFactory#setSectorDao(SectorDao)
   */
  @Override
  public void setSectorDao(final SectorDao sectorDao) {
    this.sectorDao = sectorDao;
  }
  
  /**
   * @see DaoFactory#setLengthDao(LengthDao)
   */
  @Override
  public void setLengthDao(final LengthDao lengthDao) {
    this.lengthDao = lengthDao;
  }
  
  /**
   * @see DaoFactory#setTopoDao(TopoDao)
   */
  @Override
  public void setTopoDao(final TopoDao topoDao) {
    this.topoDao = topoDao;
  }
  
  /**
   * @see DaoFactory#setUserDao(UserDao)
   */
  @Override
  public void setUserDao(final UserDao userDao) {
    this.userDao = userDao;
  }
  
  /**
   * @see DaoFactory#setRoleUserDao(RoleUserDao)
   */
  @Override
  public void setRoleUserDao(final RoleUserDao roleUserDao) {
    this.roleUserDao = roleUserDao;
  }
  
}
