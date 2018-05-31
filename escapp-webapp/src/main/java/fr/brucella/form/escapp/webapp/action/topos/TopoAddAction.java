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

/**
 * Actions related to the addition of Topo.
 *
 * @author BRUCELLA2
 */
public class TopoAddAction extends ActionSupport implements ServletRequestAware {
  
  /**
   * Serial ID.
   */
  private static final long  serialVersionUID = 2759897204577580047L;
  
  // ----- Input
  
  /**
   * Name of the {@link Topo}. Can't be empty and max size is 100 characters.
   *
   * @see #getName()
   * @see #setName(String)
   */
  private String             name;
  
  /**
   * String representing the department code associated to {@link Topo}. Can't be empty and size need
   * to be 3 characters. Ex : "034", "030", "02A"
   *
   * @see #getDepartment()
   * @see #setDepartment(String)
   */
  private String             department;
  
  /**
   * String indicating if the {@link Topo} can be borrow.
   *
   * @see #getIsBorrowable()
   * @see #setIsBorrowable(String)
   */
  private String             isBorrowable;
  
  /**
   * Municipality associated to {@link Topo}. Can be empty and max size is 100 characters.
   *
   * @see #getMunicipality()
   * @see #setMunicipality(String)
   */
  private String             municipality;
  
  /**
   * Description of the {@link Topo}. Can be empty. Max size is 10 000 characters.
   *
   * @see #getDescription()
   * @see #setDescription(String)
   */
  private String             description;
  
  /**
   * Abstract representation of the file and directory pathnames of the topo.
   *
   * @see File
   * @see #getTopoFile()
   * @see #setTopoFile(File)
   */
  private File               topoFile;
  
  /**
   * Content Type of the topo file.
   *
   * @see #getTopoFileContentType()
   * @see #setTopoFileContentType(String)
   */
  private String             topoFileContentType;
  
  /**
   * Name of the topo file.
   *
   * @see #getTopoFileFileName()
   * @see #setTopoFileFileName(String)
   */
  private String             topoFileFileName;
  
  // ----- Output
  
  // ----- Manager
  
  /**
   * The Manager Factory Manager Factory allow to get and set business managers.
   */
  @Autowired
  private ManagerFactory     managerFactory;
  
  
  // ----- Struts Elements
  
  /**
   * The Http Servlet Request. Used to get session informations.
   *
   * @see HttpServletRequest
   * @see #setServletRequest(HttpServletRequest)
   */
  private HttpServletRequest servletRequest;
  
  
  // ===== Getters =====
  
  /**
   * Get the name of the {@link Topo}. Can't be empty and max size is 100 characters.
   *
   * @return the name of the {@link Topo}. Can't be empty and max size is 100 characters.
   *
   * @see #name
   * @see #setName(String)
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Get the string representing the department code associated to {@link Topo}. Can't be empty and
   * size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @return
   *
   * @see #department
   * @see #setDepartment(String)
   */
  public String getDepartment() {
    return this.department;
  }
  
  /**
   * Get the string indicating if the {@link Topo} can be borrow.
   *
   * @return the string indicating if the {@link Topo} can be borrow.
   *
   * @see #isBorrowable
   * @see #setIsBorrowable(String)
   */
  public String getIsBorrowable() {
    return this.isBorrowable;
  }
  
  /**
   * Get the municipality associated to {@link Topo}. Can be empty and max size is 100 characters.
   *
   * @return the municipality associated to {@link Topo}. Can be empty and max size is 100 characters.
   *
   * @see #municipality
   * @see #setMunicipality(String)
   */
  public String getMunicipality() {
    return this.municipality;
  }
  
  /**
   * Get the description of the {@link Topo}. Can be empty. Max size is 10 000 characters.
   *
   * @return the description of the {@link Topo}. Can be empty. Max size is 10 000 characters.
   *
   * @see #description
   * @see #setDescription(String)
   */
  public String getDescription() {
    return this.description;
  }
  
  /**
   * Get the content Type of the topo file
   *
   * @return the content Type of the topo file
   *
   * @see #topoFileContentType
   * @see #setTopoFileContentType(String)
   */
  public String getTopoFileContentType() {
    return this.topoFileContentType;
  }
  
  /**
   * Get the name of the topo file
   *
   * @return the name of the topo file
   *
   * @see #topoFileFileName
   * @see #setTopoFileFileName(String)
   */
  public String getTopoFileFileName() {
    return this.topoFileFileName;
  }
  
