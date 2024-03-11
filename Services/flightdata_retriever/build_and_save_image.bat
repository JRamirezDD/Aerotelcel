@echo off
SETLOCAL EnableDelayedExpansion

REM Change directory to the script's location
cd /d "%~dp0"
echo Current directory: %cd%

REM Define variables
SET PROJECT_DIR=.
SET IMAGE_NAME=flightdata-retriever
SET IMAGE_TAG=latest
SET SAVE_DIR=..\..\Docker\Images
SET GRADLE_VERSION=8.6
SET GRADLE_DIR=\.gradle\"%GRADLE_VERSION%"

REM Step 0: Ensure gradle wrapper exists
echo Checking Gradle wrapper is of version "%GRADLE_VERSION%"...
IF NOT EXIST "%GRADLE_DIR%" call gradle wrapper --gradle-version "%GRADLE_VERSION%"

REM Step 1: Build the Gradle project
echo Building Gradle project...
call gradlew.bat build

IF NOT "%ERRORLEVEL%" == "0" (
    echo Gradle build failed, exiting...
    exit /b 1
)

REM Step 2: Build the Docker image
REM echo Building Docker image...
REM docker build -t %IMAGE_NAME%:%IMAGE_TAG% %PROJECT_DIR%

REM IF NOT "%ERRORLEVEL%" == "0" (
REM    echo Docker build failed, exiting...
REM     exit /b 1
REM )

REM Step 3: Save the Docker image to a file
REM echo Saving Docker image to a file...
REM docker save %IMAGE_NAME%:%IMAGE_TAG% > "%SAVE_DIR%\%IMAGE_NAME%_%IMAGE_TAG%.tar"

echo Process completed successfully.
ENDLOCAL
