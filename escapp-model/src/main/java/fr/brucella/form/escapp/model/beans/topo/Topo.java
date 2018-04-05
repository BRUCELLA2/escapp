package fr.brucella.form.escapp.model.beans.topo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Business object which represents a Topo
 *
 * @author BRUCELLA2
 */
public class Topo {

    // @TODO check messages and write it in resource bundle

    private Integer id;

    @NotEmpty(message = "Le topo doit avoir un nom")
    @Size(min = 1, max = 100, message = "Le nom doit contenir au maximum {max} caractères")
    private String name;

    @NotEmpty(message = "Le département doit être renseigné")
    @Size(min = 2, max = 2, message = "Le numéro du département doit être sur deux caractères")
    private String department;

    @Size(min = 1, max = 100, message = "La commune doit contenir au maximum {max} caractères")
    private String municipality;

    @Size(max = 10000, message = "La description d'un topo doit contenir au maximum {max} caractères.")
    private String description;

    @NotEmpty(message = "Le nom du fichier pdf doit être renseigné")
    @Size(min = 1, max = 200, message = "Le nom du fichier doit avoir {max} caractères maximum")
    private String pdfFileName;

    @NotNull(message = "Le propriétaire du topo doit être renseigné")
    private Integer owner;


    private Integer borrower;

    @NotNull(message = "Il est nécessaire d'indiquer si le topo est empruntable ou non")
    private Boolean isBorrowable;


    private Date endDateBorrow;


    // ===== Constructor =====
    /**
     * Constructor
     */
    public Topo(){

    }

    // ===== Getters =====

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getDescription() {
        return description;
    }

    public String getPdfFileName() {
        return pdfFileName;
    }

    public Integer getOwner() {
        return owner;
    }

    public Integer getBorrower() {
        return borrower;
    }

    public Boolean getBorrowable() {
        return isBorrowable;
    }

    public Date getEndDateBorrow() {
        return endDateBorrow;
    }

    // ===== Setters =====


    public void setId(Integer pId) {
        this.id = pId;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public void setDepartment(String pDepartment) {
        this.department = pDepartment;
    }

    public void setMunicipality(String pMunicipality) {
        this.municipality = pMunicipality;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    public void setPdfFileName(String pPdfFileName) {
        this.pdfFileName = pPdfFileName;
    }

    public void setOwner(Integer pOwner) {
        this.owner = pOwner;
    }

    public void setBorrower(Integer pBorrower) {
        this.borrower = pBorrower;
    }

    public void setBorrowable(Boolean pBorrowable) {
        isBorrowable = pBorrowable;
    }

    public void setEndDateBorrow(Date pEndDateBorrow) {
        this.endDateBorrow = pEndDateBorrow;
    }

    // ===== Methods =====

    // @TODO make a better toString method

    @Override
    public String toString() {
        return "Topo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", municipality='" + municipality + '\'' +
                ", description='" + description + '\'' +
                ", pdfFileName='" + pdfFileName + '\'' +
                ", owner=" + owner +
                ", borrower=" + borrower +
                ", isBorrowable=" + isBorrowable +
                ", endDateBorrow=" + endDateBorrow +
                '}';
    }
}
