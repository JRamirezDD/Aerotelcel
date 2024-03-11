FROM openjdk:17-slim
LABEL authors="javie"

# Host Configuration
ENV SPRING_APPLICATION_NAME=flightdata-handler
ENV SERVER_ADDRESS=127.0.0.1
ENV SERVER_PORT=10020

# Flights DB
ENV DATABASE_URL=jdbc:mysql://Aerotelcel-MySQL:3306/Aerotelcel
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=password
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# COPY DATABASES
# Copy database for airline:
COPY src/main/resources/airlinesdb.sql /docker-entrypoint-initdb.d/
# Copy database for airports:
COPY src/main/resources/mainairportdb.sql /docker-entrypoint-initdb.d/
# Copy database for flights:
COPY src/main/resources/flights.sql /docker-entrypoint-initdb.d/
# Copy database for departures:
COPY src/main/resources/departures.sql /docker-entrypoint-initdb.d/
# Copy database for arrivals:
COPY src/main/resources/arrivals.sql /docker-entrypoint-initdb.d/

# Copy python scripts:
COPY src/main/pythonFiles /app/pythonFiles

# Add a volume pointing to /tmp
VOLUME /tmp

# Expose the application port
EXPOSE $SERVER_PORT

# Copy the built JAR from the builder stage
COPY build/libs/flightdata_handler-0.0.1-SNAPSHOT.jar /app/

RUN ls -la /app

RUN ls -la /docker-entrypoint-initdb.d

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/flightdata_handler-0.0.1-SNAPSHOT.jar"]