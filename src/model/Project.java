package model;

import java.time.LocalDate;

/**
 * Entity class representing a Project.
 * Follows JavaBean conventions for enterprise applications.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class Project {
    private String projectNumber;
    private String projectName;
    private LocalDate deadline;
    private String buildingType;
    private String physicalAddress;
    private String erfNumber;
    private double totalFee;
    private double totalPaid;
    private String architectId;
    private String contractorId;
    private String customerId;
    private String finalised;
    private LocalDate completionDate;
    
    // Constructors
    public Project() {}
    
    public Project(String projectNumber, String projectName, LocalDate deadline, 
                   String buildingType, String physicalAddress, String erfNumber,
                   double totalFee, double totalPaid, String architectId, 
                   String contractorId, String customerId) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.deadline = deadline;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.totalPaid = totalPaid;
        this.architectId = architectId;
        this.contractorId = contractorId;
        this.customerId = customerId;
        this.finalised = "No";
    }
    
    // Getters and Setters
    public String getProjectNumber() {
        return projectNumber;
    }
    
    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public LocalDate getDeadline() {
        return deadline;
    }
    
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    
    public String getBuildingType() {
        return buildingType;
    }
    
    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }
    
    public String getPhysicalAddress() {
        return physicalAddress;
    }
    
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
    
    public String getErfNumber() {
        return erfNumber;
    }
    
    public void setErfNumber(String erfNumber) {
        this.erfNumber = erfNumber;
    }
    
    public double getTotalFee() {
        return totalFee;
    }
    
    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }
    
    public double getTotalPaid() {
        return totalPaid;
    }
    
    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }
    
    public String getArchitectId() {
        return architectId;
    }
    
    public void setArchitectId(String architectId) {
        this.architectId = architectId;
    }
    
    public String getContractorId() {
        return contractorId;
    }
    
    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getFinalised() {
        return finalised;
    }
    
    public void setFinalised(String finalised) {
        this.finalised = finalised;
    }
    
    public LocalDate getCompletionDate() {
        return completionDate;
    }
    
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
    
    // Business methods
    public double getOutstandingAmount() {
        return totalFee - totalPaid;
    }
    
    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadline) && !"Yes".equals(finalised);
    }
    
    public boolean isCompleted() {
        return "Yes".equals(finalised);
    }
    
    @Override
    public String toString() {
        return String.format("Project[%s - %s, Deadline: %s, Status: %s]", 
                           projectNumber, projectName, deadline, finalised);
    }
}
