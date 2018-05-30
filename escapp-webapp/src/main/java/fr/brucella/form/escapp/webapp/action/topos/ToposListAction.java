package fr.brucella.form.escapp.webapp.action.topos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

public class ToposListAction extends ActionSupport implements ServletRequestAware {

    private static final long  serialVersionUID = 1573862908235940140L;

    // ----- Input
    private String             departmentTopo;
    private String             municipalityTopo;
    private String             available;



    // ----- Output
    private List<Topo>         toposList;
    private List<Topo>         toposOwnerList;
    private List<Topo>         toposBorrowerList;

    // ----- Manager
    @Autowired
    private ManagerFactory     managerFactory;

    // ----- Struts Elements
    private HttpServletRequest servletRequest;

    // ===== Getters =====
    public List<Topo> getToposList() {
        return this.toposList;
    }

    public String getDepartmentTopo() {
        return this.departmentTopo;
    }

    public String getMunicipalityTopo() {
        return this.municipalityTopo;
    }

    public String getAvailable() {
        return this.available;
    }

    public List<Topo> getToposOwnerList() {
        return this.toposOwnerList;
    }

    public List<Topo> getToposBorrowerList() {
        return this.toposBorrowerList;
    }



    @Override
    public void setServletRequest(HttpServletRequest pServletRequest) {
        this.servletRequest = pServletRequest;
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

    /**
     * Get a list of all topo
     *
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doList() {

        try {
            this.toposList = this.managerFactory.getTopoManager().getAllToposList();
        } catch (TechnicalException | NotFoundException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }

    /**
     * Get a list of topo that match to the searching criteria
     *
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doTopoSearching() {

        TopoSearch vTopoSearch;

        if (StringUtils.isAllEmpty(this.departmentTopo, this.municipalityTopo, this.available)) {
            vTopoSearch = null;
        }
        else {
            vTopoSearch = new TopoSearch();
            vTopoSearch.setDepartmentTopo(this.departmentTopo);
            vTopoSearch.setMunicipalityTopo(this.municipalityTopo);
            if (StringUtils.equals(this.available, "true")) {
                vTopoSearch.setAvailableTopo(true);
            }
            else {
                vTopoSearch.setAvailableTopo(false);
            }
        }

        try {
            this.toposList = this.managerFactory.getTopoManager().getSearchToposList(vTopoSearch);
        } catch (TechnicalException | FunctionalException | NotFoundException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        }

        return Action.SUCCESS;

    }

    /**
     * Get the list of topos owned by the user and the list of topos borrowed by the user
     *
     * @return ERROR if error occurred SUCCESS otherwise
     */
    public String doMyToposList() {

        User vUser = (User) this.servletRequest.getSession().getAttribute("user");

        if (vUser == null) {
            this.addActionError("Vous n'êtes plus identifié, l'affichage de vos topos n'a pu se faire. Merci de vous reconnecter.");
            return Action.ERROR;
        }

        try {
            this.toposOwnerList = this.managerFactory.getTopoManager().getOwnerToposList(vUser.getId());
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        } catch (NotFoundException pException) {
            this.toposOwnerList = null;
        }

        try {
            this.toposBorrowerList = this.managerFactory.getTopoManager().getBorrowerToposList(vUser.getId());
        } catch (TechnicalException | FunctionalException pException) {
            this.addActionError(pException.getMessage());
            return Action.ERROR;
        } catch (NotFoundException pException) {
            this.toposBorrowerList = null;
        }

        return Action.SUCCESS;
    }
}
