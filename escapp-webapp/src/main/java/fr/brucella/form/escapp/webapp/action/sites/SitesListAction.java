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

public class SitesListAction extends ActionSupport {

    private static final long serialVersionUID = -8977464808054592705L;
    // ----- Input
    private String            departmentSite;
    private String            municipalitySite;
    private String            minGrade;
    private String            maxGrade;

    // ----- Output
    private List<Site>        sitesList;

    // ----- Manager
    @Autowired
    private ManagerFactory    managerFactory;

    // ----- Logger
    private Log               log              = LogFactory.getLog(SitesListAction.class);

    // ===== Getters =====
    public List<Site> getSitesList() {
        return this.sitesList;
    }

    public String getDepartmentSite() {
        return this.departmentSite;
    }

    public String getMunicipalitySite() {
        return this.municipalitySite;
    }

    public String getMinGrade() {
        return this.minGrade;
    }

    public String getMaxGrade() {
        return this.maxGrade;
    }

    // ===== Setters =====
    public void setDepartmentSite(String pDepartmentSite) {
        this.departmentSite = pDepartmentSite;
    }

    public void setMunicipalitySite(String pMunicipalitySite) {
        this.municipalitySite = pMunicipalitySite;
    }

    public void setMinGrade(String pMinGrade) {
        this.minGrade = pMinGrade;
    }

    public void setMaxGrade(String pMaxGrade) {
        this.maxGrade = pMaxGrade;
    }


    // ===== Methods =====

    /**
     * Get a list of all sites
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
     * Get a list of sites that match to the searching criteria
     *
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doSiteSearching() {

        SiteSearch vSiteSearch;

        if (StringUtils.isAllEmpty(this.departmentSite, this.municipalitySite, this.minGrade, this.maxGrade)) {
            vSiteSearch = null;
        }
        else {
            vSiteSearch = new SiteSearch();
            vSiteSearch.setDepartmentSite(this.departmentSite);
            vSiteSearch.setMunicipalitySite(this.municipalitySite);
            vSiteSearch.setMinGradeRoute(this.minGrade);
            vSiteSearch.setMaxGradeRoute(this.maxGrade);
        }

        try {
            this.sitesList = this.managerFactory.getSiteManager().getSearchSitesList(vSiteSearch);
        } catch (TechnicalException | NotFoundException | FunctionalException pException) {
            this.log.debug(pException.getStackTrace());
            this.log.error(pException.getMessage());
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }
}
