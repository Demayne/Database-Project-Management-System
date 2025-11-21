# Security Policy

## Supported Versions

We release security updates for the following versions:

| Version | Supported          |
| ------- | ------------------ |
| 2.0.x   | :white_check_mark: |
| < 2.0   | :x:                |

## Security Features

### Database Security
- **SQL Injection Prevention**: All database queries use prepared statements
- **Connection Pooling**: HikariCP with secure configuration
- **Credential Management**: Environment-based configuration (.env files)
- **Input Validation**: Comprehensive validation for all user inputs

### Application Security
- **Error Handling**: Custom exceptions prevent information leakage
- **Logging**: Sensitive data is never logged
- **Resource Management**: Proper cleanup with try-with-resources
- **Data Validation**: All inputs validated before processing

### Best Practices
1. **Never commit** `.env` files or database credentials
2. **Use strong passwords** for database users
3. **Enable SSL/TLS** for production database connections
4. **Regular updates** of dependencies for security patches
5. **Limit database privileges** to minimum required

## Reporting a Vulnerability

If you discover a security vulnerability, please:

1. **Do NOT** open a public issue
2. Email: security@poisedms.example.com (or contact repository owner)
3. Include:
   - Description of the vulnerability
   - Steps to reproduce
   - Potential impact
   - Suggested fix (if any)

### Response Timeline
- **Initial Response**: Within 48 hours
- **Status Update**: Within 1 week
- **Fix Timeline**: Varies based on severity
  - Critical: 1-7 days
  - High: 7-30 days
  - Medium: 30-90 days
  - Low: Best effort

## Security Checklist for Production

### Database
- [ ] Use strong, unique passwords
- [ ] Enable SSL/TLS connections
- [ ] Restrict network access (firewall rules)
- [ ] Regular backups with encryption
- [ ] Monitor for suspicious queries
- [ ] Use least privilege principle

### Application
- [ ] Update all dependencies
- [ ] Configure proper logging (no sensitive data)
- [ ] Set appropriate file permissions
- [ ] Use environment variables for secrets
- [ ] Enable application monitoring
- [ ] Implement rate limiting (future)

### Infrastructure
- [ ] Keep OS and Java runtime updated
- [ ] Configure Docker security (if using containers)
- [ ] Use dedicated service accounts
- [ ] Enable audit logging
- [ ] Regular security scans

## Known Security Considerations

### Current Limitations
1. **No Authentication Layer**: Current version is single-user CLI application
2. **No Encryption at Rest**: Database data stored in plaintext
3. **Limited Audit Trail**: Basic logging without comprehensive audit

### Planned Security Enhancements (v3.0)
- Multi-user authentication and authorization
- Role-based access control (RBAC)
- Encryption at rest for sensitive data
- Comprehensive audit logging
- API rate limiting and throttling
- OAuth2/JWT authentication for REST API

## Security Contacts

- **Security Email**: security@poisedms.example.com
- **Maintainer**: Demayne Govender
- **GitHub**: @Demayne

## Acknowledgments

We appreciate responsible disclosure of security vulnerabilities. Contributors will be acknowledged (with permission) in our security advisories.
