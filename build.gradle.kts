plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.graalvm.python") version "25.0.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}

group = "com.acme"
version = "0.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    // GraalVM Polyglot and Python embedding dependencies
    implementation("org.graalvm.polyglot:polyglot:25.0.0")
    implementation("org.graalvm.python:python-embedding:25.0.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

graalPy {
    packages.set(setOf("pygal==3.0.5"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

var defaultJvmArgs = listOf(
    "--add-opens", "java.base/java.lang=ALL-UNNAMED",
    "--enable-native-access=ALL-UNNAMED",
    "--sun-misc-unsafe-memory-access=allow",
)

tasks.bootRun {
    jvmArgs = defaultJvmArgs
}

tasks.test {
    jvmArgs(defaultJvmArgs)
    useJUnitPlatform()
}
