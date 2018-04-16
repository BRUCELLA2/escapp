package fr.brucella.form.escapp.business.contract;

import fr.brucella.form.escapp.business.contract.managers.comment.CommentManager;
import fr.brucella.form.escapp.business.contract.managers.site.SiteManager;
import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.contract.managers.user.UserManager;

public interface ManagerFactory {
	
	CommentManager getCommentManager();
	SiteManager getSiteManager();
	TopoManager getTopoManager();
	UserManager getUserManager();
	
	void setCommentManager(CommentManager pCommentManager);
	void setSiteManager(SiteManager pSiteManager);
	void setTopoManager(TopoManager pTopoManager);
	void setUserManager(UserManager pUserManager);
}
