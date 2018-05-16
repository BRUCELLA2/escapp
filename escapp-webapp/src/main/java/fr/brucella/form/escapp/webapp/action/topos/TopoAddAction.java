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
import com.opensymphony.xwork2.ActionSupport;
import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public class TopoAddAction extends ActionSupport implements ServletRequestAware {

  // ----- Input
  private String name;
  private String department;
  private String isBorrowable;
  private String municipality;
  private String description;
  
  private File topoFile;
  private String topoFileContentType;
  private String topoFileFileName;
  
  // ----- Output
  
  // ----- Manager
  @Autowired
  private ManagerFactory managerFactory;
  
  // ----- Struts Elements
  private HttpServletRequest servletRequest;
  
  
  // ===== Getters =====
  public String getName() {
    return name;
  }
  
  public String getDepartment() {
    return department;
  }
  
  public String getIsBorrowable() {
    return isBorrowable;
  }
  
  public String getMunicipality() {
    return municipality;
  }
  
  public String getDescription() {
    return description;
  }
  
  public String getTopoFileContentType() {
	  return topoFileContentType;
  }
  
  public String getTopoFileFileName() {
	  return topoFileFileName;
  }
  
  public File getTopoFile() {
	  return topoFile;
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
  
  public String execute() throws Exception {
	  
	    if(StringUtils.isAllEmpty(name, department, isBorrowable, municipality, description)) {
	        return ActionSupport.INPUT;
	      }
	      
	    User vUser = (User) this.servletRequest.getSession().getAttribute("user");
	    if(vUser == null) {
	        addActionError("Vous n'êtes plus identifié, l'affichage de vos topos n'a pu se faire. Merci de vous reconnecter.");
	        return ActionSupport.ERROR;
	    }
	    
	    if(StringUtils.isEmpty(name)) {
	    	addFieldError("name","Vous devez donner un nom à votre topo");
	    }
	    
	    if(StringUtils.isEmpty(department)) {
	    	addFieldError("department", "Le topo doit être associé à un département");;
	    }	  
	    
	    if(topoFile == null) {
	    	addFieldError("topoFile", "Vous devez envoyer un topo sous la forme d'un fichier pdf.");
	    }
	    
		if(hasFieldErrors()) {
			return ActionSupport.INPUT;
		}
		
		
		Topo vTopo = new Topo();
		vTopo.setName(name);
		vTopo.setDepartment(department);
		vTopo.setMunicipality(municipality);
		vTopo.setDescription(description);
		vTopo.setBorrowable(StringUtils.equals(isBorrowable, "true"));
		vTopo.setPdfFileName(topoFileFileName);
		vTopo.setOwner(vUser.getId());
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		String path = servletContext.getRealPath("/WEB-INF/files/");
		File destFile = new File(path, topoFileFileName);
		try {
			if(destFile.exists()) {
				addFieldError("topoFile","Un fichier avec ce nom existe déjà - Veuillez changer le nom du fichier.");
				return ActionSupport.INPUT;
			}
			FileUtils.copyFile(topoFile, destFile);
		}catch (IOException pException) {
			addActionError("Un problème lors de l'enregistrement du fichier est survenu - Echec de l'ajout du topo");
			return ActionSupport.ERROR;
		}
		
		try {
			managerFactory.getTopoManager().addTopo(vTopo);
		}catch (FunctionalException pException) {
			this.addActionError(pException.getMessage());
			FileUtils.deleteQuietly(destFile);
			return ActionSupport.ERROR;
		}catch (TechnicalException pException) {
			this.addActionError(pException.getMessage());
			FileUtils.deleteQuietly(destFile);
			return ActionSupport.ERROR;
		}	    
	    
	  return ActionSupport.SUCCESS;
  }
}
