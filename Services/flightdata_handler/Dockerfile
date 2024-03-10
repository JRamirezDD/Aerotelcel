FROM openjdk:17-slim
LABEL authors="javie"

# Host Configuration
ENV SPRING_APPLICATION_NAME=flightdata_handler
ENV SERVER_ADDRESS=127.0.0.1
ENV SERVER_PORT=10020

# Flights DB
ENV DATABASE_URL=jdbc:mysql://Aerotelcel-MySQL:3306/Aerotelcel
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=password
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Add a volume pointing to /tmp
VOLUME /tmp

# Copy database for airline:
COPY ./Services/flightdata_handler/src/main/resources/openskytestdb_airlinesdb.sql /docker-entrypoint-initdb.d/

# Copy database for airports:
COPY ./Services/flightdata_handler/src/main/resources/openskytestdb_airportsdb.sql /docker-entrypoint-initdb.d/

# Expose the application port
EXPOSE $SERVER_PORT

# Copy the built JAR from the builder stage
COPY build/libs/flightdata_handler-0.0.1-SNAPSHOT.jar /app/

RUN ls -la /app

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/flightdata_handler-0.0.1-SNAPSHOT.jar"]