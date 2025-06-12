plugins {
    kotlin("jvm") version "1.9.21"
    id("org.jetbrains.compose") version "1.6.0"
    application
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(project(":"))
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "sample.main.MainKt"
    }
}
