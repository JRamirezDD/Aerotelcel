# Stage 1: Build the application
#FROM openjdk:17-slim as builder
#WORKDIR /app
#
#COPY build.gradle settings.gradle /app/
#COPY src /app/src
#COPY gradlew /app/
#COPY gradle /app/gradle
#
## Convert line endings of gradlew to those compatible to linux
#RUN apt-get update && apt-get install -y dos2unix
#RUN dos2unix /app/gradlew
#RUN chmod +x /app/gradlew # Ensure gradlew is executable
#RUN /app/gradlew build --no-daemon # Attempt to run the build with an absolute path
#
## Debugging: Ensure files are copied correctly
#RUN ls -la /app/build/libs
#
## Debugging: Check the current working directory
#RUN pwd


# Stage 2: Package the built JAR into a runtime image
# Start with a base image containing Java runtime
FROM openjdk:17-slim
LABEL authors="jorge"

# Host Configuration
ENV SPRING_APPLICATION_NAME=notifications-handler
ENV SERVER_ADDRESS=127.0.0.1
ENV SERVER_PORT=10030

# Subscription Redis
ENV SUBSCRIPTION_REDIS_ADDRESS=http://localhost
ENV SUBSCRIPTION_REDIS_PORT=10000
ENV SUBSCRIPTION_REDIS_CONTROLLER_NAME=subscription-redis

# Flights DB
ENV DATABASE_URL=jdbc:mysql://localhost:3306/Aerotelcel
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=password
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 10000 available to the world outside this container
EXPOSE $SERVER_PORT

# Copy the built JAR from the builder stage
COPY build/libs/notifications_handler-0.0.1-SNAPSHOT.jar /app/
RUN ls -la /app

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/notifications_handler-0.0.1-SNAPSHOT.jar"]

