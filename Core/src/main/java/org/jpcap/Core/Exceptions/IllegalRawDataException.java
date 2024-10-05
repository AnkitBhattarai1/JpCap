package org.jpcap.Core.Exceptions;

/**
 * IllegalRawDataException
 */
public class IllegalRawDataException extends Exception {
     /** */
  public IllegalRawDataException() {
    super();
  }

  /** @param message message */
  public IllegalRawDataException(String message) {
    super(message);
  }

  /**
   * @param message message
   * @param cause cause
   */
  public IllegalRawDataException(String message, Throwable cause) {
    super(message, cause);
  }

  /** @param cause cause */
  public IllegalRawDataException(Throwable cause) {
    super(cause);
  }

    
}
