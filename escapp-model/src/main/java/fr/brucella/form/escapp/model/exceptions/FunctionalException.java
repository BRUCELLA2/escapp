package fr.brucella.form.escapp.model.exceptions;


/**
 * Exception class raised when an Functional erreur occured
 *
 * @author BRUCELLA2
 */
public class FunctionalException extends Exception {
  
  /**
   * Constructs a new FunctionalException with the specified detail message.
   *
   * @param pMessage the detail message.
   */
  public FunctionalException(String pMessage) {
    super(pMessage);
  }
  
  /**
   * Constructs a new FunctionalException with the specified detail message and cause.
   *
   * @param pMessage the detail message.
   * @param pCause the cause
   */
  public FunctionalException(String pMessage, Throwable pCause) {
    super(pMessage, pCause);
  }
}
