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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
        kotlinCompilerVersion = Versions.kotlin
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

    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.material)
    implementation(Libraries.Compose.uiToolingPreview)
    implementation(Libraries.Compose.coil)
    implementation(Libraries.Compose.activity)
    implementation(Libraries.Compose.navigation)

    kapt(Libraries.glideCompiler)
}