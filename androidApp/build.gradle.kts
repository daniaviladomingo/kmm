plugins {
    id("com.android.application")
    kotlin("android")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(Libraries.koinAndroid)

    implementation(SupportLibraries.material)

    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.material)
    implementation(Libraries.Compose.uiToolingPreview)
    implementation(Libraries.Compose.coil)
    implementation(Libraries.Compose.activity)
    implementation(Libraries.Compose.navigation)
}