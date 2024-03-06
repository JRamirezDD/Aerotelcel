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
#

# Stage 2: Package the built JAR into a runtime image
# Start with a base image containing Java runtime
FROM openjdk:17-slim
LABEL authors="jorge"

# Environment variables with default values for service URLs and ports
ENV SPRING_APPLICATION_NAME=logic-gateway
ENV SERVER_ADDRESS=127.0.0.1
ENV SERVER_PORT=8080

# Default URLs and Ports for services
ENV REPORTS_HANDLER_HOST=http://reports-handler
ENV REPORTS_HANDLER_PORT=10010
ENV SUBSCRIPTIONS_HANDLER_HOST=http://subscription-handler
ENV SUBSCRIPTIONS_HANDLER_PORT=10002
ENV SUBSCRIPTIONS_REDIS_HOST=http://subscription-redis
ENV SUBSCRIPTIONS_REDIS_PORT=10000

# Expose the application port
EXPOSE $SERVER_PORT

# Copy the built JAR from the builder stage
COPY build/libs/logic_gateway-0.0.1-SNAPSHOT.jar /app/
RUN ls -la /app

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/logic_gateway-0.0.1-SNAPSHOT.jar"]
