plugins {
    id("com.android.application") version "8.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.android.library") version "8.3.0" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false
    kotlin("kapt") version "1.9.23"
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(libs.realm.gradle.plugin)
    }
}
