package fr.brucella.form.escapp.webapp.action.comments;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import javassist.expr.NewArray;

public class CommentsAction extends ActionSupport implements ServletRequestAware {

	// ----- Input
	private Integer siteId;
	private Integer sectorId;
	private Integer routeId;
	private String commentSite;
	private String commentSector;
	private String commentRoute;
	
	// ----- Output
	
	
	// ----- Manager
	@Autowired
	private ManagerFactory managerFactory;
	
	// ***** Struts Elements
	private HttpServletRequest servletRequest;
	
	// ===== Getters =====
	public Integer getSiteId() {
		return siteId;
	}
	
	public Integer getSectorId() {
		return sectorId;
	}
	
	public Integer getRouteId() {
	    return routeId;
	}
	
	public String getCommentSite() {
		return commentSite;
	}
	
	public String getCommentSector() {
	    return commentSector;
	}
	
	public String getCommentRoute() {
	    return commentRoute;
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
	
	public void setCommentSite(String pCommentSite) {
		this.commentSite = pCommentSite;
	}
	
	public void setCommentSector(String pCommentSector) {
      this.commentSector = pCommentSector;
    }
	
	public void setCommentRoute(String pCommentRoute) {
	  this.commentRoute = pCommentRoute;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest pServletRequest) {
		this.servletRequest = pServletRequest;
	}
	
	
	
	
	// ===== Methods =====
	
	// TODO refactoring duplicate parts of code in methods.
	
	public String doAddCommentSite() {
		
		if(StringUtils.isEmpty(commentSite) || siteId == null) {
			return ActionSupport.INPUT;
		}
		
		User vUser = (User) this.servletRequest.getSession().getAttribute("user");
		
		if(vUser == null) {
			addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
			return ActionSupport.ERROR;
		}
		
		Comment vCommentSite = new Comment();
		vCommentSite.setEscappUser(vUser.getId());
		vCommentSite.setIdCommentTarget(siteId);
		vCommentSite.setText(commentSite);
		
		try {
			managerFactory.getCommentManager().addCommentSite(vCommentSite);
		} catch (TechnicalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (FunctionalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}
		
		return ActionSupport.SUCCESS;
	}
	
	
	public String doAddCommentSector() {
		
		if(StringUtils.isEmpty(commentSector) || sectorId == null) {
			return ActionSupport.INPUT;
		}
		
		Sector vSector;
		
		try {
			vSector = managerFactory.getSectorManager().getSectorById(sectorId);
			siteId = vSector.getSiteId();
		} catch (TechnicalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (FunctionalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (NotFoundException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}
		
		User vUser = (User) this.servletRequest.getSession().getAttribute("user");
	
	    if(vUser == null) {
            addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
            return ActionSupport.ERROR;
        }
		
		Comment vComment = new Comment();
		vComment.setEscappUser(vUser.getId());
		vComment.setIdCommentTarget(sectorId);
		vComment.setText(commentSector);
		
		try {
			managerFactory.getCommentManager().addCommentSector(vComment);
		} catch (TechnicalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (FunctionalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}
		
		return ActionSupport.SUCCESS;
	}
	
	
	public String doAddCommentRoute() {
	  
	     if(StringUtils.isEmpty(commentRoute) || routeId == null) {
           return ActionSupport.INPUT;
       }
       
       User vUser = (User) this.servletRequest.getSession().getAttribute("user");
       
       if(vUser == null) {
           addActionError("Vous n'êtes plus identifié, l'ajout du commentaire n'a pu se faire. Merci de vous reconnecter.");
           return ActionSupport.ERROR;
       }
       
       
       Comment vCommentRoute = new Comment();
       vCommentRoute.setEscappUser(vUser.getId());
       vCommentRoute.setIdCommentTarget(routeId);
       vCommentRoute.setText(commentRoute);
       
       try {
           managerFactory.getCommentManager().addCommentRoute(vCommentRoute);
       } catch (TechnicalException pException) {
           this.addActionError(pException.getMessage());
           return ActionSupport.ERROR;
       } catch (FunctionalException pException) {
           this.addActionError(pException.getMessage());
           return ActionSupport.ERROR;
       }
	  
	  return ActionSupport.SUCCESS;
	}
	
}

