# API and Integration Guide

## Overview

PoiseDMS provides a robust Java API for managing construction projects. While currently a CLI application, the architecture supports future REST API integration.

---

## Core Components

### 1. Database Connection Management

#### ConnectionPool

**Package**: `util`

**Description**: Manages HikariCP connection pool for optimal database performance.

**Key Methods**:
```java
// Get database connection
Connection conn = ConnectionPool.getConnection();

// Get pool statistics
String stats = ConnectionPool.getPoolStats();

// Close pool on shutdown
ConnectionPool.closePool();
```

**Configuration**:
```properties
db.pool.size=20
db.pool.timeout=30000
```

---

### 2. Project Management

#### ProjectManager

**Package**: (default)

**Description**: Core business logic for project operations.

#### View Operations

**View All Projects**
```java
ProjectManager pm = new ProjectManager();
pm.viewAllProjects(connection);
```

**View Incomplete Projects**
```java
pm.viewIncompleteProjects(connection);
```

**View Overdue Projects**
```java
pm.viewOverdueProjects(connection);
```

**Search Projects**
```java
Scanner scanner = new Scanner(System.in);
pm.searchProjects(connection, scanner);
```

#### Modification Operations

**Add New Project**
```java
pm.addNewProject(connection, scanner);
```

**Update Project**
```java
pm.updateProject(connection, scanner);
```

**Delete Project**
```java
pm.deleteProject(connection, scanner);
```

**Finalize Project**
```java
pm.finalizeProject(connection, scanner);
```

---

### 3. Data Models

#### Project

**Package**: `model`

**Fields**:
- `projectNumber` (int): Unique project identifier
- `projectName` (String): Project name
- `buildingType` (String): Type of building
- `physicalAddress` (String): Project location
- `erfNumber` (String): ERF number (must start with "ERF")
- `totalFee` (double): Total project cost
- `totalPaid` (double): Amount paid
- `deadline` (Date): Project deadline
- `finalised` (String): Completion status
- `architectID` (String): Associated architect
- `contractorID` (String): Associated contractor
- `customerID` (String): Associated customer

**Example**:
```java
Project project = new Project();
project.setProjectNumber(1001);
project.setProjectName("Luxury Villa");
project.setBuildingType("Residential");
project.setTotalFee(2500000.00);
project.setTotalPaid(1500000.00);
project.setDeadline(Date.valueOf("2025-12-31"));
```

#### Person (Base Class)

**Package**: `model`

**Subclasses**: Architect, Contractor, Customer

**Common Fields**:
- `id` (String): Unique identifier
- `firstName` (String): First name
- `surname` (String): Last name
- `telephone` (String): Phone number
- `email` (String): Email address
- `physicalAddress` (String): Physical address

---

### 4. Validation Utilities

#### ValidationUtil

**Package**: `util`

**Methods**:

```java
// Email validation
boolean isValid = ValidationUtil.isValidEmail("user@example.com");

// Phone validation (10-15 digits)
boolean isValid = ValidationUtil.isValidPhone("1234567890");

// Numeric validation
boolean isValid = ValidationUtil.isNumeric("12345");

// Alphanumeric validation
boolean isValid = ValidationUtil.isAlphanumeric("Project123");

// ERF number validation
boolean isValid = ValidationUtil.isValidERF("ERF12345");

// Future date validation
boolean isValid = ValidationUtil.isValidFutureDate("2025-12-31");

// Decimal validation
boolean isValid = ValidationUtil.isValidDecimal("1234.56");

// Not empty validation
boolean isValid = ValidationUtil.isNotEmpty("Some text");
```

---

### 5. Exception Handling

#### Custom Exceptions

**PoiseDMSException** (Base)
```java
throw new PoiseDMSException("General error", cause, "PDMS-000");
```

**DatabaseException**
```java
throw new DatabaseException("Database error", cause, "PDMS-DB-001");
```

**ValidationException**
```java
throw new ValidationException("Invalid input data");
```

**EntityNotFoundException**
```java
throw new EntityNotFoundException("Project not found", "Project", "1001");
```

#### Error Codes

| Code | Description |
|------|-------------|
| PDMS-000 | General application error |
| PDMS-DB-001 | Database connection error |
| PDMS-DB-002 | Query execution error |
| PDMS-VAL-001 | Validation error |
| PDMS-ENT-001 | Entity not found |

---

### 6. Logging

#### LoggerUtil

**Package**: `util`

**Usage**:
```java
// Information logging
LoggerUtil.info("Application started");

// Debug logging
LoggerUtil.debug("Debug details: " + details);

// Warning logging
LoggerUtil.warning("Potential issue detected");

// Error logging
LoggerUtil.error("Error occurred", exception);
```

**Configuration**: `src/logback.xml`

---

## Database Schema

### Tables

#### project
- **ProjectNumber** (INT, PK): Unique project ID
- **ProjectName** (VARCHAR): Project name
- **BuildingType** (VARCHAR): Building type
- **PhysicalAddress** (VARCHAR): Location
- **ERFNumber** (VARCHAR): ERF number
- **TotalFee** (DECIMAL): Total cost
- **TotalPaid** (DECIMAL): Amount paid
- **Deadline** (DATE): Completion date
- **Finalised** (VARCHAR): Status
- **ArchitectID** (FK): Reference to architect
- **ContractorID** (FK): Reference to contractor
- **CustomerID** (FK): Reference to customer

#### architect / contractor / customer
- **ID** (VARCHAR, PK): Unique identifier
- **FirstName** (VARCHAR): First name
- **Surname** (VARCHAR): Last name
- **Telephone** (VARCHAR): Phone
- **Email** (VARCHAR, UNIQUE): Email
- **PhysicalAddress** (VARCHAR): Address

