buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libraries.kotlin)
        classpath(Libraries.kotlinSerialization)
        classpath(Libraries.gradle)
        classpath(Libraries.sqlDelight)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}