# Apollo Service


## Installation of prerequisites

* requires Java SDK 17+ and Gradle 8.5+ to build and run
* requires PostgreSQL 9+ to run
* requires IntelliJ IDEA to run the test http requests in /rest-request folder in project root

### Database setup

After installing clean PostgreSQL instance (or you can reuse your existing), run this file on your PostgreSQL instance:

> src/main/resources/ddl/001-create_db.sql

This will create the necessary database, with user and table.


## Build and run the application 

To build the application, run this cmd:

> ./gradlew build

To run the application with profile "local", run this cmd:

> ./gradlew bootRun --args=--spring.profiles.active=local
