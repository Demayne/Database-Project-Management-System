#!/bin/bash
# Pre-commit validation script for Linux/Mac
# Run this before committing to catch build errors early

set -e

echo "================================================"
echo "Running Pre-Commit Validation"
echo "================================================"

echo ""
echo "[1/3] Cleaning previous builds..."
mvn clean

echo ""
echo "[2/3] Compiling source and tests..."
mvn compile test-compile

echo ""
echo "[3/3] Running tests..."
mvn test

echo ""
echo "================================================"
echo "✓ All checks passed! Safe to commit."
echo "================================================"
