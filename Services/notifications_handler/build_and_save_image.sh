#!/bin/bash

# Navigate to the script's directory to ensure relative paths work correctly
cd "$(dirname "$0")" || exit

# Step 1: Define variables
PROJECT_DIR="." # Current directory as the script is inside the project directory
IMAGE_NAME="reports-handler"
IMAGE_TAG="latest" # You can change this to any tag you prefer
SAVE_DIR="../../Docker/Images" # Relative path from the script to the Images directory

# Ensure SAVE_DIR exists
mkdir -p "$SAVE_DIR"

# Step 2: Build the Gradle project
echo "Building Gradle project..."
apt-get update && apt-get install -y dos2unix
dos2unix /app/gradlew
chmod +x /app/gradlew # Ensure gradlew is executable
./gradlew build

# Check if build was successful
if [ $? -ne 0 ]; then
    echo "Gradle build failed, exiting..."
    exit 1
fi

## Step 3: Build the Docker image
#echo "Building Docker image..."
#docker build -t "$IMAGE_NAME:$IMAGE_TAG" .
#
## Check if Docker build was successful
#if [ $? -ne 0 ]; then
#    echo "Docker build failed, exiting..."
#    exit 1
#fi

# Step 4: Save the Docker image to a file
# echo "Saving Docker image to a file..."
# docker save "$IMAGE_NAME:$IMAGE_TAG" > "$SAVE_DIR/${IMAGE_NAME}_${IMAGE_TAG}.tar"

echo "Process completed successfully."
