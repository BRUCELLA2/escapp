package fr.brucella.form.escapp.business.contract;

import fr.brucella.form.escapp.business.contract.managers.comment.CommentManager;
import fr.brucella.form.escapp.business.contract.managers.site.LengthManager;
import fr.brucella.form.escapp.business.contract.managers.site.RouteManager;
import fr.brucella.form.escapp.business.contract.managers.site.SectorManager;
import fr.brucella.form.escapp.business.contract.managers.site.SiteManager;
import fr.brucella.form.escapp.business.contract.managers.topo.TopoManager;
import fr.brucella.form.escapp.business.contract.managers.user.UserManager;

public interface ManagerFactory {
	
	CommentManager getCommentManager();
	LengthManager getLengthManager();
	RouteManager getRouteManager();
	SectorManager getSectorManager();
	SiteManager getSiteManager();
	TopoManager getTopoManager();
	UserManager getUserManager();
	
	void setCommentManager(CommentManager pCommentManager);
	void setLengthManager(LengthManager pLengthManager);
	void setRouteManager(RouteManager pRouteManager);
	void setSectorManager(SectorManager pSectorManager);
	void setSiteManager(SiteManager pSiteManager);
	void setTopoManager(TopoManager pTopoManager);
	void setUserManager(UserManager pUserManager);
}
