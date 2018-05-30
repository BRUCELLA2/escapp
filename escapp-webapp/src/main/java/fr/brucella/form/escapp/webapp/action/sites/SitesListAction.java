package fr.brucella.form.escapp.webapp.action.sites;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.SiteSearch;

/**
 * Actions related to site listing.
 * 
 * @author BRUCELLA2
 */
public class SitesListAction extends ActionSupport {
  
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -8977464808054592705L;
  
  
  // ----- Input
  
  /**
   * String representing the department code to which {@link Site} belongs. Can't be empty and size
   * need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @see #getDepartmentSite()
   * @see #setDepartmentSite(String)
   */
  private String            departmentSite;
  
  /**
   * Municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @see #getMunicipalitySite()
   * @see #setMunicipalitySite(String)
   */
  private String            municipalitySite;
  
  /**
   * Minimum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @see #getMinGrade()
   * @see #setMinGrade(String)
   */
  private String            minGrade;
  
  /**
   * Maximum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @see #getMaxGrade()
   * @see #setMaxGrade(String)
   */
  private String            maxGrade;
  
  
  // ----- Output
  
  /**
   * A list of {@link Site}.
   * 
   * @see #getSitesList()
   * @see #setSitesList(List)
   */
  private List<Site>        sitesList;
  
  
  // ----- Manager
  /**
   * The Manager Factory Manager Factory allow to get and set business managers.
   */
  @Autowired
  private ManagerFactory    managerFactory;
  
  
  // ----- Logger
  /**
   * Sites list action logger
   */
  private static final Log               LOG              = LogFactory.getLog(SitesListAction.class);
  
  
  // ===== Getters =====
  
  /**
   * Get the list of {@link Site}.
   * 
   * @return the list of {@link Site}.
   * 
   * @see #sitesList
   * @see #setSitesList(List)
   */
  public List<Site> getSitesList() {
    return this.sitesList;
  }
  
  /**
   * Get the String representing the department code to which {@link Site} belongs. Can't be empty and size
   * need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @return the String representing the department code to which {@link Site} belongs. Can't be empty and size
   *          need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @see #departmentSite
   * @see #setDepartmentSite(String)
   */
  public String getDepartmentSite() {
    return this.departmentSite;
  }
  
  /**
   * Get the Municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @return the Municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @see #municipalitySite
   * @see #setMunicipalitySite(String)
   */
  public String getMunicipalitySite() {
    return this.municipalitySite;
  }
  
  /**
   * Get the Minimum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @return the Minimum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @see #minGrade
   * @see #setMinGrade(String)
   */
  public String getMinGrade() {
    return this.minGrade;
  }
  
  /**
   * Get the Maximum grade for the {@link Route} belongs to {@link Site}.
   *  
   * @return the Maximum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @see #maxGrade
   * @see #setMaxGrade(String)
   */
  public String getMaxGrade() {
    return this.maxGrade;
  }
  
  
  // ===== Setters =====
  /**
   * Set the String representing the department code to which {@link Site} belongs. Can't be empty and size
   * need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @param departmentSite the String representing the department code to which {@link Site} belongs. Can't be empty and size
   *                        need to be 3 characters. Ex : "034", "030", "02A".
   * 
   * @see #departmentSite
   * @see #getDepartmentSite()
   */
  public void setDepartmentSite(final String departmentSite) {
    this.departmentSite = departmentSite;
  }
  
  /**
   * Set the Municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @param municipalitySite the Municipality to which {@link Site} belongs. Can't be empty and max size is 100 characters.
   * 
   * @see #municipalitySite
   * @see #getMunicipalitySite()
   */
  public void setMunicipalitySite(final String municipalitySite) {
    this.municipalitySite = municipalitySite;
  }
  
  /**
   * Set the Minimum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @param minGrade the Minimum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @see #minGrade
   * @see #getMinGrade()
   */
  public void setMinGrade(final String minGrade) {
    this.minGrade = minGrade;
  }
  
  /**
   * Set the Maximum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @param maxGrade the Maximum grade for the {@link Route} belongs to {@link Site}.
   * 
   * @see #maxGrade
   * @see #getMaxGrade()
   */
  public void setMaxGrade(final String maxGrade) {
    this.maxGrade = maxGrade;
  }
  
  /**
   * Set the list of {@link Site}.
   * 
   * @param sitesList the list of {@link Site}.
   * 
   * @see #sitesList
   * @see #getSitesList()
   */
  public void setSitesList(final List<Site> sitesList) {
    this.sitesList = sitesList;
  }
  
  // ===== Methods =====
  
  /**
   * Get a list of all sites.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doList() {
    
    try {
      this.sitesList = this.managerFactory.getSiteManager().getAllSitesList();
    } catch (TechnicalException | NotFoundException pException) {
      this.addActionError(pException.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Get a list of sites that match to the searching criteria.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doSiteSearching() {
    
    SiteSearch siteSearch;
    
    if (StringUtils.isAllEmpty(this.departmentSite, this.municipalitySite, this.minGrade, this.maxGrade)) {
      siteSearch = null;
    }
    else {
      siteSearch = new SiteSearch();
      siteSearch.setDepartmentSite(this.departmentSite);
      siteSearch.setMunicipalitySite(this.municipalitySite);
      siteSearch.setMinGradeRoute(this.minGrade);
      siteSearch.setMaxGradeRoute(this.maxGrade);
    }
    
    try {
      this.sitesList = this.managerFactory.getSiteManager().getSearchSitesList(siteSearch);
    } catch (TechnicalException | NotFoundException | FunctionalException pException) {
      LOG.debug(pException.getStackTrace());
      LOG.error(pException.getMessage());
      this.addActionError(pException.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
}
