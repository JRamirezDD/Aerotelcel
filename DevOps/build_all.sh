#!/bin/bash

#   Title: build_and_save_image Bash Script Source Code
#   Author: Ramirez de Diego, Jorge
#   Date: 2024
#   Code version: 1.0
#   Availability: https://github.com/JRamirezDD/Aerotelcel

# Navigate to the Services directory
cd ../Services

# Loop through each subdirectory in Services
for dir in */ ; do
    echo "Processing $dir..."
    if [ -f "${dir}build_and_save_image.sh" ]; then
        # Found the build script; execute it
        echo "Building and saving Docker image for $dir"
        (cd "$dir" && ./build_and_save_image.sh)
    else
        # build script not found
        echo "No build_and_save_image.sh found for $dir"
    fi

echo "Finished Building"
done
