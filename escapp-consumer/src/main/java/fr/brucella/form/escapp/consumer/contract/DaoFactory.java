package fr.brucella.form.escapp.consumer.contract;

import fr.brucella.form.escapp.consumer.contract.dao.comment.CommentDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.RouteDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;

/**
 * Interface for the Data Access Object Factory.
 * 
 * @author BRUCELLA2
 *
 */
public interface DaoFactory {

	/**
	 * Get the Comment Data Access Object
	 * 
	 * @return the Comment Data Access Object
	 */
    CommentDao getCommentDao();
    
    /**
     * Get the Site Data Access Object
     * 
     * @return the Site Data Access Object
     */
    SiteDao getSiteDao();
    
    /**
     * Get the Route Data Access Object
     * 
     * @return the Route Data Access Object
     */
    RouteDao getRouteDao();
    
    /**
     * Get the Sector Data Access Object
     * 
     * @return the Sector Data Access Object
     */
    SectorDao getSectorDao();
    
    /**
     * Get the Length Data Access Object
     * 
     * @return the Length Data Access Object
     */
    LengthDao getLengthDao();
    
    /**
     * Get the Topo Data Access Object
     * 
     * @return the Topo Data Access Object
     */
    TopoDao getTopoDao();
    
    /**
     * Get the User Data Acces Object
     * 
     * @return the User Data Acces Object
     */
    UserDao getUserDao();

    
    
    /**
     * Set the Comment Data Access Object
     * 
     * @param pCommentDao the Comment Data Access Object
     */
    void setCommentDao(CommentDao pCommentDao);
    
    /**
     * Set the Site Data Access Object
     * 
     * @param pSiteDao the Site Data Access Object
     */
    void setSiteDao(SiteDao pSiteDao);
    
    /**
     * Set the Route Data Access Object
     * 
     * @param pRouteDao the Route Data Access Object
     */
    void setRouteDao(RouteDao pRouteDao);
    
    /**
     * Set the Sector Data Access Object
     * 
     * @param pSectorDao the Sector Data Access Object
     */
    void setSectorDao(SectorDao pSectorDao);
    
    /**
     * Set the Length Data Access Object
     * 
     * @param pLengthDao the Length Data Access Object
     */
    void setLengthDao(LengthDao pLengthDao);
    
    /**
     * Set the Topo Data Access Object
     * 
     * @param pTopoDao the Topo Data Access Object
     */
    void setTopoDao(TopoDao pTopoDao);
    
    /**
     * Set the User Data Acces Object
     * 
     * @param pUserDao the User Data Acces Object
     */
    void setUserDao(UserDao pUserDao);
}
