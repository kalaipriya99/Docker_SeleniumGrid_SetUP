#!/bin/bash
set -e
echo "=============================="
echo "Starting Selenium Maven build"
echo "=============================="

# Safely delete target folder contents without failing
if [ -d "/app/target" ]; then
  echo "Cleaning /app/target manually..."
  rm -rf /app/target/* || true
fi

# Now run Maven (won’t fail on delete)
mvn test -Dextent.output.dir=/app/reports

echo "=============================="
echo "Build finished successfully"
echo "=============================="