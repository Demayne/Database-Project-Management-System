#!/bin/bash
# =====================================================
# PoiseDMS Build Script for Unix/Linux/MacOS
# =====================================================

echo ""
echo "========================================"
echo "  PoiseDMS Build Script"
echo "========================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    exit 1
fi

echo "[1/5] Cleaning previous builds..."
mvn clean
if [ $? -ne 0 ]; then
    echo "ERROR: Clean failed"
    exit 1
fi

echo ""
echo "[2/5] Compiling source code..."
mvn compile
if [ $? -ne 0 ]; then
    echo "ERROR: Compilation failed"
    exit 1
fi

echo ""
echo "[3/5] Running tests..."
mvn test
if [ $? -ne 0 ]; then
    echo "WARNING: Tests failed"
fi

echo ""
echo "[4/5] Packaging application..."
mvn package
if [ $? -ne 0 ]; then
    echo "ERROR: Packaging failed"
    exit 1
fi

echo ""
echo "[5/5] Creating executable JAR with dependencies..."
mvn assembly:single
if [ $? -ne 0 ]; then
    echo "ERROR: Assembly failed"
    exit 1
fi

echo ""
echo "========================================"
echo "  Build Completed Successfully!"
echo "========================================"
echo ""
echo "Executable JAR: target/PoiseDMS-2.0.0-jar-with-dependencies.jar"
echo ""
echo "To run the application:"
echo "  java -jar target/PoiseDMS-2.0.0-jar-with-dependencies.jar"
echo ""
