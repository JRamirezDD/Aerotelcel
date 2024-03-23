#    Title: Dockerfile
#    Author: Ramirez de Diego, Jorge
#    Date: 2024
#    Code version: 1.0
#    Availability: https://github.com/JRamirezDD/Aerotelcel

## Stage 1: Build the application
#FROM gradle:jdk17 as builder
#WORKDIR /app
#
## Copy the Gradle configuration files
#COPY build.gradle settings.gradle /app/
#
## Copy the source code
#COPY src /app/src
#
## Optionally copy gradlew and gradle wrapper
## COPY gradlew /app/
## COPY gradle /app/gradle
#
## Run the build
#RUN gradle build --no-daemon


# Stage 2: Package the built JAR into a runtime image
# Start with a base image containing Java runtime
FROM openjdk:17-slim
LABEL authors="jorge"

# Host Configuration
ENV SPRING_APPLICATION_NAME=subscriptions-handler
ENV SERVER_ADDRESS=127.0.0.1
ENV SERVER_PORT=10002

# Subscription Redis
ENV SUBSCRIPTION_REDIS_ADDRESS=http://127.0.0.1
ENV SUBSCRIPTION_REDIS_PORT=10000
ENV SUBSCRIPTION_REDIS_CONTROLLER_NAME=subscription-redis

# Expose the application port
EXPOSE $SERVER_PORT

# Copy the built JAR from the builder stage
COPY build/libs/subscription_handler-0.0.1-SNAPSHOT.jar /app/
RUN ls -la /app

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/subscription_handler-0.0.1-SNAPSHOT.jar"]

