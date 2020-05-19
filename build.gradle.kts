import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("nu.studer.jooq") version "4.2"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
}

group = "com.gt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.postgresql:postgresql")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.expediagroup:graphql-kotlin-spring-server:2.1.1")
    implementation("com.expediagroup:graphql-kotlin-schema-generator:2.1.1")
    implementation("joda-time:joda-time")
    implementation("com.graphql-java:java-dataloader:2.2.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

apply(from = "useJooq.gradle")

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
