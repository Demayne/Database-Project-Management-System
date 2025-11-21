-- =====================================================
-- PoiseDMS Database Schema
-- Project Management System Database
-- Author: Demayne Govender
-- Version: 2.0
-- Date: November 21, 2025
-- =====================================================

-- Create database if not exists
CREATE DATABASE IF NOT EXISTS PoiseDMS 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE PoiseDMS;

-- =====================================================
-- Table: architect
-- Stores information about architects
-- =====================================================
CREATE TABLE IF NOT EXISTS architect (
    ArchitectID VARCHAR(10) PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    Surname VARCHAR(50) NOT NULL,
    Telephone VARCHAR(15) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    PhysicalAddress VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_architect_email (Email),
    INDEX idx_architect_name (Surname, FirstName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- Table: contractor
-- Stores information about contractors
-- =====================================================
CREATE TABLE IF NOT EXISTS contractor (
    ContractorID VARCHAR(10) PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    Surname VARCHAR(50) NOT NULL,
    Telephone VARCHAR(15) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    PhysicalAddress VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_contractor_email (Email),
    INDEX idx_contractor_name (Surname, FirstName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- Table: customer
-- Stores information about customers
-- =====================================================
CREATE TABLE IF NOT EXISTS customer (
    CustomerID VARCHAR(10) PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    Surname VARCHAR(50) NOT NULL,
    Telephone VARCHAR(15) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    PhysicalAddress VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_customer_email (Email),
    INDEX idx_customer_name (Surname, FirstName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- Table: project
-- Stores information about construction projects
-- =====================================================
CREATE TABLE IF NOT EXISTS project (
    ProjectNumber VARCHAR(20) PRIMARY KEY,
    ProjectName VARCHAR(100) NOT NULL,
    Deadline DATE NOT NULL,
    BuildingType VARCHAR(50) NOT NULL,
    PhysicalAddress VARCHAR(255) NOT NULL,
    ERFNumber VARCHAR(20) NOT NULL,
    TotalFee DECIMAL(15, 2) NOT NULL CHECK (TotalFee >= 0),
    TotalPaid DECIMAL(15, 2) DEFAULT 0.00 CHECK (TotalPaid >= 0),
    ArchitectID VARCHAR(10) NOT NULL,
    ContractorID VARCHAR(10) NOT NULL,
    CustomerID VARCHAR(10) NOT NULL,
    Finalised VARCHAR(3) DEFAULT 'No' CHECK (Finalised IN ('Yes', 'No')),
    CompletionDate DATE DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Foreign Key Constraints
    CONSTRAINT fk_project_architect 
        FOREIGN KEY (ArchitectID) REFERENCES architect(ArchitectID)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    
    CONSTRAINT fk_project_contractor 
        FOREIGN KEY (ContractorID) REFERENCES contractor(ContractorID)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    
    CONSTRAINT fk_project_customer 
        FOREIGN KEY (CustomerID) REFERENCES customer(CustomerID)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    
    -- Check Constraints
    CONSTRAINT chk_payment 
        CHECK (TotalPaid <= TotalFee),
    
    CONSTRAINT chk_completion_date 
        CHECK (CompletionDate IS NULL OR Finalised = 'Yes'),
    
    -- Indexes for performance
    INDEX idx_project_name (ProjectName),
    INDEX idx_project_deadline (Deadline),
    INDEX idx_project_finalised (Finalised),
    INDEX idx_project_architect (ArchitectID),
    INDEX idx_project_contractor (ContractorID),
    INDEX idx_project_customer (CustomerID),
    INDEX idx_project_overdue (Deadline, Finalised)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- Sample Data for Testing
-- =====================================================

-- Insert sample architects
INSERT INTO architect (ArchitectID, FirstName, Surname, Telephone, Email, PhysicalAddress) VALUES
('ARC001', 'John', 'Smith', '0123456789', 'john.smith@arch.com', '123 Design Ave, Johannesburg, South Africa'),
('ARC002', 'Sarah', 'Johnson', '0123456790', 'sarah.johnson@arch.com', '456 Creative St, Cape Town, South Africa'),
('ARC003', 'Michael', 'Brown', '0123456791', 'michael.brown@arch.com', '789 Blueprint Rd, Durban, South Africa')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

-- Insert sample contractors
INSERT INTO contractor (ContractorID, FirstName, Surname, Telephone, Email, PhysicalAddress) VALUES
('CON001', 'David', 'Williams', '0123456792', 'david.williams@contract.com', '321 Builder Lane, Johannesburg, South Africa'),
('CON002', 'Emma', 'Davis', '0123456793', 'emma.davis@contract.com', '654 Construction Ave, Cape Town, South Africa'),
('CON003', 'James', 'Wilson', '0123456794', 'james.wilson@contract.com', '987 Build St, Pretoria, South Africa')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

-- Insert sample customers
INSERT INTO customer (CustomerID, FirstName, Surname, Telephone, Email, PhysicalAddress) VALUES
('CUS001', 'Robert', 'Taylor', '0123456795', 'robert.taylor@email.com', '111 Home St, Johannesburg, South Africa'),
('CUS002', 'Linda', 'Anderson', '0123456796', 'linda.anderson@email.com', '222 Residence Ave, Cape Town, South Africa'),
('CUS003', 'William', 'Thomas', '0123456797', 'william.thomas@email.com', '333 Living Rd, Durban, South Africa')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

-- Insert sample projects
INSERT INTO project (ProjectNumber, ProjectName, Deadline, BuildingType, PhysicalAddress, ERFNumber, 
                     TotalFee, TotalPaid, ArchitectID, ContractorID, CustomerID, Finalised) VALUES
('PRJ001', 'House Taylor', '2025-12-31', 'House', '111 Home St, Johannesburg, South Africa', 'ERF1001', 500000.00, 250000.00, 'ARC001', 'CON001', 'CUS001', 'No'),
('PRJ002', 'Apartment Anderson', '2025-11-30', 'Apartment', '222 Residence Ave, Cape Town, South Africa', 'ERF1002', 750000.00, 750000.00, 'ARC002', 'CON002', 'CUS002', 'Yes'),
('PRJ003', 'House Thomas', '2025-06-30', 'House', '333 Living Rd, Durban, South Africa', 'ERF1003', 600000.00, 300000.00, 'ARC003', 'CON003', 'CUS003', 'No')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

-- Update completion date for finalized project
UPDATE project SET CompletionDate = CURRENT_DATE WHERE ProjectNumber = 'PRJ002' AND Finalised = 'Yes';

-- =====================================================
-- Views for Common Queries
-- =====================================================

-- View for incomplete projects
CREATE OR REPLACE VIEW view_incomplete_projects AS
SELECT 
    p.*,
    a.FirstName AS ArchitectFirstName,
    a.Surname AS ArchitectSurname,
    c.FirstName AS ContractorFirstName,
    c.Surname AS ContractorSurname,
    cu.FirstName AS CustomerFirstName,
    cu.Surname AS CustomerSurname,
    (p.TotalFee - p.TotalPaid) AS OutstandingAmount
FROM project p
JOIN architect a ON p.ArchitectID = a.ArchitectID
JOIN contractor c ON p.ContractorID = c.ContractorID
JOIN customer cu ON p.CustomerID = cu.CustomerID
WHERE p.Finalised = 'No';

-- View for overdue projects
CREATE OR REPLACE VIEW view_overdue_projects AS
SELECT 
    p.*,
    a.FirstName AS ArchitectFirstName,
    a.Surname AS ArchitectSurname,
    c.FirstName AS ContractorFirstName,
    c.Surname AS ContractorSurname,
    cu.FirstName AS CustomerFirstName,
    cu.Surname AS CustomerSurname,
    DATEDIFF(CURRENT_DATE, p.Deadline) AS DaysOverdue
FROM project p
JOIN architect a ON p.ArchitectID = a.ArchitectID
JOIN contractor c ON p.ContractorID = c.ContractorID
JOIN customer cu ON p.CustomerID = cu.CustomerID
WHERE p.Deadline < CURRENT_DATE 
  AND (p.Finalised IS NULL OR p.Finalised = 'No');

-- =====================================================
-- Stored Procedures
-- =====================================================

DELIMITER //

-- Procedure to finalize a project
CREATE PROCEDURE IF NOT EXISTS sp_finalize_project(
    IN p_project_number VARCHAR(20)
)
BEGIN
    DECLARE v_total_fee DECIMAL(15,2);
    DECLARE v_total_paid DECIMAL(15,2);
    DECLARE v_customer_id VARCHAR(10);
    DECLARE v_customer_name VARCHAR(100);
    
    -- Get project details
    SELECT TotalFee, TotalPaid, CustomerID 
    INTO v_total_fee, v_total_paid, v_customer_id
    FROM project 
    WHERE ProjectNumber = p_project_number;
    
    -- Update project status
    UPDATE project 
    SET Finalised = 'Yes', 
        CompletionDate = CURRENT_DATE 
    WHERE ProjectNumber = p_project_number;
    
    -- If there's outstanding payment, generate invoice
    IF v_total_paid < v_total_fee THEN
        SELECT CONCAT(FirstName, ' ', Surname) 
        INTO v_customer_name
        FROM customer 
        WHERE CustomerID = v_customer_id;
        
        SELECT 
            'INVOICE GENERATED' AS Status,
            v_customer_name AS CustomerName,
            (v_total_fee - v_total_paid) AS OutstandingAmount;
    END IF;
END//

DELIMITER ;

-- =====================================================
-- Triggers
-- =====================================================

DELIMITER //

-- Trigger to validate payment before insert
CREATE TRIGGER IF NOT EXISTS before_project_insert
BEFORE INSERT ON project
FOR EACH ROW
BEGIN
    IF NEW.TotalPaid > NEW.TotalFee THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Total paid cannot exceed total fee';
    END IF;
END//

-- Trigger to validate payment before update
CREATE TRIGGER IF NOT EXISTS before_project_update
BEFORE UPDATE ON project
FOR EACH ROW
BEGIN
    IF NEW.TotalPaid > NEW.TotalFee THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Total paid cannot exceed total fee';
    END IF;
    
    IF NEW.Finalised = 'Yes' AND NEW.CompletionDate IS NULL THEN
        SET NEW.CompletionDate = CURRENT_DATE;
    END IF;
END//

DELIMITER ;

-- =====================================================
-- Grant Permissions (adjust as needed for production)
-- =====================================================

-- CREATE USER IF NOT EXISTS 'poise_user'@'localhost' IDENTIFIED BY 'SecurePassword123!';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON PoiseDMS.* TO 'poise_user'@'localhost';
-- FLUSH PRIVILEGES;

-- =====================================================
-- Database Statistics and Information
-- =====================================================

SELECT 
    'Database Schema Created Successfully' AS Status,
    DATABASE() AS DatabaseName,
    VERSION() AS MySQLVersion,
    NOW() AS Timestamp;

-- Display table counts
SELECT 
    (SELECT COUNT(*) FROM architect) AS Architects,
    (SELECT COUNT(*) FROM contractor) AS Contractors,
    (SELECT COUNT(*) FROM customer) AS Customers,
    (SELECT COUNT(*) FROM project) AS Projects;
