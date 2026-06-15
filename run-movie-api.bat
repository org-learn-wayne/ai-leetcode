@echo off
setlocal

set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
if not exist "%JAVA_EXE%" set "JAVA_EXE=java"

echo Starting Movie API on http://localhost:8080/
start "" "%JAVA_EXE%" --add-modules jdk.httpserver -cp target\classes interview.MovieServer
timeout /t 2 /nobreak >nul
start "" http://localhost:8080/
