plugins {
    id("java")
}

group = "Rodnik"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation("org.mockito:mockito-core:3.1.0")
}

dependencies {
    implementation(project(":CatApp:DAO"))
}

tasks.test {
    useJUnitPlatform()
}