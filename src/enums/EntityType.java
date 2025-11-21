package enums;

/**
 * Enumeration for entity types in the system.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public enum EntityType {
    ARCHITECT("Architect", "ARC"),
    CONTRACTOR("Contractor", "CON"),
    CUSTOMER("Customer", "CUS"),
    PROJECT("Project", "PRJ");
    
    private final String displayName;
    private final String prefix;
    
    EntityType(String displayName, String prefix) {
        this.displayName = displayName;
        this.prefix = prefix;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public String getTableName() {
        return displayName.toLowerCase();
    }
}
