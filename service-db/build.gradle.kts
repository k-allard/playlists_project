plugins {
    id("org.liquibase.gradle") version "2.0.4"
}

docker {
    setName("my_image_service_migration")
    copySpec.from("src/main/resources/db/changelog").into("src/main/resources/db/changelog")
}

