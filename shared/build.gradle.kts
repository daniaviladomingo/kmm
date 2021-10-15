import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libraries.ktorCore)
                implementation(Libraries.ktorSerialization)
                implementation(Libraries.Common.sqlDelight)
                implementation(Libraries.Common.kotlinxSerializationCore)
                implementation(Libraries.Common.kotlinxCoroutinesCore)
                implementation(Libraries.koinCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Libraries.Android.sqlDelight)
                implementation(Libraries.ktorAndroid)
                implementation(Libraries.coroutinesAndroid)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Libraries.IOs.ktorClient)
                implementation(Libraries.IOs.sqlDelight)
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk = Versions.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "daniel.avila.ricknmortykmm.shared.data_cache.sqldelight"
        sourceFolders = listOf("kotlin")
    }
}