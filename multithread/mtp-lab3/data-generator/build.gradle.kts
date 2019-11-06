plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.multiplatform") version "1.3.50"

    // Apply the application plugin to add support for building a CLI application.
    // application
}

kotlin {
    macosX64("native") {
        binaries {
            executable {
                entryPoint = "xyz.mazuninky.main"
            }
        }
    }

    sourceSets {

    }
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()
    jcenter()
}