  /**
   * Get the abstract representation of the file and directory pathnames of the topo.
   *
   * @return the abstract representation of the file and directory pathnames of the topo.
   *
   * @see #topoFile
   * @see #setTopoFile(File)
   */
  public File getTopoFile() {
    return this.topoFile;
  }
  
  
  
  // ===== Setters =====
  /**
   * Set the name of the {@link Topo}. Can't be empty and max size is 100 characters.
   *
   * @param name the name of the {@link Topo}. Can't be empty and max size is 100 characters.
   *
   * @see #name
   * @see #getName()
   */
  public void setName(final String name) {
    this.name = name;
  }
  
  /**
   * Set the string representing the department code associated to {@link Topo}. Can't be empty and
   * size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @param department the string representing the department code associated to {@link Topo}. Can't
   *        be empty and size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @see #department
   * @see #getDepartment()
   */
  public void setDepartment(final String department) {
    this.department = department;
  }
  
  /**
   * Set the string indicating if the {@link Topo} can be borrow.
   *
   * @param isBorrowable the string indicating if the {@link Topo} can be borrow.
   *
   * @see #isBorrowable
   * @see #getIsBorrowable()
   */
  public void setIsBorrowable(final String isBorrowable) {
    this.isBorrowable = isBorrowable;
  }
  
  /**
   * Set the municipality associated to {@link Topo}. Can be empty and max size is 100 characters.
   *
   * @param municipality the municipality associated to {@link Topo}. Can be empty and max size is 100
   *        characters.
   *
   * @see #municipality
   * @see #getMunicipality()
   */
  public void setMunicipality(final String municipality) {
    this.municipality = municipality;
  }
  
  /**
   * Set the description of the {@link Topo}. Can be empty. Max size is 10 000 characters.
   *
   * @param description the description of the {@link Topo}. Can be empty. Max size is 10 000
   *        characters.
   *
   * @see #description
   * @see #getDescription()
   */
  public void setDescription(final String description) {
    this.description = description;
  }
  
  /**
   * Set the content Type of the topo file.
   *
   * @param topoFileContentType the content Type of the topo file
   *
   * @see #topoFileContentType
   * @see #getTopoFileContentType()
   */
  public void setTopoFileContentType(final String topoFileContentType) {
    this.topoFileContentType = topoFileContentType;
  }
  
  /**
   * Set the name of the topo file.
   *
   * @param topoFileFileName the name of the topo file
   *
   * @see #topoFileFileName
   * @see #getTopoFileFileName()
   */
  public void setTopoFileFileName(final String topoFileFileName) {
    this.topoFileFileName = topoFileFileName;
  }
  
  /**
   * Set the abstract representation of the file and directory pathnames of the topo.
   *
   * @param topoFile the abstract representation of the file and directory pathnames of the topo.
   *
   * @see #topoFile
   * @see #getTopoFile()
   */
  public void setTopoFile(final File topoFile) {
    this.topoFile = topoFile;
  }
  
  /**
   * Set the Http Servlet Request. Used to get session informations.
   *
   * @see #servletRequest
   */
  @Override
  public void setServletRequest(final HttpServletRequest servletRequest) {
    this.servletRequest = servletRequest;
  }
  
  // ===== Methods =====
  
  /**
   * Add the topo. Only connected user can add a topo. Topo pdf file is add in /WEB-INF/files/
   *
   * @return ERROR if error occurred INPUT if name, department, isBorrowable, municipality and
   *         description, topoFile are null or empty. SUCCESS otherwise.
   */
  @Override
  public String execute() throws Exception {
    
    if (StringUtils.isAllEmpty(this.name, this.department, this.isBorrowable, this.municipality, this.description)) {
      return Action.INPUT;
    }
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    if (user == null) {
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
    
    
    final Topo topo = new Topo();
    topo.setName(this.name);
    topo.setDepartment(this.department);
    topo.setMunicipality(this.municipality);
    topo.setDescription(this.description);
    topo.setBorrowable(StringUtils.equals(this.isBorrowable, "true"));
    topo.setPdfFileName(this.topoFileFileName);
    topo.setOwner(user.getId());
    
    final ServletContext servletContext = ServletActionContext.getServletContext();
    final String path = servletContext.getRealPath("/WEB-INF/files/");
    final File destFile = new File(path, this.topoFileFileName);
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
      this.managerFactory.getTopoManager().addTopo(topo);
    } catch (FunctionalException | TechnicalException pException) {
      this.addActionError(pException.getMessage());
      FileUtils.deleteQuietly(destFile);
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
}
