package fr.brucella.form.escapp.webapp.action.sites;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
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

public class SiteAddAction extends ActionSupport implements ServletRequestAware {

  //----- Input
  private String siteName;
  private String siteDepartment;
  private String siteMunicipality;
  private String siteDescription;
 
  private String sectorName;
  private String sectorDescription;
  
  private String routeName;
  private String routeGrade;
  private String routeDescription;
  private Integer routePointsNb;
  
  private Integer lengthLength;
  private String lengthGrade;
  private String lengthDescription;
  private Integer lengthPointsNb;
  
  // ----- Input / Output
  private Integer siteId;
  private Integer sectorId;
  private Integer routeId;
  
  //----- Output

  
  // ----- Struts Elements
  private HttpServletRequest servletRequest;
  
  // ----- Manager
  @Autowired
  private ManagerFactory managerFactory;
  
  
  // ===== Getters =====
  public Integer getSiteId() {
    return siteId;
  }
  
  public String getSiteName() {
    return siteName;
  }
  
  public String getSiteDepartment() {
    return siteDepartment;
  }
  
  public String getSiteMunicipality() {
    return siteMunicipality;
  }
  
  public String getSiteDescription() {
    return siteDescription;
  }
  
  public String getSectorName() {
    return sectorName;
  }
  
  public String getSectorDescription() {
    return sectorDescription;
  }
  
  public Integer getSectorId() {
    return sectorId;
  }
  
  public String getRouteName() {
    return routeName;
  }
  
  public String getRouteGrade() {
    return routeGrade;
  }
  
  public String getRouteDescription() {
    return routeDescription;
  }
  
  public Integer getRoutePointsNb() {
    return routePointsNb;
  }
  
  public Integer getRouteId() {
    return routeId;
  }
  
  public Integer getLengthLength() {
    return lengthLength;
  }
  
  public String getLengthGrade() {
    return lengthGrade;
  }
  
  public String getLengthDescription() {
    return lengthDescription;
  }
  
  public Integer getLengthPointsNb() {
    return lengthPointsNb;
  }
  
  // ===== Setters =====
  public void setSiteId(Integer pSiteId) {
    this.siteId = pSiteId;
  }
  
  public void setSiteName(String pSiteName) {
    this.siteName = pSiteName;
  }
  
  public void setSiteDepartment(String pSiteDepartment) {
    this.siteDepartment = pSiteDepartment;
  }
  
  public void setSiteMunicipality(String pSiteMunicipality) {
    this.siteMunicipality = pSiteMunicipality;
  }
  
  public void setSiteDescription(String pSiteDescription) {
    this.siteDescription = pSiteDescription;
  }
  
  public void setSectorName(String pSectorName) {
    this.sectorName = pSectorName;
  }
  
  public void setSectorDescription(String pSectorDescription) {
    this.sectorDescription = pSectorDescription;
  }

  public void setSectorId(Integer pSectorId) {
    this.sectorId = pSectorId;
  }
  
  public void setRouteName(String pRouteName) {
    this.routeName = pRouteName;
  }
  
  public void setRouteGrade(String pRouteGrade) {
    this.routeGrade = pRouteGrade;
  }
  
  public void setRoutePointsNb(Integer pRoutePointsNb) {
    this.routePointsNb = pRoutePointsNb;
  }
  
  public void setRouteDescription(String pRouteDescription) {
    this.routeDescription = pRouteDescription;
  }
  
  public void setRouteId(Integer pRouteId) {
    this.routeId = pRouteId;
  }
  
  public void setLengthLength(Integer pLengthLength) {
    this.lengthLength = pLengthLength;
  }
  
  public void setLengthGrade(String pLengthGrade) {
    this.lengthGrade = pLengthGrade;
  }
  
  public void setLengthDescription(String pLengthDescription) {
    this.lengthDescription = pLengthDescription;
  }
  
  public void setLengthPointsNb(Integer pLengthPointsNb) {
    this.lengthPointsNb = pLengthPointsNb;
  }
  
  @Override
  public void setServletRequest(HttpServletRequest pServletRequest) {
      this.servletRequest = pServletRequest;
  }
  
