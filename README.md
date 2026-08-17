# Heavens-Eleven
Course Manager - CPAN228 Final Project

## About
This Course Manager portal, developed in Spring Boot, is a simple interface that allows students to register for an account and monitor the courses they are registered for. Student users can view all available courses, and teacher users can assign courses to registered students.

## Prerequisites
Before running this application, make sure the following are installed:
- Java
- Maven
- MySQL Server
- MySQL Workbench (Optional)

## Configuration and Running

### Dev Profile (H2)
Uses an in-memory H2 database, and does not require MySQL setup.

To run the Dev profile, run the following using PowerShell in the project directory:
```powershell
mvn spring-boot:run "-Dspring-boot.run.profiles=dev"
```

Once it is started, it will be available at:
- http://localhost:8081

To access the H2 Console (located in the nav bar), log into the application with the following credentials:
- Username: admin
- Password: admin123

### Production Profile (MySQL)
Uses a persistent MySQL database, which will require set up.

To set up the database, run the following in MySQL Workbench, or your SQL client of choice:
```sql
CREATE DATABASE heavens_eleven;
```

With the database created, set the following environment variables in PowerShell, replacing the password with your MySQL account password:
```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="YOUR_MYSQL_PASSWORD_HERE"
```

These environment variables are used to connect to MySQL. Once they are set, you can run the Production profile using PowerShell in the project directory:
```powershell
mvn spring-boot:run "-Dspring-boot.run.profiles=prod"
```

Finally, once it is started, it will be available at:
- http://localhost:8082

## Contributions
For this project, Nour Harrak handled a majority of the front-end development, while Kwan Min Lee handled most of the back-end development. However, we routinely switched roles to ensure we gained experience with as much unique development workload as possible. For example, Nour took up a lot of back-end development workload during Deliverable 2's development phase, while Kwan Min took a hand in front-end development during Deliverable 1's development phase.
