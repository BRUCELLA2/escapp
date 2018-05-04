package fr.brucella.form.escapp.webapp.action.sites;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public class SiteDetailsAction extends ActionSupport {

	// ----- Input
	private Integer id;
	
	// ----- Output
	private Site site;
	private Sector sector;
	private List<Sector> sectorsList;
	private Route route;
	private List<Route> routesList;
	private List<Length> lengthsList;
	private int nbPoints;
	
	private Integer nbCommentsSite;
	private List<Pair<Integer, Integer>> nbCommentsSectorsList;
	private Integer nbCommentsRoute;
	private List<Pair<Comment, String>> commentsSiteList;
	private List<Pair<Comment, String>> commentsSectorList;
	private List<Pair<Comment, String>> commentsRouteList;
	
	// ----- Manager
	@Autowired
	private ManagerFactory managerFactory;
	
	
	// ===== Getters =====
	public Integer getId() {
		return id;
	}
	
	public Site getSite() {
		return site;
	}
	
	public Sector getSector() {
	  return sector;
	}
	
	public List<Sector> getSectorsList() {
	    return sectorsList;
	}
	
	public Route getRoute() {
	  return route;
	}
	
	public List<Route> getRoutesList(){
	  return routesList;
	}
	
	public List<Length> getLengthsList(){
	  return lengthsList;
	}
	
	public int getNbPoints() {
	  return nbPoints;
	}
	
	public Integer getNbCommentsSite() {
		return nbCommentsSite;
	}
	
	public Integer getNbCommentsRoute() {
	    return nbCommentsRoute;
	}
	
	public List<Pair<Integer, Integer>> getNbCommentsSectorsList() {
		return nbCommentsSectorsList;
	}
	
	public List<Pair<Comment, String>> getCommentsSiteList(){
		return commentsSiteList;
	}
	
	public List<Pair<Comment, String>> getCommentsSectorList(){
		return commentsSectorList;
	}
	
	public List<Pair<Comment, String>> getCommentsRouteList(){
	    return commentsRouteList;
	}
	
	
	// ===== Setters =====
	
	public void setId(Integer pId) {
		id = pId;
	}
	
	
	
	
	// ===== Methods =====
	public String doSiteDetails() {
		
		if(id == null) {
			this.addActionError("L'identifiant du site recherché est incorrect (Identifiant vide) - Echec de la recherche");
			return ActionSupport.ERROR;
		}
		else {
			try {
				site = managerFactory.getSiteManager().getSiteById(id);
			}catch (TechnicalException pException) {
				this.addActionError(pException.getMessage());
				return ActionSupport.ERROR;
			}catch (FunctionalException pException) {
				this.addActionError(pException.getMessage());
				return ActionSupport.ERROR;
			}catch (NotFoundException pException) {
				this.addActionError(pException.getMessage());
				return ActionSupport.ERROR;
			}
		}
		
		try {
			commentsSiteList = managerFactory.getCommentManager().getCommentsSiteListWithLogin(id, "ASC");
		}catch (TechnicalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}catch (FunctionalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}catch (NotFoundException pException) {
			this.addActionMessage("Aucun commentaire");
			return ActionSupport.SUCCESS;
		}
		
		nbCommentsSite = commentsSiteList.size();
		
		try {
		  sectorsList = managerFactory.getSectorManager().getSectorsSiteList(site.getId());
	      routesList = new ArrayList<>();
	      commentsSectorList = new ArrayList<>();
	      nbCommentsSectorsList = new ArrayList<>();
	      for(Sector sector : sectorsList) {
              routesList.addAll(managerFactory.getRouteManager().getRoutesSectorList(sector.getId()));
              List<Pair<Comment, String>> tempCommentsSectorList = managerFactory.getCommentManager().getCommentsSectorListWithLogin(sector.getId(),"ASC");
              nbCommentsSectorsList.add(new MutablePair<Integer, Integer>(sector.getId(), tempCommentsSectorList.size()));
              commentsSectorList.addAll(tempCommentsSectorList);
	      }
		}catch(TechnicalException pException) {
		  this.addActionError(pException.getMessage());
		  return ActionSupport.ERROR;
		}catch (FunctionalException pException) {
          this.addActionError(pException.getMessage());
          return ActionSupport.ERROR;
        }catch (NotFoundException pException) {
          return  ActionSupport.SUCCESS;    
        }
		
		return(this.hasErrors()) ? ActionSupport.ERROR : ActionSupport.SUCCESS;		
		
	}
	
	public String doRouteDetails() {
	  
	   if(id == null) {
           this.addActionError("L'identifiant de la voie recherché est incorrect (Identifiant vide) - Echec de l'affichage des détails");
           return ActionSupport.ERROR;
       }
	   else {
	     try {
	       route = managerFactory.getRouteManager().getRouteById(id);
	       sector = managerFactory.getSectorManager().getSectorById(route.getSectorId());
	       site = managerFactory.getSiteManager().getSiteById(sector.getSiteId());
         }catch (TechnicalException pException) {
           this.addActionError(pException.getMessage());
           return ActionSupport.ERROR;
         }catch (FunctionalException pException) {
           this.addActionError(pException.getMessage());
           return ActionSupport.ERROR;
         }catch (NotFoundException pException) {
           this.addActionError(pException.getMessage());
           return ActionSupport.ERROR;    
         }
	   }
	   
	   try {
	     lengthsList = managerFactory.getLengthManager().getLengthsRouteList(route.getId());
       }catch (TechnicalException pException) {
         this.addActionError(pException.getMessage());
         return ActionSupport.ERROR;
       }catch (FunctionalException pException) {
         this.addActionError(pException.getMessage());
         return ActionSupport.ERROR;
       }catch (NotFoundException pException) {
         return ActionSupport.SUCCESS;    
       }
	   
	   nbPoints = nbPoints(lengthsList);
	   
       try {
         commentsRouteList = managerFactory.getCommentManager().getCommentsRouteListWithLogin(id, "ASC");
     }catch (TechnicalException pException) {
         this.addActionError(pException.getMessage());
         return ActionSupport.ERROR;
     }catch (FunctionalException pException) {
         this.addActionError(pException.getMessage());
         return ActionSupport.ERROR;
     }catch (NotFoundException pException) {
         this.addActionMessage("Aucun commentaire");
         return ActionSupport.SUCCESS;
     }
       
	  nbCommentsRoute = commentsRouteList.size();
       
	  return(this.hasErrors()) ? ActionSupport.ERROR : ActionSupport.SUCCESS; 
	}
	
	private int nbPoints(List<Length> pLengthsList) {
	  int nbPoins = 0;
	  
	  for(Length length : pLengthsList) {
	    nbPoins += length.getPointsNb();
 	  }
	  
	  return nbPoins;
	}
}
