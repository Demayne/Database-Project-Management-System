# PoiseDMS - Database Project Management System

<div align="center">

![Version](https://img.shields.io/badge/version-2.0.0-blue.svg)
![Java](https://img.shields.io/badge/Java-11+-orange.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)
![Build](https://img.shields.io/badge/build-passing-brightgreen.svg)
![Production Ready](https://img.shields.io/badge/production-ready-success.svg)
![Docker](https://img.shields.io/badge/docker-supported-2496ED.svg)
![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF.svg)

**🏗️ Enterprise-Grade Construction Project Management System**

*Efficiently manage construction projects with confidence*

[Features](#features) • [Quick Start](#installation) • [Documentation](#documentation) • [API Guide](API_GUIDE.md) • [Deploy](PRODUCTION_DEPLOYMENT.md) • [Security](SECURITY.md)

</div>

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Database Setup](#database-setup)
- [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [Usage Guide](#usage-guide)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Production Deployment](#production-deployment)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

---

## 🎯 Overview

PoiseDMS (Poise Database Management System) is a comprehensive, production-ready project management system designed specifically for construction companies. It provides a robust command-line interface for managing construction projects, including tracking architects, contractors, customers, deadlines, and financial information.

### Key Highlights

- ✅ **Production-Ready**: Implements enterprise-level patterns and best practices
- ✅ **Connection Pooling**: Uses HikariCP for optimal database performance
- ✅ **Comprehensive Logging**: Built-in logging framework for monitoring and debugging
- ✅ **Secure Configuration**: Environment-based configuration management
- ✅ **Input Validation**: Robust validation utilities to ensure data integrity
- ✅ **Database Integrity**: Foreign key constraints, triggers, and stored procedures
- ✅ **Well Documented**: Extensive Javadoc and user documentation

---

## 🚀 Features

### Core Functionality

1. **Project Management**
   - View all projects with detailed information
   - View incomplete projects
   - View overdue projects with automatic detection
   - Search projects by number or name
   - Add new projects with comprehensive validation
   - Update existing project details
   - Delete projects with confirmation
   - Finalize projects with automatic invoice generation

2. **Entity Management**
   - Manage Architects with contact information
   - Manage Contractors with detailed profiles
   - Manage Customers with full contact details
   - Automatic entity creation during project setup
   - Email and phone number validation

3. **Financial Tracking**
   - Track total project fees
   - Monitor payments received
   - Calculate outstanding amounts
   - Automatic invoice generation for unpaid projects

4. **Reporting & Analytics**
   - Formatted table displays for better readability
   - Real-time project status tracking
   - Overdue project alerts
   - Project completion tracking

### Technical Features

- **Connection Pooling**: HikariCP implementation for database efficiency
- **Configuration Management**: Properties-based configuration system
- **Logging System**: Comprehensive file and console logging
- **Input Validation**: Dedicated utility for data validation
- **Error Handling**: Robust exception management
- **SQL Injection Prevention**: Prepared statements throughout
- **Database Views**: Optimized views for common queries
- **Stored Procedures**: Business logic encapsulation
- **Database Triggers**: Automatic data validation and updates

---

## 🏗️ System Architecture

```
PoiseDMS/
├── src/
│   ├── Main.java                 # Application entry point
│   ├── DatabaseConnection.java   # Database connection manager
│   ├── ProjectManager.java       # Core business logic
│   ├── TableFormatter.java       # Display formatting utility
│   ├── config/
│   │   ├── DatabaseConfig.java   # Configuration loader
│   │   └── database.properties   # Database settings
│   └── util/
│       ├── ConnectionPool.java   # HikariCP connection pool
│       ├── LoggerUtil.java       # Logging framework
│       └── ValidationUtil.java   # Input validation
├── database/
│   └── schema.sql                # Database schema and setup
├── docs/                         # Javadoc documentation
├── logs/                         # Application logs
├── pom.xml                       # Maven build configuration
├── .gitignore                    # Git ignore rules
├── .env.example                  # Environment template
└── README.md                     # This file
```

---

## 💻 Technologies Used

### Core Technologies
- **Java 11+**: Programming language
- **MySQL 8.0+**: Relational database
- **JDBC**: Database connectivity
- **Maven**: Build automation and dependency management

### Libraries & Frameworks
- **HikariCP 5.0.1**: High-performance JDBC connection pool
- **MySQL Connector/J 8.0.33**: MySQL database driver
- **SLF4J & Logback**: Logging framework
- **Apache Commons Lang3**: Utility functions
- **JUnit 5**: Unit testing framework
- **Mockito**: Mocking framework for tests

---

## 📦 Prerequisites

Before installing PoiseDMS, ensure you have the following installed:

1. **Java Development Kit (JDK) 11 or higher**
   ```bash
   java -version
   ```

2. **MySQL Server 8.0 or higher**
   ```bash
   mysql --version
   ```

3. **Apache Maven 3.6+** (for building from source)
   ```bash
   mvn --version
   ```

4. **Git** (for cloning the repository)
   ```bash
   git --version
   ```

---

## 🔧 Installation

### Option 1: Clone from GitHub

```bash
# Clone the repository
git clone https://github.com/Demayne/Database-Project-Management-System.git

# Navigate to project directory
cd Database-Project-Management-System
```

### Option 2: Download ZIP

Download and extract the latest release from the [GitHub releases page](https://github.com/Demayne/Database-Project-Management-System/releases).

---

## ⚙️ Configuration

### 1. Database Configuration

Create a copy of the example environment file:

```bash
cp .env.example .env
```

Edit `src/config/database.properties` with your MySQL credentials:

```properties
db.url=jdbc:mysql://localhost:3306/PoiseDMS?useSSL=true&serverTimezone=UTC
db.username=your_mysql_username
db.password=your_mysql_password
db.driver=com.mysql.cj.jdbc.Driver

# Connection Pool Settings
db.pool.size=10
db.pool.timeout=30000
```

⚠️ **Security Note**: Never commit `database.properties` with actual credentials to version control!

---

## 🗄️ Database Setup

### Automatic Setup (Recommended)

Run the provided SQL schema file:

```bash
mysql -u your_username -p < database/schema.sql
```

This will:
- Create the `PoiseDMS` database
- Create all required tables with constraints
- Set up indexes for performance
- Create database views
- Set up stored procedures and triggers
- Insert sample data for testing

### Manual Setup

1. **Create Database**
   ```sql
   CREATE DATABASE PoiseDMS CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   USE PoiseDMS;
   ```

2. **Run Schema File**
   Execute the SQL commands from `database/schema.sql`

3. **Verify Setup**
   ```sql
   SHOW TABLES;
   SELECT COUNT(*) FROM project;
   ```

---

## 🔨 Building the Project

### Using Maven

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package as JAR
mvn package

# Create JAR with dependencies
mvn clean package assembly:single
```

### Build Outputs

- `target/PoiseDMS-2.0.0.jar` - Standard JAR
- `target/PoiseDMS-2.0.0-jar-with-dependencies.jar` - Executable JAR with all dependencies

---

## ▶️ Running the Application

### Method 1: Using Maven

```bash
mvn exec:java
```

### Method 2: Using Compiled JAR

```bash
java -jar target/PoiseDMS-2.0.0-jar-with-dependencies.jar
```

### Method 3: Direct Execution

```bash
# Compile first
javac -cp ".:mysql-connector-j-8.0.33.jar:HikariCP-5.0.1.jar" src/*.java src/**/*.java

# Run
java -cp ".:src:mysql-connector-j-8.0.33.jar:HikariCP-5.0.1.jar" Main
```

---

## 📖 Usage Guide

### Main Menu Options

```
==================================================
PoiseDMS - Please choose an option:
==================================================
1. View all projects
2. View incomplete projects
3. View overdue projects
4. Search projects by number or name
5. Add a new project
6. Update an existing project
7. Delete a project
8. Finalize a project
9. Exit
==================================================
```

### Common Workflows

#### Adding a New Project

1. Select option `5`
2. Enter project number (numeric)
3. Enter project name (or press Enter to auto-generate)
4. Enter deadline (YYYY-MM-DD format)
5. Enter building type
6. Enter physical address
7. Enter ERF number (must start with "ERF")
8. Enter total fee and amount paid
9. Select or add Architect, Contractor, and Customer

#### Searching for Projects

1. Select option `4`
2. Enter project number or name
3. View matching results in formatted table

#### Finalizing a Project

1. Select option `8`
2. Enter project number
3. Confirm finalization
4. If payment is outstanding, an invoice is generated

---

## 📚 API Documentation

### Generate Javadoc

```bash
mvn javadoc:javadoc
```

Access documentation at: `docs/index.html`

### Key Classes

#### Main.java
Entry point for the application. Manages user interaction and menu system.

#### ProjectManager.java
Core business logic for project operations (CRUD).

#### DatabaseConnection.java
Manages database connections using connection pooling.

#### ConnectionPool.java
HikariCP implementation for efficient connection management.

#### ValidationUtil.java
Provides comprehensive input validation methods.

#### LoggerUtil.java
Centralized logging for application monitoring.

---

## 📁 Project Structure

```
PoiseDMS/
├── .env.example              # Environment template
├── .gitignore               # Git ignore rules
├── pom.xml                  # Maven configuration
├── README.md                # This documentation
│
├── database/
│   └── schema.sql          # Complete database schema
│
├── docs/                    # Javadoc HTML documentation
│   ├── index.html
│   └── ...
│
├── logs/                    # Application logs (auto-created)
│   └── poisedms_0.log
│
├── src/
│   ├── Main.java
│   ├── DatabaseConnection.java
│   ├── ProjectManager.java
│   ├── TableFormatter.java
│   │
│   ├── config/
│   │   ├── DatabaseConfig.java
│   │   └── database.properties
│   │
│   └── util/
│       ├── ConnectionPool.java
│       ├── LoggerUtil.java
│       └── ValidationUtil.java
│
└── target/                  # Build output (generated)
    ├── classes/
    └── *.jar
```

---

## 🚀 Production Deployment

### Pre-Deployment Checklist

- [ ] Update `database.properties` with production credentials
- [ ] Configure SSL for MySQL connection
- [ ] Set up database backups
- [ ] Configure logging levels appropriately
- [ ] Run security audit
- [ ] Perform load testing
- [ ] Document disaster recovery procedures

### Security Best Practices

1. **Never commit credentials** to version control
2. **Use environment variables** for sensitive data
3. **Enable SSL/TLS** for database connections
4. **Implement user authentication** for production
5. **Regular security updates** for dependencies
6. **Monitor logs** for suspicious activity

### Performance Optimization

### Containerized Deployment (Docker / Compose)

1. Ensure `.env` contains `DB_ROOT_PASSWORD`, `DB_USERNAME`, `DB_PASSWORD`.
2. Start stack:
   ```bash
   docker compose up -d --build
   ```
3. Logs:
   ```bash
   docker compose logs -f app
   ```
4. Stop:
   ```bash
   docker compose down
   ```

The application runs as non-root (`appuser`) in the container for improved security. Database schema auto-loads from `database/schema.sql`.

### Logging Configuration

Runtime logging uses **SLF4J + Logback**. Customize `src/logback.xml` (or `/app/config/logback.xml` in Docker) to adjust levels, patterns, and rotation. Avoid verbose DEBUG in production unless diagnosing an issue.

### Environment-Based Configuration

`DatabaseConfig` automatically overrides property file values with environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, etc.). For production set only environment variables; remove or avoid distributing `database.properties` with secrets. Use a template file (e.g., `database.properties.example`).

### Graceful Shutdown

The application registers a JVM shutdown hook to close the connection pool, ensuring clean resource release during container stops or system signals.

1. **Connection Pool Tuning**: Adjust pool size based on load
2. **Database Indexing**: Ensure all queries use appropriate indexes
3. **Query Optimization**: Review and optimize slow queries
4. **Caching Strategy**: Implement caching for frequently accessed data

---

## 🔍 Troubleshooting

### Common Issues

#### Connection Failed

```
❌ Connection failed: Access denied for user...
```

**Solution**: Verify database credentials in `database.properties`

#### ClassNotFoundException

```
❌ MySQL JDBC Driver not found
```

**Solution**: Ensure MySQL connector is in classpath or run `mvn clean install`

#### Port Already in Use

```
❌ SQLException: Communications link failure
```

**Solution**: Check if MySQL is running on the specified port (default: 3306)

#### Out of Memory

```
java.lang.OutOfMemoryError: Java heap space
```

**Solution**: Increase JVM heap size: `java -Xmx512m -jar PoiseDMS.jar`

### Debug Mode

Enable detailed logging by setting log level in `LoggerUtil.java`:

```java
consoleHandler.setLevel(Level.ALL);  // Show all log levels
```

---

## 🤝 Contributing
## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

### How to Contribute

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

### Coding Standards

- Follow Java naming conventions
- Write comprehensive Javadoc comments
- Include unit tests for new features
- Ensure code passes all existing tests
- Update documentation as needed

### Reporting Issues

Please use the [GitHub Issues](https://github.com/Demayne/Database-Project-Management-System/issues) page to report bugs or request features.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2025 Demayne Govender

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 👨‍💻 Author

**Demayne Govender**

- GitHub: [@Demayne](https://github.com/Demayne)
- LinkedIn: [Demayne Govender](https://linkedin.com/in/demayne-govender)
- Email: demayne@example.com

---

## 🙏 Acknowledgments

- HikariCP team for the excellent connection pooling library
- MySQL team for the robust database system
- Apache Maven team for build automation
- The Java community for continuous support and resources

---

## 📈 Version History

### Version 2.0 (Current)
- ✅ Added HikariCP connection pooling
- ✅ Implemented comprehensive logging framework
- ✅ Added configuration management system
- ✅ Created validation utilities
- ✅ Enhanced error handling
- ✅ Added Maven build system
- ✅ Created complete SQL schema with views and procedures
- ✅ Production-ready architecture
- ✅ Comprehensive documentation

### Version 1.0
- ✅ Initial release
- ✅ Basic CRUD operations
- ✅ Project management functionality
- ✅ Simple database connectivity

---

## 🔮 Future Enhancements

- [ ] Web-based user interface
- [ ] RESTful API implementation
- [ ] User authentication and authorization
- [ ] Role-based access control
- [ ] Email notifications for overdue projects
- [ ] PDF report generation
- [ ] Data export functionality (CSV, Excel)
- [ ] Dashboard with analytics
- [ ] Mobile application
- [ ] Integration with accounting software

---

## 📞 Support

For support, please:
1. Check the [Documentation](#documentation)
2. Review [Troubleshooting](#troubleshooting)
3. Search [existing issues](https://github.com/Demayne/Database-Project-Management-System/issues)
4. Create a [new issue](https://github.com/Demayne/Database-Project-Management-System/issues/new) if needed

---

## ⭐ Show Your Support

If you find this project helpful, please consider:
- ⭐ Starring the repository
- 🍴 Forking the project
- 📢 Sharing with others
- 💬 Providing feedback

---

<div align="center">

**Made with ❤️ by Demayne Govender**

[⬆ Back to Top](#poisedms---database-project-management-system)

</div>