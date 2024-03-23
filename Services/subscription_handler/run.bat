REM   Title: Script Source Code
REM   Author: Ramirez de Diego, Jorge
REM   Date: 2024
REM   Code version: 1.0
REM   Availability: https://github.com/JRamirezDD/Aerotelcel

@echo off
setlocal enabledelayedexpansion

REM Store the full path to the build/libs directory
set "libs_dir=%~dp0build\libs"
echo Library Directory: !libs_dir!

REM Ensure the path ends with a backslash
if not "!libs_dir:~-1!"=="\" set "libs_dir=!libs_dir!\"

REM Iterate over all .jar files in the directory
for %%f in (!libs_dir!*.jar) do (
    set "file=%%~nxf"
    echo Found: !file!

    REM Skip files containing '-plain'
    if "!file!" neq "!file:-plain=!" (
        echo Skipping: !file!
    ) else (
        echo Running: %%f
        REM Execute the jar file
        java -jar "%%f"
        if errorlevel 1 (
            echo Error running the jar file. Please check the path and file permissions.
        )
        goto :eof
    )
)
