/**
 * Ktor version.
 */
val ktorVersion: String = "1.3.2"

plugins {
    application
    kotlin("jvm") version "1.3.70"
}

group = "app.ryss"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven("https://kotlin.bintray.com/ktor")
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://dl.bintray.com/excitement-engineer/ktor-graphql")
}

dependencies {

    // Ktor
    implementation("io.ktor", "ktor-server-netty", ktorVersion)
    implementation("io.ktor", "ktor-metrics", ktorVersion)
    implementation("io.ktor", "ktor-server-core", ktorVersion)
    implementation("io.ktor", "ktor-websockets", ktorVersion)
    implementation("io.ktor", "ktor-jackson", ktorVersion)
    testImplementation("io.ktor", "ktor-server-tests", ktorVersion)

    //Graphql
    implementation("com.github.excitement-engineer", "ktor-graphql", "1.0.0")

    // Logging
    implementation("org.slf4j", "slf4j-api", "2.0.0-alpha1")
    implementation("ch.qos.logback", "logback-classic", "1.3.0-alpha5")
    implementation("io.github.microutils", "kotlin-logging", "1.7.9")

    // Sentry
    implementation("io.sentry", "sentry", "1.7.30")
    implementation("io.sentry", "sentry-logback", "1.7.30")


    // Util
    implementation("org.jetbrains.kotlinx", "kotlinx-cli", "0.2.1")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
}
