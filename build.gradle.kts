plugins {
    id("io.spring.dependency-management") version "1.1.0"
    id("jacoco")
    id("java")
    id("org.sonarqube") version "3.0"
    id("org.springframework.boot") version "3.0.5"
}

jacoco {
	toolVersion = "0.8.10"
}

group = "id.ac.ui.cs.advprog"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
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
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	implementation("mysql:mysql-connector-java:8.0.32")
	implementation("org.postgresql:postgresql:42.6.0")
	implementation("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.security:spring-security-config:6.0.2")
	testImplementation("junit:junit:4.13.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.named("test") {
	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
	dependsOn(tasks.test) // tests are required to run before generating the report
	reports {
		xml.required.set(true)
	}
}
