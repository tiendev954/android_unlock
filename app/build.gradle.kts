plugins {
    id("com.android.application")
    id("realm-android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "android.bxt.unlock"
    compileSdk = 34

    defaultConfig {
        applicationId = "android.bxt.unlock"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    /* hilt */
    implementation("com.google.dagger:hilt-android:2.49")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.49")

}

kapt {
    correctErrorTypes = true
}
