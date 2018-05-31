package fr.brucella.form.escapp.webapp.action.sites;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.beans.user.RoleUser;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * Actions related to the addition of Site (with Sector, Route and Length).
 * 
 * @author BRUCELLA2
 *
 */
public class SiteAddAction extends ActionSupport implements ServletRequestAware {
  
  /**
   * Serial ID.
   */
  private static final long  serialVersionUID = -854809676971542803L;
  
  
  // ----- Input
  
  /**
   * Name of the {@link Site}. Can't be empty and max size is 100 characters.
   * 
   * @see #getSiteName()
   * @see #setSiteName(String)
   */
  private String             siteName;
  
  /**
   * String representing the department code to which {@link Site} belongs. Can't be empty and size
   * need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @see #getSiteDepartment()
   * @see #setSiteDepartment(String)
   */
  private String             siteDepartment;
  
  /**
   * Municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @see #getSiteMunicipality()
   * @see #setSiteMunicipality(String)
   */
  private String             siteMunicipality;
  
  /**
   * Description of the {@link Site}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #getSiteDescription()
   * @see #setSiteDescription(String)
   */
  private String             siteDescription;
  
  /**
   * Name of the {@link Sector}. Can't be empty and max size is 100 characters.
   * 
   * @see #getSectorName()
   * @see #setSectorName(String)
   */
  private String             sectorName;
  
  /**
   * Description of the {@link Sector}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #getSectorDescription()
   * @see #setSectorDescription(String)
   */
  private String             sectorDescription;
  
  /**
   * Name of the {@link Route}. Can't be empty and max size is 100 characters.
   * 
   * @see #getRouteName()
   * @see #setRouteName(String)
   */
  private String             routeName;
  
  /**
   * Grade of the {@link Route}. The grade is a {@link String} which represents the difficulty. Can't
   * be empty and max size is 2 characters.
   * 
   * @see #getRouteGrade()
   * @see #setRouteGrade(String)
   */
  private String             routeGrade;
  
  /**
   * Description of the {@link Route}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #getRouteDescription()
   * @see #setRouteDescription(String)
   */
  private String             routeDescription;
  
  /**
   * Number of points for this {@link Route}. Can't be null.
   * 
   * @see #getRoutePointsNb()
   * @see #setRoutePointsNb(Integer)
   */
  private Integer            routePointsNb;
  
  /**
   * Length in meters of the {@link Length}. Can't be null.
   * 
   * @see #getLengthLength()
   * @see #setLengthLength(Integer)
   */
  private Integer            lengthLength;
  
  /**
   * Grade of the {@link Length}. The grade is a {@link String} which represents the difficulty. Can't
   * be empty and max size is 2 characters.
   * 
   * @see #getLengthGrade()
   * @see #setLengthGrade(String)
   */
  private String             lengthGrade;
  
  /**
   * Description of the {@link Length}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #getLengthDescription()
   * @see #setLengthDescription(String)
   */
  private String             lengthDescription;
  
  /**
   * Number of points for this {@link Length}. Can't be null.
   * 
   * @see #getLengthPointsNb()
   * @see #setLengthPointsNb(Integer)
   */
  private Integer            lengthPointsNb;
  
  // ----- Input / Output
  /**
   * id of the {@link Site}.
   * 
   * @see #getSiteId()
   * @see #setSiteId(Integer)
   */
  private Integer            siteId;
  
  /**
   * id of the {@link Sector}.
   * 
   * @see #getSectorId()
   * @see #setSectorId(Integer)
   */
  private Integer            sectorId;
  
  /**
   * id of the {@link Route}.
   * 
   * @see #getRouteId()
   * @see #setRouteId(Integer)
   */
  private Integer            routeId;
  
  // ----- Output
  
  
  // ----- Struts Elements
  /**
   * The Http Servlet Request. Used to get session informations.
   *
   * @see HttpServletRequest
   * @see #setServletRequest(HttpServletRequest)
   */
  private HttpServletRequest servletRequest;
  
  // ----- Manager
  /**
   * The Manager Factory Manager Factory allow to get and set business managers.
   */
  @Autowired
  private ManagerFactory     managerFactory;
  
  
  // ===== Getters =====
  
  /**
   * Get the id of the {@link Site}.
   * 
   * @return the id of the {@link Site}.
   * 
   * @see #siteId
   * @see #setSiteId(Integer)
   */
  public Integer getSiteId() {
    return this.siteId;
  }
  
