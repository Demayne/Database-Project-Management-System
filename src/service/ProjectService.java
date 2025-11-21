package service;

import exception.DatabaseException;
import exception.ValidationException;
import model.Project;
import util.LoggerUtil;
import util.ValidationUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for Project-related business logic.
 * Separates business logic from presentation and data access layers.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class ProjectService {
    
    /**
     * Validates project data before database operations.
     * 
     * @param project Project to validate
     * @throws ValidationException if validation fails
     */
    public void validateProject(Project project) throws ValidationException {
        if (project == null) {
            throw new ValidationException("Project cannot be null");
        }
        
        String projectNumber = project.getProjectNumber();
        if (projectNumber == null || projectNumber.trim().isEmpty()) {
            throw new ValidationException("Project number is required");
        }
        if (!ValidationUtil.isNumeric(projectNumber)) {
            throw new ValidationException("Project number must be numeric");
        }
        
        if (project.getTotalFee() < 0) {
            throw new ValidationException("Total fee cannot be negative");
        }
        
        if (project.getTotalPaid() < 0) {
            throw new ValidationException("Total paid cannot be negative");
        }
        
        if (project.getTotalPaid() > project.getTotalFee()) {
            throw new ValidationException("Total paid cannot exceed total fee");
        }
        
        if (project.getDeadline() == null) {
            throw new ValidationException("Deadline is required");
        }
        
        LoggerUtil.debug("Project validation successful for project: " + project.getProjectNumber());
    }
    
    /**
     * Checks if a project exists by number.
     * 
     * @param connection Database connection
     * @param projectNumber Project number to check
     * @return true if exists, false otherwise
     * @throws DatabaseException if database error occurs
     */
    public boolean projectExists(Connection connection, int projectNumber) throws DatabaseException {
        String query = "SELECT COUNT(*) FROM project WHERE ProjectNumber = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, projectNumber);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LoggerUtil.error("Database error checking project existence", e);
            throw new DatabaseException("Failed to check project existence", e);
        }
        
        return false;
    }
    
    /**
     * Retrieves all overdue and incomplete projects.
     * 
     * @param connection Database connection
     * @return List of overdue projects
     * @throws DatabaseException if database error occurs
     */
    public List<Project> getOverdueProjects(Connection connection) throws DatabaseException {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM project WHERE Deadline < CURDATE() AND (Finalised IS NULL OR Finalised = 'No')";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                projects.add(mapResultSetToProject(rs));
            }
            
            LoggerUtil.info("Retrieved " + projects.size() + " overdue projects");
        } catch (SQLException e) {
            LoggerUtil.error("Failed to retrieve overdue projects", e);
            throw new DatabaseException("Failed to retrieve overdue projects", e);
        }
        
        return projects;
    }
    
    /**
     * Maps a ResultSet row to a Project object.
     * 
     * @param rs ResultSet containing project data
     * @return Project object
     * @throws SQLException if database error occurs
     */
    private Project mapResultSetToProject(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setProjectNumber(String.valueOf(rs.getInt("ProjectNumber")));
        project.setProjectName(rs.getString("ProjectName"));
        project.setBuildingType(rs.getString("BuildingType"));
        project.setPhysicalAddress(rs.getString("PhysicalAddress"));
        project.setErfNumber(rs.getString("ERFNumber"));
        project.setTotalFee(rs.getDouble("TotalFee"));
        project.setTotalPaid(rs.getDouble("TotalPaid"));
        Date date = rs.getDate("Deadline");
        if (date != null) {
            project.setDeadline(date.toLocalDate());
        }
        project.setFinalised(rs.getString("Finalised"));
        return project;
    }
    
    /**
     * Calculates outstanding amount for a project.
     * 
     * @param totalFee Total project fee
     * @param totalPaid Amount already paid
     * @return Outstanding amount
     */
    public double calculateOutstanding(double totalFee, double totalPaid) {
        return Math.max(0, totalFee - totalPaid);
    }
}
