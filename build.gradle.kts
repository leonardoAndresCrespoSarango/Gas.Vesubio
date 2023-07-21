import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("org.springframework.boot") version "2.7.11"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.gas"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven {url= uri("https://jitpack.io") }
}

dependencies {



	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.testng:testng:7.7.0")
// https://mvnrepository.com/artifact/javax.validation/validation-api
	implementation("org.testng:testng:7.7.0")
	implementation("javax.validation:validation-api:2.0.1.Final")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// https://mvnrepository.com/artifact/com.opencsv/opencsv
	implementation("org.apache.poi:poi-ooxml:5.1.0")
	implementation("com.opencsv:opencsv:3.7")


	// https://mvnrepository.com/artifact/com.github.pjfanning/excel-streaming-reader
	implementation("com.github.monitorjbl:excel-streaming-reader:2.2.0")


	//swagger
	implementation("io.springfox:springfox-swagger2:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")

	// OpenCSV
	// agregado
	implementation("org.springframework.boot:spring-boot-starter-parent:2.5.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
