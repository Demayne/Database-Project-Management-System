package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import util.LoggerUtil;

/**
 * DatabaseConfig handles loading and managing database configuration from properties file.
 * This ensures secure and flexible configuration management for production environments.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class DatabaseConfig {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "database.properties";
    private static final String ENV_URL = "DB_URL";
    private static final String ENV_USER = "DB_USERNAME";
    private static final String ENV_PASSWORD = "DB_PASSWORD";
    private static final String ENV_DRIVER = "DB_DRIVER";
    private static final String ENV_POOL_SIZE = "DB_POOL_SIZE";
    private static final String ENV_POOL_TIMEOUT = "DB_POOL_TIMEOUT";

    static {
        loadProperties();
        overrideWithEnv();
        validateSecurity();
    }
    
    /**
     * Loads database configuration from properties file.
     * Falls back to default values if file is not found.
     */
    private static void loadProperties() {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                properties.load(input);
                LoggerUtil.info("Database properties loaded from file: " + CONFIG_FILE);
            } else {
                LoggerUtil.warning("Configuration file '" + CONFIG_FILE + "' not found. Falling back to defaults.");
                setDefaultProperties();
            }
        } catch (IOException e) {
            LoggerUtil.error("Error loading configuration file. Falling back to defaults.", e);
            setDefaultProperties();
        }
    }
    
    /**
     * Sets default properties for development environment.
     */
    private static void setDefaultProperties() {
        properties.setProperty("db.url", "jdbc:mysql://localhost:3306/PoiseDMS");
        properties.setProperty("db.username", "CHANGE_ME");
        properties.setProperty("db.password", "CHANGE_ME_SECURELY");
        properties.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("db.pool.size", "10");
        properties.setProperty("db.pool.timeout", "30000");
    }

    /**
     * Overrides loaded properties with environment variables when present.
     * This enables twelve-factor style configuration for production deployments.
     */
    private static void overrideWithEnv() {
        overrideIfPresent("db.url", ENV_URL);
        overrideIfPresent("db.username", ENV_USER);
        overrideIfPresent("db.password", ENV_PASSWORD);
        overrideIfPresent("db.driver", ENV_DRIVER);
        overrideIfPresent("db.pool.size", ENV_POOL_SIZE);
        overrideIfPresent("db.pool.timeout", ENV_POOL_TIMEOUT);
    }

    private static void overrideIfPresent(String key, String envName) {
        String value = System.getenv(envName);
        if (value != null && !value.trim().isEmpty()) {
            properties.setProperty(key, value.trim());
            if (!key.equals("db.password")) { // do not log passwords
                LoggerUtil.info("Config override via ENV: " + key + " <- " + envName);
            } else {
                LoggerUtil.info("Config override via ENV: db.password <- " + envName + " (hidden)");
            }
        }
    }

    /**
     * Basic security validation to warn if insecure placeholder credentials are still in use.
     */
    private static void validateSecurity() {
        String user = properties.getProperty("db.username", "");
        String pwd = properties.getProperty("db.password", "");
        if ("CHANGE_ME".equals(user) || "CHANGE_ME_SECURELY".equals(pwd)) {
            LoggerUtil.warning("Database credentials are using insecure placeholders. Set environment variables or update properties file before production.");
        }
    }

    /**
     * Allows runtime reload (e.g., after updating environment variables in long-running process).
     */
    public static synchronized void reload() {
        loadProperties();
        overrideWithEnv();
        validateSecurity();
    }
    
    /**
     * Gets database URL.
     * @return Database connection URL
     */
    public static String getUrl() {
        return properties.getProperty("db.url");
    }
    
    /**
     * Gets database username.
     * @return Database username
     */
    public static String getUsername() {
        return properties.getProperty("db.username");
    }
    
    /**
     * Gets database password.
     * @return Database password
     */
    public static String getPassword() {
        return properties.getProperty("db.password");
    }
    
    /**
     * Gets database driver class name.
     * @return JDBC driver class name
     */
    public static String getDriver() {
        return properties.getProperty("db.driver");
    }
    
    /**
     * Gets connection pool size.
     * @return Maximum pool size
     */
    public static int getPoolSize() {
        return Integer.parseInt(properties.getProperty("db.pool.size", "10"));
    }
    
    /**
     * Gets connection timeout in milliseconds.
     * @return Connection timeout
     */
    public static int getConnectionTimeout() {
        return Integer.parseInt(properties.getProperty("db.pool.timeout", "30000"));
    }
}
