plugins {
    id("java")
    checkstyle
}

group = "br.com.dio"
version = "1.0-SNAPSHOT"
val mapstructVersion = "1.5.5.Final"
val lombokVersion = "1.18.30"
val lombokMapstructBinding = "0.2.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    implementation("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBinding")

    compileOnly("org.projectlombok:lombok:$lombokVersion")

    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBinding")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

}

tasks.withType<Checkstyle>().configureEach{
    reports{
        xml.required = true
        xml.required = true
    }
}

tasks.checkstyleMain{
    source = fileTree("src/main/java")
}