# Production Deployment Guide

## Production Readiness Checklist

### Pre-Deployment

#### 1. Environment Configuration
- [ ] Create `.env` file from `.env.example`
- [ ] Set strong database passwords
- [ ] Configure production database URL
- [ ] Verify connection pool settings
- [ ] Set appropriate log levels (INFO or WARN)

#### 2. Database Setup
- [ ] Create production database
- [ ] Run `database/schema.sql`
- [ ] Create database user with limited privileges
- [ ] Test database connectivity
- [ ] Configure backup strategy
- [ ] Enable SSL for database connections

#### 3. Security
- [ ] Review SECURITY.md guidelines
- [ ] Change all default passwords
- [ ] Restrict network access
- [ ] Enable firewall rules
- [ ] Set up SSL/TLS certificates
- [ ] Configure audit logging

#### 4. Testing
- [ ] Run all unit tests: `mvn test`
- [ ] Perform integration testing
- [ ] Load testing with expected user volume
- [ ] Verify backup and restore procedures
- [ ] Test disaster recovery plan

#### 5. Monitoring
- [ ] Set up log monitoring
- [ ] Configure alerts for errors
- [ ] Monitor database performance
- [ ] Set up health checks
- [ ] Configure metrics collection

---

## Deployment Options

### Option 1: Docker Deployment (Recommended)

#### Prerequisites
- Docker Engine 20.10+
- Docker Compose 2.0+
- 2GB RAM minimum
- 10GB disk space

#### Steps

1. **Clone Repository**
   ```bash
   git clone https://github.com/Demayne/Database-Project-Management-System.git
   cd Database-Project-Management-System
   ```

2. **Configure Environment**
   ```bash
   cp .env.example .env
   # Edit .env with your production values
   nano .env
   ```

3. **Build and Start**
   ```bash
   docker-compose up -d
   ```

4. **Verify Deployment**
   ```bash
   docker-compose ps
   docker-compose logs -f app
   ```

5. **Access Application**
   ```bash
   docker exec -it poisedms-app java -jar /app/PoiseDMS.jar
   ```

#### Docker Management Commands

```bash
# View logs
docker-compose logs -f

# Stop services
docker-compose down

# Restart services
docker-compose restart

# Update application
docker-compose pull
docker-compose up -d --build

# Backup database
docker exec poisedms-mysql mysqldump -u root -p PoiseDMS > backup.sql
```

---

### Option 2: Traditional Deployment

#### Prerequisites
- Java 11 or higher
- MySQL 8.0+
- Maven 3.6+

#### Steps

1. **Database Setup**
   ```bash
   mysql -u root -p
   ```
   ```sql
   CREATE DATABASE PoiseDMS CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   CREATE USER 'poise_user'@'localhost' IDENTIFIED BY 'YourStrongPassword';
   GRANT SELECT, INSERT, UPDATE, DELETE ON PoiseDMS.* TO 'poise_user'@'localhost';
   FLUSH PRIVILEGES;
   EXIT;
   ```

2. **Import Schema**
   ```bash
   mysql -u root -p PoiseDMS < database/schema.sql
   ```

3. **Configure Application**
   ```bash
   cp config/database.properties.example config/database.properties
   # Edit with your database credentials
   nano config/database.properties
   ```

4. **Build Application**
   ```bash
   mvn clean package
   ```

5. **Run Application**
   ```bash
   java -jar target/PoiseDMS-2.0.0-jar-with-dependencies.jar
   ```

---

## Production Configuration

### Database Properties (config/database.properties)

```properties
# Production Database Configuration
db.url=jdbc:mysql://localhost:3306/PoiseDMS?useSSL=true&serverTimezone=UTC
db.username=poise_user
db.password=YourStrongPassword
db.driver=com.mysql.cj.jdbc.Driver

# Connection Pool (HikariCP)
db.pool.size=20
db.pool.timeout=30000
```

### Environment Variables (.env)

```bash
# Database
DB_URL=jdbc:mysql://localhost:3306/PoiseDMS?useSSL=true
DB_USERNAME=poise_user
DB_PASSWORD=YourStrongPassword
DB_ROOT_PASSWORD=YourRootPassword

# Application
APP_ENVIRONMENT=production
LOG_LEVEL=INFO
```

---

## Performance Tuning

### Database Optimization

1. **Connection Pool Settings**
   ```properties
   db.pool.size=20                    # Adjust based on load
   db.pool.timeout=30000              # 30 seconds
   hikari.idleTimeout=600000          # 10 minutes
   hikari.maxLifetime=1800000         # 30 minutes
   ```

