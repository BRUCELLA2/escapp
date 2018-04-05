package fr.brucella.form.escapp.consumer.impl;

import fr.brucella.form.escapp.consumer.contract.DaoFactory;
import fr.brucella.form.escapp.consumer.contract.dao.comment.CommentDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.RouteDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;

public class DaoFactoryImpl implements DaoFactory {

    private CommentDao vCommentDao;
    private SiteDao vSiteDao;
    private TopoDao vTopoDao;
    private UserDao vUserDao;
    private RouteDao vRouteDao;
    private SectorDao vSectorDao;
    private LengthDao vLengthDao;


    // ===== Getters =====

    @Override
    public CommentDao getCommentDao() {
        return vCommentDao;
    }

    @Override
    public SiteDao getSiteDao() {
        return vSiteDao;
    }

    @Override
    public RouteDao getRouteDao() {
        return vRouteDao;
    }

    @Override
    public SectorDao getSectorDao() {
        return vSectorDao;
    }

    @Override
    public LengthDao getLengthDao() {
        return vLengthDao;
    }

    @Override
    public TopoDao getTopoDao() {
        return vTopoDao;
    }

    @Override
    public UserDao getUserDao() {
        return vUserDao;
    }


    // ===== Setters =====

    @Override
    public void setCommentDao(CommentDao pCommentDao) {
        this.vCommentDao = pCommentDao;
    }

    @Override
    public void setSiteDao(SiteDao pSiteDao) {
        this.vSiteDao = pSiteDao;
    }

    @Override
    public void setRouteDao(RouteDao pRouteDao) {
        this.vRouteDao = pRouteDao;
    }

    @Override
    public void setSectorDao(SectorDao pSectorDao) {
        this.vSectorDao = pSectorDao;
    }

    @Override
    public void setLengthDao(LengthDao pLengthDao) {
        this.vLengthDao = pLengthDao;
    }

    @Override
    public void setTopoDao(TopoDao pTopoDao) {
        this.vTopoDao = pTopoDao;
    }

    @Override
    public void setUserDao(UserDao pUserDao) {
        this.vUserDao = pUserDao;
    }
}
