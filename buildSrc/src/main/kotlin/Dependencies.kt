object App {
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val kotlin         = "1.8.22"
    const val gradle         = "8.0.2"
    const val sqlDelight     = "1.5.5"

    const val kotlinCompilerExtensionVersion = "1.4.8"

    const val composeUi       = "1.4.3"
    const val composeMaterial = "1.4.3"
    const val coil            = "2.4.0"
    const val activityCompose = "1.7.2"
    const val navigation      = "2.6.0"
    const val lifecycle       = "2.6.1"

    const val material        = "1.9.0"

    const val coroutines  = "1.7.1"
    const val koinAndroid = "3.4.2"
    const val koinCore    = "3.4.2"
    const val ktor        = "2.3.2"

    const val minSdk     = 23
    const val compileSdk = 33
    const val targetSdk  = 33

    const val kotlinxSerializationCore = "1.5.1"
}

object Libraries {
    const val kotlin                = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerialization   = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val gradle                = "com.android.tools.build:gradle:${Versions.gradle}"
    const val sqlDelight            = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    const val koinAndroid           = "io.insert-koin:koin-android:${Versions.koinAndroid}"

    object Compose {
        const val ui               = "androidx.compose.ui:ui:${Versions.composeUi}"
        const val lifecycle        = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}"
        const val material         = "androidx.compose.material:material:${Versions.composeMaterial}"
        const val activity         = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val navigation       = "androidx.navigation:navigation-compose:${Versions.navigation}"
        const val coil             = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Common {
        const val sqlDelight               = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val sqlDelightExtension      = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
        const val kotlinxSerializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerializationCore}"
        const val kotlinxCoroutinesCore    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val koinCore                 = "io.insert-koin:koin-core:${Versions.koinCore}"

        object Ktor {
            const val core              = "io.ktor:ktor-client-core:${Versions.ktor}"
            const val content           = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
            const val serializationJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
            const val logging           = "io.ktor:ktor-client-logging:${Versions.ktor}"
        }
    }

    object Android {
        const val ktorClient = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val sqlDelight = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object IOs {
        const val ktorClient = "io.ktor:ktor-client-darwin:${Versions.ktor}"
        const val sqlDelight = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    }
}

object SupportLibraries {
    const val material = "com.google.android.material:material:${Versions.material}"
}