2. **MySQL Configuration** (my.cnf)
   ```ini
   [mysqld]
   max_connections=200
   innodb_buffer_pool_size=1G
   innodb_log_file_size=256M
   query_cache_size=64M
   ```

3. **Indexes**
   - All foreign keys indexed
   - Email fields unique indexed
   - Name fields composite indexed

### Application Optimization

1. **JVM Settings**
   ```bash
   java -Xms512m -Xmx2g -XX:+UseG1GC -jar PoiseDMS.jar
   ```

2. **Logging**
   - Set to INFO level in production
   - Rotate logs daily
   - Compress old logs

---

## Monitoring and Maintenance

### Health Checks

1. **Database Connectivity**
   ```bash
   mysql -u poise_user -p -e "SELECT 1"
   ```

2. **Application Health**
   ```bash
   # Check connection pool stats
   # Check log files for errors
   tail -f logs/poisedms.log | grep ERROR
   ```

### Backup Strategy

#### Database Backups

**Daily Backups**
```bash
#!/bin/bash
# backup.sh
DATE=$(date +%Y%m%d_%H%M%S)
mysqldump -u poise_user -p PoiseDMS | gzip > backup_$DATE.sql.gz
# Rotate old backups (keep 30 days)
find . -name "backup_*.sql.gz" -mtime +30 -delete
```

**Schedule with cron**
```bash
0 2 * * * /path/to/backup.sh
```

#### Application State
- Export critical project data weekly
- Archive logs monthly
- Document configuration changes

### Log Management

**Logback Configuration** (src/logback.xml)
- Rolling file appender
- 30-day retention
- Gzip compression
- Separate error logs

**Log Locations**
- Application: `logs/poisedms.log`
- Errors: `logs/error.log`
- Docker: `docker logs poisedms-app`

---

## Scaling Considerations

### Vertical Scaling
- Increase JVM heap size
- Add more database RAM
- Faster disk I/O (SSD)

### Horizontal Scaling (Future)
- Connection pool per instance
- Load balancer
- Database replication
- Caching layer (Redis)

---

## Troubleshooting

### Common Issues

**1. Database Connection Failed**
```
Symptom: "Failed to get database connection"
Solution:
- Verify database is running
- Check credentials in config/database.properties
- Test network connectivity
- Check firewall rules
```

**2. Out of Memory**
```
Symptom: java.lang.OutOfMemoryError
Solution:
- Increase JVM heap: -Xmx2g
- Check for connection leaks
- Monitor connection pool stats
```

**3. Slow Queries**
```
Symptom: Long response times
Solution:
- Enable MySQL slow query log
- Add missing indexes
- Optimize queries
- Increase connection pool size
```

**4. Permission Denied**
```
Symptom: Access denied for user
Solution:
- Verify database user privileges
- Check host restrictions
- Reset password if needed
```

### Debug Mode

Enable debug logging:
```properties
log.level=DEBUG
```

Check detailed logs:
```bash
tail -f logs/poisedms.log
```

---

## Rollback Procedures

### Quick Rollback

1. **Stop Application**
   ```bash
   docker-compose down
   # or
   pkill -f PoiseDMS
   ```

2. **Restore Database Backup**
   ```bash
   mysql -u root -p PoiseDMS < backup_YYYYMMDD.sql
   ```

3. **Deploy Previous Version**
   ```bash
   git checkout v1.9.0
   docker-compose up -d
   ```

---

## Support and Maintenance

### Regular Maintenance Tasks

**Weekly**
- Review error logs
- Check disk space
- Monitor performance metrics

**Monthly**
- Update dependencies (security patches)
- Review and archive old logs
- Database optimization (ANALYZE, OPTIMIZE)

**Quarterly**
- Performance testing
- Security audit
- Backup restore testing

### Getting Help

- **Documentation**: See README.md
- **Issues**: GitHub Issues
- **Email**: support@poisedms.example.com

---

## Production Deployment Verification

After deployment, verify:

```bash
# 1. Database accessible
mysql -u poise_user -p -e "USE PoiseDMS; SHOW TABLES;"

# 2. Application starts
java -jar PoiseDMS.jar

# 3. Can view projects
# Select option 1 from menu

# 4. Logs are written
ls -lh logs/

# 5. Connection pool active
# Check startup logs for "Connection pool initialized"
```

---

**Last Updated**: November 21, 2025  
**Version**: 2.0.0  
**Author**: Demayne Govender
