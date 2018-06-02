package fr.brucella.form.escapp.webapp.action.comments;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Actions related to comments.
 *
 * @author BRUCELLA2
 */
public class CommentsAction extends ActionSupport implements ServletRequestAware {
  
  /**
   * Serial ID.
   */
  private static final long  serialVersionUID = -617440123390057718L;
  
  // ----- Logger
  /**
   * Comments action logger.
   */
  private static final Log   LOG              = LogFactory.getLog(CommentsAction.class);
  
  // ----- Input
  /**
   * Id of the {@link Site}.
   *
   * @see #getSiteId()
   * @see #setSiteId(Integer)
   */
  private Integer            siteId;
  
  /**
   * Id of the {@link Sector}.
   *
   * @see #getSectorId()
   * @see #setSectorId(Integer)
   */
  private Integer            sectorId;
  
  /**
   * Id of the {@link Route}.
   *
   * @see #getRouteId()
   * @see #setRouteId(Integer)
   */
  private Integer            routeId;
  
  /**
   * Id of the {@link Topo}.
   *
   * @see #getTopoId()
   * @see #setTopoId(Integer)
   */
  private Integer            topoId;
  
  /**
   * {@link String} comment for the {@link Site}.
   *
   * @see #getCommentSite()
   * @see #setCommentSite(String)
   */
  private String             commentSite;
  
  /**
   * {@link String} comment for the {@link Sector}.
   *
   * @see #getCommentSector()
   * @see #setCommentSector(String)
   */
  private String             commentSector;
  
  /**
   * {@link String} comment for the {@link Route}.
   *
   * @see #getCommentRoute()
   * @see #setCommentRoute(String)
   */
  private String             commentRoute;
  
  /**
   * {@link String} comment for the {@link Topo}.
   *
   * @see #getCommentTopo()
   * @see #setCommentTopo(String)
   */
  private String             commentTopo;
  
  
  // ----- Output
  
  
  // ----- Manager
  
  /**
   * The Manager Factory Manager Factory allow to get and set business managers.
   */
  @Autowired
  private ManagerFactory     managerFactory;
  
  
  // ----- Struts Elements
  /**
   * The Http Servlet Request. Used to get session informations.
   *
   * @see HttpServletRequest
   * @see #setServletRequest(HttpServletRequest)
   */
  private HttpServletRequest servletRequest;
  
  
  // ===== Getters =====
  
  /**
   * Get the id of the {@link Site}.
   *
   * @return the id of the {@link Site}
   *
   * @see #siteId
   * @see #setSiteId(Integer)
   */
  public Integer getSiteId() {
    return this.siteId;
  }
  
  /**
   * Get the id of the {@link Sector}.
   *
   * @return the id of the {@link Sector}
   *
   * @see #sectorId
   * @see #setSectorId(Integer)
   */
  public Integer getSectorId() {
    return this.sectorId;
  }
  
  /**
   * Get the id of the {@link Route}.
   *
   * @return the id of the {@link Route}
   *
   * @see #routeId
   * @see #setRouteId(Integer)
   */
  public Integer getRouteId() {
    return this.routeId;
  }
  
  /**
   * Get the id of the {@link Topo}.
   *
   * @return the id of the {@link Topo}
   *
   * @see #topoId
   * @see #setTopoId(Integer)
   */
  public Integer getTopoId() {
    return this.topoId;
  }
  
  /**
   * Get the {@link String} comment for the {@link Site}.
   *
   * @return the {@link String} comment for the {@link Site}
   *
   * @see #commentSite
   * @see #setCommentSite(String)
   */
  public String getCommentSite() {
    return this.commentSite;
  }
  
  /**
   * Get the {@link String} comment for the {@link Sector}.
   *
   * @return the {@link String} comment for the {@link Sector}
   *
   * @see #commentSector
   * @see #setCommentSector(String)
   */
  public String getCommentSector() {
    return this.commentSector;
  }
  
  /**
   * Get the {@link String} comment for the {@link Route}.
   *
   * @return the {@link String} comment for the {@link Route}
   *
   * @see #commentRoute
   * @see #setCommentRoute(String)
   */
  public String getCommentRoute() {
    return this.commentRoute;
  }
  
  /**
   * Get the {@link String} comment for the {@link Topo}.
   *
   * @return the {@link String} comment for the {@link Topo}
   *
   * @see #commentTopo
   * @see #setCommentTopo(String)
   */
  public String getCommentTopo() {
    return this.commentTopo;
  }
  
  
  // ===== Setters =====
  
  /**
   * Set the id of the {@link Site}.
   *
   * @param siteId the id of the {@link Site}
   *
   * @see #siteId
   * @see #getSiteId()
   */
  public void setSiteId(final Integer siteId) {
    this.siteId = siteId;
  }
  
  /**
   * Set the id of the {@link Sector}.
   *
   * @param sectorId the id of the {@link Sector}
   *
   * @see #sectorId
   * @see #getSectorId()
   */
  public void setSectorId(final Integer sectorId) {
    this.sectorId = sectorId;
  }
  
  /**
   * Set the id of the {@link Route}.
   *
   * @param routeId the id of the {@link Route}
   *
   * @see #routeId
   * @see #getRouteId()
   */
  public void setRouteId(final Integer routeId) {
    this.routeId = routeId;
  }
  
