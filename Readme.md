# Project Setup & Execution Guide

## Starting the Jenkins Server

1. Open **Command Prompt**.
2. Navigate to the Jenkins directory where the `jenkins.war` file is located:
   C:\Jenkins
3. Run the following command to start the Jenkins server:
   java -jar jenkins.war -httpPort=8080

The Jenkins server will be accessible at:
http://localhost:8080

---

## Running Tests Using Maven

1. Open **Command Prompt**.
2. Navigate to the **framework** directory of the project.
3. Run the following command:
   mvn test -P<ProfileName>

Example:
mvn test -PCucumberTests


---
Changes to check Jenkins job again