  /**
   * Get the name of the {@link Site}. Can't be empty and max size is 100 characters.
   * 
   * @return the name of the {@link Site}. Can't be empty and max size is 100 characters.
   * 
   * @see #siteName
   * @see #setSiteName(String)
   */
  public String getSiteName() {
    return this.siteName;
  }
  
  /**
   * Get the String representing the department code to which {@link Site} belongs. Can't be empty and size
   * need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @return the String representing the department code to which {@link Site} belongs. Can't be empty and size
   *          need to be 3 characters. Ex : "034", "030", "02A".
   *          
   * @see #siteDepartment
   * @see #setSiteDepartment(String)
   */
  public String getSiteDepartment() {
    return this.siteDepartment;
  }
  
  /**
   * Get the municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @return the municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @see #siteMunicipality
   * @see #setSiteMunicipality(String)
   */
  public String getSiteMunicipality() {
    return this.siteMunicipality;
  }
  
  /**
   * Get the description of the {@link Site}. Can be empty. Max size is 10 000 characters.
   * 
   * @return the description of the {@link Site}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #siteDescription
   * @see #setSiteDescription(String)
   */
  public String getSiteDescription() {
    return this.siteDescription;
  }
  
  /**
   * Get the name of the {@link Sector}. Can't be empty and max size is 100 characters.
   * 
   * @return the name of the {@link Sector}. Can't be empty and max size is 100 characters.
   * 
   * @see #sectorName
   * @see #setSectorName(String)
   */
  public String getSectorName() {
    return this.sectorName;
  }
  
  /**
   * Get the description of the {@link Sector}. Can be empty. Max size is 10 000 characters.
   * 
   * @return the description of the {@link Sector}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #sectorDescription
   * @see #setSectorDescription(String)
   */
  public String getSectorDescription() {
    return this.sectorDescription;
  }
  
  /**
   * Get the id of the {@link Sector}.
   * 
   * @return the id of the {@link Sector}.
   * 
   * @see #sectorId
   * @see #setSectorId(Integer)
   */
  public Integer getSectorId() {
    return this.sectorId;
  }
  
  /**
   * Get the name of the {@link Route}. Can't be empty and max size is 100 characters.
   * 
   * @return the name of the {@link Route}. Can't be empty and max size is 100 characters.
   * 
   * @see #routeName
   * @see #setRouteName(String)
   */
  public String getRouteName() {
    return this.routeName;
  }
  
  /**
   * Get the grade of the {@link Route}. The grade is a {@link String} which represents the difficulty. Can't
   * be empty and max size is 2 characters.
   * 
   * @return the grade of the {@link Route}. The grade is a {@link String} which represents the difficulty. Can't
   *          be empty and max size is 2 characters.
   * 
   * @see #routeGrade
   * @see #setRouteGrade(String)
   */
  public String getRouteGrade() {
    return this.routeGrade;
  }
  
  /**
   * Get the description of the {@link Route}. Can be empty. Max size is 10 000 characters.
   * 
   * @return the description of the {@link Route}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #routeDescription
   * @see #setRouteDescription(String)
   */
  public String getRouteDescription() {
    return this.routeDescription;
  }
  
  /**
   * Get the number of points for this {@link Route}. Can't be null.
   * 
   * @return the number of points for this {@link Route}. Can't be null.
   * 
   * @see #routePointsNb
   * @see #setRoutePointsNb(Integer)
   */
  public Integer getRoutePointsNb() {
    return this.routePointsNb;
  }
  
  /**
   * Get the id of the {@link Route}.
   * 
   * @return the id of the {@link Route}.
   * 
   * @see #routeId
   * @see #setRouteId(Integer)
   */
  public Integer getRouteId() {
    return this.routeId;
  }
  
  /**
   * Get the length in meters of the {@link Length}. Can't be null.
   * 
   * @return the length in meters of the {@link Length}. Can't be null.
   * 
   * @see #lengthLength
   * @see #setLengthLength(Integer)
   */
  public Integer getLengthLength() {
    return this.lengthLength;
  }
  
  /**
   * Get the grade of the {@link Length}. The grade is a {@link String} which represents the difficulty. Can't
   * be empty and max size is 2 characters.
   * 
   * @return the grade of the {@link Length}. The grade is a {@link String} which represents the difficulty. Can't
   *          be empty and max size is 2 characters.
   * 
   * @see #lengthGrade
   * @see #setLengthGrade(String)
   */
  public String getLengthGrade() {
    return this.lengthGrade;
  }
  
