plugins {
    id("io.spring.dependency-management") version "1.1.5"
    id("jacoco")
    id("java")
    id("org.sonarqube") version "5.0.0.4638"
    id("org.springframework.boot") version "3.2.5"
}

jacoco {
	toolVersion = "0.8.12"
}

group = "id.ac.ui.cs.advprog"
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
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("io.jsonwebtoken:jjwt:0.9.0")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("jakarta.servlet:jakarta.servlet-api")
	implementation("jakarta.validation:jakarta.validation-api")
	implementation("javax.xml.bind:jaxb-api:2.3.0")
	implementation("mysql:mysql-connector-java:8.0.32")
	implementation("org.postgresql:postgresql")
	implementation("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.security:spring-security-config")
	testImplementation("junit:junit")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.test {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test) // tests are required to run before generating the report
	reports {
		html.required.set(true)
		xml.required.set(true)
	}
}
