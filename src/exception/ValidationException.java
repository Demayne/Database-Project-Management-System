package exception;

/**
 * Exception thrown when input validation fails.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class ValidationException extends PoiseDMSException {
    
    private static final long serialVersionUID = 1L;
    private final String fieldName;
    
    public ValidationException(String message) {
        super(message, null, "PDMS-VAL-001");
        this.fieldName = null;
    }
    
    public ValidationException(String message, String fieldName) {
        super(message, null, "PDMS-VAL-001");
        this.fieldName = fieldName;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    @Override
    public String toString() {
        if (fieldName != null) {
            return String.format("[%s] Field '%s': %s", getErrorCode(), fieldName, getMessage());
        }
        return super.toString();
    }
}
