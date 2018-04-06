package fr.brucella.form.escapp.model.exceptions;


/**
 * Exception class raised when an technical erreur occured
 *
 * @author BRUCELLA2
 */
public class TechnicalException extends Exception {

    /**
     * Constructs a new TechnicalException with the specified detail message.
     *
     * @param pMessage the detail message.
     */
    public TechnicalException(String pMessage){
        super(pMessage);
    }

    /**
     * Constructs a new TechnicalException with the specified detail message and cause.
     *
     * @param pMessage the detail message.
     * @param pCause the cause
     */
    public TechnicalException(String pMessage, Throwable pCause){
        super(pMessage, pCause);
    }
}
