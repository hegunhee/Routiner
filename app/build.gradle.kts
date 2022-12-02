plugins {
    id("android.application")
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
//    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {

    defaultConfig {
        applicationId = "com.hegunhee.routiner"
        versionCode = (3)
        versionName = "1.2"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}
dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.junit)
    androidTestImplementation(libs.testext)
    androidTestImplementation(libs.espresso)

    implementation(libs.bundles.navigation)

    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)

    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.scope)

    //Room DB
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    //Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.gson)
}