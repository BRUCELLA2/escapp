package fr.brucella.form.escapp.webapp.action.topos;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public class TopoAddAction extends ActionSupport implements ServletRequestAware {
    
    
    private static final long  serialVersionUID = 2759897204577580047L;
    
    // ----- Input
    private String             name;
    private String             department;
    private String             isBorrowable;
    private String             municipality;
    private String             description;
    
    private File               topoFile;
    private String             topoFileContentType;
    private String             topoFileFileName;
    
    // ----- Output
    
    // ----- Manager
    @Autowired
    private ManagerFactory     managerFactory;
    
    // ----- Struts Elements
    private HttpServletRequest servletRequest;
    
    
    // ===== Getters =====
    public String getName() {
        return this.name;
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public String getIsBorrowable() {
        return this.isBorrowable;
    }
    
    public String getMunicipality() {
        return this.municipality;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getTopoFileContentType() {
        return this.topoFileContentType;
    }
    
    public String getTopoFileFileName() {
        return this.topoFileFileName;
    }
    
    public File getTopoFile() {
        return this.topoFile;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest pServletRequest) {
        this.servletRequest = pServletRequest;
    }
    
    
    // ===== Setters =====
    public void setName(String pName) {
        this.name = pName;
    }
    
    public void setDepartment(String pDepartment) {
        this.department = pDepartment;
    }
    
    public void setIsBorrowable(String pIsBorrowable) {
        this.isBorrowable = pIsBorrowable;
    }
    
    public void setMunicipality(String pMunicipality) {
        this.municipality = pMunicipality;
    }
    
    public void setDescription(String pDescription) {
        this.description = pDescription;
    }
    
    public void setTopoFileContentType(String pTopoFileContentType) {
        this.topoFileContentType = pTopoFileContentType;
    }
    
    public void setTopoFileFileName(String pTopoFileFileName) {
        this.topoFileFileName = pTopoFileFileName;
    }
    
    public void setTopoFile(File pTopoFile) {
        this.topoFile = pTopoFile;
    }
    
    
    // ===== Methods =====
    
    /**
     * Add the topo. Only connected user can add a topo.
     * 
     * Topo pdf file is add in /WEB-INF/files/
     * 
     * @return ERROR if error occurred INPUT if name, department, isBorrowable, municipality and
     *         description, topoFile are null or empty. SUCCESS otherwise.
     */
    @Override
    public String execute() throws Exception {
        
        if (StringUtils.isAllEmpty(this.name, this.department, this.isBorrowable, this.municipality, this.description)) {
            return Action.INPUT;
        }
        
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, l'affichage de vos topos n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }
        
        if (StringUtils.isEmpty(this.name)) {
            this.addFieldError("name", "Vous devez donner un nom à votre topo");
        }
        
        if (StringUtils.isEmpty(this.department)) {
            this.addFieldError("department", "Le topo doit être associé à un département");
        }
        
        if (this.topoFile == null) {
            this.addFieldError("topoFile", "Vous devez envoyer un topo sous la forme d'un fichier pdf.");
        }
        
        if (this.hasFieldErrors()) {
            return Action.INPUT;
        }
        
        
        Topo vTopo = new Topo();
        vTopo.setName(this.name);
        vTopo.setDepartment(this.department);
        vTopo.setMunicipality(this.municipality);
        vTopo.setDescription(this.description);
        vTopo.setBorrowable(StringUtils.equals(this.isBorrowable, "true"));
        vTopo.setPdfFileName(this.topoFileFileName);
        vTopo.setOwner(vUser.getId());
        
        ServletContext servletContext = ServletActionContext.getServletContext();
        String path = servletContext.getRealPath("/WEB-INF/files/");
        File destFile = new File(path, this.topoFileFileName);
        try {
            if (destFile.exists()) {
                this.addFieldError("topoFile", "Un fichier avec ce nom existe déjà - Veuillez changer le nom du fichier.");
                return Action.INPUT;
            }
            FileUtils.copyFile(this.topoFile, destFile);
        } catch (IOException pException) {
            this.addActionError("Un problème lors de l'enregistrement du fichier est survenu - Echec de l'ajout du topo");
            return Action.ERROR;
        }
        
        try {
            this.managerFactory.getTopoManager().addTopo(vTopo);
        } catch (FunctionalException | TechnicalException pException) {
            this.addActionError(pException.getMessage());
            FileUtils.deleteQuietly(destFile);
            return Action.ERROR;
        }
        
        return Action.SUCCESS;
    }
}
