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
    if [ -f "${dir}/run.sh" ]; then
        # Found the run script; execute it in the background
        echo "Running Service $dir in the background..."
        (cd "$dir" && ./run.sh &)
    else
        # run.sh not found
        echo "No run.sh found for $dir"
    fi
done

# Wait for all background jobs to finish
wait

echo "Finished Running all Files"