  /**
   * Get the description of the {@link Length}. Can be empty. Max size is 10 000 characters.
   * 
   * @return the description of the {@link Length}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #lengthDescription
   * @see #setLengthDescription(String)
   */
  public String getLengthDescription() {
    return this.lengthDescription;
  }
  
  /**
   * Get the number of points for this {@link Length}. Can't be null.
   * 
   * @return the number of points for this {@link Length}. Can't be null.
   * 
   * @see #lengthPointsNb
   * @see #setLengthPointsNb(Integer)
   */
  public Integer getLengthPointsNb() {
    return this.lengthPointsNb;
  }
  
  // ===== Setters =====
  
  /**
   * Set the id of the {@link Site}.
   * 
   * @param siteId the id of the {@link Site}.
   * 
   * @see #siteId
   * @see #getSiteId()
   */
  public void setSiteId(final Integer siteId) {
    this.siteId = siteId;
  }
  
  /**
   * Set the name of the {@link Site}. Can't be empty and max size is 100 characters.
   * 
   * @param siteName the name of the {@link Site}. Can't be empty and max size is 100 characters.
   * 
   * @see #siteName
   * @see #getSiteName()
   */
  public void setSiteName(final String siteName) {
    this.siteName = siteName;
  }
  
  /**
   * Set the String representing the department code to which {@link Site} belongs. Can't be empty and size
   * need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @param siteDepartment the String representing the department code to which {@link Site} belongs. Can't be empty and size
   *                        need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @see #siteDepartment
   * @see #getSiteDepartment()
   */
  public void setSiteDepartment(final String siteDepartment) {
    this.siteDepartment = siteDepartment;
  }
  
  /**
   * Set the municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @param siteMunicipality the municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @see #siteMunicipality
   * @see #setSiteMunicipality(String)
   */
  public void setSiteMunicipality(final String siteMunicipality) {
    this.siteMunicipality = siteMunicipality;
  }
  
  /**
   * Set the description of the {@link Site}. Can be empty. Max size is 10 000 characters.
   * 
   * @param siteDescription the description of the {@link Site}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #siteDescription
   * @see #getSiteDescription()
   */
  public void setSiteDescription(final String siteDescription) {
    this.siteDescription = siteDescription;
  }
  
  /**
   * Set the name of the {@link Sector}. Can't be empty and max size is 100 characters.
   * 
   * @param sectorName the name of the {@link Sector}. Can't be empty and max size is 100 characters.
   * 
   * @see #sectorName
   * @see #getSectorName()
   */
  public void setSectorName(final String sectorName) {
    this.sectorName = sectorName;
  }
  
  /**
   * Set the description of the {@link Sector}. Can be empty. Max size is 10 000 characters.
   * 
   * @param sectorDescription the description of the {@link Sector}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #sectorDescription
   * @see #getSectorDescription()
   */
  public void setSectorDescription(final String sectorDescription) {
    this.sectorDescription = sectorDescription;
  }
  
  /**
   * Set the id of the {@link Sector}.
   * 
   * @param sectorId the id of the {@link Sector}.
   * 
   * @see #sectorId
   * @see #getSectorId()
   */
  public void setSectorId(final Integer sectorId) {
    this.sectorId = sectorId;
  }
  
  /**
   * Set the name of the {@link Route}. Can't be empty and max size is 100 characters.
   * 
   * @param routeName the name of the {@link Route}. Can't be empty and max size is 100 characters.
   * 
   * @see #routeName
   * @see #getRouteName()
   */
  public void setRouteName(final String routeName) {
    this.routeName = routeName;
  }
  
  /**
   * Set the grade of the {@link Route}. The grade is a {@link String} which represents the difficulty. Can't
   * be empty and max size is 2 characters.
   * 
   * @param routeGrade the grade of the {@link Route}. The grade is a {@link String} which represents the difficulty. Can't
   *                    be empty and max size is 2 characters.
   * 
   * @see #routeGrade
   * @see #getRouteGrade()
   */
  public void setRouteGrade(final String routeGrade) {
    this.routeGrade = routeGrade;
  }
  
  /**
   * Set the number of points for this {@link Route}. Can't be null.
   * 
   * @param routePointsNb the number of points for this {@link Route}. Can't be null.
   * 
   * @see #routePointsNb
   * @see #setRoutePointsNb(Integer)
   */
  public void setRoutePointsNb(final Integer routePointsNb) {
    this.routePointsNb = routePointsNb;
  }
  
