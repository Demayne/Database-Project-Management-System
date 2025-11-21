package enums;

/**
 * Enumeration for building types.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public enum BuildingType {
    HOUSE("House"),
    APARTMENT("Apartment"),
    COMMERCIAL("Commercial"),
    INDUSTRIAL("Industrial"),
    RESIDENTIAL("Residential"),
    MIXED_USE("Mixed Use");
    
    private final String displayName;
    
    BuildingType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static BuildingType fromString(String text) {
        for (BuildingType type : BuildingType.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}
