plugins {
    kotlin("multiplatform") version "1.9.20-RC"
    application
    java
}

group = "ua.mani123"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

}

kotlin {
    js {
        browser {
        }
        binaries.executable()
    }
}