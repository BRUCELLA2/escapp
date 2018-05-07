package fr.brucella.form.escapp.webapp.action.sites;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.SiteSearch;

public class SitesListAction extends ActionSupport{

	// ----- Input
	private String departmentSite;
	private String municipalitySite;
	private String minGrade;
	private String maxGrade;
	
	// ----- Output
	private List<Site> sitesList;
	
	// ----- Manager
	@Autowired
	private ManagerFactory managerFactory;
	
	
	// ===== Getters =====
	public  List<Site> getSitesList(){
		return sitesList;
	}	
	
	public String getDepartmentSite() {
		return departmentSite;
	}
	
	public String getMunicipalitySite() {
		return municipalitySite;
	}
	
	public String getMinGrade() {
		return minGrade;
	}
	
	public String getMaxGrade() {
		return maxGrade;
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
	public String doList() {
		
		try {
			sitesList = managerFactory.getSiteManager().getAllSitesList();
		} catch (TechnicalException pException) {
	         this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (NotFoundException pException) {
	         this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (FunctionalException pException) {
	         this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}
		return ActionSupport.SUCCESS;		
	}
	
	public String doSiteSearching() {
		
	  SiteSearch vSiteSearch;
	  
	  if(StringUtils.isAllEmpty(departmentSite, municipalitySite, minGrade, maxGrade)) {
	    vSiteSearch = null;
	  } else {
	    vSiteSearch = new SiteSearch();
        vSiteSearch.setDepartmentSite(departmentSite);
        vSiteSearch.setMunicipalitySite(municipalitySite);
        vSiteSearch.setMinGradeRoute(minGrade);
        vSiteSearch.setMaxGradeRoute(maxGrade);
	  }
		
		try {
			sitesList = managerFactory.getSiteManager().getSearchSitesList(vSiteSearch);
		} catch (TechnicalException pException) {
	         this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (NotFoundException pException) {
	         this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		} catch (FunctionalException pException) {
	         this.addActionError(pException.getMessage());
			return ActionSupport.ERROR;
		}
		return ActionSupport.SUCCESS;		
	}
}
