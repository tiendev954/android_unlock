plugins {
    id("com.android.application") version "8.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.android.library") version "8.3.0" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.16" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(libs.realm.gradle.plugin)
    }
}
