plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "daniel.avila.ricknmortykmm.android"
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(Libraries.koinAndroid)

    implementation(SupportLibraries.material)

    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.lifecycle)
    implementation(Libraries.Compose.material)
    implementation(Libraries.Compose.uiToolingPreview)
    implementation(Libraries.Compose.coil)
    implementation(Libraries.Compose.activity)
    implementation(Libraries.Compose.navigation)
}