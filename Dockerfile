# Step 1: Build stage
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy configuration and source files
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./
COPY src ./src

# Grant execution permission
RUN chmod +x gradlew

# Build the application directly
RUN ./gradlew bootJar -x test --no-daemon

# Step 2: Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]