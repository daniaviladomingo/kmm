plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-android")
}
group = "daniel.avila.ricknmortykmm"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    // Koin
    implementation("org.koin:koin-android:2.2.0")
    implementation("org.koin:koin-core:2.2.0")

    // Ktor
    implementation("io.ktor:ktor-client-core:1.5.4")
    implementation("io.ktor:ktor-client-serialization:1.5.3")
    implementation("io.ktor:ktor-client-android:1.5.3")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "daniel.avila.ricknmortykmm.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
}