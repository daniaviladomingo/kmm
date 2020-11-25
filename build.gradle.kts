buildscript {
    val kotlin_version by extra("1.4.20")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.2")
    }
}
group = "daniel.avila.ricknmortykmm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
