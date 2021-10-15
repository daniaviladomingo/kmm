plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk
    defaultConfig {
        applicationId = "daniel.avila.ricknmortykmm.android"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = App.versionCode
        versionName = App.versionName
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

dependencies {
    implementation(project(":shared"))

    implementation(SupportLibraries.material)
    implementation(SupportLibraries.appcompat)
    implementation(SupportLibraries.constraintLayout)

    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)
    implementation(Libraries.koinCore)
    implementation(Libraries.koinAndroid)
    implementation(Libraries.ktorCore)
    implementation(Libraries.ktorSerialization)
    implementation(Libraries.ktorAndroid)
    implementation(Libraries.glide)
    kapt(Libraries.glideCompiler)
}