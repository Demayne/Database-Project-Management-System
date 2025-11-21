package constants;

/**
 * Application-wide constants for PoiseDMS.
 * Centralizes all constant values for better maintainability.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public final class AppConstants {
    
    // Private constructor to prevent instantiation
    private AppConstants() {
        throw new AssertionError("Cannot instantiate constants class");
    }
    
    // Application Info
    public static final String APP_NAME = "PoiseDMS";
    public static final String APP_VERSION = "2.0.0";
    public static final String APP_AUTHOR = "Demayne Govender";
    
    // Database Constants
    public static final String DB_NAME = "PoiseDMS";
    public static final int DEFAULT_POOL_SIZE = 10;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    
    // Entity ID Prefixes
    public static final String ARCHITECT_PREFIX = "ARC";
    public static final String CONTRACTOR_PREFIX = "CON";
    public static final String CUSTOMER_PREFIX = "CUS";
    public static final String PROJECT_PREFIX = "PRJ";
    public static final String ERF_PREFIX = "ERF";
    
    // Validation Patterns
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String PHONE_REGEX = "^[0-9]{10,15}$";
    public static final String NUMERIC_REGEX = "^\\d+$";
    public static final String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9 ]+$";
    
    // Date Format
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    // File Paths
    public static final String CONFIG_FILE = "database.properties";
    public static final String LOG_DIRECTORY = "logs";
    public static final String LOG_FILE_PATTERN = "poisedms_%g.log";
    
    // Log Settings
    public static final int LOG_FILE_SIZE = 5000000; // 5MB
    public static final int LOG_FILE_COUNT = 5;
    
    // Messages
    public static final String MSG_SUCCESS = "✅ Operation completed successfully";
    public static final String MSG_ERROR = "❌ Operation failed";
    public static final String MSG_WARNING = "⚠️  Warning";
    public static final String MSG_INFO = "ℹ️  Information";
    
    // Menu Display
    public static final String SEPARATOR = "=".repeat(60);
    public static final String WELCOME_BANNER = 
        "\n" + SEPARATOR + "\n" +
        " ".repeat(10) + "POISE DATABASE MANAGEMENT SYSTEM\n" +
        " ".repeat(12) + "Project Management Solution v" + APP_VERSION + "\n" +
        " ".repeat(18) + "by " + APP_AUTHOR + "\n" +
        SEPARATOR + "\n";
    
    // SQL Queries (Common)
    public static final String SQL_SELECT_ALL_PROJECTS = "SELECT * FROM project";
    public static final String SQL_SELECT_INCOMPLETE = "SELECT * FROM project WHERE Finalised = 'No'";
    public static final String SQL_SELECT_OVERDUE = 
        "SELECT * FROM project WHERE Deadline < CURDATE() AND (Finalised IS NULL OR Finalised = 'No')";
    
    // Error Codes
    public static final String ERR_DB_CONNECTION = "PDMS-DB-001";
    public static final String ERR_VALIDATION = "PDMS-VAL-001";
    public static final String ERR_NOT_FOUND = "PDMS-ENT-404";
    public static final String ERR_DUPLICATE = "PDMS-DUP-001";
    
    // Finalised Status
    public static final String STATUS_FINALISED_YES = "Yes";
    public static final String STATUS_FINALISED_NO = "No";
}
