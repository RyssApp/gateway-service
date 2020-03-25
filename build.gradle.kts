import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

/**
 * Ktor version.
 */
val ktorVersion: String = "1.3.2"

plugins {
    application
    kotlin("jvm") version "1.3.71"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "app.ryss"
version = "0.0.1"

application {
    mainClassName = "app.ryss.gateway.LauncherKt"
}

repositories {
    mavenLocal()
    jcenter()
    maven("https://kotlin.bintray.com/ktor")
    maven("https://dl.bintray.com/excitement-engineer/ktor-graphql")
}

dependencies {

    // Ktor
    implementation("io.ktor", "ktor-server-netty", ktorVersion)
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

    // Metrics
    implementation("com.influxdb", "influxdb-client-java", "1.6.0")

    // Sentry
    implementation("io.sentry", "sentry", "1.7.30")
    implementation("io.sentry", "sentry-logback", "1.7.30")

    // Util
    implementation("io.github.cdimascio", "java-dotenv", "5.1.3")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
        }
    }

    "shadowJar"(ShadowJar::class) {
        archiveFileName.set("gateway-service.jar")
    }
}