  /**
   * Set the description of the {@link Route}. Can be empty. Max size is 10 000 characters.
   * 
   * @param routeDescription the description of the {@link Route}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #routeDescription
   * @see #getRouteDescription()
   */
  public void setRouteDescription(final String routeDescription) {
    this.routeDescription = routeDescription;
  }
  
  /**
   * Set the id of the {@link Route}.
   * 
   * @param routeId the id of the {@link Route}.
   * 
   * @see #routeId
   * @see #getRouteId()
   */
  public void setRouteId(final Integer routeId) {
    this.routeId = routeId;
  }
  
  /**
   * Set the length in meters of the {@link Length}. Can't be null.
   *  
   * @param lengthLength the length in meters of the {@link Length}. Can't be null.
   * 
   * @see #lengthLength
   * @see #setLengthLength(Integer)
   */
  public void setLengthLength(final Integer lengthLength) {
    this.lengthLength = lengthLength;
  }
  
  /**
   * Set the grade of the {@link Length}. The grade is a {@link String} which represents the difficulty. Can't
   * be empty and max size is 2 characters.
   * 
   * @param lengthGrade the grade of the {@link Length}. The grade is a {@link String} which represents the difficulty. Can't
   *                      be empty and max size is 2 characters.
   * 
   * @see #lengthGrade
   * @see #getLengthGrade()
   */
  public void setLengthGrade(final String lengthGrade) {
    this.lengthGrade = lengthGrade;
  }
  
  /**
   * Set the description of the {@link Length}. Can be empty. Max size is 10 000 characters.
   * 
   * @param lengthDescription the description of the {@link Length}. Can be empty. Max size is 10 000 characters.
   * 
   * @see #lengthDescription
   * @see #getLengthDescription()
   */
  public void setLengthDescription(final String lengthDescription) {
    this.lengthDescription = lengthDescription;
  }
  
  /**
   * Set the number of points for this {@link Length}. Can't be null.
   * 
   * @param lengthPointsNb the number of points for this {@link Length}. Can't be null.
   * 
   * @see #lengthPointsNb
   * @see #getLengthPointsNb()
   */
  public void setLengthPointsNb(final Integer lengthPointsNb) {
    this.lengthPointsNb = lengthPointsNb;
  }
  
  /**
   * Set the Http Servlet Request.
   *
   * @see #servletRequest
   */
  @Override
  public void setServletRequest(final HttpServletRequest servletRequest) {
    this.servletRequest = servletRequest;
  }
  
  // ===== Methods =====
  
