# Use a lightweight JDK base image
FROM amazoncorretto:21-alpine

# Set the working directory
WORKDIR /app

# Copy build files
COPY build/libs/consumer-*.jar app.jar

# Expose the port (optional, for metrics or APIs)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
