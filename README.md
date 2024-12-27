PetStore API Test Automation Project

Requirements
- Java 21
- Lombok plugin (ensure it's enabled in your IDE)

Technologies Used
  Retrofit: Used for implementing API interfaces.
  Lombok: Simplifies the creation of models by reducing boilerplate code.
  Allure: Integrated for generating detailed and visually appealing test reports.
  TestNG and Cucumber: For organizing and running automated test suites.

Key Features
  Generic Response Model:
  Responses are encapsulated in a ResponseModel, which includes:
  - HTTP status code 
  - Headers 
  - Response messages 
  - Generic body (defined in service methods)

  Object Models:
  Each entity (e.g., Pet, User) has its dedicated model to represent API request and response bodies.

  Logging:
  Added a LoggingInterceptor for logging HTTP requests and responses.

  Test Organization:
  Both TestNG and Cucumber frameworks are supported, with tests grouped logically (e.g., regression tests, unit tests).


Project structure:
src/main/java/
├── org
│   ├── common
│   │   ├── api
│   │   ├── interceptors
│   │   └── models
│   ├── config
│   └── pet_store/api
│       ├── endpoints
│       ├── models
│       │   ├── pet
│       │   └── user
│       └── services
└── resources

src/test/java
├── java
│   ├── common
│   │   └── unit
│   ├── cucumber/org/pet_store
│   │   ├── runner
│   │   └── steps
│   └── testng/org/pet_store/regression
│       ├── pet
│       └── user
└── resources
    ├── features
    └── xmls
    

Notes
This project is designed to demonstrate test automation skills using the Swagger Petstore API.
Important considerations:

The Swagger Petstore API has limited support for handling error scenarios and often returns generic  
responses for invalid or erroneous requests. Due to these limitations, negative test scenarios have been 
excluded to avoid creating tests that would not align with real-world expectations or provide meaningful insights. 
Instead, the focus is placed on validating positive test flows and showcasing:
  - Clean, maintainable automation practices 
  - Well-structured and modular test design 
  - Scalability for future expansion
