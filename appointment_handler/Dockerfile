# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and other Maven files to download dependencies
COPY pom.xml ./
COPY src ./src

# Download dependencies and build the application
RUN mvn clean package -DskipTests -X

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target /app/target

EXPOSE 8090

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/target/demo-0.0.1-SNAPSHOT.jar"]