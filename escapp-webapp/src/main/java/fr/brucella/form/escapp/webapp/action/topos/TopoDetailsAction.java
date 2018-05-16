package fr.brucella.form.escapp.webapp.action.topos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public class TopoDetailsAction extends ActionSupport implements ServletRequestAware{

	// ----- Input
	private Integer id;
	private String fileName;
	private Integer nbDays;
	private String borrowable;
	
	// ----- Output
	private Topo topo;
	private Integer nbCommentsTopo;
	private List<Pair<Comment,String>> commentsTopoList;
	private User ownerTopo;
	private User borrowerTopo;
	
	
	// ----- Manager
	@Autowired
	private ManagerFactory managerFactory;
	
	// ----- Struts Elements
	private InputStream inputStream;
	private HttpServletRequest servletRequest;

	
	// ===== Getters =====
	public Integer getId() {
		return id;
	}
	
	public Topo getTopo() {
		return topo;
	}
	
	public Integer getNbCommentsTopo() {
		return nbCommentsTopo;
	}
	
	public List<Pair<Comment, String>> getCommentsTopoList(){
		return commentsTopoList;
	}
	
	public User getOwnerTopo() {
		return ownerTopo;
	}
	
	public User getBorrowerTopo() {
		return borrowerTopo;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public Integer getNbDays() {
		return nbDays;
	}
	
	public String getBorrowable() {
	  return borrowable;
	}
	
	// ===== Setters =====
	public void setId(Integer pId) {
		this.id = pId;
	}
	
	public void setFileName(String pFileName) {
		this.fileName = pFileName;
	}
	
	public void setNbDays(Integer pNbDays) {
		this.nbDays = pNbDays;
	}
	
	public void setBorrowable(String pBorrowable) {
      this.borrowable = pBorrowable;
    }
	
	@Override
	public void setServletRequest(HttpServletRequest pServletRequest) {
		this.servletRequest = pServletRequest;
	}
	
	// ===== Methods =====
	public String doTopoDetails() {
	
		if(id == null) {
			this.addActionError("L'identifiant du topo recherché est incorrect (Identifiant vide) - Echec de la recherche");
			return ActionSupport.ERROR;
		}
		
		try {
			topo = managerFactory.getTopoManager().getTopoById(id);
			if(topo.getOwner() != 0) {
				ownerTopo = managerFactory.getUserManager().getUserById(topo.getOwner());
			}
			if(topo.getBorrower() != null) {
				borrowerTopo = managerFactory.getUserManager().getUserById(topo.getBorrower());
			}
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
		
		try {
			commentsTopoList = managerFactory.getCommentManager().getCommentsTopoListWithLogin(id, "ASC");
			nbCommentsTopo = commentsTopoList.size();
		}catch (TechnicalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}catch (FunctionalException pException) {
			this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}catch (NotFoundException pException) {
			nbCommentsTopo = 0;
			this.addActionMessage("Aucun commentaire");
		}
		
		return ActionSupport.SUCCESS;
	}
	
	
	public String execute() throws FileNotFoundException{
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		String path = servletContext.getRealPath("/WEB-INF/files/"+getFileName());
		File fileToDownLoad = new File(path);
		
		inputStream = new FileInputStream(fileToDownLoad);
		fileName = fileToDownLoad.getName();
		
		return ActionSupport.SUCCESS;
	}
	
	
	public String doBooking() {
		
		if(id == null) {
			this.addActionError("L'identifiant du topo à réserver est incorrect (Identifiant vide) - Echec de la réservation");
			return ActionSupport.ERROR;
		}
		
		if(nbDays == null) {
			doTopoDetails();
			return ActionSupport.INPUT;
		}
		
		User vUser = (User) this.servletRequest.getSession().getAttribute("user");
	    if(vUser == null) {
            addActionError("Vous n'êtes plus identifié, la réservation du topo n'a pu se faire. Merci de vous reconnecter.");
            return ActionSupport.ERROR;
        }
	    
	    try {
	    	topo = managerFactory.getTopoManager().getTopoById(id);
	    	topo = managerFactory.getTopoManager().borrowTopo(topo, nbDays, vUser);
	    	borrowerTopo = vUser;
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
		
		return ActionSupport.SUCCESS;
	}
	
	
	public String doChangeBorrowable() {
	  
	  if(id == null) {
           this.addActionError("L'identifiant du topo à modifier est incorrect (Identifiant vide) - Echec de la modification");
           return ActionSupport.ERROR;
      }
	     
      User vUser = (User) this.servletRequest.getSession().getAttribute("user");
      if(vUser == null) {
          addActionError("Vous n'êtes plus identifié, la modification du topo n'a pu se faire. Merci de vous reconnecter.");
          return ActionSupport.ERROR;
      }
      
      try {
          topo = managerFactory.getTopoManager().getTopoById(id);
          topo.setBorrowable(StringUtils.equals(borrowable, "true"));
          managerFactory.getTopoManager().modifyTopo(topo, vUser);
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
      
      return ActionSupport.SUCCESS;
	  
	}
	
	
	public String doDelete() {
	  
        if(id == null) {
            this.addActionError("L'identifiant du topo à supprimer est incorrect (Identifiant vide) - Echec de la modification");
            return ActionSupport.ERROR;
        }
        
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        if(vUser == null) {
            addActionError("Vous n'êtes plus identifié, la suppression du topo n'a pu se faire. Merci de vous reconnecter.");
            return ActionSupport.ERROR;
        }
        
        try {
          
          Topo vTopo = managerFactory.getTopoManager().getTopoById(id);
          
          ServletContext servletContext = ServletActionContext.getServletContext();
          String path = servletContext.getRealPath("/WEB-INF/files/");
          File topoPdf = new File(path,vTopo.getPdfFileName());
          FileUtils.deleteQuietly(topoPdf);
          
          managerFactory.getTopoManager().deleteTopo(id, vUser);    
          
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
        
        return ActionSupport.SUCCESS;
	}
}
