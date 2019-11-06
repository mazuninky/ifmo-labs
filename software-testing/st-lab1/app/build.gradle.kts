import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    //implementation(kotlin("test"))
    //implementation(kotlin("reflect"))

    implementation(project(":test-dsl"))
    implementation(project(":selenium-ext"))

    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-Xuse-experimental=kotlin.contracts.ExperimentalContracts")
}
