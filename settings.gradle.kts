pluginManagement {
    val springBootVersion: String by settings
    val dependencyManagementVersion: String by settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version dependencyManagementVersion
    }
}
rootProject.name="graalpy-spring-boot-demo"
