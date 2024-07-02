# Base image
FROM openjdk:21-jdk-slim

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

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "build/libs/mall-0.0.1-SNAPSHOT.jar"]
