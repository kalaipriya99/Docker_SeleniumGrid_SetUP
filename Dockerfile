# Base image with Maven and JDK
FROM maven:3.9-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml /app/pom.xml
COPY testng.xml /app/testng.xml
COPY src /app/src
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/bin/bash", "/entrypoint.sh"]
