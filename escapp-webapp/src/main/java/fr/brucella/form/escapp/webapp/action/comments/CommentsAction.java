package fr.brucella.form.escapp.webapp.action.comments;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public class CommentsAction extends ActionSupport implements ServletRequestAware {
    
    
    private static final long  serialVersionUID = -617440123390057718L;
    
    // ----- Input
    private Integer            siteId;
    private Integer            sectorId;
    private Integer            routeId;
    private Integer            topoId;
    private String             commentSite;
    private String             commentSector;
    private String             commentRoute;
    private String             commentTopo;
    
    // ----- Output
    
    
    // ----- Manager
    @Autowired
    private ManagerFactory     managerFactory;
    
    // ***** Struts Elements
    private HttpServletRequest servletRequest;
    
    // ===== Getters =====
    public Integer getSiteId() {
        return this.siteId;
    }
    
    public Integer getSectorId() {
        return this.sectorId;
    }
    
    public Integer getRouteId() {
        return this.routeId;
    }
    
    public Integer getTopoId() {
        return this.topoId;
    }
    
    public String getCommentSite() {
        return this.commentSite;
    }
    
    public String getCommentSector() {
        return this.commentSector;
    }
    
    public String getCommentRoute() {
        return this.commentRoute;
    }
    
    public String getCommentTopo() {
        return this.commentTopo;
    }
    
    
    // ===== Setters =====
    public void setSiteId(Integer pSiteId) {
        this.siteId = pSiteId;
    }
    
    public void setSectorId(Integer pSectorId) {
        this.sectorId = pSectorId;
    }
    
    public void setRouteId(Integer pRouteId) {
        this.routeId = pRouteId;
    }
    
    public void setTopoId(Integer pTopoId) {
        this.topoId = pTopoId;
    }
    
    public void setCommentSite(String pCommentSite) {
        this.commentSite = pCommentSite;
    }
    
    public void setCommentSector(String pCommentSector) {
        this.commentSector = pCommentSector;
    }
    
    public void setCommentRoute(String pCommentRoute) {
        this.commentRoute = pCommentRoute;
    }
    
    public void setCommentTopo(String pCommentTopo) {
        this.commentTopo = pCommentTopo;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest pServletRequest) {
        this.servletRequest = pServletRequest;
    }
    
    
    
    // ===== Methods =====
    
    /**
     * Add a comment to a site
     * 
     * @return ERROR if error occurred INPUT if the comment of the site or the site id are null SUCCESS
     *         otherwise
     */
    public String doAddCommentSite() {
        
        if (StringUtils.isEmpty(this.commentSite) || this.siteId == null) {
            return Action.INPUT;
        }
        
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        
        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }
        
        Comment vCommentSite = new Comment();
        vCommentSite.setEscappUser(vUser.getId());
        vCommentSite.setIdCommentTarget(this.siteId);
        vCommentSite.setText(this.commentSite);
        
        try {
            this.managerFactory.getCommentManager().addCommentSite(vCommentSite);
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        return Action.SUCCESS;
    }
    
    /**
     * Add a comment to a sector
     * 
     * @return ERROR if error occurred INPUT if the comment of the sector or the sector id are null
     *         SUCCESS otherwise
     */
    public String doAddCommentSector() {
        
        if (StringUtils.isEmpty(this.commentSector) || this.sectorId == null) {
            return Action.INPUT;
        }
        
        Sector vSector;
        
        try {
            vSector = this.managerFactory.getSectorManager().getSectorById(this.sectorId);
            // siteId is need for the redirection to the site details
            this.siteId = vSector.getSiteId();
        } catch (TechnicalException | FunctionalException | NotFoundException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        
        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }
        
        Comment vComment = new Comment();
        vComment.setEscappUser(vUser.getId());
        vComment.setIdCommentTarget(this.sectorId);
        vComment.setText(this.commentSector);
        
        try {
            this.managerFactory.getCommentManager().addCommentSector(vComment);
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        return Action.SUCCESS;
    }
    
    /**
     * Add a comment to a route
     * 
     * @return ERROR if error occurred INPUT if the comment of the route or the route id are null
     *         SUCCESS otherwise
     */
    public String doAddCommentRoute() {
        
        if (StringUtils.isEmpty(this.commentRoute) || this.routeId == null) {
            return Action.INPUT;
        }
        
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        
        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }
        
        
        Comment vCommentRoute = new Comment();
        vCommentRoute.setEscappUser(vUser.getId());
        vCommentRoute.setIdCommentTarget(this.routeId);
        vCommentRoute.setText(this.commentRoute);
        
        try {
            this.managerFactory.getCommentManager().addCommentRoute(vCommentRoute);
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        return Action.SUCCESS;
    }
    
    /**
     * Add a comment to a topo
     * 
     * @return ERROR if error occurred INPUT if the comment of the topo or the topo id are null SUCCESS
     *         otherwise
     */
    public String doAddCommentTopo() {
        
        if (StringUtils.isEmpty(this.commentTopo) || this.topoId == null) {
            return Action.INPUT;
        }
        
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        
        if (vUser == null) {
            this.addActionError("Vous n'êtes pas identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }
        
        Comment vCommentTopo = new Comment();
        vCommentTopo.setEscappUser(vUser.getId());
        vCommentTopo.setIdCommentTarget(this.topoId);
        vCommentTopo.setText(this.commentTopo);
        
        try {
            this.managerFactory.getCommentManager().addCommentTopo(vCommentTopo);
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        return Action.SUCCESS;
    }
}

