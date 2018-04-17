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

	/**
	 * id of the {@link Topo}.
	 * 
	 * @see #getId()
	 * @see #setId(Integer)
	 */
    private Integer id;

    
    /**
     * Name of the {@link Topo}.
     * Can't be empty and max size is 100 characters.
     * 
     * @see #getName()
     * @see #setName(String)
     */
    @NotEmpty(message = "Le topo doit avoir un nom")
    @Size(min = 1, max = 100, message = "Le nom doit contenir au maximum {max} caractères")
    private String name;

    
    /**
     * String representing the department code associated to {@link Topo}.
     * Can't be empty and size need to be 3 characters.
     * Ex : "034", "030", "02A"
     * 
     * @see #getDepartment()
     * @see #setDepartment(String)
     */
    @NotEmpty(message = "Le département doit être renseigné")
    @Size(min = 3, max = 3, message = "Le numéro du département doit être sur deux caractères")
    private String department;

    
    /**
     * Municipality associated to {@link Topo}.
     * Can be empty and max size is 100 characters.
     * 
     * @see #getMunicipality()
     * @see #setMunicipality(String)
     */
    @Size(min = 1, max = 100, message = "La commune doit contenir au maximum {max} caractères")
    private String municipality;

    
    /**
     * Description of the {@link Topo}.
     * Can be empty.
     * Max size is 10 000 characters.
     * 
     * @see #getDescription()
     * @see #setDescription(String)
     */
    @Size(max = 10000, message = "La description d'un topo doit contenir au maximum {max} caractères.")
    private String description;

    
    /**
     * Name of the file associated to the {@link Topo} (without extension)
     * Can't be empty.
     * Max size is 200 characters.
     * 
     * @see #getPdfFileName()
     * @see #setPdfFileName(String)
     */
    @NotEmpty(message = "Le nom du fichier pdf doit être renseigné")
    @Size(min = 1, max = 200, message = "Le nom du fichier doit avoir {max} caractères maximum")
    private String pdfFileName;

    
    /**
     * id of the user who owns the {@link Topo}.
     * Can't be null.
     * 
     * @see #getOwner()
     * @see #setOwner(Integer)
     */
    @NotNull(message = "Le propriétaire du topo doit être renseigné")
    private Integer owner;


    /**
     * id of the user who borrowed the {@link Topo}.
     * Can be null
     * 
     * @see #getBorrower()
     * @see #setBorrower(Integer)
     */
    private Integer borrower;

    
    /**
     * Boolean indicating if the {@link Topo} can be borrow.
     * Can't be null.
     * 
     * @see #getBorrowable()
     * @see #setBorrowable(Boolean)
     */
    @NotNull(message = "Il est nécessaire d'indiquer si le topo est empruntable ou non")
    private Boolean isBorrowable;


    /**
     * End date of borrow for this {@link Topo}.
     * Can be null.
     * 
     * @see #getEndDateBorrow()
     * @see #setEndDateBorrow(Date)
     */
    private Date endDateBorrow;


    // ===== Constructor =====
    
    
    /**
     * Constructor
     */
    public Topo(){

    }

    
    // ===== Getters =====

    /**
     * Get the id of the {@link Topo}.
     * 
     * @return the {@link Integer} id of the {@link Topo}.
     * 
     * @see #id
     * @see #setId(Integer)
     */
    public Integer getId() {
        return id;
    }
    
    
    /**
     * Get the name of the {@link Topo}.
     * 
     * @return the name of the {@link Topo}.
     * 			Can't be empty.
     * 			Max size is 100 characters.
     * 
     * @see #name
     * @see #setName(String)
     */
    public String getName() {
        return name;
    }

    
    /**
     * Get the {@link String} representing the department code associated to {@link Topo}.
     * 
     * @return the {@link String} representing the department code associated to {@link Topo}.
     * 			Can't be empty
     * 			Size need to be 3 characters.
     * 
     * @see #department
     * @see #setDepartment(String)
     */
    public String getDepartment() {
        return department;
    }

    
    /**
     * Get the municipality associated to {@link Topo}.
     * 
     * @return the municipality associated to {@link Topo}.
     * 		   Can be empty.
     * 		   Max size is 100 characters.
     * 
     * @see #municipality
     * @see #setMunicipality(String)
     */
    public String getMunicipality() {
        return municipality;
    }

    
    /**
     * Get the description of the {@link Topo}.
     * 
     * @return the description of the {@link Topo}.
     * 			Can be empty.
     * 			Max size is 10 000 characters.
     * 
     * @see #description
     * @see #setDescription(String)
     */
    public String getDescription() {
        return description;
    }

    
    /**
     * Get the name of the file associated to the {@link Topo} (without extension)
     * 
     * @return the name of the file associated to the {@link Topo} (without extension)
     *      	Can't be empty.
     * 			Max size is 200 characters.
     * 
     * @see #pdfFileName
     * @see #setPdfFileName(String)
     */
    public String getPdfFileName() {
        return pdfFileName;
    }

    
    /**
     * Get the id of the user who owns the {@link Topo}.
     * 
     * @return the {@link Integer} id of the user who owns the {@link Topo}.
     * 			Can't be null.
     * 
     * @see #owner
     * @see #setOwner(Integer)
     */
    public Integer getOwner() {
        return owner;
    }

    
    /**
     * Get the id of the user who borrowed the {@link Topo}.
     * 
     * @return the {@link Integer} id of the user who borrowed the {@link Topo}.
     * 			Can be null.	
     * 
     * @see #borrower
     * @see #setBorrower(Integer)
     */
    public Integer getBorrower() {
        return borrower;
    }

    
    /**
     * Indicate is the {@link Topo} is borrowable.
     * 
     * @return true if the {@link Topo} is borrowable, false otherwise.
     * 			Can't be null.
     * 
     * @see #isBorrowable
     * @see #setBorrowable(Boolean)
     */
    public Boolean getBorrowable() {
        return isBorrowable;
    }

    
    /**
     * Get the end date of borrow for this {@link Topo}.
     * 
     * @return the end date of borrow for this {@link Topo}.
     * 			Can be null.
     * 
     * @see #endDateBorrow
     * @see #setEndDateBorrow(Date)
     */
    public Date getEndDateBorrow() {
        return endDateBorrow;
    }
    

    // ===== Setters =====


    /**
     * Set the id of the {@link Topo}.
     * 
     * @param pId {@link Integer} id of the {@link Topo}.
     * 
     * @see #id
     * @see #getId()
     */
    public void setId(Integer pId) {
        this.id = pId;
    }

    
    /**
     * Set the name of the {@link Topo}.
     * 
     * @param pName the name of the {@link Topo}.
     * 				Can't be empty.
     * 				Max size is 100 characters.
     * 
     * @see #name
     * @see #getName()
     */
    public void setName(String pName) {
        this.name = pName;
    }

    
    /**
     * Set the {@link String} representing the department code associated to {@link Topo}.
     * 
     * @param pDepartment the {@link String} representing the department code associated to {@link Topo}.
     * 					  Can't be empty
     * 					  Size need to be 3 characters.
     * 					  Ex : "034", "030", "02A"
     * 
     * @see #department
     * @see #getDepartment()
     */
    public void setDepartment(String pDepartment) {
        this.department = pDepartment;
    }

    
    /**
     * Set the municipality associated to {@link Topo}.
     * 
     * @param pMunicipality the municipality associated to {@link Topo}.
     * 						Can be empty
     * 						Max size is 100 characters
     * 
     * @see #municipality
     * @see #getMunicipality()
     */
    public void setMunicipality(String pMunicipality) {
        this.municipality = pMunicipality;
    }

    
    /**
     * Set the description of the {@link Topo}. 
     * 
     * @param pDescription the description of the {@link Topo}. 
     * 					   Can be empty.
     * 					   Max size is 10 000 characters.
     * 
     * @see #description
     * @see #getDescription()
     */
    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    
    /**
     * Set the name of the file associated to the {@link Topo} (without extension)
     * 
     * @param pPdfFileName the name of the file associated to the {@link Topo} (without extension)
     * 						Can't be empty.
     * 						Max size is 200 characters.
     * 
     * @see #pdfFileName
     * @see #getPdfFileName()
     */
    public void setPdfFileName(String pPdfFileName) {
        this.pdfFileName = pPdfFileName;
    }

    
    /**
     * Set the id of the user who owns the {@link Topo}.
     * 
     * @param pOwner the id of the user who owns the {@link Topo}.
     * 				 Can't be null.
     * 
     * @see #owner
     * @see #getOwner()
     */
    public void setOwner(Integer pOwner) {
        this.owner = pOwner;
    }

    
    /**
     * Set the id of the user who borrowed the {@link Topo}.
     * 
     * @param pBorrower the id of the user who borrowed the {@link Topo}.
     * 					Can be null.
     * 
     * @see #borrower
     * @see #getBorrower()
     */
    public void setBorrower(Integer pBorrower) {
        this.borrower = pBorrower;
    }

    
    /**
     * Set if the {@link Topo} is borrowable.
     * 
     * @param pBorrowable true if the {@link Topo} is borrowable, false otherwise.
     * 					  Can't be null.
     * 
     * @see #isBorrowable
     * @see #setBorrowable(Boolean)
     */
    public void setBorrowable(Boolean pBorrowable) {
        isBorrowable = pBorrowable;
    }

    
    /**
     * Set the end date of borrow for this {@link Topo}.
     * 
     * @param pEndDateBorrow the end date of borrow for this {@link Topo}.
     * 						 Can be null;
     * 
     * @see #endDateBorrow
     * @see #getEndDateBorrow()
     */
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
