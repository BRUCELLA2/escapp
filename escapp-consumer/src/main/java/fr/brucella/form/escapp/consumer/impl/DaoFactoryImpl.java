package fr.brucella.form.escapp.consumer.impl;

import fr.brucella.form.escapp.consumer.contract.DaoFactory;
import fr.brucella.form.escapp.consumer.contract.dao.comment.CommentDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.RouteDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Data Access Object Factory.
 * 
 * @author BRUCELLA2
 *
 */
@Component
public class DaoFactoryImpl implements DaoFactory {


	/**
	 * Comment Data Access Object
	 * 
	 * @see CommentDao
	 */
    @Autowired
    private CommentDao vCommentDao;
    
    /**
     * Site Data Access Object
     * 
     * @see SiteDao
     */
    @Autowired
    private SiteDao vSiteDao;
    
    /**
     * Topo Data Access Object
     * 
     * @see TopoDao
     */
    @Autowired
    private TopoDao vTopoDao;
    
    /**
     * User Data Access Object
     * 
     * @see UserDao
     */
    @Autowired
    private UserDao vUserDao;
    
    /**
     * Route Data Access Object
     * 
     * @see RouteDao
     */
    @Autowired
    private RouteDao vRouteDao;
    
    /**
     * Sector Data Access Object
     * 
     * @see SectorDao
     */
    @Autowired
    private SectorDao vSectorDao;
    
    /**
     * Length Data Access Object
     * 
     * @see LengthDao
     */
    @Autowired
    private LengthDao vLengthDao;


    // ===== Getters =====

    /**
     * @see DaoFactory#getCommentDao()
     */
    @Override
    public CommentDao getCommentDao() {
        return vCommentDao;
    }

    /**
     * @see DaoFactory#getSiteDao()
     */
    @Override
    public SiteDao getSiteDao() {
        System.out.println("test getSiteDao");
        return vSiteDao;
    }

    /**
     * @see DaoFactory#getRouteDao()
     */
    @Override
    public RouteDao getRouteDao() {
        return vRouteDao;
    }

    /**
     * @see DaoFactory#getSectorDao()
     */
    @Override
    public SectorDao getSectorDao() {
        return vSectorDao;
    }

    /**
     * @see DaoFactory#getLengthDao()
     */
    @Override
    public LengthDao getLengthDao() {
        return vLengthDao;
    }

    /**
     * @see DaoFactory#getTopoDao()
     */
    @Override
    public TopoDao getTopoDao() {
        return vTopoDao;
    }

    /**
     * @see DaoFactory#getUserDao()
     */
    @Override
    public UserDao getUserDao() {
        return vUserDao;
    }


    // ===== Setters =====

    /**
     * @see DaoFactory#setCommentDao(CommentDao)
     */
    @Override
    public void setCommentDao(CommentDao pCommentDao) {
        this.vCommentDao = pCommentDao;
    }

    /**
     * @see DaoFactory#setSiteDao(SiteDao)
     */
    @Override
    public void setSiteDao(SiteDao pSiteDao) {
        this.vSiteDao = pSiteDao;
    }

    /**
     * @see DaoFactory#setRouteDao(RouteDao)
     */
    @Override
    public void setRouteDao(RouteDao pRouteDao) {
        this.vRouteDao = pRouteDao;
    }

    /**
     * @see DaoFactory#setSectorDao(SectorDao)
     */
    @Override
    public void setSectorDao(SectorDao pSectorDao) {
        this.vSectorDao = pSectorDao;
    }

    /**
     * @see DaoFactory#setLengthDao(LengthDao)
     */
    @Override
    public void setLengthDao(LengthDao pLengthDao) {
        this.vLengthDao = pLengthDao;
    }

    /**
     * @see DaoFactory#setTopoDao(TopoDao)
     */
    @Override
    public void setTopoDao(TopoDao pTopoDao) {
        this.vTopoDao = pTopoDao;
    }

    /**
     * @see DaoFactory#setUserDao(UserDao)
     */
    @Override
    public void setUserDao(UserDao pUserDao) {
        this.vUserDao = pUserDao;
    }

}
