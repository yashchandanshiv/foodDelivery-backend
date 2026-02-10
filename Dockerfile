# Use official Maven image with Java 21
FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

# Copy everything
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/pizza-backend-0.0.1-SNAPSHOT.jar"]
