import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.61"
}

kotlin {
    macosX64("native") {
        binaries {
            executable {
//                freeCompilerArgs += listOf("-Xuse-experimental=kotlin.Experimental")
                //                compilerOpts = mutableListOf("-Xuse-experimental=kotlin.Experimental")
                entryPoint = "xyz.mazuninky.lab3.main"
            }
            binaries.getTest("DEBUG").apply {
                freeCompilerArgs += listOf("-Xlibrary-to-cover=${compilations["main"].output.classesDirs.singleFile.absolutePath}")
            }
        }
    }

    sourceSets {
        val nativeTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
            }
        }
    }
}

repositories {
    mavenCentral()
    jcenter()
}

tasks.create("createCoverageReport") {
    dependsOn("nativeTest")

    description = "Create coverage report"

    doLast {
        val testDebugBinary = kotlin.targets["native"].let { it as KotlinNativeTarget }.binaries.getTest("DEBUG").outputFile
        exec {
            commandLine("xcrun", "llvm-profdata", "merge", "$testDebugBinary.profraw", "-o", "$testDebugBinary.profdata")
        }
        exec {
            commandLine("xcrun", "llvm-cov", "report", "$testDebugBinary", "-instr-profile", "$testDebugBinary.profdata")
        }
    }
}

//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    freeCompilerArgs = listOf("-Xuse-experimental=kotlin.Experimental")
//}
