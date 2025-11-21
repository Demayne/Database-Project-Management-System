package exception;

/**
 * Exception thrown when database operations fail.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class DatabaseException extends PoiseDMSException {
    
    private static final long serialVersionUID = 1L;
    
    public DatabaseException(String message) {
        super(message, null, "PDMS-DB-001");
    }
    
    public DatabaseException(String message, Throwable cause) {
        super(message, cause, "PDMS-DB-001");
    }
}
