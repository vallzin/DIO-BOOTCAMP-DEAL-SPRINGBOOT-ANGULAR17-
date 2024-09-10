plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "jdbc-sample"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")

//	implementation("org.flywaydb:flyway-core")
//	implementation("org.flywaydb:flyway-mysql")
	implementation("org.flywaydb:flyway-core:9.0.0") // Use a versão mais recente
	implementation("org.flywaydb:flyway-mysql:9.0.0") // Use a versão mais recente

	compileOnly("org.projectlombok:lombok")

	runtimeOnly("com.mysql:mysql-connector-j")

	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
