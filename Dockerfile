# Docker Deployment Configuration for PoiseDMS
# Author: Demayne Govender

## ==============================
## Build stage: compile with Maven
## ==============================
FROM maven:3.9.6-eclipse-temurin-11 AS build

WORKDIR /workspace

# First copy pom to leverage Docker cache
COPY pom.xml ./

# Pre-fetch dependencies (faster subsequent builds)
RUN mvn -q -e -B -DskipTests dependency:go-offline

# Copy source
COPY src ./src

# Build fat JAR with dependencies
RUN mvn -q -e -B -DskipTests clean package assembly:single

## ==============================
## Runtime stage: small JRE image
## ==============================
FROM eclipse-temurin:11-jre

# Set working directory
WORKDIR /app

# Create necessary directories
RUN mkdir -p /app/logs /app/config && useradd -m appuser
USER appuser

# Copy built artifact from builder
COPY --from=build /workspace/target/PoiseDMS-2.0.0-jar-with-dependencies.jar /app/PoiseDMS.jar

# Copy configuration files (allows overriding classpath resources)
COPY src/config/*.properties /app/config/
COPY src/logback.xml /app/config/logback.xml

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV APP_HOME=/app

# Expose port (reserved for future APIs)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=5s CMD java -version || exit 1

# Run application with /app/config on classpath so database.properties can override
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -cp /app/PoiseDMS.jar:/app/config Main"]

# Labels
LABEL maintainer="Demayne Govender"
LABEL version="2.0.0"
LABEL description="PoiseDMS - Database Project Management System"
