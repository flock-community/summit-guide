plugins {
    kotlin("jvm") version "2.0.10"
}

group = "community.flock"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
}

val aigenticVersion = "0.0.7-SNAPSHOT"

dependencies {
    testImplementation(kotlin("test"))
    implementation("community.flock.aigentic:core:$aigenticVersion")

// Also available: community.flock.aigentic:gemini or community.flock.aigentic:ollama
    implementation("community.flock.aigentic:openai:$aigenticVersion")

// Add the ktor client library depending on your platform. CIO is for JVM, Android, Native. For other platforms pick the correct engine: https://ktor.io/docs/client-engines.html#platforms
    implementation("io.ktor:ktor-client-cio:2.3.10")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}