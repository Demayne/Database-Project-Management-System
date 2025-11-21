package exception;

/**
 * Base exception class for all PoiseDMS custom exceptions.
 * Provides a foundation for application-specific exception handling.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class PoiseDMSException extends Exception {
    
    private static final long serialVersionUID = 1L;
    private final String errorCode;
    
    /**
     * Constructs a new PoiseDMS exception with specified message.
     * 
     * @param message The error message
     */
    public PoiseDMSException(String message) {
        super(message);
        this.errorCode = "PDMS-000";
    }
    
    /**
     * Constructs a new PoiseDMS exception with message and cause.
     * 
     * @param message The error message
     * @param cause The cause of the exception
     */
    public PoiseDMSException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "PDMS-000";
    }
    
    /**
     * Constructs a new PoiseDMS exception with message, cause, and error code.
     * 
     * @param message The error message
     * @param cause The cause of the exception
     * @param errorCode The application-specific error code
     */
    public PoiseDMSException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    /**
     * Gets the error code associated with this exception.
     * 
     * @return The error code
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s", errorCode, getMessage());
    }
}
