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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

public class TopoDetailsAction extends ActionSupport implements ServletRequestAware {
    
    private static final long           serialVersionUID = 6745173617900744958L;
    
    Log                                 log              = LogFactory.getLog(TopoDetailsAction.class);
    
    
    // ----- Input
    private Integer                     id;
    private String                      fileName;
    private Integer                     nbDays;
    private String                      borrowable;
    
    // ----- Output
    private Topo                        topo;
    private Integer                     nbCommentsTopo;
    private List<Pair<Comment, String>> commentsTopoList;
    private User                        ownerTopo;
    private User                        borrowerTopo;
    
    
    // ----- Manager
    @Autowired
    private ManagerFactory              managerFactory;
    
    // ----- Struts Elements
    private InputStream                 inputStream;
    private HttpServletRequest          servletRequest;
    
    
    // ===== Getters =====
    public Integer getId() {
        return this.id;
    }
    
    public Topo getTopo() {
        return this.topo;
    }
    
    public Integer getNbCommentsTopo() {
        return this.nbCommentsTopo;
    }
    
    public List<Pair<Comment, String>> getCommentsTopoList() {
        return this.commentsTopoList;
    }
    
    public User getOwnerTopo() {
        return this.ownerTopo;
    }
    
    public User getBorrowerTopo() {
        return this.borrowerTopo;
    }
    
    public InputStream getInputStream() {
        return this.inputStream;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public Integer getNbDays() {
        return this.nbDays;
    }
    
    public String getBorrowable() {
        return this.borrowable;
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
    /**
     * Get Topo details with comments
     * 
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doTopoDetails() {
        
        if (this.id == null) {
            this.addActionError("L'identifiant du topo recherché est incorrect (Identifiant vide) - Echec de la recherche");
            return Action.ERROR;
        }
        
        try {
            this.topo = this.managerFactory.getTopoManager().getTopoById(this.id);
            if (this.topo.getOwner() != 0) {
                this.ownerTopo = this.managerFactory.getUserManager().getUserById(this.topo.getOwner());
            }
            if (this.topo.getBorrower() != null) {
                this.borrowerTopo = this.managerFactory.getUserManager().getUserById(this.topo.getBorrower());
            }
        } catch (TechnicalException | NotFoundException | FunctionalException pException) {
            this.log.debug(pException.getMessage());
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        try {
            this.commentsTopoList = this.managerFactory.getCommentManager().getCommentsTopoListWithLogin(this.id, "ASC");
            this.nbCommentsTopo = this.commentsTopoList.size();
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        } catch (NotFoundException pException) {
            this.nbCommentsTopo = 0;
            this.addActionMessage("Aucun commentaire");
        }
        
        return Action.SUCCESS;
    }
    
    /**
     * Topo pdf downloading
     * 
     * @return SUCCESS
     */
    @Override
    public String execute() throws FileNotFoundException {
        
        ServletContext servletContext = ServletActionContext.getServletContext();
        String path = servletContext.getRealPath("/WEB-INF/files/" + this.getFileName());
        File fileToDownLoad = new File(path);
        
        this.inputStream = new FileInputStream(fileToDownLoad);
        this.fileName = fileToDownLoad.getName();
        
        return Action.SUCCESS;
    }
    
    /**
     * Books the topo for the user. Number of days of booking is provide in a form by user (Can't be
     * more than 14 days, check is make in the form). Only connected user can books topo.
     * 
     * @return ERROR if error occurred INPUT if number of days is null SUCCESS otherwise
     */
    public String doBooking() {
        
        if (this.id == null) {
            this.addActionError("L'identifiant du topo à réserver est incorrect (Identifiant vide) - Echec de la réservation");
            return Action.ERROR;
        }
        
        if (this.nbDays == null) {
            this.doTopoDetails();
            return Action.INPUT;
        }
        
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, la réservation du topo n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }
        
        try {
            this.topo = this.managerFactory.getTopoManager().getTopoById(this.id);
            this.topo = this.managerFactory.getTopoManager().borrowTopo(this.topo, this.nbDays, vUser);
            this.borrowerTopo = vUser;
        } catch (TechnicalException | NotFoundException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        return Action.SUCCESS;
    }
    
    /**
     * Change the borrowable property of the Topo. Only owner of the Topo can make this change. Check if
     * user is owner of the topo is make in the jsp.
     * 
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doChangeBorrowable() {
        
        if (this.id == null) {
            this.addActionError("L'identifiant du topo à modifier est incorrect (Identifiant vide) - Echec de la modification");
            return Action.ERROR;
        }
        
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, la modification du topo n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }
        
        try {
            this.topo = this.managerFactory.getTopoManager().getTopoById(this.id);
            this.topo.setBorrowable(StringUtils.equals(this.borrowable, "true"));
            this.managerFactory.getTopoManager().modifyTopo(this.topo, vUser);
        } catch (TechnicalException | NotFoundException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }
        
        return Action.SUCCESS;
        
    }
    
    /**
     * Delete the topo
     * 
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doDelete() {
        
        if (this.id == null) {
            this.addActionError("L'identifiant du topo à supprimer est incorrect (Identifiant vide) - Echec de la modification");
            return Action.ERROR;
        }

        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, la suppression du topo n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }

        try {
            
            Topo vTopo = this.managerFactory.getTopoManager().getTopoById(this.id);
            
            ServletContext servletContext = ServletActionContext.getServletContext();
            String path = servletContext.getRealPath("/WEB-INF/files/");
            File topoPdf = new File(path, vTopo.getPdfFileName());
            FileUtils.deleteQuietly(topoPdf);
            
            this.managerFactory.getTopoManager().deleteTopo(this.id, vUser);
            
        } catch (TechnicalException | NotFoundException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }
}
