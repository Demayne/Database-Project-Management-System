@echo off
REM Pre-commit validation script for Windows
REM Run this before committing to catch build errors early

echo ================================================
echo Running Pre-Commit Validation
echo ================================================

echo.
echo [1/3] Cleaning previous builds...
call mvn clean
if %ERRORLEVEL% neq 0 (
    echo ERROR: Maven clean failed
    exit /b 1
)

echo.
echo [2/3] Compiling source and tests...
call mvn compile test-compile
if %ERRORLEVEL% neq 0 (
    echo ERROR: Compilation failed - fix errors before committing
    exit /b 1
)

echo.
echo [3/3] Running tests...
call mvn test
if %ERRORLEVEL% neq 0 (
    echo ERROR: Tests failed - fix failing tests before committing
    exit /b 1
)

echo.
echo ================================================
echo ✓ All checks passed! Safe to commit.
echo ================================================
exit /b 0
