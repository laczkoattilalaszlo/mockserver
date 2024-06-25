import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}

group = "com.laczkoattilalaszlo"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

sourceSets {
	create("integration-test") {
		compileClasspath +=
			sourceSets["main"].output +
			sourceSets["test"].output +				// Use this line to enable integration-test sourceSet to reach classes of test sourceSet
			resources.sourceDirectories +
			configurations["testRuntimeClasspath"]
		runtimeClasspath +=
			sourceSets["main"].output +
			compileClasspath +
			sourceSets["test"].runtimeClasspath
		resources.srcDir("src/integration-test/resources")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	testImplementation ("io.kotest:kotest-runner-junit5:5.9.1")				// Kotest - Test Framework
	testImplementation ("io.kotest:kotest-assertions-core:5.9.1")			// Kotest - Assertions Library
	testImplementation ("io.kotest:kotest-property:5.9.1")					// Kotest - Property Testing

	// To implement MockServer you will need on of the following dependencies:
//	testImplementation("org.mock-server:mockserver-netty:5.15.0")			// MockServer Java API dependency
	testImplementation("org.mock-server:mockserver-junit-jupiter:5.11.1")	// Mockserver JUnit5 Extension dependency
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Test>().configureEach {										// Kotest - Test Framework
	useJUnitPlatform()
}

tasks.register<Test>("integrationTest") {
	testClassesDirs = sourceSets["integration-test"].output.classesDirs
	classpath = sourceSets["integration-test"].runtimeClasspath
}
