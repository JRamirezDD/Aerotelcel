REM   Title: build_and_save_image Bat Script Source Code
REM   Author: Ramirez de Diego, Jorge
REM   Date: 2024
REM   Code version: 1.0
REM   Availability: https://github.com/JRamirezDD/Aerotelcel

@echo off
setlocal

REM Change to the Services directory
cd ..\Services

REM Loop through each subdirectory in Services
for /d %%d in (*) do (
    echo Processing %%d...
    if exist "%%d\run.bat" (
        REM Found the run script; execute it in a new cmd window
        echo Running Service %%d in a new cmd window...
        start "%%d" cmd /c cd %%d ^&^& call run.bat
    ) else (
        REM run.bat not found
        echo No run.bat found for %%d
    )
)

echo Finished Running all Files

endlocal