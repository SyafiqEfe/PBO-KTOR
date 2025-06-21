plugins {
    application
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.7"
    kotlin("plugin.serialization") version "1.9.22"
}

application {
    mainClass.set("academic.MainAkademikKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    // Ktor core
    implementation("io.ktor:ktor-server-core:2.3.7")
    implementation("io.ktor:ktor-server-netty:2.3.7")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-server-call-logging:2.3.7")
    implementation("io.ktor:ktor-server-auth:2.3.7")
    implementation("io.ktor:ktor-server-auth-jwt:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

    // Ktor additional features
    implementation("io.ktor:ktor-server-status-pages:2.3.7")
    implementation("io.ktor:ktor-server-request-validation:2.3.7")
    implementation("io.ktor:ktor-server-sessions:2.3.7")
    implementation("io.ktor:ktor-server-cors:2.3.7") // Added CORS support

    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.14")

    // JWT
    implementation("com.auth0:java-jwt:4.4.0")

    // Exposed ORM
    implementation("org.jetbrains.exposed:exposed-core:0.45.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.45.0")

    // SQLite
    implementation("org.xerial:sqlite-jdbc:3.43.2.0")

    // Testing
    testImplementation("io.ktor:ktor-server-tests:2.3.7")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.22")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "21"
    targetCompatibility = "21"
}
