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
}

dependencies {
    // Ktor Core
    implementation("io.ktor:ktor-server-core-jvm:2.3.7")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.7")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.7")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.7")
    
    // Static content support
    implementation("io.ktor:ktor-server-http-redirect-jvm:2.3.7")
    implementation("io.ktor:ktor-server-default-headers-jvm:2.3.7")
    
    // Serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    
    // Authentication
    implementation("io.ktor:ktor-server-auth-jvm:2.3.7")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:2.3.7")
    implementation("com.auth0:java-jwt:4.4.0")
    
    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.14")
    
    // Testing
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.7")
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
