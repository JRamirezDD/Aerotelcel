#!/bin/bash

#   Title: Script Source Code
#   Author: Ramirez de Diego, Jorge
#   Date: 2024
#   Code version: 1.0
#   Availability: https://github.com/JRamirezDD/Aerotelcel

# Store the full path to the build/libs directory
libs_dir="$(dirname "$0")/build/libs"
echo "Library Directory: $libs_dir"

# Ensure the path ends with a slash
[[ "${libs_dir}" != */ ]] && libs_dir="${libs_dir}/"

# Iterate over all .jar files in the directory
for f in "$libs_dir"*.jar; do
    file=$(basename "$f")
    echo "Found: $file"

    # Skip files containing '-plain'
    if [[ "$file" == *"-plain"* ]]; then
        echo "Skipping: $file"
    else
        echo "Running: $file"
        # Execute the jar file
        java -jar "$f"
        if [ $? -ne 0 ]; then
            echo "Error running the jar file. Please check the path and file permissions."
            exit 1
        fi
        break
    fi
done