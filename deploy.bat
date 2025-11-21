@echo off
REM =====================================================
REM PoiseDMS Deployment Script for Windows
REM =====================================================

echo.
echo ========================================
echo   PoiseDMS Deployment Script
echo ========================================
echo.

REM Set variables
set APP_NAME=PoiseDMS
set APP_VERSION=2.0.0
set JAR_FILE=target\%APP_NAME%-%APP_VERSION%-jar-with-dependencies.jar
set DEPLOY_DIR=deploy
set CONFIG_DIR=%DEPLOY_DIR%\config
set LOGS_DIR=%DEPLOY_DIR%\logs

echo [1/4] Checking build artifact...
if not exist "%JAR_FILE%" (
    echo ERROR: JAR file not found. Please build the project first.
    echo Run: build.bat
    pause
    exit /b 1
)

echo [2/4] Creating deployment directories...
if not exist "%DEPLOY_DIR%" mkdir "%DEPLOY_DIR%"
if not exist "%CONFIG_DIR%" mkdir "%CONFIG_DIR%"
if not exist "%LOGS_DIR%" mkdir "%LOGS_DIR%"

echo [3/4] Copying files to deployment directory...
copy "%JAR_FILE%" "%DEPLOY_DIR%\%APP_NAME%.jar"
copy "src\config\database.properties" "%CONFIG_DIR%\" 2>nul
copy ".env.example" "%DEPLOY_DIR%\" 2>nul
copy "README.md" "%DEPLOY_DIR%\" 2>nul

echo [4/4] Creating run script...
(
echo @echo off
echo java -jar %APP_NAME%.jar
echo pause
) > "%DEPLOY_DIR%\run.bat"

echo.
echo ========================================
echo   Deployment Completed Successfully!
echo ========================================
echo.
echo Deployment directory: %DEPLOY_DIR%
echo.
echo To run the application:
echo   cd %DEPLOY_DIR%
echo   run.bat
echo.
echo IMPORTANT: Update database credentials in config\database.properties
echo.

pause
