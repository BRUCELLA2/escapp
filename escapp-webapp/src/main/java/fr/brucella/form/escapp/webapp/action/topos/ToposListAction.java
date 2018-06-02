package fr.brucella.form.escapp.webapp.action.topos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.TopoSearch;

/**
 * Actions related to topo listing.
 *
 * @author BRUCELLA2
 */
public class ToposListAction extends ActionSupport implements ServletRequestAware {
  
  /**
   * Serial ID.
   */
  private static final long  serialVersionUID = 1573862908235940140L;
  
  // ----- Topos list action Logger
  /**
   * Site add action logger.
   */
  private static final Log   LOG              = LogFactory.getLog(ToposListAction.class);
  
  // ----- Input
  
  /**
   * String representing the department code associated to {@link Topo}. Can't be empty and size need
   * to be 3 characters. Ex : "034", "030", "02A"
   *
   * @see #getDepartmentTopo()
   * @see #setDepartmentTopo(String)
   */
  private String             departmentTopo;
  
  /**
   * Municipality associated to {@link Topo}. Can be empty and max size is 100 characters.
   *
   * @see #getMunicipalityTopo()
   * @see #setMunicipalityTopo(String)
   */
  private String             municipalityTopo;
  
  /**
   * String indicating if the {@link Topo} is available for a new borrow (Topo is borrowable and not
   * borrow at this time).
   *
   * @see #getAvailable()
   * @see #setAvailable(String)
   */
  private String             available;
  
  
  // ----- Output
  
  /**
   * A list of {@link Topo}.
   *
   * @see #getToposList()
   * @see #setToposList(List)
   */
  private List<Topo>         toposList;
  
  /**
   * List of {@link Topo} owned by the user.
   *
   * @see #getToposOwnerList()
   * @see #setToposOwnerList(List)
   */
  private List<Topo>         toposOwnerList;
  
  /**
   * List of {@link Topo} borrowed by the user.
   *
   * @see #getToposBorrowerList()
   * @see #setToposBorrowerList(List)
   */
  private List<Topo>         toposBorrowerList;
  
  
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
   * Get the list of {@link Topo}.
   *
   * @return the list of {@link Topo}.
   *
   * @see #toposList
   * @see #setToposList(List)
   */
  public List<Topo> getToposList() {
    return this.toposList;
  }
  
  /**
   * Get the String representing the department code associated to {@link Topo}. Can't be empty and
   * size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @return the String representing the department code associated to {@link Topo}. Can't be empty
   *         and size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @see #departmentTopo
   * @see #setDepartmentTopo(String)
   */
  public String getDepartmentTopo() {
    return this.departmentTopo;
  }
  
  /**
   * Get the municipality associated to {@link Topo}. Can be empty and max size is 100 characters.
   *
   * @return the municipality associated to {@link Topo}. Can be empty and max size is 100 characters.
   *
   * @see #municipalityTopo
   * @see #setMunicipalityTopo(String)
   */
  public String getMunicipalityTopo() {
    return this.municipalityTopo;
  }
  
  /**
   * Get the String indicating if the {@link Topo} is available for a new borrow (Topo is borrowable
   * and not borrow at this time).
   *
   * @return String indicating if the {@link Topo} is available for a new borrow (Topo is borrowable
   *         and not borrow at this time).
   *
   * @see #available
   * @see #setAvailable(String)
   */
  public String getAvailable() {
    return this.available;
  }
  
  /**
   * Get the list of {@link Topo} owned by the user.
   *
   * @return the list of {@link Topo} owned by the user.
   *
   * @see #toposOwnerList
   * @see #setToposOwnerList(List)
   */
  public List<Topo> getToposOwnerList() {
    return this.toposOwnerList;
  }
  
  /**
   * Get the List of {@link Topo} borrowed by the user.
   *
   * @return the List of {@link Topo} borrowed by the user.
   *
   * @see #toposBorrowerList
   * @see #setToposBorrowerList(List)
   */
  public List<Topo> getToposBorrowerList() {
    return this.toposBorrowerList;
  }
  
  
  // ===== Setters =====
  
