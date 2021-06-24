repositories {
    mavenCentral()
}

plugins {
    id ("org.springframework.boot") version "2.5.0"
    id ("io.spring.dependency-management") version "1.0.11.RELEASE"
    id ("java")
}

group = "ru.sberit"
version = "0.0.1-SNAPSHOT"

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
        implementation ("org.springframework.boot:spring-boot-starter-web")
        implementation ("org.springframework.boot:spring-boot-starter-security")
        implementation ("org.springframework.security:spring-security-test")
        implementation ("org.springframework.boot:spring-boot-starter-validation:2.4.0")
        implementation ("org.springframework.data:spring-data-jpa:2.1.9.RELEASE")
        implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation ("org.springframework.boot:spring-boot-starter-data-rest")
        implementation ("org.springframework.boot:spring-boot-starter-tomcat")
        implementation ("org.postgresql:postgresql:42.2.21")
        implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation ("org.springframework.boot:spring-boot-starter-data-rest")
        implementation ("org.springframework.boot:spring-boot-starter-tomcat")
        implementation ("org.postgresql:postgresql:42.2.21")

        testImplementation ("org.springframework.boot:spring-boot-starter-test")
        testImplementation ("org.springframework.boot:spring-boot-starter-data-jpa")
        testImplementation ("org.liquibase:liquibase-core")
        developmentOnly ("org.springframework.boot:spring-boot-devtools")
        runtimeOnly("org.postgresql:postgresql")

        // https://projectlombok.org
        compileOnly ("org.projectlombok:lombok:1.18.20")
        annotationProcessor ("org.projectlombok:lombok:1.18.20")

        implementation ("com.google.guava:guava:29.0-jre")
        implementation ("io.springfox:springfox-spring-webmvc:2.10.5")
        implementation ("io.springfox:springfox-swagger2:2.10.5")
        implementation ("io.springfox:springfox-swagger-ui:2.10.5")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

}

tasks.bootRun {
    dependsOn("web:bootRun")
    dependsOn("service:bootRun")
}
