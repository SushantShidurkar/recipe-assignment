# Recipes Application
>Simple Recipes application built using angular9 and Spring Boot 2.3
---
## Features of the Application:
- Application has dashboard to display already saved recipes.
- User can add,delete and modify recipes.
- Application's GUI is responsive in nature.
- Application's backend is developed in Spring Boot with restful architecture.
- Application's backend has javadoc.

## Steps to run the application:
- Download the source code of the application.
- Go to "backend" folder ,and run ```mvn clean install``` command.
- Once done, got to backend/target and run ```java -jar backend-0.0.1-SNAPSHOT``` command.
- To run front end application, go to /frontend and run ```npm install```.
- Once done, run ```ng serve``` and see application live on http://localhost:4200/.

## Why to use Angular 9 & Spring Boot?
- Angular 9
  - Faster build and simplified MVC architecture for single page application.
   - Easy to implement Angular material UI which is responsive in nature.
- Spring Boot
  - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that we can "just run".
  - Annotation based developement makes it easy for develop code faster.
  - Can run using standalone jar which internally uses tomcat web container.
  - JPA and Junit support.
