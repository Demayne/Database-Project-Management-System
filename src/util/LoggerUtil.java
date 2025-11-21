package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerUtil provides centralized logging functionality for the application.
 * Implements file and console logging with proper formatting and log levels.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger("PoiseDMS");
    
    /**
     * Logs an informational message.
     * 
     * @param message Message to log
     */
    public static void info(String message) { logger.info(message); }
    
    /**
     * Logs a warning message.
     * 
     * @param message Message to log
     */
    public static void warning(String message) { logger.warn(message); }
    
    /**
     * Logs an error message.
     * 
     * @param message Message to log
     */
    public static void error(String message) { logger.error(message); }
    
    /**
     * Logs an error with exception details.
     * 
     * @param message   Message to log
     * @param throwable Exception to log
     */
    public static void error(String message, Throwable throwable) { logger.error(message, throwable); }
    
    /**
     * Logs a debug message.
     * 
     * @param message Message to log
     */
    public static void debug(String message) { logger.debug(message); }
    
    /**
     * Custom formatter for log messages.
     */
    // SLF4J managed by logback; no custom formatter required here.
}