  /**
   * Add a site. Only Administrators can add site.
   *
   * @return ERROR if error occurred INPUT if input value are not valid SUCCESS otherwise
   */
  public String doAddSite() {
    
    if (this.isAdmin() == false) {
      return Action.ERROR;
    }
    
    if (StringUtils.isAllEmpty(this.siteName, this.siteDepartment, this.siteMunicipality, this.siteDescription)) {
      return Action.INPUT;
    }
    
    if (StringUtils.isEmpty(this.siteName)) {
      this.addFieldError("siteName", "Vous devez donner un nom au site");
    }
    if (StringUtils.isEmpty(this.siteDepartment)) {
      this.addFieldError("siteDepartment", "Vous devez indiquer un département associé au site.");
    }
    if (StringUtils.isEmpty(this.siteMunicipality)) {
      this.addFieldError("siteMunicipality", "Vous devez indiquer une commune associée au site.");
    }
    
    if (this.hasFieldErrors()) {
      return Action.INPUT;
    }
    
    final Site site = new Site();
    site.setName(this.siteName);
    site.setDepartment(this.siteDepartment);
    site.setMunicipality(this.siteMunicipality);
    site.setDescription(this.siteDescription);
    
    try {
      this.managerFactory.getSiteManager().addSite(site);
      this.siteId = site.getId();
    } catch (TechnicalException | FunctionalException pException) {
      this.addActionError(pException.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
    
  }
  
  /**
   * Add a sector Only Administrators can add sector.
   *
   * @return ERROR if error occurred INPUT if input value are not valid SUCCESS otherwise
   */
  public String doAddSector() {
    
    if (this.isAdmin() == false) {
      return Action.ERROR;
    }
    
    if (StringUtils.isAllEmpty(this.sectorName, this.sectorDescription)) {
      return Action.INPUT;
    }
    
    if (this.siteId == null) {
      this.addActionError("L'identifiant du site auquel doit être associé le secteur est invalide (identifiant vide) - Echec de l'ajout");
      return Action.ERROR;
    }
    
    if (StringUtils.isEmpty(this.sectorName)) {
      this.addFieldError("sectorName", "Vous devez donner un nom au secteur");
    }
    
    if (this.hasFieldErrors()) {
      return Action.INPUT;
    }
    
    final Sector sector = new Sector();
    sector.setName(this.sectorName);
    sector.setDescription(this.sectorDescription);
    sector.setSiteId(this.siteId);
    
    try {
      this.managerFactory.getSectorManager().addSector(sector);
    } catch (TechnicalException | FunctionalException pException) {
      this.addActionError(pException.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Add a route Only Administrators can add route.
   *
   * @return ERROR if error occurred INPUT if input value are not valid SUCCESS otherwise
   */
  public String doAddRoute() {
    
    if (this.isAdmin() == false) {
      return Action.ERROR;
    }
    
    if (StringUtils.isAllEmpty(this.routeName, this.routeGrade, this.routeDescription) && this.routePointsNb == null) {
      return Action.INPUT;
    }
    
    if (this.sectorId == null) {
      this.addActionError("L'identifiant du secteur auquel doit être associé la voie est invalide (identifiant vide) - Echec de l'ajout");
      return Action.ERROR;
    }
    
    if (StringUtils.isEmpty(this.routeName)) {
      this.addFieldError("routeName", "Vous devez donner un nom à la voie");
    }
    if (StringUtils.isEmpty(this.routeGrade)) {
      this.addFieldError("routeGrade", "Vous devez indiquer une cotation");
    }
    if (this.routePointsNb == null) {
      this.addFieldError("routePointsNb", "Vous devez indiquer le nombre de point de la voie");
    }
    
    if (this.hasFieldErrors()) {
      return Action.INPUT;
    }
    
    final Route route = new Route();
    route.setName(this.routeName);
    route.setGrade(this.routeGrade);
    route.setDescription(this.routeDescription);
    route.setPointsNb(this.routePointsNb);
    route.setSectorId(this.sectorId);
    
    try {
      this.managerFactory.getRouteManager().addRoute(route);
      this.routeId = route.getId();
    } catch (TechnicalException | FunctionalException pException) {
      this.addActionError(pException.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Add a length Only Administrators can add length.
   *
   * @return ERROR if error occurred INPUT if input value are not valid SUCCESS otherwise
   */
  public String doAddLength() {
    
    if (this.isAdmin() == false) {
      return Action.ERROR;
    }
    
    if (this.routeId == null) {
      this.addActionError("L'identifiant de la route à laquelle doit être associé la longueur est invalide (identifiant vide) - Echec de l'ajout");
      return Action.ERROR;
    }
    
    if (StringUtils.isAllEmpty(this.lengthGrade, this.lengthDescription) && this.lengthLength == null && this.lengthPointsNb == null) {
      return Action.INPUT;
    }
    
    if (StringUtils.isEmpty(this.lengthGrade)) {
      this.addFieldError("lengthGrade", "Vous devez indiquer la cotation de la longueur");
    }
    if (this.lengthLength == null) {
      this.addFieldError("lengthLength", "Vous devez indiquer la longueur en mètre");
    }
    if (this.lengthPointsNb == null) {
      this.addFieldError("lengthPointsNb", "Vous devez indiquer le nombre de point de la longueur");
    }
    
    if (this.hasFieldErrors()) {
      return Action.INPUT;
    }
    
    final Length length = new Length();
    length.setLength(this.lengthLength);
    length.setDescription(this.lengthDescription);
    length.setGrade(this.lengthGrade);
    length.setPointsNb(this.lengthPointsNb);
    length.setRouteId(this.routeId);
    
    try {
      this.managerFactory.getLengthManager().addLength(length);
    } catch (TechnicalException | FunctionalException pException) {
      this.addActionError(pException.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Check if user is administrator. Add ActionError if user is not administrator.
   *
   * @return true if user is administrator and false otherwise
   */
  private boolean isAdmin() {
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    if (user == null) {
      this.addActionError("Vous n'êtes plus identifié, l'ajout n'a pu se faire. Merci de vous reconnecter.");
      return false;
    }
    
    final List<RoleUser> roles = (List<RoleUser>) this.servletRequest.getSession().getAttribute("roles");
    boolean admin = false;
    for (final RoleUser role : roles) {
      if (StringUtils.equals(role.getUserRole(), "Admin")) {
        admin = true;
      }
    }
    if (admin == false) {
      this.addActionError("Votre role n'a pas les droits suffisant pour effectuer cet ajout - Echec de l'ajout");
      return false;
    }
    
    return true;
  }
}
