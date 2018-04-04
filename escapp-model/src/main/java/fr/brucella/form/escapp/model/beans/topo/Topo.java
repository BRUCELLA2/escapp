package fr.brucella.form.escapp.model.beans.topo;

import java.util.Date;

/**
 * Business object which represents a Topo
 *
 * @author BRUCELLA2
 */
public class Topo {
    // @TODO add JSR 380 validation tags

    private Integer id;
    private String name;
    private String department;
    private String municipality;
    private String description;
    private String pdfFileName;
    private Integer owner;
    private Integer borrower;
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
