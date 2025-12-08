"# Wordle-Duel" 

The app is hosted publicly at https://wordle-duel.onrender.com/



Wordle Duel – How to Run locally (Windows)

These instructions assume:

Java 17 is installed

You have downloaded this project from GitHub (ZIP or clone)

You do NOT need to install Maven or Spring Boot separately.
This project contains the Maven Wrapper (mvnw) which handles everything automatically.

1. Open a PowerShell window

Navigate into the project folder, for example:

cd "C:\Users\YourName\Downloads\Wordle_Duel"


Then go into the application code:

cd code\wordleduel


Inside this folder you should see:

pom.xml

mvnw

src

etc.

2. Start the application

Run this:

.\mvnw spring-boot:run


The first run may take a few minutes.
Maven Wrapper will automatically download everything needed.

When it finishes, the Spring Boot server is running on your computer on port 8080.

3. Open the game

Open your web browser and go to:

http://localhost:8080


You should now see the Wordle Duel main page.


