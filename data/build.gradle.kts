plugins {
    id("android.library.convention")
    id("android.hilt")
}

android {
    namespace = "com.example.data"
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":domain"))
    
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)
}