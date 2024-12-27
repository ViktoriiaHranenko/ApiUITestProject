## PetStore API Test Automation Project

A test automation framework for the Swagger Petstore API, designed to demonstrate API and test automation skills.
This project is a work in progress. Some parts of the code may need further optimization or refactoring.

## Requirements
- **Java 21**
- **Lombok plugin** (ensure it's enabled in your IDE)

## Technologies Used
- **Retrofit**: Used for implementing API interfaces.
- **Lombok**: Simplifies the creation of models by reducing boilerplate code.
- **Allure**: Integrated for generating detailed and visually appealing test reports.
- **TestNG and Cucumber**: For organizing and running automated test suites.
- **Owner**: Simplifies the management of configuration properties, supporting multiple sources 
  (e.g., .properties files, system properties, environment variables).

## Key Features
- **Generic Response Model**:
  Responses are encapsulated in a ResponseModel, which includes:
  - HTTP status code 
  - Headers 
  - Response messages 
  - Generic body (defined in service methods)

- **Object Models**:
  Each entity (e.g., Pet, User) has its dedicated model to represent API request and response bodies.

- **Logging**:
  Added a LoggingInterceptor for logging HTTP requests and responses.

- **Test Organization**:
  Both TestNG and Cucumber frameworks are supported, with tests grouped logically (e.g., regression tests, unit tests).

- **Flexible Configuration Management**:
  Using the Owner library, the project supports:
  - Easy management of configuration properties through .properties files.
  - Integration with system properties and environment variables for dynamic configuration during execution.

## Notes
This project is designed to demonstrate test automation skills using the Swagger Petstore API.
Important considerations:

The Swagger Petstore API has limited support for handling error scenarios and often returns generic  
responses for invalid or erroneous requests. Due to these limitations, negative test scenarios have been 
excluded to avoid creating tests that would not align with real-world expectations or provide meaningful insights. 
Instead, the focus is placed on validating positive test flows and showcasing:
  - Clean, maintainable automation practices 
  - Well-structured and modular test design 
  - Scalability for future expansion

## Running Tests
This project uses Maven as the build tool. To run tests:
```bash
mvn clean test