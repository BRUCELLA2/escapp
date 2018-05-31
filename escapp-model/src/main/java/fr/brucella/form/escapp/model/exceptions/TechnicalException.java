package fr.brucella.form.escapp.model.exceptions;


/**
 * Exception class raised when an technical erreur occured.
 *
 * @author BRUCELLA2
 */
public class TechnicalException extends Exception {
  
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -2565533049963103680L;
  
  /**
   * Constructs a new TechnicalException with the specified detail message.
   *
   * @param message the detail message.
   */
  public TechnicalException(final String message) {
    super(message);
  }
  
  /**
   * Constructs a new TechnicalException with the specified detail message and cause.
   *
   * @param message the detail message.
   * @param cause the cause
   */
  public TechnicalException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
