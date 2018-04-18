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

@Component
public class ManagerFactoryImpl implements ManagerFactory{

	@Autowired
	private CommentManager vCommentManager;
	@Autowired
	private LengthManager vLengthManager;
	@Autowired
	private RouteManager vRouteManager;	
	@Autowired
	private SectorManager vSectorManager;
	@Autowired
	private SiteManager vSiteManager;
	@Autowired
	private TopoManager vTopoManager;
	@Autowired
	private UserManager vUserManager;
	
    // ===== Getters =====
	
	@Override
	public CommentManager getCommentManager() {
		return vCommentManager;
	}
	
	@Override
	public LengthManager getLengthManager() {
		return vLengthManager;
	}
	
	@Override
	public RouteManager getRouteManager() {
		return vRouteManager;
	}

	@Override
	public SectorManager getSectorManager() {
		return vSectorManager;
	}
	
	@Override
	public SiteManager getSiteManager() {
		return vSiteManager;
	}

	@Override
	public TopoManager getTopoManager() {
		return vTopoManager;
	}

	@Override
	public UserManager getUserManager() {
		return vUserManager;
	}
	
	
    // ===== Setters =====

	@Override
	public void setCommentManager(CommentManager pCommentManager) {
		this.vCommentManager = pCommentManager;		
	}
	
	@Override
	public void setLengthManager(LengthManager pLengthManager) {
		this.vLengthManager = pLengthManager;
	}
	
	@Override
	public void setRouteManager(RouteManager pRouteManager) {
		this.vRouteManager = pRouteManager;
	}

	@Override
	public void setSectorManager(SectorManager pSectorManager) {
		this.vSectorManager = pSectorManager;
	}
	
	@Override
	public void setSiteManager(SiteManager pSiteManager) {
		this.vSiteManager = pSiteManager;
	}

	@Override
	public void setTopoManager(TopoManager pTopoManager) {
		this.vTopoManager = pTopoManager;
	}

	@Override
	public void setUserManager(UserManager pUserManager) {
		this.vUserManager = pUserManager;
	}

}
