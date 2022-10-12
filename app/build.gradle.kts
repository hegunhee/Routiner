plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion (32)

    defaultConfig {
        applicationId = "com.hegunhee.routiner"
        minSdkVersion(26)
        targetSdkVersion(32)
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}
dependencies {

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.2")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")


    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.2")

    implementation ("androidx.activity:activity-ktx:1.4.0")
    implementation ("androidx.fragment:fragment-ktx:1.4.1")

    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    //Room DB
    implementation ("androidx.room:room-runtime:2.4.2")
    implementation("androidx.room:room-ktx:2.4.2")
    kapt ("androidx.room:room-compiler:2.4.2")
    kapt ("android.arch.persistence.room:compiler:1.1.1")
    kaptTest ("android.arch.persistence.room:testing:1.1.1")

    //Hilt
    implementation ("com.google.dagger:hilt-android:2.41")
    kapt ("com.google.dagger:hilt-android-compiler:2.41")

    implementation ("com.squareup.retrofit2:converter-gson:2.3.0")
}