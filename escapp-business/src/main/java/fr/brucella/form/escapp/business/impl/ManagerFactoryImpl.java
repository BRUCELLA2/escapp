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
public class ManagerFactoryImpl implements ManagerFactory{

	/**
	 * Comment Manager
	 * 
	 * @see CommentManager
	 */
	@Autowired
	private CommentManager vCommentManager;
	
	/**
	 * Length Manager
	 * 
	 * @see LengthManager
	 */
	@Autowired
	private LengthManager vLengthManager;
	
	/**
	 * Route Manager
	 * 
	 * @see RouteManager
	 */
	@Autowired
	private RouteManager vRouteManager;	
	
	/**
	 * Sector Manager
	 * 
	 * @see SectorManager
	 */
	@Autowired
	private SectorManager vSectorManager;
	
	/**
	 * Site Manager
	 * 
	 * @see SiteManager
	 */
	@Autowired
	private SiteManager vSiteManager;
	
	/**
	 * Topo Manager
	 * 
	 * @see TopoManager
	 */
	@Autowired
	private TopoManager vTopoManager;
	
	/**
	 * User Manager
	 * 
	 * @see UserManager
	 */
	@Autowired
	private UserManager vUserManager;
	
	
// ===== Getters =====
	
	/**
	 * @see ManagerFactory#getCommentManager()
	 */
	@Override
	public CommentManager getCommentManager() {
		return vCommentManager;
	}
	
	/**
	 * @see ManagerFactory#getLengthManager()
	 */
	@Override
	public LengthManager getLengthManager() {
		return vLengthManager;
	}
	
	/**
	 * @see ManagerFactory#getRouteManager()
	 */
	@Override
	public RouteManager getRouteManager() {
		return vRouteManager;
	}

	/**
	 * @see ManagerFactory#getSectorManager()
	 */
	@Override
	public SectorManager getSectorManager() {
		return vSectorManager;
	}
	
	/**
	 * @see ManagerFactory#getSiteManager()
	 */
	@Override
	public SiteManager getSiteManager() {
		return vSiteManager;
	}
	
	/**
	 * @see ManagerFactory#getTopoManager()
	 */
	@Override
	public TopoManager getTopoManager() {
		return vTopoManager;
	}

	/**
	 * @see ManagerFactory#getUserManager()
	 */
	@Override
	public UserManager getUserManager() {
		return vUserManager;
	}
	
	
// ===== Setters =====

	/**
	 * @see ManagerFactory#setCommentManager(CommentManager)
	 */
	@Override
	public void setCommentManager(CommentManager pCommentManager) {
		this.vCommentManager = pCommentManager;		
	}
	
	/**
	 * @see ManagerFactory#setLengthManager(LengthManager)
	 */
	@Override
	public void setLengthManager(LengthManager pLengthManager) {
		this.vLengthManager = pLengthManager;
	}
	
	/**
	 * @see ManagerFactory#setRouteManager(RouteManager)
	 */
	@Override
	public void setRouteManager(RouteManager pRouteManager) {
		this.vRouteManager = pRouteManager;
	}

	/**
	 * @see ManagerFactory#setSectorManager(SectorManager)
	 */
	@Override
	public void setSectorManager(SectorManager pSectorManager) {
		this.vSectorManager = pSectorManager;
	}
	
	/**
	 * @see ManagerFactory#setSiteManager(SiteManager)
	 */
	@Override
	public void setSiteManager(SiteManager pSiteManager) {
		this.vSiteManager = pSiteManager;
	}

	/**
	 * @see ManagerFactory#setTopoManager(TopoManager)
	 */
	@Override
	public void setTopoManager(TopoManager pTopoManager) {
		this.vTopoManager = pTopoManager;
	}

	/**
	 * @see ManagerFactory#setUserManager(UserManager)
	 */
	@Override
	public void setUserManager(UserManager pUserManager) {
		this.vUserManager = pUserManager;
	}

}
