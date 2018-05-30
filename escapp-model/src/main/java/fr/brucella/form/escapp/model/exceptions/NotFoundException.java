package fr.brucella.form.escapp.model.exceptions;


/**
 * Exception class raised when an business object is not found.
 *
 * @author BRUCELLA2
 */
public class NotFoundException extends Exception {
  
  /**
   * Constructs a new NotFoundException with the specified detail message.
   *
   * @param pMessage the detail message.
   */
  public NotFoundException(String pMessage) {
    super(pMessage);
  }
  
  /**
   * Constructs a new NotFoundException with the specified detail message and cause.
   *
   * @param pMessage the detail message.
   * @param pCause the cause
   */
  public NotFoundException(String pMessage, Throwable pCause) {
    super(pMessage, pCause);
  }
}
