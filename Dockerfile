# Use a base image with Java
FROM openjdk:17-jdk-slim

# Add metadata
LABEL maintainer="<you@example.com>"

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/job-board-app.jar job-board-app.jar

# Expose the port (optional, for documentation)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/job-board-app.jar"]
