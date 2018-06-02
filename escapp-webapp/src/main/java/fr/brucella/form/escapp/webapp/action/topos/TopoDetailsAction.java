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

/**
 * Actions related to topo details.
 *
 * @author BRUCELLA2
 */
public class TopoDetailsAction extends ActionSupport implements ServletRequestAware {
  
  /**
   * Serial ID.
   */
  private static final long           serialVersionUID = 6745173617900744958L;
  
  /**
   * Topo Details Action logger.
   */
  private static final Log            LOG              = LogFactory.getLog(TopoDetailsAction.class);
  
  
  // ----- Input
  
  /**
   * Id of the {@link Topo}.
   *
   * @see #getId()
   * @see #setId(Integer)
   */
  private Integer                     id;
  
  /**
   * Name of the topo pdf file.
   *
   * @see #getFileName()
   * @see #setFileName(String)
   */
  private String                      fileName;
  
  /**
   * Number of days of borrow.
   *
   * @see #getNbDays()
   * @see #setNbDays(Integer)
   */
  private Integer                     nbDays;
  
  /**
   * String indicating if the {@link Topo} can be borrow.
   *
   * @see #getBorrowable()
   * @see #setBorrowable(String)
   */
  private String                      borrowable;
  
  // ----- Output
  
  /**
   * The {@link Topo} detailed in the web page.
   *
   * @see #getTopo()
   * @see #setTopo(Topo)
   */
  private Topo                        topo;
  
  /**
   * Number of {@link Comment} for the {@link Topo}.
   *
   * @see #getNbCommentsTopo()
   * @see #setNbCommentsTopo(Integer)
   */
  private Integer                     nbCommentsTopo;
  
  /**
   * {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User} who write the
   * {@link Comment} for the {@link Topo}.
   *
   * @see #getCommentsTopoList()
   * @see #setCommentsTopoList(List)
   */
  private List<Pair<Comment, String>> commentsTopoList;
  
  /**
   * Owner {@link User} of the {@link Topo}.
   *
   * @see #getOwnerTopo()
   * @see #setOwnerTopo(User)
   */
  private User                        ownerTopo;
  
  /**
   * {@link User} who borrow the {@link Topo}.
   *
   * @see #getBorrowerTopo()
   * @see #setBorrowerTopo(User)
   */
  private User                        borrowerTopo;
  
  
  // ----- Manager
  
  /**
   * The Manager Factory Manager Factory allow to get and set business managers.
   */
  @Autowired
  private ManagerFactory              managerFactory;
  
  // ----- Struts Elements
  /**
   * An input stream of bytes.
   *
   * @see #getInputStream()
   */
  private InputStream                 inputStream;
  
  /**
   * The Http Servlet Request. Used to get session informations.
   *
   * @see HttpServletRequest
   * @see #setServletRequest(HttpServletRequest)
   */
  private HttpServletRequest          servletRequest;
  
  
  // ===== Getters =====
  
  /**
   * Get the id of the {@link Topo}.
   *
   * @return the id of the {@link Topo}
   *
   * @see #id
   * @see #setId(Integer)
   */
  public Integer getId() {
    return this.id;
  }
  
  /**
   * Get the {@link Topo} detailed in the web page.
   *
   * @return the {@link Topo} detailed in the web page
   *
   * @see #topo
   * @see #setTopo(Topo)
   */
  public Topo getTopo() {
    return this.topo;
  }
  
  /**
   * Get the number of {@link Comment} for the {@link Topo}.
   *
   * @return the number of {@link Comment} for the {@link Topo}
   *
   * @see #nbCommentsTopo
   * @see #setNbCommentsTopo(Integer)
   */
  public Integer getNbCommentsTopo() {
    return this.nbCommentsTopo;
  }
  
  /**
   * Get the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User} who
   * write the {@link Comment} for the {@link Topo}.
   *
   * @return the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User}
   *         who write the {@link Comment} for the {@link Topo}.
   *
   * @see #commentsTopoList
   * @see #setCommentsTopoList(List)
   */
  public List<Pair<Comment, String>> getCommentsTopoList() {
    return this.commentsTopoList;
  }
  
