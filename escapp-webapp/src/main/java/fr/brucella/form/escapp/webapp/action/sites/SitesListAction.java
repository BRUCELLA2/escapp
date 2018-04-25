package fr.brucella.form.escapp.webapp.action.sites;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

public class SitesListAction extends ActionSupport{

	// ----- Input
	
	// ----- Output
	private List<Site> sitesList;
	
	// ----- Manager
	@Autowired
	private ManagerFactory managerFactory;
	
	// ===== Getters / Setters =====
	public  List<Site> getSitesList(){
		return sitesList;
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
}
