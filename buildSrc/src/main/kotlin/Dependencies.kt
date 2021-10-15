object App {
    const val versionCode = 1
    const val versionName = "1.0.0"
}


object Versions {
    const val kotlin         = "1.5.30"
    const val gradle         = "7.0.2"
    const val sqlDelight     = "1.5.1"

    const val material         = "1.4.0"
    const val appcompat        = "1.3.1"
    const val constraintLayout = "2.1.0"

    const val coroutines = "1.5.2"
    const val koin       = "3.1.2"
    const val ktor       = "1.6.3"
    const val glide      = "4.12.0"

    const val minSdk     = 23
    const val compileSdk = 30
    const val targetSdk  = 30

    const val kotlinxSerializationCore = "1.2.2"
    const val kotlinxCoroutinesCore    = "1.5.2-native-mt"
}

object Libraries {
    const val kotlin              = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val gradle              = "com.android.tools.build:gradle:${Versions.gradle}"
    const val sqlDelight          = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"

    const val coroutinesCore    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val koinAndroid       = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCore          = "io.insert-koin:koin-core:${Versions.koin}"
    const val ktorCore          = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val ktorAndroid       = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val glide             = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler     = "com.github.bumptech.glide:compiler:${Versions.glide}"

    object Common {
        const val sqlDelight               = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val kotlinxSerializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerializationCore}"
        const val kotlinxCoroutinesCore    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutinesCore}"
    }

    object Android {
        const val sqlDelight = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object IOs{
        const val ktorClient = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val sqlDelight = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    }
}

object SupportLibraries {
    const val material         = "com.google.android.material:material:${Versions.material}"
    const val appcompat        = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

