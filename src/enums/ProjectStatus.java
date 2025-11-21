package enums;

/**
 * Enumeration for project status.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public enum ProjectStatus {
    ACTIVE("Active", "Project is currently active"),
    COMPLETED("Completed", "Project has been completed"),
    OVERDUE("Overdue", "Project is past its deadline"),
    CANCELLED("Cancelled", "Project has been cancelled");
    
    private final String displayName;
    private final String description;
    
    ProjectStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
}
