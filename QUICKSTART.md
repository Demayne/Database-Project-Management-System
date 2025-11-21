# Quick Start Guide - PoiseDMS

## Prerequisites Check

Before you begin, ensure you have:
- [ ] Java 11 or higher installed
- [ ] MySQL 8.0 or higher installed and running
- [ ] Maven 3.6+ installed (optional, for building from source)
- [ ] Git installed (for cloning repository)

## 5-Minute Setup

### Step 1: Clone or Download
```bash
git clone https://github.com/Demayne/Database-Project-Management-System.git
cd Database-Project-Management-System
```

### Step 2: Database Setup
```bash
# Login to MySQL
mysql -u root -p

# Run the schema file
mysql -u root -p < database/schema.sql
```

### Step 3: Configure Database Connection
Edit `src/config/database.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/PoiseDMS
db.username=YOUR_USERNAME
db.password=YOUR_PASSWORD
```

### Step 4: Build the Application

**Windows:**
```cmd
build.bat
```

**Linux/Mac:**
```bash
chmod +x build.sh
./build.sh
```

### Step 5: Run the Application
```bash
java -jar target/PoiseDMS-2.0.0-jar-with-dependencies.jar
```

## First-Time Usage

1. **View Sample Data**
   - Select option `1` to view all projects
   - Observe the sample data loaded from schema.sql

2. **Search for a Project**
   - Select option `4`
   - Enter "PRJ001" or "Taylor"

3. **Add Your First Project**
   - Select option `5`
   - Follow the prompts
   - Use existing entities or create new ones

## Common Commands

### View Incomplete Projects
Select option `2` from the main menu

### View Overdue Projects
Select option `3` from the main menu

### Update a Project
Select option `6`, enter project number, update details

### Finalize a Project
Select option `8`, enter project number to mark as complete

## Troubleshooting

### "Connection failed"
- Verify MySQL is running: `mysql -u root -p`
- Check credentials in `database.properties`
- Ensure PoiseDMS database exists

### "Driver not found"
- Ensure MySQL connector is in classpath
- Rebuild with: `mvn clean install`

### "Port 3306 already in use"
- Change MySQL port or stop conflicting service
- Update port in `database.properties`

## Next Steps

- Read the full [README.md](README.md)
- Review [ARCHITECTURE.md](ARCHITECTURE.md)
- Check [CONTRIBUTING.md](CONTRIBUTING.md) to contribute
- Explore the Javadoc documentation in `docs/`

## Getting Help

- GitHub Issues: https://github.com/Demayne/Database-Project-Management-System/issues
- Documentation: See README.md
- Email: demayne@example.com

## Docker Quick Start (Alternative)

If you prefer Docker:

```bash
# Build and run with Docker Compose
docker-compose up -d

# View logs
docker-compose logs -f app

# Access application
docker exec -it poisedms-app bash
```

---

**Congratulations!** You're now ready to use PoiseDMS. 🎉
