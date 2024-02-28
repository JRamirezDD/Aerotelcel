FROM ubuntu:latest
LABEL authors="jorge"

# docker build -t Aerotelcel/subscription-handler:1.0 .

# Start with a base image containing Java runtime
FROM openjdk:17-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 10000 available to the world outside this container
EXPOSE 10010

# Add the application's jar to the container
ADD build/libs/reports-handler-0.0.1-SNAPSHOT.jar reports-handler.jar

WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "/reports-handler.jar"]

