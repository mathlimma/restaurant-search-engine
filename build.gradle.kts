plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	jacoco
}

group = "br.com"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// web/common
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// observability
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.github.loki4j:loki-logback-appender:1.4.1")
	implementation("io.micrometer:micrometer-registry-prometheus")

	// csv reader
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.11.2")

	// validation
	implementation("org.springframework.boot:spring-boot-starter-validation:3.3.5")

	// testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
	testImplementation("org.mockito:mockito-core:5.5.0")
	testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
	testImplementation("org.assertj:assertj-core:3.24.2")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jacoco {
	toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
		html.required.set(true)
	}
}
