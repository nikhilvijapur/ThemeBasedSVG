plugins {
    kotlin("multiplatform") version "1.9.21"
    id("org.jetbrains.compose") version "1.6.0"
    id("com.android.library") version "8.2.2"
}

kotlin {
    androidTarget()
    jvm()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
            }
        }
        val androidMain by getting
        val jvmMain by getting
        val iosMain by creating {
            dependsOn(commonMain)
        }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosX64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}

android {
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    namespace = "svg.themer"
}
