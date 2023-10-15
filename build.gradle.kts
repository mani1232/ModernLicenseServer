plugins {
    kotlin("jvm") version "1.9.20-Beta2"
    application
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("nu.studer.rocker") version "3.0.4"
}

group = "ua.mani123"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.fizzed/rocker-compiler
    implementation("com.fizzed:rocker-compiler:1.4.0")
    // https://mvnrepository.com/artifact/com.fizzed/rocker-runtime
    implementation("com.fizzed:rocker-runtime:1.4.0")
    // https://mvnrepository.com/artifact/io.jooby
    implementation("io.jooby:jooby-kotlin:3.0.5")
    implementation("io.jooby:jooby-netty:3.0.5")
    implementation("io.jooby:jooby-yasson:3.0.5")
    implementation ("io.jooby:jooby-conscrypt:3.0.5")
    implementation("io.jooby:jooby-rocker:3.0.5")
    implementation("io.jooby:jooby-caffeine:3.0.5")
    implementation("io.jooby:jooby-logback:3.0.5")

    testImplementation(kotlin("test"))
}

rocker {
    version.set("1.3.0")
    configurations {
        create("main") {
            optimize.set(true)
            templateDir.set(file("src/rocker"))
            outputDir.set(file("src/generated/rocker"))
        }
    }
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}