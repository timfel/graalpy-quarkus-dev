# Setup

- Use Graal JDK 25
$ ./gradlew quarkusDev

# Demo: Quarkus dev mode works with Python resources

$ ./gradlew quarkusDev
- Browse to localhost:8080/greet
- Show GreetingResource.kt, edit it, refresh page
- Show GraalPyService -> imports python_functions
- Show python_functions.py
- Change languages list to include "Python", refresh page
