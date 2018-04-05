package fr.brucella.form.escapp.consumer.contract;

import fr.brucella.form.escapp.consumer.contract.dao.comment.CommentDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.RouteDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;

public interface DaoFactory {

    CommentDao getCommentDao();
    SiteDao getSiteDao();
    RouteDao getRouteDao();
    SectorDao getSectorDao();
    LengthDao getLengthDao();
    TopoDao getTopoDao();
    UserDao getUserDao();

    void setCommentDao(CommentDao pCommentDao);
    void setSiteDao(SiteDao pSiteDao);
    void setRouteDao(RouteDao pRouteDao);
    void setSectorDao(SectorDao pSectorDao);
    void setLengthDao(LengthDao pLengthDao);
    void setTopoDao(TopoDao pTopoDao);
    void setUserDao(UserDao pUserDao);
}
