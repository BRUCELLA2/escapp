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

public class SiteAddAction extends ActionSupport implements ServletRequestAware {


    private static final long  serialVersionUID = -854809676971542803L;

    // ----- Input
    private String             siteName;
    private String             siteDepartment;
    private String             siteMunicipality;
    private String             siteDescription;

    private String             sectorName;
    private String             sectorDescription;

    private String             routeName;
    private String             routeGrade;
    private String             routeDescription;
    private Integer            routePointsNb;

    private Integer            lengthLength;
    private String             lengthGrade;
    private String             lengthDescription;
    private Integer            lengthPointsNb;

    // ----- Input / Output
    private Integer            siteId;
    private Integer            sectorId;
    private Integer            routeId;

    // ----- Output


    // ----- Struts Elements
    private HttpServletRequest servletRequest;

    // ----- Manager
    @Autowired
    private ManagerFactory     managerFactory;


    // ===== Getters =====
    public Integer getSiteId() {
        return this.siteId;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public String getSiteDepartment() {
        return this.siteDepartment;
    }

    public String getSiteMunicipality() {
        return this.siteMunicipality;
    }

    public String getSiteDescription() {
        return this.siteDescription;
    }

    public String getSectorName() {
        return this.sectorName;
    }

    public String getSectorDescription() {
        return this.sectorDescription;
    }

    public Integer getSectorId() {
        return this.sectorId;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public String getRouteGrade() {
        return this.routeGrade;
    }

    public String getRouteDescription() {
        return this.routeDescription;
    }

    public Integer getRoutePointsNb() {
        return this.routePointsNb;
    }

    public Integer getRouteId() {
        return this.routeId;
    }

    public Integer getLengthLength() {
        return this.lengthLength;
    }

    public String getLengthGrade() {
        return this.lengthGrade;
    }

    public String getLengthDescription() {
        return this.lengthDescription;
    }

    public Integer getLengthPointsNb() {
        return this.lengthPointsNb;
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

        Site vSite = new Site();
        vSite.setName(this.siteName);
        vSite.setDepartment(this.siteDepartment);
        vSite.setMunicipality(this.siteMunicipality);
        vSite.setDescription(this.siteDescription);

        try {
            this.managerFactory.getSiteManager().addSite(vSite);
            this.siteId = vSite.getId();
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

        Sector vSector = new Sector();
        vSector.setName(this.sectorName);
        vSector.setDescription(this.sectorDescription);
        vSector.setSiteId(this.siteId);

        try {
            this.managerFactory.getSectorManager().addSector(vSector);
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

        Route vRoute = new Route();
        vRoute.setName(this.routeName);
        vRoute.setGrade(this.routeGrade);
        vRoute.setDescription(this.routeDescription);
        vRoute.setPointsNb(this.routePointsNb);
        vRoute.setSectorId(this.sectorId);

        try {
            this.managerFactory.getRouteManager().addRoute(vRoute);
            this.routeId = vRoute.getId();
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

        Length vLength = new Length();
        vLength.setLength(this.lengthLength);
        vLength.setDescription(this.lengthDescription);
        vLength.setGrade(this.lengthGrade);
        vLength.setPointsNb(this.lengthPointsNb);
        vLength.setRouteId(this.routeId);

        try {
            this.managerFactory.getLengthManager().addLength(vLength);
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
        User vUser = (User) this.servletRequest.getSession().getAttribute("user");
        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, l'ajout n'a pu se faire. Merci de vous reconnecter.");
            return false;
        }

        List<RoleUser> roles = (List<RoleUser>) this.servletRequest.getSession().getAttribute("roles");
        boolean admin = false;
        for (RoleUser role : roles) {
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
