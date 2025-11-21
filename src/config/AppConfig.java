package config;

import util.LoggerUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Application configuration manager.
 * Centralizes all application-level configuration settings.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class AppConfig {
    
    private static final Properties properties = new Properties();
    private static boolean initialized = false;
    
    static {
        loadProperties();
    }
    
    /**
     * Loads application properties from classpath.
     */
    private static void loadProperties() {
        if (initialized) {
            return;
        }
        
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                LoggerUtil.warning("application.properties not found, using defaults");
                setDefaults();
            } else {
                properties.load(input);
                LoggerUtil.info("Application configuration loaded successfully");
            }
            initialized = true;
        } catch (IOException e) {
            LoggerUtil.error("Failed to load application properties", e);
            setDefaults();
        }
    }
    
    /**
     * Sets default configuration values.
     */
    private static void setDefaults() {
        properties.setProperty("app.name", "PoiseDMS");
        properties.setProperty("app.version", "2.0.0");
        properties.setProperty("app.environment", "production");
        properties.setProperty("log.level", "INFO");
        properties.setProperty("date.format", "yyyy-MM-dd");
        properties.setProperty("pagination.size", "50");
    }
    
    /**
     * Gets a configuration property value.
     * 
     * @param key Property key
     * @return Property value or null if not found
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Gets a configuration property with default value.
     * 
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Gets application name.
     * 
     * @return Application name
     */
    public static String getAppName() {
        return getProperty("app.name", "PoiseDMS");
    }
    
    /**
     * Gets application version.
     * 
     * @return Application version
     */
    public static String getAppVersion() {
        return getProperty("app.version", "2.0.0");
    }
    
    /**
     * Gets current environment.
     * 
     * @return Environment name (development, staging, production)
     */
    public static String getEnvironment() {
        return getProperty("app.environment", "production");
    }
    
    /**
     * Checks if running in production environment.
     * 
     * @return true if production, false otherwise
     */
    public static boolean isProduction() {
        return "production".equalsIgnoreCase(getEnvironment());
    }
    
    /**
     * Gets date format pattern.
     * 
     * @return Date format pattern
     */
    public static String getDateFormat() {
        return getProperty("date.format", "yyyy-MM-dd");
    }
    
    /**
     * Gets pagination size.
     * 
     * @return Number of items per page
     */
    public static int getPaginationSize() {
        try {
            return Integer.parseInt(getProperty("pagination.size", "50"));
        } catch (NumberFormatException e) {
            return 50;
        }
    }
}