  /**
   * Get the Owner {@link User} of the {@link Topo}.
   *
   * @return the Owner {@link User} of the {@link Topo}
   *
   * @see #ownerTopo
   * @see #setOwnerTopo(User)
   */
  public User getOwnerTopo() {
    return this.ownerTopo;
  }
  
  /**
   * Get the {@link User} who borrow the {@link Topo}.
   *
   * @return the {@link User} who borrow the {@link Topo}
   *
   * @see #borrowerTopo
   * @see #setBorrowerTopo(User)
   */
  public User getBorrowerTopo() {
    return this.borrowerTopo;
  }
  
  /**
   * Get an input stream of bytes.
   *
   * @return An input stream of bytes
   *
   * @see #inputStream
   */
  public InputStream getInputStream() {
    return this.inputStream;
  }
  
  /**
   * Get the name of the topo pdf file.
   *
   * @return the name of the topo pdf file.
   *
   * @see #fileName
   * @see #setFileName(String)
   */
  public String getFileName() {
    return this.fileName;
  }
  
  /**
   * Get the Number of days of borrow.
   *
   * @return the Number of days of borrow
   *
   * @see #nbDays
   * @see #setNbDays(Integer)
   */
  public Integer getNbDays() {
    return this.nbDays;
  }
  
  /**
   * Get the string indicating if the {@link Topo} can be borrow.
   *
   * @return the string indicating if the {@link Topo} can be borrow.
   *
   * @see #borrowable
   * @see #setBorrowable(String)
   */
  public String getBorrowable() {
    return this.borrowable;
  }
  
  // ===== Setters =====
  
  /**
   * Set the id of the {@link Topo}.
   *
   * @param id the id of the {@link Topo}
   *
   * @see #id
   * @see #getId()
   */
  public void setId(final Integer id) {
    this.id = id;
  }
  
  /**
   * Set the name of the topo pdf file.
   *
   * @param fileName the name of the topo pdf file.
   *
   * @see #fileName
   * @see #getFileName()
   */
  public void setFileName(final String fileName) {
    this.fileName = fileName;
  }
  
  /**
   * Set the number of days of borrow.
   *
   * @param nbDays the number of days of borrow
   *
   * @see #nbDays
   * @see #getNbDays()
   */
  public void setNbDays(final Integer nbDays) {
    this.nbDays = nbDays;
  }
  
  /**
   * Set the string indicating if the {@link Topo} can be borrow.
   *
   * @param borrowable the string indicating if the {@link Topo} can be borrow.
   *
   * @see #borrowable
   * @see #getBorrowable()
   */
  public void setBorrowable(final String borrowable) {
    this.borrowable = borrowable;
  }
  
  /**
   * Set the {@link Topo} detailed in the web page.
   *
   * @param topo the {@link Topo} detailed in the web page
   *
   * @see #topo
   * @see #getTopo()
   */
  public void setTopo(final Topo topo) {
    this.topo = topo;
  }
  
  /**
   * Set the number of {@link Comment} for the {@link Topo}.
   *
   * @param nbCommentsTopo the number of {@link Comment} for the {@link Topo}
   *
   * @see #nbCommentsTopo
   * @see #getNbCommentsTopo()
   */
  public void setNbCommentsTopo(final Integer nbCommentsTopo) {
    this.nbCommentsTopo = nbCommentsTopo;
  }
  
  /**
   * Set the {@link List} of {@link Pair} of {@link Comment} with the login of the {@link User} who
   * write the {@link Comment} for the {@link Topo}.
   *
   * @param commentsTopoList the {@link List} of {@link Pair} of {@link Comment} with the login of the
   *        {@link User} who write the {@link Comment} for the {@link Topo}.
   *
   * @see #commentsTopoList
   * @see #getCommentsTopoList()
   */
  public void setCommentsTopoList(final List<Pair<Comment, String>> commentsTopoList) {
    this.commentsTopoList = commentsTopoList;
  }
  
  /**
   * Set the Owner {@link User} of the {@link Topo}.
   *
   * @param ownerTopo
   *
   * @see #ownerTopo
   * @see #getOwnerTopo()
   */
  public void setOwnerTopo(final User ownerTopo) {
    this.ownerTopo = ownerTopo;
  }
  
