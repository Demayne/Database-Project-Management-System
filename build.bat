@echo off
REM =====================================================
REM PoiseDMS Build Script for Windows
REM =====================================================

echo.
echo ========================================
echo   PoiseDMS Build Script
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    pause
    exit /b 1
)

REM Check if Java is installed
where java >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    pause
    exit /b 1
)

echo [1/5] Cleaning previous builds...
call mvn clean
if %ERRORLEVEL% neq 0 (
    echo ERROR: Clean failed
    pause
    exit /b 1
)

echo.
echo [2/5] Compiling source code...
call mvn compile
if %ERRORLEVEL% neq 0 (
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

echo.
echo [3/5] Running tests...
call mvn test
if %ERRORLEVEL% neq 0 (
    echo WARNING: Tests failed
)

echo.
echo [4/5] Packaging application...
call mvn package
if %ERRORLEVEL% neq 0 (
    echo ERROR: Packaging failed
    pause
    exit /b 1
)

echo.
echo [5/5] Creating executable JAR with dependencies...
call mvn assembly:single
if %ERRORLEVEL% neq 0 (
    echo ERROR: Assembly failed
    pause
    exit /b 1
)

echo.
echo ========================================
echo   Build Completed Successfully!
echo ========================================
echo.
echo Executable JAR: target\PoiseDMS-2.0.0-jar-with-dependencies.jar
echo.
echo To run the application:
echo   java -jar target\PoiseDMS-2.0.0-jar-with-dependencies.jar
echo.

pause
