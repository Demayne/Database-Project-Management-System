# Database Migration Guide

## Overview
This directory contains database migration scripts for PoiseDMS.

## Migration Scripts

### V1__initial_schema.sql
- Creates initial database schema
- Sets up tables: architect, contractor, customer, project
- Establishes foreign key relationships
- Creates indexes for performance
- Adds sample data

### V2__add_views.sql
- Creates database views for common queries
- Optimizes frequently-used queries

### V3__add_procedures.sql
- Adds stored procedures for business logic
- Implements invoice generation logic

## Running Migrations

### Option 1: Manual Execution
```bash
mysql -u username -p < database/migrations/V1__initial_schema.sql
```

### Option 2: Using Flyway (Recommended for Production)
```bash
flyway migrate -user=username -password=password -url=jdbc:mysql://localhost:3306/PoiseDMS
```

## Migration Best Practices

1. **Never modify existing migrations** - Create new migration scripts instead
2. **Test migrations** on development environment first
3. **Backup database** before running migrations in production
4. **Use version control** for all migration scripts
5. **Document changes** in each migration file

## Rollback Procedures

If a migration fails:
1. Restore from backup
2. Fix the migration script
3. Re-run the migration

## Naming Convention

Format: `V{version}__{description}.sql`
- V1__initial_schema.sql
- V2__add_views.sql
- V3__add_procedures.sql

Version numbers should be sequential and never reused.
