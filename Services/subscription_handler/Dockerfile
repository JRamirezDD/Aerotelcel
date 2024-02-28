FROM ubuntu:latest
LABEL authors="jorge"

# docker build -t Aerotelcel/subscription-handler:1.0 .

# Start with a base image containing Java runtime
FROM openjdk:17-slim

# Set default environment variables
ENV SPRING_APPLICATION_NAME=subscriptions-handler
ENV SERVER_ADDRESS=localhost
ENV SERVER_PORT=10002

# Connected Controllers
ENV SUBSCRIPTION_REDIS_ADDRESS=localhost
ENV SUBSCRIPTION_REDIS_PORT=10000
ENV SUBSCRIPTION_CONTROLLER_NAME=subscription-redis
ENV SUBSCRIPTION_CONTROLLER_PATH=api/subscription-redis


# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 10000 available to the world outside this container
EXPOSE 10002

# Add the application's jar to the container
ADD build/libs/subscription_handler-0.0.1-SNAPSHOT.jar subscription_handler.jar

WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "/subscription_handler.jar"]

