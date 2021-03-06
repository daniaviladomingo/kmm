buildscript {
    val kotlinVersion by extra("1.4.32")

    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.3")
    }
}
group = "daniel.avila.ricknmortykmm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
