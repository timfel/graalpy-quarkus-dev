# Setup

- Use Graal JDK 25
$ ./gradlew bootRun

# Demo: Spring Boot code reloading works with Python resources

Run it from your IDE, with automatic recompilation (see https://docs.spring.io/spring-boot/reference/using/devtools.html#using.devtools.restart)

- Browse to localhost:8080/greet
- Show GreetingResource, edit it, refresh page
- Show GraalPyService -> imports python_functions
- Show python_functions.py
- Change languages list to include "Python", refresh page
