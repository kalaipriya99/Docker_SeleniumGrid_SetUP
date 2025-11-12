#!/bin/bash
set -e
echo "=============================="
echo "Starting Selenium Maven build"
echo "=============================="
# Remove old target folder inside container only
if [ -d "/app/target" ]; then
    echo "Removing old target folder..."
    rm -rf /app/target || true
fi
# Run Maven with reports going to /app/reports folder
mvn clean test -Dextent.output.dir=/app/reports

echo "=============================="
echo "Build finished successfully"
echo "=============================="