  /**
   * Set the String representing the department code associated to {@link Topo}. Can't be empty and
   * size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @param departmentTopo the String representing the department code associated to {@link Topo}.
   *        Can't be empty and size need to be 3 characters. Ex : "034", "030", "02A"
   *
   * @see #departmentTopo
   * @see #getDepartmentTopo()
   */
  public void setDepartmentTopo(final String departmentTopo) {
    this.departmentTopo = departmentTopo;
  }
  
  /**
   * Set the municipality associated to {@link Topo}. Can be empty and max size is 100 characters.
   *
   * @param municipalityTopo the municipality associated to {@link Topo}. Can be empty and max size is
   *        100 characters.
   *
   * @see #municipalityTopo
   * @see #getMunicipalityTopo()
   */
  public void setMunicipalityTopo(final String municipalityTopo) {
    this.municipalityTopo = municipalityTopo;
  }
  
  /**
   * Set the String indicating if the {@link Topo} is available for a new borrow (Topo is borrowable
   * and not borrow at this time).
   *
   * @param available the String indicating if the {@link Topo} is available for a new borrow (Topo is
   *        borrowable and not borrow at this time).
   *
   * @see #available
   * @see #getAvailable()
   */
  public void setAvailable(final String available) {
    this.available = available;
  }
  
  /**
   * Set the list of {@link Topo}.
   *
   * @param toposList the list of {@link Topo}.
   *
   * @see #toposList
   * @see #getToposList()
   */
  public void setToposList(final List<Topo> toposList) {
    this.toposList = toposList;
  }
  
  /**
   * Set the list of {@link Topo} owned by the user.
   *
   * @param toposOwnerList the list of {@link Topo} owned by the user.
   *
   * @see #toposOwnerList
   * @see #getToposOwnerList()
   */
  public void setToposOwnerList(final List<Topo> toposOwnerList) {
    this.toposOwnerList = toposOwnerList;
  }
  
  /**
   * Set the List of {@link Topo} borrowed by the user.
   *
   * @param toposBorrowerList the List of {@link Topo} borrowed by the user.
   *
   * @see #toposBorrowerList
   * @see #getToposBorrowerList()
   */
  public void setToposBorrowerList(final List<Topo> toposBorrowerList) {
    this.toposBorrowerList = toposBorrowerList;
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
   * Get a list of all topo.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doList() {
    
    try {
      this.toposList = this.managerFactory.getTopoManager().getAllToposList();
    } catch (TechnicalException | NotFoundException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Get a list of topo that match to the searching criteria.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doTopoSearching() {
    
    TopoSearch topoSearch;
    
    if (StringUtils.isAllEmpty(this.departmentTopo, this.municipalityTopo, this.available)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("TOpo department, topo munucipality, topo available = null");
      }
      topoSearch = null;
    }
    else {
      topoSearch = new TopoSearch();
      topoSearch.setDepartmentTopo(this.departmentTopo);
      topoSearch.setMunicipalityTopo(this.municipalityTopo);
      if (StringUtils.equals(this.available, "true")) {
        topoSearch.setAvailableTopo(true);
      }
      else {
        topoSearch.setAvailableTopo(false);
      }
    }
    
    try {
      this.toposList = this.managerFactory.getTopoManager().getSearchToposList(topoSearch);
    } catch (TechnicalException | FunctionalException | NotFoundException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
    
  }
  
  /**
   * Get the list of topos owned by the user and the list of topos borrowed by the user.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doMyToposList() {
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    
    if (user == null) {
      LOG.error("user null - my topos list failure");
      this.addActionError("Vous n'êtes plus identifié, l'affichage de vos topos n'a pu se faire. Merci de vous reconnecter.");
      return Action.ERROR;
    }
    
    try {
      this.toposOwnerList = this.managerFactory.getTopoManager().getOwnerToposList(user.getId());
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    } catch (NotFoundException exception) {
      this.toposOwnerList = null;
    }
    
    try {
      this.toposBorrowerList = this.managerFactory.getTopoManager().getBorrowerToposList(user.getId());
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    } catch (NotFoundException exception) {
      this.toposBorrowerList = null;
    }
    
    return Action.SUCCESS;
  }
}
