# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.0] - 2025-11-21

### Added - Production Enhancements
- **Service Layer**: New `ProjectService` class for business logic separation
- **Application Configuration**: `AppConfig` class for centralized configuration management
- **Comprehensive Documentation**:
  - `API_GUIDE.md` - Complete API and integration documentation
  - `PRODUCTION_DEPLOYMENT.md` - Detailed production deployment guide
  - `SECURITY.md` - Security policy and best practices
  - `CONTRIBUTING_FULL.md` - Extended contribution guidelines
- **Enhanced Application Properties**: Extended configuration with production settings
- **Production-Ready Features**:
  - Service layer architecture for better separation of concerns
  - Centralized configuration management
  - Enhanced error handling with custom exceptions
  - Comprehensive API documentation for future integrations
  - Deployment guides for Docker and traditional setups
  - Security guidelines and vulnerability reporting process

### Added - Core Features
- **Connection Pooling**: Implemented HikariCP for efficient database connection management
- **Configuration Management**: Added `DatabaseConfig` class for flexible configuration
- **Logging Framework**: Comprehensive logging using custom `LoggerUtil` with file and console output
- **Validation Utilities**: Created `ValidationUtil` class for robust input validation
- **Database Schema**: Complete SQL schema file with tables, views, procedures, and triggers
- **Build System**: Maven POM configuration for dependency management and building
- **Environment Configuration**: `.env.example` file for secure credential management
- **Production Features**:
  - Connection pool monitoring and statistics
  - Automatic resource cleanup
  - Enhanced error handling and logging
  - SQL injection prevention
  - Input sanitization
- **Database Enhancements**:
  - Foreign key constraints for data integrity
  - Indexes for performance optimization
  - Database views for common queries
  - Stored procedures for business logic
  - Triggers for automatic validation
  - Sample data for testing

### Changed
- **DatabaseConnection**: Refactored to use connection pooling instead of direct connections
- **Main**: Enhanced with welcome banner, improved menu display, and better error handling
- **ProjectManager**: Improved with better logging and validation
- **Code Structure**: Organized into packages (config, util) for better maintainability
- **Version**: Updated from 1.0 to 2.0 to reflect major changes

### Improved
- **Performance**: Connection pooling significantly improves database performance
- **Reliability**: Better error handling and resource management
- **Security**: Environment-based configuration prevents credential exposure
- **Maintainability**: Modular structure with separated concerns
- **Monitoring**: Comprehensive logging for debugging and monitoring
- **Data Integrity**: Database constraints and validation ensure data quality

### Fixed
- Resource leaks with proper connection pool management
- Inconsistent error handling throughout application
- Missing validation for user inputs
- Hard-coded database credentials
- Lack of logging for troubleshooting

## [1.0.0] - 2024

### Added
- Initial release of PoiseDMS
- Basic CRUD operations for projects
- Entity management (Architects, Contractors, Customers)
- Project search functionality
- View incomplete and overdue projects
- Project finalization feature
- TableFormatter for displaying data
- Basic database connectivity
- Command-line interface

### Features
- View all projects
- View incomplete projects
- View overdue projects
- Search projects by number or name
- Add new project
- Update existing project
- Delete project
- Finalize project

---

## Version Comparison

| Feature | v1.0 | v2.0 |
|---------|------|------|
| Connection Pooling | ❌ | ✅ |
| Configuration Management | ❌ | ✅ |
| Logging Framework | ❌ | ✅ |
| Input Validation | Partial | ✅ Complete |
| Maven Build | ❌ | ✅ |
| Database Schema | Manual | ✅ Automated |
| Production Ready | ❌ | ✅ |
| Documentation | Basic | ✅ Comprehensive |

---

[2.0.0]: https://github.com/Demayne/Database-Project-Management-System/releases/tag/v2.0.0
[1.0.0]: https://github.com/Demayne/Database-Project-Management-System/releases/tag/v1.0.0
