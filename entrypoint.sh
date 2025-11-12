#!/bin/bash
set -e
echo "=============================="
echo "Starting Selenium Maven build"
echo "=============================="
# Run Maven with reports going to /app/reports folder
mvn clean test -Dextent.output.dir=/app/reports

echo "=============================="
echo "Build finished successfully"
echo "=============================="