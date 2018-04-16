package fr.brucella.form.escapp.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.business.contract.managers.comment.CommentManager;
import fr.brucella.form.escapp.business.contract.managers.site.SiteManager;
import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.contract.managers.user.UserManager;

@Component
public class ManagerFactoryImpl implements ManagerFactory{

	@Autowired
	private CommentManager vCommentManager;
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
