@echo off
SETLOCAL EnableDelayedExpansion

REM Change directory to the script's location
cd /d "%~dp0"
echo Current directory: %cd%

REM Define variables
SET PROJECT_DIR=.
SET IMAGE_NAME=reports-handler
SET IMAGE_TAG=latest
SET SAVE_DIR=..\..\Docker\Images

REM Ensure the SAVE_DIR exists
IF NOT EXIST "%SAVE_DIR%" mkdir "%SAVE_DIR%"

REM Step 1: Build the Gradle project
echo Building Gradle project...
call gradlew.bat build

IF NOT "%ERRORLEVEL%" == "0" (
    echo Gradle build failed, exiting...
    exit /b 1
)

REM Step 2: Build the Docker image
echo Building Docker image...
docker build -t %IMAGE_NAME%:%IMAGE_TAG% %PROJECT_DIR%

IF NOT "%ERRORLEVEL%" == "0" (
    echo Docker build failed, exiting...
    exit /b 1
)

REM Step 3: Save the Docker image to a file
REM echo Saving Docker image to a file...
REM docker save %IMAGE_NAME%:%IMAGE_TAG% > "%SAVE_DIR%\%IMAGE_NAME%_%IMAGE_TAG%.tar"

echo Process completed successfully.
ENDLOCAL