  /**
   * Set the {@link User} who borrow the {@link Topo}.
   *
   * @param borrowerTopo the {@link User} who borrow the {@link Topo}
   *
   * @see #borrowerTopo
   * @see #getBorrowerTopo()
   */
  public void setBorrowerTopo(final User borrowerTopo) {
    this.borrowerTopo = borrowerTopo;
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
   * Get Topo details with comments.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doTopoDetails() {
    
    if (this.id == null) {
      LOG.error("Topo id null - topo details failure");
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
    } catch (TechnicalException | NotFoundException | FunctionalException exception) {
      TopoDetailsAction.LOG.debug(exception.getMessage());
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    try {
      this.commentsTopoList = this.managerFactory.getCommentManager().getCommentsTopoListWithLogin(this.id, "ASC");
      this.nbCommentsTopo = this.commentsTopoList.size();
    } catch (TechnicalException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    } catch (NotFoundException exception) {
      this.nbCommentsTopo = 0;
      this.addActionMessage("Aucun commentaire");
    }
    
    return Action.SUCCESS;
  }
  
  /**
   * Topo pdf downloading.
   *
   * @return SUCCESS
   */
  @Override
  public String execute() throws FileNotFoundException {
    
    final ServletContext servletContext = ServletActionContext.getServletContext();
    final String path = servletContext.getRealPath("/WEB-INF/files/" + this.getFileName());
    final File fileToDownLoad = new File(path);
    
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
      LOG.error("Topo id null - topo details failure");
      this.addActionError("L'identifiant du topo à réserver est incorrect (Identifiant vide) - Echec de la réservation");
      return Action.ERROR;
    }
    
    if (this.nbDays == null) {
      this.doTopoDetails();
      return Action.INPUT;
    }
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    if (user == null) {
      LOG.error("user null - topo booking failure");
      this.addActionError("Vous n'êtes plus identifié, la réservation du topo n'a pu se faire. Merci de vous reconnecter.");
      return Action.ERROR;
    }
    
    try {
      this.topo = this.managerFactory.getTopoManager().getTopoById(this.id);
      this.topo = this.managerFactory.getTopoManager().borrowTopo(this.topo, this.nbDays, user);
      this.borrowerTopo = user;
    } catch (TechnicalException | NotFoundException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
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
      LOG.error("Topo id null - topo change borrowable status failure");
      this.addActionError("L'identifiant du topo à modifier est incorrect (Identifiant vide) - Echec de la modification");
      return Action.ERROR;
    }
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    if (user == null) {
      LOG.error("user null - topo change borrowable status failure");
      this.addActionError("Vous n'êtes plus identifié, la modification du topo n'a pu se faire. Merci de vous reconnecter.");
      return Action.ERROR;
    }
    
    try {
      this.topo = this.managerFactory.getTopoManager().getTopoById(this.id);
      this.topo.setBorrowable(StringUtils.equals(this.borrowable, "true"));
      this.managerFactory.getTopoManager().modifyTopo(this.topo, user);
    } catch (TechnicalException | NotFoundException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
    
  }
  
  /**
   * Delete the topo.
   *
   * @return ERROR if error occurred SUCCESS otherwise
   */
  public String doDelete() {
    
    if (this.id == null) {
      LOG.error("Topo id null - topo delete failure");
      this.addActionError("L'identifiant du topo à supprimer est incorrect (Identifiant vide) - Echec de la modification");
      return Action.ERROR;
    }
    
    final User user = (User) this.servletRequest.getSession().getAttribute("user");
    if (user == null) {
      LOG.error("user null - topo delete failure");
      this.addActionError("Vous n'êtes plus identifié, la suppression du topo n'a pu se faire. Merci de vous reconnecter.");
      return Action.ERROR;
    }
    
    try {
      
      this.topo = this.managerFactory.getTopoManager().getTopoById(this.id);
      
      final ServletContext servletContext = ServletActionContext.getServletContext();
      final String path = servletContext.getRealPath("/WEB-INF/files/");
      final File topoPdf = new File(path, this.topo.getPdfFileName());
      FileUtils.deleteQuietly(topoPdf);
      
      this.managerFactory.getTopoManager().deleteTopo(this.id, user);
      
    } catch (TechnicalException | NotFoundException | FunctionalException exception) {
      this.addActionError(exception.getMessage());
      LOG.error(exception.getMessage());
      return Action.ERROR;
    }
    
    return Action.SUCCESS;
  }
}
