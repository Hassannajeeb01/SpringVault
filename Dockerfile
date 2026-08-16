# Step 1: Build stage with JDK 21
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Copy configuration and source files
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./
COPY src ./src

# Make wrapper executable
RUN chmod +x gradlew

# Build the JAR (skipping tests)
RUN ./gradlew bootJar -x test --no-daemon

# Step 2: Runtime stage with JRE 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]