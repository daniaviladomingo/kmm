plugins{
    id("com.android.application") version Versions.gradle apply false
    id("com.android.library") version Versions.gradle apply false
    id("com.squareup.sqldelight") version Versions.sqlDelight apply false
    kotlin("android") version Versions.kotlin apply false
    kotlin("multiplatform") version Versions.kotlin apply false
    kotlin("plugin.serialization") version Versions.kotlin apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}