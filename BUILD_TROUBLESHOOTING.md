# Build & CI/CD Troubleshooting Guide

## Quick Fix Commands

If you encounter build errors, run these in order:

```powershell
# Windows PowerShell
mvn clean
mvn compile
mvn test
```

```bash
# Linux/Mac
./mvnw clean
./mvnw compile
./mvnw test
```

## Common Build Errors

### 1. "Package org.junit.jupiter.api does not exist"
**Cause:** JUnit dependencies missing or test source directory not configured  
**Fix:** Already resolved in pom.xml with junit-jupiter-api and junit-jupiter-engine dependencies

### 2. "Cannot find symbol" for test methods
**Cause:** Test source directory not specified  
**Fix:** pom.xml now includes `<testSourceDirectory>src/test/java</testSourceDirectory>`

### 3. Type mismatch errors in ProjectService
**Cause:** Project.projectNumber is String but code treated it as int  
**Fix:** All type conversions updated in ProjectService.java

### 4. GitHub Actions build failure
**Cause:** Missing workflow file or wrong Java version  
**Fix:** `.github/workflows/maven-build.yml` created with JDK 11

## Pre-Commit Checklist

Before pushing to GitHub:

1. **Run validation script:**
   ```powershell
   .\pre-commit-check.bat
   ```

2. **Check for uncommitted changes:**
   ```powershell
   git status
   ```

3. **Run full build:**
   ```powershell
   mvn clean install
   ```

## GitHub Actions Workflow

Location: `.github/workflows/maven-build.yml`

**What it does:**
- Triggers on push/PR to main or develop branches
- Sets up JDK 11 (Temurin distribution)
- Runs: clean → compile → test → package
- Uploads JAR artifacts for 7 days

**View build status:**
- Go to your repo → Actions tab
- Click on latest workflow run
- Check logs if build fails

## Maven Configuration

**Java Version:** 11  
**Maven Version:** 3.9.11+

**Key pom.xml sections:**
```xml
<sourceDirectory>src</sourceDirectory>
<testSourceDirectory>src/test/java</testSourceDirectory>
```

**Dependencies:**
- JUnit 5 (jupiter-api + jupiter-engine)
- MySQL Connector 8.0.33
- HikariCP 5.0.1
- Logback for logging

## Environment Setup

**Required:**
- JDK 11 or higher (you have JDK 25 which is fine)
- Maven 3.9.11 (installed at `C:\Maven\apache-maven-3.9.11`)
- MySQL 8.0+

**Environment Variables:**
```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot"
```

## Build Lifecycle Commands

```powershell
# Clean build artifacts
mvn clean

# Compile main source
mvn compile

# Compile tests
mvn test-compile

# Run tests
mvn test

# Package without tests
mvn package -DskipTests

# Full build with tests
mvn clean install

# Generate Javadocs
mvn javadoc:javadoc
```

## Troubleshooting Steps

1. **Clear Maven cache** (if weird dependency errors):
   ```powershell
   Remove-Item -Recurse -Force ~\.m2\repository\org\junit
   mvn clean install -U
   ```

2. **Check Java version consistency**:
   ```powershell
   java -version
   mvn -version
   ```

3. **Force dependency update**:
   ```powershell
   mvn clean install -U
   ```

4. **Run with debug output**:
   ```powershell
   mvn clean compile -X
   ```

## Files Created for CI/CD

1. `.github/workflows/maven-build.yml` - GitHub Actions workflow
2. `pre-commit-check.bat` - Windows validation script
3. `pre-commit-check.sh` - Linux/Mac validation script

## Next Steps After This Fix

1. **Test the build locally:**
   ```powershell
   mvn clean compile
   ```

2. **Commit the changes:**
   ```powershell
   git add pom.xml src/ .github/
   git commit -m "Fix: Maven build configuration and JUnit dependencies"
   git push origin main
   ```

3. **Monitor GitHub Actions:**
   - Check the Actions tab in your repo
   - Verify the build passes

4. **Use pre-commit script before future pushes:**
   ```powershell
   .\pre-commit-check.bat
   ```
