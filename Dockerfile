# Base image
FROM openjdk:21-jdk-slim

# Install Redis server
RUN apt-get update && apt-get install -y redis-server

# Set the working directory
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle /app/

# Copy the gradle wrapper
COPY gradlew /app/
COPY gradle /app/gradle

# Download dependencies
RUN ./gradlew dependencies

# Copy the rest of the application code
COPY . /app/

# Build the application
RUN ./gradlew bootJar

# Expose ports
EXPOSE 8080 6379

# Command to run Redis server
CMD redis-server --daemonize yes && \
    java -jar build/libs/mall-0.0.1-SNAPSHOT.jar
