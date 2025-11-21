package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * ValidationUtil provides comprehensive input validation for the application.
 * Ensures data integrity and security through robust validation methods.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class ValidationUtil {
    
    // Validation patterns
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[0-9]{10,15}$");
    
    private static final Pattern NUMERIC_PATTERN = 
        Pattern.compile("^\\d+$");
    
    private static final Pattern ALPHANUMERIC_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9 ]+$");
    
    private static final DateTimeFormatter DATE_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Validates email address format.
     * 
     * @param email Email address to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }
    
    /**
     * Validates phone number format (10-15 digits).
     * 
     * @param phone Phone number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone.trim()).matches();
    }
    
    /**
     * Validates if string contains only numeric characters.
     * 
     * @param value String to validate
     * @return true if numeric, false otherwise
     */
    public static boolean isNumeric(String value) {
        return value != null && NUMERIC_PATTERN.matcher(value.trim()).matches();
    }
    
    /**
     * Validates if string is alphanumeric.
     * 
     * @param value String to validate
     * @return true if alphanumeric, false otherwise
     */
    public static boolean isAlphanumeric(String value) {
        return value != null && ALPHANUMERIC_PATTERN.matcher(value.trim()).matches();
    }
    
    /**
     * Validates ERF number format (must start with "ERF").
     * 
     * @param erfNumber ERF number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidERF(String erfNumber) {
        return erfNumber != null && erfNumber.trim().toUpperCase().startsWith("ERF");
    }
    
    /**
     * Validates date format and ensures it's a future date.
     * 
     * @param dateString Date string to validate
     * @return true if valid future date, false otherwise
     */
    public static boolean isValidFutureDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return false;
        }
        
        try {
            LocalDate date = LocalDate.parse(dateString.trim(), DATE_FORMAT);
            return !date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * Validates date format.
     * 
     * @param dateString Date string to validate
     * @return true if valid date format, false otherwise
     */
    public static boolean isValidDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return false;
        }
        
        try {
            LocalDate.parse(dateString.trim(), DATE_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * Validates that a string is not null or empty.
     * 
     * @param value String to validate
     * @return true if not null/empty, false otherwise
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
    /**
     * Validates that a number is positive.
     * 
     * @param value Number to validate
     * @return true if positive, false otherwise
     */
    public static boolean isPositive(double value) {
        return value > 0;
    }
    
    /**
     * Validates that a number is non-negative.
     * 
     * @param value Number to validate
     * @return true if non-negative, false otherwise
     */
    public static boolean isNonNegative(double value) {
        return value >= 0;
    }
    
    /**
     * Validates address format (must contain comma).
     * 
     * @param address Address to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidAddress(String address) {
        return address != null && address.trim().length() > 5 && address.contains(",");
    }
    
    /**
     * Sanitizes user input to prevent SQL injection.
     * 
     * @param input Input string to sanitize
     * @return Sanitized string
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        return input.trim()
                    .replace("'", "''")
                    .replace("\"", "\\\"")
                    .replace("\\", "\\\\");
    }
}