### Stored Procedures

**GenerateInvoice**
```sql
CALL GenerateInvoice(project_number);
```
Generates invoice for projects with outstanding amounts.

**UpdateProjectStatus**
```sql
CALL UpdateProjectStatus(project_number, new_status);
```
Updates project finalization status.

### Views

**project_summary**
```sql
SELECT * FROM project_summary;
```
Provides comprehensive project overview with calculated fields.

**overdue_projects**
```sql
SELECT * FROM overdue_projects;
```
Lists all overdue and incomplete projects.

---

## Integration Examples

### Example 1: Check Database Connectivity

```java
import java.sql.Connection;
import util.ConnectionPool;
import util.LoggerUtil;

public class ConnectivityTest {
    public static void main(String[] args) {
        try (Connection conn = ConnectionPool.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                LoggerUtil.info("Database connected successfully");
                System.out.println("✅ Database connection OK");
            }
        } catch (Exception e) {
            LoggerUtil.error("Connection failed", e);
            System.err.println("❌ Connection failed: " + e.getMessage());
        }
    }
}
```

### Example 2: Retrieve Project Data

```java
import java.sql.*;
import util.ConnectionPool;

public class ProjectRetrieval {
    public static void main(String[] args) {
        String query = "SELECT * FROM project WHERE ProjectNumber = ?";
        
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, 1001);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Project: " + rs.getString("ProjectName"));
                    System.out.println("Status: " + rs.getString("Finalised"));
                    System.out.println("Deadline: " + rs.getDate("Deadline"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### Example 3: Input Validation

```java
import util.ValidationUtil;
import exception.ValidationException;

public class InputValidationExample {
    public static void validateUserInput(String email, String phone) 
            throws ValidationException {
        
        if (!ValidationUtil.isValidEmail(email)) {
            throw new ValidationException("Invalid email format");
        }
        
        if (!ValidationUtil.isValidPhone(phone)) {
            throw new ValidationException("Invalid phone format");
        }
        
        System.out.println("✅ All inputs valid");
    }
}
```

### Example 4: Transaction Management

```java
import java.sql.*;
import util.ConnectionPool;

public class TransactionExample {
    public static void updateProjectWithTransaction(int projectNumber, 
                                                    String newName) {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            
            String query = "UPDATE project SET ProjectName = ? WHERE ProjectNumber = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newName);
                pstmt.setInt(2, projectNumber);
                
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    conn.commit();
                    System.out.println("✅ Update successful");
                } else {
                    conn.rollback();
                    System.out.println("❌ No rows updated");
                }
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

---

## Future REST API Design

### Proposed Endpoints (v3.0)

#### Projects

```
GET    /api/v1/projects              - List all projects
GET    /api/v1/projects/{id}         - Get project by ID
POST   /api/v1/projects              - Create new project
PUT    /api/v1/projects/{id}         - Update project
DELETE /api/v1/projects/{id}         - Delete project
GET    /api/v1/projects/overdue      - Get overdue projects
GET    /api/v1/projects/incomplete   - Get incomplete projects
POST   /api/v1/projects/{id}/finalize - Finalize project
```

#### Entities

```
GET    /api/v1/architects            - List architects
POST   /api/v1/architects            - Create architect
GET    /api/v1/contractors           - List contractors
POST   /api/v1/contractors           - Create contractor
GET    /api/v1/customers             - List customers
POST   /api/v1/customers             - Create customer
```

#### Response Format

```json
{
  "status": "success",
  "data": {
    "projectNumber": 1001,
    "projectName": "Luxury Villa",
    "buildingType": "Residential",
    "totalFee": 2500000.00,
    "totalPaid": 1500000.00,
    "outstanding": 1000000.00,
    "deadline": "2025-12-31",
    "finalised": "No"
  },
  "timestamp": "2025-11-21T10:30:00Z"
}
```

---

## Performance Optimization

### Connection Pool Tuning

```properties
# For high-load scenarios
db.pool.size=50
db.pool.timeout=20000
hikari.minimumIdle=10
hikari.maximumPoolSize=50
```

### Query Optimization

1. **Use indexes** for frequently queried columns
2. **Limit result sets** with pagination
3. **Use prepared statements** to prevent SQL injection and improve performance
4. **Batch operations** for bulk inserts/updates

### Caching Strategy (Future)

- Cache frequently accessed project data
- Implement Redis for distributed caching
- Use Ehcache for local caching

---

## Testing

### Unit Testing

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import util.ValidationUtil;

class ValidationUtilTest {
    @Test
    void testEmailValidation() {
        assertTrue(ValidationUtil.isValidEmail("test@example.com"));
        assertFalse(ValidationUtil.isValidEmail("invalid-email"));
    }
    
    @Test
    void testPhoneValidation() {
        assertTrue(ValidationUtil.isValidPhone("1234567890"));
        assertFalse(ValidationUtil.isValidPhone("123"));
    }
}
```

### Integration Testing

```java
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import util.ConnectionPool;

class DatabaseIntegrationTest {
    @Test
    void testDatabaseConnection() {
        assertDoesNotThrow(() -> {
            try (Connection conn = ConnectionPool.getConnection()) {
                assertNotNull(conn);
                assertFalse(conn.isClosed());
            }
        });
    }
}
```

---

## Support and Documentation

- **Javadoc**: See `docs/` directory
- **README**: See `README.md`
- **Architecture**: See `ARCHITECTURE.md`
- **Deployment**: See `PRODUCTION_DEPLOYMENT.md`
- **Security**: See `SECURITY.md`

---

**Version**: 2.0.0  
**Last Updated**: November 21, 2025  
**Author**: Demayne Govender
