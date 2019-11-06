plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
//    implementation(kotlin("test"))
    implementation(kotlin("reflect"))

    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
}
