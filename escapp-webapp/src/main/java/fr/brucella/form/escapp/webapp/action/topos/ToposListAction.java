package fr.brucella.form.escapp.webapp.action.topos;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.TopoSearch;

public class ToposListAction extends ActionSupport{

	// ----- Input
	private String departmentTopo;
	private String municipalityTopo;
	private String available;
	
	// ----- Output
	private List<Topo> toposList;
	
	// ----- Manager
	@Autowired
	private ManagerFactory managerFactory;
	
	// ===== Getters =====
	public List<Topo> getToposList(){
		return toposList;
	}
	
	public String getDepartmentTopo() {
		return departmentTopo;
	}
	
	public String getMunicipalityTopo() {
		return municipalityTopo;
	}
	
	public String getAvailable() {
		return available;
	}
	
	// ===== Setters =====
	public void setDepartmentTopo(String pDepartmentTopo) {
		this.departmentTopo = pDepartmentTopo;
	}
	
	public void setMunicipalityTopo(String pMunicipalityTopo) {
		this.municipalityTopo = pMunicipalityTopo;
	}
	
	public void setAvailable(String pAvailable) {
		this.available = pAvailable;
	}
	
	// ===== Methods =====
	public String doList() {
		
		try {
			toposList = managerFactory.getTopoManager().getAllToposList();
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
	
	public String doTopoSearching() {
		
		TopoSearch vTopoSearch;
		
		if(StringUtils.isAllEmpty(departmentTopo, municipalityTopo, available)) {
			vTopoSearch = null;
		} else {
			vTopoSearch = new TopoSearch();
			vTopoSearch.setDepartmentTopo(departmentTopo);
			vTopoSearch.setMunicipalityTopo(municipalityTopo);
			if(StringUtils.equals(available, "true")){
				vTopoSearch.setAvailableTopo(true);
			}else {
				vTopoSearch.setAvailableTopo(false);
			}
		}
		
		try {
			toposList = managerFactory.getTopoManager().getSearchToposList(vTopoSearch);
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