  /**
   * Set the id of the {@link Topo}.
   *
   * @param topoId the id of the {@link Topo}
   *
   * @see #topoId
   * @see #getTopoId()
   */
  public void setTopoId(final Integer topoId) {
    this.topoId = topoId;
  }
  
  /**
   * Set the {@link String} comment for the {@link Site}.
   *
   * @param commentSite the {@link String} comment for the {@link Site}
   *
   * @see #commentSite
   * @see #getCommentSite()
   */
  public void setCommentSite(final String commentSite) {
    this.commentSite = commentSite;
  }
  
  /**
   * Set the {@link String} comment for the {@link Sector}.
   *
   * @param commentSector the {@link String} comment for the {@link Sector}
   *
   * @see #commentSector
   * @see #getCommentSector()
   */
  public void setCommentSector(final String commentSector) {
    this.commentSector = commentSector;
  }
  
  /**
   * Set the {@link String} comment for the {@link Route}.
   *
   * @param commentRoute the {@link String} comment for the {@link Route}
   *
   * @see #commentRoute
   * @see #getCommentRoute()
   */
  public void setCommentRoute(final String commentRoute) {
    this.commentRoute = commentRoute;
  }
  
  /**
   * Set the {@link String} comment for the {@link Topo}.
   *
   * @param commentTopo the {@link String} comment for the {@link Topo}
   *
   * @see #commentTopo
   * @see #getCommentTopo()
   */
  public void setCommentTopo(final String commentTopo) {
    this.commentTopo = commentTopo;
  }
  
  /**
   * Set the Http Servlet Request. Used to get session informations.
   *
   * @see #servletRequest
   */
  @Override
  public void setServletRequest(final HttpServletRequest servletRequest) {
    this.servletRequest = servletRequest;
  }
  
  
  
  // ===== Methods =====
  
  /**
   * Add a comment to a site.
   *
   * @return ERROR if error occurred INPUT if the comment of the site or the site id are null SUCCESS
   *         otherwise
   */
  public String doAddCommentSite() {
    
    if (StringUtils.isEmpty(this.commentSite) || this.siteId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("Comment site and site id = null");
      }
      return Action.INPUT;
    }
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    
    if (user == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("User not connected - add comment site failure");
      }
      this.addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
      return Action.ERROR;
    }
    
    final Comment comment = new Comment();
    comment.setEscappUser(user.getId());
    comment.setIdCommentTarget(this.siteId);
    comment.setText(this.commentSite);
    
    try {
      this.managerFactory.getCommentManager().addCommentSite(comment);
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Add a comment to a sector.
   *
   * @return ERROR if error occurred INPUT if the comment of the sector or the sector id are null
   *         SUCCESS otherwise
   */
  public String doAddCommentSector() {
    
    if (StringUtils.isEmpty(this.commentSector) || this.sectorId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("Comment sector and sector id = null");
      }
      return Action.INPUT;
    }
    
    Sector sector;
    
    try {
      sector = this.managerFactory.getSectorManager().getSectorById(this.sectorId);
      // siteId is need for the redirection to the site details
      this.siteId = sector.getSiteId();
    } catch (TechnicalException | FunctionalException | NotFoundException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    
    if (user == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("User not connected - add comment sector failure");
      }
      this.addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
      return Action.ERROR;
    }
    
    final Comment comment = new Comment();
    comment.setEscappUser(user.getId());
    comment.setIdCommentTarget(this.sectorId);
    comment.setText(this.commentSector);
    
    try {
      this.managerFactory.getCommentManager().addCommentSector(comment);
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Add a comment to a route.
   *
   * @return ERROR if error occurred INPUT if the comment of the route or the route id are null
   *         SUCCESS otherwise
   */
  public String doAddCommentRoute() {
    
    if (StringUtils.isEmpty(this.commentRoute) || this.routeId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("Comment route and route id = null");
      }
      return Action.INPUT;
    }
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    
    if (user == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("User not connected - add comment route failure");
      }
      this.addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
      return Action.ERROR;
    }
    
    
    final Comment comment = new Comment();
    comment.setEscappUser(user.getId());
    comment.setIdCommentTarget(this.routeId);
    comment.setText(this.commentRoute);
    
    try {
      this.managerFactory.getCommentManager().addCommentRoute(comment);
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Add a comment to a topo.
   *
   * @return ERROR if error occurred INPUT if the comment of the topo or the topo id are null SUCCESS
   *         otherwise
   */
  public String doAddCommentTopo() {
    
    if (StringUtils.isEmpty(this.commentTopo) || this.topoId == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("Comment topo and topo id = null");
      }
      return Action.INPUT;
    }
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    
    if (user == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("User not connected - add comment topo failure");
      }
      this.addActionError("Vous n'êtes pas identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
      return Action.ERROR;
    }
    
    final Comment comment = new Comment();
    comment.setEscappUser(user.getId());
    comment.setIdCommentTarget(this.topoId);
    comment.setText(this.commentTopo);
    
    try {
      this.managerFactory.getCommentManager().addCommentTopo(comment);
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
}

