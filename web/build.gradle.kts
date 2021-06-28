dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-webflux")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation ("org.springframework.security:spring-security-test")
    implementation ("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")

}

docker {
    setName("my_image_web")
    copySpec.from("build/libs/").into("build/libs/")
}

tasks.docker {
    dependsOn("bootJar")
}
