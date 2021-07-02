repositories {
    mavenCentral()
}

plugins {
    id ("org.springframework.boot") version "2.5.0"
    id ("io.spring.dependency-management") version "1.0.11.RELEASE"
    id ("java")
    id ("com.palantir.docker") version "0.26.0"

}

group = "ru.sberit"
version = "0.0.1-SNAPSHOT"

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "com.palantir.docker")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation ("org.springframework.boot:spring-boot-starter-validation:2.4.0")
        implementation ("org.springframework.data:spring-data-jpa:2.1.9.RELEASE")
        implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation ("org.springframework.boot:spring-boot-starter-data-rest")
        implementation ("org.springframework.boot:spring-boot-starter-tomcat")
        implementation ("org.postgresql:postgresql:42.2.21")

        testImplementation ("org.springframework.boot:spring-boot-starter-test")
        testImplementation ("org.springframework.boot:spring-boot-starter-data-jpa")
        testImplementation ("org.liquibase:liquibase-core")
        developmentOnly ("org.springframework.boot:spring-boot-devtools")

        // https://projectlombok.org
        compileOnly ("org.projectlombok:lombok:1.18.20")
        annotationProcessor ("org.projectlombok:lombok:1.18.20")

        implementation ("com.google.guava:guava:29.0-jre")
        implementation ("io.springfox:springfox-spring-webmvc:2.10.5")
        implementation ("io.springfox:springfox-swagger2:2.10.5")
        implementation ("io.springfox:springfox-swagger-ui:2.10.5")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
    }
}

tasks.bootRun {
    dependsOn("web:bootRun")
    dependsOn("service:bootRun")
}

tasks.register("dockers") {
    dependsOn("web:docker")
    dependsOn("service:docker")
    dependsOn("service-db:docker")
    doLast {
        println("Images built!")
    }
}
