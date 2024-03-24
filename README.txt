### Credits:

Application Developed by:
Jorge Ramirez de Diego
Javier Ortega Mendoza
Marcos Gonzalez Fernandez


### Initial Notes

- Ensure that all requirements are met and correctly configured before starting the launch procedures.
- In case you are having issues with any of the described steps, please send an email with a detailed explanation to 'no.reply.aerotelcel@gmail.com' in order to receive assistance.


# Application Front-end Setup Guide



# Application Back-end Setup Guide

This guide is designed to help you set up and launch the back-end components of our application quickly and efficiently.
Please follow the steps below carefully to ensure a successful setup.

## Requirements

Before proceeding, ensure you have the following software installed in your system:

- **Java Development Kit (JDK) 17.0.10+7**
  - [Download JDK](https://adoptium.net/temurin/)

- **Docker Desktop 4.28.0**
  - [Download Docker Desktop](https://www.docker.com/products/docker-desktop/)

- **MySQL and Redis Instances**
  - MySQL should be accessible on localhost, port 3306, with root password `password`.
  - Redis should be accessible on localhost, port 6379.

## Launch Procedures

We offer two methods for launching the back-end services: the Default Launch Procedure and the more prone to failure Docker-Compose Launch Procedure.

### Default Launch Procedure

1. **Configure Environment Variables:**
   - Modify the `application.properties` files within each microservice to adjust any environment variables, particularly if your MySQL or Redis configurations differ from the expected configurations.

2. **Launch Services:**
   - Navigate to the project's `DevOps` directory.
   - For Windows, run `build_all.bat` followed by `run_all.bat`.
   - For Linux, run `build_all.sh` followed by `run_all.sh`.

### Docker-Compose Launch Procedure (Alternative)

1. **Prepare Docker Compose:**
   - Ensure Docker Desktop is installed and running.
   - Directly modify the `docker-compose.yaml` in the `DevOps` directory if you need to adjust environment variables for the microservices.

2. **Launch Services Using Docker Compose:**
   - In the `DevOps` directory, execute the following commands:
     - For Windows, run 'build_all.bat', then execute `docker-compose up --build`.
     - For Linux, run 'build_all.sh', then execute `docker-compose up --build`



