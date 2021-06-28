docker {
    setName("my_image_service")
    copySpec.from("build/libs/").into("build/libs/")
}

tasks.docker {
    dependsOn("bootJar")
}
