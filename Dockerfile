FROM ubuntu:latest

# Use an official OpenJDK runtime as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR ./

# Copy the build output from the target folder to the container
COPY demo-0.0.1-SNAPSHOT.jar ./

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
