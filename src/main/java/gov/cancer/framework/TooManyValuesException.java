package gov.cancer.framework;

/**
 * Custom Exception to throw when user encounters more values than the method can handle
 */
public class TooManyValuesException extends RuntimeException {
  public TooManyValuesException (String msg)
  {
    super (msg);
  }
}
