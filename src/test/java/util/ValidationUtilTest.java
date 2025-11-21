package util;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ValidationUtil class.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
class ValidationUtilTest {
    
    @Test
    @DisplayName("Valid email should pass validation")
    void testValidEmail() {
        assertTrue(ValidationUtil.isValidEmail("test@example.com"));
        assertTrue(ValidationUtil.isValidEmail("user.name@domain.co.za"));
        assertTrue(ValidationUtil.isValidEmail("info@company.org"));
    }
    
    @Test
    @DisplayName("Invalid email should fail validation")
    void testInvalidEmail() {
        assertFalse(ValidationUtil.isValidEmail("invalidemail"));
        assertFalse(ValidationUtil.isValidEmail("@example.com"));
        assertFalse(ValidationUtil.isValidEmail("test@"));
        assertFalse(ValidationUtil.isValidEmail(null));
    }
    
    @Test
    @DisplayName("Valid phone number should pass validation")
    void testValidPhone() {
        assertTrue(ValidationUtil.isValidPhone("0123456789"));
        assertTrue(ValidationUtil.isValidPhone("01234567890123"));
    }
    
    @Test
    @DisplayName("Invalid phone number should fail validation")
    void testInvalidPhone() {
        assertFalse(ValidationUtil.isValidPhone("123"));
        assertFalse(ValidationUtil.isValidPhone("abc1234567"));
        assertFalse(ValidationUtil.isValidPhone(null));
    }
    
    @Test
    @DisplayName("Numeric validation should work correctly")
    void testNumeric() {
        assertTrue(ValidationUtil.isNumeric("12345"));
        assertFalse(ValidationUtil.isNumeric("12a45"));
        assertFalse(ValidationUtil.isNumeric(null));
    }
    
    @Test
    @DisplayName("ERF number validation should work correctly")
    void testERFValidation() {
        assertTrue(ValidationUtil.isValidERF("ERF1234"));
        assertTrue(ValidationUtil.isValidERF("erf5678"));
        assertFalse(ValidationUtil.isValidERF("ABC1234"));
        assertFalse(ValidationUtil.isValidERF(null));
    }
    
    @Test
    @DisplayName("Future date validation should work correctly")
    void testFutureDateValidation() {
        assertTrue(ValidationUtil.isValidFutureDate("2026-12-31"));
        assertFalse(ValidationUtil.isValidFutureDate("2020-01-01"));
        assertFalse(ValidationUtil.isValidFutureDate("invalid-date"));
        assertFalse(ValidationUtil.isValidFutureDate(null));
    }
    
    @Test
    @DisplayName("Positive number validation should work correctly")
    void testPositiveNumber() {
        assertTrue(ValidationUtil.isPositive(100.50));
        assertTrue(ValidationUtil.isPositive(0.01));
        assertFalse(ValidationUtil.isPositive(0));
        assertFalse(ValidationUtil.isPositive(-10));
    }
    
    @Test
    @DisplayName("Non-negative number validation should work correctly")
    void testNonNegativeNumber() {
        assertTrue(ValidationUtil.isNonNegative(100.50));
        assertTrue(ValidationUtil.isNonNegative(0));
        assertFalse(ValidationUtil.isNonNegative(-10));
    }
    
    @Test
    @DisplayName("Address validation should work correctly")
    void testAddressValidation() {
        assertTrue(ValidationUtil.isValidAddress("123 Main St, City, Country"));
        assertFalse(ValidationUtil.isValidAddress("Short"));
        assertFalse(ValidationUtil.isValidAddress("No comma here"));
        assertFalse(ValidationUtil.isValidAddress(null));
    }
    
    @Test
    @DisplayName("Input sanitization should work correctly")
    void testInputSanitization() {
        assertEquals("test", ValidationUtil.sanitizeInput("test"));
        assertEquals("test''s", ValidationUtil.sanitizeInput("test's"));
        assertEquals("", ValidationUtil.sanitizeInput(null));
    }
}
