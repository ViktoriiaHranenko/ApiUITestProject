Requirements for project:
Java 21, Lombok plugin
Встановіть Lombok Plugin у вашій IDE (наприклад, IntelliJ IDEA):

У IntelliJ IDEA перейдіть до File > Settings > Plugins, 
знайдіть Lombok і встановіть його.
Увімкніть обробку анотацій:

У IntelliJ IDEA перейдіть до File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors 
і переконайтеся, що опція Enable annotation processing увімкнена.

Technologies used for project:
Retrofit library for api interfaces implementation.

Responses are represented as Response model as
the generic model, which store information about 
response status code, headers, messages and generic body
that is defined in service methods.

For each object were created separate models as Pet, User etc.

For logging was added LoggingInterceptor

Project structure:
src/main/java/
├── org
│   ├── common
│   │   ├── api
│   │   ├── interceptors
│   │   ├── models
│   │   └── utils
│   ├── config
│   ├── pet_store/api
│   │   ├── endpoints
│   │   ├── models
│   │   │   ├── pet
│   │   │   └── user
│   │   └── services
│   └── swaglabs
│       ├── config
│       ├── pages
│       └── services 
└── resources

src/test/java
├── java
│   ├── common
│   │   └── unit
│   ├── cucumber/org/pet_store
│   │   ├── runner
│   │   └── steps
│   └── testng/org
│       ├── regression
│       │   ├── pet
│       │   └── user
│       └── smoke
└── resources
    └── features