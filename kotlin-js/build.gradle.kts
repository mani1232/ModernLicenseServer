plugins {
    kotlin("multiplatform") version "1.9.20-RC"
    application
}

group = "ua.mani123"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(npm("memfs", "> 4.6.0 <=4.6.0"))
}

kotlin {
    js {
        browser {
            //webpackTask {
            //    mainOutputFileName = "chat.js"
            //}
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }
}