  // ===== Methods =====
  public String doAddSite() {
    
    if(isAdmin() == false) {
      return ActionSupport.ERROR;
    }
    
    if(StringUtils.isAllEmpty(siteName, siteDepartment, siteMunicipality, siteDescription)) {
      return ActionSupport.INPUT;
    }
    
    if(StringUtils.isEmpty(siteName)) {
      addFieldError("siteName", "Vous devez donner un nom au site");
    }
    if(StringUtils.isEmpty(siteDepartment)) {
      addFieldError("siteDepartment", "Vous devez indiquer un département associé au site.");
    }
    if(StringUtils.isEmpty(siteMunicipality)) {
      addFieldError("siteMunicipality", "Vous devez indiquer une commune associée au site.");
    }
    
    if(hasFieldErrors()) {
      return ActionSupport.INPUT;
    }
    
    Site vSite = new Site();
    vSite.setName(siteName);
    vSite.setDepartment(siteDepartment);
    vSite.setMunicipality(siteMunicipality);
    vSite.setDescription(siteDescription);
    
    try {
      managerFactory.getSiteManager().addSite(vSite);
      siteId = vSite.getId();
    } catch (TechnicalException pException) {
        this.addActionError(pException.getMessage());
        return ActionSupport.ERROR;
    } catch (FunctionalException pException) {
        this.addActionError(pException.getMessage());
        return ActionSupport.ERROR;
    }
    
    return ActionSupport.SUCCESS;
    
  }
  
  
  public String doAddSector() {
    
    if(isAdmin() == false) {
      return ActionSupport.ERROR;
    }
    
    if(StringUtils.isAllEmpty(sectorName, sectorDescription)) {
      return ActionSupport.INPUT;
    }
    
    if(siteId == null) {
      addActionError("L'identifiant du site auquel doit être associé le secteur est invalide (identifiant vide) - Echec de l'ajout");
      return ActionSupport.ERROR;
    }
    
    if(StringUtils.isEmpty(sectorName)) {
      addFieldError("sectorName", "Vous devez donner un nom au secteur");
    }
    
    if(hasFieldErrors()) {
      return ActionSupport.INPUT;
    }
    
    Sector vSector = new Sector();
    vSector.setName(sectorName);
    vSector.setDescription(sectorDescription);
    vSector.setSiteId(siteId);
    
    try {
      managerFactory.getSectorManager().addSector(vSector);
    } catch (TechnicalException pException) {
        this.addActionError(pException.getMessage());
        return ActionSupport.ERROR;
    } catch (FunctionalException pException) {
        this.addActionError(pException.getMessage());
        return ActionSupport.ERROR;
    }
    
    return ActionSupport.SUCCESS;
  }
  
  
  public String doAddRoute() {
    
    if(isAdmin() == false) {
      return ActionSupport.ERROR;
    }
    
    if(StringUtils.isAllEmpty(routeName, routeGrade, routeDescription) && routePointsNb == null) {
      return ActionSupport.INPUT;
    }
    
    if(sectorId == null) {
      addActionError("L'identifiant du secteur auquel doit être associé la voie est invalide (identifiant vide) - Echec de l'ajout");
      return ActionSupport.ERROR;
    }
    
    if(StringUtils.isEmpty(routeName)) {
      addFieldError("routeName", "Vous devez donner un nom à la voie");
    }
    if(StringUtils.isEmpty(routeGrade)) {
      addFieldError("routeGrade", "Vous devez indiquer une cotation");
    }
    if(routePointsNb == null) {
      addFieldError("routePointsNb", "Vous devez indiquer le nombre de point de la voie");
    }
    
    if(hasFieldErrors()) {
      return ActionSupport.INPUT;
    }
    
    Route vRoute = new Route();
    vRoute.setName(routeName);
    vRoute.setGrade(routeGrade);
    vRoute.setDescription(routeDescription);
    vRoute.setPointsNb(routePointsNb);
    vRoute.setSectorId(sectorId);
    
    try {
      managerFactory.getRouteManager().addRoute(vRoute);
      routeId = vRoute.getId();
    } catch (TechnicalException pException) {
        this.addActionError(pException.getMessage());
        return ActionSupport.ERROR;
    } catch (FunctionalException pException) {
        this.addActionError(pException.getMessage());
        return ActionSupport.ERROR;
    }
    
    return ActionSupport.SUCCESS;
  }
  
  public String doAddLength() {
    
    if(isAdmin() == false) {
      return ActionSupport.ERROR;
    }
    
    if(routeId == null) {
      addActionError("L'identifiant de la route à laquelle doit être associé la longueur est invalide (identifiant vide) - Echec de l'ajout");
      return ActionSupport.ERROR;
    }
    
    if(StringUtils.isAllEmpty(lengthGrade, lengthDescription) && lengthLength == null && lengthPointsNb == null) {
      return ActionSupport.INPUT;
    }
    
    if(StringUtils.isEmpty(lengthGrade)) {
      addFieldError("lengthGrade", "Vous devez indiquer la cotation de la longueur");
    }
    if(lengthLength == null) {
      addFieldError("lengthLength", "Vous devez indiquer la longueur en mètre");
    }
    if(lengthPointsNb == null) {
      addFieldError("lengthPointsNb", "Vous devez indiquer le nombre de point de la longueur");
    }
    
    if(hasFieldErrors()) {
      return ActionSupport.INPUT;
    }
    
    Length vLength = new Length();
    vLength.setLength(lengthLength);
    vLength.setDescription(lengthDescription);
    vLength.setGrade(lengthGrade);
    vLength.setPointsNb(lengthPointsNb);
    vLength.setRouteId(routeId);
    
    try {
      managerFactory.getLengthManager().addLength(vLength);
    } catch (TechnicalException pException) {
        this.addActionError(pException.getMessage());
        return ActionSupport.ERROR;
    } catch (FunctionalException pException) {
        this.addActionError(pException.getMessage());
        return ActionSupport.ERROR;
    }
    
    return ActionSupport.SUCCESS;
  }
  
  
  private boolean isAdmin() {
    User vUser = (User) this.servletRequest.getSession().getAttribute("user");
    if(vUser == null) {
      this.addActionError("Vous n'êtes plus identifié, l'ajout n'a pu se faire. Merci de vous reconnecter.");
      return false;
    }
    
    List<RoleUser> roles = (List<RoleUser>) this.servletRequest.getSession().getAttribute("roles");
    boolean admin = false;
    for(RoleUser role : roles) {
      if(StringUtils.equals(role.getUserRole(), "Admin")) {
        admin = true;
      }
    }
    if(admin != true) {
      this.addActionError("Votre role n'a pas les droits suffisant pour effectuer cet ajout - Echec de l'ajout");
      return false;
    }
    
    return true;
  }
}
