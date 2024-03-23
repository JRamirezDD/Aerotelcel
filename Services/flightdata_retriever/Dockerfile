#    Title: Dockerfile
#    Author: Ramirez de Diego, Jorge
#    Date: 2024
#    Code version: 1.0
#    Availability: https://github.com/JRamirezDD/Aerotelcel

FROM openjdk:17-slim
LABEL authors="javie"

# Host Configuration
ENV SPRING_APPLICATION_NAME=flightdata_retriever
ENV SERVER_ADDRESS=127.0.0.1
ENV SERVER_PORT=10030

# Flights DB
ENV DATABASE_URL=jdbc:mysql://localhost:3306/Aerotelcel
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=password
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# FlightData Handler api
ENV FLIGHTDATA_HANDLER_ADDRESS=http://flightdata_handler
ENV FLIGHTDATA_HANDLER_PORT=10020
ENV FLIGHTDATA_HANDLER_CONTROLLER_NAME=flightdata_handler

# Expose the application port
EXPOSE $SERVER_PORT

# Copy the built JAR from the builder stage
COPY build/libs/flightdata_retriever-0.0.1-SNAPSHOT.jar /app/
RUN ls -la /app

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/flightdata_retriever-0.0.1-SNAPSHOT.jar"]