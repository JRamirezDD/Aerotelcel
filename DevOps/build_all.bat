@echo off
setlocal

REM Change to the Services directory
cd ..\Services

REM Loop through each subdirectory in Services
for /d %%d in (*) do (
    echo Processing %%d...
    if exist "%%d\build_and_save_image.bat" (
        REM Found the build script; execute it
        echo Building and saving Docker image for %%d
        cd %%d
        call build_and_save_image.bat
        cd ..
    ) else (
        REM build script not found
        echo No build_and_save_image.bat found for %%d
    )
)

echo "Finished Building"

endlocal