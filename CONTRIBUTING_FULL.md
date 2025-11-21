# Contributing to PoiseDMS

Thank you for considering contributing to PoiseDMS! This document provides guidelines for contributing to the project.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Process](#development-process)
- [Coding Standards](#coding-standards)
- [Testing Guidelines](#testing-guidelines)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)
- [Reporting Bugs](#reporting-bugs)
- [Feature Requests](#feature-requests)

---

## Code of Conduct

### Our Pledge

We are committed to providing a welcoming and inclusive environment for all contributors. We expect all participants to:

- Use welcoming and inclusive language
- Be respectful of differing viewpoints
- Accept constructive criticism gracefully
- Focus on what is best for the community
- Show empathy towards other community members

### Unacceptable Behavior

- Harassment or discriminatory language
- Trolling or insulting comments
- Public or private harassment
- Publishing others' private information
- Other conduct inappropriate in a professional setting

---

## Getting Started

### Prerequisites

1. **Java Development Kit 11+**
2. **Maven 3.6+**
3. **MySQL 8.0+**
4. **Git**
5. **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

### Fork and Clone

```bash
# Fork the repository on GitHub
# Clone your fork
git clone https://github.com/YOUR_USERNAME/Database-Project-Management-System.git
cd Database-Project-Management-System

# Add upstream remote
git remote add upstream https://github.com/Demayne/Database-Project-Management-System.git
```

### Set Up Development Environment

```bash
# Create database
mysql -u root -p < database/schema.sql

# Configure database connection
cp config/database.properties.example config/database.properties
# Edit config/database.properties with your credentials

# Build project
mvn clean install

# Run tests
mvn test
```

---

## Development Process

### Branch Strategy

- `main` - Production-ready code
- `develop` - Integration branch for features
- `feature/*` - New features
- `bugfix/*` - Bug fixes
- `hotfix/*` - Emergency fixes for production

### Creating a Feature Branch

```bash
# Update your local repository
git checkout develop
git pull upstream develop

# Create feature branch
git checkout -b feature/your-feature-name
```

---

## Coding Standards

### Java Code Style

Follow **Google Java Style Guide** with these specifics:

#### Naming Conventions

- **Classes**: PascalCase (e.g., `ProjectManager`)
- **Methods**: camelCase (e.g., `viewAllProjects`)
- **Variables**: camelCase (e.g., `projectNumber`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_POOL_SIZE`)
- **Packages**: lowercase (e.g., `util`, `model`)

#### Code Organization

```java
package com.example;

// Imports organized
import java.util.List;
import java.sql.Connection;

/**
 * Javadoc for class.
 * 
 * @author Your Name
 * @version 2.0
 */
public class MyClass {
    
    // Constants first
    private static final int MAX_SIZE = 100;
    
    // Fields
    private String name;
    
    // Constructors
    public MyClass(String name) {
        this.name = name;
    }
    
    // Public methods
    public void doSomething() {
        // Implementation
    }
    
    // Private methods
    private void helperMethod() {
        // Implementation
    }
}
```

#### Documentation

**All public classes and methods must have Javadoc:**

```java
/**
 * Brief description of what the method does.
 * 
 * <p>Longer description if needed, explaining behavior,
 * edge cases, or important details.</p>
 * 
 * @param connection Database connection to use
 * @param projectId ID of the project to retrieve
 * @return Project object if found
 * @throws DatabaseException if database error occurs
 * @throws EntityNotFoundException if project not found
 */
public Project getProject(Connection connection, int projectId) 
        throws DatabaseException, EntityNotFoundException {
    // Implementation
}
```

#### Error Handling

**Always use try-with-resources:**

```java
// Good
try (Connection conn = ConnectionPool.getConnection();
     PreparedStatement pstmt = conn.prepareStatement(query)) {
    // Use resources
} catch (SQLException e) {
    LoggerUtil.error("Database error", e);
    throw new DatabaseException("Failed to execute query", e);
}

// Bad - doesn't close resources
Connection conn = ConnectionPool.getConnection();
PreparedStatement pstmt = conn.prepareStatement(query);
```

**Use custom exceptions:**

```java
// Good
throw new ValidationException("Invalid email format");

// Bad
throw new Exception("Invalid email format");
```

#### Logging

**Use LoggerUtil instead of System.out:**

```java
// Good
LoggerUtil.info("Processing project: " + projectId);
LoggerUtil.error("Failed to save project", exception);

// Bad
System.out.println("Processing project: " + projectId);
e.printStackTrace();
```

---

## Testing Guidelines

### Unit Tests

**Every new class should have corresponding tests:**

```java
package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {
    
    @Test
    void testValidEmailAccepted() {
        assertTrue(ValidationUtil.isValidEmail("test@example.com"));
    }
    
    @Test
    void testInvalidEmailRejected() {
        assertFalse(ValidationUtil.isValidEmail("invalid-email"));
    }
    
    @Test
    void testNullEmailRejected() {
        assertFalse(ValidationUtil.isValidEmail(null));
    }
}
```

### Test Coverage

- **Minimum 70% code coverage** for new code
- Test happy paths and edge cases
- Test error conditions
- Use meaningful test names

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ValidationUtilTest

# Run with coverage report
mvn clean test jacoco:report
```

---

## Commit Guidelines

### Commit Message Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation only
- `style`: Formatting changes
- `refactor`: Code restructuring
- `perf`: Performance improvement
- `test`: Adding tests
- `chore`: Maintenance tasks

### Examples

```
feat(project): add search by ERF number

Implement search functionality to find projects by ERF number.
Includes validation and error handling.

Closes #123
```

```
fix(database): resolve connection pool leak

Fixed issue where connections weren't being returned to pool
after certain error conditions.

Fixes #456
```

### Best Practices

- Use present tense ("add feature" not "added feature")
- Keep first line under 72 characters
- Reference issues in footer
- Explain *why* not *what* in body

---

## Pull Request Process

### Before Submitting

- [ ] Code follows style guidelines
- [ ] All tests pass
- [ ] New tests added for new features
- [ ] Documentation updated
- [ ] No merge conflicts
- [ ] Commit messages follow guidelines

### PR Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
Describe testing performed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] Tests added/updated
- [ ] All tests pass

## Related Issues
Closes #123
```

### Review Process

1. Submit PR to `develop` branch
2. Automated CI/CD runs tests
3. At least one maintainer reviews
4. Address review comments
5. Maintainer merges when approved

---

## Reporting Bugs

### Before Reporting

- Search existing issues
- Check if already fixed in latest version
- Verify it's reproducible

### Bug Report Template

```markdown
**Description**
Clear description of the bug

**To Reproduce**
1. Step 1
2. Step 2
3. See error

**Expected Behavior**
What should happen

**Actual Behavior**
What actually happens

**Environment**
- OS: [e.g., Windows 10, Ubuntu 20.04]
- Java Version: [e.g., 11.0.12]
- MySQL Version: [e.g., 8.0.26]
- PoiseDMS Version: [e.g., 2.0.0]

**Logs**
```
Paste relevant logs
```

**Additional Context**
Any other relevant information
```

---

## Feature Requests

### Feature Request Template

```markdown
**Is your feature request related to a problem?**
Description of the problem

**Describe the solution you'd like**
What you want to happen

**Describe alternatives you've considered**
Other solutions considered

**Additional context**
Any other relevant information
```

---

## Development Tips

### Database Changes

If modifying schema:
1. Update `database/schema.sql`
2. Create migration script in `database/migrations/`
3. Update relevant model classes
4. Update documentation

### Adding Dependencies

```xml
<!-- Add to pom.xml -->
<dependency>
    <groupId>com.example</groupId>
    <artifactId>library</artifactId>
    <version>1.0.0</version>
</dependency>
```

Then update documentation and README.

### Performance Considerations

- Profile code before optimizing
- Consider connection pool impact
- Use prepared statements
- Implement pagination for large datasets

---

## Code Review Checklist

### For Reviewers

- [ ] Code is readable and maintainable
- [ ] Follows project coding standards
- [ ] Adequate test coverage
- [ ] No security vulnerabilities
- [ ] Performance considerations addressed
- [ ] Documentation updated
- [ ] No unnecessary dependencies

### For Contributors

Before requesting review:
- [ ] Self-reviewed code
- [ ] Removed debug statements
- [ ] Updated documentation
- [ ] All tests pass locally
- [ ] No compiler warnings

---

## Release Process

(For maintainers)

1. Update version in `pom.xml`
2. Update `CHANGELOG.md`
3. Create release branch
4. Final testing
5. Merge to main
6. Tag release
7. Deploy to production
8. Announce release

---

## Getting Help

- **Questions**: Open GitHub Discussions
- **Bugs**: Create GitHub Issue
- **Security**: Email security contact (see SECURITY.md)
- **Chat**: Join our community (if applicable)

---

## Recognition

Contributors will be recognized in:
- README.md contributors section
- Release notes
- GitHub contributors page

---

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

**Thank you for contributing to PoiseDMS! 🎉**

---

**Last Updated**: November 21, 2025  
**Version**: 2.0.0
