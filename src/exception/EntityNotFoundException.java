package exception;

/**
 * Exception thrown when a requested entity is not found.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class EntityNotFoundException extends PoiseDMSException {
    
    private static final long serialVersionUID = 1L;
    private final String entityType;
    private final String entityId;
    
    public EntityNotFoundException(String entityType, String entityId) {
        super(String.format("%s with ID '%s' not found", entityType, entityId), null, "PDMS-ENT-404");
        this.entityType = entityType;
        this.entityId = entityId;
    }
    
    public String getEntityType() {
        return entityType;
    }
    
    public String getEntityId() {
        return entityId;
    }
}
